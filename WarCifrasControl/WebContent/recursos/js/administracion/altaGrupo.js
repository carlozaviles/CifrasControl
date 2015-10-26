$(document).ready(function(){
	
	$('#nombreRequerido').hide();
	$('#descripcionRequerido').hide();
	$('#pantallaRequerida').hide();
	$('#usuarioAdministrador').attr('disabled', true);
	
	$('#tipoPerfil').change(function(){
		if($('#tipoPerfil').val() === 'O'){
			$('#usuarioAdministrador').attr('disabled', false);
		}else{
			$('#usuarioAdministrador').attr('disabled', true);
		}
	});
	
    $('#btnAltaGrupo').click(function(){
    	var error = false;
    	if ($.trim($('#nombreGrupo').val()) === '' || $('#nombreGrupo').val().length > 50) {
    		$('#nombreRequerido').show();
            error = true;
        }else{
        	$('#nombreRequerido').hide();
        }
    	if ($('#descripcionGrupo').val().length == 0 || $.trim($('#descripcionGrupo').val()) === '' || $('#descripcionGrupo').val().length > 300 ) {
    		$('#descripcionRequerido').show();
            error = true;
        }else{
        	$('#descripcionRequerido').hide();
        }
    	if($('input[name="pantallaActiva"]').is(':checked')){
    		$('#pantallaRequerida').hide();
        }else{
        	$('#pantallaRequerida').show();
            error = true;
        }
    	
    	if($('#tipoPerfil').val() === 'NONE'){
    		error = true;
    	}
    	
    	if($('#tipoPerfil').val() === 'O' && $('#usuarioAdministrador').val() === 'NONE'){
    		error = true;
    		jAlert('', 'Se requiere un perfil Administrador.', 'Alerta', 
    				'Cuando se selecciona un tipo de perfil operativo, se requiere informar a que grupo administrativo pertenece.');
    		return;
    	}
    	
    	if(error == true){
    		jAlert($('#gralCamposObligatorios').val(), $('#gralFaltanCampos').val(), 'Alerta', $('#gralVerifique').val());
    	}else{
            $('#altaGrupo').submit();
    	}
    	
    });
    
    $("#regresar").click(function(event) {
    	$('#altaGrupo').attr('action', 'consultarGrupos.do');
        $('#altaGrupo').submit();
	});
});