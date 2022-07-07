<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>

<!-- CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<!-- jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>
<body>
	<div id="center-content">
		
		<!-- 메인 해더 -->
		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>

		<div>		
			<form id="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
				<table>
			      	<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td><label for="txtId">아이디</label></td>
		      			<td><input id="txtId" type="text" name="id"></td>
		      			<td><button id="btnIdCheck" type="button">아이디체크</button></td>
		      		</tr>
		      		<tr>
		      			<td></td>
		      			<td id="tdMsg" colspan="2">사용할 수 있는 아이디 입니다.</td>
		      		</tr> 
		      		<tr>
		      			<td><label for="txtPassword">패스워드</label> </td>
		      			<td><input id="txtPassword" type="password" name="password"  value=""></td>   
		      			<td></td>  			
		      		</tr> 
		      		<tr>
		      			<td><label for="txtUserName">이름</label> </td>
		      			<td><input id="txtUserName" type="text" name="userName"  value=""></td>   
		      			<td></td>  			
		      		</tr>  
		      		<tr>
		      			<td><span>약관동의</span> </td>
		      			<td colspan="3">
		      				<input id="chkAgree" type="checkbox" name="agree" value="y">
		      				<label for="chkAgree">서비스 약관에 동의합니다.</label>
		      			</td>   
		      		</tr>   		
		      	</table>
	      		<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit" >회원가입</button>
				</div>
	      		
			</form>
			
		</div>
		
		
		<!-- 메인 푸터  자리-->
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
		
	</div>

</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#tdMsg").hide();
})


//아이디체크 버튼 클릭했을때
$("#joinForm").on("click", "#btnIdCheck", function(){
	console.log("아이디 체크 버튼");
	
	var id = $("#txtId").val();
	
	if(id == "" || id == null){
		alert("아이디를 입력해주세요");
		return false;
	}else{
		$("#tdMsg").show();
	}
});


//회원가입 버튼
$("#joinForm").on("click", "#btnJoin", function(){
	console.log("회원가입 버튼");
	
	var id = $("#txtId").val();
	var password = $("#txtPassword").val();
	var userName = $("#txtUserName").val();
	
	
	
	if(id == "" || id == null){
		alert("아이디를 입력해주세요");
		return false;
	}
	if(password.length < 8){
		alert("패스워드를 체크해주세요")
		return false;
	}
	if(userName == "" || userName == null){
		alert("이름을 입력해주세요");
		return false;
	}
	
	//약관동의
	var agree = $("#chkAgree").is(":checked");
	if(agree == false){
		alert("약관에 동의해주세요");
		return false;
	}
	
});




</script>












</html>