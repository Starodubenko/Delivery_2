package com.epam.star.action;

import com.epam.star.pool.ConnectionPool;
import com.epam.star.entity.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetDataFromDataBaseAction implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetDataFromDataBaseAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        String tableName = request.getParameter("TableName");
        LOGGER.debug("Name of table which got in GetDataFromDBAction: {}", tableName);
        if (tableName != null && tableName != "") {

        Connection connection = ConnectionPool.getConnectionFromPool("jdbc:h2:tcp://localhost/F:/Видео Epam/db/FPDB","Rody","1");
        Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData resultSetMD = resultSet.getMetaData();

            List<List> result = new ArrayList<>();
            List<Element> titlesOfColumns = new ArrayList<>();

            boolean f = true;
            while (resultSet.next()) {
                List<Element> row = new ArrayList<>();
                int i = 1;
                    while(i <= resultSetMD.getColumnCount()){

                        if (f) titlesOfColumns.add(new Element(FirsUpperSymbol(resultSetMD.getColumnName(i).toLowerCase())));
                        row.add(new Element(resultSet.getString(i)));
                        i++;
                    }
                f = false;

                result.add(row);
            }
            request.setAttribute("titlesOfColumns", titlesOfColumns);
            request.setAttribute("result", result);

            ConnectionPool.addConnectionToPool(connection);
        }
            return null;
    }

    private String FirsUpperSymbol(String s){
        String result = s.substring(0,1).toUpperCase() + s.substring(1);
        return result;
    }
}
