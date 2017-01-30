<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
<style>
.errorsStyle {
	color: red;
	
}
</style>
</head>
<body>
	<div>
		<c:if test="${message!=null}">
			${message}
		</c:if>
	</div>

	<c:url value="/users/save" var="regUrl"></c:url>
	<form:form action="${regUrl}" method="POST" commandName="regUser">
		<form:errors path="*" element="div" cssStyle="errorsStyle"></form:errors>

		<table>
			<tr>
				<td><spring:message code="user.reg.name.lbl"></spring:message></td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name"></form:errors></td>
			</tr>

			<tr>
				<td><spring:message code="user.reg.dob.lbl"></spring:message></td>
				<td><form:input path="dob" alt="yyyy-MM-dd" /></td>
				<td><form:errors path="dob"></form:errors></td>
			</tr>

			<tr>
				<td><spring:message code="user.reg.phone.lbl"></spring:message></td>
				<td><form:input path="phone" /></td>
				<td><form:errors path="phone"></form:errors></td>
			</tr>

			<tr>
				<td><spring:message code="user.reg.email.lbl"></spring:message></td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email"></form:errors></td>
			</tr>

			<tr>
				<td><spring:message code="user.reg.username.lbl"></spring:message></td>
				<td><form:input path="credentials.username" /></td>
				<td><form:errors path="credentials.username"></form:errors></td>
			</tr>

			<tr>
				<td><spring:message code="user.reg.password.lbl"></spring:message></td>
				<td><form:password path="credentials.password" /></td>
				<td><form:errors path="credentials.password"></form:errors></td>
			</tr>

			<tr>
				<td><spring:message code="user.reg.authority.lbl"></spring:message></td>
				<td><form:select multiple="true" path="credentials.authorities" items="${authoritiesList}"
					itemLabel="authority" itemValue="authority">
						<%-- <form:option value="ROLE_USER" label="ROLE_USER"></form:option>
						<form:option value="ROLE_TELLER" label="ROLE_TELLER"></form:option>
						<form:option value="ROLE_ADMIN" label="ROLE_ADMIN"></form:option> --%>
					</form:select></td>
				<td><form:errors path="name"></form:errors></td>
			</tr>

			<tr>
				<td colspan="3"><form:button value="Register">Register</form:button></td>
			</tr>
		</table>
	</form:form>

</body>
</html>