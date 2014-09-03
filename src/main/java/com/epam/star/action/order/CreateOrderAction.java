package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.login.LoginAction;
import com.epam.star.dao.*;
import com.epam.star.dao.h2dao.DaoFactory;
import com.epam.star.dao.h2dao.DaoManager;
import com.epam.star.entity.AbstractUser;
import com.epam.star.entity.Client;
import com.epam.star.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateOrderAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateOrderAction.class);
    ActionResult client = new ActionResult("client",true);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException{

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();
        try {
            OrderDao orderDao = daoManager.getOrderDao();
            Order order = createOrder(request,daoManager);
            orderDao.insert(order);

            AbstractUser user = (AbstractUser)(request.getSession().getAttribute("user"));

            request.getSession().removeAttribute("todayOrders");
            request.getSession().setAttribute("todayOrders", LoginAction.getTodayOrdersFromDataBase(user, orderDao));

            daoManager.commit();
        } catch (Exception e){
            daoManager.rollback();
        }finally {
            daoManager.closeConnection();
        }

        return client;
    }

    private Order createOrder(HttpServletRequest request, DaoManager daoManager) {

        Order order = null;
        try {
            PeriodDao periodDao = daoManager.getPeriodDao();
            GoodsDao goodsDao = daoManager.getGoodsDao();
            StatusDao statusDao = daoManager.getStatusDao();
            ClientDao clientDao = daoManager.getClientDao();

            String paymentType = request.getParameter("PaymentType");
            AbstractUser user = (Client)request.getAttribute("user");
            BigDecimal clientBalance = user.getVirtualBalance();

            boolean f;
            if (paymentType.equals("online") && clientBalance.compareTo(goodsDao.findByGoodsName(request.getParameter("goodsname")).getPrice()) == 0)
                f = true; else  f = false;


            order = new Order();
            order.setUser((Client) request.getSession().getAttribute("user"));
            order.setCount(Integer.parseInt(request.getParameter("goodscount")));
            order.setPeriod(periodDao.findByPeriod(Time.valueOf(request.getParameter("deliverytime"))));
            order.setGoods(goodsDao != null ? goodsDao.findByGoodsName(request.getParameter("goodsname")) : null);
            try {
                order.setDeliveryDate(new SimpleDateFormat("dd.MM.yyyy").parse(request.getParameter("deliverydate")));
            } catch (ParseException e) {
                throw new ActionException(e);
            }
            order.setAdditionalInfo(request.getParameter("additionalinformation"));
            order.setStatus(statusDao != null ? statusDao.findByStatusName("waiting") : null);
            order.setOrderDate(new Date());

            user.setVirtualBalance(user.getVirtualBalance().divide(goodsDao.findByGoodsName("goodsname").getPrice()));
            clientDao.updateElement((Client)user);
        } catch (Exception e){
            request.setAttribute("CreateOrderError","You made a mistake, check all fields");
        }

        return order;
    }
}
