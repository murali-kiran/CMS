<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- map<cat<id,mediaGroup>,bean<isMoreMedia's,List<mediaInfo>>> --%>
<div style="width: 100%; background-color: #DBA901; border-bottom: 1px; border-color: black;margin-bottom: 2px;text-align: center;height: 22px;">Home</div>
<div style="background-color: #D8D8D8">
<c:forEach var="categoryMap" items="${mediaInfoMap}">

	<c:set var="category" scope="request" value="${categoryMap.key}" />
	
	<div style="width: 100%; background-color: #2E2E2E;border-bottom: mediaGroupMap1px; border-color: black;color: #BDBDBD">${category.name.mediaGroupName}</div>
	
	
	<c:set var="media" scope="request" value="${categoryMap.value}" />
	
	<!-- Below we get serviceMediaGroup of category -->
	<c:set var="serviceMediaGroup" value="${mediaGroupMap[category.id].name}"/>
	
	
	<c:choose>
			<c:when test="${serviceMediaGroup.isSpecialMediaGroup eq '1'}">

				<c:forEach var="mediaInfo" items="${media.name}" varStatus="status">
				
				<c:choose>
					<c:when test="${fn:length(media.name) eq 3}">
						<div
						style="width: 33%; float: left; text-align: center; margin: 2px 0px 2px;">
						<div>${mediaInfo.mediaName}</div>
						<div>
							<a
								href="<c:url value="/vdo/dwl/${serviceId}/${mediaInfo.mediaId}/${mediaInfo.serviceKeypriceKey}?channel=${channel}&servicKeyId=${mediaInfo.serviceKeyId}"/>">
								<img alt="noImage" src="${mediaInfo.storagePath}"
								width="${previewWidth}" height="${previewHeight}">
							</a>
						</div>
						<%-- <div>Buy @ ${mediaInfo.price}</div> --%>
						</div>
					</c:when>
					<c:when test="${fn:length(media.name) eq 2}">
						<div
						style="width: 50%; float: left; text-align: center; margin: 2px 0px 2px;">
						<div>${mediaInfo.mediaName}</div>
						<div>
							<a
								href="<c:url value="/vdo/dwl/${serviceId}/${mediaInfo.mediaId}/${mediaInfo.serviceKeypriceKey}?channel=${channel}&servicKeyId=${mediaInfo.serviceKeyId}"/>">
								<img alt="noImage" src="${mediaInfo.storagePath}"
								width="${previewWidth}" height="${previewHeight}">
							</a>
						</div>
						<%-- <div>Buy @ ${mediaInfo.price}</div> --%>
						</div>
					</c:when>
					<c:when test="${fn:length(media.name) eq 1}">
						<div
						style="width: 100%; float: left; text-align: center; margin: 2px 0px 2px;">
						<div>${mediaInfo.mediaName}</div>
						<div>
							<a
								href="<c:url value="/vdo/dwl/${serviceId}/${mediaInfo.mediaId}/${mediaInfo.serviceKeypriceKey}?channel=${channel}&servicKeyId=${mediaInfo.serviceKeyId}"/>">
								<img alt="noImage" src="${mediaInfo.storagePath}"
								width="${previewWidth}" height="${previewHeight}">
							</a>
						</div>
						<%-- <div>Buy @ ${mediaInfo.price}</div> --%>
						</div>
					</c:when>
				</c:choose>
					
				</c:forEach>

			</c:when>
			<c:otherwise>
	<table width="100%" >
	<c:forEach var="mediaInfo" items="${media.name}" varStatus="status">
			
	   <c:choose>
	    <c:when test="${mediaInfo.isSubMediaGroup eq true}">
	      	  		
  		</c:when>
  		<c:otherwise>  		
						<tr>
							<c:choose>
								<c:when test="${status.first}">
									<td rowspan="3" width="10%" >
										<%-- <div>${mediaInfo.mediaName}</div> --%>
										<div>
											<a
												href="<c:url value="/vdo/dwl/${serviceId}/${mediaInfo.mediaId}/${mediaInfo.serviceKeypriceKey}?channel=${channel}&servicKeyId=${mediaInfo.serviceKeyId}&mediaTypeId=${mediaInfo.mediaTypeId}"/>">
												<img alt="noImage" src="${mediaInfo.storagePath}"
												width="${previewWidth}" height="${previewHeight}">
											</a>
										</div>

									</td>
								<td
									style="text-align: left; vertical-align: top; padding: 10px;">
									<div>
										<span>  
										<img src="${pageContext.servletContext.contextPath}/resources/images/rightBullet.png">
										</span> <a
											href="<c:url value="/vdo/dwl/${serviceId}/${mediaInfo.mediaId}/${mediaInfo.serviceKeypriceKey}?channel=${channel}&servicKeyId=${mediaInfo.serviceKeyId}&mediaTypeId=${mediaInfo.mediaTypeId}"/>" style="color: #000000">
											${mediaInfo.mediaName} </a>
									</div>


								</td>
							</c:when>
								<c:otherwise>
									<td style="text-align:left;vertical-align:top;padding:10px;">
										<div>
										<span>  
										<img src="${pageContext.servletContext.contextPath}/resources/images/rightBullet.png" >
										</span>
											<a
												href="<c:url value="/service2/dwl/${serviceId}/${mediaInfo.mediaId}/${mediaInfo.serviceKeypriceKey}?channel=${channel}&servicKeyId=${mediaInfo.serviceKeyId}&mediaTypeId=${mediaInfo.mediaTypeId}"/>" style="color: #000000">
												${mediaInfo.mediaName} </a>
										</div>
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
											
  		</c:otherwise>
  		</c:choose>
  		  
	</c:forEach>
	</table>
	
	</c:otherwise>
	</c:choose>
		
	<br/>
	
	<c:choose>
	<c:when test="${media.id eq true}">
	<div style="width: 100%;text-align: left;">
	&nbsp;<a href="${pageContext.servletContext.contextPath}/vdo/cat/${serviceId}/${category.id}?channel=${channel}">More &gt;</a>&nbsp;
	</div>
	</c:when>
	<c:otherwise>
	<div style="width: 100%;">&nbsp;</div>
	</c:otherwise>
	</c:choose>
	
</c:forEach>
</div>