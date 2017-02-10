<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TICKET MANAGEMENT SYSTEM</title>
</head>
<body>

		<h3>Re-Assign a Ticket</h3>
		<br> <br>
		<form action="/home/reAssign" method="GET">
		<input type="text" name="emailId" placeholder="EmailId" required autofocus></input> <br> <br>
		<input type="password" name="password" placeholder="Password" required></input> <br> <br>
		<input type="text" name="ticketId" placeholder="Ticket ID" required></input> <br> <br>
		<input type="text" name="employeeId" placeholder="Employee ID" required></input> <br> <br>
		<br> ${ERROR} <br>
		<br> <br> <input type="submit" name="reassignticket" value="OK">
	</form>
</body>
</html>