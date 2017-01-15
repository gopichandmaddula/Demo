<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="coretag"%>

<style>
.message {
	border: 1px solid;
	border-color: blue;
	border-radius: 5px;
	width: 30%;
	height: 20px;
	background-color: olive;
	margin: 1.5em;
	font-size: 1em;
	font-weight: bold;
}
</style>

<div class="panel">
	<c:if test="${sessionScope.error!=null}">
		<div class="error">${sessionScope.error}</div>
	</c:if>

	<c:url value="/ViewAccount?vat=single" var="viewSingleUrl"></c:url>
	<form action="${viewSingleUrl}" method="POST">
		<table>
			<tr>
				<td colspan="2">Get Account Information
			</tr>
			<tr>
				<td>Enter User Id :</td>
				<td><input type="text" name="userId" /></td>
			</tr>
			<tr align="right">
				<td colspan="2"><input type="submit" value="Find User" /></td>
			</tr>
		</table>
	</form>

	<c:if test="${sessionScope.accountsList!=null}">
		<table>
			<tr>
				<td colspan="2">Account Information
			</tr>
			<c:forEach items="${sessionScope.accountsList}" var="account">
				<tr>
					<td>User Id :</td>
					<td>${account.userId}</td>
				</tr>
				<tr>
					<td>Name :</td>
					<td>${account.name}</td>
				</tr>
				<tr>
					<td>ROLE :</td>
					<td>${account.role}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>