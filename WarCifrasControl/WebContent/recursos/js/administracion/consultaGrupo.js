$(document).ready(function(){
    
	if($('input[name="idGrupo"]').length){
		$('#detalleGrupo').show();
	}else{
		$('#detalleGrupo').hide();
	}
	
	$('#detalleGrupo').click(function(){  
    	if($('input[name="idGrupo"]').is(':checked')){
    		$('#modificar').submit();
        }else{
        	jError('Debe seleccionar un grupo para mostrar el detalle', 'Falta seleccionar un grupo', 'Error', '-Seleccione un grupo');
        }    	
    });
    
    
});