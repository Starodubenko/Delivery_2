package com.epam.star.dao.H2dao;

import com.epam.star.entity.AbstractEntity;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;

public abstract class AbstractH2Dao<T extends AbstractEntity> {
    protected Connection conn;
    protected DaoManager daoManager;

    protected AbstractH2Dao() {
    }

    protected AbstractH2Dao(Connection conn, DaoManager daoManager) {
        this.conn = conn;
        this.daoManager = daoManager;
    }

    public DaoManager getDaoManager() {
        return daoManager;
    }

    public void setDaoManager(DaoManager daoManager) {
        this.daoManager = daoManager;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public abstract List<T> findRange(int startRow, int rowsCount);

    public abstract int getRecordsCount();

    public abstract List<T> findRangeWithValue(int firstRow, int rowsCount, HttpServletRequest request);
}
