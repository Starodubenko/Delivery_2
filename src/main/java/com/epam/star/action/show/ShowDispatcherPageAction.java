package com.epam.star.action.show;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.Pagination;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ClientDao;
import com.epam.star.dao.H2dao.H2OrderDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ShowDispatcherPageAction implements Action {
    private ActionResult dispatcher = new ActionResult("dispatcher");
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2ClientDao clientDao = daoManager.getClientDao();
        H2OrderDao orderDao = daoManager.getOrderDao();

        Pagination pagination = new Pagination();
        pagination.executePaginationAction(request, clientDao, "dispatcher", "clients");
        pagination.executePaginationAction(request, orderDao, "dispatcher", "orders");

        daoManager.closeConnection();

        return dispatcher;
    }
}
