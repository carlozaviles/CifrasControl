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
package mx.isban.cifrascontrol.util.cifrascontrol;

/**
* Clase ConstantesCifrasControl
* 
* <P>Clase que contiene los valores constantes utilizados en 
* el modulo cifras de control 
* 
* @author Everis
* @version 1.0
* @see www.everis.com/mexico
* 
*/
public final class ConstantesCifrasControl {
	
	/**
	 * Constructor privado
	 */
	private ConstantesCifrasControl() {
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
	
	
	/**
	 * Codigo de error que se lanza cuando la configuracion para la consulta de 
	 * insidencias no es cargada de manera correcta.
	 */
	public static final String ERROR_CONFIGURACION_CONSULTA_INCIDENCIAS = "10001";
	/**
	 * Codigo de error que se lanza cuando existe un error al procesar los nombres de los
	 * archivos en la consulta de incidencia de cifras de control.
	 */
	public static final String ERROR_PROCESA_ARCHIVOS_CONSULTA_INCIDENCIAS = "10002";
	/**
	 * Codigo de error lanzado cuando no se encuentra el nombre del DataSource de Catalogos.
	 */
	public static final String ERROR_CARGA_CONFIG_DS_CATALOGOS = "10003";
	/**
	 * Codigo para indicar que ocurrio un error al cargar el DataSource de Catalogos.
	 */
	public static final String ERROR_DS_LOOKUP = "10004";
}
