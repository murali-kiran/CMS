<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="categoryMap" items="${mediaInfoMap}">

	<c:set var="category" scope="session" value="${categoryMap.key}" />
	<div style="width: 100%; background-color: #00FF00">${category.name}</div>
	
	<c:set var="media" scope="request" value="${categoryMap.value}" />
	
	<c:forEach var="mediaInfo" items="${media.name}">
		<div style="width: 33%;float: left;text-align: center;margin: 2px 0px 2px;">
		
	   <c:choose>
	    <c:when test="${mediaInfo.isSubMediaGroup eq true}">
  		<a href="${pageContext.servletContext.contextPath}/service2/cat/${serviceId}/${mediaInfo.mediagroupParentId}/${mediaInfo.mediagroupId}?channel=${channel}">
  		<div>${mediaInfo.mediaName}</div>
  		<div>
				<img alt="noImage" src="${pageContext.servletContext.contextPath}/${mediaInfo.storagePath}"
					width="52px" height="52px">
			</div>
			
  		
  		</a> 
  		</c:when>
  		<c:otherwise>
  		<div>${mediaInfo.mediaName}</div>
  		<div>
  		<a href="${pageContext.servletContext.contextPath}/service2/dwl/${serviceId}/${mediaInfo.mediaId}?channel=${channel}">
		<img alt="noImage" src="${pageContext.servletContext.contextPath}/${mediaInfo.storagePath}"
					width="52px" height="52px">
		</a>			
		</div>
		
  		
  		</c:otherwise>
  		</c:choose>
  		<div>@ ${mediaInfo.price}</div>
  		<div><img alt="noImage"
					src="${pageContext.servletContext.contextPath}/resources/images/buy.png"
					width="40px" height="15px"></div>
  		</div>
	</c:forEach>
	
	<br/>
	<div style="width: 100%;text-align: right;">
	<c:if test="${media.id eq true}">
	&nbsp;<a href="${pageContext.servletContext.contextPath}/service2/cat/${serviceId}/${category.id}?channel=${channel}">more</a>&nbsp;
	</c:if>
	</div>
	
	
</c:forEach>