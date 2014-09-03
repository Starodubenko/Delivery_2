package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.OrderDao;
import com.epam.star.dao.StatusDao;
import com.epam.star.entity.Order;
import com.epam.star.entity.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CancelOrderAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(CancelOrderAction.class);
    private ActionResult client = new ActionResult("client");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        String[] idChecedOrders = request.getParameterValues("IdOrder");

        if (idChecedOrders.length > 0) {
            DaoFactory daoFactory = DaoFactory.getInstance();
            DaoManager daoManager = daoFactory.getDaoManager();

            daoManager.beginTransaction();
            try {
                StatusDao statusDao = daoManager.getStatusDao();
                OrderDao orderDao = daoManager.getOrderDao();

                for (String id : idChecedOrders) {
                    int index = Integer.parseInt(id);
                    Order order = orderDao.getElement(index);
                    Status status = statusDao.findByStatusName("canceled");
                    order.setStatus(status);

                    orderDao.updateElement(order);
                }
            } catch (Exception e){
                daoManager.rollback();
            }finally {
                daoManager.closeConnection();
            }
        } else
            LOGGER.error("The order was not selected {}");
        request.setAttribute("SelectOrderError","Did not selected orders");
        return client;
    }
}
