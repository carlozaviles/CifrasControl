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
package mx.isban.cifrascontrol.beans.producto;

import java.io.Serializable;
import java.util.List;

import mx.isban.agave.commons.interfaces.BeanResultBO;

/**
 * Clase BeanProductoRespuesta
 * 
 * Clase que contiene las propiedades relacionadas 
 * a la respuesta obtenida del IDA en relacion a la consulta de productos
 *  
 * @author Everis
 * @version 1.0
 * @see www.everis.com/mexico
 *
 */
public class BeanProductoRespuesta implements BeanResultBO, Serializable {

	/**
	 * Numero de la clase serializada
	 */
	private static final long serialVersionUID = -9028859538279542632L;

	/**
	 * Propiedad que contiene el codigo de error asociado a la consulta 
	 * de productos en la base de datos
	 */
	private String codError;
	
	/**
	 * Propiedad que contiene el mensaje de error asociado a la consulta
	 * de productos en la base de datos
	 */
	private String msgError;
	
	/**
	 * Propiedad que contiene el resultado de la consulta a la base de datos
	 */
	private List<BeanProducto> productos;
	
	/**
	 * Constructor vacio
	 */
	public BeanProductoRespuesta() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see mx.isban.agave.commons.interfaces.BeanResultBO#getCodError()
	 */
	@Override
	public String getCodError() {
		return this.codError;
	}

	/* (non-Javadoc)
	 * @see mx.isban.agave.commons.interfaces.BeanResultBO#getMsgError()
	 */
	@Override
	public String getMsgError() {
		return this.msgError;
	}

	/* (non-Javadoc)
	 * @see mx.isban.agave.commons.interfaces.BeanResultBO#setCodError(java.lang.String)
	 */
	@Override
	public void setCodError(String codError) {
		this.codError = codError;
	}

	/* (non-Javadoc)
	 * @see mx.isban.agave.commons.interfaces.BeanResultBO#setMsgError(java.lang.String)
	 */
	@Override
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	/**
	 * @return the productos
	 */
	public List<BeanProducto> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(List<BeanProducto> productos) {
		this.productos = productos;
	}

}
