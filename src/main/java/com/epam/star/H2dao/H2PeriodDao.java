package com.epam.star.H2dao;

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

    public H2PeriodDao (Connection conn) {
        this.conn = conn;
    }

    @Override
    public Period findByPeriod(Time period) {

        String sql = "SELECT * FROM period WHERE period = " + "'" + period + "'";
        Period periodResult = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            periodResult = getPeriodFromResultSet(resultSet);
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
        return periodResult;
    }

    @Override
    public Period getElement(int ID) {
        String sql = "SELECT * FROM period WHERE id = " + ID;
        Period period = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            period = getPeriodFromResultSet(resultSet);
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
        return period;
    }

    @Override
    public String insert(Period period) {
        String statuss = "Period do not added";

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(ADD_PERIOD);
            prstm.setString(1, null);
            prstm.setTime(2, period.getPeriod());
            prstm.execute();
            statuss = "Period added successfully";
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
    public String updateElement(int ID) {
        return null;
    }

    private Period getPeriodFromResultSet(ResultSet resultSet) {
        Period period = new Period();
        try {
            resultSet.next();
            period.setId(resultSet.getInt("id"));
            period.setPeriod(resultSet.getTime("period"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return period;
    }
}
