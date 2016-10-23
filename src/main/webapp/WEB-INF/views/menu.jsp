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
<%@page import="com.javalec.spring_pjt_board.command.ULoginCommand"%>
<%@ page import="java.util.*" %>
<a href="home">메인페이지</a>&nbsp;&nbsp;

<%
		 String value = (String)session.getAttribute("User_ID");
	
		if (!isset(value))
		{
			out.println("<a href=\"login_view\">로그인</a>&nbsp;&nbsp;");
		}
		else
		{
			out.println("<a href=\"logout\">로그아웃</a>&nbsp;&nbsp;");
		}

%>

  <a href="board_view">게시판</a>&nbsp;&nbsp;
<hr width="500" align="left" noshade="noshade">