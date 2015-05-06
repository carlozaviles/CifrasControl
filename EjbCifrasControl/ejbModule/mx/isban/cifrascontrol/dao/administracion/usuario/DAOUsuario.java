package mx.isban.cifrascontrol.dao.administracion.usuario;

import javax.ejb.Local;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuario;
import mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuarioRespuesta;

@Local
public interface DAOUsuario {
	
	public BeanUsuarioRespuesta obtenerTodosUsuarios(ArchitechSessionBean sessionBean);
	public BeanUsuarioRespuesta obtenerUsuarioPorID(ArchitechSessionBean sessionBean,String idUsuario);
	public BeanUsuarioRespuesta modificarUsuario(ArchitechSessionBean sessionBean,BeanUsuario usuario);
	public BeanUsuarioRespuesta altaUsuario(ArchitechSessionBean sessionBean,BeanUsuario usuario);
	public BeanUsuarioRespuesta bajaUsuario(ArchitechSessionBean sessionBean,BeanUsuario usuario);
	
}
