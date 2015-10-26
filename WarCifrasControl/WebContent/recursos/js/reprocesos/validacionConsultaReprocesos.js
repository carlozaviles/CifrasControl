$(document).ready(function(){
	$("#consultaReproceso").click(function(evt){
		
		if($.trim($('#numeroCuenta').val()) === ''){
			alert("El campo 'N\u00famero de Cuenta' debe ser informado.");
			return;
		}
		if($('#mes').val() === 'NONE'){
			alert("El campo 'Mes' debe ser informado.");
			return;
		}
		if($('#anio').val() === 'NONE'){
			alert("El campo 'A\u00f1o' debe ser informado.");
			return;
		}
		if($('#productoSeleccionado').val() === 'NONE'){
			alert("El campo 'Aplicativo' debe ser informado.");
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
			alert('Letras no validas en este tipo de campo.');
			inputActual.value = "";
			return false;
		}
	}

}
