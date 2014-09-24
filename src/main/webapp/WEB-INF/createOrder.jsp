<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:bundle basename="i18n.messages">
    <html>

    <head>
        <title>Welcome</title>
        <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>'>
            <%--<link rel='stylesheet' href='<c:url value="/webjars/bootstrap-datepicker/1.3.0/css/datepicker3.css"/>'>--%>
            <%--<link rel='stylesheet' href='<c:url value="/webjars/bootstrap-datetimepicker/2.3.1/css/bootstrap-datetimepicker.css"/>'>--%>
        <link rel='stylesheet'
              href='<c:url value="/webjars/bootstrap-timepicker/0.2.3/css/bootstrap-timepicker.min.css"/>'>
        <link rel='stylesheet' href='<c:url value="/style/createOrder.css"/>'>
    </head>
    <body background="<c:url value="/style/img/background.jpg"/>">

    <t:navigation></t:navigation>

    <div class="main panel panel-default">

        <div class="client-info">
            <t:authentication></t:authentication>

            <c:if test="${not empty user}">
                <t:payment></t:payment>
            </c:if>
        </div>

        <c:if test="${empty user}">
            <div class="nonLogin"><h1>Login please !</h1></div>
        </c:if>

        <c:if test="${not empty user}">
            <div class="panel panel-default create-order-panel">
                <div class="center"><h1>Creation the order</h1></div>
                <div>
                    <div class="orderText form-group">
                        <div class="center"><label for="Datee">Delivery date</label></div>
                        <input type="text" name="deliverydate" placeholder="Date" class="form-control timepicker"
                               id="Datee">
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
                        <div class="center"><label for="Additional Information">Additional Information</label></div>
                        <textarea name="additionalinformation" value="Count" class="form-control"
                                  id="Additional Information">
                        </textarea>
                    </div>
                </div>

                <div class="center form-group">
                    <label class="paymentTypeContent">Online =></label><input type="radio" name="paymentType"
                                                                              value="online">

                    <p>
                        <label class="paymentTypeContent">Cache =></label><input type="radio" name="paymentType"
                                                                                 value="cache">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="create">Create order</button>
                </div>

                <div class="input-append bootstrap-timepicker">
                    <input id="timepicker2" type="text" class="input-small">
                    <span class="add-on">
                        <i class="icon-time"></i>
                    </span>
                </div>

                <script type="text/javascript">
                    $('#timepicker2').timepicker({
                        minuteStep: 1,
                        template: 'modal',
                        appendWidgetTo: 'body',
                        showSeconds: true,
                        showMeridian: false,
                        defaultTime: false
                    });
                </script>

            </div>
        </c:if>

        <div class="clear"></div>
    </div>

    <t:footer></t:footer>

    <script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"/>"></script>
        <%--<script src="<c:url value="/webjars/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"/>"></script>--%>
        <%--<script src="<c:url value="/webjars/bootstrap-datetimepicker/2.3.1/js/bootstrap-datetimepicker.js"/>"></script>--%>
    <script src="<c:url value="/webjars/bootstrap-timepicker/0.2.3/js/bootstrap-timepicker.min.js"/>"></script>
    <script src="<c:url value="/script/createOrder.js"/>"></script>
    </body>
    </html>
</fmt:bundle>
