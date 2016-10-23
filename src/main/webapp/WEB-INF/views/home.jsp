<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.javalec.spring_pjt_board.command.ULoginCommand"%>
<%@ page import="java.util.*" %>
<html>

<%!
	boolean isset(String str)
	{
		if(str == null) return false;
		if(str.equals("")) return false;
		return true;
	}
%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Home</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/menu.jsp"/>
		<tr>
		  <td><h2>Home</h2><p></td>
		 </tr>
		 <%
		 String value = (String)session.getAttribute("User_ID");
			
		 if (!isset(value))
			{
				out.println("<p>로그인되지 않았습니다.</p>");
				out.println(value);
			}
			else
			{
				out.println("<p>로그인되었습니다.</p>");
				out.println(value);
			}
		 %>


</body>
</html>
