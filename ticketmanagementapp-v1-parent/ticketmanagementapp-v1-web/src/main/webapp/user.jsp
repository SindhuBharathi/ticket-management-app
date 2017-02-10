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
	<form action="/createTicket.jsp" method="get">
		<button type="submit"> Create </button> <br> <br> <br>
	</form>
	<form action="/updateTicket.jsp" method="get">
		<button type="submit"> Update </button> <br> <br> <br>
	</form>
	<form action="/closeTicket.jsp" method="get">
		<button type="submit"> Close </button> <br> <br> <br>
	</form>
	<form action="/home/userViewTickets" method="get">
		<button type="submit"> View </button> <br> <br> <br>
	</form>
	<form action="/home/userLogout" method="get">
		<button type="submit"> Logout </button> <br> <br> <br>    
 	</form>
</body>
</html>