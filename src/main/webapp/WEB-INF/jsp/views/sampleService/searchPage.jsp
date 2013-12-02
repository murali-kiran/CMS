<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="${pageContext.servletContext.contextPath}/service/searchMediaByTag/${serviceId}/1" >

<input type="hidden" name="channel" value="smd">
<div align="center" style="vertical-align: middle;height: 100px;">
<c:if test="${not empty error}">${error}</c:if>
<input type="text" name="tag"/> &nbsp; <input type="submit" value="Search"/>
</div>
</form>