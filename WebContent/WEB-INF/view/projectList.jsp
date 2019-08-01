<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	p {
		text-align: right;
	}
	th, td {
		border: 1px solid black;
		padding: 5px 10px;
	}
	table {
		border-collapse: collapse;
		width: 700px;
		text-align: center;
		margin: 0 auto;
	}
	a {
		color: black;
	}
</style>
</head>
<body>
	<div id="container">
		<jsp:include page="project_top.jsp" />
		<p>
			<a href="write.do">[새 프로젝트 등록]</a>
		</p>
		<table>
			<tr>
				<th>프로젝트 이름</th>
				<th>시작날짜</th>
				<th>종료날짜</th>
				<th>상태</th>
			</tr>
			<c:forEach var="i" items="${list}">
				<tr>
					<td>
						<a href="detail.do?no=${i.serialNum}">${i.projectName}</a>
					</td>
					<td>
						<fmt:formatDate value="${i.startDate}" pattern="yyyy-MM-dd" />
					</td>
					<td>
						<fmt:formatDate value="${i.endDate}" pattern="yyyy-MM-dd" />
					</td>
					<td>${i.projectProgress}</td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="project_bottom.jsp" />
	</div>
</body>
</html>