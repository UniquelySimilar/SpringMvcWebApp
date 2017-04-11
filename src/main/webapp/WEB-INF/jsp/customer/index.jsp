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
<title>Customer List</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<a class="btn btn-default" href="${pageContext.request.contextPath}/customer/create" role="button">Add Customer</a>
				<h3>Customer List</h3>
				<c:choose>
					<c:when test="${customerList.size() > 0}">
						<table class="table table-striped">
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
									<th></th>
									<th></th>
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
										<td>
											<a class="btn btn-default" href="${pageContext.request.contextPath}/customer/${customer.id}/edit" role="button">Edit</a>
										</td>
										<td>
				<form:form id="form-delete-${customer.id}" cssClass="form-delete" method="DELETE"
					action="${pageContext.request.contextPath}/customer/${customer.id}">
					<input type="hidden" name="customerId" value="${ customer.id }"/>
					<input type="hidden" name="customerName" value="${ customer.name }"/>
					<button type="submit" class="btn btn-default">Delete</button>
				</form:form>
											<!-- button type="button" class="btn btn-default"
												data-toggle="modal" data-target="#delete-modal"
												data-id="${ customer.id }" data-name="${ customer.name }">Delete</button-->
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:otherwise>
						<p>
						<h4>No Customers Found</h4>
						</p>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>

	<div id="delete-modal" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary modal-delete-btn" data-del-id="">Delete</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				</div>

			</div>
		</div>
	</div>

	<script src="<c:url value="/resources/js/jquery.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
	<script>
	$(document).ready(function() {
		// TODO: Add method to handle delete form submit button and display modal
		// Add second method to submit delete form
		$(".form-delete").submit(function(event) {
			var customerId = $(this).find("input[name='customerId']").val();
			console.log("Customer ID: " + customerId);
			event.preventDefault();
		});
		
		$('#delete-modal').on('show.bs.modal', function (event) {
			var button = $(event.relatedTarget);	// Button that triggered the modal
			var id = button.data('id');
			var name = button.data('name');
			var modal = $(this);
			modal.find('.modal-title').text('Delete ' + name + '?');
			modal.find('.modal-delete-btn').attr('data-del-id', id);
		});

		$(".modal-delete-btn").click(function() {
			var delId = $(this).attr('data-del-id');
			console.log("Clicked modal delete button for id " + delId);
		});
	});
	</script>
</body>
</html>