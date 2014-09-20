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
import java.util.ArrayList;
import java.util.List;

public class H2EmployeeDao extends AbstractH2Dao implements EmployeeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_EMPLOYEE = "INSERT INTO  USERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_EMPLOYEE = "UPDATE users SET id = ?, login = ?, password = ?, firstname = ?, lastname = ?, middlename = ?," +
            "address = ?, telephone = ?, mobilephone = ?, identitycard = ?, workbook = ?, rnn = ?, sik = ?, position_id = ?, virtual_balance = ? WHERE id = ?";
    private static final String DELETE_EMPLOYEE = "DELETE FROM users WHERE id = ?";
    private static final String RANGE_EMPLOYEE = "SELECT * FROM users LIMIT ? OFFSET ?";

    protected H2EmployeeDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    @Override
    public Employee findByCredentials(String login, String password) throws DaoException {
        String sql = "SELECT *" +
                " FROM USERS" +
                " inner join POSITIONS" +
                " on users.POSITION_ID = positions.id" +
                " where POSITION_ID != 11 and LOGIN = " + "'" + login + "'" + "and PASSWORD = " + "'" + password + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Employee employee = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next())
                employee = getEmployeeFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return employee;
    }

    @Override
    public Employee findById(int ID) throws DaoException {
        return null;
    }

    @Override
    public String insert(Employee employee) throws DaoException {
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
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public String deleteElement(int ID) throws DaoException {
        return null;
    }

    @Override
    public String updateElement(Employee employee) throws DaoException {
        String status = "Employee do not updated";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(UPDATE_EMPLOYEE);
            prstm.setInt(1, employee.getId());
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
            prstm.setInt(16, employee.getId());
            prstm.executeUpdate();
            status = "Employee updated successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    private Employee getEmployeeFromResultSet(ResultSet resultSet) throws DaoException {

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
            employee.setRole(positionDao.findById(resultSet.getInt("position_id")));
            employee.setVirtualBalance(new BigDecimal(resultSet.getInt("virtual_balance")));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return employee;
    }

    private void closeStatement(PreparedStatement prstm, ResultSet resultSet) {
        if (prstm != null) {
            try {
                prstm.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public List findRange(int startRow, int rowsCount) {
        List<Employee> result = new ArrayList<>();

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(RANGE_EMPLOYEE);
            prstm.setInt(1, rowsCount);
            prstm.setInt(2, startRow);
            resultSet = prstm.executeQuery();
            while (resultSet.next()) {
                result.add(getEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return result;
    }

    @Override
    public int getRecordsCount() {
        return 0;
    }

    @Override
    public List findRangeWithValue(int firstPosition, int count, String columnName, String desiredValue) {
        List<Employee> result = new ArrayList<>();

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(RANGE_EMPLOYEE);
            prstm.setInt(1, count);
            prstm.setInt(2, firstPosition);
            resultSet = prstm.executeQuery();
            while (resultSet.next()) {
                Employee employee = getEmployeeFromResultSet(resultSet);
                if (
                        String.valueOf(employee.getId()).contains(desiredValue) |
                                employee.getLogin().contains(desiredValue) |
                                employee.getPassword().contains(desiredValue) |
                                employee.getLastName().contains(desiredValue) |
                                employee.getMiddleName().contains(desiredValue) |
                                employee.getAddress().contains(desiredValue) |
                                employee.getTelephone().contains(desiredValue) |
                                employee.getMobilephone().contains(desiredValue) |
                                employee.getIdentityCard().contains(desiredValue) |
                                employee.getWorkBook().contains(desiredValue) |
                                employee.getRNN().contains(desiredValue) |
                                employee.getSIK().contains(desiredValue)

                        )
                    result.add(getEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return result;
    }
}
