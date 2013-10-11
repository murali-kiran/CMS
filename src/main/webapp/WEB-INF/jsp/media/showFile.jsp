<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

       
				<c:forEach items="${contentBuilds}" var="build" varStatus="buildStatus">
				 
		 <fieldset>
	       <legend>Content Files </legend>
			<table> 
	          <c:if test="${uploadFile.isRemote == true}"><tr>
						<td>RemoteContentId :</td>
						<td><form:input path="uploadFile.remoteContentId" /></td>
					</tr></c:if>
			
			  <c:forEach items="${build.fileBeans}" var="fileContent" varStatus="fileStatus">
		
			 <tr>
			 <c:choose>
				<c:when test="${uploadFile.isRemote == true}"><td>RemoteUrl for ${fileContent.label} :</td><td>
				<form:input path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].remoteUrl" /></td>
				<td><form:errors path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].remoteUrl" cssClass="error" /></td>
				</c:when>
				
				<c:otherwise>
				 <td>${fileContent.label} :</td><td>
				 <form:input type="file" path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].file" /></td>
				<td><form:errors path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].file" cssClass="error" /></td>
				</c:otherwise>
			 </c:choose>
				
				<form:hidden path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].contentItemFileSpecId"  />
				<form:hidden path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].mimeType"  />
				<form:hidden path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].minBitRate"  />
				<form:hidden path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].maxBitRate"  />
				<form:hidden path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].minFileSize"  />
				<form:hidden path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].maxFileSize"  />
				<form:hidden path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].minWidth"  />
				<form:hidden path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].maxWidth"  />
				<form:hidden path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].minHeight"  />
				<form:hidden path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].maxHeight"  />
				<form:hidden path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].isOptional"  />
				<form:hidden path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].isLocal"  />
				<form:hidden path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].purpose"  />
				
				
				
				<%-- <td><form:errors path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].file" cssClass="error" /></td> --%>
			</tr>
				
			<c:if test="${fileContent.isDuration==true}">	
				
			<tr>
			<td>Duration : (in hh:mm:ss):</td>
			<td><form:input path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].duration" /></td>
			<td><form:errors path="uploadFile.contentBuilds[0].fileBeans[${fileContent.id}].duration" cssClass="error" /></td>
			</tr></c:if>

                	</c:forEach></table></fieldset>
                	
                  </c:forEach>
                  