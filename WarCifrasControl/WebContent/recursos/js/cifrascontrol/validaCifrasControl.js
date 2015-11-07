$(document).ready(function(){
	if($('#camposObligatorios').length){
		$('#camposObligatorios').hide();
	}
	if($('#tableExport').length){
		$('#tableExport').hide();
	}
	if($('.pagebanner').length){
		$('.pagebanner').hide();
	}
	if($('.pagelinks').length){
		$('.pagelinks').hide();
	}
	
	if($('#sinDatos').length){
		if($.trim($('#sinDatos').val()) != ''){
			jAlert($('#gralSinDatos').val(), $('#gralSinDatosRespuesta').val(), 'Alerta', $('#gralModificarFiltros').val());
		}
	}
	
	if($('#tamanioDetalle').length){
		if($.trim($('#tamanioDetalle').val()) === '0'){
			$('#linkDetalle').hide();
		}
	}
	$('#aceptarFormulario').click(function(){
		var isError = false;
		if($.trim($('#aplicativo').val()) === ''){
			isError = true;
		}
		if($.trim($('#mes').val()) === ''){
			isError = true;
		}
		if($.trim($('#anio').val()) === ''){
			isError = true;
		}
		if(isError === true){
			jAlert('Todos los campos deben de ser informados para realizar la consulta.', 'Faltan campos por informar.','Alerta', '');
		}else{
			var txt = $("#aplicativo option:selected").text();
			var txtAnio = $("#anio option:selected").text();
			var txtMes = $("#mes option:selected").text();
			$('#productoText').val(txt);
			$('#periodoText').val(txtMes+' '+txtAnio);
			 $('#formularioFacturas').submit();
		}
	});
	
});