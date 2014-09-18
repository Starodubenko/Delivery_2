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
        <link rel='stylesheet' href='<c:url value="/style/admin.css"/>'>
    </head>
    <body background="<c:url value="/style/img/background.jpg"/>" onload="">

    <t:navigation></t:navigation>

    <div class="main panel panel-default">
        <t:authentication></t:authentication>

        <div class="table-name form-group">
            <select class="form-control" name="tableName" class="form-control">
                    <%--<c:forEach var="period" items="${periods}">--%>
                <option>Table Name</option>
                <option>Table Name</option>
                <option>Table Name</option>
                    <%--</c:forEach>--%>
            </select>
        </div>

        <div class="input-group searchRow">
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

        <div class="clear"></div>

        <div class="panel panel-default dataBase">
            <div class="panel panel-default table-data-base">

            </div>

            <button class="btn btn-default buttons">Insert</button>
            <button class="btn btn-default buttons">Save</button>
            <button class="btn btn-default buttons">Delete</button>
        </div>
        <div class="panel panel-default log">
            <div class="center"><label>Log</label></div>
            <div class="log-report-content panel panel-default">

            </div>
            <div class="buttons input-daterange input-group" id="datepickerLog">
                <input type="text" class="input-sm form-control" name="start"/>
                <span class="input-group-addon">to</span>
                <input type="text" class="input-sm form-control" name="end"/>
            </div>
            <button class="btn btn-default buttons">Searche</button>
        </div>
        <div class="panel panel-default report">
            <div class="center"><label>Report</label></div>
            <div class="log-report-content panel panel-default">

            </div>
            <div class="buttons input-daterange input-group" id="datepickerReport">
                <input type="text" class="input-sm form-control" name="start"/>
                <span class="input-group-addon">to</span>
                <input type="text" class="input-sm form-control" name="end"/>
            </div>
            <button class="btn btn-default buttons">Searche</button>
        </div>


        <div class="clear"></div>
        <t:footer></t:footer>
    </div>


    <script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"/>"></script>
    <script src="<c:url value="/script/admin.js"/>"></script>
    </body>
    </html>
</fmt:bundle>

