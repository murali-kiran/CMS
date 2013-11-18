<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="categoryMap" items="${mediaInfoMap}">

	<c:set var="category" scope="session" value="${categoryMap.key}" />
	<div style="width: 100%; background-color: #00FF00">${category.name}</div>

	<c:set var="media" scope="request" value="${categoryMap.value}" />
<div>
	<c:forEach var="mediaInfo" items="${media.name}">
		<div style="width: 33%; float: left; text-align: center;margin: 2px 0px 2px;">
			<div>${mediaInfo.mediaName}</div>
			<div>
			<a href="${pageContext.servletContext.contextPath}/service2/dwl/${serviceId}/${mediaInfo.mediaId}?channel=${channel}">		<img alt="noImage"
					src="${pageContext.servletContext.contextPath}/${mediaInfo.storagePath}"
					width="52px" height="52px">
			</a>
			</div>
			<div>@ ${mediaInfo.price}</div>
			<div><img alt="noImage"
					src="${pageContext.servletContext.contextPath}/resources/images/buy.png"
					width="40px" height="15px"></div>
		</div>
	</c:forEach>
</div>
	<br />

	<div style="width: 100%; text-align: right;display: block;">


		<c:forEach var="i" begin="1" end="${media.id}">
	&nbsp;<a
				href="<c:url value="/service2/cat/pageId/pageCount/${serviceId}/${category.id}/${i}/${PaginationCount}?channel=${channel}"/>">&nbsp;${i}&nbsp;</a>&nbsp;
	</c:forEach>
	</div>



</c:forEach>

<div><a href="${pageContext.servletContext.contextPath}/service/${serviceId}?channel=${channel}">&nbsp;Home</a></div>