<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Information</title>
</head>
<body>
	<c:if test="${message!=null}">
		<div class="message">
			${message}
		</div>
	</c:if>
	<c:if test="${user!=null}">
		<table>
			<tr>
				<td>Name</td>
				<td>${user.name}</td>
			</tr>
			<tr>
				<td>DOB</td>
				<td>${user.dob}</td>
			</tr>
			<tr>
				<td>Phone</td>
				<td>${user.phone}</td>
			</tr>
			<tr>
				<td>Email</td>
				<td>${user.email}</td>
			</tr>
			<tr>
				<td>Username</td>
				<td>${user.credentials.username}</td>
			</tr>
			<tr>
				<td>Authorities</td>
				<td>${authorities}</td>
			</tr>
		</table>
	</c:if>
</body>
</html>