

<ul id="accordion">
	<li>Upload</li>
	<ul>
		<li><a href="upload">Upload</a></li>
		<li><a href="appUpload">Game Upload</a></li>
		<li><a href="showSearch">List</a></li>
	</ul>
	<li>Media Group</li>
	<ul>
		<li><a href="addGroup">Create Group</a></li>
		<li><a href="listMediaGroup">Group List</a></li>
	</ul>
	<li>Mis</li>
	<ul>
		<li><a href="mis/show">Mis</a></li>
	</ul>
</ul>


<SCRIPT>
$("#accordion > li").click(function(){

	if(false == $(this).next().is(':visible')) {
		$('#accordion > ul').slideUp(300);
	}
	$(this).next().slideToggle(300);
});

//$('#accordion > ul:eq(0)').show();

</SCRIPT>
