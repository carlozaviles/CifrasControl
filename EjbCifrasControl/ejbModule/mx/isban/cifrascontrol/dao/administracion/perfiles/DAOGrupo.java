package mx.isban.cifrascontrol.dao.administracion.perfiles;

import javax.ejb.Local;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.cifrascontrol.beans.administracion.grupo.BeanGrupo;
import mx.isban.cifrascontrol.beans.administracion.grupo.BeanGrupoRespuesta;

@Local
public interface DAOGrupo {

	public BeanGrupoRespuesta buscarTodosGrupos(ArchitechSessionBean sessionBean);
	public BeanGrupoRespuesta consultarGrupoPorId(ArchitechSessionBean sessionBean, String idGrupo);
	public BeanGrupoRespuesta consultarGrupoPorNombre(ArchitechSessionBean sessionBean, String nombreGrupo);
	public BeanGrupoRespuesta modificarGrupo(ArchitechSessionBean sessionBean,BeanGrupo grupo);
	public BeanGrupoRespuesta altaGrupo(ArchitechSessionBean sessionBean,BeanGrupo grupo);
	public BeanGrupoRespuesta borrarGrupo(ArchitechSessionBean sessionBean, String idGrupo);
	public BeanGrupoRespuesta obtenerGrupoPorUsuario(ArchitechSessionBean sessionBean, String idUsuario);
}
