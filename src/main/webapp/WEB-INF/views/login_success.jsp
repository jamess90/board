<%@page import="org.springframework.format.Printer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.javalec.spring_pjt_board.dto.UDto"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home</title>
</head>
<body>
	<%
	UDto dto = (UDto)request.getAttribute("request");

	session.setAttribute("User_ID", request.getParameter("uId"));
	session.setAttribute("User_GRADE", dto.getuGrade());
	
	out.println(dto.getuGrade());
	
	%>
	<jsp:include page="/WEB-INF/views/menu.jsp"/>
 		<tr>
 		
		  <td><h2>${request.uName}님 안녕하세요!</h2><p></td>
		</tr>

</body>
</html>
