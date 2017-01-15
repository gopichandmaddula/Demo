<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="coretag" %>
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

	<c:if test="${message!=null}">
		<div class="message">
			${message}
		</div>
	</c:if>

	<c:url var="addAccountUrl" value="/AddAccount" />
	<form action="${addAccountUrl}" method="POST">
		<table>
			<tr>
				<td colspan="2">Add New Account</td>
			</tr>
			<tr>
				<td>Name :</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>Username :</td>
				<td><input type="text" name="userId" /></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><input type="password" name="password" /></td>
			</tr>

			<tr>
				<td>ROLE :</td>
				<td><select name="role">
						<option value="ROLE_USER" selected="selected">ROLE_USER</option>
						<option value="ROLE_ADMIN">ROLE_ADMIN</option>
				</select></td>
			</tr>
			<tr align="right">
				<td colspan="2"><input type="submit" value="Add Account" /></td>
			</tr>
		</table>
	</form>
</div>