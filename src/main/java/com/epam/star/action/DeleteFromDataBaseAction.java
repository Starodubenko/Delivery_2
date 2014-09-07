package com.epam.star.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeleteFromDataBaseAction implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetDataFromDataBaseAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request) throws SQLException{
//        String tableName = request.getParameter("TableName");
//        LOGGER.debug("Name of table which got in GetDataFromDBAction: {}", tableName);
//
//        DaoFactory daoFactory = DaoFactory.getInstance();
//        ClientDao clientDao = daoFactory.getClientDao();
//        Client registration = clientDao.findByName("Lina");
//
//
//
        return null;
    }
}
