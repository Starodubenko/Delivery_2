package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.SearchePagination;
import com.epam.star.dao.Dao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2OrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AjaxFindOrderAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxFindOrderAction.class);
    private ActionResult orderr = new ActionResult("ordersBlock");
    private ActionResult jsonn = new ActionResult("json");

    private Dao dao;


    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2OrderDao orderDao = daoManager.getOrderDao();

        SearchePagination pagination = new SearchePagination();
        pagination.executePaginationAction(request, orderDao, "dispatcher", "orders");

        daoManager.closeConnection();

        return orderr;
    }

    public void determineEntity() {
        dao = DaoFactory.getInstance().getDaoManager().getClientDao();
    }
}
