$(document).ready(function(){
	$('#contenedorTablaReprocesos').css('overflow-x', 'scroll');
	$("#tablaReprocesos tbody tr:even").addClass('odd1');
	$('#tablaReprocesos tbody tr:odd').addClass('odd2');
	$('.pagebanner').hide();
	$('.pagelinks').hide();
	
	
$('#exportarInformeReprocesos').click(function(){
		
		var form = document.createElement("form");
	      form.action = "../reprocesos/realizaConsultaReprocesoExcel.xls";
	      form.method = "POST";
	      form.style = "toolbar=yes, scrollbars=yes, resizable=yes, top=500, left=500, width=400, height=400";
	      form.target = "_blank" || "_self";
	      
	   var anio = document.createElement("INPUT");
	   anio.name = 'anio';
	   anio.value = $.trim($('#anio').val());
	      form.appendChild(anio);
	      
	   var nCuenta = document.createElement("INPUT");
	   	   nCuenta.name = 'nCuenta';
	       nCuenta.value = $.trim($('#nCuenta').val());
	      form.appendChild(nCuenta);
	      
	
	      
	      var mes = document.createElement("INPUT");
	      mes.name = 'mes';
	      mes.value =$.trim($('#mes').val());
	      form.appendChild(mes);
	      
	      var producto = document.createElement("INPUT");
	      producto.name = 'producto';
	      producto.value =$.trim($('#producto').val());
	      form.appendChild(producto);
	     
	     
	      
	      form.style.display = 'none';
	      document.body.appendChild(form);
	      form.submit();
	});
});