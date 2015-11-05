	function getNumCuentaLength(){
	          return 16;
	}
	function getNumCuentaFormat(){
        return /[^0-9]/g;
    }
	function validarNumCuenta(field){
		 var numCuentaLength=$.trim(field.val()).length;
		if(numCuentaLength != 11 && numCuentaLength!=15 && numCuentaLength!=16)
			return false;
		return true;
	}
