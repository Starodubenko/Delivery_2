<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:bundle basename="i18n.messages">
<html>

<head>
    <title>Welcome</title>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>'>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap-datepicker/1.3.0/css/datepicker3.css"/>'>
    <link rel='stylesheet' href='<c:url value="/style/dispatcher.css"/>'>
    <link rel='stylesheet' href='<c:url value="/style/retractableWindow.css"/>'>
</head>
<body background="<c:url value="/style/img/background.jpg"/>" onload="">

<t:navigation></t:navigation>

<div class="main panel panel-default">
    <t:authentication></t:authentication>

    <c:if test="${empty user}">
        <c:redirect url="/do/welcome"/>
    </c:if>

    <div class="orderList panel panel-default">
        <ul class="nav nav-tabs  nav-justified" role="tablist">
            <li id="t1" class="active"><a href="#Clients" role="tab" data-toggle="tab">Clients</a></li>
            <li id="t2"><a href="#Orders" role="tab" data-toggle="tab">Orders</a></li>
        </ul>
        <div class="tab-content">
            <div class="orderListHeight tab-pane active" id="Clients">
                <div id="clients-block">
                    <ul id="changee" class="pagination">
                        <li id="cBack"><a href="#page">&laquo;</a></li>

                        <c:forEach varStatus="status" items="${clientsPaginationlist}" var="pl">
                            <li value="${pl.intValue()}" name="page${pl.intValue()}"
                                class="cNumbered "><a href="#page${pl.intValue()}"
                                                      class="page">${pl.intValue()}</a>
                            </li>
                        </c:forEach>

                        <li id="cNext"><a href="#page">&raquo;</a></li>
                    </ul>

                    <div class="form-group rows-count floatRight">
                        <label class="labelCount" for="clientsrows">Rows count</label>

                        <form action="${pageContext.request.contextPath}/do/dispatcher">
                            <div class="input-group">
                                <input type="text" name="clientsrows" id="clientsrows" value="${clientsRowsCount}"
                                       class="form-control textCount">
                        <span class="input-group-btn">
                            <button class="btn btn-default floatRight" type="submit">aply</button>
                        </span>
                            </div>
                        </form>
                    </div>

                    <div class="orderListHeight tab-pane" style="overflow-y: scroll">
                        <table class="table table-hover" ID="clientsTable">
                            <input type="hidden" id="clientsPageNumber" value="${clientsPageNumber}"/>
                            <tr>
                                <th>ID</th>
                                <th>Firs name</th>
                                <th>Middle name</th>
                                <th>Last name</th>
                                <th>Address</th>
                                <th>Telephone</th>
                                <th>Mobilephone</th>
                                <th>Create order</th>
                            </tr>
                            <c:forEach var="row" items="${clientsList}">
                                <tr>
                                    <td class="id">${row.getId()}</td>
                                    <td>${row.getFirstName()}</td>
                                    <td>${row.getMiddleName()}</td>
                                    <td>${row.getLastName()}</td>
                                    <td>${row.getAddress()}</td>
                                    <td>${row.getTelephone()}</td>
                                    <td>${row.getMobilephone()}</td>
                                    <td class=" createOrder">
                                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                                value="${row.getId()}"
                                                data-target="#myModel">Order
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>

            <div class="orderListHeight tab-pane" id="Orders">
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

                <input type="button" class="ordersButtons btn btn-primary" value="Cancel the Order" id="cancel">
                <input type="button" class="ordersButtons btn btn-primary" value="accept the order" id="accept">
                <input type="button" class="ordersButtons btn btn-primary" value="restore order" id="restore">

            </div>

            <div class="panel-group edit-panel" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading edit-panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                Edit
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse">
                        <div class="panel-body">
                            <form id="changeForm" action="${pageContext.request.contextPath}/do/saveOrderData">

                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panel-group edit-panel">
                <div class="panel panel-default">
                    <div class="panel-heading edit-panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseSearche">
                                Searching
                            </a>
                        </h4>
                    </div>
                    <div id="collapseSearche" class="panel-collapse collapse">
                        <div class="panel-body">
                            <form id="findForm">
                                <div id="searcheFields">
                                    <div class="form-group edit">
                                        <div class="center"><label for="order-id" class="edit-field">ID</label>
                                        </div>
                                        <input type="text" class="form-control" name="order-id" id="order-id"
                                               value="39">
                                    </div>
                                    <div class="form-group edit">
                                        <div class="center"><label for="order-date" class="edit-field">Date</label>
                                        </div>
                                        <input type="text" class="form-control searchDate" name="order-date"
                                               id="order-date"
                                               value="2014-09-14">
                                    </div>
                                    <div class="form-group edit">
                                        <div class="center"><label for="order-goods-name"
                                                                   class="edit-field">Goods</label></div>
                                        <input type="text" class="form-control" name="order-goods-name"
                                               id="order-goods-name"
                                               value="Water 20L">
                                    </div>
                                    <div class="form-group edit">
                                        <div class="center"><label for="delivery-time" class="edit-field">Goods
                                            count</label></div>
                                        <input type="text" class="form-control" name="order-goods-count"
                                               id="order-goods-count"
                                               value="${order.getPeriod().getPeriod()}">
                                    </div>
                                    <div class="form-group edit">
                                        <div class="center"><label for="delivery-time" class="edit-field">Order
                                            cost</label></div>
                                        <input type="text" class="form-control" name="order-cost" id="order-cost"
                                               value="${order.getPeriod().getPeriod()}">
                                    </div>
                                    <div class="form-group edit">
                                        <div class="center"><label for="delivery-time" class="edit-field">Delivery
                                            date</label></div>
                                        <input type="text" class="form-control searchDate" name="delivery-date"
                                               id="delivery-date"
                                               value="${order.getPeriod().getPeriod()}">
                                    </div>
                                    <div class="form-group edit">
                                        <div class="center"><label for="delivery-time" class="edit-field">Delivery
                                            time</label></div>
                                        <input type="text" class="form-control" name="delivery-time"
                                               id="delivery-time"
                                               value="${order.getPeriod().getPeriod()}">
                                    </div>
                                    <div class="form-group edit">
                                        <div class="center"><label for="delivery-time" class="edit-field">Additional
                                            info</label></div>
                                        <input type="text" class="form-control" name="order-addInfo"
                                               id="order-addInfo"
                                               value="${order.getPeriod().getPeriod()}">
                                    </div>
                                    <div class="form-group edit">
                                        <div class="center"><label for="delivery-time"
                                                                   class="edit-field">status</label></div>
                                        <input type="text" class="form-control" name="order-status"
                                               id="order-status"
                                               value="${order.getPeriod().getPeriod()}">
                                    </div>
                                    <br>
                                    <input type="button" class="btn btn-primary" id="search" value="Search">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        <div class="clear"></div>
    </div>
