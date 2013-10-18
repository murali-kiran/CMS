<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/script/climb3.js"></script>
<script>

 $(document).ready(function(){
	 

   
}); 

</script>
<div class="panel-header panel-title">Media List</div>
<table border="1">
<tr>
	<th>Media Id</th>
	<th>Media Name</th>
	<th>Media Title</th>
	<th>Media Cycle</th>
	<th>Media Language</th>
	<th>Media Type</th>
	<th>Media Description</th>
	<th> Edit </th>
</tr>
<c:forEach items="${mediaList}" var="media">
	<tr>
		<td><c:out value="${media.mediaId}" /></td>
		<td><c:out value="${media.mediaName}" /></td>
		<td><c:out value="${media.mediaTitle}" /></td>
		 <td><c:out value="${media.mediaCycle.mediaCycleState}" /></td> 
		 <td><c:out value="${media.language.languageName}" /></td>
		 <td><c:out value="${media.mediaType.mediaTypeName}" /></td>
		<td><c:out value="${media.description}" /></td>
		<td><a href="editMedia?mediaId=<c:out value="${media.mediaId}" />">Edit </a></td>
	</tr>
</c:forEach>
</table>