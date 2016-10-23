<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring_MVC_Board</title>
</head>
	<body>
	<jsp:include page="/WEB-INF/views/menu.jsp"/>
		<table>
			<form action="register_do" method = "post">
				 <tr>
				  <td><h2>회원가입</h2><p></td>
		 		</tr>
				<tr>
					<td>ID</td>
					<td> <input type = "text" name = "uId"></td>
				</tr>
				<tr>
					<td>PW</td>
					<td> <input type = "password" name = "uPassword"></td>
				</tr>
				<tr>
					<td>NAME</td>
					<td> <input type = "text" name = "uName"></td>
				</tr>
				<tr>
					<td colspan="2"> <input type = "submit" value = "회원가입"><input type="reset" value="초기화"></td>
				 </tr>
			</form>
		</table>
	
</body>
</html>