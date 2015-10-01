/**
 * 
 */
package mx.isban.cifrascontrol.bean.reprocesos;

import java.io.Serializable;
import java.util.List;

import mx.isban.cifrascontrol.beans.producto.BeanProducto;

/**
 * @author everis
 *
 */
public class BeanParamsConsultaReproceso implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -5987883658417813282L;
	
	

	/**
	 * Representa el mes para el cual se va a realizar la consulta de reprocesos.
	 */
	
	private String mes;
	/**
	 * Representa el anio para el cual se va a realizar la consutla de reprocesos.
	 */
	private String anio;
	/**
	 * Numero de cuenta.
	 */
	private String numeroCuenta;
	
	private String productoSeleccionado;
	
	public String getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(String productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	/**
	 * Productos a los que un usuario puede tener acceso
	 */
	private List<BeanProducto> productos;
	
	/**
	 * Retorna el valor del campo mes
	 * @return String
	 */
	public String getMes() {
		return mes;
	}
	
	/**
	 * Establece el valor del campo mes.
	 * @param mes Valor que sera colocado en el campo mes.
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	/**
	 * Retorna el valor del campo anio.
	 * @return String
	 */
	public String getAnio() {
		return anio;
	}
	
	/**
	 * Establece el valor del campo anio.
	 * @param anio Valor que sera colocado en en campo anio.
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}
	/*
	 * Establece el valor del campo de numero de cuenta
	 * */
	
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		final StringBuilder salida = new StringBuilder();
		salida.append("mes: " + this.mes).append(", anio: " + this.anio).append(", numeroCuenta: " + this.numeroCuenta).append(", producto: " + this.productoSeleccionado);
		return salida.toString();
	}

	/**
	 * Metodo encargado de obtener los productos a los que un usuario tiene acceso
	 * @return the productos
	 */
	public List<BeanProducto> getProductos() {
		return productos;
	}

	/**
	 * Metodo encargado de establecer los productos a los que un usuario puede acceder
	 * @param productos the productos to set
	 */
	public void setProductos(List<BeanProducto> productos) {
		this.productos = productos;
	}
}
