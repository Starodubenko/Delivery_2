package com.epam.star;

import com.epam.star.dao.H2dao.DaoFactory;
import com.epam.star.entity.Order;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Order order = DaoFactory.getInstance().getOrderDao().getElement(1);

        System.out.println(order.getOrderDate());
        System.out.println(order.getDeliveryDate());

    }
}
