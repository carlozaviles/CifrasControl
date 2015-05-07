$(document).ready(function(){
    
	if($('input[name="idPantalla"]').length){
		$('#detallePantalla').show();
	}else{
		$('#detallePantalla').hide();
	}
	
	$('#detallePantalla').click(function(){  
    	if($('input[name="idPantalla"]').is(':checked')){
    		$('#modificar').submit();
        }else{
        	jError('Debe seleccionar una pantalla para mostrar el detalle', 'Falta seleccionar una pantalla', 'Error', '-Seleccione una pantalla');
        }    	
    });
    
    
});