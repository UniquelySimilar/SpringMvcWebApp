<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.css" />" >
<title>Home Page</title>
</head>
<body>
	<h3>Customer List</h3>
	<c:choose>
		<c:when test="${customerList.size() > 0}">
			<table>
				<thead>
					<tr>
						<th>Name</th>
						<th>Street</th>
						<th>City</th>
						<th>State</th>
						<th>Zip Code</th>
						<th>Home Phone</th>
						<th>Work Phone</th>
						<th>Email</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${customerList}" var="customer">
						<tr>
							<td>${ customer.name }</td>
							<td>${ customer.street }</td>
							<td>${ customer.city }</td>
							<td>${ customer.state }</td>
							<td>${ customer.zipcode }</td>
							<td>${ customer.homePhone }</td>
							<td>${ customer.workPhone }</td>
							<td>${ customer.email }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p><h4>No Customers Found</h4></p>
		</c:otherwise>
	</c:choose>
	
	<script src="<c:url value="/resources/js/jquery.js" />"  ></script>
	<script src="<c:url value="/resources/js/bootstrap.js" />"  ></script>
</body>
</html>