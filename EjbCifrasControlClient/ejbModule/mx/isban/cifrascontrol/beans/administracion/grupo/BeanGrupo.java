/**************************************************************
* Queretaro, Qro Mayo 2015
*
* La redistribucion y el uso en formas fuente y binario, 
* son responsabilidad del propietario.
* 
* Este software fue elaborado en @Everis
* 
* Para mas informacion, consulte <www.everis.com/mexico>
***************************************************************/
package mx.isban.cifrascontrol.beans.administracion.grupo;

import java.io.Serializable;
import java.util.List;

import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantalla;

/**
 * Clase BeanGrupo
 * 
 * Clase que contiene las propiedades relacionadas a un grupo
 *  
 * @author Everis
 * @version 1.0
 * @see www.everis.com/mexico
 *
 */
public class BeanGrupo implements Serializable {

	/**
	 * Numero de version de la clase serializada
	 */
	private static final long serialVersionUID = 1991541919210186071L;
	/**
	 * Propiedad que representa el id del grupo
	 */
	private String idGrupo;
	/**
	 * Propiedad que representa el nombre del grupo
	 */
	private String nombreGrupo;
	/**
	 * Propiedad que representa la descripcion del grupo
	 */
	private String descripcionGrupo;
	/**
	 * Propiedad que representa TRUE si el grupo es seleccionado
	 */
	private boolean grupoSeleccionado;
	/**
	 * Lista de pantallas relacionadas al grupo
	 */
	private List<BeanPantalla> pantallas;
	/**
	 * Contiene el tipo de Grupo.
	 */
	private String tipoGrupo;
	/**
	 * En caso de que este grupo sea de tipo Operativo, contiene el grupo Administrador al que pertenece
	 */
	private String grupoAdministrador;

	/**
	 *Constructor que crea un nuevo objeto de tipo BeanGrupo 
	 */
	public BeanGrupo(){
		super();
	}	
	
	/**
	 * Metodo que obtiene el id del grupo
	 * @return String con el id del grupo
	 */
	public String getIdGrupo() {
		return idGrupo;
	}

	/**
	 * Metodo que establece el id del grupo
	 * @param idGrupo El id del grupo a establecer
	 */
	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}



	/**
	 * Metodo que obtiene el nombre del grupo
	 * @return El nombre del grupo
	 */
	public String getNombreGrupo() {
		return nombreGrupo;
	}



	/**
	 * Metodo que establece el nombre del grupo
	 * @param nombreGrupo El nombre del grupo a establecer
	 */
	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}



	/**
	 * Metodo que obtiene la descripcion del grupo
	 * @return La descripcion del grupo
	 */
	public String getDescripcionGrupo() {
		return descripcionGrupo;
	}



	/**
	 * Metodo que establece la descripcion del grupo
	 * @param descripcionGrupo Valor a establecer.
	 */
	public void setDescripcionGrupo(String descripcionGrupo) {
		this.descripcionGrupo = descripcionGrupo;
	}



	/**
	 * Metodo que obtiene las pantallas relacionadas al grupo
	 * @return Las pantallas relacionadas a un grupo
	 */
	public List<BeanPantalla> getPantallas() {
		return pantallas;
	}

	/**
	 * Metodo que establece las pantallas relacionadas al grupo
	 * @param pantallas Pantallas relacionas al grupo
	 */
	public void setPantallas(List<BeanPantalla> pantallas) {
		this.pantallas = pantallas;
	} 
	
	/**
	 * Metodo que obtiene en forma de String las pantallas relacionadas al grupo
	 * @return String con el listado de pantallas
	 */
	public String pantallasToString(){
		if(null == this.pantallas){
			return "";
		}else{
			final StringBuilder grupos = new StringBuilder();
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

	/**
	 * Metodo que regresa el valor booleano de un grupo seleccionado
	 * @return Valor booleano (True si el grupo es seleccionado)
	 */
	public boolean isGrupoSeleccionado() {
		return grupoSeleccionado;
	}

	/**
	 * Establece el valor booleano si el grupo es seleccionado
	 * 
	 * @param grupoSeleccionado Valor a establecer
	 */
	public void setGrupoSeleccionado(boolean grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;
	}
	
	/**
	 * @return the tipoGrupo
	 */
	public String getTipoGrupo() {
		return tipoGrupo;
	}

	/**
	 * @param tipoGrupo the tipoGrupo to set
	 */
	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	/**
	 * @return the grupoAdministrador
	 */
	public String getGrupoAdministrador() {
		return grupoAdministrador;
	}

	/**
	 * @param grupoAdministrador the grupoAdministrador to set
	 */
	public void setGrupoAdministrador(String grupoAdministrador) {
		this.grupoAdministrador = grupoAdministrador;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGrupo == null) ? 0 : idGrupo.hashCode());
		result = prime * result
				+ ((nombreGrupo == null) ? 0 : nombreGrupo.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (!(obj instanceof BeanGrupo)){
			return false;
		}
		BeanGrupo other = (BeanGrupo) obj;
		if (idGrupo == null) {
			if (other.idGrupo != null)
				return false;
		} else if (!idGrupo.equals(other.idGrupo)){
			return false;
		}
		if (nombreGrupo == null) {
			if (other.nombreGrupo != null)
				return false;
		} else if (!nombreGrupo.equals(other.nombreGrupo)){
			return false;
		}
		return true;
	}
}
