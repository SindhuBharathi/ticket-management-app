<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TICKET MANAGEMENT SYSTEM</title>
</head>
<body>
	<h2>Welcome</h2>
	<form action="/assignTicket.jsp" method="get">
		<button type="submit"> Assign a ticket </button> <br> <br> <br>
	</form>
	<form action="/reAssignTicket.jsp" method="get">
		<button type="submit"> Re-assign a ticket </button> <br> <br> <br>
	</form>
	<form action="/replyTicket.jsp" method="get">
		<button type="submit"> Reply a ticket </button> <br> <br> <br>
	</form>
	<form action="/deleteTicket.jsp" method="get">
		<button type="submit"> Delete a ticket </button> <br> <br> <br>
	</form>
	<form action="/home/employeeViewTickets" method="get">
		<button type="submit"> View </button> <br> <br> <br>
		<br> ${ERROR} <br>
	</form>
	<form action="/home/employeeLogout" method="get">
		<button type="submit"> Logout </button> <br> <br> <br>
	</form>
</body>
</html>