/**
 * Este Script da formato a la tabla resultado de la consulta de Previos
 */
$(document).ready(function(){
	$('#tablaConsultaPrevios').css('width', '100%');
	$("#tablaConsultaPrevios tbody tr:even").addClass('odd1');
	$('#tablaConsultaPrevios tbody tr:odd').addClass('odd2');
});