/**
 * 
 */
package mx.isban.cifrascontrol.beans.facturas;

import java.io.Serializable;

/**
 * @author everis
 *
 */
public class BeanReporteFacturas implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 5490679480660297480L;
	/**
	 * Numero de facturas correctas.
	 */
	private String facturasCorrectas;
	/**
	 * Subtotal Facturas Correctas.
	 */
	private String subtotalFactCorrectas;
	/**
	 * IVA facturas Correctas.
	 */
	private String ivaFactCorrectas;
	/**
	 * Total Facturas Correctas.
	 */
	private String totalFactCorrectas;
	/**
	 * Numero de facturas canceladas.
	 */
	private String facturasCanceladas;
	/**
	 * Subtotal Facturas Canceladas.
	 */
	private String subtotalFactCanceladas;
	/**
	 * IVA Facturas Canceladas.
	 */
	private String ivaFactCanceladas;
	/**
	 * Total Facturas Canceladas.
	 */
	private String totalFactCanceladas;
	
	/**
	 * moneda
	 */
	private String moneda;
	/**
	 * @return the facturasCorrectas
	 */
	public String getFacturasCorrectas() {
		return facturasCorrectas;
	}
	
	/**
	 * @param facturasCorrectas the facturasCorrectas to set
	 */
	public void setFacturasCorrectas(String facturasCorrectas) {
		this.facturasCorrectas = facturasCorrectas;
	}
	
	/**
	 * @return the subtotalFactCorrectas
	 */
	public String getSubtotalFactCorrectas() {
		return subtotalFactCorrectas;
	}
	
	/**
	 * @param subtotalFactCorrectas the subtotalFactCorrectas to set
	 */
	public void setSubtotalFactCorrectas(String subtotalFactCorrectas) {
		this.subtotalFactCorrectas = subtotalFactCorrectas;
	}
	
	/**
	 * @return the ivaFactCorrectas
	 */
	public String getIvaFactCorrectas() {
		return ivaFactCorrectas;
	}
	
	/**
	 * @param ivaFactCorrectas the ivaFactCorrectas to set
	 */
	public void setIvaFactCorrectas(String ivaFactCorrectas) {
		this.ivaFactCorrectas = ivaFactCorrectas;
	}
	
	/**
	 * @return the totalFactCorrectas
	 */
	public String getTotalFactCorrectas() {
		return totalFactCorrectas;
	}
	
	/**
	 * @param totalFactCorrectas the totalFactCorrectas to set
	 */
	public void setTotalFactCorrectas(String totalFactCorrectas) {
		this.totalFactCorrectas = totalFactCorrectas;
	}
	
	/**
	 * @return the facturasCanceladas
	 */
	public String getFacturasCanceladas() {
		return facturasCanceladas;
	}
	
	/**
	 * @param facturasCanceladas the facturasCanceladas to set
	 */
	public void setFacturasCanceladas(String facturasCanceladas) {
		this.facturasCanceladas = facturasCanceladas;
	}
	
	/**
	 * @return the subtotalFactCanceladas
	 */
	public String getSubtotalFactCanceladas() {
		return subtotalFactCanceladas;
	}
	
	/**
	 * @param subtotalFactCanceladas the subtotalFactCanceladas to set
	 */
	public void setSubtotalFactCanceladas(String subtotalFactCanceladas) {
		this.subtotalFactCanceladas = subtotalFactCanceladas;
	}
	
	/**
	 * @return the ivaFactCanceladas
	 */
	public String getIvaFactCanceladas() {
		return ivaFactCanceladas;
	}
	
	/**
	 * @param ivaFactCanceladas the ivaFactCanceladas to set
	 */
	public void setIvaFactCanceladas(String ivaFactCanceladas) {
		this.ivaFactCanceladas = ivaFactCanceladas;
	}
	
	/**
	 * @return the totalFactCanceladas
	 */
	public String getTotalFactCanceladas() {
		return totalFactCanceladas;
	}
	
	/**
	 * @param totalFactCanceladas the totalFactCanceladas to set
	 */
	public void setTotalFactCanceladas(String totalFactCanceladas) {
		this.totalFactCanceladas = totalFactCanceladas;
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

	
}
