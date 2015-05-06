package mx.isban.cifrascontrol.servicio.administracion.usuario;

import java.util.List;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuario;

public interface BOUsuario {
	
	public List<BeanUsuario> obtenerTodosUsuarios(ArchitechSessionBean sessionBean);
	public BeanUsuario obtenerUsuarioPorID(ArchitechSessionBean sessionBean,String idUsuario);
	public void modificarUsuario(ArchitechSessionBean sessionBean,BeanUsuario usuario);
	public void altaUsuario(ArchitechSessionBean sessionBean,BeanUsuario usuario);
	public void bajaUsuario(ArchitechSessionBean sessionBean,BeanUsuario usuario);
	

}
