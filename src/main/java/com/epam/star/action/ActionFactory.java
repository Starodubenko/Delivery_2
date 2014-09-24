package com.epam.star.action;

import com.epam.star.action.login.AjaxLoginAction;
import com.epam.star.action.login.GoToPersonalCabinet;
import com.epam.star.action.order.*;
import com.epam.star.action.registration.AjaxClientRegistration;
import com.epam.star.action.registration.ClientRergistrationAction;
import com.epam.star.action.login.LoginAction;
import com.epam.star.action.login.LogoutAction;
import com.epam.star.action.paycard.PaymentAction;
import com.epam.star.action.show.*;
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

        actions.put("POST/login", new LoginAction());
        actions.put("GET/login", new ShowPageAction("welcome"));

        actions.put("GET/logout", new LogoutAction());

        actions.put("GET/director", new ShowPageAction("director"));

        actions.put("GET/dispatcher", new ShowDispatcherPageAction());

        actions.put("GET/admin", new ShowAdminPageAction());

        actions.put("GET/database", new ShowDataBasePageAction());

        actions.put("GET/welcome", new ShowWelcomePageAction());

        actions.put("GET/fastCreateOrder", new CreateOrderAction());

//        actions.put("GET/selectStatus", new AjaxSelectOrderStatus());

        actions.put("GET/cancelOrder", new CancelOrderAction());

        actions.put("GET/cancel", new AjaxCancelOrderAction());

        actions.put("GET/accept", new AjaxAcceptOrderAction());

        actions.put("GET/restore", new AjaxRestoreOrderAction());

        actions.put("POST/payment", new PaymentAction());

        actions.put("GET/client", new ShowClientPageAction());

        actions.put("GET/searching", new SeachAction());

        actions.put("GET/ajax_test", new ShowPageAction("ajax_test"));

        actions.put("GET/ajaxTable", new ShowAjaxTestAction());

        actions.put("POST/ajaxLogin", new AjaxLoginAction());

        actions.put("POST/ajaxClientRegistration", new AjaxClientRegistration());

        actions.put("GET/ajaxChangeClientsPage", new AjaxChangeClientsPage());

        actions.put("GET/ajaxChangeOrdersPage", new AjaxChangeOrdersPage());

        actions.put("GET/personal-cabinet", new GoToPersonalCabinet());

        actions.put("GET/createOrder", new ShowCreateOrderPageAction());

        actions.put("GET/SetOrderToEditFields", new AjaxSetOrderToEditFields());

        actions.put("GET/saveOrderData", new AjaxSaveOrderData());


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
