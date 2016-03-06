<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<script src="${pageContext.servletContext.contextPath}/resources/script/jqgrid/gridJS/grid.locale-en.js"  type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath}/resources/script/jqgrid/gridJS/jquery.jqGrid.min.js" type="text/javascript"></script>


<script>

$(function(){

	try {
		
jQuery("#list").jqGrid({
		url : '${pageContext.request.contextPath}/serviceKeyList',
		datatype: "json",
		height: 250,
		width: 1000,
		colNames:['Service Key Id','Service Key Name','Service Key Title','Service Description', 'Map Groups'],
		colModel :[	{name:'serviceKeyId', label:'Service Key Id',index:'serviceKeyId',align:'center'},
					{name:'serviceKeyName',label:'Service Key Name', index:'serviceKeyName',align:'center'},
					{name:'serviceKeyTitle', label:'Service Key Title',index:'serviceKeyTitle',align:'center'},
					{name:'serviceKeyDescription', label:'Service Description',index:'serviceKeyDescription',align:'center'},
					
					
					{name:'mapGroup',label:'Map Group', index:'mapGroup',align:'center',formatter: function (cellvalue, options, rowObject) {
					    return "<a href=\"showMappedGroup?mgid=" + rowObject.mediaGroupId + "\" style=\"color: #0000FF\" > map group </a>";
					}} ],
		paging: true,
	    rowNum:20,
		rowList:[10,20,30],
	    pager: "#page",
        caption: "Services List", 
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
<br/>
<c:out value="${message}"></c:out>
<br/>
<fieldset>
	<legend>Service List </legend>
<!--  <div id="jQGrid" ><table id="list" class=".ui-jqgrid"></table><div id="page"></div></div>-->
<table border="1">
<tr>
	<th>Service Key Id</th>
	<th>Service Key Name</th>
	<th>Service Key Title</th>
	<th> Map Service Key Price </th>
	<!-- <th> Map Packages </th> -->
</tr>
<c:forEach items="${serviceKeyList}" var="serviceKey">
	<tr>
		<td><c:out value="${serviceKey.serviceKeyId}" /></td>
		<td><c:out value="${serviceKey.serviceKeyName}" /></td>
		<td><c:out value="${serviceKey.serviceKeyTitle}" /></td>
		<td><a href="mapPackagePrices?serviceKeyId=<c:out value="${serviceKey.serviceKeyId}" />">Map Package Prices </a></td>
		<!-- <td><a href="mapPackages?serviceKeyId=<c:out value="${serviceKey.serviceKeyId}" />">Map Packages </a></td> -->
	</tr>
</c:forEach>
</table>
</fieldset>

