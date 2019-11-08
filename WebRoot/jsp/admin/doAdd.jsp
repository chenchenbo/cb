<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="com.kgc.pojo.News"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@include file="../common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'doAdd.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<%
		//将新闻信息封装为一个新闻对象
		News news = new News();
		//接受增加的新闻信息，并调用后台方法，将新闻信息插入数据库
		request.setCharacterEncoding("utf-8");
		String fieldName = null;
		String uploadFileName = null;
		//解析请求之前先判断请求类型是否是文件上传类型
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		//指定上传位置
		String uploadFilePath = request.getSession().getServletContext().getRealPath("upload/");
		File saveDir = new File(uploadFilePath);
		//如果文件夹目录不存在，就创建目录
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}
		if (isMultipart) {
			//创建文件上传核心类
			//实例化一个硬盘文件工厂，用来配置上传组件ServletFileUpload
			FileItemFactory factory = new DiskFileItemFactory();
			//用上面的工厂实例化一个上传组件
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				//处理表单请求
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					//判断是否是普通的表单对象，如果是执行if否则文件上传执行else
					if (item.isFormField()) {
						fieldName = item.getFieldName();
						if (fieldName.equals("title")) {
							news.setTitle(item.getString("UTF-8"));
						} else if (fieldName.equals("author")) {
							news.setAuthor(item.getString("UTF-8"));
						} else if (fieldName.equals("categoryId")) {
							news.setCategoryId(Integer.parseInt(item.getString("UTF-8")));
						} else if (fieldName.equals("summary")) {
							news.setSummary(item.getString("UTF-8"));
						} else if (fieldName.equals("newscontent")) {
							news.setContent(item.getString("UTF-8"));
						}
					} else {//如果是文件类型
						String fileName = item.getName();//获得文件名
						if (!fileName.equals("") || fileName != null) {
							//通过文件名创建文件对象
							File fullFile = new File(fileName);
							//将文件保存在指定路径下
							File saveFile = new File(uploadFilePath, fullFile.getName());
							//将上传的文件保存在指定的路径中
							item.write(saveFile);
							uploadFileName = item.getName();
							news.setPicPath(uploadFileName);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		news.setCreateDate(new Date());

		//调用后台方法，将新闻信息插入数据库
		boolean flag = newsService.add(news);
		if (flag) {
	%>
	<jsp:forward page="newsDetailList.jsp" />
	<%
		//request.getRequestDispatcher("newsDetailList.jsp").forward(request, response);
		}
	%>
</body>
</html>
