/**************************************************************
* Queretaro, Qro 2015 Junio
*
* La redistribucion y el uso en formas fuente y binario, 
* son responsabilidad del propietario.
* 
* Este software fue elaborado en @Everis
* 
* Para mas informacion, consulte <www.everis.com/mexico>
***************************************************************/
package mx.isban.cifrascontrol.beans.facturas;

import java.io.Serializable;

/**
 * Clase BeanFactura
 * 
 * Clase que contiene las propiedades obtenidas de la respuesta a la consulta
 * del servicio web "Cifras de control", para la consulta de facturas.
 * 
 * @author Everis
 * @version 1.0
 * @see www.everis.com/mexico
 * 
 */
public class BeanFactura implements Serializable {

	/**
	 * Numero de la clase serializada
	 */
	private static final long serialVersionUID = -1750263878389386608L;

	/**
	 * Propiedad que contiene el id del registro
	 */
	private String id;
	/**
	 * Propiedad que contiene la cantidad de facturas;
	 */
	private String cantidadFacturas;
	/**
	 * Propiedad que contiene el subtotal
	 */
	private String subTotal;
	/**
	 * Propiedad que contiene el iva
	 */
	private String iva;
	/**
	 *Propiedad que contiene el total de impuestos 
	 */
	private String totalImpuestos;
	/**
	 * Propiedad que contiene la propieda que indica si la factura es incidente
	 */
	private String facturaIncidente;
	/**
	 * Propiedad que contiene el tipo de factura
	 */
	private String tipoFactura;
	/**
	 * Propiedad que contiene el aplicativo
	 */
	private String aplicativo;
	/**
	 * Propiedad que contiene el periodo
	 */
	private String periodo;
	/**
	 * Propiedad que contiene la moneda
	 */
	private String moneda;
	/**
	 * Propiedad que contiene la cantidad de recibos
	 */
	private String cantidadRecibos;
	/**
	 * Propiedad que contiene el importe
	 */
	private String importe;
	
	/**
	 * Constructor vacio
	 */
	public BeanFactura() {
		super();
	}

	/**
	 * Metodo que obtiene la propiedad id
	 * @return El id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Metodo que establece la propiedad id
	 * @param id El id a establecer
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Metodo que obtiene la propiedad cantidad facturas
	 * @return La cantidad de facturas
	 */
	public String getCantidadFacturas() {
		return cantidadFacturas;
	}

	/**
	 * Metodo que establece la propiedad cantidad facturas.
	 * @param cantidadFacturas
	 */
	public void setCantidadFacturas(String cantidadFacturas) {
		this.cantidadFacturas = cantidadFacturas;
	}

	/**
	 * Metodo que obtiene la propiedad sub total
	 * @return El sub total
	 */
	public String getSubTotal() {
		return subTotal;
	}

	/**
	 * Metodo que establece la propiedad sub total
	 * @param subTotal El sub total a establecer
	 */
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	/**
	 * Metodo que obtiene la propiedad iva
	 * @return El iva
	 */
	public String getIva() {
		return iva;
	}

	/**
	 * Metodo que establece la propiedad iva
	 * @param iva El iva a establecer
	 */
	public void setIva(String iva) {
		this.iva = iva;
	}

	/**
	 * Metodo que obtiene la propiedad total impuestos
	 * @return El total de impuestos
	 */
	public String getTotalImpuestos() {
		return totalImpuestos;
	}

	/**
	 * Metodo que establece la propiedad  total impuestos
	 * @param totalImpuestos El total de impuestos a establecer
	 */
	public void setTotalImpuestos(String totalImpuestos) {
		this.totalImpuestos = totalImpuestos;
	}

	/**
	 * Metodo que obtiene la propiedad factura incidente
	 * @return La propiedad que indica si la factura es incidente
	 * (0 no es incidente, 1 es incidente)
	 */
	public String getFacturaIncidente() {
		return facturaIncidente;
	}

	/**
	 * @param facturaIncidente
	 */
	public void setFacturaIncidente(String facturaIncidente) {
		this.facturaIncidente = facturaIncidente;
	}

	/**
	 * Metodo que obtiene la propiedad tipo factura
	 * @return El tipo de factura
	 */
	public String getTipoFactura() {
		return tipoFactura;
	}

	/**
	 * Metodo que establece la propiedad tipo factura
	 * @param tipoFactura El tipo de factura a establecer
	 */
	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	/**
	 * Metodo que obtiene la propiedad aplicativo
	 * @return El aplicativo
	 */
	public String getAplicativo() {
		return aplicativo;
	}

	/**
	 * Metodo que establece la propiedad aplicativo
	 * @param aplicativo a establecer
	 */
	public void setAplicativo(String aplicativo) {
		this.aplicativo = aplicativo;
	}

	/**
	 * Metodo que obtiene la propiedad periodo
	 * @return El periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * Metodo que establece la propiedad periodo
	 * @param periodo El periodo a establecer
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * Metodo que obtiene la propiedad moneda
	 * @return La moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Metodo que establece la propiedad moneda
	 * @param moneda La moneda a establecer
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Metodo que obtiene la propiedad cantidad recibos
	 * @return La cantidad de recibos
	 */
	public String getCantidadRecibos() {
		return cantidadRecibos;
	}

	/**
	 * Metodo que establece la propiedad cantidad recibos
	 * @param cantidadRecibos La cantidad de recibos a establecer
	 */
	public void setCantidadRecibos(String cantidadRecibos) {
		this.cantidadRecibos = cantidadRecibos;
	}

	/**
	 * Metodo que obtiene la propiedad importe
	 * @return El importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Metodo que establece la propiedad importe
	 * @param importe El importe a establecer
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}
	
}
