package com.epam.star.action.login;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.dao.ClientDao;
import com.epam.star.dao.EmployeeDao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.entity.AbstractUser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class AjaxLoginAction implements Action {
    private ActionResult result = new ActionResult(new JSONObject());
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {
        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        EmployeeDao employeeDao = daoManager.getEmployeeDao();
        ClientDao clientDao = daoManager.getClientDao();

        String login = request.getParameter("authenticationLogin");
        String password = request.getParameter("authenticationPassword");
        AbstractUser user = clientDao.findByCredentials(login, password);
        if (user == null)
            user = employeeDao.findByCredentials(login, password);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        LOGGER.debug("Name and Surname obtained in the case, if authentication is successful : {}", user);

        if (user != null) {
            if (user.getRole().equals("client")) result.getJson().put("roleView", "client");
            if (user.getRole().equals("dispatcher")) result.getJson().put("roleView", "dispatcher");
            if (user.getRole().equals("admin")) result.getJson().put("roleView", "admin");
        }
        else result.getJson().put("errorView", "client");

        request.setAttribute("json",result.getJson());

        return result;
    }
}
