<html>
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