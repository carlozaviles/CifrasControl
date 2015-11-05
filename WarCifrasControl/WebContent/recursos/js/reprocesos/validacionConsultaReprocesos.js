$(document).ready(function(){
	 $('#numeroCuenta').keyup(function (){
			this.value = $.trim(this.value).substr(0, getNumCuentaLength());
			this.value = (this.value + '').replace(getNumCuentaFormat(), '');
		  });
	$("#consultaReproceso").click(function(evt){
		
		if($.trim($('#numeroCuenta').val()) === ''){
			jAlert("El campo 'N\u00famero de Cuenta' debe ser informado.", 
					'Faltan campos por informar', 'Alerta', '');
			return;
		}
		if($('#mes').val() === 'NONE'){
			jAlert("El campo 'Mes' debe ser informado.", 
					'Faltan campos por informar', 'Alerta', '');
			return;
		}
		if($('#anio').val() === 'NONE'){
			jAlert("El campo 'A\u00f1o' debe ser informado.", 
					'Faltan campos por informar', 'Alerta', '');
			return;
		}
		if($('#productoSeleccionado').val() === 'NONE'){
			jAlert("El campo 'Aplicativo' debe ser informado.", 
					'Faltan campos por informar', 'Alerta', '');
			return;
			}
		if(!validarNumCuenta($('#numeroCuenta'))){
			jAlert("La longitud del numero de cuenta es invalido.",
	    			'Error en el formato','Alerta', '');
			return;
	    }
		$('#consultaReprocesoForm').submit();
	});	
});


function permiteUpperLista(permiso, inputActual) {

	var nombreTexto = inputActual.value;
	var permitidos = /^[0-9A-Za-z ]+$/;
	if (nombreTexto.length != 0) {
		if (permiso == 3) {
			permitidos = /^[0-9]+$/;
		}	
		if (permitidos.test(nombreTexto)) {
			inputActual.value = nombreTexto.toUpperCase();
		} else {
			jAlert('Letras no validas en este tipo de campo.',
	    			'Error en el formato','Alerta', '');
			inputActual.value = "";
			return false;
		}
	}

}
