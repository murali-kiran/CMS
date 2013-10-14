<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<form:form method="post" id="feedbackform" name="feedbackform"  commandName="mediaList"
                           action="${pageContext.servletContext.contextPath}/saveMappedMedia" >
<table border="1">
<tr>
	<th>Media Id</th>
	<th>Media Name</th>
	<th>Media Title</th>
	<th>Media Cycle State</th>
	<th>Media Description</th>
	<th>status</th>
	
</tr>
<c:forEach items="${mediaList}" var="media">
	<tr>
		<td><c:out value="${media.mediaId}" /></td>
		<td><c:out value="${media.mediaName}" /></td>
		<td><c:out value="${media.mediaTitle}" /></td>
		<td><c:out value="${media.mediaCycle.mediaCycleState}" /></td>
		<td><c:out value="${media.description}" /></td>
		<td>
		<c:choose>
		  <c:when test="${media.checkStatus}"><input type="checkbox" name="selectedMedia" value="${media.mediaId}" checked="${media.checkStatus}"/></c:when>
		  <c:otherwise><input type="checkbox" name="selectedMedia" value="${media.mediaId}"/></c:otherwise>
		</c:choose>
		<input type="hidden" name="mgid" value="${media.mgid}"/>
		</td>
		
	</tr>
</c:forEach>
</table>
<div><input type="submit"/></div>
</form:form>

