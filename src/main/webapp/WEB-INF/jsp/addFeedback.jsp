<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Feedback</title>
</head>
<body>
	<h3 style="color: red;">Feedback</h3>
	<form id="showFeedbacksForm" method="POST" action="/showFeedbacks">
		<input type="SUBMIT" value="Show Feedbacks" />
	</form>
	<div id="addFeedback">
		<form:form action="/addFeedback" method="post" modelAttribute="feedback">
			<p>
				<label width="120px">Enter your rating (1-10): </label>
				<form:input path="rating"/>
			</p>
			<p><label>Feedback</label></p>
			<form:textarea id="w3review" name="w3review" rows="10" cols="50" path="comments" />
			<p margin-top="20px">
				<input type="SUBMIT" value="Submit" />
			</p>
			<h2 style="color: #ff0000;">
				<a onclick="document.forms['logoutForm'].submit()">Logout</a>
			</h2>
		</form:form>
		<form id="logoutForm" method="POST" action="${contextPath}/logout" />
	</div>
</body>
</html>
