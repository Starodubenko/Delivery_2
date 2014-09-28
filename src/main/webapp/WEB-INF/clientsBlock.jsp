<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="clients-block">
    <ul id="changee" class="pagination">
        <li id="cBack"><a href="#page">&laquo;</a></li>

        <c:forEach varStatus="status" items="${clientsPaginationlist}" var="pl">
            <li value="${pl.intValue()}" name="page${pl.intValue()}"
                class="cNumbered "><a href="#page${pl.intValue()}"
                                      class="page">${pl.intValue()}</a>
            </li>
        </c:forEach>

        <li id="cNext"><a href="#page">&raquo;</a></li>
    </ul>

    <div class="form-group rows-count floatRight">
        <label class="labelCount" for="clientsrows">Rows count</label>

        <form action="${pageContext.request.contextPath}/do/dispatcher">
            <div class="input-group">
                <input type="text" name="clientsrows" id="clientsrows" value="${clientsRowsCount}"
                       class="form-control textCount">
                        <span class="input-group-btn">
                            <button class="btn btn-default floatRight" type="submit">aply</button>
                        </span>
            </div>
        </form>
    </div>

    <div class="orderListHeight tab-pane" style="overflow-y: scroll">
        <table class="table table-hover" ID="clientsTable">
            <input type="hidden" id="clientsPageNumber" value="${clientsPageNumber}"/>
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
            <c:forEach var="row" items="${clientsList}">
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
    </div>
</div>
