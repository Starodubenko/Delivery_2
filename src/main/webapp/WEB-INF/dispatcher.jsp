<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:bundle basename="i18n.messages">
<html>

<head>
    <title>Welcome</title>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>'>
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
                <ul id="changee" class="pagination">
                    <li id="cBack"><a href="#page">&laquo;</a></li>

                    <c:forEach varStatus="status" items="${clientsPaginationlist}" var="pl">
                        <li value="${pl.intValue()}" name="page${pl.intValue()}"
                            class="cNumbered <c:if test="${status.first}">active</c:if>"><a href="#page${pl.intValue()}"
                                                                                            class="page">${pl.intValue()}</a>
                        </li>
                    </c:forEach>

                    <li id="cNext"><a href="#page">&raquo;</a></li>
                </ul>
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
                                            data-target="#myModel">Order
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>

            <select class="form-control switcher" id="switchStatusOrser">
                <option>Waiting</option>
                <option>Active</option>
                <option>Canceled</option>
                <option>Executed</option>
            </select>

            <div class="orderListHeight tab-pane" id="Orders">
                <ul id="change" class="pagination">
                    <li id="oBack"><a href="#page">&laquo;</a></li>

                    <c:forEach varStatus="status" items="${ordersPaginationlist}" var="pl">
                        <li value="${pl.intValue()}" name="page${pl.intValue()}"
                            class="oNumbered <c:if test="${status.first}">active</c:if>"><a href="#page${pl.intValue()}"
                                                                                            class="page">${pl.intValue()}</a>
                        </li>
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
        </div>

        <div class="searchRow">
            <div class="col-lg-6">
                <form action="${pageContext.request.contextPath}/do/searching">
                    <div class="input-group">
                        <span class="searcheWidth input-group-btn">
                            <select class="form-control" name="columnName">
                                <option>ID</option>
                                <option>First name</option>
                                <option>Middle name</option>
                                <option>Last name</option>
                                <option>Address</option>
                                <option>Telephone</option>
                                <option>Mobile phone</option>
                            </select>
                        </span>
                        <input type="text" class="form-control" name="desiredValue">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit">Search</button>
                        </span>
                    </div>
                    <input type="hidden" name="daoName" value="clientDao">
                </form>
            </div>
        </div>
    </div>

    <div class="clear"></div>
</div>

<t:footer></t:footer>

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
                <div class="orderText form-group has-error has-feedback">
                    <div class="center"><label for="Date">Delivery date</label></div>
                    <input type="text" name="deliverydate" value="Date" class="form-control datepicker" id="Date">
                </div>
                <div class="orderText form-group has-success">
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
<script src="<c:url value="/script/dispatcher.js"/>"></script>
</body>
</html>
</fmt:bundle>


