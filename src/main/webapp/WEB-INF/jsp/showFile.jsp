<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

       
			 
		 <fieldset>
	       <legend>Content Files </legend>
			<table> 
		
			  <c:forEach items="${uploadFile.mediaContentModelList}" var="fileContent" varStatus="fileStatus">
		
			 <tr>
			
				 <td>${fileContent.label} :</td><td>
				 <form:input type="file" path="uploadFile.mediaContentModelList[${fileContent.id}].file" /></td>
				<td><form:errors path="uploadFile.mediaContentModelList[${fileContent.id}].file" cssClass="error" /></td>
			
				<form:hidden path="uploadFile.mediaContentModelList[${fileContent.id}].mediaSpecificationId"  />
				<form:hidden path="uploadFile.mediaContentModelList[${fileContent.id}].mimeType"  />
				<form:hidden path="uploadFile.mediaContentModelList[${fileContent.id}].bitRate"  />
				<form:hidden path="uploadFile.mediaContentModelList[${fileContent.id}].width"  />
				<form:hidden path="uploadFile.mediaContentModelList[${fileContent.id}].height"  />
				<form:hidden path="uploadFile.mediaContentModelList[${fileContent.id}].purpose"  />
				
				
				
			</tr>
				
			<%-- <c:if test="${fileContent.isDuration==true}">	
				
			<tr>
			<td>Duration : (in hh:mm:ss):</td>
			<td><form:input path="uploadFile.mediaContentModelList[${fileContent.id}].duration" /></td>
			<td><form:errors path="uploadFile.mediaContentModelList[${fileContent.id}].duration" cssClass="error" /></td>
			</tr></c:if> --%>

                	</c:forEach></table></fieldset>
                	
                  