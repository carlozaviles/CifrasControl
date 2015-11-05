/**************************************************************
* Queretaro, Qro Junio 2015
*
* La redistribucion y el uso en formas fuente y binario, 
* son responsabilidad del propietario.
* 
* Este software fue elaborado en @Everis
* 
* Para mas informacion, consulte <www.everis.com/mexico>
***************************************************************/
package mx.isban.cifrascontrol.servicio.facturas;

import java.util.List;

import javax.ejb.Remote;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.beans.facturas.BeanFactura;
import mx.isban.cifrascontrol.beans.facturas.BeanReporteFacturas;

/**
 * Interface BOFactura
 * 
 * Interface encargada de definir los metodos necesarios que permitan realizar
 * la consulta de :
 * <ul>
 * <li>Productos (Para inicializar los formularios)</li>
 * <li>Facturas</li>
 * <li>Notas de credito</li>
 * <li>Divisas</li>
 * <li>Recibos deducibles</li>
 * </ul>
 * 
 * @author everis
 * @version 1.0
 * @see www.everis.com/mexico
 * 
 */
@Remote
public interface BOFactura {

	/**
	 * <p>Metodo encargado de realizar la consulta de las facturas,
	 * mediante la invocacion del servicio web Consulta cifras control para 
	 * obtener las facturas totales para alguno de los siguientes tipos de factura: 
	 * <ul>
	 * <li>Facturas</li>
	 * <li>Notas de credito</li>
	 * <li>Divisas</li>
	 * <li>Recibos deducibles</li>
	 * </ul>
	 * 
	 * @param aplicativo Objeto de tipo {@link String} con el codigo del aplicativo a consultar
	 * @param periodo Objeto de tipo {@link String} con el periodo a consultar (Anio-mes) p.e '2015-02'
	 * @param tipoFactura Objeto de tipo {@link String} con el codigo que indica el tipo de factura
	 * @param sessionBean Un objeto de tipo {@link ArchitechSessionBean} necesario por la implementacion de Agave
	 * @return Una lista de tipo {@link BeanFactura} con el resultado de la consulta
	 * @throws BusinessException En caso de presentarse un error al momento de realizar la consulta de facturas.
	 */
	public List<BeanFactura> consultarFacturas(String aplicativo, String periodo, String tipoFactura, 
			ArchitechSessionBean sessionBean)throws BusinessException;
	
	/**
	 * Ejecuta la consulta de facturas y a partir de los resultados crear la relacion entre las facturas correctas e incorrectas.
	 * @param aplicativo Aplicativo para el cual se realizar el reporte.
	 * @param periodo Perido anio/mes para el cual se realiza el reporte.
	 * @param tipoFactura Tipo de factura para el cual se realiza el reporte (Factura, Notas de Credito, Divisas).
	 * @param sessionBean Objeto de la arquitectura agave.
	 * @return List<BeanReporteFacturas>
	 * @throws BusinessException Exception
	 */
	public List<BeanReporteFacturas> obtenerReporteFacturas(String aplicativo, String periodo, String tipoFactura, 
			ArchitechSessionBean sessionBean) throws BusinessException;
	
	/**
	 * Ejecuta la consulta de facturas y a partir de los resultados crea la la relacion entre los recibos correctos y cancelados.
	 * @param aplicativo Aplicativo a partir del cual se realiza el reporte.
	 * @param periodo Periodo anio-mes para el cual se realiza el reporte.
	 * @param tipoRecibo Clave que identifica a los recibos.
	 * @param sessionBean Objeto de la arquitectura agave.
	 * @return List<BeanReporteFacturas>
	 * @throws BusinessException Exception
	 */
	public List<BeanReporteFacturas> obtenerReporteRecibos(String aplicativo, String periodo, String tipoRecibo, 
			ArchitechSessionBean sessionBean) throws BusinessException;
	
	/**
	 * <p>Metodo encargado de diferenciar y obtener los totales de las facturas 
	 * correctas, por lo cual es necesario el listado de las facturas obtenidas
	 * de consultar el servicio web cifras de control.
	 *  
	 * @param totalFacturas Listado con el total de las facturas
	 * @param sessionBean Un objeto de tipo {@link ArchitechSessionBean} necesario por la implementacion de Agave
	 * @return Listado de las facturas correctas
	 */
	public List<BeanFactura> obtenerFacturasCorrectas(List<BeanFactura> totalFacturas, ArchitechSessionBean sessionBean);

	
	/**
	 * <p>Metodo encargado de diferenciar y obtener los totales de las facturas 
	 * correctas, por lo cual es necesario el listado de las facturas obtenidas
	 * de consultar el servicio web cifras de control.
	 *  
	 * @param totalFacturas Listado con el total de las facturas
	 * @param sessionBean Un objeto de tipo {@link ArchitechSessionBean} necesario por la implementacion de Agave
	 * @return Listado de las facturas correctas
	 */
	public List<BeanFactura> obtenerFacturasIncorrectas(List<BeanFactura> totalFacturas, ArchitechSessionBean sessionBean);
	
	/**
	 * <p>Metodo encargado de diferenciar y obtener los los recibos generados, 
	 * por lo cual es necesario el listado de las facturas para recibos obtenidas
	 * de consultar el servicio web cifras de control.
	 *  
	 * @param totalRecibos Listado con el total de los registros
	 * @param sessionBean Un objeto de tipo {@link ArchitechSessionBean} necesario por la implementacion de Agave
	 * @return Listado de las facturas con las cifras de los recibos generados
	 */
	public List<BeanFactura> obtenerFacturasRecibosGenerados(List<BeanFactura> totalRecibos, ArchitechSessionBean sessionBean);

	
	/**
	 * <p>Metodo encargado de diferenciar y obtener los los recibos cancelados, 
	 * por lo cual es necesario el listado de las facturas para recibos obtenidas
	 * de consultar el servicio web cifras de control.
	 *  
	 * @param totalRecibos Listado con el total de los registros
	 * @param sessionBean Un objeto de tipo {@link ArchitechSessionBean} necesario por la implementacion de Agave
	 * @return Listado de las facturas con las cifras de los recibos generados
	 */
	public List<BeanFactura> obtenerFacturasRecibosCancelados(List<BeanFactura> totalRecibos, ArchitechSessionBean sessionBean);

}
