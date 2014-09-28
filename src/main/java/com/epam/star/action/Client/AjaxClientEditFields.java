package com.epam.star.action.Client;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ClientDao;
import com.epam.star.entity.Client;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AjaxClientEditFields implements Action {
    private ActionResult editOrdersBlock = new ActionResult("ajaxOrderTableRow");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        int id = Integer.parseInt(request.getParameter("id"));

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2ClientDao clientDao = daoManager.getClientDao();

        Client client = clientDao.findById(id);

        request.setAttribute("client", client);

        daoManager.closeConnection();

        return editOrdersBlock;
    }
}
