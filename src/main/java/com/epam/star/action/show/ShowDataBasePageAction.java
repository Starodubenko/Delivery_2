package com.epam.star.action.show;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ShowDataBasePageAction implements Action {
    private ActionResult database = new ActionResult("database");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        return database;
    }
}
