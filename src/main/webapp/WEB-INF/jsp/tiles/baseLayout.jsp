<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/climb3.css">
	
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
<body style="border-collapse: collapse;">
<%-- <table style="width: 100%;">
      <tr>
			<td colspan="4"><tiles:insertAttribute name="header" /></td>
	  </tr> 
       <tr><td><tiles:insertAttribute name="menu" /></td>
       <td><tiles:insertAttribute name="body" /></td></tr>
       
       <tr><td colspan="4"><tiles:insertAttribute name="footer" /></td></tr>
</table> --%>

<div style="width: 100%;height: auto;border: 0px;border-collapse: collapse;">
<%-- <div style="width: 100%;height: 150px;background-image: url('/CMS/resources/images/headbg.jpg');width: 100%;background-repeat: repeat-x;border-collapse: collapse;">
	<tiles:insertAttribute name="header" />
</div> --%>
<div style="width: 100%;height: 150px;float: left;background-image: url('/CMS/resources/images/headbg1.jpg');width: 100%;background-repeat: repeat-x;
   left:0px;
   top:0px;">
	<tiles:insertAttribute name="header" />
</div>
<div style="width: 100%;height: 70%;float: left;background-color: #B467CF;padding-top: 0px;border-collapse: collapse;padding-bottom: 20px;">
	<div style="width: 18%;height:auto; text-align: left;float: left;background-color:#B467CF;padding-left: 5px;"><tiles:insertAttribute name="menu" /></div>
	<div style="width: 80%;height:auto;text-align: left;float: right;padding-top: 20px;"><tiles:insertAttribute name="body" /></div>
</div>
<div style="width: 100%;height: 20px;float: left;background-image: url('/CMS/resources/images/headbg1.jpg');width: 100%;background-repeat: repeat-x;position:fixed;
   left:0px;
   bottom:0px;">
	<tiles:insertAttribute name="footer" />
</div>
</div>
</body>
</html>
