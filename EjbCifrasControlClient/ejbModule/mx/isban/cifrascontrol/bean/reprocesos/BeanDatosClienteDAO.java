/**
 * 
 */
package mx.isban.cifrascontrol.bean.reprocesos;

import java.io.Serializable;

import mx.isban.agave.commons.interfaces.BeanResultBO;

/**
 * @author everis
 *
 */
public class BeanDatosClienteDAO implements BeanResultBO, Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -258223805451285355L;
	/**
	 * Numero de cuenta del usuario.
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
	 * Nombre del cliente resultado de la consulta.
	 */
	private String nombre;
	/**
	 * Apellido paterno del cliente resultado de la consulta.
	 */
	private String paterno;
	/**
	 * Apellido materno del cliente resultado de la consulta.
	 */
	private String materno;
	/**
	 * RFC del cliente producto de la consulta de personas.
	 */
	private String rfc;
	/**
	 * Calle que tiene registrada el cliente en su direccion.
	 */
	private String calle;
	/**
	 * Numero exterior que tiene registrado el cliente en su direccion.
	 */
	private String numeroExterior;
	/**
	 * Numero interior que tiene registrado el cliente en su direccion.
	 */
	private String numeroInterior;
	/**
	 * Colonia que tiene registrada el cliente en su direccion.
	 */
	private String colonia;
	/**
	 * Codigo Postal que tiene registrado el cliente en su direccion.
	 */
	private String codigoPostal;
	/**
	 * Municipio que tiene registrado el cliente en su direccion.
	 */
	private String municipio;
	/**
	 * Estado que tiene registrado el cliente en su direccion.
	 */
	private String estado;
	/**
	 * Codigo de error.
	 */
	private String codError;
	/**
	 * Mensaje de error.
	 */
	private String msgError;

	
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
	 * Retorna el campo numeroCuenta
	 * @return String
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Establece el valor del campo numeroCuenta
	 * @param numeroCuenta Valor que sera colocado en el campo numeroCuenta.
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Obtiene el campo nombre.
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el valor del campo nombre
	 * @param nombre Valor que sera colocado en el campo nombre.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el valor del campo paterno.
	 * @return String
	 */
	public String getPaterno() {
		return paterno;
	}

	/**
	 * Establece el valor del campo paterno.
	 * @param paterno Valor que sera colocado en el campo paterno.
	 */
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	/**
	 * Obtiene el campo materno.
	 * @return String
	 */
	public String getMaterno() {
		return materno;
	}

	/**
	 * Establece el valor del campo materno.
	 * @param materno Valor que sera colocado en el campo materno.
	 */
	public void setMaterno(String materno) {
		this.materno = materno;
	}

	/**
	 * Obtiene el valor del campo rfc.
	 * @return String
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * Establece el valor del campo rfc.
	 * @param rfc Valor que sera colocado en el campo rfc.
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/**
	 * Obtiene el valor del campo calle.
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
	 * Obtiene el valor del campo numeroExterior.
	 * @return String
	 */
	public String getNumeroExterior() {
		return numeroExterior;
	}

	/**
	 * Establece el valor del campo numeroExterior.
	 * @param numeroExterior Valor que sera colocado en el campo numeroExterior.
	 */
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}

	/**
	 * Obtiene el valor del campo numeroInterior.
	 * @return String
	 */
	public String getNumeroInterior() {
		return numeroInterior;
	}

	/**
	 * Establece el valor del campo numeroInterior.
	 * @param numeroInterior Valor que sera colocado en el campo numeroInterior.
	 */
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}

	/**
	 * Obtiene el valor del campo colonia.
	 * @return String
	 */
	public String getColonia() {
		return colonia;
	}

	/**
	 * Establece el valor del campo colonia.
	 * @param colonia Valor que sera colocado en el campo colonia.
	 */
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	/**
	 * Obtiene el valor del campo codigoPostal.
	 * @return String
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * Establece el valor del campo codigoPostal.
	 * @param codigoPostal Valor que sera colocado en el campo codigoPostal.
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * Obtiene el valor del campo municipio.
	 * @return String
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * Establece el valor del campo municipio.
	 * @param municipio Valor que sera colocado en el campo municipio.
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * Obtiene el valor del campo estado.
	 * @return String
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Establece el valor del campo estado.
	 * @param estado Valor que sera colocado en el campo estado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/* (non-Javadoc)
	 * @see mx.isban.agave.commons.interfaces.BeanResultBO#getCodError()
	 */
	@Override
	public String getCodError() {
		return codError;
	}

	/* (non-Javadoc)
	 * @see mx.isban.agave.commons.interfaces.BeanResultBO#getMsgError()
	 */
	@Override
	public String getMsgError() {
		return msgError;
	}

	/* (non-Javadoc)
	 * @see mx.isban.agave.commons.interfaces.BeanResultBO#setCodError(java.lang.String)
	 */
	@Override
	public void setCodError(String codError){
		this.codError = codError;
	}

	/* (non-Javadoc)
	 * @see mx.isban.agave.commons.interfaces.BeanResultBO#setMsgError(java.lang.String)
	 */
	@Override
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

}
