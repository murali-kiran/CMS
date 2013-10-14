
<HTML>
<HEAD>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/script/jquery-1.4.4.min.js"></script>
<TITLE>Menu</TITLE>
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
	font-size: 15px;
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
}
#accordion a {
	text-decoration: none;
	color: white;
}
#accordion a:hover {
	text-decoration: underline;
}

</STYLE>
</HEAD>
<BODY>

<ul id="accordion">
	<li>Upload</li>
	<ul>
		<li><a href="upload">Upload</a></li>
		<li><a href="#">List</a></li>
	</ul>
	<li>Media Group</li>
	<ul>
		<li><a href="addGroup">Create Group</a></li>
		<li><a href="listMediaGroup">Group List</a></li>
	</ul>
	
</ul>


</BODY>
<SCRIPT>
$("#accordion > li").click(function(){

	if(false == $(this).next().is(':visible')) {
		$('#accordion > ul').slideUp(300);
	}
	$(this).next().slideToggle(300);
});

//$('#accordion > ul:eq(0)').show();

</SCRIPT>
</HTML>