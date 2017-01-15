<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="coretag" prefix="c" %>
	<style>
		.logout a {
			float:right;
			color: white;
		}
		.logout a:hover {
			color: aqua;
		}
	</style>

    <div class="logout">
    	<c:url var="logoutUrl" value="/logout"></c:url>
    	<a href="${logoutUrl}">Logout</a>
    </div>