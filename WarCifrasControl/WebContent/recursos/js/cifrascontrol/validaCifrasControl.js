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
		if($('#sinDatos').val().trim() != ''){
			jAlert($('#gralSinDatos').val(), $('#gralSinDatosRespuesta').val(), 'Alerta', $('#gralModificarFiltros').val());
		}
	}
	
	if($('#tamanioDetalle').length){
		if($('#tamanioDetalle').val().trim() === '0'){
			$('#linkDetalle').hide();
		}
	}
	
	$('#linkDetalle').click(function(){
		 $('#formularioFacturas').submit();
	});
	
	$('#aceptarFormulario').click(function(){
		var isError = false;
		if($('#aplicativo').val().trim() === ''){
			isError = true;
		}
		if($('#mes').val().trim() === ''){
			isError = true;
		}
		if($('#anio').val().trim() === ''){
			isError = true;
		}
		if(isError === true){
			$('#camposObligatorios').show();
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