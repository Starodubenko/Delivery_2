package com.epam.star.action;

import java.sql.SQLException;

public class ActionException extends Exception{
    private int detail;

    public ActionException(int detail) {
        this.detail = detail;
    }

    public ActionException(SQLException e) {
        super(e);
    }

    @Override
    public String toString() {
//        if ()
        return "ActionException{" +
                "detail=" + detail +
                '}';
    }
}
