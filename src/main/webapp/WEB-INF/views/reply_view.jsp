<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.javalec.spring_pjt_board.command.BListCommand"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/menu.jsp"/>
	 <table width="500" cellpadding="0" cellspacing="0" border="1">
		  <form action="reply" method="post">
		  <input type="hidden" name="bNum" value="${reply_view.bNum}">
		  <input type="hidden" name="bGroup" value="${reply_view.bGroup}">
		  <input type="hidden" name="bStep" value="${reply_view.bStep}">
		  <input type="hidden" name="bIndent" value="${reply_view.bIndent}">
				   <tr>
				    <td> 번호 </td>
				    <td> ${reply_view.bNum} </td>
				   </tr>
				   <tr>
				    <td> 조회 </td>
				    <td> ${reply_view.bHit} </td>
				   </tr>
				   <tr>
				    <td> 이름 </td>
				    <td> <input type="text" name="bName" value="${reply_view.bName}"></td>
				   </tr>
				   <tr>
				    <td> 제목 </td>
				    <td> <input type="text" name="bTitle" value="${reply_view.bTitle}"></td>
				   </tr>
				   <tr>
				    <td> 내용 </td>
				    <td> <textarea rows="10"  name="bContent">${reply_view.bContent}</textarea></td>
				   </tr>
				  
		  
		 
		  <%
		
		BListCommand list = null;
				
		if(list.blist == "list")
		{
		%>
				<td colspan="2"><input type="submit" value="답변"> <a href="list" >목록</a></td>
			</form>
		<%
		}
		%>
		<%
		if(list.blist == "viplist")
		{
		%>
				<td colspan="2"><input type="submit" value="답변"> <a href="viplist" >목록</a></td>
			</form>
		<%
		}
		%>
			    
		
	 </table>
 
</body>
</html>