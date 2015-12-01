package mx.isban.cifrascontrol.bean.reprocesos;

import java.io.Serializable;


/**
 * @author everis
 *
 */
public class BeanParamsConsultaCancel implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -5987883658417813282L;
	/**
	 * Periodo.
	 */
	private String periodo;
	/**
	 * Mes
	 */
	private String mes;
	/**
	 * Representa el anio para el cual se va a realizar la consutla de reprocesos.
	 */
	private String anio;

	/**
	 * Obtiene el valor del campo mes.
	 * @return String
	 */
	public String getMes() {
		return mes;
	}
	
	/**
	 * Establece el valor del campo mes.
	 * @param mes Valor a establecer.
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Obtiene el valor del campo anio.
	 * @return String
	 */
	public String getAnio() {
		return anio;
	}

	/**
	 * Establece el valor del campo anio.
	 * @param anio Valor a establecer.
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}
	
	/**
	 * Obtiene el valor del campo periodo.
	 * @return String
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * Establece el valor del campo periodo.
	 * @param periodo Valor a esteblecer.
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
	
	