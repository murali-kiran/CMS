<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/climb3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/demo.css">
	
	<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/resources/script/climb3.js"></script> --%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/resources/script/jquery-1.10.2.js"></script> 
     <script src="<%=request.getContextPath()%>/resources/script/jquery-ui.js"></script>
     
     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/formwizard.css" />
	<script type='text/javascript' src='http://code.jquery.com/jquery-1.4.4.min.js'></script>
	<script src="<%=request.getContextPath()%>/resources/script/formwizard.js" type="text/javascript"></script>
    <script type="text/javascript">
  /* $(function() {
    $( ".datepicker" ).datepicker();
  }); */
  
  
	var myform = new formtowizard({
		formid : 'feedbackform',
		persistsection : true,
		revealfx : [ 'slide', 500 ]
	})
	
</script>

</head>
<body>
<table style="width: 100%;">
      <tr>
			<td colspan="4"><tiles:insertAttribute name="header" /></td>
	  </tr> 
       <tr><td><tiles:insertAttribute name="menu" /></td>
       <td><tiles:insertAttribute name="body" /></td></tr>
       
       <tr><td colspan="4"><tiles:insertAttribute name="footer" /></td></tr>
</table>
</body>
</html>
