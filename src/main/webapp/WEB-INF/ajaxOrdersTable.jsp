<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table class="table table-hover" ID="ordersTable">
    <input type="hidden" id="ordersPageNumber" value="${ordersPaginatedList.getPageNumber()}"/>
    <tr>
        <th>
            <p> Check all</p>

            <div class="checkbox">
                <label>
                    <input id="maincheck" type="checkbox">
                </label>
            </div>
        </th>
        <th>ID</th>
        <th>Order date</th>
        <th>Goods name</th>
        <th>Goods count</th>
        <th>Order cost</th>
        <th>Delivery date</th>
        <th>Delivery time</th>
        <th>Additional info</th>
        <th>Status</th>
    </tr>
    <c:forEach var="row" items="${ordersPaginatedList}">
        <tr data-toggle="collapse" data-parent="#accordion">
            <td>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="IdOrder" class="mc" value="${row.getId()}">
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
