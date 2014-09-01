package com.epam.star.dao.H2dao;

import com.epam.star.dao.PeriodDao;
import com.epam.star.entity.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class H2PeriodDao implements PeriodDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_PERIOD = "INSERT INTO period VALUES (?, ?)";
    private static final String DELETE_PERIOD = "DELETE FROM period WHERE id = ?";
    private Connection conn;

    public H2PeriodDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Period findByPeriod(Time period) throws DaoException{

        String sql = "SELECT * FROM period WHERE period = " + "'" + period + "'";
        Period periodResult = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            periodResult = getPeriodFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm,resultSet);
        }
        return periodResult;
    }

    @Override
    public Period getElement(int ID) throws DaoException{
        String sql = "SELECT * FROM period WHERE id = " + ID;
        Period period = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            period = getPeriodFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm,resultSet);
        }
        return period;
    }

    @Override
    public String insert(Period period) throws DaoException{
        String statuss = "Period do not added";

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(ADD_PERIOD);
            prstm.setString(1, null);
            prstm.setTime(2, period.getPeriod());
            prstm.execute();
            statuss = "Period added successfully";
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
    public String updateElement(Period period) throws DaoException{
        return null;
    }

    private Period getPeriodFromResultSet(ResultSet resultSet) throws DaoException{
        Period period = new Period();
        try {
            resultSet.next();
            period.setId(resultSet.getInt("id"));
            period.setPeriod(resultSet.getTime("period"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return period;
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
