$(document).ready(function (){
  $('#numero-cuenta').keyup(function (){
	this.value = $.trim(this.value).substr(0, 11);
	this.value = (this.value + '').replace(/[^0-9]/g, '');
  });
  
  $('#aceptarFormularioDatos').click(function(event) {
	    if ($.trim($('#numero-cuenta').val()) === '') {
	        alert('El n\u00famero de cuenta debe ser informado.');
	        return;
	    } else if($.trim($('#numero-cuenta').val()).length != 11){
	    	alert("La longitud del numero de cuenta debe ser de 11.");
	    }else {
	    	$('#numero-cuenta').val($.trim($('#numero-cuenta').val()));
	        $('form[name="consultaDatosPersonales"]').submit();
	    }
	});

	$('#solicitudReproceso').click(function(event){
		if($('#producto').val() === 'NONE'){
			alert("El campo producto debe ser informado.");
			return;
		}
		if($('#mes').val() === 'NONE'){
			alert("El campo mes debe ser informado.");
			return;
		}
		if($('#anio').val() === 'NONE'){
			alert("El campo a\u00f1o debe ser informado.");
			return;
		}
		if($('#tipoMovimiento').val() === 'NONE'){
			alert("El campo Tipo Movimiento debe ser informado.");
			return;
		}
		$('#solicitudReprocesoForm').submit();
	});
});



 