<html>
<head>
    <title>Ticket Login</title>
</head>
<body>
    <h2>Login</h2><br>
    <h3>Please Log In to access the ticket page.</h3><br>
    <c:if test="${loginFailed == true}">
      <b><c:out value ="The username or password you entered are incorrect, Please try again."></c:out></b><br><br>
    </c:if>
    <form method="post" action="<c:url value='/login'/>">
        Username: <input type="text" name="username"><br><br>
        Password: <input type="password" name="password"><br><br>
        <input type="submit" value="Log In">
    </form>
</body>
</html>
