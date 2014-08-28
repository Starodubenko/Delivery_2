package com.epam.star.H2dao;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {
    private static DaoFactory instance = new DaoFactory();

     private final BoneCP connectionPool;

    private DaoFactory() {

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        BoneCPConfig config = new BoneCPConfig();

        config.setJdbcUrl("jdbc:h2:tcp://localhost/FPDB");
        config.setUsername("Rody");
        config.setPassword("1");
        try {
            connectionPool = new BoneCP(config);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //todo move to init() method and call this method from ContextListener
    }

    //todo create destroy() method for pool shutdowning and  call this method from ContextListener

    public static DaoFactory getInstance() {
        return instance;
    }

    public H2ClientDao getClientDao() throws SQLException {
        Connection connection = connectionPool.getConnection();
        return new H2ClientDao(connection);
    }

    public H2ContactDao getContactDao() throws SQLException {
        Connection connection = connectionPool.getConnection();
        return new H2ContactDao(connection);
    }

    public H2EmployeeDao getEmployeeDao() throws SQLException {
        Connection connection = connectionPool.getConnection();
        return new H2EmployeeDao(connection);
    }

    public H2OrderDao getOrderDao() throws SQLException {
        Connection connection = connectionPool.getConnection();
        return new H2OrderDao(connection);
    }

    public H2PeriodDao getPeriodDao() throws SQLException {
        Connection connection = connectionPool.getConnection();
        return new H2PeriodDao(connection);
    }

    public H2StatusDao getStatusDao() throws SQLException {
        Connection connection = connectionPool.getConnection();
        return new H2StatusDao(connection);
    }

    public H2GoodsDao getGoodsDao() throws SQLException {
        Connection connection = connectionPool.getConnection();
        return new H2GoodsDao(connection);
    }
}

