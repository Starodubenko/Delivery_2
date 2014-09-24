<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<t:navigation></t:navigation>
<p>Status code ${statusCode}</p>

<p>Message ${message}</p>
<t:footer></t:footer>
</body>
</html>
