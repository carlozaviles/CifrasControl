package mx.isban.cifrascontrol.beans.administracion.grupo;

import java.io.Serializable;
import java.util.List;

import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantalla;

public class BeanGrupo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idPerfil;
	private String nombrePerfil;
	private String descripcionPerfil;
	private List<BeanPantalla> pantallas;

	public BeanGrupo(){
		super();
	}
	
	public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombrePerfil() {
		return nombrePerfil;
	}
	
	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}
	
	public String getDescripcionPerfil() {
		return descripcionPerfil;
	}
	
	public void setDescripcionPerfil(String descripcionPerfil) {
		this.descripcionPerfil = descripcionPerfil;
	}
	
	
	public List<BeanPantalla> getPantallas() {
		return pantallas;
	}

	public void setPantallas(List<BeanPantalla> pantallas) {
		this.pantallas = pantallas;
	} 
	
}
