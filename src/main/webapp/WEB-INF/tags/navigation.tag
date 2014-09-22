<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel='stylesheet' href='<c:url value="/style/navigation.css"/>'>

<div class="background-navigation">
    <nav class="center">
        <ul class="fancyNav">
            <li id="home"><a href="welcome" class="homeIcon">Home</a></li>
            <li id="news"><a href="#news">News</a></li>
            <li id="about"><a href="#about">About us</a></li>
            <li id="services"><a href="#services">Services</a></li>
            <li id="createOrder"><a href="createOrder">Create order</a></li>
            <li id="personalCabinet"><a href="personal-cabinet">Personal cabinet</a></li>
        </ul>

        <select class="form-control language" id="switchLanguage">
            <option>en</option>
            <option>ru</option>
        </select>
    </nav>
</div>
