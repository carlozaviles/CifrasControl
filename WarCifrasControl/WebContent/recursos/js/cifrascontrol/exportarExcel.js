$(document).ready(function(){
	$('#exportarExcel').click(function(){ 
	
	  var form = document.createElement("form");
      form.action = "../cifrascontrol/excelCifras.xls";
      form.method = "POST";
      form.style = "toolbar=yes, scrollbars=yes, resizable=yes, top=500, left=500, width=400, height=400";
      form.target = "_blank" || "_self";
      
      var aplicativo = document.createElement("INPUT");
      aplicativo.name = 'aplicativo';
      aplicativo.value = $.trim($('#aplicativo').val());
      form.appendChild(aplicativo);
      
      var  comPeriodo=$.trim($('#periodo').val()).split(' ');
      
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