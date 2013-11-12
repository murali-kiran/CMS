<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="categoryMap" items="${mediaInfoMap}">

	<c:set var="category" scope="session" value="${categoryMap.key}" />
	<div style="width: 100%; background-color: #00FF00">${category.name}</div>
	
	<c:set var="media" scope="request" value="${categoryMap.value}" />
	
	<c:forEach var="mediaInfo" items="${media.name}">
  		<div style="width: 50%;float: left;text-align: center;">${mediaInfo.mediaName} </div>
	</c:forEach>
	
	<br/>
	
	<div style="width: 100%;text-align: right;">
	
	
	<c:forEach var="i" begin="1" end="${media.id}">
	&nbsp;<a href="<c:url value="/service2/cat/pageId/pageCount/${serviceId}/${category.id}/${i}/${PaginationCount}"/>">${i}</a>&nbsp;
	</c:forEach>
	</div>
	
	
</c:forEach>