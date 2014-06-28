<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- map<cat<id,mediaGroup>,bean<isMoreMedia's,List<mediaInfo>>> --%>
<div style="width: 100%; background-color: #DBA901; border-bottom: 1px; border-color: black;margin-bottom: 2px;text-align: center;height: 22px;">Home</div>
<div style="background-color: #D8D8D8">
<c:forEach var="categoryMap" items="${mediaInfoMap}">

	<c:set var="category" scope="request" value="${categoryMap.key}" />
	<div style="width: 100%; background-color: red;border-bottom: 1px; border-color: black;">${category.name.mediaGroupName}</div>
	
	<c:set var="media" scope="request" value="${categoryMap.value}" />
	
	<table width="100%" >
	<c:forEach var="mediaInfo" items="${media.name}" varStatus="status">
		<!-- <div style="width: 33%;float: left;text-align: center;margin: 2px 0px 2px;"> -->
		
	   <c:choose>
	    <c:when test="${mediaInfo.isSubMediaGroup eq true}">
	    
  		<%-- <a href="${pageContext.servletContext.contextPath}/service2/cat/${serviceId}/${mediaInfo.mediagroupParentId}/${mediaInfo.mediagroupId}?channel=${channel}">
  		<div>${mediaInfo.mediaName}</div>
  		<div>
				<img alt="noImage" src="${mediaInfo.storagePath}"
					width="${previewWidth}" height="${previewHeight}">
		</div>			  		
  		</a>  --%>
  		
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
											href="<c:url value="/vdo/dwl/${serviceId}/${mediaInfo.mediaId}/${mediaInfo.serviceKeypriceKey}?channel=${channel}&servicKeyId=${mediaInfo.serviceKeyId}&mediaTypeId=${mediaInfo.mediaTypeId}"/>">
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
												href="<c:url value="/service2/dwl/${serviceId}/${mediaInfo.mediaId}/${mediaInfo.serviceKeypriceKey}?channel=${channel}&servicKeyId=${mediaInfo.serviceKeyId}&mediaTypeId=${mediaInfo.mediaTypeId}"/>">
												${mediaInfo.mediaName} </a>
										</div>
									</td>
								</c:otherwise>
							</c:choose>
						</tr>
						
					
  		</c:otherwise>
  		</c:choose>
  		<%-- <div>Buy @ ${mediaInfo.price}</div> --%>
  		
  		<!-- </div> -->
	</c:forEach>
	</table>
	<br/>
	<div style="width: 100%;text-align: left;">
	<c:if test="${media.id eq true}">
	&nbsp;<a href="${pageContext.servletContext.contextPath}/vdo/cat/${serviceId}/${category.id}?channel=${channel}">More &gt;</a>&nbsp;
	</c:if>
	</div>
	
	
</c:forEach>
</div>