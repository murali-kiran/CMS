<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

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
<td>MediaProvider</td>
<td><div class="styled-select">
<form:select path="mediaProviderId" id="mediaProviderId" class="validate">
<form:option value="-1" label="--- Select ---"/>
<form:options items="${mediaProviderList}" itemValue="mediaProviderId" itemLabel="mediaProviderName"/>
</form:select></div>
</td>
</tr>

<tr>
<td>Media StartTime  </td>
<td><form:input path="mediaStartTime" class="datepicker calender validate" /></td>
</tr>

<tr>
<td>Media EndTime  </td>
<td><form:input path="mediaEndTime" class="datepicker calender validate" /></td>
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

<c:choose>
    <c:when test="${empty mediaList}">
    	No media found. Please search
    </c:when>
    <c:otherwise> 
<div class="panel-header panel-title">Media List</div>
<%-- <table border="1">
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
</table> --%>

<%-- <display:table id="user" name="sessionScope.mediaList" defaultsort="1" defaultorder="ascending" pagesize="2">
    <display:column property="mediaId" sortable="true" title="Employee ID"
        maxLength="25" />
    <display:column property="mediaName" sortable="true" title="Real Name"
        maxLength="25" />
    <display:column property="mediaTitle" sortable="true"
        title="Email Address" maxLength="25" />
    <display:column property="description" sortable="true" title="Phone"
        maxLength="25" />
    <display:setProperty name="basic.empty.showtable" value="true" />
    <display:setProperty name="paging.banner.group_size" value="10" />
    <display:setProperty name="paging.banner.item_name" value="user" />
    <display:setProperty name="paging.banner.item_names" value="users" />
    <display:setProperty name="paging.banner.onepage" value="<span class="pagelinks">&nbsp;</span>" />
</display:table> --%>




<display:table style="border: 1px solid #333333;text-align:left;" pagesize="20" name="mediaList" id="media" requestURI="${pageContext.servletContext.contextPath}/searchMediaPaging">
  <display:column style="border: 1px solid #333333;" sortable="true" title="ID"> <c:out value="${media.mediaId}"/> </display:column>
  <display:column style="border: 1px solid #333333;" property="mediaTitle" autolink="true"/>
  <display:column style="border: 1px solid #333333;" property="mediaCycle.mediaCycleState" title="State"/>
  <display:column style="border: 1px solid #333333;" property="language.languageName" title="Language Name"/>
  <display:column style="border: 1px solid #333333;" property="mediaType.mediaTypeName" title="Media Type"/>
  <display:column style="border: 1px solid #333333;" property="description" title="Comments"/>
  <display:column style="border: 1px solid #333333;"><a href="editMedia?mediaId=<c:out value="${media.mediaId}" />">Edit </a></display:column>
  <display:column style="border: 1px solid #333333;"><a href="showMediaContent?mediaId=<c:out value="${media.mediaId}" />">MediaContents </a></display:column>
</display:table>
</c:otherwise>
</c:choose>
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
