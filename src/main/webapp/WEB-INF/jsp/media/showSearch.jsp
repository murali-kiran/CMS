<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/script/cms.js"></script>
<script>

 $(document).ready(function(){
	 

   
}); 

</script>
<% String s = request.getParameter("type");
//String action = "/searchMedia";

%>
<c:out value="${message}"></c:out>
<div style="width: 100%;display: block;border: 1px solid #95B8E7;">
<div class="panel-header panel-title">Search</div>
<%if(s == null /* && !s.equalsIgnoreCase("mapping") */){ ;%>
<form:form method="post" id="feedbackform" name="feedbackform"  commandName="searchMedia"
                           action="${pageContext.servletContext.contextPath}/searchMedia" escape="false" enctype="multipart/form-data">

<table>
<tr><td style="width: 50%;vertical-align: top;">
<table>


<tr>
<td>MediaType</td>
<td><div class="styled-select">
<form:select path="mediaTypeId" id="mediaTypeId" class="validate">
<form:option value="-1" label="--- Select ---"/>
<form:options items="${mediaTypeList}" itemValue="mediaTypeId" itemLabel="mediaTypeTitle"/>
</form:select></div>
</td>
<td><form:errors path="mediaTypeId" cssClass="error" /></td>
</tr>

<tr>
<td>Media Title </td>
<td><form:input path="mediaTitle" class="validate" /></td>
<td><form:errors path="mediaTitle" cssClass="error" /></td>
</tr>

<tr>
<td>Media Name<br>(for internal purpose) </td>
<td><form:input path="mediaName" class="validate"/></td>
<td><form:errors path="mediaName" cssClass="error" /></td>
</tr>

<tr>
<td>Media State </td>
<td><div class="styled-select">
<form:select path="mediaCycleId" id="mediaCycleId" class="validate">
<form:option value="-1" label="--- Select ---"/>
<form:options items="${mediaCycleList}" itemValue="mediaCycleId" itemLabel="mediaCycleState"/>
</form:select></div>
</td>
<td><form:errors path="mediaCycleId" cssClass="error" /></td>
</tr>

<tr>
<td>Language </td>
<td><div class="styled-select">
<form:select path="languageId" id="languageId" class="validate">
<form:option value="-1" label="--- Select ---"/>
<form:options items="${languageList}" itemValue="languageId" itemLabel="languageName"/>
</form:select></div>
</td>
<td><form:errors path="languageId" cssClass="error" /></td>
</tr>

<tr>
<td>Description   </td>
<td><form:textarea path="description" /></td>
<td><form:errors path="description" cssClass="error" /></td>
</tr>

</table>
</td>
<td  style="width: 50%;vertical-align: top;">

<table>
<tr><td id="fileTable">
</td></tr>

</table>
</td>
</tr>
</table>
<div><input type="submit"/></div>
</form:form>
<% }else{;%>

<form:form method="post" id="feedbackform" name="feedbackform"  commandName="searchMedia"
            action="${pageContext.servletContext.contextPath}/showRemMedia" escape="false" enctype="multipart/form-data">
<table>
<tr><td style="width: 50%;vertical-align: top;">
<table>


<tr>
<td>MediaType <input type="hidden" name="mgid" id="mgid" value="<%=request.getParameter("mgid")%>"/></td>
<td><div class="styled-select">
<form:select path="mediaTypeId" id="mediaTypeId" class="validate">
<form:option value="-1" label="--- Select ---"/>
<form:options items="${mediaTypeList}" itemValue="mediaTypeId" itemLabel="mediaTypeTitle"/>
</form:select></div>
</td>
<td><form:errors path="mediaTypeId" cssClass="error" /></td>
</tr>

<tr>
<td>Media Title </td>
<td><form:input path="mediaTitle" class="validate" /></td>
<td><form:errors path="mediaTitle" cssClass="error" /></td>
</tr>

<tr>
<td>Media Name<br>(for internal purpose) </td>
<td><form:input path="mediaName" class="validate"/></td>
<td><form:errors path="mediaName" cssClass="error" /></td>
</tr>

<tr>
<td>Media State </td>
<td><div class="styled-select">
<form:select path="mediaCycleId" id="mediaCycleId" class="validate">
<form:option value="-1" label="--- Select ---"/>
<form:options items="${mediaCycleList}" itemValue="mediaCycleId" itemLabel="mediaCycleState"/>
</form:select></div>
</td>
<td><form:errors path="mediaCycleId" cssClass="error" /></td>
</tr>

<tr>
<td>Language </td>
<td><div class="styled-select">
<form:select path="languageId" id="languageId" class="validate">
<form:option value="-1" label="--- Select ---"/>
<form:options items="${languageList}" itemValue="languageId" itemLabel="languageName"/>
</form:select></div>
</td>
<td><form:errors path="languageId" cssClass="error" /></td>
</tr>

<tr>
<td>Description   </td>
<td><form:textarea path="description" /></td>
<td><form:errors path="description" cssClass="error" /></td>
</tr>

</table>
</td>
<td  style="width: 50%;vertical-align: top;">

<table>
<tr><td id="fileTable">
</td></tr>

</table>
</td>
</tr>
</table>
<div><input type="submit"/></div>
</form:form>            
	
<%};%>
</div>
<c:choose>
    <c:when test="${empty mediaList}">
    	No media found. Please search
    </c:when>
    <c:otherwise> 
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
	<th> Edit Content </th>
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
		<td><a href="showMediaContent?mediaId=<c:out value="${media.mediaId}" />">MediaContents </a></td>
	</tr>
</c:forEach>
</table>
</c:otherwise>
</c:choose>