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
                <div id="unAutorized" class="border panel panel-default">
                    <label class="alignHorizontalCenter" for="inputLogin">Login</label>
                    <input type="text" name="authenticationLogin" class="form-control" id="inputLogin" value="Elena">
                    <label class="alignHorizontalCenter" for="inputPassword">Password</label>
                    <input type="text" name="authenticationPassword" class="form-control" id="inputPassword"
                           value="EleEle">
                    <input type="button" class="loginbtn btn btn-primary" value="Login" id="goLogin">
                    <button class="registrationbtn btn btn-primary" data-toggle="modal" data-target="#RegistrationForm">
                        Registration
                    </button>
                    <p class="name_surname errorRegistationLabel" id="errorLogin"></p>
                </div>
            </c:if>

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

        <div class="contactInformation panel panel-default" style="overflow-y: scroll">
            <c:forEach var="contact" items="${contacts}">
                <label>${contact.owner}: ${contact.telephone}</label>
            </c:forEach>
        </div>
    </div>

    <div>
        <form action="${pageContext.request.contextPath}/controller">
            <input type="submit" value="Go to DataBase">
            <ul class="pager">
                <li class="previous"><a href="#">&larr; Older</a></li>
                <li class="next"><a href="#">Newer &rarr;</a></li>
            </ul>
            <input type="hidden" name="actionName" value="DeleteFromDataBaseAction"> //
        </form>
    </div>

    <div class="modal fade" id="RegistrationForm" tabindex="-1" role="dialog" aria-labelledby="RegistrationFormLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span
                            aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="RegistrationFormLabel">Registration form</h4>
                </div>
                <div class="registration">
                    <div class="form-group" id="loginDiv">
                        <label for="Login" class="name_surname">Login</label>
                        <input type="text" name="login" value="Ivanov99" class="form-control" id="Login">

                        <p for="Login" class="name_surname errorRegistationLabel" id="loginInput"></p>
                    </div>
                    <div class="form-group" id="passwordDiv">
                        <label for="Password" class="name_surname">Password</label>
                        <input type="text" name="password" value="Ivanov9" class="form-control" id="Password">

                        <p for="Login" class="name_surname errorRegistationLabel" id="passwordInput"></p>
                    </div>
                    <div class="form-group" id="firstnameDiv">
                        <label for="Firstname" class="name_surname">Firsname</label>
                        <input type="text" name="firstname" value="Ivan" class="form-control" id="Firstname">

                        <p for="Login" class="name_surname errorRegistationLabel" id="firstnameInput"></p>
                    </div>
                    <div class="form-group" id="lastnameDiv">
                        <label for="Lastname" class="name_surname">Lastname</label>
                        <input type="text" name="lastname" value="Ivanov" class="form-control" id="Lastname">

                        <p for="Login" class="name_surname errorRegistationLabel" id="lastnameInput"></p>
                    </div>
                    <div class="form-group" id="middlenameDiv">
                        <label for="Middlename" class="name_surname">Middlename</label>
                        <input type="text" name="middlename" value="Ivanovich" class="form-control" id="Middlename">

                        <p for="Login" class="name_surname errorRegistationLabel" id="middlenameInput"></p>
                    </div>
                    <div class="form-group" id="addressDiv">
                        <label for="Address" class="name_surname">Address</label>
                        <input type="text" name="address" value="Ivanova-32" class="form-control" id="Address">

                        <p for="Login" class="name_surname errorRegistationLabel" id="addressInput"></p>
                    </div>
                    <div class="form-group" id="telephoneDiv">
                        <label for="Telephone" class="name_surname">Telephone</label>
                        <input type="text" name="telephone" value="87212965896" class="form-control" id="Telephone">

                        <p for="Login" class="name_surname errorRegistationLabel" id="telephoneInput"></p>
                    </div>
                    <div class="form-group" id="mobilephoneDiv">
                        <label for="Mobilephone" class="name_surname">Mobilephone</label>
                        <input type="text" name="mobilephone" value="87007778958" class="form-control" id="Mobilephone">

                        <p for="Login" class="name_surname errorRegistationLabel" id="mobilephoneInput"></p>
                    </div>

                    <p class="name_surname errorRegistationLabel" id="registrationSuccessful"></p>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" data-dismiss="modal">Close registration form</button>
                    <button class="btn btn-primary" id="goRegistration">Registration</button>
                </div>
            </div>
        </div>
    </div>

    <a href="ajax_test">Go to Ajax Test</a>

    <script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
    <script src="<c:url value="/webjars/bootstrap/3.2.0/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/script/welcome.js"/>"></script>
    </body>
    </html>
</fmt:bundle>
