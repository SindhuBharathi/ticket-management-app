<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TICKET MANAGEMENT SYSTEM</title>
</head>
<body>

		<h3>Close your Ticket</h3>
		<br> <br>
		<form action="/home/close" method="GET">
		<input type="text" name="emailId" placeholder="EmailId" required autofocus></input> <br> <br>
		<input type="text" name="password" placeholder="Password" required></input> <br> <br>
		<input type="text" name="ticketId" placeholder="Ticket ID" required></input> <br> <br>
		<br> ${ERROR} <br>
		<br> <br> <input type="submit" name="closeticket" value="OK">
	</form>
</body>
</html>