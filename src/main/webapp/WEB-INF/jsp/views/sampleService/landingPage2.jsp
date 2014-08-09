<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <head>
<script src="http://code.jquery.com/jquery-1.5.2.min.js"></script>
      <script src="http://code.jquery.com/mobile/1.0a4/jquery.mobile-1.0a4.min.js"></script>
      </head>
<script>
function function_callback() {
 // window.alert("new func");
  for (var i = 0; i < arguments.length; i++) {
   // window.alert(arguments[i]);
  }
 // newAjax(arguments[1]);
 //var respCode = arguments[1];
 var respCode = arguments[1];
 var a = document.getElementsByTagName('a');
//alert("before forloop");
 for (var idx= 0; idx < a.length; ++idx){
	// alert("inside forloop");
   var x = a[idx].href;
   a[idx].href = x + "&respCode="+respCode;
   
 }
}
function newAjax(data1) {
	window.alert(data1);
	$.ajax({
        type: "POST",
        url: "http://49.50.68.139:8080/Wap/temp/temp.jsp",
        data: ({name: data1}),
        cache: false,
        dataType: "text",
        success: function(msg){
            $('#resultip').html(msg);
        }
      });
}

</script>
 
<script type="text/javascript" src="${ipaymsisdnUrl}"> </script>

<div id="resultip" style="color:red;font-weight: bold;">
	${message}
</div>
<c:forEach var="categoryMap" items="${mediaInfoMap}">

	<c:set var="media" scope="request" value="${categoryMap.value}" />
	<c:set var="category" scope="request" value="${categoryMap.key}" />
	
 	<div style="width: 100%; background-color: red;border-bottom: 1px; border-color: black;">${category.name}</div> 
	
	<c:forEach var="mediaInfo" items="${media.name}">
	<c:choose>
	<c:when test="${fn:length(media.name) eq 3}">
		<div style="width: 33%;float: left;text-align: center;margin: 2px 0px 2px;">
		<c:set var="serkeyid" value="${mediaInfo.serviceKeyId}" />
	    
	   <c:choose>
	    <c:when test="${mediaInfo.isSubMediaGroup eq true}">
	    
  		<a href="${pageContext.servletContext.contextPath}/service2/cat/${serviceId}/${mediaInfo.mediagroupParentId}/${mediaInfo.mediagroupId}?channel=${channel}">
  		<div>${mediaInfo.mediaName}</div>
  		<div>
				<img alt="noImage" src="${mediaInfo.storagePath}"
					width="${previewWidth}" height="${previewHeight}">
			</div>
			
  		
  		</a> 
  		</c:when>
  		<c:otherwise>
  		<div>${mediaInfo.mediaName}</div>
  		<div>
  		
  		<a href="<c:url value="/service2/dwl/${serviceId}/${mediaInfo.mediaId}/${mediaInfo.serviceKeypriceKey}?channel=${channel}&serviceKeyId=${mediaInfo.serviceKeyId}"/>">
		<img alt="noImage" src="${mediaInfo.storagePath}"
					width="${previewWidth}" height="${previewHeight}">
		</a>			
		</div>
		
  		
  		</c:otherwise>
  		</c:choose>
  		<div>Buy @ ${mediaInfo.price}</div>
  		<%-- <div><img alt="noImage"
					src="${pageContext.servletContext.contextPath}/resources/images/buy.png"
					width="40px" height="15px"></div> --%>
  		</div>
  		</c:when>
  		
  			<c:when test="${fn:length(media.name) eq 2}">
		<div style="width: 50%;float: left;text-align: center;margin: 2px 0px 2px;">
		<c:set var="serkeyid" value="${mediaInfo.serviceKeyId}" />
	    
	   <c:choose>
	    <c:when test="${mediaInfo.isSubMediaGroup eq true}">
	    
  		<a href="${pageContext.servletContext.contextPath}/service2/cat/${serviceId}/${mediaInfo.mediagroupParentId}/${mediaInfo.mediagroupId}?channel=${channel}">
  		<div>${mediaInfo.mediaName}</div>
  		<div>
				<img alt="noImage" src="${mediaInfo.storagePath}"
					width="${previewWidth}" height="${previewHeight}">
			</div>
			
  		
  		</a> 
  		</c:when>
  		<c:otherwise>
  		<div>${mediaInfo.mediaName}</div>
  		<div>
  		
  		<a href="<c:url value="/service2/dwl/${serviceId}/${mediaInfo.mediaId}/${mediaInfo.serviceKeypriceKey}?channel=${channel}&serviceKeyId=${mediaInfo.serviceKeyId}"/>">
		<img alt="noImage" src="${mediaInfo.storagePath}"
					width="${previewWidth}" height="${previewHeight}">
		</a>			
		</div>
		
  		
  		</c:otherwise>
  		</c:choose>
  		<div>Buy @ ${mediaInfo.price}</div>
  		<%-- <div><img alt="noImage"
					src="${pageContext.servletContext.contextPath}/resources/images/buy.png"
					width="40px" height="15px"></div> --%>
  		</div>
  		</c:when>
  		
  		<c:when test="${fn:length(media.name) eq 1}">
		<div style="width: 100%;float: left;text-align: center;margin: 2px 0px 2px;">
		<c:set var="serkeyid" value="${mediaInfo.serviceKeyId}" />
	    
	   <c:choose>
	    <c:when test="${mediaInfo.isSubMediaGroup eq true}">
	    
  		<a href="${pageContext.servletContext.contextPath}/service2/cat/${serviceId}/${mediaInfo.mediagroupParentId}/${mediaInfo.mediagroupId}?channel=${channel}">
  		<div>${mediaInfo.mediaName}</div>
  		<div>
				<img alt="noImage" src="${mediaInfo.storagePath}"
					width="${previewWidth}" height="${previewHeight}">
			</div>
			
  		
  		</a> 
  		</c:when>
  		<c:otherwise>
  		<div>${mediaInfo.mediaName}</div>
  		<div>
  		
  		<a href="<c:url value="/service2/dwl/${serviceId}/${mediaInfo.mediaId}/${mediaInfo.serviceKeypriceKey}?channel=${channel}&serviceKeyId=${mediaInfo.serviceKeyId}"/>">
		<img alt="noImage" src="${mediaInfo.storagePath}"
					width="${previewWidth}" height="${previewHeight}">
		</a>			
		</div>
		
  		
  		</c:otherwise>
  		</c:choose>
  		<div>Buy @ ${mediaInfo.price}</div>
  		<%-- <div><img alt="noImage"
					src="${pageContext.servletContext.contextPath}/resources/images/buy.png"
					width="40px" height="15px"></div> --%>
  		</div>
  		</c:when>
  				
	</c:choose>	
	</c:forEach>
	
	<br/>
	<c:choose>
	<c:when test="${media.id eq true}">
	<div style="width: 100%;text-align: right;">
	&nbsp;<a href="${pageContext.servletContext.contextPath}/service2/cat/${serviceId}/${category.id}?channel=${channel}&serviceKeyId=${serkeyid}">More</a>&nbsp;
	</div>
	</c:when>
	<c:otherwise>
	<div style="width: 100%;text-align: right;">&nbsp;</div>
	</c:otherwise>
	</c:choose>
	
	
</c:forEach>