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
package mx.isban.cifrascontrol.beans.general;

import java.io.Serializable;

/**
 * Clase BeanProducto
 * 
 * Clase que contiene las propiedades relacionadas a un 
 * Producto, el cual contempla las propiedades:
 * <ul>
 * <li>idProducto</li>
 * <li>descripcion</li>
 * </ul>
 * 
 * @author everis
 *
 */
public class BeanProducto implements Serializable {

	/**
	 * Numero de la clase serializada
	 */
	private static final long serialVersionUID = -6264308606260909570L;

	/**
	 *Propiedad que contiene el id del producto 
	 */
	private String idProducto;
	/**
	 * Propiedad que contiene la descripcion del producto
	 */
	private String descripcion;
	
	/**
	 * Constructor vacio
	 */
	public BeanProducto() {
		super();
	}

	/**
	 * Metodo que obtiene la propiedad idProducto
	 * @return El id del producto
	 */
	public String getIdProducto() {
		return idProducto;
	}

	/**
	 * Metodo que establece el id del producto
	 * @param idProducto El id del producto a establecer
	 */
	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}

	/**
	 * Metodo encargado de obtener la descripcion del producto
	 * @return La descripcion del producto
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Metodo encargado de establecer la descripcion del producto
	 * @param descripcion La descripcion del producto
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
