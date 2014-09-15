package com.epam.star.dao.H2dao;

import com.epam.star.dao.PositionDao;
import com.epam.star.entity.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class H2PositionDao extends AbstractH2Dao implements PositionDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2PositionDao.class);
    private static final String ADD_POSITION = "INSERT INTO  positions VALUES (?, ?)";
    private static final String DELETE_POSITION = "DELETE FROM positions WHERE id = ?";
    private Connection conn;

    public H2PositionDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Position findByPositionName(String name) throws DaoException {
        String sql = "SELECT * FROM positions WHERE position_name = " + "'" + name + "'";
        Position position = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                position = getStatusFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return position;
    }

    @Override
    public Position getElement(int ID) throws DaoException {
        String sql = "SELECT * FROM positions WHERE id = " + ID;
        Position position = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                position = getStatusFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return position;
    }

    @Override
    public String insert(Position entity) throws DaoException {
        return null;
    }

    @Override
    public String deleteElement(int ID) throws DaoException {
        return null;
    }

    @Override
    public String updateElement(Position entity) throws DaoException {
        return null;
    }

    private Position getStatusFromResultSet(ResultSet resultSet) throws DaoException {
        Position position = new Position();
        try {
            position.setId(resultSet.getInt("id"));
            position.setPositionName(resultSet.getString("position_name"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return position;
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
        return null;
    }

    @Override
    public int getRecordsCount() {
        return 0;
    }
}
