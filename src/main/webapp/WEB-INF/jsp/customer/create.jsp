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
<title>Add Customer</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<!-- TODO: implement form -->
			<div class="col-md-12">
				<form:form modelAttribute="customer" action="/springmvcwebapp/dispatch/customer/store" >
					<form:input path="name"/>
					<input type="submit" value="Save"/>
				</form:form>
			</div>

		</div>
	</div>

	<script src="<c:url value="/resources/js/jquery.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
</body>
</html>
