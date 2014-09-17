<%@tag description="authentication template" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel='stylesheet' href='<c:url value="/style/authentication.css"/>'>

<div class="authentication">
    <c:if test="${not empty user}">
        <div id="autorized">
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
        <div id="unAutorized">
            <div class="center"><label for="inputLogin">Login</label></div>
            <input type="text" name="authenticationLogin" class="form-control" id="inputLogin" value="Elena">

            <div class="center"><label for="inputPassword">Password</label></div>
            <input type="text" name="authenticationPassword" class="form-control" id="inputPassword" value="EleEle">

            <input type="button" class="loginbtn btn btn-primary" value="Login" id="goLogin">
            <button class="registrationbtn btn btn-primary" data-toggle="modal" data-target="#RegistrationForm">
                Registration
            </button>
            <p class="name_surname errorRegistationLabel" id="errorLogin"></p>
        </div>
    </c:if>
</div>

<script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
<script src="<c:url value="/script/authentication.js"/>"></script>