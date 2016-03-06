<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

 <% int i=0; %>
<style>
.txtbox {
 border: none;
 width: 100%;
}
input {
 font-size: 17px;
 height: 30px;
}
table {
 background: none repeat scroll 0 0 #abcdef;
 border: 1px solid #000;
}
</style>
<script>

var j = 0;
$(document).ready(function(){
	var k = $('#listsize').val(); 
	//alert(k);
    $('.plusbtn').click(function() {
    	//alert("k:"+k);
        $(".test").append('<tr><td><input name="serviceKeyPriceList['+k+'].serviceKeyPriceId" type="text" class="txtbox" value="${serviceKeyPrice.serviceKeyPriceId}" /></td><td><input name="serviceKeyPriceList['+k+'].serviceKeyPriceName" type="text" class="txtbox" value="${serviceKeyPrice.serviceKeyPriceName}" /></td><td><input name="serviceKeyPriceList['+k+'].serviceKeyPriceType" type="text" class="txtbox" value="${serviceKeyPrice.serviceKeyPriceType}" /></td><td><input name="serviceKeyPriceList['+k+'].price" type="text" class="txtbox" value="${serviceKeyPrice.price}" /></td><td><input name="serviceKeyPriceList['+k+'].duration" type="text" class="txtbox" value="${serviceKeyPrice.duration}" /></td><td><input name="serviceKeyPriceList['+k+'].tokens" type="text" class="txtbox" value="${serviceKeyPrice.tokens}" /></td></tr>');
        k++;
    });
  $('.minusbtn').click(function() {
      if($(".test tr").length != 2)
        {
           $(".test tr:last-child").remove();
           k = k--;alert("rem::"+k);
        }
     else
        {
           alert("You cannot delete first row");
        }
   });
});

</script>

<div>
 <input type="button" value="Add" class="plusbtn" />
 <input type="button" value="Remove" class="minusbtn" />
</div>
<form:form method="post" action="saveServiceKeyPrices" modelAttribute="serviceKeyPriceList">
<table width="50%" border="1" cellpadding="1" cellspacing="1" class="test">
 <tr>
 <td>ID</td>
 <td>Name.</td>
 <td>Price Type</td>
 <td>Price</td>
 <td>Duration</td>
 <td>Tokens</td>
 </tr>

<c:forEach items="${serviceKeyPriceListContainer.serviceKeyPriceList}" var="serviceKeyPrice" varStatus="status">
	<tr>
		<td><input name="serviceKeyPriceList[${status.index}].serviceKeyPriceId" type="text" class="txtbox" value="${serviceKeyPrice.serviceKeyPriceId}" /></td>
		<td><input name="serviceKeyPriceList[${status.index}].serviceKeyPriceName" type="text" class="txtbox" value="${serviceKeyPrice.serviceKeyPriceName}" /></td>
		<td><input name="serviceKeyPriceList[${status.index}].serviceKeyPriceType" type="text" class="txtbox" value="${serviceKeyPrice.serviceKeyPriceType}" /></td>
		<td><input name="serviceKeyPriceList[${status.index}].price" type="text" class="txtbox" value="${serviceKeyPrice.price}" /></td>
		<td><input name="serviceKeyPriceList[${status.index}].duration" type="text" class="txtbox" value="${serviceKeyPrice.duration}" /></td>
		<td><input name="serviceKeyPriceList[${status.index}].tokens" type="text" class="txtbox" value="${serviceKeyPrice.tokens}" /></td>
	</tr>
	<%i++; %>
</c:forEach> 
</table>
<input type="hidden" name="listsize" id="listsize" value="<%= i %>" />
<input type="submit" value="Save" id="submit" />
</form:form>
