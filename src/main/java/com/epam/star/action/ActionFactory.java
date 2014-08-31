package com.epam.star.action;

import com.epam.star.action.client.ClientRergistrationAction;
import com.epam.star.action.login.LoginAction;
import com.epam.star.action.login.LogoutAction;
import com.epam.star.action.order.CancelOrderAction;
import com.epam.star.action.order.CreateOrderAction;
import com.epam.star.action.paycard.PaymentAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    static Map<String, Action> actions = new HashMap<>();

    static {
//        Reflections reflections = new Reflections(ActionFactory.class.getPackage().getName());
//        Set<Class<? extends Action>> actions = reflections.getSubTypesOf(Action.class);

        actions.put("POST/ClientRegistration", new ClientRergistrationAction());
        actions.put("GET/ClientRegistration", new ShowPageAction("/WEB-INF/welcome.jsp"));

        actions.put("POST/welcome", new LoginAction());
        actions.put("GET/welcome", new ShowPageAction("welcome"));

        actions.put("GET/logout", new LogoutAction());

        actions.put("GET/client", new ShowPageAction("client"));

        actions.put("GET/director", new ShowPageAction("director"));

        actions.put("GET/dispatcher", new ShowPageAction("dispatcher"));

        actions.put("GET/admin", new ShowPageAction("admin"));

        actions.put("GET/welcome", new ShowWelcomePageAction());

        actions.put("GET/createOrder", new CreateOrderAction());

        actions.put("GET/cancelOrder", new CancelOrderAction());

        actions.put("POST/payment", new PaymentAction());


//        for (Class<? extends Action> actionClass : actions) {
////            Annotation[] annotations = actionClass.getAnnotations();
//            //todo foreach on annotations
////            if (annotation.annotationType().isInstance(Post.class)) {
////            }
//            Action action = null;
//            try {
//                action = actionClass.newInstance();
//            } catch (InstantiationException e) {
//                logger.error(e.toString());
//            } catch (IllegalAccessException e) {
//                logger.error(e.toString());
//            }
//            actionMap.put(actionClass.getSimpleName().toLowerCase(), action); //todo check for null: if you cannot instantiate you should not put it into map!!!
//        }
    }

    private static Logger logger = LoggerFactory.getLogger(ActionFactory.class);

    public static Action getAction(String actionName) {
        return actions.get(actionName);
    }

    public static void getAllAction() {
        for (String s : actions.keySet()) {
            System.out.println(s);
        }
    }
}
