$(document).ready(function (){
  $('#numero-cuenta').keyup(function (){
	this.value = $.trim(this.value).substr(0, getNumCuentaLength());
	this.value = (this.value + '').replace(getNumCuentaFormat(), '');
  });
  
  $('#aceptarFormularioDatos').click(function(event) {
	    if ($.trim($('#numero-cuenta').val()) === '') {
	    	jAlert('El n\u00famero de cuenta debe ser informado.', 
					'Faltan campos por informar', 'Alerta', '');
	        return;
	    } 
	    if(!validarNumCuenta($('#numero-cuenta'))){
	    	jAlert('La longitud del numero de cuenta es invalido.', 
	    			'Error en el formato', 'Alerta', '');
	    	 return;
	    }
	    $('#numero-cuenta').val($.trim($('#numero-cuenta').val()));
	    $('form[name="consultaDatosPersonales"]').submit();
	    
	});

	
});



 