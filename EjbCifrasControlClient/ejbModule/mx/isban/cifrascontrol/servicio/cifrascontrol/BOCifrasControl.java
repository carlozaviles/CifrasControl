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
package mx.isban.cifrascontrol.servicio.cifrascontrol;

import java.util.List;

import javax.ejb.Remote;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.beans.cifrascontrol.BeanCifrasControl;
import mx.isban.cifrascontrol.beans.cifrascontrol.BeanDetalleCifrasControl;
import mx.isban.cifrascontrol.beans.general.BeanProducto;

/**
 * Interface BOCifrasControl
 * 
 * Interface encargada de definir los metodos necesarios que permitan realizar
 * la consulta de las diferentes cifras de control, ademas de la consulta del 
 * detalle de las cifras de control, el componente BOCifrasControl se encargara
 * de obtener las cifras de control para:
 * <ul>
 * <li>Aplicativo origen</li>
 * <li>Comprobante fiscal digital por internet</li>
 * <li>Sistema de administracion tributaria</li>
 * <li>Cuentas con generacion de estados de cuenta</li>
 * </ul>
 * 
 * @author everis
 * @version 1.0
 * @see www.everis.com/mexico
 * 
 */
@Remote
public interface BOCifrasControl {

	/**
	 * Metodo encargado de obtener todos los productos para el formulario
	 * de consulta de las cifras de control, el metodo regresa una lista de 
	 * objetos de tipo {@link BeanProducto}
	 * 
	 * @param sessionBean Un objeto de tipo {@link ArchitechSessionBean} necesario por la implementacion de Agave
	 * @return Una lista de objetos de tipo {@link BeanProducto}
	 * @throws BusinessException En caso de presentarse un error al momento de realizar la consulta de productos.
	 */
	public List<BeanProducto> obtenerProductos(ArchitechSessionBean sessionBean)throws BusinessException;
	
	/**
	 * Metodo encargado de realizar la consulta de las cifras de control, para establecer
	 * el listado obtenido y se puedan realizar las siguientes funcionalidades:
	 * <ul>
	 * 	<li>Obtener las cifras de control de los aplicativos origen</li>
	 * 	<li>Obtener las cifras de control del comprobante fiscal digital por internet</li>
	 * 	<li>Obtener las cifras de control del SAT</li>
	 * 	<li>Obtener las cuentas con generacion de estados de cuenta</li>
	 * </ul>
	 * 
	 * @param aplicativo El aplicativo que se utilizara para realizar la consulta
	 * @param periodo El periodo a consultar (Anio-Mes p.e 2010-02)
	 * @param sessionBean Un objeto de tipo {@link ArchitechSessionBean} necesario por la implementacion de Agave
	 * @return El tamanio de la lista obtenida
	 * @throws BusinessException En caso de presentarse un error al momento de realizar la consulta 
	 * de las cifras de control
	 */
	public int consultarCifrasControl(String aplicativo, String periodo, ArchitechSessionBean sessionBean)throws BusinessException;
	
	/**
	 * Metodo encargado de obtener la lista de las cifras de control para los 
	 * aplicativos (Origen, CFD, EDC, SAT), antes de ejecutar este metodo es necesario haber ejecutado
	 * el metodo consultarCifrasControl
	 * @param aplicativo La clave del aplicativo del cual se desea obtener el listado
	 * @param sessionBean Un objeto de tipo {@link ArchitechSessionBean} necesario por la implementacion de Agave
	 * @return Una lista de tipo {@link BeanCifrasControl} con las cifras de control para los aplicativos origen
	 */
	public List<BeanCifrasControl> obtenerCifrasPorAplicativo(String aplicativo,ArchitechSessionBean sessionBean);
	
	
	/**
	 * Metodo encargado de realizar la consulta del detalle de las cifras de control
	 * en relacion a un periodo.
	 * @param aplicativo El aplicativo a realizar la busqueda
	 * @param periodo El periodo con el formato anio-mes p.e 2010-09
	 * @param sessionBean Un objeto de tipo {@link ArchitechSessionBean} necesario por la implementacion de Agave
	 * @return Una lista de tipo {@link BeanDetalleCifrasControl} 
 	 * @throws BusinessException En caso de presentarse un error al momento de consultar
 	 * el detalle de las cifras de control
	 */
	public List<BeanDetalleCifrasControl> obtenerDetalleCifrasControl(String aplicativo, String periodo, ArchitechSessionBean sessionBean)throws BusinessException;
	
}
