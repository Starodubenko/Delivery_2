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
            <div class="orderListHeight tab-pane active" id="Clients" style="overflow-y: scroll">
                <ul id="change" class="pagination">
                    <li><a href="${clientsPagename}?clientspage=${clientsPageNumber-1}" form="back">&laquo;</a></li>
                    <li>
                        <c:forEach items="${clientsPaginationlist}" var="pl">
                    <li><a href="${clientsPagename}?clientspage=${pl.intValue()}&rows=${clientsRowsCount}"
                           name="clientspage">${pl.intValue()}</a></li>
                    </c:forEach>
                    </li>
                    <li><a href="${clientsPagename}?clientspage=${clientsPageNumber+1}&rows=${clientsRowsCount}"
                           form="next">&raquo;</a></li>
                </ul>
                <table class="table table-hover">
                    <tr>
                        <th></th>
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
                            <td>
                                <div class="checkbox">
                                    <label>
                                        <input type="radio" name="IdOrder" value="${row.getId()}">
                                    </label>
                                </div>
                            </td>
                            <td>${row.getId()}</td>
                            <td>${row.getFirstName()}</td>
                            <td>${row.getMiddleName()}</td>
                            <td>${row.getLastName()}</td>
                            <td>${row.getAddress()}</td>
                            <td>${row.getTelephone()}</td>
                            <td>${row.getMobilephone()}</td>
                            <td>
                                <form></form>
                                <button name="">
                                    Order
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="orderListHeight tab-pane" id="Orders" style="overflow: scroll">
                <ul id="changee" class="pagination">
                    <li><a href="${ordersPagename}?orderspage=${ordersPageNumber-1}" form="back">&laquo;</a></li>
                    <li>
                        <c:forEach items="${ordersPaginationlist}" var="pl">
                    <li><a href="${ordersPagename}?orderspage=${pl.intValue()}&rows=${ordersRowsCount}"
                           name="orderspage">${pl.intValue()}</a></li>
                    </c:forEach>
                    </li>
                    <li><a href="${ordersPagename}?orderspage=${ordersPageNumber+1}&rows=${ordersRowsCount}"
                           form="next">&raquo;</a></li>
                </ul>
                <table class="table table-hover">
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


<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">Registration form</h4>
            </div>
            <form action="${pageContext.request.contextPath}/do/ClientRegistration" method="post">
                <div class="registration">
                    <div class="form-group">
                        <label for="Login" class="name_surname">Login</label>
                        <input type="text" name="login" value="Ivanov99" class="form-control"
                               id="Login">
                    </div>
                    <div class="form-group">
                        <label for="Password" class="name_surname">Password</label>
                        <input type="text" name="password" value="Ivanov9" class="form-control"
                               id="Password">
                    </div>
                    <div class="form-group">
                        <label for="Firsname" class="name_surname">Firsname</label>
                        <input type="text" name="firstname" value="Ivan" class="form-control"
                               id="Firsname">
                    </div>
                    <div class="form-group">
                        <label for="Lastname" class="name_surname">Lastname</label>
                        <input type="text" name="lastname" value="Ivanov" class="form-control"
                               id="Lastname">
                    </div>
                    <div class="form-group">
                        <label for="Middlename" class="name_surname">Middlename</label>
                        <input type="text" name="middlename" value="Ivanovich" class="form-control"
                               id="Middlename">
                    </div>
                    <div class="form-group">
                        <label for="Address" class="name_surname">Address</label>
                        <input type="text" name="address" value="Ivanova-32" class="form-control"
                               id="Address">
                    </div>
                    <div class="form-group">
                        <label for="Telephone" class="name_surname">Telephone</label>
                        <input type="text" name="telephone" value="87212965896" class="form-control"
                               id="Telephone">
                    </div>
                    <div class="form-group">
                        <label for="Mobilephone" class="name_surname">Mobilephone</label>
                        <input type="text" name="mobilephone" value="87007778958" class="form-control"
                               id="Mobilephone">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close registration form
                    </button>
                    <button type="submit" class="btn btn-primary">Registration</button>
                </div>
                <input type="hidden" name="actionName" value="ClientRegistrationAction">
                <input type="hidden" name="TableName" value="Clients">
            </form>
        </div>
    </div>
</div>


<script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/script/dispatcher.js"/>"></script>
</body>
</html>
</fmt:bundle>

