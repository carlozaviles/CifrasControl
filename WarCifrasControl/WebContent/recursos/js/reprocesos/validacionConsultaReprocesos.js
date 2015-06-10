$(document).ready(function(){
	$("#consultaReproceso").click(function(evt){
		if($('#mes').val() === 'NONE'){
			alert("El campo mes debe ser informado.");
			return;
		}
		if($('#anio').val() === 'NONE'){
			alert("El campo a\u00f1o debe ser informado.");
			return;
		}
		$('#consultaReprocesoForm').submit();
	});	
});