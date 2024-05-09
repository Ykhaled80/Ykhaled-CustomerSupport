<html>
<head>
    <title>Ticket Login</title>
</head>
<body style="background-color: burlywood; display: flex; justify-content: center; align-items: center; height: 100vh;">
<div style="text-align: center; background-color: white; padding: 20px;">
    <h2 style="font-size: 30px;">Login</h2><br>
    <h3 style="font-size: 26px;">Please Log In to access the ticket page.</h3><br>
    <c:if test="${param.signupSuccess == 'true'}">
        <b style="color: green; font-size: 22px;"><c:out value="Signup Successful! You can now log in now."></c:out></b><br><br>
    </c:if>
    <c:if test="${loginFailed == true}">
        <b style="color: red; font-size: 22px;"><c:out value="The username or password you entered are incorrect, Please try again."></c:out></b><br><br>
    </c:if>
    <a href="<c:url value='/signup'/>" style="font-size: 18px;">Sign Up</a><br><br>
    <form:form method="POST" action="login" modelAttribute="loginForm">
        <form:label path="username">Username:&nbsp;</form:label>
        <form:input path="username"/><br><br>
        <form:label path="password">Password:&nbsp;</form:label>
        <form:password path="password"/><br><br>
        <input type="submit" value="Log In">
    </form:form>
</div>
</body>
</html>
