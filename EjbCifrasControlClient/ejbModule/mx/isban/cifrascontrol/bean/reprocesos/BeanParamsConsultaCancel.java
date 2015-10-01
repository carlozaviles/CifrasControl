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

	private String periodo;
	
	private String mes;
	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	/**
	 * Representa el anio para el cual se va a realizar la consutla de reprocesos.
	 */
	private String anio;
	
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
	
	