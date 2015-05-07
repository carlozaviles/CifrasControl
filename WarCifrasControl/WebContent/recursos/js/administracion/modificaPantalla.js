$(document).ready(function(){
	
	$('#nombreRequerido').hide();
	$('#descripcionRequerido').hide();
	
    $('#btnGuardaPantalla').click(function(){
    	var error = false;
    	if ($('#nombrePantalla').val().trim() === '' || $('#nombrePantalla').val().length > 50) {
    		$('#nombreRequerido').show();
            error = true;
        }else{
        	$('#nombreRequerido').hide();
        }
    	if ($('#descripcionPantalla').val().length == 0 || $.trim($('#descripcionPantalla').val()) === '' || $('#descripcionGrupo').val().length > 300 ) {
    		$('#descripcionRequerido').show();
            error = true;
        }else{
        	$('#descripcionRequerido').hide();
        }
    	
    	if(error == true){
    		jAlert('Los campos marcados son obligatorios. Recuerde que debe completarlos.', 'Faltan campos por completar', 'Alerta', '-Verifique que los campos obligatorios esten completados.');
    	}else{
    		$('#modificarPantalla').attr('action', 'modificarPantalla.do');
            $('#modificarPantalla').submit();
    	}
    	
    });
    $('#btnBorrarPantalla').click(function(){
        $('#modificarPantalla').attr('action', 'borrarPantalla.do');
        $('#modificarPantalla').submit();
    });
    
    $("#regresar").click(function(event) {
    	$('#modificarGrupo').attr('action', 'consultarGrupos.do');
        $('#modificarGrupo').submit();
	});
});