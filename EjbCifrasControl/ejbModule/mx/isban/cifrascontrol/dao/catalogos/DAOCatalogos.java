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

import java.util.List;

import javax.ejb.Local;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
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
	 * Constante con el valor 0, el cual indica que no se presento un error al 
	 * momento de realizar acciones relacionadas al Grupo
	 */
	public static final String CODIGO_SIN_ERRORES = "0";
	
	/**
	 * Metodo encargado de consultar todos los productos disponibles
	 * para EDC y para FACT, para que el usuario pueda dar de alta 
	 * los productos asignados a algun cliente
	 * @return Un listado de objetos de tipo {@link BeanProducto}
	 * @throws BusinessException En caso de presentarse un error al momento de
	 * realizar la consulta en la base de datos
	 */
	public List<BeanProducto> obtenerTodosProductos()throws BusinessException;
	
	/**
	 * Metodo encargado de obtener los productos por usuario, en relacion 
	 * a un tipo (EDC o FACT), los productos a mostrar son los que se encuentran
	 * asignados a algun usuario operativo.
	 * @param idUsuario El id del usuario que realiza la operacion
	 * @param tipo El tipo de operacion (EDC o FACT)
	 * @param sessionBean Objeto de la arquitectura agave de tipo {@link ArchitechSessionBean}
	 * @return Un objeto de tipo {@link BeanProductoRespuesta}
	 */
	public BeanProductoRespuesta obtenerProductoPorUsuario(String idUsuario,String tipo,ArchitechSessionBean sessionBean);
	
}
