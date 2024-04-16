<html>
<head>
    <title>Ticket List</title>
</head>
<body>
<a href="<c:url value='/login'>
<c:param name='logout'/>
</c:url>">Log Out</a>

<h2>Ticket List</h2>
<a href="<c:url value='/ticket'>
        <c:param name='action' value='createTicket' />
    </c:url>">Create Ticket</a><br><br>
<c:choose>
    <c:when test="${ticketDatabase.size() == 0}">
        <p>There are no tickets to view yet...</p>
    </c:when>
    <c:otherwise>
        <c:forEach var="ticket" items="${ticketDatabase}">
            Ticket: <c:out value="${ticket.key}"/><br><br>
            <c:out value="Customer Name:  "/>
            <a href="<c:url value='/ticket' >
                    <c:param name='action' value='view' />
                    <c:param name='ticketId' value='${ticket.key}' />
                </c:url>" ><c:out value="${ticket.value.customerName}"/></a><br><br>
        </c:forEach>

    </c:otherwise>
</c:choose>

</body>
</html>