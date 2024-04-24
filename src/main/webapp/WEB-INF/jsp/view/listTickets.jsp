<html>
<head>
    <title>Ticket List</title>
</head>
<body style="background-color: burlywood">
<a href="<c:url value='/logout'/>">Log Out</a>
<h2>Ticket List</h2>
<a href="<c:url value='/ticket/create'/>">Create Ticket</a><br><br>

<c:if test="${ticketDatabase == null or ticketDatabase.size() == 0}">
    <b style="color: blue">There are no tickets to view yet...</b>
</c:if>
<c:if test="${ticketDatabase != null and ticketDatabase.size() > 0}">
    <c:forEach var="ticket" items="${ticketDatabase}">
        Ticket: <c:out value="${ticket.key}"/><br><br>
        <c:out value="Customer Name:  "/>
        <a href="<c:url value='/ticket/view/${ticket.key}'/>">
            <c:out value="${ticket.value.customerName}"/></a><br><br>
    </c:forEach>
</c:if>

</body>
</html>
