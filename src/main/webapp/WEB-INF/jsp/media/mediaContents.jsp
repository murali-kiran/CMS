<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/script/cms.js"></script>
<script>

 $(document).ready(function(){
	 

   
}); 

</script>
<div class="panel-header panel-title">Media Contents List</div>
<table border="1">
<tr>
	<th>Media Id</th>
	<th>Width</th>
	<th>Height</th>
	<th>Bitrate</th>
	<th>Download</th>
</tr>
<c:forEach items="${mediaContentList}" var="mediaContent">
	<tr>
		<td><c:out value="${mediaContent.media.mediaId}" /></td>
		<td><c:out value="${mediaContent.mediaSpecification.width}" /></td>
		<td><c:out value="${mediaContent.mediaSpecification.height}" /></td>
		 <td><c:out value="${mediaContent.mediaSpecification.bitrate}" /></td> 
		 <td><a href="<c:out value="${relativePath}" /><c:out value="${mediaContent.storagePath}" />">download</a></td>
	</tr>
</c:forEach>
</table>