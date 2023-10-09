<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>
<html>
<head>
<title>Feedbacks</title>
<style>
	table {
		border-collapse: collapse;
		width: 100%;
	}

	td, th {
		border: 1px solid #dddddd;
		text-align: left;
		padding: 8px;
	}

	tr:nth-child(even) {
		background-color: #eeeeee;
	}
</style>
</head>
<body>
	<h3 style="color: red;">Feedbacks</h3>
	<table style="width:50%">
		<tr>
			<th>Username</th>
			<th>Comments</th>
			<th>Rating</th>
		</tr>
		<c:forEach var="listValue" items="${feedbacks}">
			<tr>
				<th>${listValue.username}</th>
				<th>${listValue.comments}</th>
				<th>${listValue.rating}</th>
			</tr>
		</c:forEach>
	</table>
	<form id="addFeedback" method="POST" action="/addFeedback">
		<input type="SUBMIT" value="Add Feedback" />
		<h2 style="color: #ff0000;">
			<a onclick="document.forms['logoutForm'].submit()">Logout</a>
		</h2>
	</form>
	<form id="logoutForm" method="POST" action="${contextPath}/logout" />
</body>
</html>