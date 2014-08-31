package com.epam.star.action;

import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.ContactDao;
import com.epam.star.entity.Contact;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class ShowWelcomePageAction implements Action {

    private ActionResult login = new ActionResult("login");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoFactory daoFactory = DaoFactory.getInstance();
        ContactDao contactDao = daoFactory.getContactDao();
        List<Contact> contacts = contactDao.getContacts();

        request.getSession().setAttribute("contacts",contacts);

        return login;
    }
}

