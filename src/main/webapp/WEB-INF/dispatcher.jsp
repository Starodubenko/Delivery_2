<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:bundle basename="i18n.messages">
<html>

<head>
    <title>Welcome</title>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>'>
    <link rel='stylesheet' href='<c:url value="/style/dispatcher.css"/>'>
</head>
<body background="<c:url value="/style/img/background.jpg"/>" onload="">

<div class="main panel panel-default">
    <div class="authentication panel panel-default">
        <c:if test="${not empty user}">
            <div id="autorized" class="border panel panel-default">
                <form action="<c:url value="/do/logout"/>">
                    <p align="center">Welcome</p>

                    <p align="center" class="name_surname">${user.getFirstName()}</p>

                    <p align="center" class="name_surname">${user.getLastName()}</p>
                    <br>
                    <input type="submit" class="logoutbtn btn btn-primary" value="Logout">
                </form>
            </div>
        </c:if>
        <c:if test="${empty user}">
            <c:redirect url="/do/welcome"/>
        </c:if>
    </div>

    <div class="orderList panel panel-default">
        <ul class="nav nav-tabs  nav-justified" role="tablist">
            <li id="t1" class="active"><a href="#Clients" role="tab" data-toggle="tab">Clients</a></li>
            <li id="t2"><a href="#Orders" role="tab" data-toggle="tab">Orders</a></li>
        </ul>
        <div class="tab-content">
            <div class="orderListHeight tab-pane active" id="Clients">
                    <%--<ul id="change" class="pagination">--%>
                    <%--<li><a href=""&lt;%&ndash;${clientsPagename}?clientspage=${clientsPageNumber-1}"&ndash;%&gt; form="back" id="cBackPage">&laquo;</a></li>--%>
                    <%--<c:forEach items="${clientsPaginationlist}" var="pl">--%>
                    <%--<li><a href="${clientsPagename}?clientspage=${pl.intValue()}&rows=${clientsRowsCount}"--%>
                    <%--name="clientspage" type="submit">${pl.intValue()}</a></li>--%>
                    <%--</c:forEach>--%>
                    <%--<li><a href="${clientsPagename}?clientspage=${clientsPageNumber+1}&rows=${clientsRowsCount}"--%>
                    <%--form="next" id="nextPage">&raquo;</a></li>--%>
                    <%--</ul>--%>
                <ul id="changee" class="pagination">
                    <li id="cBack"><a href="#">&laquo;</a></li>

                    <c:forEach items="${clientsPaginationlist}" var="pl">
                        <li value="${pl.intValue()}" name="page${pl.intValue()}" class="cNumbered"><a href="#"
                                                                                                      class="page">${pl.intValue()}</a>
                        </li>
                    </c:forEach>

                    <li id="cNext"><a href="#">&raquo;</a></li>
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
                                <td>${row.getId()}</td>
                                <td>${row.getFirstName()}</td>
                                <td>${row.getMiddleName()}</td>
                                <td>${row.getLastName()}</td>
                                <td>${row.getAddress()}</td>
                                <td>${row.getTelephone()}</td>
                                <td>${row.getMobilephone()}</td>
                                <td>
                                    <button type="button" class="btn btn-primary" data-toggle="modal"
                                            data-target="#createOrder">
                                        Order
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <div class="orderListHeight tab-pane" id="Orders">
                    <%--<ul id="changee" class="pagination">--%>
                    <%--<li><a href="${ordersPagename}?orderspage=${ordersPageNumber-1}&rows=${ordersRowsCount}" form="back">&laquo;</a></li>--%>

                    <%--<c:forEach items="${ordersPaginationlist}" var="pl">--%>
                    <%--<li><a href="${ordersPagename}?orderspage=${pl.intValue()}&rows=${ordersRowsCount}"name="orderspage" class="page">${pl.intValue()}</a></li>--%>
                    <%--</c:forEach>--%>

                    <%--<li><a href="${ordersPagename}?orderspage=${ordersPageNumber+1}&rows=${ordersRowsCount}" form="next">&raquo;</a></li>--%>
                    <%--</ul>--%>
                <ul id="changee" class="pagination">
                    <li id="oBack"><a href="#">&laquo;</a></li>

                    <c:forEach items="${ordersPaginationlist}" var="pl">
                        <li value="${pl.intValue()}" name="page${pl.intValue()}" class="oNumbered"><a href="#"
                                                                                                      class="page">${pl.intValue()}</a>
                        </li>
                    </c:forEach>

                    <li id="oNext"><a href="#">&raquo;</a></li>
                </ul>
                <div class="orderListHeight tab-pane" style="overflow-y: scroll">
                    <table class="table table-hover" ID="ordersTable">
                        <input type="hidden" id="ordersPageNumber" value="${ordersPageNumber}"/>
                        <tr>
                            <th>
                                <p> Check all</p>

                                <div id="checkAll" class="checkbox">
                                    <label>
                                        <input type="checkbox" name="IdOrder" value="${row.getId()}">
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
                <input type="submit" class="ordersButtons cancelOrderButton btn btn-primary" data-toggle="modal"
                       data-target="#cancelOrder" value="Cancel the Order">
                <input type="submit" class="ordersButtons cancelOrderButton btn btn-primary" data-toggle="modal"
                       data-target="#cancelOrder" value="accept the order">
                <input type="submit" class="ordersButtons cancelOrderButton btn btn-primary" data-toggle="modal"
                       data-target="#cancelOrder" value="restore order">
            </div>
        </div>

        <div class="searchRow">
            <div class="col-lg-6">
                <form action="${pageContext.request.contextPath}/do/searching">
                    <div class="input-group">
                        <input type="text" class="form-control" name="searcheValue">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">Search</button>
                        </span>
                    </div>
                    <input type="hidden" name="daoName" value="clientDao">
                </form>
            </div>
        </div>
    </div>

    <div class="contactInformation panel panel-default" style="overflow-y: scroll">
        <c:forEach var="contact" items="${contacts}">
            <label>${contact.owner}: ${contact.telephone}</label>
        </c:forEach>
    </div>
