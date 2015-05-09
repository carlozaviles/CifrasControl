package mx.isban.cifrascontrol.beans.administracion.modulo;

import java.io.Serializable;
import java.util.List;

import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantalla;

public class BeanModulo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idModulo;
	private String nombreModulo;
	private String descripcionModulo;
	private boolean moduloSeleccionado;
	private List<BeanPantalla> pantallas;
	
	public BeanModulo() {
		super();
	}

	public String getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(String idModulo) {
		this.idModulo = idModulo;
	}

	public String getNombreModulo() {
		return nombreModulo;
	}

	public void setNombreModulo(String nombreModulo) {
		this.nombreModulo = nombreModulo;
	}

	public List<BeanPantalla> getPantallas() {
		return pantallas;
	}

	public void setPantallas(List<BeanPantalla> pantallas) {
		this.pantallas = pantallas;
	}

	public boolean isModuloSeleccionado() {
		return moduloSeleccionado;
	}

	public void setModuloSeleccionado(boolean moduloSeleccionado) {
		this.moduloSeleccionado = moduloSeleccionado;
	}

	public String getDescripcionModulo() {
		return descripcionModulo;
	}

	public void setDescripcionModulo(String descripcionModulo) {
		this.descripcionModulo = descripcionModulo;
	}
	
	
}
