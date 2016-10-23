<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.javalec.spring_pjt_board.dao.BDao"%>
<%@page import="com.javalec.spring_pjt_board.dto.UDto"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%!
	public Integer toInt(String x){
		int a = 0;
		try{
			a = Integer.parseInt(x);
		}catch(Exception e){}
		return a;
	}
%>
<%
	int pageno = toInt(request.getParameter("pageno"));
	if(pageno<1){
		pageno = 1;
	}
	
	int num = BDao.getinstance().count_rows("vip_list");
	int total_record = num;		   //현재 페이지
	int page_per_record_cnt = 5;   //페이지 당 레코드 수
	int group_per_page_cnt =5;     //페이지 당 보여줄 번호 수[1],[2],[3],[4],[5]
//					  									  [6],[7],[8],[9],[10]											
	int record_end_no = pageno*page_per_record_cnt;				
	int record_start_no = record_end_no-(page_per_record_cnt-1);
	if(record_end_no>total_record){
		record_end_no = total_record;
	}
										   
	int total_page = total_record / page_per_record_cnt + (total_record % page_per_record_cnt>0 ? 1 : 0);
	if(pageno>total_page){
		pageno = total_page;
	}

// 	현재 페이지(정수) / 한페이지 당 보여줄 페지 번호 수(정수) + (그룹 번호는 현제 페이지(정수) % 한페이지 당 보여줄 페지 번호 수(정수)>0 ? 1 : 0)
	int group_no = pageno/group_per_page_cnt+( pageno%group_per_page_cnt>0 ? 1:0);
//		현재 그룹번호 = 현재페이지 / 페이지당 보여줄 번호수 (현재 페이지 % 페이지당 보여줄 번호 수 >0 ? 1:0)	
//	ex) 	14		=	13(몫)		=	 (66 / 5)		1	(1(나머지) =66 % 5)			  
	
	int page_eno = group_no*group_per_page_cnt;		
//		현재 그룹 끝 번호 = 현재 그룹번호 * 페이지당 보여줄 번호 
//	ex) 	70		=	14	*	5
	int page_sno = page_eno-(group_per_page_cnt-1);	
// 		현재 그룹 시작 번호 = 현재 그룹 끝 번호 - (페이지당 보여줄 번호 수 -1)
//	ex) 	66	=	70 - 	4 (5 -1)
	
	if(page_eno>total_page){
//	   현재 그룹 끝 번호가 전체페이지 수 보다 클 경우		
		page_eno=total_page;
//	   현재 그룹 끝 번호와 = 전체페이지 수를 같게
	}
	
	int prev_pageno = page_sno-group_per_page_cnt;  // <<  *[이전]* [21],[22],[23]... [30] [다음]  >>
//		이전 페이지 번호	= 현재 그룹 시작 번호 - 페이지당 보여줄 번호수	
//	ex)		46		=	51 - 5				
	int next_pageno = page_sno+group_per_page_cnt;	// <<  [이전] [21],[22],[23]... [30] *[다음]*  >>
//		다음 페이지 번호 = 현재 그룹 시작 번호 + 페이지당 보여줄 번호수
//	ex)		56		=	51 - 5
	if(prev_pageno<1){
//		이전 페이지 번호가 1보다 작을 경우		
		prev_pageno=1;
//		이전 페이지를 1로
	}
	if(next_pageno>total_page){
//		다음 페이지보다 전체페이지 수보가 클경우		
		next_pageno=total_page/group_per_page_cnt*group_per_page_cnt+1;
//		next_pageno=total_page
//		다음 페이지 = 전체페이지수 / 페이지당 보여줄 번호수 * 페이지당 보여줄 번호수 + 1 
//	ex)			   = 	76 / 5 * 5 + 1	???????? 		
	}
	
	// [1][2][3].[10]
	// [11][12]
%>
<title>Insert title here</title>
</head>
<body> 
	<jsp:include page="/WEB-INF/views/menu.jsp"/>
<%
	request.setAttribute("uId", session.getAttribute("User_ID"));
%>
	<input type="hidden" name="uId" value="${uId}">
				
<%

	String grade = (String)session.getAttribute("User_GRADE");

	if(!grade.equals("우수회원"))
	{	
		out.println(grade+"님 ");
		out.println("게시판을 이용하려면 가입인사를 해주세요");
	}
	else
	{
%>

    <table width="960" cellpadding="1" cellspacing="1" border="2">
        <tr>
            <td>번호</td>
            <td>제목</td>
            <td>작성자</td>
            <td>작성일</td>
            <td>조회수</td>
        </tr>
        <c:forEach items="${viplist}"  begin = "<%=record_start_no-1%>" end = "<%=record_end_no-1%>" var="dto">
        <tr>
            <td>${dto.bNum}</td>
            <td>
                <c:forEach begin="2" end="${dto.bIndent}">　</c:forEach>
                <c:if test="${dto.bIndent>0}">└re)</c:if>
                <a href="content_view?bNum=${dto.bNum}">${dto.bTitle}</a></td>
            <td>${dto.bName}</td>
            <td>${dto.bDate}</td>
            <td>${dto.bHit}</td>
        </tr>
        </c:forEach>
        <tr>
            <td colspan="5"> <a href="write_view">글작성</a> </td>
        </tr>
    </table>    
  
<br>

　　　　　　　　　　　　　　　　　　　
<a href="viplist?pageno=1">[＜＜]</a>
<a href="viplist?pageno=<%=prev_pageno%>">[＜]</a> 
<%for(int i =page_sno;i<=page_eno;i++){%>
	<a href="viplist?pageno=<%=i %>">
		<%if(pageno == i){ %>
			[<%=i %>]
		<%}else{ %>
			<%=i %>
		<%} %>
	</a> 
<%--	콤마	 --%>	
	<%if(i<page_eno){ %>
		,
	<%} %>
<%} %>
 
<a href="viplist?pageno=<%=next_pageno%>" >[＞]</a>
<a href="viplist?pageno=<%=total_page %>">[＞＞]</a><br>
    
 <%--   
현재 페이지   (pageno)   : <%=pageno%><br />
전체 데이터 수   (total_record) : <%=total_record %><br />
한페이지 당 레코드 수   (page_per_record_cnt) : <%=page_per_record_cnt %><br />
한페이지 당 보여줄 페지 번호 수   (group_per_page_cnt) : <%=group_per_page_cnt %><br />

<hr />
레코드 시작 번호  (record_start_no) : <%=record_start_no%><br />
레코드 끝 번호    (record_end_no) : <%=record_end_no %><br />
전체페이지 수     (total_page)  : <%=total_page %><br />
<hr />
현재 그룹번호 [1] (group_no):  <%=group_no %><br />
현재 그룹 시작 번호(page_sno): <%= page_sno%><br />
현재 그룹 끝 번호  (page_eno): <%= page_eno%><br />
이전 페이지 번호   (prev_pageno) <%=prev_pageno%><br />
다음 페이지 번호   (next_pageno) <%=next_pageno%><br />
<hr />
--%>

</body>
<%
}
%>
</html>
