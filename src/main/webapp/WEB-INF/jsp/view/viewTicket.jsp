<html>
<head>
    <title>Ticket Details</title>
</head>
<body style="background-color: burlywood">
<a href="<c:url value='/logout'/>">Log Out</a>
<h2>Ticket Detail:</h2>
<h2>Ticket: <c:out value ="${ticketId}"/></h2>
<h3><c:out value="${ticket.customerName}" /></h3>
<P>Subject: <c:out value="${ticket.subject}"/></P>
<p>Description: <c:out value="${ticket.body}"/><p/>

<c:if test="${ticket.hasAttachment()}">
    <a href="<c:url value='/ticket/${ticketId}/attachment/${ticket.attachment.name}'/>">
    <c:out value="${ticket.attachment.name}"/></a><br><br>
</c:if>
<br>
<a href="<c:url value='/ticket/list'/>">Return to ticket list</a>
</body>
</html>
