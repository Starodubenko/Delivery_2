<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table class="table table-hover" ID="clientsTable">
    <input type="hidden" id="clientsPageNumber" value="${clientsPageNumber}"/>
    <tr>
        <th></th>
        <th>ID</th>
        <th>Firs name</th>
        <th>Middle name</th>
        <th>Last name</th>
        <th>Address</th>
        <th>Telephone</th>
        <th>Mobilephone</th>
        <th>Create order</th>
    </tr>
    <c:forEach var="row" items="${clientsList}">
        <tr>
            <td>
                <div class="checkbox">
                    <label>
                        <input type="radio" name="IdOrder" value="${row.getId()}">
                    </label>
                </div>
            </td>
            <td>${row.getId()}</td>
            <td>${row.getFirstName()}</td>
            <td>${row.getMiddleName()}</td>
            <td>${row.getLastName()}</td>
            <td>${row.getAddress()}</td>
            <td>${row.getTelephone()}</td>
            <td>${row.getMobilephone()}</td>
            <td>
                <form></form>
                <button name="">
                    Order
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
