$(document).ready(function(){
	$('#aceptarFormulario').click(function(){
		if($('#aplicativo').val() === 'NONE' || $('#mes').val() === 'NONE' || $('#anio').val() === 'NONE'){
			jAlert('Todos los campos deben ser informados para realizar la consulta', 
					'Faltan campos por informar', 'Alerta', '');
		}else{
			$('#formularioIncidencias').submit();
		}
	});
});