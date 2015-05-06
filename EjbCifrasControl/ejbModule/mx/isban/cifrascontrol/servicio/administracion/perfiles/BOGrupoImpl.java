package mx.isban.cifrascontrol.servicio.administracion.perfiles;

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
import mx.isban.cifrascontrol.dao.administracion.pantalla.DAOPantalla;
import mx.isban.cifrascontrol.dao.administracion.perfiles.DAOGrupo;
import mx.isban.cifrascontrol.servicio.administracion.grupo.BOGrupo;

/**
 * Session Bean implementation class BOPerfilesImpl
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Remote(BOGrupo.class)
public class BOGrupoImpl extends Architech implements BOGrupo {
       
	private static final long serialVersionUID = 1L;
	
	private static final String CODIGO_RESULTADO_NULO = "4002";
	
	private static final String CODIGO_SIN_ERRORES = "0";


	@EJB
	private DAOGrupo daoGrupo;
	
	@EJB
	private DAOPantalla daoPantalla;
	
	/**
     * @see Architech#Architech()
     */
    public BOGrupoImpl() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<BeanGrupo> buscarTodosGrupos(ArchitechSessionBean sessionBean)throws BusinessException {
		this.info("Iniciando la busqueda de todos los grupos....");
		BeanGrupoRespuesta resultadoConsulta = daoGrupo.buscarTodosGrupos(sessionBean);
		verificarRespuesta(resultadoConsulta);
		List<BeanGrupo> grupos = resultadoConsulta.getGrupos();
		this.info("El numero de grupos administrativos encontrados:"+grupos.size());
		this.info("Finaliza la ejecucion del metodo de busqueda de todos los grupos administrativos");
		return grupos;
	}

	@Override
	public void agregarGrupo(BeanGrupo perfil,
			ArchitechSessionBean sessionBean) throws BusinessException {
		this.info("Iniciando el alta de un grupo....");
		BeanGrupoRespuesta resultadoConsulta = daoGrupo.altaGrupo(sessionBean, perfil);
		verificarRespuesta(resultadoConsulta);
		this.info("Finaliza la ejecucion del metodo de creacion de grupo");	
	}

	@Override
	public void modificarGrupo(BeanGrupo perfil,
			ArchitechSessionBean sessionBean) throws BusinessException {
		this.info("Iniciando la modificacion de un grupo....");
		BeanGrupoRespuesta resultadoConsulta = daoGrupo.modificarGrupo(sessionBean, perfil);
		verificarRespuesta(resultadoConsulta);
		this.info("Finaliza la ejecucion del metodo de actualizacion de grupo");	
	}

	@Override
	public BeanGrupo consultarGrupo(String idPerfil,
			ArchitechSessionBean sessionBean) throws BusinessException {
		this.info("Iniciando la busqueda de un grupo....");
		BeanGrupoRespuesta resultadoConsulta = daoGrupo.consultarGrupoPorId(sessionBean, idPerfil);
		verificarRespuesta(resultadoConsulta);
		List<BeanGrupo> grupos = resultadoConsulta.getGrupos();
		this.info("El numero de grupos administrativos encontrados:"+grupos.size());
		BeanGrupo grupo = new BeanGrupo();
		if(grupos.size() >= 1){
			grupo = grupos.get(0);
			List<BeanPantalla> pantallas = establecerPantallasSeleccionadas(sessionBean, idPerfil);
			grupo.setPantallas(pantallas);
		}
		this.info("Finaliza la ejecucion del metodo de busqueda de un grupo");
		return grupo;
	}
	
	@Override
	public void borrarGrupo(String idGrupo, ArchitechSessionBean sessionBean)throws BusinessException{
		this.info("Iniciando el borrado de un grupo....");
		BeanGrupoRespuesta resultadoConsulta = daoGrupo.borrarGrupo(sessionBean,idGrupo);
		verificarRespuesta(resultadoConsulta);
		this.info("Finaliza la ejecucion del metodo de borrado de grupo");
	}

	private List<BeanPantalla> establecerPantallasSeleccionadas(ArchitechSessionBean sessionBean,String idGrupo)throws BusinessException{
		BeanPantallaRespuesta pantallasSeleccionadasRespuesta = daoPantalla.obtenerPantallasPorGrupo(sessionBean, idGrupo);
		verificarRespuesta(pantallasSeleccionadasRespuesta);
		List<BeanPantalla> pantallasSeleccionadas = pantallasSeleccionadasRespuesta.getPantallas();
		BeanPantallaRespuesta todasPantallas = daoPantalla.obtenerTodasPantallas(sessionBean);
		verificarRespuesta(todasPantallas);
		List<BeanPantalla>pantallas = todasPantallas.getPantallas();
		for (BeanPantalla beanPantalla : pantallas) {
			for (BeanPantalla beanPantallaSeleccionada : pantallasSeleccionadas) {
				if(beanPantalla.getIdPantalla().equals(beanPantallaSeleccionada.getIdPantalla())){
					beanPantalla.setPantallaSeleccionada(true);
				}
			}
		}
		return pantallas;
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
