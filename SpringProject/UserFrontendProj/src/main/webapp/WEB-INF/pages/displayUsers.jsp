<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users Information</title>
</head>
<body>

	<c:if test="${message!=null}">
		<div class="message">
			${message}
		</div>
	</c:if>
	
	<c:if test="${users!=null}">
		<table>
			<tr>
				<th>Name</th>
				<th>DOB</th>
				<th>Phone</th>
				<th>Email</th>
				<th>Username</th>
				<th>More Info</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.name}</td>
					<td>${user.dob}</td>
					<td>${user.phone}</td>
					<td>${user.email}</td>
					<td>${user.credentials.username}</td>
					<c:url value="/users/${user.userId}" var="sUrl"></c:url>
					<td><form action="${sUrl}" method="GET">
							<input type="submit" value="More Info..." />
						</form> <c:url value="/users/delete/${user.userId}" var="dUrl"></c:url>
					</td>
					<td><form action="${dUrl}" method="GET">
							<input type="submit" value="Delete" />
						</form>
					</td>
					<c:url value="/users/update/${user.userId}" var="uUrl"></c:url>
					<td><form action="${uUrl}" method="GET">
							<input type="submit" value="Update" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>