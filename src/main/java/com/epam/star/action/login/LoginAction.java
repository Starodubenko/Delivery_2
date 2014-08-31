package com.epam.star.action.login;

import com.epam.star.action.Action;
import com.epam.star.action.ActionResult;
import com.epam.star.action.Post;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.ClientDao;
import com.epam.star.dao.EmployeeDao;
import com.epam.star.dao.OrderDao;
import com.epam.star.entity.AbstractEntity;
import com.epam.star.entity.AbstractUser;
import com.epam.star.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@Post
public class LoginAction implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAction.class);
    private ActionResult loginn = new ActionResult("login");
    private ActionResult client = new ActionResult("client", true);
    private ActionResult dispatcher = new ActionResult("dispatcher", true);
    private ActionResult admin = new ActionResult("admin", true);
    private ActionResult director = new ActionResult("director", true);

    public static List<Order> getTodayOrdersFromDataBase(AbstractEntity user, OrderDao orderDao) {
        List<Order> todayOrders = orderDao.findAllByClientIdToday(user.getId());
        return todayOrders;
    }

    public static List<Order> getPastOrdersFromDataBase(AbstractEntity user, OrderDao orderDao) {
        List<Order> pastOrders = orderDao.findAllByClientIdLastDays(user.getId());
        return pastOrders;
    }

    @Override
    public ActionResult execute(HttpServletRequest request) throws SQLException {

        DaoFactory daoFactory = DaoFactory.getInstance();
        EmployeeDao employeeDao = daoFactory.getEmployeeDao();
        OrderDao orderDao = daoFactory.getOrderDao();
        ClientDao clientDao = daoFactory.getClientDao();

        String login = request.getParameter("authenticationLogin");
        String password = request.getParameter("authenticationPassword");
        AbstractUser user = clientDao.findByCredentials(login, password);
        if (user == null)
            user = employeeDao.findByCredentials(login, password);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("userType", "user");
        session.setAttribute("todayOrders", getTodayOrdersFromDataBase(user, orderDao));
        session.setAttribute("pastOrders", getPastOrdersFromDataBase(user, orderDao));


        LOGGER.debug("Name and Surname obtained in the case, if authentication is successful : {}", user);

        if (user == null) {
            request.setAttribute("loginError", "login.incorrect.login.or.password");
            return loginn;
        }

        if (user.getRole().getPositionName().equalsIgnoreCase("admin")) return admin;

        if (user.getRole().getPositionName().equalsIgnoreCase("director")) return director;

        if (user.getRole().getPositionName().equalsIgnoreCase("dispatcher")) return dispatcher;

        return client;
    }
}
