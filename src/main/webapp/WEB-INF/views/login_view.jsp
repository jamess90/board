<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring_MVC_Board</title>
</head>
<body>
	<%
		session.invalidate();
	%>
	
	<jsp:include page="/WEB-INF/views/menu.jsp"/>
	<table>
		<form action="login" method = "post">
		 <tr>
		  <td><h2>로그인</h2><p></td>
		 </tr>
		 <tr>
		  <td>id</td><td><input type="text" name="uId"></td>
		 </tr>
		 <tr>
		  <td>password</td><td><input type="password" name="uPassword"></td>
		 </tr>
		  <tr>
		  <td colspan="2"><input type="submit" value="로그인">&nbsp;&nbsp;<a href="register_view">회원가입</a></td>
		  </tr>
		</form>

	</table>
	
	
	
</body>
</html>