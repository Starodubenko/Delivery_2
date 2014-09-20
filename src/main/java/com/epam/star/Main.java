package com.epam.star;

import com.epam.star.dao.H2dao.AbstractH2Dao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;

import java.sql.SQLException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException {


        DaoManager daoManager = DaoFactory.getInstance().getDaoManager();

        System.out.println(daoManager.getDao("clientdao"));

        Map<String, AbstractH2Dao> daoMap = daoManager.getDaoMap();

        System.out.println(daoMap.get("h2clientdao"));

        System.out.println(daoMap.size());
        for (AbstractH2Dao abstractH2Dao : daoMap.values()) {
            System.out.println(abstractH2Dao);
        }
        daoManager.closeConnection();
    }
}
