<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

  
<meta http-equiv="Cache-Control" content="no-cache"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<STYLE>
body, input{
	font-family: Calibri, Arial;
}
#accordion {
	list-style: none;
	padding: 0 0 0 0;
	width: 170px;
}
#accordion li{
	display: block;
	background-color: #371746;
	font-weight: bold;
	margin: 1px;
	cursor: pointer;
	padding: 5 5 5 7px;
	list-style: circle;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
	border-radius: 10px;
	color : white;
	font-size: 16px;
}
#accordion ul {
	list-style: none;
	padding: 0 0 0 0;
	display: none;
}
#accordion ul li{
	font-weight: normal;
	cursor: auto;
	background-color: #662E85;
	padding: 0 0 0 7px;
	color : white;
	font-size: 14px;
}
#accordion a {
	text-decoration: none;
	color: white;
}
#accordion a:hover {
	text-decoration: underline;
}

</STYLE>

<title><tiles:insertAttribute name="title" ignore="true" /></title>

<link href="<%=request.getContextPath()%>/resources/css_new/templatemo_style.css" rel="stylesheet" type="text/css" />

<link href="<%=request.getContextPath()%>/resources/css_new/svwp_style.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/resources/js_new/jquery-1.3.2.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/resources/js_new/jquery.slideViewerPro.1.0.js" type="text/javascript"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/cms.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/jquery-ui.css" />
	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/demo.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/themes/demo.css">
	<link href="${pageContext.servletContext.contextPath}/resources/script/jqgrid/gridJS/ui.jqgrid.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.servletContext.contextPath}/resources/script/jqgrid/gridJS/jquery-ui-1.10.1.custom.css" rel="stylesheet" type="text/css"  />
	
	 
	
	 <script type="text/javascript" src="<%=request.getContextPath()%>/resources/script/jquery-1.10.2.js"></script> 
     <script src="<%=request.getContextPath()%>/resources/script/jquery-ui.js"></script>
     
     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/formwizard.css" />
	<script type='text/javascript' src='<%=request.getContextPath()%>/resources/script/jquery-1.4.4.min.js'></script>
	<script src="<%=request.getContextPath()%>/resources/script/formwizard.js" type="text/javascript"></script>
	
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
<script type="text/javascript">
//<![CDATA[ 
$(window).load(function(){
var fixHelperModified = function(e, tr) {
    var $originals = tr.children();
    var $helper = tr.clone();
    $helper.children().each(function(index) {
        $(this).width($originals.eq(index).width())
    });
    return $helper;
},
    updateIndex = function(e, ui) {
        $('td.index', ui.item.parent()).each(function (i) {
            $(this).html(i + 1);
        });
    };

$("#sort tbody").sortable({
    helper: fixHelperModified,
    stop: updateIndex
}).disableSelection();
});//]]>  
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

<div style="width: 100%;border: 0px;border-collapse: collapse;">
<%-- <div style="width: 100%;height: 150px;background-image: url('/CMS/resources/images/headbg.jpg');width: 100%;background-repeat: repeat-x;border-collapse: collapse;">
	<tiles:insertAttribute name="header" />
</div> --%>
<div >
	<tiles:insertAttribute name="header" />
</div>
<div style="width: 100%;height: 70%;background-color: #F5F5F5;padding-top: 0px;border-collapse: collapse;padding-bottom: 20px;" align="center">
	<%-- <div style="width: 18%;height:auto; text-align: left;float: left;background-color:#B467CF;padding-left: 5px;"><tiles:insertAttribute name="menu" /></div> --%>
	<div style="width: 70%;padding-top: 20px;" align="center"><tiles:insertAttribute name="body" /></div>
</div>
<div style="width: 100%;height: 20px;float: left;background-image: url('<%=request.getContextPath()%>/resources/images/headbg1.jpg');width: 100%;background-repeat: repeat-x;position:fixed;
   left:0px;
   bottom:0px;">
	<tiles:insertAttribute name="footer" />
</div>
</div>
</body>
</html>
