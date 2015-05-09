package mx.isban.cifrascontrol.servicio.administracion.modulo;

import java.util.List;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.beans.administracion.modulo.BeanModulo;

public interface BOModulo {
	
	public List<BeanModulo> obtenerModulosPorUsuarioLogueado(ArchitechSessionBean sessionBean,String idUsuario)throws BusinessException;
	public List<BeanModulo> obtenerTodosModulos(ArchitechSessionBean sessionBean)throws BusinessException;
	public BeanModulo obtenerModuloPorId(ArchitechSessionBean sessionBean, String idModulo)throws BusinessException;
	public void modificarModulo(ArchitechSessionBean sessionBean, BeanModulo modulo)throws BusinessException;
	public void altaModulo(ArchitechSessionBean sessionBean, BeanModulo modulo)throws BusinessException;
	public void borrarModulo(ArchitechSessionBean sessionBean, String idModulo)throws BusinessException;
}
