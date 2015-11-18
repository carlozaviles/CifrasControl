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
package mx.isban.cifrascontrol.servicio.catalogos;

import java.util.List;

import javax.ejb.Remote;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.beans.producto.BeanProducto;

/**
 * Interface BOCatalogos
 * 
 * Interface encargada de definir los metodos necesarios que permitan realizar
 * la consulta de los catalogos, ya sea para mostrarlos en el modulo de perfilamiento
 * o para mostrarlos al momento de realizar alguna consulta
 * 
 * @author everis
 * @version 1.0
 * @see www.everis.com/mexico
 * 
 */
@Remote
public interface BOCatalogos {

	/**
	 * Metodo encargado de realizar la consulta a la base de datos para obtener
	 * todos los productos disponibles, este metodo es utilizado en el modulo
	 * de perfilamiento, al momento de realizar el alta/modificacion de un 
	 * usuario
	 * @param tipoProducto El tipo de producto a consultar (EDC o FACT)
	 * @return Lista de objetos de tipo {@link BeanProducto}
	 * @throws BusinessException En caso de presentarse un error al momento de 
	 * consultar la base de datos
	 */
	public List<BeanProducto> obtenerTodosProductos(String tipoProducto)throws BusinessException;

	
	/**
	 * Metodo encargado de obtener los productos relacionados a un usuario,
	 * los cuales se muestran al momento de realizar la consulta y/o solicitud
	 * de reprocesos, ademas de la consulta de las cifras de control para EDC y Facturas
	 * 
	 * @param sessionBean Objeto de la arquitectura agave de tipo {@link ArchitechSessionBean}
	 * @param idUsuario El id del usuario a realizar la busqueda
	 * @param tipo El tipo de producto a buscar (EDC o FACT)
	 * @return Un listado de objetos de tipo {@link BeanProducto}
	 * @throws BusinessException En caso de presentarse un error al momento de 
	 * consultar los registros en la base de datos
	 */
	public List<BeanProducto> obtenerProductosUsuario(ArchitechSessionBean sessionBean,String idUsuario, String tipo)throws BusinessException;
	
	/**
	 * Metodo encargado de obtener los productos relacionados a un usuario, el  cual cuenta con el permiso
	 * de Reproceso, estos se muestran al momento de realizar la consulta y/o solicitud
	 * de reprocesos
	 * 
	 * @param sessionBean Objeto de la arquitectura agave de tipo {@link ArchitechSessionBean}
	 * @param idUsuario El id del usuario a realizar la busqueda
	 * @param tipo El tipo de producto a buscar (EDC o FACT)
	 * @return Un listado de objetos de tipo {@link BeanProducto}
	 * @throws BusinessException En caso de presentarse un error al momento de 
	 * consultar los registros en la base de datos
	 */
	 public List<BeanProducto> obtenerProductosUsuarioReporceso(ArchitechSessionBean sessionBean,String idUsuario, String tipo)throws BusinessException;
	 
}
