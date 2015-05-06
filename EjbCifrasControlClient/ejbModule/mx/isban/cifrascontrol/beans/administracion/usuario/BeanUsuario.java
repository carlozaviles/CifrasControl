package mx.isban.cifrascontrol.beans.administracion.usuario;

import java.io.Serializable;
import java.util.List;

import mx.isban.cifrascontrol.beans.administracion.grupo.BeanGrupo;

public class BeanUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idUsuario;
	private boolean estatus;
	private List<BeanGrupo> grupos;
	
	public BeanUsuario() {
		super();
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public List<BeanGrupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<BeanGrupo> grupos) {
		this.grupos = grupos;
	}
	
	
	
}
