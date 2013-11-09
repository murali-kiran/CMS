<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<script src="${pageContext.servletContext.contextPath}/resources/script/jqgrid/gridJS/grid.locale-en.js"  type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath}/resources/script/jqgrid/gridJS/jquery.jqGrid.min.js" type="text/javascript"></script>


<script>

$(function(){

	try {
		
jQuery("#list").jqGrid({
		url : '${pageContext.request.contextPath}/listMediaGroupAjaxCopy',
		datatype: "json",
		height: 250,
		width: 1000,
		colNames:['Group Id','Group Name','Group Title','Group Preview Id','Group Desc','Map Media','Map Group'],
		colModel :[	{name:'mediaGroupId', label:'Group Id',index:'mediaGroupId',align:'center'},
					{name:'mediaGroupName',label:'Group Name', index:'mediaGroupName',align:'center'},
					{name:'mediaGroupTitle', label:'Group Title',index:'mediaGroupTitle',align:'center'},
					{name:'mediaGroupPreviewId', label:'Group Preview Id',index:'mediaGroupPreviewId',align:'center'},
					{name:'mediaGroupDescription', label:'Group Desc',index:'mediaGroupDescription',align:'center'},
					{name:'mapMedia', label:'Map Media',index:'mapMedia',align:'center',formatter: function (cellvalue, options, rowObject) {
					    return "<a href=\"showSearchMap?mgid=" + rowObject.mediaGroupId + "\" style=\"color: #0000FF\" > map media </a>";
					}},
					{name:'mapGroup',label:'Map Group', index:'mapGroup',align:'center',formatter: function (cellvalue, options, rowObject) {
					    return "<a href=\"showMappedGroup?mgid=" + rowObject.mediaGroupId + "\" style=\"color: #0000FF\" > map group </a>";
					}} ],
		paging: true,
	    rowNum:2,
		rowList:[10,20,30],
	    pager: "#page",
        caption: "Media Group List", 
	    sortable: true,
        viewrecords: true,
        jsonReader : { 
        rows: "rows", 
        page: "page", 
        total: "total", 
        repeatitems: false,
        id:"0"
       
      }
				        
});

	} catch (e) {
		alert(e);
	}

});

</script>

<fieldset>
	<legend>Media Group List </legend>
<div id="jQGrid" ><table id="list" class=".ui-jqgrid"></table><div id="page"></div></div>
</fieldset>

