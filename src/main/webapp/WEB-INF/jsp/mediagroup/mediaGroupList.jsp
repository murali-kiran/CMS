<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 

<c:out value="${message}"></c:out>
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
			<th>Map Group</th>
		</tr>
		<c:forEach items="${groupList}" var="mediaGrp">
			<tr>
				<td><c:out value="${mediaGrp.mediaGroupId}" /></td>
				<td><c:out value="${mediaGrp.mediaGroupName}" /></td>
				<td><c:out value="${mediaGrp.mediaGroupTitle}" /></td>
				<td><c:out value="${mediaGrp.mediaGroupPreviewId}" /></td>
				<td><c:out value="${mediaGrp.mediaGroupDescription}" /></td>
				<td><a href="showSearchMap?mgid=${mediaGrp.mediaGroupId}">Map Media</a></td>
				<td><a href="showMappedGroup?mgid=${mediaGrp.mediaGroupId}">Map Group</a></td>
			</tr>
		</c:forEach>
	</table>
</fieldset>

