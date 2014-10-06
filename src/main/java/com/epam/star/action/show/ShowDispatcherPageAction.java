package com.epam.star.action.show;

import com.epam.star.action.*;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ClientDao;
import com.epam.star.dao.H2dao.H2OrderDao;
import com.epam.star.dao.util.Pagination;
import com.epam.star.entity.Goods;
import com.epam.star.entity.Period;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@MappedAction("GET/dispatcher")
public class ShowDispatcherPageAction implements Action {
    private ActionResult dispatcher = new ActionResult("dispatcher");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        H2ClientDao clientDao = daoManager.getClientDao();
        H2OrderDao orderDao = daoManager.getOrderDao();

        List<Period> periods = daoManager.getPeriodDao().getAllPeriods();
        List<Goods> goods = daoManager.getGoodsDao().getAllGoods();

        Pagination pagination = new Pagination();
        pagination.paginationEntity(request, clientDao, "clients");
        pagination.paginationEntity(request, orderDao, "orders");

        HttpSession session = request.getSession();
        session.setAttribute("periods", periods);
        session.setAttribute("goods", goods);

        daoManager.closeConnection();

        return dispatcher;
    }
}
