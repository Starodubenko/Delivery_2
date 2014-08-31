package com.epam.star.action.paycard;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.dao.*;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;

public class PaymentAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentAction.class);
    private ActionResult client = new ActionResult("client");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoFactory daoFactory = DaoFactory.getInstance();
        PayCardDao payCardDao = daoFactory.getPayCardDao();
        PayCardStatusDao statusPayCard = daoFactory.getPayCardStatusDao();
        ClientDao clientDao = daoFactory.getClientDao();
        EmployeeDao employeeDao = daoFactory.getEmployeeDao();
        PositionDao userPositionDao = daoFactory.getPositionDao();

        String code = request.getParameter("SecretCode");
        PayCard payCard = payCardDao.findBySecretNumber(code);

        StatusPayCard notActivatedStatus = statusPayCard.findByStatusName("not activated");
        StatusPayCard currentStatus = payCard.getStatusPayCard();

        if (currentStatus.equals(notActivatedStatus)) {
            AbstractUser user = (AbstractUser) request.getSession().getAttribute("user");

            BigDecimal userbal = user.getVirtualBalance();
            BigDecimal payBal = payCard.getBalance();
            BigDecimal newBal = userbal.add(payBal);

            payCardDao = daoFactory.getPayCardDao();
            statusPayCard = daoFactory.getPayCardStatusDao();

            payCard.setStatusPayCard(statusPayCard.findByStatusName("activated"));
            payCardDao.updateElement(payCard);

            user.setVirtualBalance(newBal);

            Position userRole = user.getRole();
            Position clientRole = userPositionDao.findByPositionName("client");

            if (userRole.equals(clientRole)) {
                clientDao.updateElement((Client) user);
            }
            if (!userRole.equals(clientRole))
                employeeDao.updateElement((Employee) user);
        } else
            LOGGER.error("The payment card have status: {}", payCard.getStatusPayCard().getStatusName());
        return client;
    }
}
