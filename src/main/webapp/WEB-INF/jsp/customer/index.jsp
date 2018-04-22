<%@ include file="../header.jsp" %>
	
		<div class="row">
			<div class="col-md-12">
				<a class="btn btn-default" href="${pageContext.request.contextPath}/customer/create" role="button">Add Customer</a>
				<h3>Customer List</h3>
				<c:choose>
					<c:when test="${customerList.size() > 0}">
						<table id="customer-table" class="table table-striped">
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
					<button type="button" class="btn btn-primary delete-btn" data-customer-id="">Delete</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				</div>

			</div>
		</div>
	</div>
	<script>
	$(document).ready(function() {
		$('#customer-table').DataTable({
			columns: [
				null,
				{
					orderable: false
				},
				{
					orderable: false
				},
				{
					orderable: false
				},
				{
					orderable: false
				},
				{
					orderable: false
				},
				{
					orderable: false
				},
				{
					orderable: false
				},
				{
					orderable: false,
					searchable: false
				},
				{
					orderable: false,
					searchable: false
				}
			]
		});
		
		var deleteConfirmed = false;
		$('.form-delete').submit(function(event) {
			if (deleteConfirmed == false) {
				// Prevent form submission and display modal
				event.preventDefault();
				var customerId = $(this).find("input[name='customerId']").val();
				//console.log("Customer ID: " + customerId);
				var customerName = $(this).find("input[name='customerName']").val();
				//console.log("Customer Name: " + customerName);
				$('#delete-modal .modal-title').text('Delete ' + customerName + '?');
				$('#delete-modal .delete-btn').attr('data-customer-id', customerId);
				$('#delete-modal').modal('show');
			}
			else {
				// Reset flag and allow form submission
				deleteConfirmed = false;	// Reset
			}
		});

		$('#delete-modal .delete-btn').click(function() {
			var customerId = $(this).attr('data-customer-id');
			//console.log("Clicked modal delete button for Customer ID " + customerId);
			var formId = "#form-delete-" + customerId;
			//console.log("Form ID: " + formId);
			deleteConfirmed = true;
			$(formId).submit();
			$('#delete-modal').modal('hide');
		});
	});
	</script>
</body>
</html>