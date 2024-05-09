<html>
<head>
    <title>Ticket List</title>
</head>
<body style="background-color: burlywood">
<a href="<c:url value='/logout'/>">Log-Out</a>
<h2>Ticket List</h2>
<a href="<c:url value='/ticket/create'/>">Create Ticket</a><br><br>
<c:choose>
<c:when test="${ticketDatabase.size() == 0}">
    <b style="color: blue">There are no tickets to view yet...</b>
</c:when>
<c:otherwise>
    <c:forEach var="ticket" items="${ticketDatabase}">
        Ticket: <c:out value="${ticket.id}"/><br><br>
        <c:out value="Customer Name:  "/>
        <a href="<c:url value='/ticket/view/${ticket.id}'/>">
            <c:out value="${ticket.customerName}"/>
        </a><br><br>
    </c:forEach>
</c:otherwise>
</c:choose>
</body>
</html>
