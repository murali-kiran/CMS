<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <div><img alt="noImage"
					src="${pageContext.servletContext.contextPath}/resources/images/Faltu.jpg"
					width="160px" height="auto">
  		</div>
  		
<div style="width: 100%;font-size: 12;background-color: #FF0000;">
<div style="margin-bottom: 2px;background-color: #FF0000;width: 33%;float: left;">&nbsp;</div>
<div style="background-color: #FF0000;margin-bottom: 2px;width: 33%;float: left;text-align: center;">${title}</div>
<div style="background-color: #FF0000;margin-bottom: 2px;width: 34%;text-align: right;float: left;"><a href="${pageContext.servletContext.contextPath}/service/showSearchPage/${serviceId}?channel=smd">Search</a></div>
</div>  --%> 		
<%-- <div style="text-align: center;font-size: 12;color: white;background-color: #FF0000;width: 100%;margin-bottom: 2px;">${title}</div> --%>


<div align="left"><img alt="noImage"
					src="${pageContext.servletContext.contextPath}/resources/images/Faltu.jpg"
					width="160px" height="auto">
</div>

<c:if test="${not empty title }">
<div style="text-align: center;font-size: 14px;font-weight:bold;margin-bottom:3px;color: black;background-color: yellow;width: 100%;border:1px solid black;">${title}</div>
</c:if>
<div style="text-align: center;margin-bottom:3px;color: black;background-color: yellow;width: 100%;border:1px solid black;"><a href="${pageContext.servletContext.contextPath}/service/showSearchPage/${serviceId}?channel=smd" style="font-size: 14px;font-weight:bold;">Search</a></div>
<%-- <div style="background-color: yellow;font-size: 14px;font-weight:bold;margin-bottom: 2px;width: 100%;text-align: center;border:1px solid black;"><a href="${pageContext.servletContext.contextPath}/service/showSearchPage/${serviceId}?channel=smd">Search</a></div> --%>


