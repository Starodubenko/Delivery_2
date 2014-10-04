<%@tag description="StatusBar template" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="target" fragment="true" %>

<div class="form-group rows-count floatRight">
    <label class="labelCount" for="clientsrows">Rows count</label>

    <form action="${pageContext.request.contextPath}/do/dispatcher">
        <div class="input-group rows-count-height">
            <input type="text" name="clientsrows" id="clientsrows" value="${clientsPaginatedList.getRowsPerPage()}"
                   class="form-control textCount">
                        <span class="input-group-btn">
                            <button class="btn btn-default rows-count-height" type="submit">apply</button>
                        </span>
        </div>
    </form>
</div>