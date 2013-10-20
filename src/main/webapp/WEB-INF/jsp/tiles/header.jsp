<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8"%>



<!-- <div style="width: 70%;float: left;border: 2px;">&nbsp;</div> -->

 <table style="width: 100%;height: 145px;s" cellspacing="0" cellpadding="0"  >
  <tr>
  <td rowspan="2">
  <div style="background-image: url('/CMS/resources/logo/SumadgaLogo.png');height: 145px; width: 320px;background-repeat: no-repeat;float: left;border: 0px;border-collapse: collapse;border-spacing: 0px;"></div>
  </td>
  </tr>
   <tr>
	<sec:authorize access="isAuthenticated()">
				<td style="width: 200px; vertical-align: top; padding-right: 5px; padding-top: 5px;color:#ffffff">
					<sec:authentication property="principal.username" /> <br/>
					<sec:authorize access="isAuthenticated()">
					<a href="${pageContext.servletContext.contextPath}/logout" style="color:#9966cc;font-size: 12px;">logout</a>
					</sec:authorize>
				</td>	
	</sec:authorize>					
	</tr>
	
</table>
