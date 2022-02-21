<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_MVC2 LoginForm **</title>
</head>
<body>
<h3>** Spring_MVC2 LoginForm **</h3><br>
<form action="login" method="post">
<table>
	<tr><td bgcolor="PaleTurquoise">I D</td>
		<td><input type="text" name="id" value="banana"></td></tr>
	<tr><td bgcolor="PaleTurquoise ">Password</td>
		<td><input type="password" name="password" value="12345"></td></tr>
	<tr><td></td>
		<td><input type="submit" value="Login">&nbsp;&nbsp;
			<input type="reset" value="Reset"></td>
	</tr> 
</table>
</form>
<c:if test="${not empty message}">
	<br>=> ${message}<br><br>
</c:if>
<hr>
<a href="home">[Home]</a>
</body>
</html>