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
package mx.isban.cifrascontrol.dao.catalogos;

import javax.ejb.Local;

import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.beans.producto.BeanIDProductoRespuesta;
import mx.isban.cifrascontrol.beans.producto.BeanProducto;
import mx.isban.cifrascontrol.beans.producto.BeanProductoRespuesta;

/**
 * Interface DAOCatalogos
 * 
 * Interface encargada de establecer los metodos necesarios 
 * para la consulta de los diferentes catalogos a los que
 * el usuario puede tener acceso
 * 
 * @author everis
 * @version 1.0
 * @see www.everis.com/mexico
 * 
 */
@Local
public interface DAOCatalogos {

	
	/**
	 * Constante con el valor EDC que indica el tipo de 
	 * operacion a realizar
	 */
	public static final String EDC = "EDC";
	
	/**
	 * Constante con el valor FACT que indica el tipo de 
	 * operacion a realizar
	 */
	public static final String FACT = "FACT";
	
	/**
	 * Constante que contiene el codigo de error para la consulta de 
	 * todos los productos
	 */
	public static final String CODIGO_ERROR_CONSULTA_PRODUCTOS = "CAT 01";
	
	/**
	 * Constante que contiene el codigo de error para la consulta de
	 * los productos por usuario
	 */
	public static final String CODIGO_ERROR_CONSULTA_PRODUCTOS_USUARIO = "CAT 02";
	
	/**
	 * Constante que contiene el codigo de error para la consulta de 
	 * los productos por id
	 */
	public static final String  CODIGO_ERROR_CONSULTA_PRODUCTOS_POR_ID = "CAT 03";
	
	/**
	 * Constante con el valor 0, el cual indica que no se presento un error al 
	 * momento de realizar acciones relacionadas al Grupo
	 */
	public static final String CODIGO_SIN_ERRORES = "0";
	
	/**
	 * Obtiene todos los productos de EDC.
	 * @return BeanProductoRespuesta
	 * @throws BusinessException Exception
	 */
	public BeanProductoRespuesta obtenerProductosEDC();
	
	/**
	 * Obtiene todos los productos de Facturas.
	 * @return BeanProductoRespuesta
	 * @throws BusinessException Exception.
	 */
	public BeanProductoRespuesta obtenerProductosFacturas();
	
	/**
	 * Metodo encargado de obtener un producto por su id
	 * @param tipoProducto El tipo de producto a buscar
	 * @return Un objeto de tipo 
	 * @throws BusinessException En caso de presentarse un error al momento
	 * de realizar la consulta de productos por id
	 */
	public BeanProducto obtenerProductoFacturaPorId(String idProducto)throws BusinessException;
	
	/**
	 * Metodo que se encarga de buscar un producto EDC por su ID.
	 * @param idProducto ID de producto EDC.
	 * @return BeanProductoRespuesta
	 */
	public BeanProductoRespuesta obtenerProductoEDCPorId(String idProducto);
	
	/**
	 * Obtiene los ID de productos asignados a un usuario.
	 * @param idUsuario ID de usuario para el cual se buscaran sus productos asignados.
	 * @param tipo Tipo de producto, EDC o Factura
	 * @return BeanIDProductoRespuesta
	 */
	public BeanIDProductoRespuesta obtenerIdentificadoresProductosPorUsuario(String idUsuario, String tipo);
}
