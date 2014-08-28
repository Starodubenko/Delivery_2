package com.epam.star.action;

import com.epam.star.H2dao.DaoFactory;
import com.epam.star.dao.ClientDao;
import com.epam.star.entity.Client;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ClientRergistrationAction implements Action {

    private ActionResult login = new ActionResult("login");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        Client client = createClient(request);

        DaoFactory daoFactory = DaoFactory.getInstance();
        ClientDao clientDao = daoFactory.getClientDao();
        clientDao.insert(client);

        return login;
    }

    private Client createClient(HttpServletRequest request) {
        Client client = new Client();
        client.setLogin(request.getParameter("login"));
        client.setPassword(request.getParameter("password"));
        client.setFirstName(request.getParameter("firstname"));
        client.setLastName(request.getParameter("lastname"));
        client.setMiddleName(request.getParameter("middlename"));
        client.setAddress(request.getParameter("address"));
        client.setTelephone(request.getParameter("telephone"));
        client.setMobilephone(request.getParameter("mobilephone"));
        return client;
    }
}
