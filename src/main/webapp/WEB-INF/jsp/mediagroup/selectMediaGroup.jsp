<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:choose>
    <c:when test="${empty mediaGroupList}">
    	No media found. Please search and add media
    </c:when>
    <c:otherwise> 
<form:form method="post" id="feedbackform" name="feedbackform"  commandName="mediaGroupList"
                           action="${pageContext.servletContext.contextPath}/remAddOrderMedia" >
<table border="1" id="sort" class="grid">
<thead>
<tr>
	
	<th class="index">Order</th>
	<th>Media Group Id</th>
	<th>Media Group Name</th>
	<th>Media Group Title</th>
	<th>status</th>
	
</tr>
</thead>
<tbody>
<c:forEach items="${mediaGroupList}" var="mediaGroup" varStatus="status">
	<tr>
		
		<td class="index"> <c:out value="${status.count}"/>  </td>
		<td><c:out value="${mediaGroup.mediaGroupId}" /></td>
		<td><c:out value="${mediaGroup.mediaGroupName}" /></td>
		<td><c:out value="${mediaGroup.mediaGroupTitle}" /></td>
		<%-- <td><c:out value="${mediaGroup.mediaCycle.mediaCycleState}" /></td>
		<td><c:out value="${mediaGroup.description}" /></td> --%>
		<td>
		<c:choose>
		  <c:when test="${mediaGroup.checkStatus}"><input type="checkbox" name="selectedMediaGroup" value="${mediaGroup.mediaGroupId}" checked="${mediaGroup.checkStatus}"/></c:when>
		  <c:otherwise><input type="checkbox" name="selectedMedia" value="${mediaGroup.mediaGroupId}"/></c:otherwise>
		</c:choose>
		<input type="hidden" name="parentmgid" value="${mediaGroup.parentmgId}"/>
		</td>
		
	</tr>
</c:forEach>
</tbody>
</table>
<div><input type="submit" value="Remove Or Save Order"/></div>
</form:form>
</c:otherwise>
</c:choose>

<br/>
<a href="showGroupSearch?mgid=${mgid}">Search Media Group To Add</a>
<br/>
<c:choose>
    <c:when test="${empty remMediaGroupList}">
    	
    </c:when>
    <c:otherwise>
<form:form method="post" id="feedbackform" name="feedbackform"  commandName="remMediaList"
                           action="${pageContext.servletContext.contextPath}/saveMappedMediaGroup" >
                           

<table border="1">
<tr>
	<th class="index">Order</th>
	<th>Media Group Id</th>
	<th>Media Group Name</th>
	<th>Media Group Title</th>
	<th>status</th>
	
</tr>
<c:forEach items="${remMediaGroupList}" var="mediaGroup" varStatus="status">
	<tr>
		
		<td class="index"> <c:out value="${status.count}"/>  </td>
		<td><c:out value="${mediaGroup.mediaGroupId}" /></td>
		<td><c:out value="${mediaGroup.mediaGroupName}" /></td>
		<td><c:out value="${mediaGroup.mediaGroupTitle}" /></td>
		<%-- <td><c:out value="${mediaGroup.mediaCycle.mediaCycleState}" /></td>
		<td><c:out value="${mediaGroup.description}" /></td> --%>
		<td>
		<c:choose>
		  <c:when test="${mediaGroup.checkStatus}"><input type="checkbox" name="selectedMediaGroup" value="${mediaGroup.mediaGroupId}" checked="${mediaGroup.checkStatus}"/></c:when>
		  <c:otherwise><input type="checkbox" name="selectedMedia" value="${mediaGroup.mediaGroupId}"/></c:otherwise>
		</c:choose>
		<input type="hidden" name="parentmgid" value="${mediaGroup.parentmgId}"/>
		</td>
		
	</tr>
</c:forEach>
</table>
<div><input type="submit" value="Add"/></div>
</form:form>
</c:otherwise>
</c:choose> 