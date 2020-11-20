<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>  
<%@ page import = "cs.dit.dao.*, cs.dit.dto.*, java.util.List, java.sql.Date" %>
   
<!DOCTYPE html>
<html>
<head>
	<title>게시판</title>
	<meta charset="UTF-8">
 	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<h2>상세보기</h2>
	<br/>
	<form action="list.do" method="post">
		<input type="hidden" name="bcode" value="${dto.bcode}" id="bcode">
		<table class="table table-striped table-hover">
			<tr>
				<th>bcode</th><td>${dto.bcode}</td>
				<th>subject</th><td><input type="text" value="${dto.subject}" name="subject"></td>
			</tr>
			<tr>
				<th>content</th><td><input type="text" value="${dto.content}" name="content"></td>
				<th>writer</th><td><input type="text" value="${dto.writer}" name="writer"></td>
			</tr>
			<tr>
				<th>regDate</th><td colspan="3"><input type="text" value="${dto.regDate}" name="regDate"></td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="submit" value ="멤버 목록" >
					<input type="button" value ="홈으로" onclick ="location.href='index.html'">
				</td>
			</tr>
		</table><br><br>
	</form>
</div>
</body>

<head>
<!-- 댓글 달기 -->
	<script type="text/javascript">
		var bcode = encodeURIComponent(document.getElementById("bcode").value);
		
		function replylist() {
			
			$.ajax({
				url:'replyLoad.rp',
				type:'post',
				data: {"bcode": bcode},
				async:true,
				dataType:'json',
				success:function(data){
					var str = '';
					
					for(var i in data){
						str +='<tr><td>'+ data[i].rcode+ '</td>';
						str +='<td>'+ data[i].reply+ '</td>';
						str +='<td>' + data[i].regdate + '</td></tr>';
					}
					$('#replyTable').html(str);
				}	
			});
		}
		
		function rinsert() {
	 		var reply = encodeURIComponent(document.getElementById("reply").value);
	 		
	 		$.ajax({
	 			url:'replyInsert.rp',
	 			data:{"bcode":bcode, "reply":reply},
	 			type:'POST',
	 			async:true,
	 			success:function() {
	 				replylist();
	 				$('#reply').val("");
	 			}
	 		});
		}
		
		$('document').ready(function() {
			replylist();
		});
	</script>
</head>

<body>
	<br>
	<div class="container">
		<table class="table" style="text-align:center; border: 1px solid #ddddddd">
				<tr>
					<td style="background-color:#fafafa; text-align:center">댓글 : </td>
					<td><input class="form-control" type="text" id="reply" size="100"></td>
					<td colspan="2"><button class="btn btn-primary pull-right" onclick="rinsert();">한줄 댓글 작성</button></td>
				</tr>
			</tbody>
		</table>
		<table class="table" style="text-align:center; border:1px solid #dddddd">
			<tbody id ="replyTable">
			</tbody>
		</table>
	</div>
</body>
</html>