<html>
<head>
    <title>Ticket Session Admin View</title>
</head>
<body>
<a href="<c:url value='/logout'/>">Log Out</a>
<h2>Sessions</h2>
The total number of active sessions: <c:out value="${numSessions}"/>
<ul>
    <c:forEach items="${sessionList}" var="s">
        <li><c:out value="${s.id} - ${s.getAttribute('username')} - last active ${now - (s.getLastAccessedTime())/1000} seconds ago."/>
        </li>
    </c:forEach>
</ul>
</body>
</html>
