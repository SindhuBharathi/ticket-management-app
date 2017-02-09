<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TICKET MANAGEMENT SYSTEM</title>
</head>
<body>
	<h2>New User Registration</h2>
	<form action="/home/register" method="GET">
		<input type="text" name="name" placeholder="Name of the user" required autofocus /> <br> <br> 
		<input type="text" name="emailId" placeholder="Email address" required /> <br> <br> 
		<input type="text" name="password" placeholder="Password" required /> <br> <br> 
		${ERROR}
		<button type="Submit" value="register">Register</button>
		<br> <br>
	</form>
</body>
</html>