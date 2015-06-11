$(document).ready(function(){
	if($('#camposObligatorios').length){
		$('#camposObligatorios').hide();
	}
	
		$('#tableExport').hide();
	
		$('.pagebanner').hide();
	
		$('.pagelinks').hide();
	
	if($('#sinDatos').length){
		if($('#sinDatos').val().trim() != ''){
			jAlert($('#gralSinDatos').val(), $('#gralSinDatosRespuesta').val(), 'Alerta', $('#gralModificarFiltros').val());
		}
	}

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
			 $('#formularioFacturas').submit();
		}
	});
	
});