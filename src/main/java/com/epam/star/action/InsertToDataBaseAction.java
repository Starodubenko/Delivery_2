package com.epam.star.action;

import com.epam.star.entity.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InsertToDataBaseAction implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsertToDataBaseAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request) throws SQLException {


        String tableName = request.getParameter("TableName");
        System.out.println(tableName);
        if (tableName != null && tableName != "") {

            try {
                Class.forName("org.h2.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/F:/Видео Epam/db/FPDB", "Rody", "1"); //"jdbc:h2:tcp://localhost/F:/Видео Epam/db/FPDB", "Rody", "1"
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            ResultSetMetaData resultSetMD = resultSet.getMetaData();

            List<Element> namesOfColumns = new ArrayList<>();

            int i = 2;
            while(i <= resultSetMD.getColumnCount()){
                if (resultSetMD.getColumnName(i) != "ID")
                    namesOfColumns.add(new Element(FirsUpperSymbol(resultSetMD.getColumnName(i).toLowerCase())));
                i++;
            }

            String valuesString = "(";
            String colsNamesString = "(";

            String[] parameters = request.getParameterValues("ValuesForInsertToDB");
            for (String parameter : parameters) {
                valuesString = valuesString + "'" + parameter + "'" +",";
            }
            valuesString = valuesString.substring(0,valuesString.length()-1) + ")";
            LOGGER.debug("String created for values of construction which insert data to database: {}",valuesString);

            for (Element nameOfColumn : namesOfColumns) {
                colsNamesString = colsNamesString + nameOfColumn.getElement() + ",";
            }
            colsNamesString = colsNamesString.substring(0,colsNamesString.length()-1) + ")";
            LOGGER.debug("String created for names of columns of table of construction which insert data to database: {}", namesOfColumns);

            statement.execute("insert into " + tableName + " " + colsNamesString + " values " + valuesString);

            connection.close();
        }
        return null;
    }

    private String FirsUpperSymbol(String s){
        String result = s.substring(0,1).toUpperCase() + s.substring(1);
        return result;
    }
}
