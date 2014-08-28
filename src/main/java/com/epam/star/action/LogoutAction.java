package com.epam.star.action;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class LogoutAction implements Action {

    ActionResult welcome = new ActionResult("welcome", true);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        request.getSession().invalidate();
        return welcome;
    }
}
