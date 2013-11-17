<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<div class="panel-header panel-title">Revenue List</div>
<table border="1">
<tr>
	<th>Purchase Time</th>
	<th>Operator</th>
	<th>Media Name</th>
	<th>Downloads</th>
	<th>Revenue</th>
</tr>
<c:forEach items="${RevenueList}" var="mis">
	<tr>
		<td><c:out value="${mis.purchaseTime}" /></td>
		<td><c:out value="${mis.operator}" /></td>
		<td><c:out value="${mis.content}" /></td>
		 <td><c:out value="${mis.downloads}" /></td> 
		 <td><c:out value="${mis.revenue}" /></td>
	</tr>
</c:forEach>
</table>