<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table class="table table-hover" ID="ordersTable">
    <tr>
        <th></th>
        <th>ID</th>
        <th>Order date</th>
        <th>Goods name</th>
        <th>Goods count</th>
        <th>Order cost</th>
        <th>Delivry date</th>
        <th>Delivry time</th>
        <th>Additional info</th>
        <th>Status</th>
    </tr>
    <c:forEach var="row" items="${ordersList}">
        <tr>
            <td>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="IdOrder" value="${row.getId()}">
                    </label>
                </div>
            </td>
            <td>${row.getId()}</td>
            <td>${row.getOrderDate()}</td>
            <td>${row.getGoods().getGoodsName()}</td>
            <td>${row.getCount()}</td>
            <td>${row.getOrderCost()}</td>
            <td>${row.getDeliveryDate()}</td>
            <td>${row.getPeriod().getPeriod()}</td>
            <td>${row.getAdditionalInfo()}</td>
            <td>${row.getStatus().getStatusName()}</td>
        </tr>
    </c:forEach>
</table>
