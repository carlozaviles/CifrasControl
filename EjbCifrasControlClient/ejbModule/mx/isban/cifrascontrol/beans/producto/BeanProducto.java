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
package mx.isban.cifrascontrol.beans.producto;

import java.io.Serializable;

/**
 * Clase BeanProducto
 * 
 * Clase que contiene las propiedades relacionadas a un 
 * Producto, el cual contempla las propiedades:
 * <ul>
 * <li>idProducto</li>
 * <li>descripcion</li>
 * <li>tipoProducto</li>
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
	 * Propiedad que contiene el tipo de producto
	 */
	private String tipoProducto;
	/**
	 * Propiedad que indica si un producto esta seleccionado 
	 */
	private boolean productoSeleccionado;
	/**
	 * Sirve para indicar si un usuario tiene permiso para reproceso con este producto.
	 */
	private boolean permisoReproceso;
	
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

	/**
	 * Metodo encargado de obtener el tipo de producto
	 * @return El tipo de producto
	 */
	public String getTipoProducto() {
		return tipoProducto;
	}

	/**
	 * Metodo encargado de establecer el tipo de producto
	 * @param tipoProducto El tipo de producto a establecer
	 */
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	/**
	 * @return the productoSeleccionado
	 */
	public boolean isProductoSeleccionado() {
		return productoSeleccionado;
	}

	/**
	 * @param productoSeleccionado the productoSeleccionado to set
	 */
	public void setProductoSeleccionado(boolean productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	/**
	 * @return the permisoReproceso
	 */
	public boolean isPermisoReproceso() {
		return permisoReproceso;
	}

	/**
	 * @param permisoReproceso the permisoReproceso to set
	 */
	public void setPermisoReproceso(boolean permisoReproceso) {
		this.permisoReproceso = permisoReproceso;
	}

	
}
