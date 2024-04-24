<html>
<head>
    <title>Create a new ticket</title>
</head>
<body style="background-color: burlywood; display: flex; justify-content: center; align-items: center; height: 100vh;">
<div style="text-align: center; background-color: white; padding: 20px;">
    <a href="<c:url value='/logout'/>" style="font-size: 18px;">Log Out</a>
    <h2 style="font-size: 24px;">Create a Ticket</h2>

    <form method="post" action="create" enctype="multipart/form-data">
        <label for="customerName" style="font-size: 18px;">Customer Name:</label><br>
        <input type="text" id="customerName" name="customerName" style="font-size: 18px;"><br><br>
        <label for="subject" style="font-size: 18px;">Subject:</label><br>
        <input type="text" id="subject" name="subject" style="font-size: 18px;"><br><br>
        <label for="body" style="font-size: 18px;">Description:</label><br>
        <textarea id="body" name="body" rows="25" cols="100" style="font-size: 18px;"></textarea><br><br>
        <b style="font-size: 18px;">File</b>
        <input type="file" id="attachment" name="attachment" style="font-size: 18px;"><br><br>
        <input type="submit" value="Submit" style="font-size: 18px;">
    </form>

</div>
</body>
</html>
