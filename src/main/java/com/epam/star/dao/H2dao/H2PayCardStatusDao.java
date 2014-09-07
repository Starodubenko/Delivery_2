package com.epam.star.dao.H2dao;

import com.epam.star.dao.PayCardStatusDao;
import com.epam.star.entity.StatusPayCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class H2PayCardStatusDao extends AbstractH2Dao implements PayCardStatusDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_STATUS_PAY_CARD = "INSERT INTO status_card VALUES (?, ?)";
    private static final String DELETE_STATUS_PAY_CARD = "DELETE FROM status_card WHERE id = ?";
    private Connection conn;

    public H2PayCardStatusDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public StatusPayCard findByStatusName(String name) throws DaoException{
        String sql = "SELECT * FROM status_card WHERE status_name = " + "'" + name + "'";
        StatusPayCard statusPayCard = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                statusPayCard = getStatusPayCardFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }finally {
            closeStatement(prstm,resultSet);
        }
        return statusPayCard;
    }

    @Override
    public StatusPayCard getElement(int ID) throws DaoException{
        String sql = "SELECT * FROM status_card WHERE id = " + ID;
        StatusPayCard statusPayCard = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                statusPayCard = getStatusPayCardFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm,resultSet);
        }
        return statusPayCard;
    }

    @Override
    public String insert(StatusPayCard statusPayCard) throws DaoException{
        String statuss = "StatusPayCard do not added";

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(ADD_STATUS_PAY_CARD);
            prstm.setString(1, null);
            prstm.setString(2, statusPayCard.getStatusName());
            prstm.execute();
            statuss = "StatusPayCard added successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        }finally {
            closeStatement(prstm,null);
        }
        return statuss;
    }

    @Override
    public String deleteElement(int ID) throws DaoException{
        return null;
    }

    @Override
    public String updateElement(StatusPayCard entity) throws DaoException{
        return null;
    }

    private StatusPayCard getStatusPayCardFromResultSet(ResultSet resultSet) throws DaoException{
        StatusPayCard statusPayCard = new StatusPayCard();
        try {
            statusPayCard.setId(resultSet.getInt("id"));
            statusPayCard.setStatusName(resultSet.getString("status_name"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return statusPayCard;
    }

    private void closeStatement(PreparedStatement prstm, ResultSet resultSet){
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
    public int getAll() {
        return 0;
    }
}
