package mx.isban.cifrascontrol.beans.administracion.pantalla;

import java.util.List;

import mx.isban.agave.commons.interfaces.BeanResultBO;

public class BeanPantallaRespuesta implements BeanResultBO {

	private List<BeanPantalla> pantallas;
	private String codError;
	private String msgError;
	
	public BeanPantallaRespuesta() {
		super();
	}
	
	public List<BeanPantalla> getPantallas() {
		return pantallas;
	}

	public void setPantallas(List<BeanPantalla> pantallas) {
		this.pantallas = pantallas;
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