</div>


<div class="modal fade" id="createOrder" tabindex="-1" role="dialog" aria-labelledby="createOrderLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="createOrderLabel">Create order</h4>
            </div>
            <form action="${pageContext.request.contextPath}/do/createOrder">
                <div class="registration">

                    <div class="orderText form-group has-error has-feedback">
                        <label for="Date">Delivery date</label>
                        <input type="text" name="deliverydate" value="Date" class="form-control datepicker" id="Date">
                    </div>
                    <div class="orderText form-group has-success">
                        <label for="PeriodTime">Delivery time</label>
                        <select class="form-control" name="deliverytime" value="Time" class="form-control"
                                id="PeriodTime">
                            <c:forEach var="period" items="${periods}">
                                <option>${period.period}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="orderText form-group">
                        <label for="GoodsName">Goods type</label>
                        <select class="form-control" name="goodsname" value="Goods name" class="form-control"
                                id="GoodsName">
                            <c:forEach var="goodss" items="${goods}">
                                <option>${goodss.getGoodsName()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="orderText form-group">
                        <label for="Count">Goods count</label>
                        <input type="text" name="goodscount" value="Count" class="form-control" id="Count">
                    </div>
                    <div class="form-group">
                        <label for="Additional Information">Additional Information</label>
                        <textarea name="additionalinformation" value="Count" class="form-control"
                                  id="Additional Information">
                        </textarea>
                    </div>
                </div>
                <div class="paymentType form-group">
                    <label class="paymentTypeContent">Online</label><input type="radio" name="PaymentType"
                                                                           value="online">
                    <label class="paymentTypeContent">Cache</label><input type="radio" name="PaymentType" value="cache">
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close order form
                    </button>
                    <button type="submit" class="btn btn-primary">Create order</button>
                </div>
            </form>
        </div>
    </div>
</div>


<script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"/>"></script>
    <%--<script src="<c:url value="/script/ajaxTest.js"/>"></script>--%>
<script src="<c:url value="/script/dispatcher.js"/>"></script>
</body>
</html>
</fmt:bundle>

