<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="resources/myLib/myStyle.css">
	<script src="resources/myLib/jquery-3.2.1.min.js"></script>
</head>
<body>
<h1> Hello Spring !!! </h1>
<P> Start time : ${serverTime}</P>
<c:if test="${not empty loginID}">
=> ${loginName} 님 안녕하세요 ~~<br> 
</c:if>
<c:if test="${not empty message}">
	<hr>
	=> ${message}<br>
</c:if>
<hr>
<img src="resources/image/Simpsons1.jpg" width="400" height="300">
<hr>
<br>
<a href="mlist">MemberList</a><br>
<a href="loginf">LoginForm</a><br>
<a href="logout">Logout</a><br>
</body>
</html>
