package com.epam.star.action.Client;

import com.epam.star.action.*;
import com.epam.star.dao.Dao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ClientDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@MappedAction("GET/findClient")
public class AjaxFindClientAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjaxFindClientAction.class);
    private ActionResult orderr = new ActionResult("clientsBlock");
    private ActionResult jsonn = new ActionResult("json");

    private Dao dao;

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2ClientDao clientDao = daoManager.getClientDao();

        SearchePagination pagination = new SearchePagination();
        pagination.executePaginationAction(request, clientDao, "dispatcher", "clients");

        daoManager.closeConnection();

        return orderr;
    }

    public void determineEntity() {
        dao = DaoFactory.getInstance().getDaoManager().getClientDao();
    }
}
