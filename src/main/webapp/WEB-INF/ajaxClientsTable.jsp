<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table class="table table-hover" ID="clientsTable">
    <input type="hidden" id="clientsPageNumber" value="${clientsPaginatedList.getPageNumber()}"/>
    <tr>
        <th>ID</th>
        <th>Firs name</th>
        <th>Middle name</th>
        <th>Last name</th>
        <th>Address</th>
        <th>Telephone</th>
        <th>Mobilephone</th>
        <th>Create order</th>
    </tr>
    <c:forEach var="row" items="${clientsPaginatedList}">
        <tr>
            <td class="id">${row.getId()}</td>
            <td>${row.getFirstName()}</td>
            <td>${row.getMiddleName()}</td>
            <td>${row.getLastName()}</td>
            <td>${row.getAddress()}</td>
            <td>${row.getTelephone()}</td>
            <td>${row.getMobilephone()}</td>
            <td class=" createOrder">
                <button type="button" class="btn btn-primary" data-toggle="modal"
                        value="${row.getId()}"
                        data-target="#myModel">Order
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
