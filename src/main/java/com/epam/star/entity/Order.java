package com.epam.star.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class Order extends AbstractEntity {

    private Client user;
    private int count;
    private Period period;
    private Goods goods;
    private Date deliveryDate;
    private String additionalInfo;
    private Status status;
    private Date orderDate;

    public BigDecimal getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    private BigDecimal orderCost;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(java.util.Date orderDate) {

        this.orderDate = new Date(orderDate.getTime());
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(java.util.Date deliveryDate) {
        this.deliveryDate = new Date(deliveryDate.getTime());
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public AbstractUser getUser() {
        return user;
    }

    public void setUser(Client user) {
        this.user = user;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
