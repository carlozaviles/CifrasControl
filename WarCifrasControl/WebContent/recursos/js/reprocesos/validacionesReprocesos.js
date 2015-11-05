$(document).ready(function (){

	$('#solicitudReproceso').click(function(event){
		if($('#producto').val() === 'NONE'){
			alert("El campo producto debe ser informado.");
			return;
		}
		if($('#mes').val() === 'NONE'){
			alert("El campo mes debe ser informado.");
			return;
		}
		if($('#anio').val() === 'NONE'){
			alert("El campo a\u00f1o debe ser informado.");
			return;
		}
		if($('#tipoMovimiento').val() === 'NONE'){
			alert("El campo Tipo Movimiento debe ser informado.");
			return;
		}
		$('#solicitudReprocesoForm').submit();
	});
});



 