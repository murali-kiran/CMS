<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html > 
	<head>
		<meta http-equiv="Cache-Control" content="no-cache"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/> 
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
		<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/idea.css"/>
	</head>
	<body style="background-color: #EEEEEE;"> 
		<table style="border: 0px;padding: 0px;width: 100%;border-spacing: 0px;">
			<tr>
				<td>
					<tiles:insertAttribute name="header" />
				</td>
			</tr>
			<tr>
				<td>
					<tiles:insertAttribute name="body" />
				</td>
			</tr>
			<tr >
				<td>
					<tiles:insertAttribute name="footer" />
				</td>
			</tr>
		</table>
		</body>
	
</html>