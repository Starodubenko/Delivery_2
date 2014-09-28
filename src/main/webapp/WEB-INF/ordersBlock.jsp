<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="orders-block">
    <ul id="change" class="pagination">
        <li id="oBack"><a href="#page">&laquo;</a></li>

        <c:forEach varStatus="status" items="${ordersPaginationlist}" var="pl">
            <li value="${pl.intValue()}" name="page${pl.intValue()}"
                class="oNumbered page"><a href="#page${pl.intValue()}">${pl.intValue()}</a>
            </li>
        </c:forEach>

        <li id="oNext"><a href="#page">&raquo;</a></li>
    </ul>

    <select class="form-control switcher floatRight" id="switchStatusOrser">
        <option>Waiting</option>
        <option>Active</option>
        <option>Canceled</option>
        <option>Executed</option>
    </select>

    <div class="form-group rows-count floatRight">
        <label class="labelCount" for="ordersrows">Rows count</label>

        <form action="${pageContext.request.contextPath}/do/dispatcher">
            <div class="input-group">
                <input type="text" name="ordersrows" id="ordersrows" value="${ordersRowsCount}"
                       class="form-control textCount">
                        <span class="input-group-btn">
                        <button class="btn btn-default floatRight" type="submit">aply</button>
                        </span>
            </div>
        </form>
    </div>

    <div class="orderListHeight tab-pane" style="overflow-y: scroll">
        <table class="table table-hover" ID="ordersTable">
            <input type="hidden" id="ordersPageNumber" value="${ordersPageNumber}"/>
            <tr>
                <th>
                    <p> Check all</p>

                    <div class="checkbox">
                        <label>
                            <input id="maincheck" type="checkbox" value="${row.getId()}">
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
            <c:forEach var="row" items="${ordersList}">
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
    </div>
</div>