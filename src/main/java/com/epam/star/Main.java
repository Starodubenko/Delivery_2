package com.epam.star;

import com.epam.star.action.Action;
import com.epam.star.action.MappedAction;
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

    static Map<String, Action> actionMap = new HashMap<>();

    static {
        Reflections reflections = new Reflections(Main.class.getPackage().getName());
        Set<Class<?>> actions = reflections.getTypesAnnotatedWith(MappedAction.class);


        for (Class<?> actionClass : actions) {
            MappedAction mappedAction = actionClass.getAnnotation(MappedAction.class);

            Action action = null;
            try {
                action = (Action) actionClass.newInstance();
            } catch (InstantiationException e) {
                LOGGER.error(e.toString());
            } catch (IllegalAccessException e) {
                LOGGER.error(e.toString());
            }
            actionMap.put(mappedAction.value(), action);
        }
    }

    public static void main(String[] args) throws SQLException, ParseException {

        for (Map.Entry<String, Action> record : actionMap.entrySet()) {
            System.out.println(record.getKey() + " = " + record.getValue() + "\n\r");

        }
    }
}
