package com.epam.star.action.order;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2OrderDao;
import com.epam.star.dao.util.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/findOrder")
public class AjaxFindOrderAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxFindOrderAction.class);
    private ActionResult orderr = new ActionResult("ordersBlock");
    private ActionResult jsonn = new ActionResult("json");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2OrderDao orderDao = daoManager.getOrderDao();

        Pagination pagination = new Pagination();
        pagination.paginationEntity(request, orderDao, "orders");

        daoManager.closeConnection();

        return orderr;
    }
}
