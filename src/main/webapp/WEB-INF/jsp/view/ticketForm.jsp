
<html>
<head>
    <title>Create a new ticket</title>
</head>
<body>
<a href="<c:url value='/login'>
<c:param name='logout'/>
</c:url>">Log Out</a>
    <h2>Create a Ticket</h2>

    <form:form method="post" action="create" modelAttribute="ticket" enctype="multipart/form-data">
        <form:label path="customeName">Customer Name:</form:label><br>
        <form:input path="customerName"/><br><br>
        <form:label path="subject">Subject:</form:label><br>
        <form:input path="subject"/><br><br>
        <form:label path="body">Description:</form:label><br>
        <form:textarea path="body" rows="25" cols="100"/><br><br>
        <b>File</b>
        <input type="file" path="attachment"><br><br>
        <input type="submit" value="Submit">

    </form:form>

</body>
</html>
