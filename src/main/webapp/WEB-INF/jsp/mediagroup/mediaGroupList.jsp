<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 


<fieldset>
	<legend>Media Group List </legend>
	<table border="1">
		<tr>
			<th>Group Id</th>
			<th>Group Name</th>
			<th>Group Title</th>
			<th>Group Preview Id</th>
			<th>Group Desc</th>
			<th>Map Media</th>
		</tr>
		<c:forEach items="${groupList}" var="mediaGrp">
			<tr>
				<td><c:out value="${mediaGrp.mediaGroupId}" /></td>
				<td><c:out value="${mediaGrp.mediaGroupName}" /></td>
				<td><c:out value="${mediaGrp.mediaGroupTitle}" /></td>
				<td><c:out value="${mediaGrp.mediaGroupPreviewId}" /></td>
				<td><c:out value="${mediaGrp.mediaGroupDescription}" /></td>
				<td><a href="showSearchMap?mgid=${mediaGrp.mediaGroupId}">mapMedia</a></td>
			</tr>
		</c:forEach>
	</table>
</fieldset>

