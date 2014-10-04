package com.epam.star.action.order;

import com.epam.star.action.*;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2OrderDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/ajaxChangeOrdersPage")
public class AjaxChangeOrdersPage implements Action {
    private ActionResult dispatcher = new ActionResult("ajaxOrdersTable");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2OrderDao orderDao = daoManager.getOrderDao();

        PaginatedSearch pagination = new PaginatedSearch();
        pagination.executePaginationAction(request, orderDao, "dispatcher", "orders");

        daoManager.closeConnection();

        return dispatcher;
    }
}
