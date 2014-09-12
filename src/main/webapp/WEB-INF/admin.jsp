<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:bundle basename="i18n.messages">
    <html>

    <head>
        <title>Welcome</title>
        <link rel='stylesheet' href='<c:url value="/webjars/bootstrap/3.2.0/css/bootstrap.min.css"/>'>
        <link rel='stylesheet' href='<c:url value="/style/welcome.css"/>'>
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

        <form action="<c:url value="/do/database"/>">
            <input type="submit" class="logoutbtn btn btn-primary" value="Go to database">
        </form>




    </div>

    <script src="<c:url value="/script/client.js"/>"></script>
    <script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"/>"></script>
    </body>
    </html>
</fmt:bundle>

