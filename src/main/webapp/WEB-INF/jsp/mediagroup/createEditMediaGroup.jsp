<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/script/climb3.js"></script>
<script>

 $(document).ready(function(){
	 
  
}); 
 function validateForm(){
		var msg = "";
		var title = $('#mediaGroupTitle').val();
		var name = $('#mediaGroupName').val();
		var media = $('#mediaId').val();
		
		if (title == "") {
			msg = msg + "Title is required.\n";
			$('#title').focus();
		}
		if (name == "") {
			msg = msg + "Name is required.\n";
			$('#name').focus();
		}
		if (media == "" || media == -1) {
			msg = msg + "Please select Group Preview.\n";
			$('#media').focus();
		}
		
		
		if (msg != "") {
			alert(msg);
			return false;
		}else {
			var status = confirm('Do you want to save the changes? ');
			
			if(status == true){
				$(window).unbind('beforeunload');
			}
			else{return false;}
		}
	}
</script>
<div style="width: 100%;display: block;border: 1px solid #95B8E7;">
<div class="panel-header panel-title">Create Group</div>
<form:form method="post" id="mediagroupform" name="mediagroupform"  commandName="addGroup"
                           action="${pageContext.servletContext.contextPath}/saveGroup" onsubmit="return validateForm();">

<table>


<tr>
<td>Group Title </td>
<td><form:input path="mediaGroupTitle" class="validate" /></td>
<td><form:errors path="mediaGroupTitle" cssClass="error" /><form:hidden path="mediaGroupId"/></td>
</tr>

<tr>
<td>Group Name<br>(for internal purpose) </td>
<td><form:input path="mediaGroupName" class="validate" /></td>
<td><form:errors path="mediaGroupName" cssClass="error" /></td>
</tr>

<tr>
<td>Media Group Thumbnail</td>
<td><div class="styled-select">
<form:select path="mediaId" class="validate" >
<form:option value="-1" label="--- Select ---"/>
<form:options items="${mediaList}" itemValue="mediaId" itemLabel="mediaName"/>
</form:select></div>
</td>
<td><form:errors path="mediaId" cssClass="error" /></td>
</tr>

<tr>
<td>Description   </td>
<td><form:textarea path="description" /></td>
<td><form:errors path="description" cssClass="error" /></td>
</tr>
<tr>
</table>
<div><input type="submit"/></div>
</form:form>
</div>

