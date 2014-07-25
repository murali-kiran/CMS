<%@ page contentType="text/html; charset=UTF-8"%>
<!-- <div style="padding-top: 2px;background-color: #DF7401;color: white;width: 100%;text-align: center;height: 23px;">
	<input type="text" height="18px"> Search
	
</div> -->

<form action="${pageContext.servletContext.contextPath}/vdo/searchMediaByTag/${serviceId}/1" >

<input type="hidden" name="channel" value="smd">
<div style="padding-top: 2px;background-color: #DF7401;color: white;width: 100%;text-align: center;height: 23px;">
${error}<input type="text" name="tag" height="18px"> &nbsp; <input type="submit" value="Search" style="background-color: #822DD7"/>
</div>
</form>

<div style="padding-bottom: 2px;padding-top: 2px;background-color: #094EA1;color: white;width: 100%;text-align: center;">
	&copy; Sumadga
</div>