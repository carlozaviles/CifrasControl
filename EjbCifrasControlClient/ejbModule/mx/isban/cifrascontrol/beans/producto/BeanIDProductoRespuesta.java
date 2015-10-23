package mx.isban.cifrascontrol.beans.producto;

import java.io.Serializable;
import java.util.Map;

import mx.isban.agave.commons.interfaces.BeanResultBO;

public class BeanIDProductoRespuesta implements BeanResultBO, Serializable {
	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 6195311811361793280L;
	/**
	 * Codigo de error
	 */
	private String codError;
	/**
	 * Mensaje de error.
	 */
	private String msgError;
	/**
	 * Lista de Identificadores de productos.
	 */
	private Map<String, String> listaIdentificadoresProductos;

	/**
	 * @return the listaIdentificadoresProductos
	 */
	public Map<String, String> getListaIdentificadoresProductos() {
		return listaIdentificadoresProductos;
	}

	/**
	 * @param listaIdentificadoresProductos the listaIdentificadoresProductos to set
	 */
	public void setListaIdentificadoresProductos(
			Map<String, String> listaIdentificadoresProductos) {
		this.listaIdentificadoresProductos = listaIdentificadoresProductos;
	}

	/* (non-Javadoc)
	 * @see mx.isban.agave.commons.interfaces.BeanResultBO#getCodError()
	 */
	@Override
	public String getCodError() {
		return this.codError;
	}

	/* (non-Javadoc)
	 * @see mx.isban.agave.commons.interfaces.BeanResultBO#getMsgError()
	 */
	@Override
	public String getMsgError() {
		return this.msgError;
	}

	/* (non-Javadoc)
	 * @see mx.isban.agave.commons.interfaces.BeanResultBO#setCodError(java.lang.String)
	 */
	@Override
	public void setCodError(String codError) {
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
