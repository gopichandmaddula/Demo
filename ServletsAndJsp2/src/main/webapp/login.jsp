<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="coretag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form action="/ServletsAndJsp2/login" method="POST">
		<label>Username : </label><input type="text" name="userid" />
		<br />
		<label>Password : </label><input type="password" name="password" />
		<br />
		<input type="submit" value="Submit" />
	</form>

</body>
</html>