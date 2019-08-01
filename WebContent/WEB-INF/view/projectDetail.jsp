<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#container {
		width: 900px;
		margin: 0 auto;
	}
	th, td{
		border: 1px solid black;
		padding: 5px 10px;
	}
	th {
		width: 25%;
	}
	td {
		word-break: break-all; /* 자동줄바꿈 */
	}
	table {
		border-collapse: collapse;
		width: 700px;
		margin: 20px auto;
	}
	#a_href {
		text-align: center;
	}
	#a_href a {
		margin: 0 10px;
		text-decoration: none;
		color: black;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	$(function() {
		$(".delete").click(function() {
			var result = confirm("삭제하시겠습니까?");
			
			if(result == true) {
				location.href="delete.do?no=${project.serialNum}";
			}
			return false;
		})
	})
</script>
</head>
<body>
	<div id="container">
		<jsp:include page="project_top.jsp" />
		<table>
			<tr>
				<th>프로젝트 이름</th>
				<td>${project.projectName}</td>
			</tr>
			<tr>
				<th>프로젝트 내용</th>
				<td>${project.projectContent}</td>
			</tr>
			<tr>
				<th>시작날짜</th>
				<td><fmt:formatDate value="${project.startDate}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>종료날짜</th>
				<td><fmt:formatDate value="${project.endDate}" pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<th>상태</th>
				<td>${project.projectProgress}</td>
			</tr>
		</table>
		<div id="a_href">
			<a href="update.do?no=${project.serialNum}">[수정]</a>
			<a href="" class="delete">[삭제]</a>
			<a href="list.do">[돌아가기]</a>
		</div>
		<jsp:include page="project_bottom.jsp" />
	</div>
</body>
</html>