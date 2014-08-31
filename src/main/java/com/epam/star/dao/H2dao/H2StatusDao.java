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
    public Status findByStatusName(String name) {
        String sql = "SELECT * FROM status WHERE status_name = " + "'" + name +"'";
        Status status = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            status = getStatusFromResultSet(resultSet);
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
        return status;
    }

    @Override
    public Status getElement(int ID) {
        String sql = "SELECT * FROM status WHERE id = " + ID;
        Status status = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            status = getStatusFromResultSet(resultSet);
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
        return status;
    }

    @Override
    public String insert(Status status) {
        String statuss = "Status do not added";

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(ADD_STATUS);
            prstm.setString(1, null);
            prstm.setString(2, status.getStatusName());
            prstm.execute();
            statuss = "Status added successfully";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return statuss;
    }

    @Override
    public String deleteElement(int ID) {
        return null;
    }

    @Override
    public String updateElement(Status entity) {
        return null;
    }

    private Status getStatusFromResultSet(ResultSet resultSet) {
        Status status = new Status();
        try {
            resultSet.next();
            status.setId(resultSet.getInt("id"));
            status.setStatusName(resultSet.getString("status_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
