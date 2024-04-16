<html>
<head>
    <title>Sign Up</title>
</head>
<body>
<h2>Sign Up</h2>
<form method="post" action="<c:url value='/signup'/>">
    <input type="hidden" name="action" value="signup">
    Username: <input type="text" name="username"><br>
    Password: <input type="password" name="password"><br>
    <input type="submit" value="Sign Up">
</form>
</body>
</html>
