<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web MVC2 MemberJoin Form **</title>
<link rel="stylesheet" type="text/css" href="/Web02/myLib/myStyle.css">
<script src="/Web02/myLib/jquery-3.2.1.min.js"></script>
<script src="/Web02/myLib/inCheck.js"></script>
<script>
//1) 개별적 오류 확인을 위한 switch 변수 (전역)	
  var iCheck=false;
  var pCheck=false;	
  var nCheck=false;
  var bCheck=false;
  var oCheck=false; 
  var wCheck=false; 

// 2) 개별적 오류점검위한 focusout 이벤트 핸들러 : JQuery
  $(function() {
	  $('#id').focus();
	  $('#id').keydown(function(e){
		  // enter 누르면 다음 으로 이동
			if (e.which==13) {
				e.preventDefault(); 
				$('#idDup').focus()
			}
	  }).focusout(function() {
		 	iCheck=idCheck();
	  }); //id_focusout
	  
	  $('#password').focusout(function(){
			pCheck=pwCheck();		  
	  }); //password_focusout
	  
	  $('#name').focusout(function(){
			nCheck=nmCheck();		  
	  }); //name_focusout
	  
	  $('#birthd').focusout(function(){
			bCheck=bdCheck();		  
	  }); //birthd_focusout
	  
	  $('#point').focusout(function(){
			oCheck=poCheck();		  
	  }); //point_focusout
	  
	  $('#weight').focusout(function(){
			wCheck=weCheck();		  
	  }); //weight_focusout
  }); //ready


// 3) submit 여부를 판단 & 실행 : JS 의 function
  function inCheck(){
	// 모든 항목에 오류 없음을 확인 : switch 변수
	if (iCheck==false) {
		$('#iMessage').html('~~ id 를 확인하세요 ~~');
	}
	if (pCheck==false) {
		$('#pMessage').html('~~ password 를 확인하세요 ~~');
	}	
	if (nCheck==false) {
		$('#nMessage').html('~~ name 을 확인하세요 ~~');
	}	
	if (bCheck==false) {
		$('#bMessage').html('~~ birthday 를 확인하세요 ~~');
	}
	if (oCheck==false) {
		$('#oMessage').html('~~ point 를 확인하세요 ~~');
	}
	if (wCheck==false) {
		$('#wMessage').html('~~ weight 를 확인하세요 ~~');
	}	
	// 모든 오류 확인완료 
	// => 없으면 submit : return true , 
	//    있으면 submit 을 취소 : return false 
	if ( iCheck && pCheck && nCheck &&
		 bCheck && oCheck && wCheck ) {
		// => submit : 404
		if (confirm("~~ 정말 가입 하십니까 ? (Yes:확인 / No:취소)")==false) {   
		 	  alert('~~ 가입이 취소 되었습니다 ~~');
		 	  return false;
		}else return true; // submit 진행 -> server의 join  
	} else return false; 
  } //inCheck

  //** ID 중복 확인하기
  function idDupCheck() {
	// id 의 입력값 무결성 점검 확인
	if (iCheck==false) {
		iCheck=idCheck();
	}else { 
		// id 중복확인
		// => id를 서버로 보내 중복확인, 결과 처리 
		// => window.open(url,'','')
		//    url 요청을 서버로 전달(request) 하고, 그결과(response)를 Open 해줌
		var url="/Web02/idcheck?id="+$('#id').val(); 
		window.open(url,'_blank',
				'toolbar=no,menubar=yes,scrollbars=yes,resizable=yes,width=400,height=300');
	}  
  } //idDupCheck
  
</script>

</head>
<body>
<h3>** Web MVC2 MemberJoin Form **</h3>
<form action="/Web02/join" method="post">
<table>
	<tr height="40"><td bgcolor="aqua">I D</td>
		<td><input type="text" name="id" id="id" value="testid" size="20">&nbsp;&nbsp;
			<input type="button" value="ID중복확인" id="idDup" onclick="idDupCheck()"><br>
			<span id="iMessage" class="eMessage"></span></td></tr>
	<tr height="40"><td bgcolor="aqua">Password</td>
		<td><input type="password" name="password" id="password" value="12345" size="20"><br>
			<span id="pMessage" class="eMessage"></span></td></tr>	
	<tr height="40"><td bgcolor="aqua">Name</td>
		<td><input type="text" name="name" id="name" value="홍길동" size="20"><br>
			<span id="nMessage" class="eMessage"></span></td></tr>
	<tr height="40"><td bgcolor="aqua">Level</td>
		<td><select name="lev" id="lev">
				<option value="A" >관리자</option>
				<option value="B" >나무</option>
				<option value="C" >잎새</option>
				<option value="D" selected>새싹</option>
			</select>
		</td></tr>
	<tr height="40"><td bgcolor="aqua">Birthday</td>
		<td><input type="date" name="birthd" id="birthd"><br>
			<span id="bMessage" class="eMessage"></span></td></tr>
	<tr height="40"><td bgcolor="aqua">Point</td>
		<td><input type="text" name="point" id="point" value="3000" size="20"><br>
			<span id="oMessage" class="eMessage"></span></td></tr>
	<tr height="40"><td bgcolor="aqua">Weight</td>
		<td><input type="text" name="weight" id="weight" value="66.77" size="20"><br>
			<span id="wMessage" class="eMessage"></span></td></tr>
	<tr height="40"><td></td>
		<td><input type="submit" value="가입" onclick="return inCheck()" id="submit" disabled>&nbsp;&nbsp;
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