$(document).ready(function(){
	if($('#camposObligatorios').length){
		$('#camposObligatorios').hide();
	}
	
	$('#tableExport').hide();
	$('.pagebanner').hide();
	$('.pagelinks').hide();
	$('.exportlinks').hide();
	var url = $('.exportlinks a:nth-child(2)').attr('href');
	$('#exportarReporte').attr('href', url);
	
	if($('#sinDatos').length){
		if($.trim($('#sinDatos').val()) != ''){
			jAlert($('#gralSinDatos').val(), $('#gralSinDatosRespuesta').val(), 'Alerta', $('#gralModificarFiltros').val());
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
			$('#camposObligatorios').show();
		}else{
			 $('#formularioFacturas').submit();
		}
	});
	
});