$(document).ready(function(){
	
	$('#nombreRequerido').hide();
	$('#descripcionRequerido').hide();
	$('#pantallaRequerida').hide();
	
    $('#btnAltaGrupo').click(function(){
    	var error = false;
    	if ($('#nombreGrupo').val().trim() === '' || $('#nombreGrupo').val().length > 50) {
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
    	
    	if(error == true){
    		jAlert('Los campos marcados son obligatorios. Recuerde que debe completarlos.', 'Faltan campos por completar', 'Alerta', '-Verifique que los campos obligatorios esten completados.');
    	}else{
            $('#altaGrupo').submit();
    	}
    	
    });
    
    $("#regresar").click(function(event) {
    	$('#altaGrupo').attr('action', 'consultarGrupos.do');
        $('#altaGrupo').submit();
	});
});