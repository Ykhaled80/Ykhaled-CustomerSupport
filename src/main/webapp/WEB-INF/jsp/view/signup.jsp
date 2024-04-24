<html>
<head>
    <title>Sign Up</title>
</head>
<body style="background-color: burlywood; display: flex; justify-content: center; align-items: center; height: 100vh;">
<div style="text-align: center; background-color: white; padding: 20px;">
    <h2 style="font-size: 24px;">Sign Up</h2><br>
    <form method="post" action="<c:url value='/authenticateSignup'/>">
        Username: <input type="text" name="username" style="font-size: 18px;"><br><br>
        Password: <input type="password" name="password" style="font-size: 18px;"><br><br>
        <input type="submit" value="Sign Up" style="font-size: 18px;">
    </form>
    <c:if test="${signupError}">
        <p style="color: red; font-size: 18px;">Username already exists. Please choose a different username.</p>
    </c:if>
</div>
</body>
</html>
