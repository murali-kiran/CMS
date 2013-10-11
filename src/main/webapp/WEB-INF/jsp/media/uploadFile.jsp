<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/script/climb3.js"></script>
<script>

 $(document).ready(function(){
	 

  $("#contentTypeId , #isRemote").change(function(){
	  		    
	    /* $("#fileTable").find("tr").remove(); */
	    $("#fileTable").empty();
	      var cpid = $('#contentProviderId').val();
		  var cpoid = $('#copyrightOwnerId').val();
		  var ctype = $('#contentTypeId').val();
		  
		  if(cpid == null || cpid == -1){
			  alert('Please select ContentProvider'); return;  
		  }
		  
		  if(cpoid == null || cpoid == -1){
			  alert('Please select CopyrightOwner');return;
			   }
		  
		  if(ctype == null || ctype == -1){
			  alert('Please select ContentType');return;
		  }
		  var isRemote = document.getElementById('isRemote').checked; 
		 
		  $("#fileTable").html("<div align='center'>loading<div>");
		  
		  var data =  $.ajax({
	             url: "/CLIMB3/getFiles?contentProviderId=" + cpid + "&copyrightOwnerId=" + cpoid+ "&contentTypeId=" + ctype+ "&isRemote=" + isRemote,
	             type: "GET",
	             async: false
	           }).responseText;
		           $('#fileTable').html(data); 
	    
	  });
  
 
  $("#isLang").change(function(){
	   var isLang = document.getElementById('isLang').checked;
	  
	  if(isLang == true){
		  
		  var data =  $.ajax({
	             url: "/CLIMB3/getLanguages",
	             type: "GET",
	             async: false
	           }).responseText;
		           $('#lang').html(data); 
	    
	  }else{
		  $("#lang").empty();
	  }		 
	  });
  
  
   
  
  
  
  
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
<td>MediaType : </td>
<td><div class="styled-select">
<form:select path="mediaTypeId" id="mediaTypeId" class="validate">
<form:option value="-1" label="--- Select ---"/>
<form:options items="${mediaTypeList}" itemValue="mediaTypeId" itemLabel="mediaTypeTitle"/>
</form:select></div>
</td>
<td><form:errors path="mediaTypeId" cssClass="error" /></td>
</tr>

<tr>
<td>Title : </td>
<td><form:input path="mediaTitle" class="validate" /></td>
<td><form:errors path="mediaTitle" cssClass="error" /></td>
</tr>

<tr>
<td>Short Title : </td>
<td><form:input path="mediaName" class="validate"/></td>
<td><form:errors path="mediaName" cssClass="error" /></td>
</tr>

<tr>
<td>Media State : </td>
<td><div class="styled-select">
<form:select path="mediaCycleId" id="mediaCycleId" class="validate">
<form:option value="-1" label="--- Select ---"/>
<form:options items="${mediaCycleList}" itemValue="mediaCycleId" itemLabel="mediaCycleState"/>
</form:select></div>
</td>
<td><form:errors path="mediaCycleId" cssClass="error" /></td>
</tr>

<tr>
<td>Language : </td>
<td><div class="styled-select">
<form:select path="languageId" id="languageId" class="validate">
<form:option value="-1" label="--- Select ---"/>
<form:options items="${languageList}" itemValue="languageId" itemLabel="languageName"/>
</form:select></div>
</td>
<td><form:errors path="languageId" cssClass="error" /></td>
</tr>

<tr>
<td>Description : </td>
<td><form:textarea path="description" /></td>
<td><form:errors path="description" cssClass="error" /></td>
</tr>

<tr>
<td>Tags : </td>
<td><form:textarea path="tags" /></td>
<td><form:errors path="tags" cssClass="error" /></td>
</tr>

<tr><td>MediaStartTime : </td>
<td><form:input path="mediaStartTime" class="datepicker calender validate" /></td>
<td><form:errors path="mediaStartTime" cssClass="error" /></td></tr>

<tr><td>MediaEndTime : </td>
<td><form:input path="mediaEndTime" class="datepicker calender validate" /></td>
<td><form:errors path="mediaEndTime" cssClass="error" /></tr>

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

<!-- <div><input type="submit" onclick="return sub('feedbackform')"/></div> -->


</form:form>

</div>