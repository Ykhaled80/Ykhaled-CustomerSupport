<html>
<head>
    <title>Ticket List</title>
</head>
<body>
<a href="<c:url value='/logout'/>">Log Out</a>
<h2>Ticket List</h2>
<a href="<c:url value='/ticket/create'/>">Create Ticket</a><br><br>
<c:choose>
    <c:when test="${ticketDatabase.size() == 0}">
        <p>There are no tickets to view yet...</p>
    </c:when>
    <c:otherwise>
        <c:forEach var="ticket" items="${ticketDatabase}">
            Ticket: <c:out value="${ticket.key}"/><br><br>
            <c:out value="Customer Name:  "/>
            <a href="<c:url value='/ticket/view/${ticket.key}'/>">
                <c:out value="${ticket.value.customerName}"/></a><br><br>
        </c:forEach>
    </c:otherwise>
</c:choose>

</body>
</html>