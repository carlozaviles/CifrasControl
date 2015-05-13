$(document).ready(function(){
	
	$('#nombreRequerido').hide();
	$('#descripcionRequerido').hide();
	$('#moduloRequerido').hide();
	
    $('#btnGuardaPantalla').click(function(){
    	var error = false;
    	if ($('#nombrePantalla').val().trim() === '' || $('#nombrePantalla').val().length > 50) {
    		$('#nombreRequerido').show();
            error = true;
        }else{
        	$('#nombreRequerido').hide();
        }
    	if ($('#descripcionPantalla').val().length == 0 || $.trim($('#descripcionPantalla').val()) === '' || $('#descripcionPantalla').val().length > 300 ) {
    		$('#descripcionRequerido').show();
            error = true;
        }else{
        	$('#descripcionRequerido').hide();
        }
    	
    	if($('input[name="moduloActivo"]').is(':checked')){
    		$('#moduloRequerido').hide();
        }else{
        	$('#moduloRequerido').show();
            error = true;
        }
    	
    	if(error == true){
    		jAlert($('#gralCamposObligatorios').val(), $('#gralFaltanCampos').val(), 'Alerta', $('#gralVerifique').val());
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
    	$('#modificarPantalla').attr('action', 'consultarPantallas.do');
        $('#modificarPantalla').submit();
	});
});