/**
 * 
 */
package mx.isban.cifrascontrol.util.reproceso;

/**
 * @author everis
 *
 */
public final class ConstantesReprocesos {
	
	/**
	 * Constructor privado que tiene como fin evitar que esta clase
	 * sea instanciada.
	 */
	private ConstantesReprocesos(){}
	/**
	 * Error al ejecutar el WebService de Reprocesos.
	 */
	public static final String CODIGO_ERROR_SOLICITUD_REPROCESO = "2001";
	/**
	 * Error al tratar los datos para la realizar la solicitud de reproceso.
	 */
	public static final String CODIGO_ERROR_GENERA_SOLICITUD_REPROCESO = "2002";
	/**
	 * Codigo de error al procesar respuesta de la consulta de personas. 
	 */
	public static final String CODIGO_ERROR_PROCESA_RESULTADO_CONSULTA = "2003";
	/**
	 * Codigo de error generado al realizar la consulta de reprocesos.
	 */
	public static final String CODIGO_ERROR_CONSULTA_REPROCESOS = "2004";
	/**
	 * Codigo de error generado al procesar los datos retornados por la consulta de reprocesos.
	 */
	public static final String CODIGO_ERROR_PROCESA_RESULTADO_CONSULTA_REP = "2005";
	/**
	 * Codigo de error al ejecutar la consulta de Personas.
	 */
	public static final String CODIGO_ERROR_EJECUCION_CONSULTA_PERSONAS = "2006";
	/**
	 * Formato de fecha para solicitud de reproceso.
	 */
	public static final String FORMATO_FECHA_SOLICITUD_REPROCESO = "yyyy/MM/dd HH:mm:ss";
	/**
	 * Codigo de error al cargar configuracion para consulta de Previos
	 */
	public static final String CODIGO_ERROR_CONFIG_PREVIOS = "2007";
	/**
	 * Codigo de error que indica falla al procesar los resultados de la consulta de previos.
	 */
	public static final String CODIGO_ERROR_PROCESO_PREVIOS = "2008";
	/**
	 * Codigo de error que indica que el filtro para la consulta de personas es invalido
	 */
	public static final String CODIGO_ERROR_FILTRO_CONSULTA_PERSONAS = "2009";
}
