<html>
<head>
    <title>Ticket Details</title>
</head>
<body>
<a href="<c:url value='/login'>
<c:param name='logout'/>
</c:url>">Log Out</a>
<h2>Ticket: <c:out value ="${ticketId}"/></h2>
<h3><c:out value="${ticket.customerName}" /></h3>
<P>Subject: <c:out value="${ticket.subject}"/></P>
<p>Description: <c:out value="${ticket.body}"/><p/>

<c:if test="${ticket.hasAttachment()}">
    <a href="<c:url value='/ticket'>
        <c:param name='action' value='download'/>
        <c:param name='ticketId' value='${ticketId}'/>
        <c:param name='attachment' value='${ticket.attachment.name}'/>
    </c:url>"><c:out value="${ticket.attachment.name}"/></a><br><br>
</c:if>
<br>
<a href="ticket">Return to ticket list</a>
</body>
</html>
