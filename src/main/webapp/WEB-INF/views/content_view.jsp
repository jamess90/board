<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.javalec.spring_pjt_board.command.BListCommand"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring_MVC_Board</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/menu.jsp"/>
	<table width = "500" cellpadding = "0" cellspacing = "0" border = "1">
		<form action="modify" method = "post">
		<input type= "hidden" name = "bNum" value = "${content_view.bNum}">
			<tr>
				<td>번호</td>
				<td>${content_view.bNum}</td>
			</tr>
			<tr>
				<td>조회</td>
				<td>${content_view.bHit}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td> <input type = "text" name = "bName" value = "${content_view.bName}"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td> <input type = "text" name = "bTitle" value = "${content_view.bTitle}"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td> <textarea name = "bContent">${content_view.bContent}</textarea></td>
			</tr>

		<%
		
		BListCommand list = null;
				
		if(list.blist == "list")
		{
		%>
			<td colspan="2"> <input type = "submit" value = "수정">  &nbsp;&nbsp;
			 <a href = "list">목록보기</a>  &nbsp;&nbsp; <a href = "delete?bNum=${content_view.bNum}">삭제</a>  &nbsp;&nbsp;
			  <a href = "reply_view?bNum=${content_view.bNum}">답변</a> </td>
			  </form>
		<%
		}
		%>
		<%
		if(list.blist == "viplist")
		{
		%>
			<td colspan="2"> <input type = "submit" value = "수정">  &nbsp;&nbsp;
			 <a href = "viplist">목록보기</a>  &nbsp;&nbsp; <a href = "delete?bNum=${content_view.bNum}">삭제</a>  &nbsp;&nbsp;
			  <a href = "reply_view?bNum=${content_view.bNum}">답변</a> </td>
			  </form>
		<%
		}
		%>
		
		
		
	</table>
	
</body>
</html>