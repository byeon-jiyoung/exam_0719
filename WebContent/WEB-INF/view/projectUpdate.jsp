<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<style>
	#container {
		width: 900px;
		margin: 0 auto;
	}
	#f1 {
		margin-left: 50px;
	}
	label {
		width: 100px;
		float: left;
		text-align: right;
		margin-right: 10px;
	}
	.submit {
		text-align: center;
	}
	input[type="submit"] {
		background-color: black;
		color: white;
		border: 1px solid black;
		padding: 2px 20px;
	}
	input[type="reset"] {
		background-color: black;
		color: white;
		border: 1px solid black;
		padding: 2px 20px;
	}
</style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script>
	$(function() {
		$(".date").datepicker({
			dateFormat: 'yy-mm-dd'
		});
		
		$("#f1").submit(function() {
			var start = $("input[name='start']").val();
			var end = $("input[name='end']").val();
			
			var start_date = new Date(start).getTime();
			var end_date = new Date(end).getTime();
			
			if(start_date > end_date) {
				alert("시작일이 종료일보다 늦습니다. \n다시 선택하여 주시기 바랍니다.");
				return false;
			}
		})
	})
</script>
</head>
<body>
	<div id="container">
		<jsp:include page="project_top.jsp" />
		<form action="update.do" method="post" id="f1">
			<input type="hidden" name="no" value="${no}">
			<p>
				<label>프로젝트이름</label>
				<input type="text" name="name" value="${name}">
			</p>
			<p>
				<label>프로젝트내용</label>
				<textarea rows="10" cols="50" name="content">${content}</textarea>
			</p>
			<p>
				<label>시작날짜</label>
				<input type="text" name="start" value="${start}" class="date" autocomplete="off">
				
			</p>
			<p>
				<label>마감날짜</label>
				<input type="text" name="end" value="${end}" class="date" autocomplete="off">
			</p>
			<p>
				<label>상태</label>
				<select name="state">
					<option <c:if test="${state == '준비'}">selected</c:if> >준비</option>
					<option <c:if test="${state == '진행중'}">selected</c:if> >진행중</option>
					<option <c:if test="${state == '종료'}">selected</c:if> >종료</option>
					<option <c:if test="${state == '보류'}">selected</c:if> >보류</option>
				</select>
			</p>
			<p class="submit">
				<input type="submit" value="수정">
				<input type="reset" value="취소">
			</p>
		</form>
		<jsp:include page="project_bottom.jsp" />
	</div>
</body>
</html>