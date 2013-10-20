<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/script/climb3.js"></script>
<script>

 $(document).ready(function(){
	 

   
}); 

</script>
<div>
<form:form method="post" id="feedbackform" name="feedbackform"  commandName="searchMediaGroup"
                           action="${pageContext.servletContext.contextPath}/showRemMediaGroup" escape="false" enctype="multipart/form-data">

<table>
<tr><td style="width: 50%;vertical-align: top;">
<table>


<tr>
<td>Media Group Title <input type="hidden" name="parentmgId" id="parentmgId" value="<%=request.getParameter("mgid")%>"/> </td>
<td><form:input path="mediaGroupTitle" class="validate" /></td>
<td><form:errors path="mediaGroupTitle" cssClass="error" /></td>
</tr>

<tr>
<td>Media Group Name</td>
<td><form:input path="mediaGroupName" class="validate"/></td>
<td><form:errors path="mediaGroupName" cssClass="error" /></td>
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

</div>