<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
    <link rel='stylesheet' href='<c:url value="/style/ajaxTest.css"/>'>
</head>
<body>

<div class="main" id="main">
    <div class="picture"></div>
    <div class="picture"></div>
    <div class="picture"></div>
    <div class="picture"></div>
    <div class="picture"></div>
    <div class="picture"></div>
    <div class="picture"></div>
    <div class="picture"></div>
    <div class="picture"></div>
    <div class="picture"></div>
    <div class="picture"></div>
    <div class="picture"></div>
    <div class="picture"></div>
    <div class="picture"></div>
    <div class="picture"></div>
    <div class="picture"></div>
    <div class="text">
        <div><input type="text" name="text1"/></div>
        <form>
            <div><input type="text" name="text2"/></div>
            <div><button type="button" id="go">go!</button></div>
        </form>
    </div>

    <table id="table1">
        <tr><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td></tr>
        <tr><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td></tr>
        <tr><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td></tr>
        <tr><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td></tr>
        <tr><td>1</td><td>1</td><td>1</td><td>1</td><td>1</td></tr>
    </table>
</div>

    <script src="<c:url value="/webjars/jquery/1.11.1/jquery.js"/>"></script>
    <%--<script src="<c:url value="/script/ajaxTest.js"/>"></script>--%>
<script>
    $(document).ready(function(){
        $('#go').click(function () {
            $('#table1').load('showAjaxTestAction');
        });
    });
</script>
</body>
</html>
