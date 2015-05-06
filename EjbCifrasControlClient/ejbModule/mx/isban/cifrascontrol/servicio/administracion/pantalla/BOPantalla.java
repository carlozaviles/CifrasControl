package mx.isban.cifrascontrol.servicio.administracion.pantalla;

import java.util.List;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantalla;

public interface BOPantalla {
	public List<BeanPantalla> buscarTodasPantallas(ArchitechSessionBean sessionBean)throws BusinessException;
	public BeanPantalla obtenerPantallaPorId(ArchitechSessionBean sessionBean, String idPantalla) throws BusinessException;
	public void modificarPantalla(ArchitechSessionBean sessionBean,BeanPantalla pantalla)throws BusinessException;
	public void agregarPantalla(ArchitechSessionBean sessionBean,BeanPantalla pantalla)throws BusinessException;
	public void borrarPantalla(ArchitechSessionBean sessionBean,String idPantalla)throws BusinessException;
}
