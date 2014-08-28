package com.epam.star.H2dao;

import com.epam.star.dao.EmployeeDao;
import com.epam.star.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2EmployeeDao extends AbstractH2Dao implements EmployeeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_CLIENT = "INSERT INTO  USERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_CLIENT = "DELETE FROM clients WHERE id = ?";
    private Connection conn;

    public H2EmployeeDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Employee findByCredentials(String login, String password) {
        String sql = "SELECT *" +
                " FROM USERS" +
                " inner join POSITIONS" +
                " on users.POSITION_ID = positions.id" +
                " where POSITION_ID != 11 and LOGIN = " + "'" + login + "'" + "and PASSWORD = " + "'" + password + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next())
                return getClientFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (prstm != null) {
                try {
                    prstm.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return  null;
    }

    @Override
    public Employee getElement(int ID) {
        return null;
    }

    @Override
    public String insert(Employee employee) {
        return null;
    }

    @Override
    public String deleteElement(int ID) {
        return null;
    }

    @Override
    public String updateElement(int ID) {
        return null;
    }

    private Employee getClientFromResultSet(ResultSet resultSet) {
        Employee employee = new Employee();
        try {
            employee.setId(resultSet.getInt("id"));
            employee.setLogin(resultSet.getString("login"));
            employee.setPassword(resultSet.getString("password"));
            employee.setFirstName(resultSet.getString("firstname"));
            employee.setLastName(resultSet.getString("lastname"));
            employee.setMiddleName(resultSet.getString("middlename"));
            employee.setAddress(resultSet.getString("address"));
            employee.setTelephone(resultSet.getString("telephone"));
            employee.setMobilephone(resultSet.getString("mobilephone"));
            employee.setRole(resultSet.getString("position_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
