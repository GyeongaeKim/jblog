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
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id }/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${authUser.id }/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${authUser.id }/admin/writeForm">글작성</a></li>
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
		      			
		      			<!-- <div id="listArea">
						</div> -->
						
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
		      			<td><input type="text" name="cateName" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="description"></td>
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
	console.log("카테고리 화면");
	fetchList();
});

var id = '${authUser.id}';

//리스트 요청
function fetchList(){
	$.ajax({
		
		url : "${pageContext.request.contextPath }/getCList",		
		type : "post",
		contentType : "application/json",
		data : JSON.stringify(id),

		dataType : "json",
		success : function(categoryList){
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



//카테고리 추가
$("#btnAddCate").on("click", function(){
	var cateName = $("[name=cateName]").val();
	var description = $("[name=description]").val();
	
	if(cateName == "" || cateName == null){
		alert("카테고리명을 입력해 주세요.");
		return;
	}
	if(description == "" || description == null){
		alert("설명을 입력해 주세요.");
		return;
	}
	
	var categoryVo = {
			cateName: cateName,
			id: id,
			description: description
	};
	
	$.ajax({
		url : "${pageContext.request.contextPath}/addCategory",
		type: "post",
		contentType : "application/json",
		data : JSON.stringify(categoryVo),
		dataType : "json",
		success : function(categoryMap){
			console.log(categoryMap);
			render(categoryMap, 'up');
			
			$("[name=cateName]").val("");
			$("[name=description]").val("");
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
});



//카테고리 삭제
$("#cateList").on("click", ".btnCateDel", function(){
	var $this = $(this);
	var cateNo = parseInt($this.data("cateno"));
	
	$.ajax({
		url : "${pageContext.request.contextPath}/deleteCategory",
		type: "post",
		contentType : "application/json",
		data : JSON.stringify(cateNo),
		dataType : "json",
		success : function(count){
			$("#t" + cateNo).remove();
		},
		error : function(XHR, status, error) {
			console.error(status + " : " + error);
		}
	});
});


//테이블 데이터 추가
function render(categoryList, opt) {
	
	
	var str = '';
	str += '<tr id="t' + categoryVo.cateNo + '">';
	str += '	<td>' + categoryVo.cateNo + '</td>';
	str += '	<td>' + categoryVo.cateName + '</td>';
	str += '	<td>포스트수</td>';
	str += '	<td>' + categoryVo.description + '</td>';
	str += '	<td>';
	str += '		<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg" data-cateno="'+categoryVo.cateNo+'">';
	str += '	</td>';
	str += '</tr>';
	
	if(opt == 'down') {
		$("#cateList").append(str);
	} else if(opt == 'up'){
		$("#cateList").prepend(str);
	} else {
		console.log("opt오류");
	}
}

</script>
</html>