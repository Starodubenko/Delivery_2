<%@ tag description="authentication template" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel='stylesheet' href='<c:url value="/style/authentication.css"/>'>

<div class="orderListHeight tab-pane" id="Orders">
    <ul id="change" class="pagination">
        <li id="oBack"><a href="#page">&laquo;</a></li>

        <c:forEach varStatus="status" items="${ordersPaginationlist}" var="pl">
            <li value="${pl.intValue()}" name="page${pl.intValue()}"
                class="oNumbered <c:if test="${status.first}">active</c:if>"><a href="#page${pl.intValue()}"
                                                                                class="page">${pl.intValue()}</a></li>
        </c:forEach>

        <li id="oNext"><a href="#page">&raquo;</a></li>
    </ul>

    <div class="orderListHeight tab-pane" style="overflow-y: scroll">
        <table class="table table-hover" ID="ordersTable">
            <input type="hidden" id="ordersPageNumber" value="${ordersPageNumber}"/>
            <tr>
                <th>
                    <p> Check all</p>

                    <div id="checkAll" class="checkbox">
                        <label>
                            <input type="checkbox" value="${row.getId()}">
                        </label>
                    </div>
                </th>
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
    </div>
    <input type="button" class="ordersButtons btn btn-primary" value="Cancel the Order" id="cancel">
    <input type="button" class="ordersButtons btn btn-primary" value="accept the order" id="accept">
    <input type="button" class="ordersButtons btn btn-primary" value="restore order" id="restore">
</div>

<script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
<script src="<c:url value="/script/authentication.js"/>"></script>