<%@ page language="java" import="com.kgc.pojo.News"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="newsServiceImpl"
	class="com.kgc.service.impl.NewsServiceImpl" />
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String summary = request.getParameter("summary");
		String newscontent = request.getParameter("newscontent");
		News news = (News) session.getAttribute("news");
		boolean isOK=false;
		
		if (news.getTitle().equals(title)) {
			request.getRequestDispatcher("adminNewsView.jsp?id="+news.getId()).forward(request, response);
		} else {
			 news.setTitle(title);
			 isOK=newsServiceImpl.updateNewsById(news);
			 session.setAttribute("isOK", isOK);
			 response.sendRedirect("newsDetailList.jsp");
		}
	%>
</body>
</html>