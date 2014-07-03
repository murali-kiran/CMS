<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8"%>



<div id="templatemo_header_wrapper">
	<div id="templatemo_header">
    <div style="float: right;color:#ffffff;font-size: 12px;">
			<sec:authorize access="isAuthenticated()">
							<sec:authentication property="principal.username" /> <br/>
							<sec:authentication property="principal.authorities" /> <br/>
							<sec:authorize access="isAuthenticated()">
							<a href="${pageContext.servletContext.contextPath}/logout" style="color:#ffffff;font-size: 12px;">logout</a>
							</sec:authorize>
			</sec:authorize>					
	</div>
    	<div id="site_title">
            <!-- <h1><img src="resources/images_new/sumadga_logo.png" alt="logo" /></h1> -->
      	</div> <!-- end of site_title -->
        
        <div id="templatemo_menu">
        	
            <ul>
            	<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                <li><a href="${pageContext.servletContext.contextPath}/upload">Upload</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/appUpload">Game Upload</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/showSearch">Search</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/addGroup">Group</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/listMediaGroup">Group List</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/serviceList">Services</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/mis/show">MIS</a></li>
                </sec:authorize>
                
                <sec:authorize access="hasAnyRole('ROLE_USER')">
                <li><a href="${pageContext.servletContext.contextPath}/upload">Upload</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/showSearch">Search</a></li>
                
                <%-- <li><a href="${pageContext.servletContext.contextPath}/appUpload">Game Upload</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/addGroup">Group</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/listMediaGroup">Group List</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/serviceList">Services</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/mis/show">MIS</a></li> --%>
                </sec:authorize>
            </ul>    	
        </div> <!-- end of templatemo_menu -->
    
    </div>
</div>
