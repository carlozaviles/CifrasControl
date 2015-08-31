/**
 * Este script da formato a la tabla resultado de la consulta de Incidencias.
 */
$(document).ready(function(){
	$('#tablaIncidencias').css('width', '100%');
	$("#tablaIncidencias tbody tr:even").addClass('odd1');
	$('#tablaIncidencias tbody tr:odd').addClass('odd2');
});