package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.dao.util.Pagination;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2OrderDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AjaxSelectOrderStatus implements Action {
    private ActionResult ordersBlock = new ActionResult("ordersBlock");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2OrderDao orderDao = daoManager.getOrderDao();

        Pagination pagination = new Pagination();
        pagination.paginationEntity(request, orderDao, "orders");

        daoManager.closeConnection();

        return ordersBlock;
    }
}
