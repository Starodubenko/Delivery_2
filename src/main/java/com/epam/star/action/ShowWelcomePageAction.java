package com.epam.star.action;

import com.epam.star.dao.ContactDao;
import com.epam.star.dao.h2dao.DaoFactory;
import com.epam.star.dao.h2dao.DaoManager;
import com.epam.star.entity.Contact;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class ShowWelcomePageAction implements Action {

    private ActionResult login = new ActionResult("welcome");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoFactory daoFactory = DaoFactory.getInstance();
        DaoManager daoManager = daoFactory.getDaoManager();
        ContactDao contactDao = daoManager.getContactDao();
        List<Contact> contacts = contactDao.getContacts();

        daoManager.closeConnection();
        request.getSession().setAttribute("contacts",contacts);

        return login;
    }
}

