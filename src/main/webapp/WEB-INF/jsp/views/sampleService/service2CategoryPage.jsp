<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="categoryMap" items="${mediaInfoMap}">

	<c:set var="category" scope="session" value="${categoryMap.key}" />

	<div style="width: 100%; background-color: red;border-bottom: 1px; border-color: black;">${category.name}</div>


	<c:set var="media" scope="request" value="${categoryMap.value}" />
<div>
	<c:forEach var="mediaInfo" items="${media.name}">
		<div style="width: 33%; float: left; text-align: center;margin: 2px 0px 2px;">
			<div>${mediaInfo.mediaName}</div>
			<div>
			<a href="${pageContext.servletContext.contextPath}/service2/dwl/${serviceId}/${mediaInfo.mediaId}/${mediaInfo.serviceKeypriceKey}?channel=${channel}">		<img alt="noImage"
					src="${mediaInfo.storagePath}"
					width="${previewWidth}" height="${previewHeight}">
			</a>
			</div>
			<div>Buy @ ${mediaInfo.price}</div>
			<%-- <div>
			<img alt="noImage"
					src="${pageContext.servletContext.contextPath}/resources/images/buy.png"
					width="40px" height="15px">
					
					</div> --%>
		</div>
	</c:forEach>
</div>
	<br />

	<div style="width: 100%;clear: both; text-align:center;display: block;">


		<c:forEach var="i" begin="1" end="${media.id}">
	&nbsp;<a
				href="<c:url value="/service2/cat/pageId/pageCount/${serviceId}/${category.id}/${i}/${PaginationCount}?channel=${channel}"/>">&nbsp;${i}&nbsp;</a>&nbsp;
	</c:forEach>
	</div>



</c:forEach>

<div style="width: 100%;clear: both;"><a href="${pageContext.servletContext.contextPath}/service/${serviceId}?channel=${channel}">&nbsp;Home</a></div>
