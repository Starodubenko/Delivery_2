package com.epam.star.servlet;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionFactory;
import com.epam.star.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Controller extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String actionName = req.getMethod() + req.getPathInfo();
        LOGGER.debug("Action name which was obtained in Controller: {}", actionName);

        Action action = ActionFactory.getAction(actionName);
        ActionResult result = null;
        try {
            result = action.execute(req);
        } catch (ActionException e) {
            LOGGER.info("Exception cached during executing of action {}", e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result.isRedirect()) {
            resp.sendRedirect(req.getContextPath()+"/do/" + result.getView()); //redirect goes to some action (/do/welcome or something like that)
            return;
        }
        //forward goes to some page (welcome.jsp index.jsp etc) so you need to add "/WEB-INF/" "jsp" here or in every action
        req.getRequestDispatcher("/WEB-INF/"+result.getView()+".jsp").forward(req, resp);
    }
}
