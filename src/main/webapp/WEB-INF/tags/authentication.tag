<%@tag description="authentication template" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="authentication" fragment="true" %>

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
        <div id="unAutorized" class="border panel panel-default">
            <form action="${pageContext.request.contextPath}/do/login" method="post">
                <label class="alignHorizontalCenter" for="inputLogin">Login</label>
                <input type="text" name="authenticationLogin" class="form-control" id="inputLogin" value="Elena">
                <label class="alignHorizontalCenter" for="inputPassword" >Password</label>
                <input type="text" name="authenticationPassword" class="form-control" id="inputPassword" value="EleEle">
                <input type="submit" class="loginbtn btn btn-primary" value="Login">
                <button type="button" class="registrationbtn btn btn-primary" data-toggle="modal" data-target="RegistrationForm">
                    Registration
                </button>
            </form>
            <div id="errorRegistration">
                <label>fsdfsdfsdf</label>
            </div>
            <button type="button" class="registrationbtn btn btn-primary" id="registration">
                Registration
            </button>
            <button type="button" class="registrationbtn btn btn-primary" id="Gotoclientpage">
                Go to client page
            </button>
        </div>
    </c:if>
</div>