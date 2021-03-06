<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:bundle basename="i18n.messages">
<html>

<head>
    <title>Welcome</title>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap-datepicker/1.3.0/css/datepicker3.css"/>'>
    <link rel='stylesheet'
          href='<c:url value="/webjars/bootstrap-datetimepicker/2.3.1/css/bootstrap-datetimepicker.css"/>'>
    <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>'>

    <link rel='stylesheet' href='<c:url value="/style/client.css"/>'>
</head>
<body background="<c:url value="/style/img/background.jpg"/>">

<t:navigation></t:navigation>

<div class="main panel panel-default">

    <div class="client-info">
        <t:authentication></t:authentication>

        <t:payment></t:payment>
    </div>

    <div class="orderList panel panel-default">
        <ul class="nav nav-tabs  nav-justified" role="tablist">
            <li class="active"><a href="#Today" role="tab" data-toggle="tab">Today</a></li>
            <li><a href="#HistoryOrders" role="tab" data-toggle="tab">History of orders</a></li>
        </ul>
        <form action="${pageContext.request.contextPath}/do/cancelOrder" id="cancelOrderForm">
            <div class="tab-content">
                <div class="orderListHeight tab-pane active" id="Today" style="overflow: scroll">
                    <table class="table table-hover">
                        <tr>
                            <th></th>
                            <th>ID</th>
                            <th>Order date</th>
                            <th>Goods name</th>
                            <th>Goods count</th>
                            <th>Order cost</th>
                            <th>Paid</th>
                            <th>Delivry date</th>
                            <th>Delivry time</th>
                            <th>Additional info</th>
                            <th>Status</th>
                        </tr>
                        <c:forEach var="row" items="${todayOrders}">
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
                                <td>${row.getPaid()}</td>
                                <td>${row.getDeliveryDate()}</td>
                                <td>${row.getPeriod().getPeriod()}</td>
                                <td>${row.getAdditionalInfo()}</td>
                                <td>${row.getStatus().getStatusName()}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="orderListHeight tab-pane" id="HistoryOrders" style="overflow: scroll">
                    <table class="table table-hover">
                        <tr>
                            <th></th>
                            <th>ID</th>
                            <th>Order date</th>
                            <th>Goods name</th>
                            <th>Goods count</th>
                            <th>Order cost</th>
                            <th>Paid</th>
                            <th>Delivry date</th>
                            <th>Delivry time</th>
                            <th>Additional info</th>
                            <th>Status</th>
                        </tr>
                        <c:forEach var="row" items="${pastOrders}">
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
                                <td>${row.getPaid()}</td>
                                <td>${row.getDeliveryDate()}</td>
                                <td>${row.getPeriod().getPeriod()}</td>
                                <td>${row.getAdditionalInfo()}</td>
                                <td>${row.getStatus().getStatusName()}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <button type="button" class="createOrderButton btn btn-primary" data-toggle="modal"
                    data-target="#myModal">
                Create an Order
            </button>
            <input type="submit" class="cancelOrderButton btn btn-primary" data-toggle="modal"
                   data-target="#cancelOrder" value="Cancel the Order">
        </form>
    </div>

    <div class="clear"></div>

</div>

<t:footer></t:footer>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="createOrderLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span
                        aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="createOrderLabel">Create order</h4>
            </div>
            <form action="${pageContext.request.contextPath}/do/fastCreateOrder">
                <div class="registration">

                    <div class="orderText form-group">
                        <label for="Date">Delivery date</label>
                        <input type="text" name="deliverydate" placeholder="Date" class="form-control datetimepicker"
                               id="Date">
                    </div>
                    <div class="orderText form-group">
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
                        <input type="text" name="goodscount" placeholder="Count" class="form-control" id="Count">
                    </div>
                    <div class="form-group">
                        <label for="Additional Information">Additional Information</label>
                        <textarea name="additionalinformation" value="Count" class="form-control"
                                  id="Additional Information">
                        </textarea>
                    </div>
                </div>
                <div class="paymentType form-group">
                    <label class="paymentTypeContent">Online</label><input type="radio" name="paymentType"
                                                                           value="online">
                    <label class="paymentTypeContent">Cache</label><input type="radio" name="paymentType" value="cache">
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

<div class="modal fade" id="Payment" tabindex="-1" role="dialog" aria-labelledby="PaymentLabel"
     aria-hidden="true">
    <form action="${pageContext.request.contextPath}/do/payment" method="post">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="PaymentLabel">Payment</h4>
                </div>
                <div class="paymentform">
                    <div class="form-group">
                        <p for="SecretCode" align="center">input the secret code of your payment card</p>
                        <input type="text" name="SecretCode" placeholder="Enter the secret code" class="form-control"
                               id="SecretCode">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Pay</button>
                </div>
            </div>
        </div>
    </form>
    <button type="button" class="btn btn-default" data-dismiss="modal">Close payment form</button>
</div>


<div class="clear"></div>


<script src="<c:url value="/webjars/jquery/1.11.1/jquery.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"/>"></script>
<script src="<c:url value="/webjars/bootstrap-datetimepicker/2.3.1/js/bootstrap-datetimepicker.js"/>"></script>
<script src="<c:url value="/script/client.js"/>"></script>
</body>
</html>
</fmt:bundle>

