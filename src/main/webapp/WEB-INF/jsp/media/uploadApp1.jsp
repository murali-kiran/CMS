<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/script/cms.js"></script>
<script type="text/javascript">
var x=1;
function add(){
	//alert("hi");
	
	var k=x-1;
	var $curRow = $('#apptr'+k);
    var $newRow = $curRow.clone(true).attr('id','apptr'+x);
    $newRow.find('#heightjs'+k).attr('id','heightjs'+x);
    $newRow.find('#heightjs'+x).attr('name','mediaContentModelList['+x+'].height');
    
    $newRow.find('#widthjs'+k).attr('id','widthjs'+x);
    $newRow.find('#widthjs'+x).attr('name','mediaContentModelList['+x+'].width');
    
    $newRow.find('#mimejs'+k).attr('id','mimejs'+x);
    $newRow.find('#mimejs'+x).attr('name','mediaContentModelList['+x+'].mimeType');
    
    $newRow.find('#osjs'+k).attr('id','osjs'+x);
    $newRow.find('#osjs'+x).attr('name','mediaContentModelList['+x+'].osid');
    
    $newRow.find('#filejs'+k).attr('id','filejs'+x);
    $newRow.find('#filejs'+x).attr('name','mediaContentModelList['+x+'].file');
    
    $newRow.find('#desjs'+k).attr('id','desjs'+x);
    $newRow.find('#desjs'+x).attr('name','mediaContentModelList['+x+'].duration');//reused for description
    
    $newRow.find('#purjs'+k).attr('id','purjs'+x);
    $newRow.find('#purjs'+x).attr('name','mediaContentModelList['+x+'].purpose');
       
    $('#apptr'+k).after($newRow);
    x++;
}

</script>
<div style="width: 100%;display: block;border: 1px solid #95B8E7;">
<div class="panel-header panel-title">Upload</div>
<form:form method="post" id="feedbackform" name="feedbackform"  commandName="uploadFile"
                           action="${pageContext.servletContext.contextPath}/saveapp" enctype="multipart/form-data">
<c:out value="${mediaContentModelList}"/>
<table>
<tr><td style="width: 50%;vertical-align: top;">
<table>
<tr>
<td width="20%;">MediaProvider</td>
<td ><div class="styled-select">
<form:select path="mediaProviderId" id="mediaProviderId" class="validate">
<form:option value="-1" label="--- Select ---"/>
<form:options items="${mediaProviderList}" itemValue="mediaProviderId" itemLabel="mediaProviderName"/>
</form:select></div>
</td>
<td><form:errors path="mediaTypeId" cssClass="error" /></td>
</tr>

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

<%-- <tr><td> Media TranscodeNow </td>
<td><form:checkbox path="transcodeNow" /></td>
<td><form:errors path="transcodeNow" cssClass="error"/></td></tr> --%>

<c:if test="${ empty mediaId}">
  <form:hidden  path="mediaId" />
</c:if>
</table>
</td>

</tr>
</table>
<fieldset>
<legend>Game Files Upload</legend>
 <table>
 
   <c:set var="mediaContentModelLst" scope="request" value="${uploadFile.mediaContentModelList}" />
   
	  <c:forEach items="${mediaContentModelLst}" var="fileContent" varStatus="fileStatus">
	  
			 <tr id="apptr0"> 
			 <td style="font-family: verdana;">
			 <pre>
				Width    : <form:input id="widthjs0" type="text" path="mediaContentModelList[0].width" /> <br/>
				Height   : <form:input id="heightjs0" type="text" path="mediaContentModelList[0].height" /><br/>
				Purpose  : <select id="purjs0" name="mediaContentModelList[0].purpose"><option value="Preview">Preview</option><option value="Game">Application</option></select><br>
				Mime Type: <select id="mimejs0" name="mediaContentModelList[0].mimeType"><c:forEach items="${mimeTypeList}" var="mime"> <option value="${mime.mimeTypeId}">${mime.mimeType}</option></c:forEach></select><br>
				OS Type  : <select id="osjs0" name="mediaContentModelList[0].osid"><c:forEach items="${osList}" var="os" ><option value="${os.osId}">${os.osName}</option></c:forEach></select>	<br>
				File     : <form:input id="filejs0" type="file" path="mediaContentModelList[0].file" /> </br>
				Description: <form:input id="desjs0" type="text" path="mediaContentModelList[0].duration" /> <br/>
			</pre>	
			</td>	
			</tr>
			</c:forEach>
			
</table> 
</fieldset>
<div><input type="button" onclick="add()" value="ADD"></div>
<div><input type="submit"/></div>



</form:form>



</div>
