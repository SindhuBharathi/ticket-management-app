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
<!-- 		<input type="text" name="emailId" placeholder="EmailId" required autofocus></input>
		<input type="text" name="password" placeholder="Password" required></input> 
 -->		<button type="submit"> Assign a ticket </button> <br> <br> <br>
	</form>
	<form action="/reAssignTicket.jsp" method="get">
<!-- 		<input type="text" name="emailId" placeholder="EmailId" required autofocus></input>
		<input type="text" name="password" placeholder="Password" required></input>
 -->		<button type="submit"> Re-assign a ticket </button> <br> <br> <br>
	</form>
	<form action="/replyTicket.jsp" method="get">
<!-- 		<input type="text" name="emailId" placeholder="EmailId" required autofocus></input>
		<input type="text" name="password" placeholder="Password" required></input>
 -->		<button type="submit"> Reply a ticket </button> <br> <br> <br>
	</form>
	<form action="/deleteTicket.jsp" method="get">
<!-- 		<input type="text" name="emailId" placeholder="EmailId" required autofocus></input>
		<input type="text" name="password" placeholder="Password" required></input> 
 -->		<button type="submit"> Delete a ticket </button> <br> <br> <br>
	</form>
	<form action="/home/employeeViewTickets" method="get">
		<input type="text" name="emailId" placeholder="EmailId" required autofocus></input>
		<input type="password" name="password" placeholder="Password" required></input>
		<button type="submit"> View </button> <br> <br> <br>
		<br> ${ERROR} <br>
	</form>
</body>
</html>