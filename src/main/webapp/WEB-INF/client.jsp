<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:bundle basename="i18n.messages">
<html>

<head>
    <title>Welcome</title>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>'>
    <link rel='stylesheet' href='<c:url value="/script/js/jquery-ui-1.11.1.custom/jquery-ui.min.css"/>'>
    <link rel='stylesheet' href='<c:url value="/style/client.css"/>'>
</head>
<body background="<c:url value="/style/img/background.jpg"/>" onload="">

<div class="main panel panel-default">

    <div class="right_top_area">
        <div class="authentication panel panel-default">
            <c:if test="${not empty user}">
                <div id="autorized" class="border panel panel-default">
                    <form action="<c:url value="/do/logout"/>">
                        <p align="center" >Welcome</p>
                        <p align="center" class="name_surname">${user.getFirstName()}</p>
                        <p align="center" class="name_surname">${user.getLastName()}</p>
                        <br>
                        <input type="submit" class="logoutbtn btn btn-primary" value="Logout">
                    </form>
                </div>
            </c:if>
        </div>

        <div class="eWalet panel panel-default">
            <label class="balance"> Your balance: </label>
            <label> ${user.getVirtualBalance()} </label>
            <br>
            <input class="logoutbtn btn btn-primary" type="submit" value="Top-up">
        </div>

    </div>

    <div class="banner panel panel-default">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>

            <div class="carousel-inner">
                <div class="item active">
                    <img src="<c:url value="/style/img/water/water.jpg"/>" alt="...">

                    <div class="carousel-caption">
                    </div>
                </div>
                <div class="item">
                    <img src="<c:url value="/style/img/water/water1.jpg"/>" alt="...">

                    <div class="carousel-caption">
                    </div>
                </div>
                <div class="item">
                    <img src="<c:url value="/style/img/water/water2.jpg"/>" alt="...">

                    <div class="carousel-caption">
                    </div>
                </div>
            </div>

            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
        </div>
    </div>

    <div class="createOrder panel panel-default">

    </div>


    <div class="orderList panel panel-default">
        <ul class="nav nav-tabs  nav-justified" role="tablist">
            <li class="active"><a href="#Today" role="tab" data-toggle="tab">Today</a></li>
            <li><a href="#HistoryOrders" role="tab" data-toggle="tab">History of orders</a></li>
        </ul>
        <div class="tab-content">
            <div class="orderListHeight tab-pane active" id="Today" style="overflow-y: scroll">
                <table class="table table-hover">
                    <tr>
                        <th>ID</th>
                        <th>Order date</th>
                        <th>Goods name</th>
                        <th>Goods count</th>
                        <th>Delivry date</th>
                        <th>Delivry time</th>
                        <th>Additional info</th>
                        <th>Status</th>
                    </tr>
                    <c:forEach var="row" items="${todayOrders}">
                        <tr style="overflow-y: scroll">
                            <td>${row.getId()}</td>
                            <td>${row.getOrderDate()}</td>
                            <td>${row.getGoods().getGoodsName()}</td>
                            <td>${row.getCount()}</td>
                            <td>${row.getDeliveryDate()}</td>
                            <td>${row.getPeriod().getPeriod()}</td>
                            <td>${row.getAdditionalInfo()}</td>
                            <td>${row.getStatus().getStatusName()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="orderListHeight tab-pane" id="HistoryOrders" style="overflow-y: scroll" >
                <table class="table table-hover">
                    <tr>
                        <th>ID</th>
                        <th>Order date</th>
                        <th>Goods name</th>
                        <th>Goods count</th>
                        <th>Delivry date</th>
                        <th>Delivry time</th>
                        <th>Additional info</th>
                        <th>Status</th>
                    </tr>
                    <c:forEach var="row" items="${pastOrders}">
                        <tr>
                            <td>${row.getId()}</td>
                            <td>${row.getOrderDate()}</td>
                            <td>${row.getGoods().getGoodsName()}</td>
                            <td>${row.getCount()}</td>
                            <td>${row.getDeliveryDate()}</td>
                            <td>${row.getPeriod().getPeriod()}</td>
                            <td>${row.getAdditionalInfo()}</td>
                            <td>${row.getStatus().getStatusName()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <table>

        </table>
        <button class="createOrderButton btn btn-primary" data-toggle="modal" data-target="#myModal">
            Create an Order
        </button>
        <button class="cancelOrderButton btn btn-primary" data-toggle="modal" data-target="#myModal">
            Cancel the Order
        </button>
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
                <h4 class="modal-title" id="myModalLabel">Create order</h4>
            </div>
            <form action="${pageContext.request.contextPath}/do/createOrder">
                <div class="registration">

                    <div class="orderText form-group">
                        <label for="Date">Delivery date</label>
                        <input type="text" name="deliverydate" value="Date" class="form-control" id="Date">
                    </div>
                    <div class="orderText form-group">
                        <label for="PeriodTime">Delivery time</label>
                        <input type="text" name="deliverytime" value="Time" class="form-control"
                               id="PeriodTime">
                    </div>
                    <div class="orderText form-group">
                        <label for="GoodsName">Goods type</label>
                        <input type="text" name="goodsname" value="Goods name" class="form-control"
                               id="GoodsName">
                    </div>
                    <div class="orderText form-group">
                        <label for="Count">Goods count</label>
                        <input type="text" name="goodscount" value="Count" class="form-control" id="Count">
                    </div>
                    <div class="form-group">
                        <label for="Additional Information">Additional Information</label>
                        <textarea name="additionalinformation" value="Count" class="form-control" id="Additional Information">
                        </textarea>
                    </div>
                </div>
                <div class="paymentType form-group">
                    <label class="paymentTypeContent">Online</label><input type="radio" name="PaymentType" value="online">
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

<script src="<c:url value="/script/client.js"/>"></script>
<script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"/>"></script>

<script src="<c:url value="/webjars/jquery-ui/1.11.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/script/client.js"/>"></script>
</body>
</html>
</fmt:bundle>
