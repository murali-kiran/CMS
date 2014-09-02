<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="format-detection" content="telephone=no"/>
<meta http-equiv="x-rim-auto-match" content="none"/>
<meta http-equiv="x-ua-compatible" content="IE=Edge"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Cache-Control" content="no-cache" />
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<meta name="MobileOptimized" content="320" />
<meta name="HandheldFriendly" content="true" />


<title><tiles:insertAttribute name="title" ignore="true" /></title>
<c:if test="${isDownloadURL eq true}">
<meta http-equiv="Refresh" content="5;URL=${downloadURL}"/>
</c:if>

<c:if test="${isDownloadURL eq true}">

<script type="text/javascript">
   window.history.forward();
   function noBack() { window.history.forward(); }
</script>
</c:if>

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
<!--  <body style="background-color: #EEEEEE;">  -->
<c:choose>
	<c:when test="${isDownloadURL eq true}">
		<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
			onunload="">
	</c:when>
	<c:otherwise>
		<body>
	</c:otherwise>
</c:choose>
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