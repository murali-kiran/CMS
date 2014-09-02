<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="${pageContext.servletContext.contextPath}/service/searchMediaByTag/${serviceId}/1" >

<input type="hidden" name="channel" value="smd">
<div align="center" style="vertical-align: middle;height: 100px;">
<c:if test="${not empty error}">${error}</c:if>
<input type="text" name="tag" width="85%;"/> &nbsp; <input type="submit" value="Search" style="background-color: #822DD7"/>

</div>
</form>

<div style="width: 100%;clear: both;"><a href="${pageContext.servletContext.contextPath}/service/${serviceId}?channel=${channel}">&nbsp;Home</a></div>