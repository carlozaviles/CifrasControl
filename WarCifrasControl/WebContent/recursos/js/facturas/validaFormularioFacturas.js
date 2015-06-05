$(document).ready(function(){
	$('#camposObligatorios').hide();
	
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