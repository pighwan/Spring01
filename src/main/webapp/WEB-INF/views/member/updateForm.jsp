<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web MVC2 MemberUpdate Form **</title>
</head>
<body>
<h3>** Web MVC2 MemberUpdate Form **</h3>
<form action="/Web02/mupdate" method="post">
<table>
	<tr height="40"><td bgcolor="Lavender">I D</td>
		<td><input type="text" name="id" value="${apple.id}" size="20" readonly></td></tr>
			<!-- ** input Tag 입력 막기 
				=> disabled :  서버로 전송 안됨 
				=> readonly :  서버로 전송 됨   -->
	<tr height="40"><td bgcolor="Lavender">Password</td>
		<td><input type="password" name="password" value="${apple.password}" size="20"></td></tr>	
	<tr height="40"><td bgcolor="Lavender">Name</td>
		<td><input type="text" name="name" value="${apple.name}" size="20"></td></tr>
	<tr height="40"><td bgcolor="Lavender">Level</td>
	<%-- ${apple.lev} 에 따라서 해당되는 option 을 selected --%>
		<td><select name="lev">
		    <c:choose>
			<c:when test="${apple.lev=='A'}">
				<option value="A" selected>관리자</option>
				<option value="B">나무</option>
				<option value="C">잎새</option>
				<option value="D">새싹</option>
			</c:when>
			<c:when test="${apple.lev=='B'}">
				<option value="A">관리자</option>
				<option value="B" selected>나무</option>
				<option value="C">잎새</option>
				<option value="D">새싹</option>
			</c:when>
			<c:when test="${apple.lev=='C'}">
				<option value="A">관리자</option>
				<option value="B">나무</option>
				<option value="C" selected>잎새</option>
				<option value="D">새싹</option>
			</c:when>
			<c:when test="${apple.lev=='D'}">
				<option value="A">관리자</option>
				<option value="B">나무</option>
				<option value="C">잎새</option>
				<option value="D" selected>새싹</option>
			</c:when>
		    </c:choose>
			</select>
		</td></tr>
	<tr height="40"><td bgcolor="Lavender">Birthday</td>
		<td><input type="date" name="birthd" value="${apple.birthd}"></td></tr>
	<tr height="40"><td bgcolor="Lavender">Point</td>
		<td><input type="text" name="point" value="${apple.point}" size="20"></td></tr>
	<tr height="40"><td bgcolor="Lavender">Weight</td>
		<td><input type="text" name="weight" value="${apple.weight}" size="20"></td></tr>
	<tr height="40"><td></td>
		<td><input type="submit" value="수정">&nbsp;&nbsp;
			<input type="reset" value="취소">
		</td></tr>			
</table>
</form>
<c:if test="${not empty message}">
<br>=> ${message}<br><br> 
</c:if>
<hr>
<a href="/Web02/index.jsp" >[Home]</a>
</body>
</html>