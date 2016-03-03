/**
 * 
 */
package mx.isban.cifrascontrol.bean.reprocesos;

import java.io.Serializable;

/**
 * @author everis
 *
 */
public class BeanDatosSolicitudReprocesos implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 3116623693942643024L;
	/**
	 * Numero de cuenta.
	 */
	private String numeroCuenta;
	/**
	 * Secuencia de domicilio
	 */
	private String secDom;
	/**
	 * Codigo de cliente
	 */
	private String numeroCliente;
	/**
	 * Producto.
	 */
	private String producto;
	/**
	 * Mes.
	 */
	private String mes;
	/**
	 * Anio.
	 */
	private String anio;
	/**
	 * Tipo de Movimiento.
	 */
	private String movimiento;
	/**
	 * Pripiedad que representa el nombre del cliente.
	 */
	private String nombre;
	/**
	 * Propiedad que representa al apellido paterno del cliente.
	 */
	private String paterno;
	/**
	 * Propieadad que representa al apellido materno del cliente.
	 */
	private String materno;
	/**
	 * Propiedad que representa el RFC del cliente.
	 */
	private String rfc;
	/**
	 * Propiedad que representa la calle de la direccion del cliente
	 */
	private String calle;
	/**
	 * Propiedad que representa el numero exterior del cliente.
	 */
	private String numeroExterior;
	/**
	 * Propiedad que representa el numero interior del cliente.
	 */
	private String numeroInterior;
	/**
	 * Propiedad que representa la colonia de la direccion del cliente.
	 */
	private String colonia;
	/**
	 * Propiedad que representa el codigo postal del cliente.
	 */
	private String codigoPostal;
	/**
	 * Propiedad que representa el municipio del cliente.
	 */
	private String municipio;
	/**
	 * Propiedad que representa la entidad federativa del cliente.
	 */
	private String estado;
	
	
	/**
	 * @return the secDom
	 */
	public String getSecDom() {
		return secDom;
	}

	/**
	 * @param secDom the secDom to set
	 */
	public void setSecDom(String secDom) {
		this.secDom = secDom;
	}

	/**
	 * @return the numeroCliente
	 */
	public String getNumeroCliente() {
		return numeroCliente;
	}

	/**
	 * @param numeroCliente the numeroCliente to set
	 */
	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	/**
	 * Retorna el numero de cuenta.
	 * @return String
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	
	/**
	 * Establece el numero de cuenta.
	 * @param numeroCuenta Valor que sera establecido en el numero de cuenta.
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	/**
	 * Retorna el campo producto.
	 * @return String
	 */
	public String getProducto() {
		return producto;
	}
	
	/**
	 * Establece el valor del campo producto
	 * @param producto Valor que sera colocado en el campo producto.
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	/**
	 * Retorna el campo mes.
	 * @return String
	 */
	public String getMes() {
		return mes;
	}
	
	/**
	 * Establece el campo mes.
	 * @param mes Valor que sera colocado en el campo mes.
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	/**
	 * Retorna el campo anio
	 * @return String
	 */
	public String getAnio() {
		return anio;
	}
	
	/**
	 * Establece el campo anio.
	 * @param anio Valor que sera establedido en el campo anio.
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}
	
	/**
	 * Retorna el campo movimiento
	 * @return String
	 */
	public String getMovimiento() {
		return movimiento;
	}
	
	/**
	 * Establece el campo movimiento
	 * @param tipoMovimiento Valor que sera colocado en el campo movimiento.
	 */
	public void setMovimiento(String tipoMovimiento) {
		this.movimiento = tipoMovimiento;
	}
	
	/**
	 * Retorna el campo nombre.
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Establece el campo nombre
	 * @param nombre Valor que sera colocado en el campo nombre.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Retorna el campo paterno.
	 * @return String
	 */
	public String getPaterno() {
		return paterno;
	}
	
	/**
	 * Establece el campo paterno
	 * @param paterno Valor que sera colocado en el campo paterno.
	 */
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
	
	/**
	 * Retorna el campo materno.
	 * @return String
	 */
	public String getMaterno() {
		return materno;
	}
	
	/**
	 * Establece el campo materno.
	 * @param materno Valor que sera colocado en el campo materno.
	 */
	public void setMaterno(String materno) {
		this.materno = materno;
	}
	
	/**
	 * Obtiene el campo rfc
	 * @return String
	 */
	public String getRfc() {
		return rfc;
	}
	
	/**
	 * Establece el campo rfc.
	 * @param rfc Valor que sera colocado en el campo rfc.
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
	/**
	 * Retorna el valor del campo calle.
	 * @return String
	 */
	public String getCalle() {
		return calle;
	}
	
	/**
	 * Establece el valor del campo calle.
	 * @param calle Valor que sera colocado en el campo calle.
	 */
	public void setCalle(String calle) {
		this.calle = calle;
	}
	
	/**
	 * Retorna el campo numeroExterior
	 * @return String
	 */
	public String getNumeroExterior() {
		return numeroExterior;
	}
	
	/**
	 * Establece el campo numeroExterior
	 * @param numeroExterior Valor que sera colocado en el campo numeroExterior.
	 */
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}
	
	/**
	 * Retorna el campo numeroInterior
	 * @return String.
	 */
	public String getNumeroInterior() {
		return numeroInterior;
	}
	
	/**
	 * Establece el campo numeroInterior.
	 * @param numeroInterior Valor que sera colocado en el campo numeroInterior.
	 */
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}
	
	/**
	 * Retorna el campo colonia.
	 * @return String
	 */
	public String getColonia() {
		return colonia;
	}
	
	/**
	 * Establece el valor del campo colonia.
	 * @param colonia Valor que sera establecido en el campo colonia.
	 */
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	
	/**
	 * Retorna el campo codigoPostal.
	 * @return String
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}
	
	/**
	 * Establece el campo codigoPostal.
	 * @param codigoPostal Valor que sera colocado en el campo codigoPostal.
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	/**
	 * Retorna el campo municipio.
	 * @return String
	 */
	public String getMunicipio() {
		return municipio;
	}
	
	/**
	 * Establece el campo municipio.
	 * @param municipio Valor que sera colocado en el campo municipio.
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	/**
	 * Retorna el campo estado.
	 * @return String
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Establece el campo estado.
	 * @param estado Valor que sera colocado en el campo estado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		final StringBuilder salida = new StringBuilder();
		salida.append("numeroCuenta: " + this.numeroCuenta).append(", producto: " + this.producto)
		.append(", mes: " + this.mes).append(" , anio: " + this.anio).append(" , movimiento; " + this.movimiento)
		.append(", nombre: " + this.nombre).append(", paterno: " + this.paterno).append(", materno: " + this.materno)
		.append(", rfc: " + this.rfc).append(", calle: " + this.calle).append(", numeroExterior: " + this.numeroExterior)
		.append(", numeroInterior: " + this.numeroInterior).append(", colonia: " + this.colonia)
		.append(", codigoPostal: " + this.codigoPostal).append(", municipio: " + this.municipio)
		.append(", estado: " + this.estado).append(", numeroCliente: " + this.numeroCliente).append(", secDom: " + this.secDom);
		
		return salida.toString();
	}
}
