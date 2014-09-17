package com.epam.star.action;

import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ClientDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class SeachAction implements Action {

    private ActionResult dispatcher = new ActionResult("dispatcher");
    private ActionResult director = new ActionResult("direcror");
    private ActionResult admin = new ActionResult("admin");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2ClientDao clientDao = daoManager.getClientDao();

        Pagination pagination = new Pagination();
        pagination.executePaginationAction(request, clientDao, "dispatcher", "clients");

        daoManager.closeConnection();
        return dispatcher;
    }
}
