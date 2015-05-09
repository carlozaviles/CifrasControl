package mx.isban.cifrascontrol.beans.administracion.pantalla;

import java.io.Serializable;
import java.util.List;

import mx.isban.cifrascontrol.beans.administracion.modulo.BeanModulo;

public class BeanPantalla implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idPantalla;
	private String nombrePantalla;
	private String descripcionPantalla;
	private String url;
	private boolean pantallaSeleccionada;
	private List<BeanModulo> modulos;
	
	public BeanPantalla() {
		super();
	}
	
	
	
	public String getIdPantalla() {
		return idPantalla;
	}



	public void setIdPantalla(String idPantalla) {
		this.idPantalla = idPantalla;
	}



	public String getNombrePantalla() {
		return nombrePantalla;
	}

	public void setNombrePantalla(String nombrePantalla) {
		this.nombrePantalla = nombrePantalla;
	}

	public String getDescripcionPantalla() {
		return descripcionPantalla;
	}

	public void setDescripcionPantalla(String descripcionPantalla) {
		this.descripcionPantalla = descripcionPantalla;
	}



	public boolean isPantallaSeleccionada() {
		return pantallaSeleccionada;
	}



	public void setPantallaSeleccionada(boolean pantallaSeleccionada) {
		this.pantallaSeleccionada = pantallaSeleccionada;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public List<BeanModulo> getModulos() {
		return modulos;
	}



	public void setModulos(List<BeanModulo> modulos) {
		this.modulos = modulos;
	}
	
}
