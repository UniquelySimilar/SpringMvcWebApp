<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/bootstrap.css" />">
<title>Login</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-8 col-md-offset-4">
				<form:form action="${pageContext.request.contextPath}/login" cssClass="form-horizontal">
					<div class="form-group">
						<div class="row">
							<div class="col-md-2">
								<label>User:</label>
							</div>
							<div class="col-md-5">
								<form:input path="username" cssClass="form-control"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-md-2">
								<label>Password:</label>
							</div>
							<div class="col-md-5">
								<form:password path="password" cssClass="form-control"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-default">Login</button>
					</div>
				</form:form>
			</div>

		</div>
	</div>

	<script src="<c:url value="/resources/js/jquery.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
</body>
</html>
