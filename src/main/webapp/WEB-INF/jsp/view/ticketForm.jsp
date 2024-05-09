<html>
<head>
    <title>Create a new ticket</title>
</head>
<body style="background-color: burlywood; display: flex; justify-content: center; align-items: center; height: 100vh;">
<div style="text-align: center; background-color: white; padding: 20px;">
    <a href="<c:url value='/logout'/>" style="font-size: 18px;">Log Out</a>
    <h2 style="font-size: 24px;">Create a Ticket</h2>

    <form:form method="post" action="create" modelAttribute="ticket" enctype="multipart/form-data">
        <form:label path="customerName" style="font-size: 18px;">Customer Name:</form:label><br>
        <form:input path="customerName" style="font-size: 18px;"/><br><br>
        <form:label path="subject" style="font-size: 18px;">Subject:</form:label><br>
        <form:input path="subject" style="font-size: 18px;"/><br><br>
        <form:label path="body" style="font-size: 18px;">Description:</form:label><br>
        <form:textarea path="body" name="body" rows="25" cols="100" style="font-size: 18px;"/><br><br>
        <b style="font-size: 18px;">File</b>
        <form:input path="attachment" type= "file" style="font-size: 18px;"/><br><br>
        <input type="submit" value="Submit" style="font-size: 18px;">
    </form:form>
</div>
</body>
</html>
