<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.kgc.pojo.News"%>
<jsp:useBean id="newsServiceImpl"
	class="com.kgc.service.impl.NewsServiceImpl" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String message = null;
		String id = request.getParameter("id");
		News news = newsServiceImpl.getNewById(new Integer(id));
		session.setAttribute("news", news);
		Boolean isOK = (Boolean) session.getAttribute("isOK");
		if (isOK != null) {
			if (isOK == true) {
				message = "修改成功！";
			} else {
				message = "修改失败！";
			}
	%>
	<span style="display: none" onload="javascript:alert('<%=message%>')"></span>
	<%
		session.removeAttribute("isOK");
		}
	%>
	<form method="post" action="doUpdate.jsp">
		<table>
			<tr>
				<td style="text-align: right;" class="text_tabledetail2">标题</td>
				<td style="text-align: left;"><input type="text" name="title"
					value="<%=news.getTitle()%>" /></td>
			</tr>
			<tr>
				<td style="text-align: right;" class="text_tabledetail2">作者</td>
				<td style="text-align: left;"><input type="text" name="author"
					value="<%=news.getAuthor()%>" /></td>
			</tr>
			<tr>
				<td style="text-align: right;" class="text_tabledetail2">摘要</td>
				<td style="text-align: left;"><textarea id="summary"
						name="summary" rows="8" cols="50"><%=news.getSummary()%></textarea></td>
			</tr>
			<tr>
				<td style="text-align: right;" class="text_tabledetail2">内容</td>
				<td style="text-align: left;">
					<div id="xToolbar"></div> <textarea id="newscontent"
						name="newscontent" rows="8" cols="30"><%=news.getContent()%></textarea>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="保存"></td>
				<td><a href="newsDetailList.jsp">取消</a></td>
			</tr>
		</table>
	</form>
</body>
</html>