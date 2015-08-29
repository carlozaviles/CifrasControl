/**
 * 
 */
package mx.isban.cifrascontrol.beans.cifrascontrol;

import java.io.Serializable;
import java.util.Date;

/**
 * @author everis
 *
 */
public class BeanInsidenciaCifras implements Serializable {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 3801033576352803129L;
	/**
	 * Producto para el que se generon insidencias.
	 */
	private String producto;
	/**
	 * Fase en la que se generon insidencias.
	 */
	private String fase;
	/**
	 * Ruta en la que se encuentra el archivo de insidencias.
	 */
	private String rutaIncidencia;
	/**
	 * Fecha en la que se genero este archivo de insidencias de cifras de control.
	 */
	private Date fechaInsidencia;
	/**
	 * Cadena que representa la fecha del archivo de Incidencia.
	 */
	private String cadenaFecha;
	
	/**
	 * Retorna el campo producto.
	 * @return String
	 */
	public String getProducto() {
		return producto;
	}
	
	/**
	 * Establece el valor del campo producto.
	 * @param producto Valor que sera colocado en el campo producto.
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	/**
	 * Obtiene el campo fase.
	 * @return String
	 */
	public String getFase() {
		return fase;
	}
	
	/**
	 * Establece el campo fase.
	 * @param fase Campo que se asignara al campo fase.
	 */
	public void setFase(String fase) {
		this.fase = fase;
	}
	
	/**
	 * Retorna el campo rutaIncidencia
	 * @return String
	 */
	public String getRutaIncidencia() {
		return rutaIncidencia;
	}
	
	/**
	 * Establece el valor del campo rutaIncidencia
	 * @param rutaIncidencia Valor que sera colocado en el campo rutaInsidencia
	 */
	public void setRutaIncidencia(String rutaIncidencia) {
		this.rutaIncidencia = rutaIncidencia;
	}
	
	/**
	 * Obtiene el valor del campo fechaInsidencia
	 * @return Date
	 */
	public Date getFechaInsidencia() {
		return fechaInsidencia;
	}
	
	/**
	 * Establece el valor del campo fechaInsidencia.
	 * @param fechaInsidencia Valor que sera colocado en el campo fechaInsidencia
	 */
	public void setFechaInsidencia(Date fechaInsidencia) {
		this.fechaInsidencia = fechaInsidencia;
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
}