</div>

<t:footer></t:footer>

<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="confirmModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="confirmModalLabel">Do you confirm to change the data</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                <button type="submit" class="btn btn-primary" form="changeForm">Yes</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="myModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">Create order</h4>
            </div>
                <%--<form action="${pageContext.request.contextPath}/do/fastCreateOrder">--%>
            <div>
                <div class="orderText form-group">
                <div class="center"><label for="Date">Delivery date</label></div>
                    <input type="text" name="deliverydate" value="Date" class="form-control datepicker" id="Date">
                </div>
                <div class="orderText form-group">
                <div class="center"><label for="PeriodTime">Delivery time</label></div>
                    <select class="form-control" name="deliverytime" value="Time" class="form-control"
                            id="PeriodTime">
                        <c:forEach var="period" items="${periods}">
                            <option>${period.period}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="orderText form-group">
                    <div class="center"><label for="GoodsName">Goods type</label></div>
                    <select class="form-control" name="goodsname" value="Goods name" class="form-control"
                            id="GoodsName">
                        <c:forEach var="goodss" items="${goods}">
                            <option>${goodss.getGoodsName()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="orderText form-group">
                    <div class="center"><label for="Count">Goods count</label></div>
                    <input type="text" name="goodscount" value="Count" class="form-control" id="Count">
                </div>
                <div class="orderInfo form-group">
                    <div class="center"><label for="AdditionalInformation">Additional Information</label></div>
                    <textarea name="additionalinformation" value="Count" class="form-control"
                              id="AdditionalInformation">
                    </textarea>
                </div>
            </div>

            <div class="center form-group">
                <input type="hidden" name="PaymentType" value="cache" id="PaymentType">
            </div>

            <p id="errorCreatingOrder"></p>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="check">Create order</button>
            </div>

                <%--</form>--%>
        </div>
    </div>
</div>

<script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"/>"></script>
<script src="<c:url value="/script/dispatcher.js"/>"></script>
</body>
</html>
</fmt:bundle>


