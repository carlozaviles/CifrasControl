$(document).ready(function(){
	
	$('#nombreRequerido').hide();
	$('#descripcionRequerido').hide();
	$('#pantallaRequerida').hide();
	
    $('#btnModificaGpo').click(function(){
    	var error = false;
    	if($('input[name="pantallaActiva"]').is(':checked')){
    		$('#pantallaRequerida').hide();
        }else{
        	$('#pantallaRequerida').show();
            error = true;
        }
    	
    	if(error == true){
    		jAlert($('#gralCamposObligatorios').val(), $('#gralFaltanCampos').val(), 'Alerta', $('#gralVerifique').val());
    	}else{
    		$('#modificarGrupo').attr('action', 'modificarGrupo.do');
            $('#modificarGrupo').submit();
    	}
    	
    });
    $('#btnBorrarGpo').click(function(){
        $('#modificarGrupo').attr('action', 'borrarGrupo.do');
        $('#modificarGrupo').submit();
    });
    
    $("#regresar").click(function(event) {
    	$('#modificarGrupo').attr('action', 'consultarGrupos.do');
        $('#modificarGrupo').submit();
	});
});