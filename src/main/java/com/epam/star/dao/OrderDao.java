package com.epam.star.dao;

import com.epam.star.entity.Order;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    public List<Order> findAllByClientIdToday(int id);
    public List<Order> findAllByClientIdLastDays(int id);
    public Order findByClientAddress(String address);
    public Order findByPeriod(String period);
    public Order findByGoods(String period);
}
