package com.epam.star.dao.H2dao;

import java.sql.SQLException;

public class DaoException extends Exception {
    private int detail;

    public DaoException(int detail) {
        this.detail = detail;
    }

    public DaoException(SQLException e) {
        super(e);
    }
}
