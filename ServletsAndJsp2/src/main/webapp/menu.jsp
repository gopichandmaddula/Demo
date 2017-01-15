<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="coretag" %>
	<c:url var="singleUrl" value="/home?vat=single"></c:url>
	<c:url var="multiUrl" value="/home?vat=multiple"></c:url>
	<c:url var="addAccountUrl" value="/home?vat=addaccount"></c:url>
	Menu
	<br />
	<a  href="${singleUrl}" >View Account</a>
	<br />
	<a href="${multiUrl}">View All Accounts</a>
	<br />
	<c:set var="rr" value="${sessionScope.role}"></c:set>
	<c:if test="${rr.equals('ROLE_ADMIN')}">
	<a href="${addAccountUrl}">Add New Account</a>
	</c:if>
	