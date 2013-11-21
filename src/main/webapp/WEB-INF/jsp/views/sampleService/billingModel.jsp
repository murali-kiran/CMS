<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
 function sendForm(){
	 document.billingForm.submit();
 }
</script>
</head>
<body onload="sendForm();">
	<form action="${billingModel.secretKeyOtherAPI}" method="post" name="billingForm">
		<input type="hidden" value="${billingModel.msisdn}" name="msisdn"> 
		<input type="hidden" value="${billingModel.operation}" name="operation"> <input
			type="hidden" value="${billingModel.operator}" name="operator"> <input
			type="hidden" value="${billingModel.password}" name="password"> <input
			type="hidden" value="${billingModel.productid}" name="productid"> <input
			type="hidden" value="${billingModel.redirecturl}" name="redirecturl"> <input
			type="hidden" value="${billingModel.requestid}" name="Requestid"> <input
			type="hidden" value="${billingModel.username}" name="username"> <input
			type="hidden" value="${billingModel.serviceKeypriceKey}" name="serviceKeypriceKey">
	</form>
</body>
</html>


