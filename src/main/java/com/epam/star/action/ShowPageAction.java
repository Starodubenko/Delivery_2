package com.epam.star.action;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ShowPageAction implements Action{
    private ActionResult actionResult;

    public ShowPageAction(String PageName) {
        this.actionResult = new ActionResult(PageName);
    }

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        return actionResult;
    }
}
