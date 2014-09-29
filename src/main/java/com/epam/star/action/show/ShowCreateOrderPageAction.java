package com.epam.star.action.show;

import com.epam.star.action.Action;
import com.epam.star.action.ActionException;
import com.epam.star.action.ActionResult;
import com.epam.star.action.MappedAction;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.entity.Goods;
import com.epam.star.entity.Period;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@MappedAction("GET/createOrder")
public class ShowCreateOrderPageAction implements Action {
    ActionResult createOrder = new ActionResult("createOrder");

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException, SQLException {

        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        List<Period> periods = daoManager.getPeriodDao().getAllPeriods();
        List<Goods> goods = daoManager.getGoodsDao().getAllGoods();

        HttpSession session = request.getSession();
        session.setAttribute("periods", periods);
        session.setAttribute("goods", goods);

        daoManager.closeConnection();

        return createOrder;
    }
}
