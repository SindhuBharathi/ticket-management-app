<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%
	ResultSet resultset = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TICKET MANAGEMENT SYSTEM</title>
</head>
<body>
	<%
		try {
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/ticket_management_v3_db?user=root&password=asiba");
			Statement statement = connection.createStatement();
			resultset = statement.executeQuery("SELECT NAME FROM DEPARTMENTS");
	%>
	<h3>Create your Ticket</h3>
	<br>
	<br>
	<form action="/home/create" method="GET">
		<input type="text" name="emailId" placeholder="EmailId" required
			autofocus></input> <br> <br> <input type="password"
			name="password" placeholder="Password" required></input> <br> <br>
		<input type="text" name="subject" placeholder="Subject of the ticket"
			required /> <br> <br> <input type="text"
			name="description" placeholder="Description" required /> <br> <br>
		<select name="department">
			<option selected>Choose the Department</option>
			<%
				while (resultset.next()) {
			%>
			<option>
				<%=resultset.getString(1)%>
			</option>
			<%
				}
			%>
		</select>
		<ol type="none">
			<br> Priority
			<%
				resultset = statement.executeQuery("select NAME from PRIORITIES");
			%>
			<%
				while (resultset.next()) {
			%>
			<li><input type="radio" name="priority"
				value=<%=resultset.getString(1)%>> <%=resultset.getString(1)%>
			</li>
			<%
				}
			%>
		</ol>
		<br> ${ERROR} <br>
		<br> <br> <input type="submit" name="createticket"
			value="OK">
	</FORM>
	<%
		} catch (Exception e) {
			out.println("wrong entry" + e);
		}
	%>
</body>
</html>
