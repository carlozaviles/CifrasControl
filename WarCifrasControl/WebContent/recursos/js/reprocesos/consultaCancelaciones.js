/**
 * 
 */
$(document).ready(function(){
	$('#tablaCancelaciones').css('width', '100%');
	$("#tablaCancelaciones tbody tr:even").addClass('odd1');
	$('#tablaCancelaciones tbody tr:odd').addClass('odd2');
	$('.pagebanner').hide();
	$('.pagelinks').hide();
	$('#exportarCancelaciones').hide();
	$('.exportlinks').hide();
	var url = $('.exportlinks a:nth-child(2)').attr('href');
	$('#exportaExcel').attr('href', url);
});