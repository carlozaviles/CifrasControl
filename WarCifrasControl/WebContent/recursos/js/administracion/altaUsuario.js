$(document).ready(function(){
	
	$('#idRequerido').hide();
	$('#grupoRequerido').hide();
	
    $('#btnAltaUsuario').click(function(){
    	var error = false;
    	if ($.trim($('#idUsuario').val()) === '' || $('#idUsuario').val().length > 10) {
    		$('#idRequerido').show();
            error = true;
        }else{
        	$('#idRequerido').hide();
        }
    	if($('input[name="idGrupo"]').is(':checked')){
    		$('#grupoRequerido').hide();
        }else{
        	$('#grupoRequerido').show();
            error = true;
        }
    	
    	if(error == true){
    		jAlert($('#gralCamposObligatorios').val(), $('#gralFaltanCampos').val(), 'Alerta', $('#gralVerifique').val());
    	}else{
            $('#altaUsuario').submit();
    	}
    	
    });
    
    $("#regresar").click(function(event) {
    	$('#altaUsuario').attr('action', 'consultarUsuarios.do');
        $('#altaUsuario').submit();
	});
});