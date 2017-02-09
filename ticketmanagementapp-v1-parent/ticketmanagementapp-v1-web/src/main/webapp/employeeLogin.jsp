<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TICKET MANAGEMENT SYSTEM</title>
</head>
<body>
	<h2>Employee Login</h2>
	<form action="/home/employeeLogin" method="GET">
		<input type="text" name="emailId" placeholder="Email address" required
			autofocus /> <br> <br> <input type="password"
			name="password" placeholder="Password" required autofocus /> <br>
		<br> ${ERROR} <br>
		<button type="Submit" value="login">Sign In</button>
		<br> <br>
	</form>
</body>
</html>