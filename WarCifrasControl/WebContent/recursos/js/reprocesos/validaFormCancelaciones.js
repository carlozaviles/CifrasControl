/**
 * 
 */
$(document).ready(function(){
	$('#aceptarFormulario').click(function(){
		if($('#mes').val() === 'NONE'){
			jAlert('Todos los campos deben ser informados para realizar la consulta', 
					'Faltan campos por informar', 'Alerta', '');
			return;
		}
		$('#formularioCancelaciones').submit();
	});
});