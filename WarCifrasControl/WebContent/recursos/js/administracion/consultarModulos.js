$(document).ready(function(){
    
	if($('input[name="idModulo"]').length){
		$('#detalleModulo').show();
	}else{
		$('#detalleModulo').hide();
	}
	
	$('#detalleModulo').click(function(){  
    	if($('input[name="idPantalla"]').is(':checked')){
    		$('#modificar').submit();
        }else{
        	jError('Debe seleccionar un m&oacute;dulo para mostrar el detalle', 'Falta seleccionar un m&oacute;dulo', 'Error', '-Seleccione un m&oacute;');
        }    	
    });
    
    
});