<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

				
				
			 <%
		 String value = (String)session.getAttribute("User_ID");
			
		 if (!isset(value))
			{
				out.println("<p>로그인되지 않았습니다.</p>");
			}
			else
			{
			%>
 		<tr>
		  <td><h2>게시판</h2><p></td>
		 </tr>
        <tr>
        	<%
			request.setAttribute("uId", session.getAttribute("User_ID"));
			%>
        	<input type="hidden" name="uId" value="${uId}">
            <td colspan="5"> <a href="list">가입인사</a> </td><br>
            <td colspan="5"> <a href="viplist">자유게시판</a> </td></br>
        </tr>
        <%
			}
        %>

</body>
</html>
