<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring Mvc2 MemberDetail **</title>
</head>
<body>
<h2>** Spring Mvc2 MemberDetail **</h2>
<table>
	<tr height="40">
		<td bgcolor="Lavender">I  D</td><td>${apple.id}</td>
	</tr>
	<tr height="40">
		<td bgcolor="Lavender">Password</td><td>${apple.password}</td>
	</tr>
	<tr height="40">
		<td bgcolor="Lavender">Name</td><td>${apple.name}</td>
	</tr>
	<tr height="40">
		<td bgcolor="Lavender">Level</td><td>${apple.lev}</td>
	</tr>
	<tr height="40">
		<td bgcolor="Lavender">Birthday</td><td>${apple.birthd}</td>
	</tr>
	<tr height="40">
		<td bgcolor="Lavender">Point</td><td>${apple.point}</td>
	</tr>
	<tr height="40">
		<td bgcolor="Lavender">Weight</td><td>${apple.weight}</td>
	</tr>
</table>
<c:if test="${not empty message}">
<hr>
=> ${message}<br>
</c:if>
<hr>
<a href="mdetail?jcode=U&id=${apple.id}">내정보수정</a>&nbsp;&nbsp;
<!-- 1) 내정보수정 -> 내정보읽기서블릿 (mdetail) 
 			  -> 내정보수정화면 (updateForm.jsp) : 수정후, submit 
 			  -> 서블릿(컨트롤러) 
 	 2) 관리자기능 추가 -> loghinID 는 관리자이지만, 수정대상은 현재 출력된 id
 	 				 -> 수정, 탈퇴 uri 에 &id=${apple.id} 추가 		  
 			  -->
<a href="mdelete?id=${apple.id}">회원탈퇴</a>
<hr>
<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;&nbsp;
<a href="home">HOME</a>
</body>
</html>