/**
 * 
 */
package mx.isban.cifrascontrol.bean.reprocesos;

import java.io.Serializable;

/**
 * @author everis
 *
 */
public class BeanCancelacion implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 2581470385961705456L;
	/**
	 * Numero de Cuenta.
	 */
	private String numeroCuenta;
	/**
	 * Aplicativo.
	 */
	private String aplicativo;
	/**
	 * Periodo.
	 */
	private String periodo;
	/**
	 * Fecha cancelacion.
	 */
	private String fechaCancelacion;
	/**
	 * Folio SAT.
	 */
	private String folioSat;
	/**
	 * Comiciones e Intereses.
	 */
	private String comicionInteres;
	/**
	 * IVA.
	 */
	private String iva;
	/**
	 * Retenciones.
	 */
	private String retenciones;
	
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
	 * @return the fechaCancelacion
	 */
	public String getFechaCancelacion() {
		return fechaCancelacion;
	}
	
	/**
	 * @param fechaCancelacion the fechaCancelacion to set
	 */
	public void setFechaCancelacion(String fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}
	
	/**
	 * @return the folioSat
	 */
	public String getFolioSat() {
		return folioSat;
	}
	
	/**
	 * @param folioSat the folioSat to set
	 */
	public void setFolioSat(String folioSat) {
		this.folioSat = folioSat;
	}
	
	/**
	 * @return the comicionInteres
	 */
	public String getComicionInteres() {
		return comicionInteres;
	}
	
	/**
	 * @param comicionInteres the comicionInteres to set
	 */
	public void setComicionInteres(String comicionInteres) {
		this.comicionInteres = comicionInteres;
	}
	
	/**
	 * @return the iva
	 */
	public String getIva() {
		return iva;
	}
	
	/**
	 * @param iva the iva to set
	 */
	public void setIva(String iva) {
		this.iva = iva;
	}
	
	/**
	 * @return the retenciones
	 */
	public String getRetenciones() {
		return retenciones;
	}
	
	/**
	 * @param retenciones the retenciones to set
	 */
	public void setRetenciones(String retenciones) {
		this.retenciones = retenciones;
	}
}
