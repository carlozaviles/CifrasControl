package mx.isban.cifrascontrol.servicio.administracion.grupo;

import java.util.List;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.beans.administracion.grupo.BeanGrupo;

public interface BOGrupo {

	public List<BeanGrupo> buscarTodosGrupos(ArchitechSessionBean sessionBean)throws BusinessException;
	
	public void agregarGrupo(BeanGrupo perfil, ArchitechSessionBean sessionBean) throws BusinessException;
	
	public void modificarGrupo(BeanGrupo perfil, ArchitechSessionBean sessionBean)throws BusinessException;
	
	public void borrarGrupo(String idGrupo, ArchitechSessionBean sessionBean)throws BusinessException;
	
	public BeanGrupo consultarGrupo(String idPerfil, ArchitechSessionBean sessionBean) throws BusinessException;
	
}
