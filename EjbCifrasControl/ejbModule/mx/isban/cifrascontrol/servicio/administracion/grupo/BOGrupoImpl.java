/**************************************************************
* Queretaro, Qro Mayo 2015
*
* La redistribucion y el uso en formas fuente y binario, 
* son responsabilidad del propietario.
* 
* Este software fue elaborado en @Everis
* 
* Para mas informacion, consulte <www.everis.com/mexico>
***************************************************************/
package mx.isban.cifrascontrol.servicio.administracion.grupo;

import java.util.ArrayList;
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
import mx.isban.cifrascontrol.bean.auditoria.BeanPistaAuditoria;
import mx.isban.cifrascontrol.beans.administracion.grupo.BeanGrupo;
import mx.isban.cifrascontrol.beans.administracion.grupo.BeanGrupoRespuesta;
import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantalla;
import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantallaRespuesta;
import mx.isban.cifrascontrol.dao.administracion.pantalla.DAOPantalla;
import mx.isban.cifrascontrol.dao.administracion.perfiles.DAOGrupo;
import mx.isban.cifrascontrol.servicio.administracion.grupo.BOGrupo;
import mx.isban.cifrascontrol.servicio.auditoria.BOPistasAuditoria;
import mx.isban.cifrascontrol.util.general.ConstantesModuloIntegrador;

/**
* Clase BOGrupoImpl
* 
* <P>Clase de tipo SessionBean, el cual implementa la interface BOGrupo, 
* la cual se encarga de realizar la logica de negocio relacionada a los grupos.
*  
* @author Everis
* @version 1.0
* @see www.everis.com/mexico
* 
*/
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Remote(BOGrupo.class)
public class BOGrupoImpl extends Architech implements BOGrupo {
  
	/**
	 * Numero de version de la clase serializada
	 */
	private static final long serialVersionUID = -7436848327301155306L;
	/**
	 * Constante con el valor 4002, el cual indica que se genero un resultado
	 * nulo, al momento de realizar acciones relacionadas al Grupo
	 */
	private static final String CODIGO_RESULTADO_NULO = "4002";
	/**
	 * Constante con el valor 0, el cual indica que no se presento un error al 
	 * momento de realizar acciones relacionadas al Grupo
	 */
	private static final String CODIGO_SIN_ERRORES = "0";
	/**
	 * Objeto de tipo DAOGrupo, el cual se encarga de realizar las consultas a la
	 * base de datos, para obtener todo lo relacionado a un grupo 
	 */
	@EJB
	private DAOGrupo daoGrupo;
	/**
	 * Referencia hacia el servicio BOPistasAuditoria
	 */
	@EJB
	private BOPistasAuditoria boPistas;
	/**
	 * Objeto de tipo DAOPantalla, el cual se encarga de realizar las consultas a la
	 * base de datos, para obtener todo lo relacionado a las pantallas 
	 */
	@EJB
	private DAOPantalla daoPantalla;
	
