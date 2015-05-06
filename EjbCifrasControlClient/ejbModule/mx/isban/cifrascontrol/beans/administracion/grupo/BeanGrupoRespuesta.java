package mx.isban.cifrascontrol.beans.administracion.grupo;

import java.io.Serializable;
import java.util.List;

import mx.isban.agave.commons.interfaces.BeanResultBO;

public class BeanGrupoRespuesta implements Serializable, BeanResultBO {
	
	private static final long serialVersionUID = 1L;

	private String codError;
	private String msgError;
	private List<BeanGrupo> grupos;
	
	public BeanGrupoRespuesta() {
		super();
	}
	
	public List<BeanGrupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<BeanGrupo> grupos) {
		this.grupos = grupos;
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

}
