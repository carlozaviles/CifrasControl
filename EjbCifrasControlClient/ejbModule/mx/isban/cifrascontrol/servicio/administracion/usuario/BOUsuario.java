package mx.isban.cifrascontrol.servicio.administracion.usuario;

import java.util.List;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuario;

public interface BOUsuario {
	
	public List<BeanUsuario> obtenerTodosUsuarios(ArchitechSessionBean sessionBean)throws BusinessException;
	public BeanUsuario obtenerUsuarioPorID(ArchitechSessionBean sessionBean,String idUsuario)throws BusinessException;
	public void modificarUsuario(ArchitechSessionBean sessionBean,BeanUsuario usuario)throws BusinessException;
	public void altaUsuario(ArchitechSessionBean sessionBean,BeanUsuario usuario)throws BusinessException;
	

}
