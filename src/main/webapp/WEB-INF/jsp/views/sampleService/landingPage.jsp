<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table
	style="border: 0px; padding: 0px; width: 100%; border-spacing: 0px; text-align: center;" border="1px;">
	<tr>
		<c:forEach var="category" items="${categories}">
			<td><a
				href="${pageContext.servletContext.contextPath}/service/cat/${serviceId}/${category.id}">${category.name}</a></td>
		</c:forEach>
	</tr>
</table>

<c:forEach var="mediaType" items="${mediaInfoMap}">

	<c:set var="mediaTypeInfo" scope="session" value="${mediaType.key}" />
	<div style="width: 100%; background-color: #00FF00">${mediaTypeInfo.name}</div>
	
	
	<%-- <input type="hidden" name="mediaTypePageId${mediaTypeInfo.id}" value="${paginationMap[mediaTypeInfo.id]}" /> --%>

	<c:set var="media" scope="session" value="${mediaType.value}" />
	<c:forEach var="mediaInfo" items="${media.name}">
  		<div style="width: 45%;float: left;text-align: center;">	${mediaInfo.mediaName} </div>
	</c:forEach>
	
	<br/>
	<div style="width: 100%;text-align: right;">
	<c:forEach var="i" begin="1" end="${media.id/PaginationCount}">
	&nbsp;<a href="<c:url value="/service/cat/typeId/pageId/${serviceId}/${categoryId}/${mediaTypeInfo.id}/${i}/${paginationMap}"/>">${i}</a>&nbsp;
	</c:forEach>
	</div>
	
	
</c:forEach>