	/**
     * @see Architech#Architech()
     */
    public BOGrupoImpl() {
        super();
    }

  
	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.administracion.grupo.BOGrupo#buscarTodosGrupos(mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanGrupo> buscarTodosGrupos(ArchitechSessionBean sessionBean)throws BusinessException {
		this.info("Iniciando la busqueda de todos los grupos....");
		final BeanGrupoRespuesta resultadoConsulta = daoGrupo.buscarTodosGrupos(sessionBean);
		verificarRespuesta(resultadoConsulta);
		final List<BeanGrupo> grupos = resultadoConsulta.getGrupos();
		this.info("El numero de grupos administrativos encontrados:"+grupos.size());
		this.info("Finaliza la ejecucion del metodo de busqueda de todos los grupos administrativos");
		return grupos;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.administracion.grupo.BOGrupo#agregarGrupo(mx.isban.cifrascontrol.beans.administracion.grupo.BeanGrupo, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public void agregarGrupo(BeanGrupo grupo,
			ArchitechSessionBean sessionBean) throws BusinessException {
		this.info("Iniciando el alta de un grupo....");
		final BeanGrupoRespuesta resultadoConsulta = daoGrupo.altaGrupo(sessionBean, grupo);
		verificarRespuesta(resultadoConsulta);
		this.info("Finaliza la ejecucion del metodo de creacion de grupo");	
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.administracion.grupo.BOGrupo#modificarGrupo(mx.isban.cifrascontrol.beans.administracion.grupo.BeanGrupo, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public void modificarGrupo(BeanGrupo grupo,
			ArchitechSessionBean sessionBean) throws BusinessException {
		this.info("Iniciando la modificacion de un grupo....");
		final BeanGrupoRespuesta resultadoConsulta = daoGrupo.modificarGrupo(sessionBean, grupo);
		verificarRespuesta(resultadoConsulta);
		final BeanPistaAuditoria pistaAuditoria = new BeanPistaAuditoria();
		pistaAuditoria.setCodigoOperacion(ConstantesModuloIntegrador.COD_PA_ASIGNACION_PANTALLAS);
		pistaAuditoria.setEstatusOperacion(ConstantesModuloIntegrador.COD_PA_OPERACION_OK);
		pistaAuditoria.setClienteAfectado(ConstantesModuloIntegrador.COD_PA_NO_APLICA);
		boPistas.generaPistaAuditoria(pistaAuditoria, sessionBean);
		this.info("Finaliza la ejecucion del metodo de actualizacion de grupo");	
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.administracion.grupo.BOGrupo#consultarGrupo(java.lang.String, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public BeanGrupo consultarGrupo(String idGrupo,
			ArchitechSessionBean sessionBean) throws BusinessException {
		this.info("Iniciando la busqueda de un grupo por el id:"+idGrupo);
		final BeanGrupoRespuesta resultadoConsulta = daoGrupo.consultarGrupoPorId(sessionBean, idGrupo);
		verificarRespuesta(resultadoConsulta);
		final List<BeanGrupo> gruposList = resultadoConsulta.getGrupos();
		this.info("El numero de grupos administrativos encontrados:"+gruposList.size());
		BeanGrupo grupo = new BeanGrupo();
		if(gruposList.size() >= 1){
			grupo = gruposList.get(0);
			final List<BeanPantalla> pantallas = establecerPantallasSeleccionadas(sessionBean, idGrupo);
			grupo.setPantallas(pantallas);
		}
		this.info("Finaliza la ejecucion del metodo de busqueda de un grupo");
		return grupo;
	}
	
	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.administracion.grupo.BOGrupo#borrarGrupo(java.lang.String, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public void borrarGrupo(String idGrupo, ArchitechSessionBean sessionBean)throws BusinessException{
		this.info("Iniciando el borrado de un grupo....");
		final BeanGrupoRespuesta resultadoConsulta = daoGrupo.borrarGrupo(sessionBean,idGrupo);
		verificarRespuesta(resultadoConsulta);
		this.info("Finaliza la ejecucion del metodo de borrado de grupo");
	}
	
	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.administracion.grupo.BOGrupo#obtenerGruposPorTipo(java.lang.String, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanGrupo> obtenerGruposPorTipo(String tipoGrupo, ArchitechSessionBean sessionBean) 
			throws BusinessException {
		this.info("Se realizara la busqueda de los perfiles de tipo: " + tipoGrupo);
		final BeanGrupoRespuesta consultaGrupos = daoGrupo.buscarTodosGrupos(sessionBean);
		verificarRespuesta(consultaGrupos);
		List<BeanGrupo> gruposCoincidentes = new ArrayList<BeanGrupo>();
		for(BeanGrupo grupo : consultaGrupos.getGrupos()){
			if(grupo.getTipoGrupo().equals(tipoGrupo)){
				gruposCoincidentes.add(grupo);
			}
		}
		this.info("El numero de grupos que coinciden con la busqueda es: " + gruposCoincidentes.size());
		return gruposCoincidentes;
	}

	
	/**Metodo encargado de obtener un objeto de tipo DAOGrupo
	 * @return Un objeto de tipo DAOGrupo
	 */
	public DAOGrupo getDaoGrupo() {
		return daoGrupo;
	}

	/**Metodo encargado de establecer un objeto de tipo DAOGrupo
	 * @param daoGrupo El objeto de tipo DAOGrupo a establecer
	 */
	public void setDaoGrupo(DAOGrupo daoGrupo) {
		this.daoGrupo = daoGrupo;
	}

	/**
	 * Metodo encargado de obtener un objeto de tipo DAOPantalla
	 * @return un objeto de tipo DAOPantalla
	 */
	public DAOPantalla getDaoPantalla() {
		return daoPantalla;
	}

	/**
	 * Metodo encargado de establecer un objeto de tipo DAOPantalla
	 * @param daoPantalla El objeto de tipo DAOPantalla a establecer
	 */
	public void setDaoPantalla(DAOPantalla daoPantalla) {
		this.daoPantalla = daoPantalla;
	}
	
	/**
	 * Metodo encargado de obtener y establecer las pantallas seleccionadas por un usuario
	 * para que se puedan visualizar en el formulario de modificacion de grupo
	 * @param sessionBean Objeto de tipo ArchitechSessionBean
	 * @param idGrupo String con el id del Grupo a buscar las pantallas que tiene asignado
	 * @return Una lista con las pantallas (Tanto las seleccionadas como las que no lo estan)
	 * @throws BusinessException En caso de presentarse un error
	 */
	private List<BeanPantalla> establecerPantallasSeleccionadas(ArchitechSessionBean sessionBean,String idGrupo)throws BusinessException{
		final BeanPantallaRespuesta pantallasSeleccionadasRespuesta = daoPantalla.obtenerPantallasPorGrupo(sessionBean, idGrupo);
		verificarRespuesta(pantallasSeleccionadasRespuesta);
		final List<BeanPantalla> pantallasSeleccionadas = pantallasSeleccionadasRespuesta.getPantallas();
		final BeanPantallaRespuesta todasPantallas = daoPantalla.obtenerTodasPantallas(sessionBean);
		verificarRespuesta(todasPantallas);
		final List<BeanPantalla>pantallas = todasPantallas.getPantallas();
		for (BeanPantalla beanPantalla : pantallas) {
			for (BeanPantalla beanPantallaSeleccionada : pantallasSeleccionadas) {
				if(beanPantalla.getIdPantalla().equals(beanPantallaSeleccionada.getIdPantalla())){
					beanPantalla.setPantallaSeleccionada(true);
				}
			}
		}
		return pantallas;
	}
	
	/**
	 * Metodo encargado de verificar las respuestas obtenidas del DAO
	 * @param resultadoConsulta Un objeto con los resultados de la consulta
	 * @throws BusinessException En caso de presentarse un error
	 */
	private void verificarRespuesta(BeanResultBO resultadoConsulta)throws BusinessException{
		if(null == resultadoConsulta){
			this.error("Respuesta nula al consultar la base de datos");
			throw new BusinessException(CODIGO_RESULTADO_NULO,"Respuesta nula al consultar la base de datos");
		}
		if(!CODIGO_SIN_ERRORES.equals(resultadoConsulta.getCodError())){
			this.error("Se ha presentado un error al momento de realizar la consulta en la base de datos :"+resultadoConsulta.getCodError());
			throw new BusinessException(resultadoConsulta.getCodError(),resultadoConsulta.getMsgError());
		}
	}
}
