/**
 * 
 */
package mx.isban.cifrascontrol.bean.reprocesos;

import java.io.Serializable;

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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		final StringBuilder salida = new StringBuilder();
		salida.append("mes: " + this.mes).append(", anio: " + this.anio);
		return salida.toString();
	}
}
