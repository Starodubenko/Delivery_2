package com.epam.star.dao.H2dao;

import com.epam.star.dao.PayCardDao;
import com.epam.star.dao.PayCardStatusDao;
import com.epam.star.entity.PayCard;
import com.epam.star.entity.StatusPayCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2PayCardDao extends AbstractH2Dao implements PayCardDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_PAYCARD = "INSERT INTO pay_card VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PAYCARD = "UPDATE pay_card SET id = ?, serial_number = ?, secret_number = ?, balance = ?, id_status_pay_card = ?  WHERE id = ?";
    private Connection conn;
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private DaoManager daoManager = daoFactory.getDaoManager();

    public H2PayCardDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public PayCard findBySerialNumber(String serNum) throws DaoException{
        String sql = "SELECT * FROM pay_card WHERE serial_number = " + "'" + serNum + "'";
        PayCard payCard = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            payCard = getPayCardFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm,resultSet);
        }
        return payCard;
    }

    @Override
    public PayCard findBySecretNumber(String secNum) throws DaoException{
        String sql = "SELECT * FROM pay_card WHERE secret_number = " + "'" + secNum + "'";
        PayCard payCard = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;

        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                payCard = getPayCardFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm,resultSet);
        }
        return payCard;
    }

    @Override
    public PayCard findByStatus(String status) throws DaoException{

        H2PayCardStatusDao h2PayCardStatusDao = daoManager.getPayCardStatusDao();

        StatusPayCard statusPayCard = h2PayCardStatusDao.findByStatusName(status);
        String sql = "SELECT * FROM pay_card WHERE ID_STATUS = " + statusPayCard.getId();
        PayCard payCard = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            payCard = getPayCardFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm,resultSet);
        }
        return payCard;
    }

    @Override
    public PayCard getElement(int ID) throws DaoException{
        String sql = "SELECT * FROM pay_card WHERE Id = " + ID;
        PayCard payCard = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;

        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            payCard = getPayCardFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm,resultSet);
        }
        return payCard;
    }

    @Override
    public String insert(PayCard payCard) throws DaoException{
        String statuss = "PayCard do not added";

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(ADD_PAYCARD);
            prstm.setString(1, null);
            prstm.setString(2, payCard.getSerialNumber());
            prstm.setString(3, payCard.getSecretNumber());
            prstm.setBigDecimal(4, payCard.getBalance());
            prstm.setInt(5, payCard.getStatusPayCard().getId());
            prstm.execute();
            statuss = "PayCard added successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm,null);
        }
        return statuss;
    }

    @Override
    public String deleteElement(int ID) throws DaoException{
        return null;
    }

    @Override
    public String updateElement(PayCard payCard) throws DaoException{

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(UPDATE_PAYCARD);
            prstm.setInt(1, payCard.getId());
            prstm.setString(2, payCard.getSerialNumber());
            prstm.setString(3, payCard.getSecretNumber());
            prstm.setBigDecimal(4, payCard.getBalance());
            prstm.setInt(5, payCard.getStatusPayCard().getId());
            prstm.setInt(6, payCard.getId());
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm,null);
        }
        return null;
    }

    private PayCard getPayCardFromResultSet(ResultSet resultSet) throws DaoException{

        PayCardStatusDao payCardStatusDao = daoManager.getPayCardStatusDao();
        PayCard payCard = new PayCard();
        try {
            payCard.setId(resultSet.getInt("id"));
            payCard.setSerialNumber(resultSet.getString("serial_number"));
            payCard.setSecretNumber(resultSet.getString("secret_number"));
            payCard.setBalance(new BigDecimal(resultSet.getInt("balance")));
            StatusPayCard statusPayCard = payCardStatusDao.getElement(resultSet.getInt("id_status_pay_card"));
            payCard.setStatusPayCard(statusPayCard);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return payCard;
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
}
