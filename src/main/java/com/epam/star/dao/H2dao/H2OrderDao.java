package com.epam.star.dao.H2dao;

import com.epam.star.dao.*;
import com.epam.star.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class H2OrderDao extends AbstractH2Dao implements OrderDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_ORDER = "INSERT INTO  orders VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String CANCEL_ORDER = "UPDATE orders SET id = ?, user_id = ?, count = ?, period_id = ?, goods_id = ?, delivery_date = ?, additional_info = ?, status_id = ?, order_date = ? where id = ?";
    private Connection conn;

    public H2OrderDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Order> findAllByClientIdToday(int id) {
        String sql = "SELECT *" +//" orders.id,order_date,user_id,lastname,goods_name,count,delivery_date,period,additional_info,status_name" +
                " FROM orders" +
                " inner join users" +
                " on orders.user_id = users.id" +
                " inner join period" +
                " on orders.period_id = period.id" +
                " inner join goods" +
                " on orders.goods_id = goods.id" +
                " inner join status" +
                " on orders.status_id = status.id" +
                " where user_id = 20 and order_date = CAST(GETDATE() AS DATE)";
        List<Order> orders = new ArrayList<>();
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> findAllByClientIdLastDays(int id) {
        String sql = "SELECT *" + //orders.id,order_date,user_id,lastname,goods_name,count,delivery_date,period,additional_info,status_name" +
                " FROM orders" +
                " inner join users" +
                " on orders.user_id = users.id" +
                " inner join period" +
                " on orders.period_id = period.id" +
                " inner join goods" +
                " on orders.goods_id = goods.id" +
                " inner join status" +
                " on orders.status_id = status.id" +
                " where user_id = 20 and order_date != CAST(GETDATE() AS DATE)";
        List<Order> orders = new ArrayList<>();
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order findByClientAddress(String address) {
        return null;
    }

    @Override
    public Order findByPeriod(String period) {
        return null;
    }

    @Override
    public Order findByGoods(String period) {
        return null;
    }

    @Override
    public Order getElement(int ID) {
        String sql = "SELECT * FROM Orders WHERE id = " + ID;
        Order order = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            if (resultSet.next())
                order = getOrderFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (prstm != null) {
                try {
                    prstm.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return order;
    }

    @Override
    public String insert(Order order) {
        String status = "Order do not added";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(ADD_ORDER);
            prstm.setString(1, null);
            prstm.setInt(2, order.getUser().getId());
            prstm.setInt(3, order.getCount());
            prstm.setInt(4, order.getPeriod().getId());
            prstm.setInt(5, order.getGoods().getId());
            prstm.setDate(6, order.getDeliveryDate());
            prstm.setString(7, order.getAdditionalInfo());
            prstm.setInt(8, order.getStatus().getId());
            prstm.setDate(9, order.getOrderDate());
            prstm.execute();
            status = "Order added successfully";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (prstm != null) {
                try {
                    prstm.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return status;
    }


    @Override
    public String deleteElement(int ID) {
        return null;
    }

    @Override
    public String updateElement(Order order) {

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(CANCEL_ORDER);
            prstm.setInt(1, order.getId());
            prstm.setInt(2, order.getUser().getId());
            prstm.setInt(3, order.getCount());
            prstm.setInt(4, order.getPeriod().getId());
            prstm.setInt(5, order.getGoods().getId());
            prstm.setDate(6, order.getDeliveryDate());
            prstm.setString(7, order.getAdditionalInfo());
            prstm.setInt(8, order.getStatus().getId());
            prstm.setDate(9, order.getOrderDate());
            prstm.setInt(10, order.getId());
            prstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (prstm != null) {
                try {
                    prstm.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return null;
    }

    private Order getOrderFromResultSet(ResultSet resultSet) {
        Order order = new Order();

        DaoFactory daoFactory = DaoFactory.getInstance();
        PeriodDao periodDao = daoFactory.getPeriodDao();
        GoodsDao goodsDao = daoFactory.getGoodsDao();
        StatusDao statusDao = daoFactory.getStatusDao();
        ClientDao clientDao = daoFactory.getClientDao();

        try {
            order.setId(resultSet.getInt("id"));
            order.setOrderDate(resultSet.getDate("order_date"));
            order.setUser(clientDao.getElement(resultSet.getInt("user_id")));
            order.setGoods(goodsDao.getElement(resultSet.getInt("goods_id")));
            order.setCount(resultSet.getInt("count"));
            order.setDeliveryDate(resultSet.getDate("delivery_date"));
            order.setPeriod(periodDao.getElement(resultSet.getInt("period_id")));
            order.setAdditionalInfo(resultSet.getString("ADDITIONAL_INFO"));
            order.setStatus(statusDao.getElement(resultSet.getInt("status_id")));

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return order;
    }
}
