<%@ include file="../header.jsp" %>

		<div class="row">
			<div class="col-md-8 col-md-offset-4">
				<form:form modelAttribute="customer" action="${pageContext.request.contextPath}/customer/store" cssClass="form-horizontal">
					<div class="form-group">
						<div class="row">
							<div class="col-md-2">
								<label>Name:</label>
							</div>
							<div class="col-md-5">
								<form:input path="name" cssClass="form-control"/>
							</div>
							<div class="col-md-5">
								<form:errors path="name" cssStyle="color: red;"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-md-2">
								<label>Street:</label>
							</div>
							<div class="col-md-5">
								<form:input path="street" cssClass="form-control"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-md-2">
								<label>City:</label>
							</div>
							<div class="col-md-5">
								<form:input path="city" cssClass="form-control"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-md-2">
								<label>State:</label>
							</div>
							<div class="col-md-5">
								<form:select path="state" items="${stateList}" cssClass="form-control"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-md-2">
								<label>Zip Code:</label>
							</div>
							<div class="col-md-5">
								<form:input path="zipcode" cssClass="form-control"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-md-2">
								<label>Home Phone:</label>
							</div>
							<div class="col-md-5">
								<form:input path="homePhone" cssClass="form-control"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-md-2">
								<label>Work Phone:</label>
							</div>
							<div class="col-md-5">
								<form:input path="workPhone" cssClass="form-control"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-md-2">
								<label>Email:</label>
							</div>
							<div class="col-md-5">
								<form:input path="email" cssClass="form-control"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-default">Save</button>
						<a class="btn btn-default" href="${pageContext.request.contextPath}/customer" role="button">Cancel</a>
					</div>
				</form:form>
			</div>

		</div>
	</div>

	<script src="<c:url value="/resources/js/jquery.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
</body>
</html>
