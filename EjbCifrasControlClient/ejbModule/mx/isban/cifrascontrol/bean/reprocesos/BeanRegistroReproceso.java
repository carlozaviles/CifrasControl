/**
 * 
 */
package mx.isban.cifrascontrol.bean.reprocesos;

import java.io.Serializable;

/**
 * @author everis
 *
 */
public class BeanRegistroReproceso implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -8206469457464520895L;
	/**
	 * Campo que representa la calle de la direccion del cliente.
	 */
	private String calle;
	/**
	 * Campo que representa el codigo postal de la direccion del cliente.
	 */
    private String codigoPostal;
    /**
     * Campo que representa la colonia de la direccion del cliente.
     */
    private String colonia;
    /**
     * Campo que representa el estado para la direccion del cliente.
     */
    private String estado;
    /**
     * Campo que representa el estatus del reproceso.
     */
    private String estatus;
    /**
     * Campo que representa la fecha final.
     */
    private String fechaFin;
    /**
     * Campo que representa la fecha de solicitud del reproceso.
     */
    private String fechaSolicitud;
    /**
     * Folio de cancelacion.
     */
    private String folioCancelacion;
    /**
     * Folio Inicial.
     */
    private String folioInicial;
    /**
     * Folio Nuevo.
     */
    private String folioNuevo;
    /**
     * Apellido materno del cliente.
     */
    private String materno;
    /**
     * Tipo de movimiento.
     */
    private String movimiento;
    /**
     * Municipio del cliente.
     */
    private String municipio;
    /**
     * Nombre del cliente.
     */
    private String nombre;
    /**
     * Numero de cuenta del cliente.
     */
    private String numeroCuenta;
    /**
     * Numero exterior de la direccion del cliente.
     */
    private String numeroExterior;
    /**
     * Numero interior de la direccion del cliente.
     */
    private String numeroInterior;
    /**
     * Apellido paterno del cliente.
     */
    private String paterno;
    /**
     * Periodo de este reproceso.
     */
    private String periodo;
    /**
     * Producto.
     */
    private String producto;
    /**
     * RFC del cliente.
     */
    private String rfc;
    /**
     * Usuario Operativo que solicito el reproceso.
     */
    private String usuarioOperativo;
    
	/**
	 * @return the calle
	 */
	public String getCalle() {
		return calle;
	}
	
	/**
	 * @param calle the calle to set
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}
	
	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	/**
	 * @return the colonia
	 */
	public String getColonia() {
		return colonia;
	}
	
	/**
	 * @param colonia the colonia to set
	 */
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/**
	 * @return the estatus
	 */
	public String getEstatus() {
		return estatus;
	}
	
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}
	
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	/**
	 * @return the fechaSolicitud
	 */
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	
	/**
	 * @param fechaSolicitud the fechaSolicitud to set
	 */
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	
	/**
	 * @return the folioCancelacion
	 */
	public String getFolioCancelacion() {
		return folioCancelacion;
	}
	
	/**
	 * @param folioCancelacion the folioCancelacion to set
	 */
	public void setFolioCancelacion(String folioCancelacion) {
		this.folioCancelacion = folioCancelacion;
	}
	
	/**
	 * @return the folioInicial
	 */
	public String getFolioInicial() {
		return folioInicial;
	}
	
	/**
	 * @param folioInicial the folioInicial to set
	 */
	public void setFolioInicial(String folioInicial) {
		this.folioInicial = folioInicial;
	}
	
	/**
	 * @return the folioNuevo
	 */
	public String getFolioNuevo() {
		return folioNuevo;
	}
	
	/**
	 * @param folioNuevo the folioNuevo to set
	 */
	public void setFolioNuevo(String folioNuevo) {
		this.folioNuevo = folioNuevo;
	}
	
	/**
	 * @return the materno
	 */
	public String getMaterno() {
		return materno;
	}
	
	/**
	 * @param materno the materno to set
	 */
	public void setMaterno(String materno) {
		this.materno = materno;
	}
	
	/**
	 * @return the movimiento
	 */
	public String getMovimiento() {
		return movimiento;
	}
	
	/**
	 * @param movimiento the movimiento to set
	 */
	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}
	
	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}
	
	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * @return the numeroExterior
	 */
	public String getNumeroExterior() {
		return numeroExterior;
	}
	/**
	 * @param numeroExterior the numeroExterior to set
	 */
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}
	/**
	 * @return the numeroInterior
	 */
	public String getNumeroInterior() {
		return numeroInterior;
	}
	/**
	 * @param numeroInterior the numeroInterior to set
	 */
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}
	/**
	 * @return the paterno
	 */
	public String getPaterno() {
		return paterno;
	}
	/**
	 * @param paterno the paterno to set
	 */
	public void setPaterno(String paterno) {
		this.paterno = paterno;
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
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}
	/**
	 * @param producto the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}
	/**
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}
	/**
	 * @param rfc the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	/**
	 * @return the usuarioOperativo
	 */
	public String getUsuarioOperativo() {
		return usuarioOperativo;
	}
	/**
	 * @param usuarioOperativo the usuarioOperativo to set
	 */
	public void setUsuarioOperativo(String usuarioOperativo) {
		this.usuarioOperativo = usuarioOperativo;
	}
}
