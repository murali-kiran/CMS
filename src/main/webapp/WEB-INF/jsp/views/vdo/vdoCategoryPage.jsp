<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="background-color: #D8D8D8">
<!-- MediaGroup -->
<c:set var="category" value="${mediaInfoMap.id}" />

<!-- Map<MediaType,Bean<Integer,List<MediaBean>>>> -->
<c:set var="mediaTypeMap" value="${mediaInfoMap.name}" />

<div style="width: 100%; background-color: blue; border-bottom: 1px; border-color: black;text-align: center;">${category.mediaGroupName}</div>

<c:forEach var="categoryMap" items="${mediaTypeMap}">

	<!-- MediaType of Category -->
	<c:set var="mediaType" scope="session" value="${categoryMap.key}" />

	<div style="width: 100%; background-color: #DBA901; border-bottom: 1px; border-color: black;margin-top: 2px;">${mediaType.mediaTypeTitle}-${mediaType.mediaTypeId}</div>
	<div style="width: 100%; background-color: #F7FE2E; border-bottom: 1px; border-color: black;"> Click on image to download </div>

	<!-- Bean<Integer,List<MediaBean>> of each Media Type -->
	<c:set var="mediaBeanOfMediaType" scope="request" value="${categoryMap.value}" />
	
	<div>
		<c:forEach var="mediaInfo" items="${mediaBeanOfMediaType.name}">
			<div
				style="width: 33%; float: left; text-align: center; margin: 2px 0px 2px;">
				<div>${mediaInfo.mediaName}</div>
				<div>
					<a
						href="${pageContext.servletContext.contextPath}/vdo/dwl/${serviceId}/${mediaInfo.mediaId}/${mediaInfo.serviceKeypriceKey}?channel=${channel}">
						<img alt="noImage" src="${mediaInfo.storagePath}"
						width="${previewWidth}" height="${previewHeight}">
					</a>
				</div>
				<div>Buy @ ${mediaInfo.price}</div>

			</div>
		</c:forEach>
	</div>
	<br />

	<div style="width: 100%; clear: both; text-align: right; display: block;">


		<c:forEach var="i" begin="1" end="${mediaBeanOfMediaType.id}">
	&nbsp;<a href="<c:url value="/vdo/cat/pageId/pageCount/${serviceId}/${category.mediaGroupId}/${i}/${PaginationCount}?channel=${channel}&mediaTypeId=${mediaType.mediaTypeId}"/>">&nbsp;${i}&nbsp;</a>&nbsp;
	</c:forEach>
	</div>



</c:forEach>

<div style="width: 100%; clear: both;">
	<a href="${pageContext.servletContext.contextPath}/service/vdo/${serviceId}?channel=${channel}">&nbsp;Home</a>
</div>
</div>
