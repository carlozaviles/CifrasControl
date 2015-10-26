$(document).ready(function(){
	
	$('#nombreRequerido').hide();
	$('#descripcionRequerido').hide();
	
    $('#btnGuardaModulo').click(function(){
    	var error = false;
    	if ($.trim($('#nombreModulo').val()) === '' || $('#nombreModulo').val().length > 50) {
    		$('#nombreRequerido').show();
            error = true;
        }else{
        	$('#nombreRequerido').hide();
        }
    	if ($('#descripcionModulo').val().length == 0 || $.trim($('#descripcionModulo').val()) === '' || $('#descripcionModulo').val().length > 300 ) {
    		$('#descripcionRequerido').show();
            error = true;
        }else{
        	$('#descripcionRequerido').hide();
        }
    	
    	if(error == true){
    		jAlert($('#gralCamposObligatorios').val(), $('#gralFaltanCampos').val(), 'Alerta', $('#gralVerifique').val());
    	}else{
    		$('#modificarModulo').attr('action', 'altaModulo.do');
            $('#modificarModulo').submit();
    	}
    	
    });
    
    $("#regresar").click(function(event) {
    	$('#modificarGrupo').attr('action', 'consultarGrupos.do');
        $('#modificarGrupo').submit();
	});
});