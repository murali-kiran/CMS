<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/script/cms.js"></script>
<script>

 $(document).ready(function(){
	 

 
  
   
}); 

</script>
<div style="width: 100%;display: block;border: 1px solid #95B8E7;">
<div class="panel-header panel-title">Upload</div>
<form:form method="post" id="feedbackform" name="feedbackform"  commandName="uploadFile"
                           action="${pageContext.servletContext.contextPath}/save" enctype="multipart/form-data">

<table>
<tr><td style="width: 50%;vertical-align: top;">
<table>


<tr>
<td>MediaType </td>
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

<tr>
<td>Tags  </td>
<td><form:textarea path="tags" /></td>
<td><form:errors path="tags" cssClass="error" /></td>
</tr>

<tr><td>Media StartTime  </td>
<td><form:input path="mediaStartTime" class="datepicker calender validate" /></td>
<td><form:errors path="mediaStartTime" cssClass="error" /></td></tr>

<tr><td>Media EndTime  </td>
<td><form:input path="mediaEndTime" class="datepicker calender validate" /></td>
<td><form:errors path="mediaEndTime" cssClass="error" /></tr>
<c:if test="${ empty mediaId}">
  <form:hidden  path="mediaId" />
</c:if>
</table>
</td>

</tr>
</table>

 <table>
	<c:forEach items="${appList}" var="fileContent" varStatus="fileStatus">
		 <tr>
			<td>file :</td>
			<td>
			<form:input type="file" path="fileContent[${fileContent.id}].file" /></td>
			<td><form:errors path="mediaContentModelList[${fileContent.id}].file" cssClass="error" /></td>
			
			<form:hidden path="fileContent[fileStatus].description"  />
			
		</tr>
	</c:forEach>
</table> 

<div><input type="submit"/></div>



</form:form>

</div>--%>

hi