/**
 * 
 */
$(document).ready(function(){
	$('#aceptarFormulario').click(function(){
		if($('#aplicativo').val() === 'NONE' || $('#mes').val() === 'NONE' || $('#anio').val() === 'NONE'){
			jAlert('Todos los campos deben ser informados para realizar la consulta', 
					'Faltan campos por informar', 'Alerta', '');
			return;
		}
		if(!$('#numeroCuenta').val().match('^[0-9]{11}$')){
			jAlert('El N\u00famero de Cuenta no tiene el formato correcto.', '', 'Alerta', '');
			return;
		}
		
		$('#formularioPrevios').submit();
	});
});