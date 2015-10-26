$(document).ready(function(){
	$('#contenedorTablaReprocesos').css('overflow-x', 'scroll');
	$("#tablaReprocesos tbody tr:even").addClass('odd1');
	$('#tablaReprocesos tbody tr:odd').addClass('odd2');
	$('.pagebanner').hide();
	$('.pagelinks').hide();
	$('#exportaReprocesos').hide();
	$('.exportlinks').hide();
	var url = $('.exportlinks a:nth-child(2)').attr('href');
	$('#exportarInformeReprocesos').attr('href', url);
});