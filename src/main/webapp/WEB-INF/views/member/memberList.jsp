<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Web_MVC2 MemberList</title>
</head>
<body>
<h3>** Web_MVC2 MemberList **</h3>
<br>
<c:if test="${not empty message}">
=> ${message}<br>
</c:if>
<hr>
<table width=100%>
<tr height="30" bgcolor="pink">
	<th>I D</th><th>Password</th><th>Name</th><th>Level</th>
	<th>Birthday</th><th>Point</th><th>Weight</th>
</tr>	
<c:forEach var="list" items="${banana}">
<tr  height="30" align="center">
	<td> <!-- 관리자 기능 추가하기 -->
	<c:if test="${loginID=='admin'}">
		<a href="mdetail?id=${list.id}">${list.id}</a>
	</c:if>
	<c:if test="${loginID!='admin'}">
		${list.id} 
	</c:if>
	</td>
	<td>${list.password}</td><td>${list.name}</td>
	<td>${list.lev}</td><td>${list.birthd}</td>
	<td>${list.point}</td><td>${list.weight}</td>
</tr>
</c:forEach>
</table>
<hr>
<a href='javascript:history.go(-1)'>이전으로</a>&nbsp;&nbsp;
<a href="home" >[Home]</a>
<pre>
** 과제 **
id 를 클릭하면 해당 id 의 detail(상세정보) 화면 출력하기 
=> id 에 a Tag 적용
=> detail Controller 만들기
=> Service, DAO 에 detail 메서드 (  selectOne(MemberVO vo)  ) 추가하기
=> View 만들기 ()
</pre>

</body>
</html>