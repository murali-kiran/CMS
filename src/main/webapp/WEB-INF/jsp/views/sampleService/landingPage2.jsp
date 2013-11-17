<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="categoryMap" items="${mediaInfoMap}">

	<c:set var="category" scope="session" value="${categoryMap.key}" />
	<div style="width: 100%; background-color: #00FF00">${category.name}</div>
	
	<c:set var="media" scope="request" value="${categoryMap.value}" />
	
	<c:forEach var="mediaInfo" items="${media.name}">
		<div style="width: 33%;float: left;text-align: center;margin: 2px 0px 2px;">
		<div>@ ${mediaInfo.price}</div>
	   <c:choose>
	    <c:when test="${mediaInfo.isSubMediaGroup eq true}">
  		<a href="${pageContext.servletContext.contextPath}/service2/cat/${serviceId}/${mediaInfo.mediagroupId}?channel=${channel}">
  		
  		<div>
				<img alt="noImage" src="${pageContext.servletContext.contextPath}/${mediaInfo.storagePath}"
					width="52px" height="52px">
			</div>
			<div>${mediaInfo.mediaName}</div>
  		
  		</a> 
  		</c:when>
  		<c:otherwise>
  		
  		<div>
  		<a href="${pageContext.servletContext.contextPath}/service2/dwl/${serviceId}/${mediaInfo.mediaId}?channel=${channel}">
		<img alt="noImage" src="${pageContext.servletContext.contextPath}/${mediaInfo.storagePath}"
					width="52px" height="52px">
		</a>			
		</div>
		<div>${mediaInfo.mediaName}</div>
  		
  		</c:otherwise>
  		</c:choose>
  		</div>
	</c:forEach>
	
	<br/>
	<div style="width: 100%;text-align: right;">
	<c:if test="${media.id eq true}">
	&nbsp;<a href="${pageContext.servletContext.contextPath}/service2/cat/${serviceId}/${category.id}?channel=${channel}">more</a>&nbsp;
	</c:if>
	</div>
	
	
</c:forEach>