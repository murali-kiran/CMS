<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<script src="${pageContext.servletContext.contextPath}/resources/script/jqgrid/gridJS/grid.locale-en.js"  type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath}/resources/script/jqgrid/gridJS/jquery.jqGrid.min.js" type="text/javascript"></script>


<script>

$(function(){

	try {
		
jQuery("#list").jqGrid({
		url : '${pageContext.request.contextPath}/serviceList',
		datatype: "json",
		height: 250,
		width: 1000,
		colNames:['Service Id','Service Name','Service Title','Service Description', 'Map Groups'],
		colModel :[	{name:'serviceId', label:'Service Id',index:'serviceId',align:'center'},
					{name:'serviceName',label:'Service Name', index:'serviceName',align:'center'},
					{name:'serviceTitle', label:'service Title',index:'serviceTitle',align:'center'},
					{name:'serviceDescription', label:'Service Description',index:'serviceDescription',align:'center'},
					{name:'mediaGroupDescription', label:'Group Desc',index:'mediaGroupDescription',align:'center'},
					
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
	<th>Service Id</th>
	<th>Service Name</th>
	<th>Service Title</th>
	<!-- <th> Map Groups </th> -->
</tr>
<c:forEach items="${serviceList}" var="service">
	<tr>
		<td><c:out value="${service.serviceId}" /></td>
		<td><c:out value="${service.serviceName}" /></td>
		<td><c:out value="${service.serviceTitle}" /></td>
		
		<%-- <td><a href="mapMediaCategories?serviceId=<c:out value="${service.serviceId}" />">Map Categories </a></td> --%>
	</tr>
</c:forEach>
</table>
</fieldset>

