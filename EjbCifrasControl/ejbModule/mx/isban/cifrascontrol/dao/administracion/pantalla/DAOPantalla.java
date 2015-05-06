package mx.isban.cifrascontrol.dao.administracion.pantalla;

import javax.ejb.Local;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantalla;
import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantallaRespuesta;

@Local
public interface DAOPantalla {
	
	public BeanPantallaRespuesta obtenerTodasPantallas(ArchitechSessionBean sessionBean);
	public BeanPantallaRespuesta obtenerPantallasPorGrupo(ArchitechSessionBean sessionBean,String idGrupo);
	public BeanPantallaRespuesta obtenerPantallaPorId(ArchitechSessionBean sessionBean,String idPantalla);
	public BeanPantallaRespuesta modificarPantalla(ArchitechSessionBean sessionBean,BeanPantalla pantalla);
	public BeanPantallaRespuesta altaPantalla(ArchitechSessionBean sessionBean,BeanPantalla pantalla);
	public BeanPantallaRespuesta borrarPantalla(ArchitechSessionBean sessionBean,String idPantalla);
}
