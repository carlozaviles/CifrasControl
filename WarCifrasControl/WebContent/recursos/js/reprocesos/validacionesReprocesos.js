        $(document).ready(function (){
          $('#numero-cuenta').keyup(function (){
            this.value = (this.value + '').replace(/[^0-9]/g, '');
          });
        });
        
        $('#aceptarFormularioDatos').click(function(event) {
            if ($('#numero-cuenta').val().trim() === '') {
                alert('El campo esta vacio');
            }
            else {
                alert('Campo correcto');
            }
        });
        
        
 