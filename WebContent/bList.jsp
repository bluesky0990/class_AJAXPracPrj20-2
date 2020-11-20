<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>  
<%@ page import = "cs.dit.dao.*, cs.dit.dto.*, java.util.List, java.sql.Date" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<title>게시판</title>
</head>
<body>
<div class="container">
	<h2>게시판 목록</h2>
	<br/>
	<table class="table table-striped table-hover">
		<tr>
			<th>bcode</th>
			<th>subject</th>
			<th>content</th>
			<th>writer</th>
			<th>regDate</th>
		</tr>
	<c:forEach var="dto" items = "${dtos}">
		<tr>
			<td><a href="view.do?bcode=${dto.bcode}">${dto.bcode}</a></td>
			<td>${dto.subject}</td>
			<td>${dto.content}</td>
			<td>${dto.writer}</td>
			<td><fmt:formatDate value="${dto.regDate}"/></td>
		</tr>
	</c:forEach>
	</table>
	<input type="button" value ="홈으로" onclick ="location.href='index.html'">
</div>
</body>
</html>
