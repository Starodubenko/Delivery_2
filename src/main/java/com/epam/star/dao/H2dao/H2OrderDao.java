package com.epam.star.dao.H2dao;

import com.epam.star.dao.*;
import com.epam.star.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class H2OrderDao extends AbstractH2Dao implements OrderDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2OrderDao.class);
    private static final String INSERT_ORDER = "INSERT INTO  orders VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String RANGE_ORDERS = "SELECT * FROM orders LIMIT ? OFFSET ?";
    private static final String CANCEL_ORDER = "UPDATE orders SET id = ?, user_id = ?, count = ?, period_id = ?, goods_id = ?, order_cost = ?, paid = ?, delivery_date = ?, additional_info = ?, status_id = ?, order_date = ? where id = ?";

    protected H2OrderDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    @Override
    public List<Order> findAllByClientIdToday(int id) throws DaoException {
        String sql = "SELECT *" +
                " FROM orders" +
                " inner join users" +
                " on orders.user_id = users.id" +
                " inner join period" +
                " on orders.period_id = period.id" +
                " inner join goods" +
                " on orders.goods_id = goods.id" +
                " inner join status" +
                " on orders.status_id = status.id" +
                " where user_id = " + id + " and order_date = CAST(GETDATE() AS DATE)";
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
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return orders;
    }

    @Override
    public List<Order> findAllByClientIdLastDays(int id) throws DaoException {
        String sql = "SELECT *" +
                " FROM orders" +
                " inner join users" +
                " on orders.user_id = users.id" +
                " inner join period" +
                " on orders.period_id = period.id" +
                " inner join goods" +
                " on orders.goods_id = goods.id" +
                " inner join status" +
                " on orders.status_id = status.id" +
                " where user_id = " + id + " and order_date != CAST(GETDATE() AS DATE)";
        List<Order> orders = new ArrayList<>();
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            while (resultSet.next()) {
                Order order = getOrderFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return orders;
    }

    @Override
    public Order findByClientAddress(String address) throws DaoException {
        return null;
    }

    @Override
    public Order findByPeriod(String period) throws DaoException {
        return null;
    }

    @Override
    public Order findByGoods(String period) throws DaoException {
        return null;
    }

    @Override
    public Order findById(int ID) throws DaoException {
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
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return order;
    }

    @Override
    public String insert(Order order) throws DaoException {
        String status = "Order do not added";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(INSERT_ORDER);
            prstm.setString(1, null);
            prstm.setInt(2, order.getUser().getId());
            prstm.setInt(3, order.getCount());
            prstm.setInt(4, order.getPeriod().getId());
            prstm.setInt(5, order.getGoods().getId());
            prstm.setDate(6, order.getDeliveryDate());
            prstm.setString(7, order.getAdditionalInfo());
            prstm.setInt(8, order.getStatus().getId());
            prstm.setDate(9, order.getOrderDate());
            prstm.setBigDecimal(10, order.getOrderCost());
            prstm.setBigDecimal(11, order.getPaid());
            prstm.execute();
            status = "Order added successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }


    @Override
    public String deleteElement(int ID) throws DaoException {
        return null;
    }

    @Override
    public String updateElement(Order order) throws DaoException {

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(CANCEL_ORDER);
            prstm.setInt(1, order.getId());
            prstm.setInt(2, order.getUser().getId());
            prstm.setInt(3, order.getCount());
            prstm.setInt(4, order.getPeriod().getId());
            prstm.setInt(5, order.getGoods().getId());
            prstm.setBigDecimal(6, order.getOrderCost());
            prstm.setBigDecimal(7, order.getPaid());
            prstm.setDate(8, order.getDeliveryDate());
            prstm.setString(9, order.getAdditionalInfo());
            prstm.setInt(10, order.getStatus().getId());
            prstm.setDate(11, order.getOrderDate());
            prstm.setInt(12, order.getId());
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return null;
    }

    private Order getOrderFromResultSet(ResultSet resultSet) throws DaoException {
        Order order = new Order();
        PeriodDao periodDao = daoManager.getPeriodDao();
        GoodsDao goodsDao = daoManager.getGoodsDao();
        StatusDao statusDao = daoManager.getStatusDao();
        ClientDao clientDao = daoManager.getClientDao();

        try {
            order.setId(resultSet.getInt("id"));
            order.setOrderDate(resultSet.getDate("order_date"));
            order.setUser(clientDao.findById(resultSet.getInt("user_id")));
            order.setGoods(goodsDao.findById(resultSet.getInt("goods_id")));
            order.setOrderCost(resultSet.getBigDecimal("order_cost"));
            order.setPaid(resultSet.getBigDecimal("paid"));
            order.setCount(resultSet.getInt("count"));
            order.setDeliveryDate(resultSet.getDate("delivery_date"));
            order.setPeriod(periodDao.findById(resultSet.getInt("period_id")));
            order.setAdditionalInfo(resultSet.getString("ADDITIONAL_INFO"));
            order.setStatus(statusDao.findById(resultSet.getInt("status_id")));
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return order;
    }

    private void closeStatement(PreparedStatement prstm, ResultSet resultSet) {
        if (prstm != null) {
            try {
                prstm.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public List findRange(int startRow, int rowsCount) {
        List<Order> result = new ArrayList<>();

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(RANGE_ORDERS);
            prstm.setInt(1, rowsCount);
            prstm.setInt(2, startRow);
            resultSet = prstm.executeQuery();
            while (resultSet.next())
                result.add(getOrderFromResultSet(resultSet));
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return result;
    }

    @Override
    public int getRecordsCount() {
        int result = 0;

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement("SELECT COUNT(*) FROM orders");
            resultSet = prstm.executeQuery();
            while (resultSet.next())
                result = resultSet.getInt("count(*)");
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return result;
    }

    @Override
    public List findRangeWithValue(int firstPosition, int count, HttpServletRequest request) {

        String findOrders = "SELECT *" +
                " FROM orders" +
                " inner join users" +
                " on orders.user_id = users.id" +
                " inner join period" +
                " on orders.period_id = period.id" +
                " inner join goods" +
                " on orders.goods_id = goods.id" +
                " inner join status" +
                " on orders.status_id = status.id";

        String conditions = "";

        int selectedFieldsCount = 0;
        if (request.getParameter("order-id") != null & request.getParameter("order-id") != "") {
            selectedFieldsCount++;
            if (selectedFieldsCount > 1) conditions += " and ";
            conditions += " orders.id = ?";
        }
        if (request.getParameter("order-date") != null & request.getParameter("order-date") != "") {
            selectedFieldsCount++;
            if (selectedFieldsCount > 1) conditions += " and ";
            conditions += " orders.order_date = ?";
        }
        if (request.getParameter("order-goods-name") != null & request.getParameter("order-goods-name") != "") {
            selectedFieldsCount++;
            if (selectedFieldsCount > 1) conditions += " and ";
            conditions += " goods.goods_name = ?";
        }
        if (request.getParameter("order-goods-count") != null & request.getParameter("order-goods-count") != "") {
            selectedFieldsCount++;
            if (selectedFieldsCount > 1) conditions += " and ";
            conditions += " orders.count = ?";
        }
        if (request.getParameter("order-cost") != null & request.getParameter("order-cost") != "") {
            selectedFieldsCount++;
            if (selectedFieldsCount > 1) conditions += " and ";
            conditions += " orders.order_cost = ?";
        }
        if (request.getParameter("delivery-date") != null & request.getParameter("delivery-date") != "") {
            selectedFieldsCount++;
            if (selectedFieldsCount > 1) conditions += " and ";
            conditions += " orders.delivery_date = ?";
        }
        if (request.getParameter("delivery-time") != null & request.getParameter("delivery-time") != "") {
            selectedFieldsCount++;
            if (selectedFieldsCount > 1) conditions += " and ";
            conditions += " period.period = ?";
        }
        if (request.getParameter("order-addInfo") != null & request.getParameter("order-addInfo") != "") {
            selectedFieldsCount++;
            if (selectedFieldsCount > 1) conditions += " and ";
            conditions += " orders.additional_info = ?";
        }
        if (request.getParameter("order-status") != null & request.getParameter("order-status") != "") {
            selectedFieldsCount++;
            if (selectedFieldsCount > 1) conditions += " and ";
            conditions += " status.status_name = ?";
        }

        if (selectedFieldsCount > 0) findOrders += " where " + conditions;

        findOrders += " LIMIT ? OFFSET ?";

        List<Order> result = new ArrayList<>();

        H2GoodsDao goodsDao = daoManager.getGoodsDao();
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(findOrders);

            int prstmIndex = 0;
            String dynamicalString = request.getParameter("order-id");
            if (dynamicalString != null & dynamicalString != "") {
                dynamicalString = request.getParameter("order-id");
                prstmIndex++;
                prstm.setInt(prstmIndex, Integer.parseInt(dynamicalString));
            }
            Date date = null;
            try {
                dynamicalString = request.getParameter("order-date");
                if (dynamicalString != null & dynamicalString != "") {
                    date = new Date(new SimpleDateFormat("yy-MM-dd").parse(dynamicalString).getTime());
                    prstmIndex++;
                    prstm.setDate(prstmIndex, date);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dynamicalString = request.getParameter("order-goods-name");
            if (dynamicalString != null & dynamicalString != "") {
                prstmIndex++;
                prstm.setString(prstmIndex, dynamicalString);
            }
            dynamicalString = request.getParameter("order-goods-count");
            if (dynamicalString != null & dynamicalString != "") {
                prstmIndex++;
                prstm.setInt(prstmIndex, Integer.parseInt(dynamicalString));
            }
            dynamicalString = request.getParameter("order-cost");
            if (dynamicalString != null & dynamicalString != "") {
                prstmIndex++;
                prstm.setInt(prstmIndex, Integer.parseInt(dynamicalString));
            }
            try {
                dynamicalString = request.getParameter("delivery-date");
                if (dynamicalString != null & dynamicalString != "") {
                    date = new Date(new SimpleDateFormat("yy-MM-dd").parse(dynamicalString).getTime());
                    prstmIndex++;
                    prstm.setDate(prstmIndex, date);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dynamicalString = request.getParameter("delivery-time");
            if (dynamicalString != null & dynamicalString != "") {
                prstmIndex++;
                prstm.setString(prstmIndex, dynamicalString);
            }
            dynamicalString = request.getParameter("order-addInfo");
            if (dynamicalString != null & dynamicalString != "") {
                prstmIndex++;
                prstm.setString(prstmIndex, dynamicalString);
            }
            dynamicalString = request.getParameter("order-status");
            if (dynamicalString != null & dynamicalString != "") {
                prstmIndex++;
                prstm.setString(prstmIndex, dynamicalString);
            }

            prstmIndex++;
            prstm.setInt(prstmIndex, count);
            prstmIndex++;
            prstm.setInt(prstmIndex, firstPosition);
            resultSet = prstm.executeQuery();
            while (resultSet.next()) {
                result.add(getOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return result;
    }
}
