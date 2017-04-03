<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Simple</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/login" method="POST">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<label>User:</label>
		<input type="text" name="username"/>
		<br>
		<label>Password:</label>
		<input type="password" name="password"/>
		<br>
		<button type="submit" class="btn btn-default">Login</button>
	</form>
</body>
</html>
