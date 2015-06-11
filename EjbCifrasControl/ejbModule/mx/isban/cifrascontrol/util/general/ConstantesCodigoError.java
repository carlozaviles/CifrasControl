/**************************************************************
* Queretaro, Qro Mayo 2015
*
* La redistribucion y el uso en formas fuente y binario, 
* son responsabilidad del propietario.
* 
* Este software fue elaborado en @Everis
* 
* Para mas informacion, consulte <www.everis.com/mexico>
***************************************************************/
package mx.isban.cifrascontrol.util.general;

/**
* Clase ConstantesCodigoError
* 
* <P>Clase que contiene los valores constantes de los direfentes codigos de error 
* 
* @author Everis
* @version 1.0
* @see www.everis.com/mexico
* 
*/
public final class ConstantesCodigoError {
	
	/**
	 * Constructor privado
	 */
	private ConstantesCodigoError() {
		super();
	}
	
	public static final String ILLEGAL_ACCESS_EXCEPTION_CODE = "GEN-01";

	public static final String ILLEGAL_ARGUMENT_EXCEPTION = "GEN-02";
	
	public static final String INVOCATION_TARGET_EXCEPTION = "GEN-03";
	
	public static final String INSTANTIATION_EXCEPTION = "GEN-04";
	
	public static final String CLASS_NOT_FOUND_EXCEPTION = "GEN-05";
		
	
	//Cifras control
	
	public static final String ERROR_CONSULTAR_CIFRAS_CONTROL = "CIFRAS-01";
	
	public static final String ERROR_CONSULTAR_CIFRAS_CONTROL_DETALLE = "CIFRAS-02";
	
}
