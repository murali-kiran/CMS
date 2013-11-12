<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="categoryMap" items="${mediaInfoMap}">

	<c:set var="category" scope="session" value="${categoryMap.key}" />
	<div style="width: 100%; background-color: #00FF00">${category.name}</div>
	
	<c:set var="media" scope="request" value="${categoryMap.value}" />
	
	<c:forEach var="mediaInfo" items="${media.name}">
		<div style="width: 50%;float: left;text-align: center;">
	   <c:choose>
	    <c:when test="${mediaInfo.isSubMediaGroup eq true}">
  		<a href="${pageContext.servletContext.contextPath}/service2/cat/${serviceId}/${mediaInfo.mediagroupId}">${mediaInfo.mediaName}</a> 
  		</c:when>
  		<c:otherwise>
  		${mediaInfo.mediaName}
  		</c:otherwise>
  		</c:choose>
  		</div>
	</c:forEach>
	
	<br/>
	<div style="width: 100%;text-align: right;">
	<c:if test="${media.id eq true}">
	&nbsp;<a href="${pageContext.servletContext.contextPath}/service2/cat/${serviceId}/${category.id}">more</a>&nbsp;
	</c:if>
	</div>
	
	
</c:forEach>