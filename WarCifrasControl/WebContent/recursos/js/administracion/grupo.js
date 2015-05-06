
$(document).ready(
	function(){
		$('#btnModificaPerfil').click(
			function(event){
				 if ($('#nombreGrupo').val().trim() === '') {
		                alert('El campo esta vacio');
		                event.preventDefault();
		            }
		         else {
		             $('#modificarGrupo').submit();
		         }
			}
		);
		$("#regresar").click(function(event) {
		    event.preventDefault();
		    history.back(1);
		});
		$('#btnAltaGrupo').click(
				function(event){
					 if ($('#nombreGrupo').val().trim() === '') {
			                alert('El campo esta vacio');
			                event.preventDefault();
			            }
			         else {
			                $('#altaGrupo').submit();
			         }
				}
		);
	}
);


