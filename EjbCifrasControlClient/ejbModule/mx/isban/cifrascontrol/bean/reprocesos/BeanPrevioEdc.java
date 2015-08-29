/**
 * 
 */
package mx.isban.cifrascontrol.bean.reprocesos;

import java.io.Serializable;
import java.util.Date;

/**
 * @author everis
 *
 */
public class BeanPrevioEdc implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 2011848010212167476L;
	/**
	 * Numero de Cuenta.
	 */
	private String numeroCuenta;
	/**
	 * Producto.
	 */
	private String producto;
	/**
	 * Ruta en la que se encuentra el previo.
	 */
	private String rutaPrevio;
	/**
	 * Cadena que representa la fecha del previo.
	 */
	private String cadenaFecha;
	/**
	 * Fecha del previo.
	 */
	private Date fecha;
	
	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	
	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	/**
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}
	
	/**
	 * @param producto the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	/**
	 * @return the rutaPrevio
	 */
	public String getRutaPrevio() {
		return rutaPrevio;
	}
	
	/**
	 * @param rutaPrevio the rutaPrevio to set
	 */
	public void setRutaPrevio(String rutaPrevio) {
		this.rutaPrevio = rutaPrevio;
	}
	
	/**
	 * @return the cadenaFecha
	 */
	public String getCadenaFecha() {
		return cadenaFecha;
	}
	
	/**
	 * @param cadenaFecha the cadenaFecha to set
	 */
	public void setCadenaFecha(String cadenaFecha) {
		this.cadenaFecha = cadenaFecha;
	}
	
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
