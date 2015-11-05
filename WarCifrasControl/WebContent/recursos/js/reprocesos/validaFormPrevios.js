/**
 * 
 */
$(document).ready(function(){
	$('#numeroCuenta').keyup(function (){
		this.value = $.trim(this.value).substr(0, getNumCuentaLength());
		this.value = (this.value + '').replace(getNumCuentaFormat(), '');
	  });
	$('#aceptarFormulario').click(function(){
		if($('#aplicativo').val() === 'NONE' || $('#mes').val() === 'NONE' || $('#anio').val() === 'NONE'){
			jAlert('Todos los campos deben ser informados para realizar la consulta', 
					'Faltan campos por informar', 'Alerta', '');
			return;
		}
		if(!validarNumCuenta($('#numeroCuenta'))){
	    	jAlert('La longitud del numero de cuenta es invalido.',
	    			'Error en el formato','Alerta', '');
	    	return;
	    }
		
		$('#formularioPrevios').submit();
	});
});