package com.epam.star.dao.H2dao;

import com.epam.star.dao.StatusDao;
import com.epam.star.entity.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2StatusDao extends AbstractH2Dao implements StatusDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_STATUS = "INSERT INTO  Status VALUES (?, ?)";
    private static final String DELETE_STATUS = "DELETE FROM status WHERE id = ?";
    private Connection conn;

    public H2StatusDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Status findByStatusName(String name) throws DaoException{
        String sql = "SELECT * FROM status WHERE status_name = " + "'" + name + "'";
        Status status = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            status = getStatusFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public Status getElement(int ID) throws DaoException{
        String sql = "SELECT * FROM status WHERE id = " + ID;
        Status status = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
            status = getStatusFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public String insert(Status status) throws DaoException{
        String statuss = "Status do not added";

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(ADD_STATUS);
            prstm.setString(1, null);
            prstm.setString(2, status.getStatusName());
            prstm.execute();
            statuss = "Status added successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return statuss;
    }

    @Override
    public String deleteElement(int ID) throws DaoException{
        return null;
    }

    @Override
    public String updateElement(Status entity) throws DaoException{
        return null;
    }

    private Status getStatusFromResultSet(ResultSet resultSet) throws DaoException{
        Status status = new Status();
        try {
            status.setId(resultSet.getInt("id"));
            status.setStatusName(resultSet.getString("status_name"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return status;
    }
}
