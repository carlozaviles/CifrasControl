$(document).ready(function(){
	if($('#camposObligatorios').length){
		$('#camposObligatorios').hide();
	}
	
	//$('#tableExport').hide();
	$('.pagebanner').hide();
	$('.pagelinks').hide();
	$('.exportlinks').hide();
	//var url = $('.exportlinks a:nth-child(2)').attr('href');
	//$('#exportarReporte').attr('href', url);
	
	if($('#sinDatos').length){
		if($.trim($('#sinDatos').val()) != ''){
			jAlert($('#gralSinDatos').val(), $('#gralSinDatosRespuesta').val(), 'Alerta', $('#gralModificarFiltros').val());
		}
	}

	$('#aceptarFormulario').click(function(){
		var isError = false;
		if($.trim($('#aplicativo').val()) === ''){
			isError = true;
		}
		if($.trim($('#mes').val()) === ''){
			isError = true;
		}
		if($.trim($('#anio').val()) === ''){
			isError = true;
		}
		if(isError === true){
			jAlert('Todos los campos deben de ser informados para realizar la consulta.', 'Faltan campos por informar.','Alerta', '');
		}else{
			 $('#formularioFacturas').submit();
		}
	});
	
	$('#exportarReporte').click(function(){
		//alert("Exportar");
		var form = document.createElement("form");
	      form.action = "../facturas/consultaFacturasExcel.xls";
	      form.method = "POST";
	      form.style = "toolbar=yes, scrollbars=yes, resizable=yes, top=500, left=500, width=400, height=400";
	      form.target = "_blank" || "_self";
	      
	      var aplicativo = document.createElement("INPUT");
	      aplicativo.name = 'aplicativo';
	      aplicativo.value = $.trim($('#aplicativo').val());
	      form.appendChild(aplicativo);
	      
	      var  comPeriodo=$.trim($('#periodo').val()).split('-');
	      
	      var mes = document.createElement("INPUT");
	      mes.name = 'mes';
	      mes.value =$.trim($('#mes').val());
	      form.appendChild(mes);
	      
	      var mesText = document.createElement("INPUT");
	      mesText.name = 'mesText';
	      mesText.value =comPeriodo[0];
	      form.appendChild(mesText);
	      
	      var anio = document.createElement("INPUT");
	      anio.name = 'anio';
	      anio.value =comPeriodo[1];
	      form.appendChild(anio);
	      
	      form.style.display = 'none';
	      document.body.appendChild(form);
	      form.submit();
	});
	
	
	$('#exportarReporteNotas').click(function(){
		
		var form = document.createElement("form");
	      form.action = "../facturas/consultaNotasCreditoExcel.xls";
	      form.method = "POST";
	      form.style = "toolbar=yes, scrollbars=yes, resizable=yes, top=500, left=500, width=400, height=400";
	      form.target = "_blank" || "_self";
	      
	      var aplicativo = document.createElement("INPUT");
	      aplicativo.name = 'aplicativo';
	      aplicativo.value = $.trim($('#aplicativo').val());
	      form.appendChild(aplicativo);
	      
	      var  comPeriodo=$.trim($('#periodo').val()).split('-');
	      
	      var mes = document.createElement("INPUT");
	      mes.name = 'mes';
	      mes.value =$.trim($('#mes').val());
	      form.appendChild(mes);
	      
	      var mesText = document.createElement("INPUT");
	      mesText.name = 'mesText';
	      mesText.value =comPeriodo[0];
	      form.appendChild(mesText);
	      
	      var anio = document.createElement("INPUT");
	      anio.name = 'anio';
	      anio.value =comPeriodo[1];
	      form.appendChild(anio);
	      
	      form.style.display = 'none';
	      document.body.appendChild(form);
	      form.submit();
	});
	
	
$('#exportarReporteDivisas').click(function(){
		
		var form = document.createElement("form");
	      form.action = "../facturas/consultaDivisasExcel.xls";
	      form.method = "POST";
	      form.style = "toolbar=yes, scrollbars=yes, resizable=yes, top=500, left=500, width=400, height=400";
	      form.target = "_blank" || "_self";
	      
	      var aplicativo = document.createElement("INPUT");
	      aplicativo.name = 'aplicativo';
	      aplicativo.value = $.trim($('#aplicativo').val());
	      form.appendChild(aplicativo);
	      
	      var  comPeriodo=$.trim($('#periodo').val()).split('-');
	      
	      var mes = document.createElement("INPUT");
	      mes.name = 'mes';
	      mes.value =$.trim($('#mes').val());
	      form.appendChild(mes);
	      
	      var mesText = document.createElement("INPUT");
	      mesText.name = 'mesText';
	      mesText.value =comPeriodo[0];
	      form.appendChild(mesText);
	      
	      var anio = document.createElement("INPUT");
	      anio.name = 'anio';
	      anio.value =comPeriodo[1];
	      form.appendChild(anio);
	      
	      form.style.display = 'none';
	      document.body.appendChild(form);
	      form.submit();
	});


$('#exportarReporteRecibos').click(function(){
	
	var form = document.createElement("form");
      form.action = "../facturas/consultaRecibosExcel.xls";
      form.method = "POST";
      form.style = "toolbar=yes, scrollbars=yes, resizable=yes, top=500, left=500, width=400, height=400";
      form.target = "_blank" || "_self";
      
      var aplicativo = document.createElement("INPUT");
      aplicativo.name = 'aplicativo';
      aplicativo.value = $.trim($('#aplicativo').val());
      form.appendChild(aplicativo);
      
      var  comPeriodo=$.trim($('#periodo').val()).split('-');
      
      var mes = document.createElement("INPUT");
      mes.name = 'mes';
      mes.value =$.trim($('#mes').val());
      form.appendChild(mes);
      
      var mesText = document.createElement("INPUT");
      mesText.name = 'mesText';
      mesText.value =comPeriodo[0];
      form.appendChild(mesText);
      
      var anio = document.createElement("INPUT");
      anio.name = 'anio';
      anio.value =comPeriodo[1];
      form.appendChild(anio);
      
      form.style.display = 'none';
      document.body.appendChild(form);
      form.submit();
});


	
});