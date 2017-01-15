<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="coretag"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<style>
.wrapper {
	width: 96%;
	align: center;
}

.header {
	width: 95%;
	background-color: green;
	padding: 2.5em;
}

.menu {
	width: 15%;
	background-color: blue;
	padding-top: 2em;
	padding-bottom: 2em;
	float: left;
	color: white;
}

.menu a {
	color: white
}

.menu a:hover {
	color: black;
}

.content {
	width: 85%;
	float: left;
	padding-top: 2em;
	padding-bottom: 2em;
	background-color: red;
}
</style>
</head>
<body>
	<div class="wrapper">
		<div class="header">
			<jsp:include page="header.jsp"></jsp:include>
		</div>
		<div class="menu">
			<jsp:include page="menu.jsp"></jsp:include>
		</div>
		<div class="content">
			<c:if test="${param.vat.equalsIgnoreCase('single')}">
				<jsp:include page="/ViewAccount?vat=${param.vat}"></jsp:include>
			</c:if>
			 <c:if test="${param.vat.equalsIgnoreCase('multiple')}">
				<jsp:include page="/ViewMultipleAccount?vat=${param.vat}"></jsp:include>
			</c:if>
			<c:if test="${param.vat.equalsIgnoreCase('addaccount')}">
				<jsp:include page="/AddAccount"></jsp:include>
			</c:if>
		</div>
	</div>
</body>
</html>