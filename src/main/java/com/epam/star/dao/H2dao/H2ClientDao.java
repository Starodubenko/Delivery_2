package com.epam.star.dao.H2dao;

import com.epam.star.dao.ClientDao;
import com.epam.star.dao.PositionDao;
import com.epam.star.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class H2ClientDao extends AbstractH2Dao implements ClientDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_CLIENT = "INSERT INTO  users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String RANGE_CLIENT = "SELECT * FROM users LIMIT ? OFFSET ?";
    private static final String UPDATE_CLIENT = "UPDATE users SET id = ?, login = ?, password = ?, firstname = ?, lastname = ?, middlename = ?," +
            "address = ?, telephone = ?, mobilephone = ?, identitycard = ?, workbook = ?, rnn = ?, sik = ?, position_id = ?, virtual_balance = ? WHERE id = ?";

    protected H2ClientDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    @Override
    public int getRecordsCount() {
        int result = 0;

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement("SELECT COUNT(*) FROM users");
            resultSet = prstm.executeQuery();
            while (resultSet.next())
                result = resultSet.getInt("count(*)");
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return result;
    }

    @Override
    public List findRangeWithValue(int firstPosition, int count, String columnName, String desiredValue) {

        String RANGE_CLIENT = null;
        try {
            Integer.parseInt(desiredValue);
            RANGE_CLIENT = "SELECT * FROM users WHERE " + columnName + " = " + desiredValue + " LIMIT ? OFFSET ?";
        } catch (Exception e) {
            columnName = columnName.replace(" ", "");
            RANGE_CLIENT = "SELECT * FROM users WHERE " + columnName + " = " + "'" + desiredValue + "'" + " LIMIT ? OFFSET ?";
        }

        List<Client> result = new ArrayList<>();

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(RANGE_CLIENT);
            prstm.setInt(1, count);
            prstm.setInt(2, firstPosition);
            resultSet = prstm.executeQuery();
            while (resultSet.next()) {
                result.add(getClientFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return result;
    }

    @Override
    public Client findByLogin(String login) throws DaoException {
        String sql = "select * from clients where login = " + "'" + login + "'";

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next()) ;
            client = getClientFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return client;
    }

    @Override
    public Client findByName(String name) throws DaoException {
        String sql = "select * from clients where firstname = " + "'" + name + "'";

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next()) ;
            client = getClientFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return client;
    }

    @Override
    public Client findBySurnameName(String surName) throws DaoException {
        String sql = "select * from clients where surname = " + "'" + surName + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next()) ;
            client = getClientFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return client;
    }

    @Override
    public Client findByAddress(String address) throws DaoException {
        String sql = "select * from clients where address= " + "'" + address + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next()) ;
            client = getClientFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return client;
    }

    @Override
    public Client findByTelephone(String telephone) throws DaoException {
        String sql = "select * from clients where telephone = " + "'" + telephone + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next()) ;
            client = getClientFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return client;
    }

    @Override
    public Client findByMobilephone(String mobilephone) throws DaoException {
        String sql = "select * from clients where surname = " + "'" + mobilephone + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return getClientFromResultSet(resultSet);
    }

    @Override
    public Client findByCredentials(String login, String password) throws DaoException {
        String sql = "SELECT *" +
                " FROM USERS" +
                " inner join POSITIONS" +
                " on users.POSITION_ID = positions.id" +
                " where POSITION_ID = 11 and LOGIN = " + "'" + login + "'" + " and PASSWORD = " + "'" + password + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next())
                client = getClientFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return client;
    }

    @Override
    public List findRange(int firstPosition, int count) {
        List<Client> result = new ArrayList<>();

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(RANGE_CLIENT);
            prstm.setInt(1, count);
            prstm.setInt(2, firstPosition);
            resultSet = prstm.executeQuery();
            while (resultSet.next())
                result.add(getClientFromResultSet(resultSet));
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return result;
    }

    @Override
    public Client findById(int ID) throws DaoException {
        String sql = "select * from users where id = " + ID;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        Client client = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next())
                client = getClientFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return client;
    }

    @Override
    public String insert(Client client) throws DaoException {

        String status = "Client do not added";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(ADD_CLIENT);
            prstm.setString(1, null);
            prstm.setString(2, client.getLogin());
            prstm.setString(3, client.getPassword());
            prstm.setString(4, client.getFirstName());
            prstm.setString(5, client.getLastName());
            prstm.setString(6, client.getMiddleName());
            prstm.setString(7, client.getAddress());
            prstm.setString(8, client.getTelephone());
            prstm.setString(9, client.getMobilephone());
            prstm.setString(10, null);
            prstm.setString(11, null);
            prstm.setString(12, null);
            prstm.setString(13, null);
            prstm.setInt(14, client.getRole().getId());
            prstm.setBigDecimal(15, client.getVirtualBalance());
            prstm.execute();
            status = "Client added successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public String deleteElement(int ID) throws DaoException {
        String status = "Client do not deleted";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(UPDATE_CLIENT);
            prstm.setString(1, String.valueOf(ID));
            prstm.execute();
            status = "Client successfully deleted";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public String updateElement(Client client) throws DaoException {
        String status = "Client do not updated";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(UPDATE_CLIENT);
            prstm.setInt(1, client.getId());
            prstm.setString(2, client.getLogin());
            prstm.setString(3, client.getPassword());
            prstm.setString(4, client.getFirstName());
            prstm.setString(5, client.getLastName());
            prstm.setString(6, client.getMiddleName());
            prstm.setString(7, client.getAddress());
            prstm.setString(8, client.getTelephone());
            prstm.setString(9, client.getMobilephone());
            prstm.setString(10, null);
            prstm.setString(11, null);
            prstm.setString(12, null);
            prstm.setString(13, null);
            prstm.setInt(14, client.getRole().getId());
            prstm.setBigDecimal(15, client.getVirtualBalance());
            prstm.setInt(16, client.getId());
            prstm.executeUpdate();
            status = "Client updated successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    private Client getClientFromResultSet(ResultSet resultSet) throws DaoException {

        PositionDao positionDao = daoManager.getPositionDao();

        Client client = new Client();
        try {
            client.setId(resultSet.getInt("id"));
            client.setLogin(resultSet.getString("login"));
            client.setPassword(resultSet.getString("password"));
            client.setFirstName(resultSet.getString("firstname"));
            client.setLastName(resultSet.getString("lastname"));
            client.setMiddleName(resultSet.getString("middlename"));
            client.setAddress(resultSet.getString("address"));
            client.setTelephone(resultSet.getString("telephone"));
            client.setMobilephone(resultSet.getString("mobilephone"));
            client.setRole(positionDao.findById(resultSet.getInt("position_id")));
            client.setVirtualBalance(new BigDecimal(resultSet.getInt("virtual_balance")));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return client;
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
}
