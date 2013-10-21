 $(document).ready(function(){
	 
	 $("body").append("<div id='error_div_id'></div>");
	 
  $( ".validate" ).mouseover(function() {
	   var input = $(this);
	   var v = input.val();
	 
	     var position = $(input).offset();
		 var left = position.left+200;
		 var top = position.top-2;
		 /* alert(left+" "+right); */
		
		 var tmp="<div tabindex='-1' class='tooltip tooltip-right' style='display: block; color: rgb(, 0, 0); border-top-color: rgb(204, 153, 51);"+ 
	    	"border-right-color: rgb(204, 153, 51); border-bottom-color: rgb(204, 153, 51); border-left-color: rgb(204, 153, 51);"+ 
	    	"background-color: rgb(255, 255, 204); left: "+left+"px; top: "+top+"px; '>"+
	    	"<div class='tooltip-content'>This field is required.</div>"+
	    	"<div class='tooltip-arrow-outer' style='border-right-color: rgb(204, 153, 51);'></div>"+
	    	"<div class='tooltip-arrow' style='border-right-color: rgb(255, 255, 204);'></div></div>";
	    	
	     
		 if(v == null || v == -1 || v == "" || v.trim().length == 0){
	               $( "#error_div_id" ).html(tmp);
		 }
	  
  });
  
  $( ".validate" ).mouseout(function() {
	  $("#error_div_id").empty();
	  
  });
  
  
$(".calender").change(function(){
	  
	  var value = $(this).val();
	  
	  var re = /^\d{1,2}\/\d{1,2}\/\d{4}$/;
	  if (!re.test(value)) {
		  
		     var position = $(this).offset();
			 var left = position.left+200;
			 var top = position.top-2;
			
			 
			 var tmp="<div tabindex='-1' class='tooltip tooltip-right' style='display: block; color: rgb(, 0, 0); border-top-color: rgb(204, 153, 51);"+ 
		    	"border-right-color: rgb(204, 153, 51); border-bottom-color: rgb(204, 153, 51); border-left-color: rgb(204, 153, 51);"+ 
		    	"background-color: rgb(255, 255, 204); left: "+left+"px; top: "+top+"px; '>"+
		    	"<div class='tooltip-content'>Enter the date dd/mm/yyyy</div>"+
		    	"<div class='tooltip-arrow-outer' style='border-right-color: rgb(204, 153, 51);'></div>"+
		    	"<div class='tooltip-arrow' style='border-right-color: rgb(255, 255, 204);'></div></div>";
		     
		    	$(this).val("");
		    	      
		        $( "#error_div_id" ).html(tmp); 
		               
	  }
	  
  });


  
 });

$(function() {
    $( ".datepicker" ).datepicker();
  });
  
function sub(formId){

	 var flag = true; 
	 /* var formId = "ff"; */
	 //var myVar = $(formId).find('.validate');
	 
	 /* myVar.each(function(){ */
	
	  $("form#"+formId+" :input.validate").each(function(){
	   var input = $(this);
	   var v = input.val();
	 
	    /*alert(v);*/
	 
		 if(v == null || v == -1 || v == "" || v.trim().length == 0){
			 var position = $(input).offset();
			 var left = position.left+200;
			 var top = position.top-2;
			 var tmp="<div tabindex='-1' class='tooltip tooltip-right' style='display: block; color: rgb(, 0, 0); border-top-color: rgb(204, 153, 51);"+ 
		    	"border-right-color: rgb(204, 153, 51); border-bottom-color: rgb(204, 153, 51); border-left-color: rgb(204, 153, 51);"+ 
		    	"background-color: rgb(255, 255, 204); left: "+left+"px; top: "+top+"px; '>"+
		    	"<div class='tooltip-content'>This field is required.</div>"+
		    	"<div class='tooltip-arrow-outer' style='border-right-color: rgb(204, 153, 51);'></div>"+
		    	"<div class='tooltip-arrow' style='border-right-color: rgb(255, 255, 204);'></div></div>";
			 $( "#error_div_id" ).html(tmp );		 
			 
			 flag = false;
			 
			 return flag;
			 
		     }
		 
		 }) ;
		 
	return flag;
	
	
}

