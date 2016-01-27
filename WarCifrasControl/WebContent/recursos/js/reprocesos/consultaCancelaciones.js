$(document).ready(function(){
	$('#tablaCancelaciones').css('width', '100%');
	$("#tablaCancelaciones tbody tr:even").addClass('odd1');
	$('#tablaCancelaciones tbody tr:odd').addClass('odd2');
	$('.pagebanner').hide();
	$('.pagelinks').hide();	
	
$('#exportarCancelaciones').click(function(){
		
		var form = document.createElement("form");
	      form.action = "../reprocesos/consultaCancelacionesExcel.xls";
	      form.method = "POST";
	      form.style = "toolbar=yes, scrollbars=yes, resizable=yes, top=500, left=500, width=400, height=400";
	      form.target = "_blank" || "_self";
	      
	    
	      
	      var mes = document.createElement("INPUT");
	      mes.name = 'mes';
	      mes.value =$.trim($('#mes').val());
	      form.appendChild(mes);
	      
	     
	     
	      
	      form.style.display = 'none';
	      document.body.appendChild(form);
	      form.submit();
	});
	
});