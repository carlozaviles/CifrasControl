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
	 * Anio para la busqueda de Cancelaciones
	 */
	private String anio;
	

	/**
	 * Fecha cancelacion.
	 */
	private String fechaCancelacion;
	/**
	 * Folio SAT.
	 */
	private String folioSAT;
	/**
	 * Comiciones e Intereses.
	 */
	private String importeComisInter;
	/**
	 * IVA.
	 */
	private String importeIva;
	
	/**
	 * Retenciones.
	 */
	private String importeRetencion;
	
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
	public String getFolioSAT() {
		return folioSAT;
	}
	
	/**
	 * @param folioSAT the folioSat to set
	 */
	public void setFolioSAT(String folioSAT) {
		this.folioSAT = folioSAT;
	}
	
	/**
	 * @return the comicionInteres
	 */
	public String getimporteComisInter() {
		return importeComisInter;
	}
	
	/**
	 * @param importeComisInter the comicionInteres to set
	 */
	public void setimporteComisInter(String importeComisInter) {
		this.importeComisInter = importeComisInter;
	}
	
	/**
	 * Retorna importeIva
	 * @return String
	 */
	public String getImporteIva() {
		return importeIva;
	}

	/**
	 * Establece el valor del campo importeIva
	 * @param importeIva Valor a establecer
	 */
	public void setImporteIva(String importeIva) {
		this.importeIva = importeIva;
	}
	
	/**
	 * Retorna el valor del campo importeRetencion
	 * @return String
	 */
	public String getImporteRetencion() {
		return importeRetencion;
	}

	/**
	 * Establece el valor del campo importeRetencion.
	 * @param importeRetencion Valor a esteblecer.
	 */
	public void setImporteRetencion(String importeRetencion) {
		this.importeRetencion = importeRetencion;
	}
	
	/**
	 * Retorna el valor del campo anio.
	 * @param anio Valor a establecer.
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
}
