package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.dao.ClientDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.OrderDao;
import com.epam.star.dao.StatusDao;
import com.epam.star.entity.Client;
import com.epam.star.entity.Order;
import com.epam.star.entity.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;

public class AjaxCancelOrderAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxCancelOrderAction.class);
    ActionResult client = new ActionResult("ajaxOrdersTable");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        String[] idCheckedOrders = request.getParameterValues("IdOrder");

        DaoFactory daoFactory = DaoFactory.getInstance();
        DaoManager daoManager = daoFactory.getDaoManager();

        try {
            if (idCheckedOrders.length > 0) {
                daoManager.beginTransaction();

                StatusDao statusDao = daoManager.getStatusDao();
                OrderDao orderDao = daoManager.getOrderDao();

                for (String id : idCheckedOrders) {
                    int index = Integer.parseInt(id);
                    Order order = orderDao.getElement(index);
                    Status status = statusDao.findByStatusName("canceled");
                    order.setStatus(status);

                    orderDao.updateElement(order);

                    returnFunds(order, daoManager);
                }
            } else {
                LOGGER.error("The order was not selected {}");
                request.setAttribute("SelectOrderError", "Did not selected orders");
            }
            daoManager.commit();
        } catch (Exception e) {
            daoManager.rollback();
        } finally {
            daoManager.closeConnection();
        }
        return client;
    }

    private void returnFunds(Order order, DaoManager daoManager) {

        ClientDao clientDao = daoManager.getClientDao();
        Client client = (Client) order.getUser();

        BigDecimal goodsCost = order.getOrderCost();
        BigDecimal clientVBalance = client.getVirtualBalance();
        BigDecimal summ = clientVBalance.add(goodsCost);

        client.setVirtualBalance(summ);

        clientDao.updateElement(client);
    }
}
