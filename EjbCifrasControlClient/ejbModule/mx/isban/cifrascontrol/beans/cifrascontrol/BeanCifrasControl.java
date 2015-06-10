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

/**
 * Clase BeanCifrasControl
 * 
 * Clase que contiene las propiedades obtenidas de la respuesta a la consulta
 * del servicio web "Cifras de control", para la consulta de las cifras de control.
 * 
 * @author Everis
 * @version 1.0
 * @see www.everis.com/mexico
 * 
 */
public class BeanCifrasControl implements Serializable {

	/**
	 * Numero de la clase serializada
	 */
	private static final long serialVersionUID = -3376620812773329351L;
	
	/**
	 * Propiedad que contiene el valor del aplicativo
	 */
	private String aplicativo;
	/**
	 * Propiedad que contiene el valor de la fase
	 */
	private String fase;
	/**
	 * propiedad que contiene la cantidad de cuentas
	 */
	private String cantidadCuentas;
	/**
	 * Propiedad que contiene el valor del status
	 */
	private String status;
	/**
	 * Propiedad que contiene el valor del tipo de documento
	 */
	private String tipoDocumento;
	/**
	 * Propiedad que contiene el valor de la moneda
	 */
	private String moneda;
	/**
	 * Propiedad que contiene el valor del las comisiones e intereses
	 */
	private String comisionesIntereses;
	/**
	 * Propiedad que contiene el valor de los ivas
	 */
	private String ivas;
	/**
	 * Propiedad que contiene el valor de las retenciones
	 */
	private String retenciones;

	/**
	 * Propiedad que contiene el valor del periodo
	 */
	private String periodo;
	
	/**
	 * Propiedad que contiene el id del registro
	 */
	private String id;
	
	/**
	 * Propiedad que contiene la cantidad de cuentas que se
	 * generaron sin sello
	 */
	private String cuentasSinSello;
	
	/**
	 * Propiedad que contiene la cantidad de cuentas que se 
	 * generaron con incidencias
	 */
	private String cuentasConIncidencias;
	
	/**
	 * Propiedad que contiene la cantidad de cuentas con sello
	 * que se generaron
	 */
	private String cuentasConSello;
	
	/**
	 * Constructor por defecto
	 */
	public BeanCifrasControl() {
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
	 * @return La fase de las cifras
	 */
	public String getFase() {
		return fase;
	}

	/**
	 * Metodo que establece la fase
	 * @param fase a establecer
	 */
	public void setFase(String fase) {
		this.fase = fase;
	}

	/**
	 * Metodo que obtiene la cantidad de cuentas
	 * @return La cantidad de cuentas
	 */
	public String getCantidadCuentas() {
		return cantidadCuentas;
	}

	/**
	 * Metodo que establece el
	 * @param cantidadCuentas a establecer
	 */
	public void setCantidadCuentas(String cantidadCuentas) {
		this.cantidadCuentas = cantidadCuentas;
	}

	/**
	 * Metodo que obtiene el estatus
	 * @return El status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Metodo que establece el status
	 * @param status a establecer
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @param tipoDocumento a establecer
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Metodo que obtiene la moneda
	 * @return La moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Metodo que establece la moneda
	 * @param moneda la moneda a establecer
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Metodo que obtiene las comisiones e intereses
	 * @return Las comisiones e intereses
	 */
	public String getComisionesIntereses() {
		return comisionesIntereses;
	}

	/**
	 * Metodo que establece las comisiones - intereses
	 * @param comisionesIntereses a establecer
	 */
	public void setComisionesIntereses(String comisionesIntereses) {
		this.comisionesIntereses = comisionesIntereses;
	}

	/**
	 * Metodo que obtiene las ivas
	 * @return Los ivas presentados
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
	 * @param retenciones a establecer
	 */
	public void setRetenciones(String retenciones) {
		this.retenciones = retenciones;
	}


	/**
	 * Metodo que obtiene el periodo
	 * @return El periodo solicitado
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
	 * Metodo que obtiene el id del registro
	 * @return El id del registro
	 */
	public String getId() {
		return id;
	}

	/**
	 * Metodo que establece el id del registro
	 * @param id El id a establecer
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Metodo para obtener las cuentas sin sello
	 * @return Las cuentas sin sello
	 */
	public String getCuentasSinSello() {
		return cuentasSinSello;
	}

	/**
	 * Metodo encargado de establecer las cuentas sin sello
	 * @param cuentasSinSello Las cuentas sin sello a establecer
	 */
	public void setCuentasSinSello(String cuentasSinSello) {
		this.cuentasSinSello = cuentasSinSello;
	}

	/**
	 * Metodo que obtiene la cantidad de cuentas con incidencias
	 * @return La cantidad de cuentas con incidencias
	 */
	public String getCuentasConIncidencias() {
		return cuentasConIncidencias;
	}

	/**
	 * Metodo encargado de establecer las cuentas con incidencias
	 * @param cuentasConIncidencias La cantidad de cuentas con incidencias
	 * a establecer
	 */
	public void setCuentasConIncidencias(String cuentasConIncidencias) {
		this.cuentasConIncidencias = cuentasConIncidencias;
	}

	/**
	 * Metodo encargado de obtener la cantidad de cuentas con sello
	 * @return La cantidad de cuentas generadas con sello
	 */
	public String getCuentasConSello() {
		return cuentasConSello;
	}

	/**
	 * Metodo encargado de establecer la cantidad de cuentas con sello
	 * @param cuentasConSello La cantidad de cuentas con sello 
	 */
	public void setCuentasConSello(String cuentasConSello) {
		this.cuentasConSello = cuentasConSello;
	}

	/* (sin Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builderCifras = new StringBuilder();
		builderCifras.append("[Id:").append(id).append("][Aplicativo:").append(aplicativo)
		.append("][Fase:").append(fase).append("][Cantidad cuentas:").append(cantidadCuentas)
		.append("][Cuentas sin sello:").append(cuentasSinSello).append("][Cuentas con incidencias")
		.append(cuentasConIncidencias).append("][Cuentas con sello:").append(cuentasConSello).append("][Status:").append(status)
		.append("][Tipo documento:").append(tipoDocumento).append("][Moneda:").append(moneda)
		.append("][Comisiones intereses:").append(comisionesIntereses).append("][Ivas:").append(ivas)
		.append("][Retenciones:").append(retenciones).append("][Periodo:").append(periodo).append("]");
		return builderCifras.toString();
	}
}
