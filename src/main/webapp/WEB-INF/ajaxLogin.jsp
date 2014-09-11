<%@ page import="org.json.JSONObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    final JSONObject Json = (JSONObject) request.getAttribute ("json");
%>

<input type="hidden" name="Json" value="${json}">
<label>Login or password was introduced with error</label>