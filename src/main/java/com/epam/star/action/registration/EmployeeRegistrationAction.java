package com.epam.star.action.registration;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class EmployeeRegistrationAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientRergistrationAction.class);
    private ActionResult login = new ActionResult("welcome", true);
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

//        Validator validator = new Validator();
//        Client client = createClient(request, validator);
//
//        if (client != null) {
//            DaoFactory daoFactory = DaoFactory.getInstance();
//            DaoManager daoManager = daoFactory.getDaoManager();
//
//            daoManager.beginTransaction();
//            try {
//                PositionDao positionDao = daoManager.getPositionDao();
//                ClientDao clientDao = daoManager.getClientDao();
//
//                client.setRole(positionDao.findByPositionName("registration"));
//                client.setVirtualBalance(new BigDecimal(0));
//                clientDao.insert(client);
//                daoManager.commit();
//            } catch (Exception e){
//                daoManager.rollback();
//            }finally {
//                daoManager.closeConnection();
//            }
//        }
//        return login;
        return null;
    }
}
