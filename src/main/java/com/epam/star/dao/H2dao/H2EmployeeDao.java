package com.epam.star.dao.H2dao;

import com.epam.star.dao.EmployeeDao;
import com.epam.star.dao.PositionDao;
import com.epam.star.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2EmployeeDao extends AbstractH2Dao implements EmployeeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_EMPLOYEE = "INSERT INTO  USERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_EMPLOYEE = "DELETE FROM users WHERE id = ?";
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
        }
        return null;
    }

    @Override
    public Employee getElement(int ID) {
        return null;
    }

    @Override
    public String insert(Employee employee) {
        String status = "Employee do not added";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(ADD_EMPLOYEE);
            prstm.setString(1, null);
            prstm.setString(2, employee.getLogin());
            prstm.setString(3, employee.getPassword());
            prstm.setString(4, employee.getFirstName());
            prstm.setString(5, employee.getLastName());
            prstm.setString(6, employee.getMiddleName());
            prstm.setString(7, employee.getAddress());
            prstm.setString(8, employee.getTelephone());
            prstm.setString(9, employee.getMobilephone());
            prstm.setString(10, employee.getIdentityCard());
            prstm.setString(11, employee.getWorkBook());
            prstm.setString(12, employee.getRNN());
            prstm.setString(13, employee.getSIK());
            prstm.setInt(14, employee.getRole().getId());
            prstm.setBigDecimal(15, employee.getVirtualBalance());
            prstm.execute();
            status = "Employee added successfully";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public String deleteElement(int ID) {
        return null;
    }

    @Override
    public String updateElement(Employee employee) {
        return null;
    }

    private Employee getClientFromResultSet(ResultSet resultSet) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        DaoManager daoManager = daoFactory.getDaoManager();
        PositionDao positionDao = daoManager.getPositionDao();

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
            employee.setIdentityCard(resultSet.getString("identitycard"));
            employee.setWorkBook(resultSet.getString("workbook"));
            employee.setRNN(resultSet.getString("rnn"));
            employee.setSIK(resultSet.getString("sik"));
            employee.setRole(positionDao.getElement(resultSet.getInt("position_id")));
            employee.setVirtualBalance(new BigDecimal(resultSet.getInt("virtual_balance")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }
}
