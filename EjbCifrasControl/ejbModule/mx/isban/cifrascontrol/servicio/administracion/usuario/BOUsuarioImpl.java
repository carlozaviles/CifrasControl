package mx.isban.cifrascontrol.servicio.administracion.usuario;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.agave.commons.interfaces.BeanResultBO;
import mx.isban.cifrascontrol.beans.administracion.grupo.BeanGrupo;
import mx.isban.cifrascontrol.beans.administracion.grupo.BeanGrupoRespuesta;
import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantalla;
import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantallaRespuesta;
import mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuario;
import mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuarioRespuesta;
import mx.isban.cifrascontrol.dao.administracion.pantalla.DAOPantalla;
import mx.isban.cifrascontrol.dao.administracion.perfiles.DAOGrupo;
import mx.isban.cifrascontrol.dao.administracion.usuario.DAOUsuario;

/**
 * Session Bean implementation class BOUsuarioImpl
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Remote(BOUsuario.class)
public class BOUsuarioImpl extends Architech implements BOUsuario {

	private static final long serialVersionUID = 1L;
	private static final String CODIGO_RESULTADO_NULO = "4002";
	private static final String CODIGO_SIN_ERRORES = "0";
	
	@EJB
	private DAOUsuario daoUsuario;
	
	@EJB
	private DAOGrupo daoGrupo;
	
	@EJB
	private DAOPantalla daoPantalla;

	/**
     * Default constructor. 
     */
    public BOUsuarioImpl() {
        super();
    }

	@Override
	public List<BeanUsuario> obtenerTodosUsuarios(
			ArchitechSessionBean sessionBean)throws BusinessException {
		this.info("Iniciando la busqueda de todos los usaurios....");
		BeanUsuarioRespuesta resultadoConsulta = daoUsuario.obtenerTodosUsuarios(sessionBean);
		verificarRespuesta(resultadoConsulta);
		List<BeanUsuario> usuarios = resultadoConsulta.getUsuarios();
		establecerGrupos(usuarios, sessionBean);
		this.info("El numero de grupos administrativos encontrados:"+usuarios.size());
		this.info("Finaliza la ejecucion del metodo de busqueda de todos los grupos administrativos");
		return usuarios;
	}
	
	private void establecerGrupos(List<BeanUsuario> usuarios, ArchitechSessionBean sessionBean)throws BusinessException{
		this.info("Iniciando la busqueda de todos los grupos por usuario....");
		for (BeanUsuario beanUsuario : usuarios) {
			BeanGrupoRespuesta respuesta = daoGrupo.obtenerGrupoPorUsuario(sessionBean, beanUsuario.getIdUsuario());
			verificarRespuesta(respuesta);
			List<BeanGrupo> grupo = respuesta.getGrupos();
			establecerPantallas(grupo,sessionBean);
			beanUsuario.setGrupos(grupo);
		}
	}

	private void establecerPantallas(List<BeanGrupo> grupo,
			ArchitechSessionBean sessionBean)throws BusinessException {
		this.info("iniciando la busqueda de todas las pantallas relacionadas a un grupo");
		for (BeanGrupo beanGrupo : grupo) {
			BeanPantallaRespuesta respuesta = daoPantalla.obtenerPantallasPorGrupo(sessionBean, beanGrupo.getIdGrupo());
			verificarRespuesta(respuesta);
			List<BeanPantalla> pantallas = respuesta.getPantallas();
			beanGrupo.setPantallas(pantallas);
		}
	}

	@Override
	public BeanUsuario obtenerUsuarioPorID(ArchitechSessionBean sessionBean,
			String idUsuario) throws BusinessException{
		this.info("Iniciando la busqueda de un usuario....");
		BeanUsuarioRespuesta resultadoConsulta = daoUsuario.obtenerUsuarioPorID(sessionBean, idUsuario);
		verificarRespuesta(resultadoConsulta);
		List<BeanUsuario> usuarios = resultadoConsulta.getUsuarios();
		this.info("El numero de usuarios encontrados:"+usuarios.size());
		BeanUsuario usuario = new BeanUsuario();
		if(usuarios.size() >= 1){
			usuario = usuarios.get(0);
			List<BeanGrupo> grupos = establecerGruposSeleccionados(sessionBean, idUsuario);
			usuario.setGrupos(grupos);
		}
		this.info("Finaliza la ejecucion del metodo de busqueda de un usuario");
		return usuario;
	}

	private List<BeanGrupo> establecerGruposSeleccionados(
			ArchitechSessionBean sessionBean, String idUsuario)throws BusinessException {
		BeanGrupoRespuesta gruposSeleccionadosRespuesta = daoGrupo.obtenerGrupoPorUsuario(sessionBean, idUsuario);
		verificarRespuesta(gruposSeleccionadosRespuesta);
		List<BeanGrupo> gruposSeleccionados = gruposSeleccionadosRespuesta.getGrupos();
		BeanGrupoRespuesta todosGrupos = daoGrupo.buscarTodosGrupos(sessionBean);
		verificarRespuesta(todosGrupos);
		List<BeanGrupo> grupos = todosGrupos.getGrupos();
		for (BeanGrupo beanGrupo : grupos) {
			for (BeanGrupo beanGrupoSeleccionado : gruposSeleccionados) {
				if(beanGrupo.getIdGrupo().equals(beanGrupoSeleccionado.getIdGrupo())){
					beanGrupo.setGrupoSeleccionado(true);
				}
			}
		}
		return grupos;
	}

	@Override
	public void modificarUsuario(ArchitechSessionBean sessionBean,
			BeanUsuario usuario) throws BusinessException{
		this.info("Iniciando la modificacion de un usuario....");
		BeanUsuarioRespuesta resultadoConsulta = daoUsuario.modificarUsuario(sessionBean, usuario);
		verificarRespuesta(resultadoConsulta);
		this.info("Finaliza la ejecucion del metodo de actualizacion de usuario");	
		
	}

	@Override
	public void altaUsuario(ArchitechSessionBean sessionBean,
			BeanUsuario usuario) throws BusinessException{
		this.info("Iniciando el alta de un usuario....");
		BeanUsuarioRespuesta resultadoConsulta = daoUsuario.altaUsuario(sessionBean, usuario);
		verificarRespuesta(resultadoConsulta);
		this.info("Finaliza la ejecucion del metodo de alta de usuario");	
	}

	private void verificarRespuesta(BeanResultBO resultadoConsulta)throws BusinessException{
		if(null == resultadoConsulta){
			this.error("Respuesta nula al consultar la base de datos");
			throw new BusinessException(CODIGO_RESULTADO_NULO);
		}
		if(!CODIGO_SIN_ERRORES.equals(resultadoConsulta.getCodError())){
			this.error("Se ha presentado un error al momento de realizar la consulta en la base de datos :"+resultadoConsulta.getCodError());
			throw new BusinessException(resultadoConsulta.getCodError());
		}
	}

	public DAOUsuario getDaoUsuario() {
		return daoUsuario;
	}

	public void setDaoUsuario(DAOUsuario daoUsuario) {
		this.daoUsuario = daoUsuario;
	}

	public DAOGrupo getDaoGrupo() {
		return daoGrupo;
	}

	public void setDaoGrupo(DAOGrupo daoGrupo) {
		this.daoGrupo = daoGrupo;
	}

	public DAOPantalla getDaoPantalla() {
		return daoPantalla;
	}

	public void setDaoPantalla(DAOPantalla daoPantalla) {
		this.daoPantalla = daoPantalla;
	}
	
	
	
}
