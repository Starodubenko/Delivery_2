package com.epam.star.dao.H2dao;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {

    private final BoneCP connectionPool;
//    private ConnectionPool connectionPool;

    private DaoFactory() throws DaoException {

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new DaoException(e);
        }

        BoneCPConfig config = new BoneCPConfig();

        config.setJdbcUrl("jdbc:h2:tcp://localhost/FPDB");
        config.setUsername("Rody");
        config.setPassword("1");

        try {
            connectionPool = new BoneCP(config);
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        //todo move to init() method and call this method from ContextListener
    }

    //todo create destroy() method for pool shutdowning and  call this method from ContextListener

    public static DaoFactory getInstance() {
        return InstanceHolder.instance;
    }

    public DaoManager getDaoManager() throws DaoException {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();//getConnectionFromPool("jdbc:h2:tcp://localhost/FPDB","Rody","1");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return new DaoManager(connection);
    }

    private static class InstanceHolder {
        private static DaoFactory instance = new DaoFactory();
    }

}

