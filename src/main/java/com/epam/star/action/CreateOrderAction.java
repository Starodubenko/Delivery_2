package com.epam.star.action;

import com.epam.star.H2dao.DaoFactory;
import com.epam.star.dao.GoodsDao;
import com.epam.star.dao.OrderDao;
import com.epam.star.dao.PeriodDao;
import com.epam.star.dao.StatusDao;
import com.epam.star.entity.AbstractUser;
import com.epam.star.entity.Client;
import com.epam.star.entity.Order;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateOrderAction implements Action {

    ActionResult client = new ActionResult("client",true);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        Order order = createOrder(request);

        DaoFactory daoFactory = DaoFactory.getInstance();
        OrderDao orderDao = daoFactory.getOrderDao();
        orderDao.insert(order);

        AbstractUser user = (AbstractUser)(request.getSession().getAttribute("user"));
        request.getSession().setAttribute("todayOrders",LoginAction.getTodayOrdersFromDataBase(user,orderDao));
        request.getSession().setAttribute("pastOrders", LoginAction.getPastOrdersFromDataBase(user, orderDao));

        return client;
    }

    private Order createOrder(HttpServletRequest request) {

        PeriodDao periodDao = null;
        GoodsDao goodsDao = null;
        StatusDao statusDao = null;
        try {
            DaoFactory daoFactory = DaoFactory.getInstance();
            periodDao = daoFactory.getPeriodDao();
            goodsDao = daoFactory.getGoodsDao();
            statusDao = daoFactory.getStatusDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Order order = new Order();
        order.setUser((Client) request.getSession().getAttribute("user"));
        order.setCount(Integer.parseInt(request.getParameter("goodscount")));
        order.setPeriod(periodDao.findByPeriod(Time.valueOf(request.getParameter("deliverytime")))); //check for null
        order.setGoods(goodsDao != null ? goodsDao.findByGoodsName(request.getParameter("goodsname")) : null);
        try {
            order.setDeliveryDate(new SimpleDateFormat("dd.mm.yyyy").parse(request.getParameter("deliverydate")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setAdditionalInfo(request.getParameter("additionalinformation"));
        order.setStatus(statusDao != null ? statusDao.findByStatusName("waiting") : null);
        order.setOrderDate(new Date());

        return order;
    }
}
