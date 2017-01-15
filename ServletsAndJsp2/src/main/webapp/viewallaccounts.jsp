 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="coretag"%>

<style>
	table, tr, td {
		border: 1px solid;
	}
</style>


<c:if test="${vat!=null && vat.equalsIgnoreCase('multiple')}">
	<c:if test="${accountsList==null}">
		<div class="error">No user found.</div>
	</c:if>
</c:if>

<c:if test="${accountsList!=null}">
	<table>
		<tr>
			<td colspan="3">Account Information
		</tr>
		<tr>
			<td>Name</td>
			<td>Username</td>
			<td>Role</td>
		</tr>
		<c:forEach items="${accountsList}" var="account">
			<tr>
				<td>${account.userId}</td>
				<td>${account.name}</td>
				<td>${account.role}</td>
			</tr>
		</c:forEach>
	</table>
</c:if>