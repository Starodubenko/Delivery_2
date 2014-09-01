package com.epam.star.dao.H2dao;

import com.epam.star.dao.GoodsDao;
import com.epam.star.entity.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2GoodsDao implements GoodsDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_GOODS = "INSERT INTO goods VALUES (?, ?, ?)";
    private static final String DELETE_GOODS = "DELETE FROM goods WHERE id = ?";
    private Connection conn;

    public H2GoodsDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Goods findByGoodsName(String name) throws DaoException{
        String sql = "SELECT * FROM goods WHERE goods_name = " + "'" + name + "'";
        Goods goods = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            goods = getGoodsFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return goods;
    }

    @Override
    public Goods getElement(int ID) throws DaoException{
        String sql = "SELECT * FROM goods WHERE id = " + ID;
        Goods goods = null;
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();

            goods = getGoodsFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return goods;
    }

    @Override
    public String insert(Goods goods) throws DaoException{
        String status = "Goods do not added";

        PreparedStatement prstm = null;
        try {
            prstm = conn.prepareStatement(ADD_GOODS);
            prstm.setString(1, null);
            prstm.setString(2, goods.getGoodsName());
            prstm.setBigDecimal(3, goods.getPrice());
            prstm.execute();
            status = "Goods added successfully";
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return status;
    }

    @Override
    public String deleteElement(int ID) throws DaoException{
        return null;
    }

    @Override
    public String updateElement(Goods goods) throws DaoException{
        return null;
    }

    private Goods getGoodsFromResultSet(ResultSet resultSet) throws DaoException{
        Goods goods = new Goods();
        try {
            resultSet.next();
            goods.setId(resultSet.getInt("id"));
            goods.setGoodsName(resultSet.getString("goods_name"));
            goods.setPrice(resultSet.getBigDecimal("price"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return goods;
    }
}
