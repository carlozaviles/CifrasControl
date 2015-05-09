package mx.isban.cifrascontrol.beans.administracion.modulo;

import java.util.List;

import mx.isban.agave.commons.interfaces.BeanResultBO;

public class BeanModuloRespuesta implements BeanResultBO {

	private String codError;
	private String msgError;
	private List<BeanModulo> modulos;
	
	public BeanModuloRespuesta() {
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

	public List<BeanModulo> getModulos() {
		return modulos;
	}

	public void setModulos(List<BeanModulo> modulos) {
		this.modulos = modulos;
	}

}
