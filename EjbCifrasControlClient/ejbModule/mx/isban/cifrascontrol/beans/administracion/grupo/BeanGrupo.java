package mx.isban.cifrascontrol.beans.administracion.grupo;

import java.io.Serializable;
import java.util.List;

import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantalla;

public class BeanGrupo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idGrupo;
	private String nombreGrupo;
	private String descripcionGrupo;
	private boolean grupoSeleccionado;
	private List<BeanPantalla> pantallas;

	public BeanGrupo(){
		super();
	}	
	
	public String getIdGrupo() {
		return idGrupo;
	}



	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}



	public String getNombreGrupo() {
		return nombreGrupo;
	}



	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}



	public String getDescripcionGrupo() {
		return descripcionGrupo;
	}



	public void setDescripcionGrupo(String descripcionGrupo) {
		this.descripcionGrupo = descripcionGrupo;
	}



	public List<BeanPantalla> getPantallas() {
		return pantallas;
	}

	public void setPantallas(List<BeanPantalla> pantallas) {
		this.pantallas = pantallas;
	} 
	
	public String pantallasToString(){
		if(null == this.pantallas){
			return "";
		}else{
			StringBuilder grupos = new StringBuilder();
			for (int i = 0; i < this.pantallas.size(); i++) {
				if(i == (this.pantallas.size()-1)){
					grupos.append(this.pantallas.get(i).getNombrePantalla());
				}else{
					grupos.append(this.pantallas.get(i).getNombrePantalla()).append(",");
				}
			}
			return grupos.toString();
		}
	}

	public boolean isGrupoSeleccionado() {
		return grupoSeleccionado;
	}

	public void setGrupoSeleccionado(boolean grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;
	}
	
	
}
