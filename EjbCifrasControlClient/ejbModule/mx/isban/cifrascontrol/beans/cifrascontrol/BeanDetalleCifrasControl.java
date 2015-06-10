/**************************************************************
* Queretaro, Qro Junio 2015
*
* La redistribucion y el uso en formas fuente y binario, 
* son responsabilidad del propietario.
* 
* Este software fue elaborado en @Everis
* 
* Para mas informacion, consulte <www.everis.com/mexico>
***************************************************************/
package mx.isban.cifrascontrol.beans.cifrascontrol;

import java.io.Serializable;

public class BeanDetalleCifrasControl implements Serializable {

	/**
	 * Numero de la clase serializada
	 */
	private static final long serialVersionUID = 4707882479340081607L;

	/**
	 * Propiedad que contiene el id del registro
	 */
	private String id;
	
	/**
	 * Propiedad que contiene la moneda asignada al usuario
	 */
	private String moneda;
	
	/**
	 * Propiedad que contiene el aplicativo
	 */
	private String aplicativo;
	/**
	 * Propiedad que contiene la fase
	 */
	private String fase;
	/**
	 * Propiedad que contiene al emisor RFC
	 */
	private String emisorRFC;
	/**
	 * Propiedad que contiene al numero de cliente
	 */
	private String numeroCliente;
	/**
	 * Propiedad que contiene el numero de cuenta
	 */
	private String numeroCuenta;
	/**
	 * Propiedad que contiene el numero de tarjera
	 */
	private String numeroTarjeta;
	/**
	 * Propiedad que contiene el tipo de documento
	 */
	private String tipoDocumento;
	/**
	 * Propiedad que contiene comisiones Intereses
	 */
	private String comisionesIntereses;
	/**
	 * Propiedad que contiene ivas
	 */
	private String ivas;
	/**
	 * Propiedad que contiene las retenciones
	 */
	private String retenciones;
	/**
	 * Propiedad que contiene el periodo
	 */
	private String periodo;
	/**
	 * Propiedad que contiene la descripcion del error
	 */
	private String descripcionError;
	
	/**
	 * Constructor por defecto
	 */
	public BeanDetalleCifrasControl() {
		super();
	}

	/**
	 * Metodo que obtiene el aplicativo
	 * @return El aplicativo
	 */
	public String getAplicativo() {
		return aplicativo;
	}

	/**
	 * Metodo que establece el aplicativo
	 * @param aplicativo El aplicativo a establecer
	 */
	public void setAplicativo(String aplicativo) {
		this.aplicativo = aplicativo;
	}

	/**
	 * Metodo que obtiene la fase
	 * @return La fase
	 */
	public String getFase() {
		return fase;
	}

	/**
	 * Metodo que establece la fase
	 * @param fase La fase a establecer
	 */
	public void setFase(String fase) {
		this.fase = fase;
	}

	/**
	 * Metodo que obtiene el emisor RFC
 	 * @return El emisor RFC
	 */
	public String getEmisorRFC() {
		return emisorRFC;
	}

	/**
	 * Metodo que establece el emisor RFC
	 * @param emisorRFC Emisor RFC a establecer
	 */
	public void setEmisorRFC(String emisorRFC) {
		this.emisorRFC = emisorRFC;
	}

	/**
	 * Metodo que obtiene el numero de cliente
	 * @return El numero de cliente
	 */
	public String getNumeroCliente() {
		return numeroCliente;
	}

	/**
	 * Metodo que establece el numero de cliente
	 * @param numeroCliente a establecer
	 */
	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	/**
	 * Metodo que obtiene el numero de cuenta
	 * @return El numero de cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Metodo que establece el numero de cuenta
	 * @param numeroCuenta a establecer
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Metodo que obtiene el numero de tarjeta
	 * @return El numero de tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Metodo que establece el numero de tarjeta
	 * @param numeroTarjeta El numero de tarjeta a establecer
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	/**
	 * Metodo que obtiene el tipo de documento
	 * @return El tipo de documento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Metodo que establece el tipo de documento
	 * @param tipoDocumento
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Metodo que obtiene las comisiones intereses
	 * @return Las comisiones intereses
	 */
	public String getComisionesIntereses() {
		return comisionesIntereses;
	}

	/**
	 * Metodo que establece las comisiones intereses
	 * @param comisionesIntereses
	 */
	public void setComisionesIntereses(String comisionesIntereses) {
		this.comisionesIntereses = comisionesIntereses;
	}

	/**
	 * Metodo que obtiene los ivas
	 * @return Los ivas
	 */
	public String getIvas() {
		return ivas;
	}

	/**
	 * Metodo que establece los ivas
	 * @param ivas a establecer
	 */
	public void setIvas(String ivas) {
		this.ivas = ivas;
	}

	/**
	 * Metodo que obtiene las retenciones
	 * @return Las retenciones
	 */
	public String getRetenciones() {
		return retenciones;
	}

	/**
	 * Metodo que establece las retenciones
	 * @param retenciones Las retenciones a establecer
	 */
	public void setRetenciones(String retenciones) {
		this.retenciones = retenciones;
	}

	/**
	 * Metodo que obtiene el periodo
	 * @return El periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * Metodo que establece el periodo
	 * @param periodo a establecer
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * Metodo que obtiene la descripcion del error
	 * @return El error a obtener
	 */
	public String getDescripcionError() {
		return descripcionError;
	}

	/**
	 * Metodo que establece la descripcion del error
	 * @param descripcionError Descripcion del error a establecer
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	/**
	 * Metodo que obtiene el id del registro
	 * @return El id del registro
	 */
	public String getId() {
		return id;
	}

	/**
	 * Metodo para establecer el id del registro
	 * @param id El id del registro
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Metodo para obtener la moneda
	 * @return La moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Metodo que establece la moneda
	 * @param moneda La moneda a establecer
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/* (sin Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder detalleCifras = new StringBuilder();
		detalleCifras.append("[id:").append(id).append("][Aplicativo:").append(aplicativo)
		.append("][Fase:").append(fase).append("][Emisor RFC:").append(emisorRFC).append("][Moneda:").append(moneda)
		.append("][Numero de cliente:").append(numeroCliente).append("][Numero cuenta:").append(numeroCuenta).append("][Numero tarjeta:").append(numeroTarjeta)
		.append("][Tipo documento:").append(tipoDocumento).append("][Comisiones Intereses:").append(comisionesIntereses).append("][Ivas:").append(ivas).append("][Retenciones:")
		.append(retenciones).append("][Periodo:").append(periodo).append("][Descripcion Error:").append(descripcionError).append("]");
		return detalleCifras.toString();
	}
}
