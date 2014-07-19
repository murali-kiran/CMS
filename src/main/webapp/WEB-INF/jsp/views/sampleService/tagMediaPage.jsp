<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="width: 100%; background-color: red;border-bottom: 1px; border-color: black;">${tagName}</div>
	
<div style="width: 100%;">
	<c:forEach var="mediaInfo" items="${mediaBeans}">
		<div style="width: 33%; float: left; text-align: center;margin: 2px 0px 2px;">
			<div>${mediaInfo.mediaName}</div>
			<div>
			<a href="${pageContext.servletContext.contextPath}/service2/dwl/${serviceId}/${mediaInfo.mediaId}/${mediaInfo.serviceKeypriceKey}?channel=${channel}">		<img alt="noImage"
					src="${mediaInfo.storagePath}"
					width="${previewWidth}" height="${previewHeight}">
			</a>
			</div>
			<div>Buy @ ${mediaInfo.price}</div>
		</div>
	</c:forEach>
</div>

<div style="width: 100%; text-align:center;clear: both;">


<c:forEach var="i" begin="1" end="${paginationCount}">
	&nbsp;<a
				href="<c:url value="/service/searchMediaByTag/${serviceId}/${i}?channel=${channel}&tag=${tagName}"/>">&nbsp;${i}&nbsp;</a>&nbsp;
	</c:forEach>
</div>

<div style="width: 100%;clear: both;"><a href="${pageContext.servletContext.contextPath}/service/${serviceId}?channel=${channel}">&nbsp;Home</a></div>
