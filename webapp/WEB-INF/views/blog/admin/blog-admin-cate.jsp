<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<!-- jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>


</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>


		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="">기본설정</a></li>
				<li class="tabbtn selected"><a href="">카테고리</a></li>
				<li class="tabbtn"><a href="">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
		      			
		      			<div id="listArea">
						</div>
						
						<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">

<!-- 준비가 끝나면 -->
$(document).ready(function(){
	console.log("jquery로 요청 - data만 받는 요청");
	fetchList();
});

//리스트 요청
function fetchList(){
	$.ajax({
		
		url : "${pageContext.request.contextPath }/blog/categoryList",		
		type : "post",
		contentType : "application/json",
		data : {name: name},

		dataType : "json",
		success : function(categoryList){	//function()안의 이름은 알아서 정하면 됨
			/*성공시 처리해야될 코드 작성*/
			console.log(categoryList);
			for(var i=0; i<categoryList.length; i++){
				render(categoryList[i], "down");
			}
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
};

//리스트 그리기
function render(categotyVo, opt){	//opt 옵션을 추가한다.
	console.log("render()");
	
	var str = '';
	str += '<tr>';
	str += '	<td>'+categoryVo.no+'</td>';
	str += '	<td>'+cateNo.cateNo+'</td>';
	str += '	<td>포스트 수</td>';
	str += '	<td>'+cateNo.cateName+'</td>';
	str += '	<td class='text-center'>';
	str += '		<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg">';
	str += '	</td>';
	str += '</tr>';
	
	
	if(opt == "down"){
		$("#listArea").append(str);
	}else if(opt == "up"){
		$("#listArea").prepend(str);
	}else{
		console.log("opt오류");
	}
}


</script>
</html>