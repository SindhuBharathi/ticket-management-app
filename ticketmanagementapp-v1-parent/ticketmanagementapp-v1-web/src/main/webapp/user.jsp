<!DOCTYPE html>
<html>
<head>
<title>TICKET MANAGEMENT SYSTEM</title>
</head>
<body>
	<h2>Welcome</h2>
		<form action="/createTicket.jsp" method="get">
<!-- 		<input type="text" name="emailId" placeholder="EmailId" required autofocus></input>
		<input type="text" name="password" placeholder="Password" required></input> 
 -->		<button type="submit"> Create </button> <br> <br> <br>
	</form>
	<form action="/updateTicket.jsp" method="get">
<!-- 		<input type="text" name="emailId" placeholder="EmailId" required autofocus></input>
		<input type="text" name="password" placeholder="Password" required></input>
 -->		<button type="submit"> Update </button> <br> <br> <br>
	</form>
	<form action="/closeTicket.jsp" method="get">
<!-- 		<input type="text" name="emailId" placeholder="EmailId" required autofocus></input>
		<input type="text" name="password" placeholder="Password" required></input>
 -->		<button type="submit"> Close </button> <br> <br> <br>
	</form>
	<form action="/home/userViewTickets" method="get">
		<input type="text" name="emailId" placeholder="EmailId" required autofocus></input>
		<input type="text" name="password" placeholder="Password" required></input>
		<button type="submit"> View </button> <br> <br> <br>
		<br> ${ERROR} <br>
	</form>
</body>
</html>