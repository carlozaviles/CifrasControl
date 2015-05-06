package mx.isban.cifrascontrol.beans.administracion.usuario;

import java.io.Serializable;
import java.util.List;

import mx.isban.agave.commons.interfaces.BeanResultBO;

public class BeanUsuarioRespuesta implements Serializable, BeanResultBO {

	private static final long serialVersionUID = 1L;

	private String codError;
	private String msgError;
	private List<BeanUsuario> usuarios;
	
	public BeanUsuarioRespuesta() {
		super();
	}
	
	@Override
	public String getCodError() {
		return this.codError;
	}

	@Override
	public String getMsgError() {
		return this.msgError;
	}

	@Override
	public void setCodError(String codError) {
		this.codError = codError;
	}

	@Override
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public List<BeanUsuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<BeanUsuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	

}
