<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<style>
body,p,td,div,span,a {
	font-size: 12px;
	font-weight:bold;
	margin: 0;
	padding: 0;
	/* text-transform: lowercase; */
	text-decoration: none;
	font:CALIBRI;
}
</style>
</head>
<body style="background-color: #EEEEEE;font-family: verdana;">
	<table
		style="border: 0px; padding: 0px; width: 100%; border-spacing: 0px; border-collapse: collapse;">
		<tr>
			<td><tiles:insertAttribute name="header" /></td>
		</tr>
		<tr>
			<td><tiles:insertAttribute name="body" /></td>
		</tr>
		<tr>
			<td><tiles:insertAttribute name="footer" /></td>
		</tr>
	</table>
</body>

</html>