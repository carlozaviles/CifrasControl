/**
 * 
 */
package mx.isban.cifrascontrol.bean.auditoria;

import java.io.Serializable;

/**
 * @author everis
 *
 */
public class BeanPistaAuditoria implements Serializable {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 5924830850611152385L;
	/**
	 * Fecha en la que se realiza la operacion.
	 */
	private String fecha;
	/**
	 * Hora en la que se realiza la operacion.
	 */
	private String hora;
	/**
	 * IP de la terminal en la que el usuario realiza la operacion.
	 */
	private String ipTerminalCliente;
	/**
	 * Id del usuario de la aplicacion.
	 */
	private String usuarioApp;
	/**
	 * Nombre de la aplicacion.
	 */
	private String aplicacion;
	/**
	 * Codigo de la operacion que se realizo.
	 */
	private String codigoOperacion;
	/**
	 * Estatus en que termino la ejecucion de la operacion.
	 */
	private String estatusOperacion;
	/**
	 * Este campo se informa en caso de que un cliente haya sido afectado por la operacion.
	 */
	private String clienteAfectado;
	/**
	 * Identificador de la sesion.
	 */
	private String idSesion;
	/**
	 * Nombre del servidor en el que se ejecuta la aplicacion.
	 */
	private String nombreServidorWeb;
	/**
	 * Direccion IP del servidor en el que se ejecuta la operacion.
	 */
	private String ipServidorWeb;
	
	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}
	
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}
	
	/**
	 * @param hora the hora to set
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	/**
	 * @return the ipTerminalCliente
	 */
	public String getIpTerminalCliente() {
		return ipTerminalCliente;
	}
	
	/**
	 * @param ipTerminalCliente the ipTerminalCliente to set
	 */
	public void setIpTerminalCliente(String ipTerminalCliente) {
		this.ipTerminalCliente = ipTerminalCliente;
	}
	
	/**
	 * @return the usuarioApp
	 */
	public String getUsuarioApp() {
		return usuarioApp;
	}
	
	/**
	 * @param usuarioApp the usuarioApp to set
	 */
	public void setUsuarioApp(String usuarioApp) {
		this.usuarioApp = usuarioApp;
	}
	
	/**
	 * @return the aplicacion
	 */
	public String getAplicacion() {
		return aplicacion;
	}
	
	/**
	 * @param aplicacion the aplicacion to set
	 */
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
	
	/**
	 * @return the codigoOperacion
	 */
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	
	/**
	 * @param codigoOperacion the codigoOperacion to set
	 */
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	
	/**
	 * @return the estatusOperacion
	 */
	public String getEstatusOperacion() {
		return estatusOperacion;
	}
	
	/**
	 * @param estatusOperacion the estatusOperacion to set
	 */
	public void setEstatusOperacion(String estatusOperacion) {
		this.estatusOperacion = estatusOperacion;
	}
	
	/**
	 * @return the clienteAfectado
	 */
	public String getClienteAfectado() {
		return clienteAfectado;
	}
	
	/**
	 * @param clienteAfectado the clienteAfectado to set
	 */
	public void setClienteAfectado(String clienteAfectado) {
		this.clienteAfectado = clienteAfectado;
	}
	
	/**
	 * @return the idSesion
	 */
	public String getIdSesion() {
		return idSesion;
	}
	
	/**
	 * @param idSesion the idSesion to set
	 */
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}
	
	/**
	 * @return the nombreServidorWeb
	 */
	public String getNombreServidorWeb() {
		return nombreServidorWeb;
	}
	
	/**
	 * @param nombreServidorWeb the nombreServidorWeb to set
	 */
	public void setNombreServidorWeb(String nombreServidorWeb) {
		this.nombreServidorWeb = nombreServidorWeb;
	}
	
	/**
	 * @return the ipServidorWeb
	 */
	public String getIpServidorWeb() {
		return ipServidorWeb;
	}
	
	/**
	 * @param ipServidorWeb the ipServidorWeb to set
	 */
	public void setIpServidorWeb(String ipServidorWeb) {
		this.ipServidorWeb = ipServidorWeb;
	}
}