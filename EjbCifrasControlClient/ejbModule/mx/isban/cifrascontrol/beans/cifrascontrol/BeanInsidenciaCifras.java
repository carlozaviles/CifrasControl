/**
 * 
 */
package mx.isban.cifrascontrol.beans.cifrascontrol;

import java.io.Serializable;

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
	 * Id de incidencia.
	 */
	private String id;
	/**
	 * Codigo de moneda
	 */
	private String moneda;
	/**
	 * Nombre del aplicativo.
	 */
	private String aplicativo;
	/**
	 * Fase en la que se generon insidencias.
	 */
	private String fase;
	/**
	 * Propiedad con el numero de cuenta
	 */
	private String numeroCuenta;
	/**
	 * Tipo de documento.
	 */
	private String tipoDocumento;
	/**
	 * Periodo de consulta.
	 */
	private String periodo;
	/**
	 * Descripcion de esta incidencia.
	 */
	private String descripcionError;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}
	
	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	
	/**
	 * @return the aplicativo
	 */
	public String getAplicativo() {
		return aplicativo;
	}
	
	/**
	 * @param aplicativo the aplicativo to set
	 */
	public void setAplicativo(String aplicativo) {
		this.aplicativo = aplicativo;
	}
	
	/**
	 * @return the fase
	 */
	public String getFase() {
		return fase;
	}
	
	/**
	 * @param fase the fase to set
	 */
	public void setFase(String fase) {
		this.fase = fase;
	}
	
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
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}
	
	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	/**
	 * @return the descripcionError
	 */
	public String getDescripcionError() {
		return descripcionError;
	}
	
	/**
	 * @param descripcionError the descripcionError to set
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BeanInsidenciaCifras [id=" + id + ", moneda=" + moneda
				+ ", aplicativo=" + aplicativo + ", fase=" + fase
				+ ", numeroCuenta=" + numeroCuenta + ", tipoDocumento="
				+ tipoDocumento + ", periodo=" + periodo
				+ ", descripcionError=" + descripcionError + "]";
	}
}
