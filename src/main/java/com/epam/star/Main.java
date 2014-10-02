package com.epam.star;

import com.epam.star.dao.Dao;
import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.dao.H2dao.DaoManager;
import com.epam.star.dao.H2dao.H2ClientDao;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);

    private static final Map<String, Dao> daoMap = new HashMap<>();

    static {
        Reflections reflections = new Reflections(Main.class.getPackage().getName());
        Set<Class<? extends Dao>> daos = reflections.getSubTypesOf(Dao.class);

        for (Class<?> dao : daos) {
            Dao daoo = null;
            try {
                daoo = (Dao) dao.newInstance();
            } catch (InstantiationException e) {
                LOGGER.error(e.toString());
            } catch (IllegalAccessException e) {
                LOGGER.error(e.toString());
            }
            daoMap.put(dao.getSimpleName(), daoo);
        }
    }

    public static void main(String[] args) throws SQLException, ParseException {

        DaoFactory daoFactory = DaoFactory.getInstance();
        DaoManager daoManager = daoFactory.getDaoManager();
        H2ClientDao clientDao = daoManager.getClientDao();

        System.out.println(clientDao.getClass().getName());
        System.out.println(clientDao.getClass().getSimpleName());
        System.out.println(daoMap);
    }
}
