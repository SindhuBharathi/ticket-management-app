<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TICKET MANAGEMENT SYSTEM</title>
</head>
<body>
	<table>
	<thead>
		<tr> 
			<th> Ticket ID </th>
			<th> Subject </th>
			<th> Description </th>
			<th> Department </th>
			<th> Priority </th>
			<th> Created DateTime </th>
			<th> Resolved DateTime </th>
			<th> Status </th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="c" items="${list}" varStatus="i">
			<tr>
				<td>${c.id}</td>
				<td>${c.subject}</td>
				<td>${c.description}</td>
				<td>${c.departmentId.getId()}</td>
				<td>${c.priorityId.getId()}</td>
				<td>${c.createdDateTime}</td>
				<td>${c.resolvedDateTime}</td>
				<td>${c.status}</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	Click here to <a href="../user.jsp"> User </a> page. <br>
Click here to <a href="../userLogin.jsp"> User Login </a> page. <br>
Click here to <a href="../index.jsp"> General login </a> page. <br>
</body>
</html>	