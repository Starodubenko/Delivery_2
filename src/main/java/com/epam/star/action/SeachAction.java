package com.epam.star.action;

import com.epam.star.dao.H2dao.AbstractH2Dao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.entity.AbstractEntity;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class SeachAction<T extends AbstractEntity, E extends AbstractH2Dao> implements Action {

    private ActionResult dispatcher = new ActionResult("dispatcher", true);
    private ActionResult director = new ActionResult("direcror", true);
    private ActionResult admin = new ActionResult("admin", true);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        String fieldValue = request.getParameter("searcheValue");
        String daoName = (String) request.getAttribute("daoName");
        String pageName = (String) request.getAttribute("pageName");
        //todo daoName from table swicher at DispatcherPage
        //todo remember the selected table at DispatcherPage
        DaoFactory daoFactory = DaoFactory.getInstance();
        DaoManager daoManager = daoFactory.getDaoManager();

            daoManager.closeConnection();
        return dispatcher;
    }

    private T find(String parameterName, String parameterValue, E genericdao, String pageName){



        return null;
    }
}
