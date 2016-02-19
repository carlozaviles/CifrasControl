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
package mx.isban.cifrascontrol.servicio.administracion.usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
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
import mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuario;
import mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuarioRespuesta;
import mx.isban.cifrascontrol.beans.producto.BeanProducto;
import mx.isban.cifrascontrol.beans.producto.BeanProductoRespuesta;
import mx.isban.cifrascontrol.dao.administracion.pantalla.DAOPantalla;
import mx.isban.cifrascontrol.dao.administracion.perfiles.DAOGrupo;
import mx.isban.cifrascontrol.dao.administracion.usuario.DAOUsuario;
import mx.isban.cifrascontrol.dao.catalogos.DAOCatalogos;
import mx.isban.cifrascontrol.servicio.auditoria.BOPistasAuditoria;
import mx.isban.cifrascontrol.util.general.ConstantesModuloIntegrador;
import mx.isban.cifrascontrol.util.reproceso.CatalogoProductosReproceso;

/**
* Clase BOUsuarioImpl
* 
* <P>Clase de tipo SessionBean, el cual implementa la interface BOUsuario, 
* la cual se encarga de realizar la logica de negocio relacionada a la administracion de 
* usuarios.
* 
* @author Everis
* @version 1.0
* @see www.everis.com/mexico
* 
*/
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BOUsuarioImpl extends Architech implements BOUsuario {

	/**
	 * Numero de version de la clase serializada
	 */
	private static final long serialVersionUID = -1025972788766748015L;
	/**
	 * Constante que contiene el valor para el codigo de un resultado nulo, el cual es 4002
	 */
	private static final String CODIGO_RESULTADO_NULO = "4002";
	/**
	 * Constante para el valor del codigo sin errores
	 */
	private static final String CODIGO_SIN_ERRORES = "0";
	
	/**
	 * Un objeto de tipo DAOUsuario
	 */
	@EJB
	private DAOUsuario daoUsuario;
	
	/**
	 * Un objeto de tipo DAOGrupo
	 */
	@EJB
	private transient DAOGrupo daoGrupo;
	
	/**
	 * Un objeto de tipo DAOPantalla
	 */
	@EJB
	private transient DAOPantalla daoPantalla;
	/**
	 * Referencia hacia el objeto de la capa de datos DAOCatalogos
	 */
	@EJB
	private DAOCatalogos daoCatalogos;
	/**
	 * Referencia hacia el servicio BOPistasAuditoria.
	 */
	@EJB
	private transient BOPistasAuditoria boPistas;
	
	/**
     * Default constructor. 
     */
    public BOUsuarioImpl() {
        super();
    }

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.administracion.usuario.BOUsuario#obtenerTodosUsuarios(mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanUsuario> obtenerTodosUsuarios(ArchitechSessionBean sessionBean)
			throws BusinessException {
		this.info("Iniciando la busqueda de todos los usaurios....");
		final BeanUsuarioRespuesta resultadoConsulta = daoUsuario.obtenerTodosUsuarios(sessionBean);
		verificarRespuesta(resultadoConsulta);
		final List<BeanUsuario> usuarios = resultadoConsulta.getUsuarios();
		establecerGrupos(usuarios, sessionBean);
		this.info("El numero de usuarios encontrados:"+usuarios.size());
		this.info("Finaliza la ejecucion del metodo de busqueda de todos los grupos administrativos");
		return usuarios;
	}
	
	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.administracion.usuario.BOUsuario#obtenerUsuarioPorID(mx.isban.agave.commons.beans.ArchitechSessionBean, java.lang.String)
	 */
	@Override
	public BeanUsuario obtenerUsuarioPorID(ArchitechSessionBean sessionBean,
			String idUsuario) throws BusinessException{
		this.info("Iniciando la busqueda de un usuario....");
		final BeanUsuarioRespuesta resultadoConsulta = daoUsuario.obtenerUsuarioPorID(sessionBean, idUsuario);
		verificarRespuesta(resultadoConsulta);
		final List<BeanUsuario> usuarios = resultadoConsulta.getUsuarios();
		this.info("El numero de usuarios encontrados:"+usuarios.size());
		BeanUsuario usuario = new BeanUsuario();
		if(!usuarios.isEmpty()){
			usuario = usuarios.get(0);
			final List<BeanGrupo> grupos = establecerGruposSeleccionados(sessionBean, idUsuario);
			usuario.setGrupos(grupos);
		}
		this.info("Finaliza la ejecucion del metodo de busqueda de un usuario");
		return usuario;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.administracion.usuario.BOUsuario#modificarUsuario(mx.isban.agave.commons.beans.ArchitechSessionBean, mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuario)
	 */
	@Override
	public void modificarUsuario(ArchitechSessionBean sessionBean, BeanUsuario usuario) 
			throws BusinessException{
		this.info("Iniciando la modificacion de un usuario....");
		List<BeanProducto> productosList = new ArrayList<BeanProducto>();
		List<BeanProducto> productosIdList = usuario.getProductos();
		for (BeanProducto beanProducto : productosIdList) {
			BeanProducto producto = new BeanProducto();
			if("FACT".equalsIgnoreCase(beanProducto.getTipoProducto())){
				 producto = daoCatalogos.obtenerProductoFacturaPorId(beanProducto.getIdProducto());
			}else{
				BeanProductoRespuesta respuesta = daoCatalogos.obtenerProductoEDCPorId(beanProducto.getIdProducto());
				verificarRespuesta(respuesta);
				producto = respuesta.getProductos().get(0);
				producto.setProductoSeleccionado(beanProducto.isProductoSeleccionado());
				producto.setPermisoReproceso(beanProducto.isPermisoReproceso());
			}
			productosList.add(producto);
		}
		usuario.setProductos(productosList);
		final BeanUsuarioRespuesta resultadoConsulta = daoUsuario.modificarUsuario(sessionBean, usuario);
		verificarRespuesta(resultadoConsulta);
		final BeanPistaAuditoria pistaAuditoria = new BeanPistaAuditoria();
		pistaAuditoria.setCodigoOperacion(ConstantesModuloIntegrador.COD_PA_ASIGNACION_PRODUCTOS);
		pistaAuditoria.setEstatusOperacion(ConstantesModuloIntegrador.COD_PA_OPERACION_OK);
		pistaAuditoria.setClienteAfectado(ConstantesModuloIntegrador.COD_PA_NO_APLICA);
		boPistas.generaPistaAuditoria(pistaAuditoria, sessionBean);
		this.info("Finaliza la ejecucion del metodo de actualizacion de usuario");		
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.administracion.usuario.BOUsuario#altaUsuario(mx.isban.agave.commons.beans.ArchitechSessionBean, mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuario)
	 */
	@Override
	public void altaUsuario(ArchitechSessionBean sessionBean,
			BeanUsuario usuario) throws BusinessException{
		this.info("Iniciando el alta de un usuario....");
		List<BeanProducto> productosList = new ArrayList<BeanProducto>();
		List<BeanProducto> productosIdList = usuario.getProductos();
		for (BeanProducto beanProducto : productosIdList) {
			BeanProducto producto = new BeanProducto();
			if("FACT".equalsIgnoreCase(beanProducto.getTipoProducto())){
				 producto = daoCatalogos.obtenerProductoFacturaPorId(beanProducto.getIdProducto());
			}else{
				producto = CatalogoProductosReproceso.obtenerCatalogoProductosReprocesos().get(Integer.parseInt(beanProducto.getIdProducto())-1);
			}
			productosList.add(producto);
		}
		usuario.setProductos(productosList);
		final BeanUsuarioRespuesta resultadoConsulta = daoUsuario.altaUsuario(sessionBean, usuario);
		verificarRespuesta(resultadoConsulta);
		this.info("Finaliza la ejecucion del metodo de alta de usuario");	
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.administracion.usuario.BOUsuario#validaUsuario(mx.isban.agave.commons.beans.ArchitechSessionBean, java.lang.String, java.lang.String[])
	 */
	@Override
	public void validaUsuario(ArchitechSessionBean sessionBean, String usuario, String[] grupos) 
			throws BusinessException {
		this.info("El usuario a validar es: " + usuario);
		StringBuilder cadenaGrupos = new StringBuilder();
		for(String grupo : grupos){
			cadenaGrupos.append(grupo).append(" ");
		}
		this.info("Los grupos que tiene el usuario a validar son: " + cadenaGrupos.toString());
		//Se realiza una consulta para verificar si el usuario ya existe en la BD de la aplicacion.
		final BeanUsuarioRespuesta existeUsuario = daoUsuario.obtenerUsuarioPorID(sessionBean, usuario);		
		verificarRespuesta(existeUsuario);
		
		//Se valida que los grupos que vienen con el usuario ya esten dados de alta.
		//Si un grupo no esta en la BD, este se ignora.
		List<BeanGrupo> gruposValidos = new ArrayList<BeanGrupo>();
		this.info("Se valida que los grupos con los que viene el usuario ya esten dados de alta en la BD");
		for(String grupo : grupos){
			grupo=grupo.trim();
			BeanGrupoRespuesta respuestaValidacionGrupo = daoGrupo.consultarGrupoPorNombre(sessionBean, grupo);
			verificarRespuesta(respuestaValidacionGrupo);
			if(respuestaValidacionGrupo.getGrupos().size() > 0){
				this.info("Este grupo es valido: " + grupo);
				gruposValidos.addAll(respuestaValidacionGrupo.getGrupos());
			}else{
				this.info("Este grupo no se encuentra en BD, por lo que no es tomado en cuenta para el registro del usuario: " 
						+ grupo);
			}
		}
		
		if(existeUsuario.getUsuarios().size() == 0){
			this.info("El usuario no existe en la BD de la aplicacion.");
			final BeanUsuario nuevoUsuario = new BeanUsuario();
			nuevoUsuario.setIdUsuario(usuario);
			nuevoUsuario.setGrupos(gruposValidos.size() > 0 ? gruposValidos : null);
			nuevoUsuario.setEstatus(true);
			final BeanUsuarioRespuesta altaUsuarioResp = daoUsuario.altaUsuario(sessionBean, nuevoUsuario);
			verificarRespuesta(altaUsuarioResp);
			final BeanPistaAuditoria pistaAuditoria = new BeanPistaAuditoria();
			pistaAuditoria.setCodigoOperacion(ConstantesModuloIntegrador.COD_PA_REGISTRO_USUARIO);
			pistaAuditoria.setEstatusOperacion(ConstantesModuloIntegrador.COD_PA_OPERACION_OK);
			pistaAuditoria.setClienteAfectado(usuario);
			boPistas.generaPistaAuditoria(pistaAuditoria, sessionBean);
		}else{
			this.info("El usuario ya existe en la BD de la aplicacion.");
			//Se obtienen los grupos que actualmente tiene el usuario.
			BeanGrupoRespuesta gruposUsuario = daoGrupo.obtenerGrupoPorUsuario(sessionBean, usuario);
			verificarRespuesta(gruposUsuario);
			this.info("Se valida que el usuario siga contando con los mismos perfiles.");
			//En caso de que se detecte un cambio en los perfiles, estos se actualizaran.
			boolean cambioPerfiles = false;
			for(BeanGrupo grupoExistente : gruposUsuario.getGrupos()){
				if(gruposValidos.contains(grupoExistente)){
					this.info("Se valido que el usuario sigue contando con el perfil: " + grupoExistente.getNombreGrupo());
				}else{
					this.info("El usuario no cuenta mas con el perfil: " + grupoExistente.getNombreGrupo());
					cambioPerfiles = true;
				}
			}
			//Se valida si el usuario tiene asignados nuevos perfiles.
			this.info("Se valida si al usuario se le han asginado nuevos perfiles.");
			for(BeanGrupo grupoPeticion : gruposValidos){
				if(gruposUsuario.getGrupos().contains(grupoPeticion)){
					this.info("El usuario ya contaba con el perfil: " + grupoPeticion.getNombreGrupo());
				}else{
					this.info("Se ha detectado un nuevo perfil para el usuario: " + grupoPeticion.getNombreGrupo());
					cambioPerfiles = true;
				}
			}
			if(cambioPerfiles){
				this.info("Se actualizan los perfiles del usuario.");
				final BeanUsuario usuarioActualizado = new BeanUsuario();
				usuarioActualizado.setIdUsuario(usuario);
				usuarioActualizado.setGrupos(gruposValidos);
				usuarioActualizado.setProductos(new ArrayList<BeanProducto>());
				usuarioActualizado.setEstatus(true);
				BeanUsuarioRespuesta actualizacionUsuarioResp = daoUsuario.modificarUsuario(sessionBean, usuarioActualizado);
				verificarRespuesta(actualizacionUsuarioResp);
			}else{
				this.info("Los perfiles asignados al usuario no han sufrido cambios.");
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.administracion.usuario.BOUsuario#obtenerUsuariosOperativos(mx.isban.agave.commons.beans.ArchitechSessionBean, java.lang.String)
	 */
	@Override
	public List<BeanUsuario> obtenerUsuariosOperativos(ArchitechSessionBean sessionBean, String usuarioAdministrador)
			throws BusinessException {
		this.info("Se consultan los usuarios operativos relacionados con un perfil administrador.");
		final BeanGrupoRespuesta gruposUsuarioAdministrador = daoGrupo.obtenerGrupoPorUsuario(sessionBean, usuarioAdministrador);
		verificarRespuesta(gruposUsuarioAdministrador);
		final BeanUsuarioRespuesta totalUsuarios = daoUsuario.obtenerTodosUsuarios(sessionBean);
		verificarRespuesta(totalUsuarios);
		final Set<BeanUsuario> usuariosOperativos = new HashSet<BeanUsuario>();
		for(BeanGrupo grupoAdmn : gruposUsuarioAdministrador.getGrupos()){
			if("A".equals(grupoAdmn.getTipoGrupo())){
				this.info("Se obtienen los usuarios operativos del perfil administrativo: " + grupoAdmn.getNombreGrupo());
				for(BeanUsuario usuario : totalUsuarios.getUsuarios()){
					BeanGrupoRespuesta gruposUsuario = daoGrupo.obtenerGrupoPorUsuario(sessionBean, usuario.getIdUsuario());
					verificarRespuesta(gruposUsuario);
					usuario.setGrupos(gruposUsuario.getGrupos());
					if(validaPertenenciaUsuarioGrupo(usuario, grupoAdmn)){
						usuariosOperativos.add(usuario);
					}
				}
			}
		}
		this.info("El numero de usuarios operativos para este administrador es: " + usuariosOperativos.size());
		final List<BeanUsuario> listaUsuariosOperativos = new ArrayList<BeanUsuario>();
		listaUsuariosOperativos.addAll(usuariosOperativos);
		return listaUsuariosOperativos;
	}

	/**
	 * Metodo encargado de obtener un objeto de tipo DAOUsuario
	 * @return Un objeto de tipo DAOUsuario
	 */
	public DAOUsuario getDaoUsuario() {
		return daoUsuario;
	}

	/**
	 * Metodo encargado de establecer un objeto de tipo DAOUsuario
	 * @param daoUsuario El objeto de tipo DAOUsuario a establecer
	 */
	public void setDaoUsuario(DAOUsuario daoUsuario) {
		this.daoUsuario = daoUsuario;
	}

	/**
	 * Metodo encargado de obtener un objeto de tipo DAOGrupo
	 * @return Un objeto de tipo DAOGrupo
	 */
	public DAOGrupo getDaoGrupo() {
		return daoGrupo;
	}

	/**
	 * Metodo encargado de establecer un objeto de tipo DAOGrupo
	 * @param daoGrupo El objeto de tipo DAOGrupo a establecer
	 */
	public void setDaoGrupo(DAOGrupo daoGrupo) {
		this.daoGrupo = daoGrupo;
	}

	/**
	 * Metodo encargado de obtener un objeto de tipo DAOPantalla
	 * @return Un objeto de tipo DAOPantalla
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
	 * Metodo que establece los grupos seleccionados en relacion a un usuario
	 * @param sessionBean Un objeto de tipo ArchitechSessionBean
	 * @param idUsuario El id del usuario a buscar los grupos seleccionados
	 * @return El listado de grupos al que pertenece el usuario
	 * @throws BusinessException En caso de presentarse un error al consultar la base de datos
	 */
	private List<BeanGrupo> establecerGruposSeleccionados(
			ArchitechSessionBean sessionBean, String idUsuario)throws BusinessException {
		this.info("Inicia el establecimiento de grupos seleccionados");
		final BeanGrupoRespuesta gruposSeleccionadosRespuesta = daoGrupo.obtenerGrupoPorUsuario(sessionBean, idUsuario);
		verificarRespuesta(gruposSeleccionadosRespuesta);
		final List<BeanGrupo> gruposSeleccionados = gruposSeleccionadosRespuesta.getGrupos();
		final BeanGrupoRespuesta todosGrupos = daoGrupo.buscarTodosGrupos(sessionBean);
		verificarRespuesta(todosGrupos);
		final List<BeanGrupo> grupos = todosGrupos.getGrupos();
		for (BeanGrupo beanGrupo : grupos) {
			for (BeanGrupo beanGrupoSeleccionado : gruposSeleccionados) {
				if(beanGrupo.getIdGrupo().equals(beanGrupoSeleccionado.getIdGrupo())){
					beanGrupo.setGrupoSeleccionado(true);
				}
			}
		}
		this.info("Finaliza el establecmimiento de los grupos seleccionados");
		return grupos;
	}
	
	/**
	 * Metodo que establece los grupos de los usuarios
	 * @param usuarios Lista de usuarios a establecer los grupos
	 * @param sessionBean Un objeto de tipo ArchitechSessionBean
	 * @throws BusinessException En caso de presentarse un error al consultar la base de datos 
	 */
	private void establecerGrupos(List<BeanUsuario> usuarios, ArchitechSessionBean sessionBean)throws BusinessException{
		this.info("Iniciando la busqueda de todos los grupos por usuario....");
		for (BeanUsuario beanUsuario : usuarios) {
			final BeanGrupoRespuesta respuesta = daoGrupo.obtenerGrupoPorUsuario(sessionBean, beanUsuario.getIdUsuario());
			verificarRespuesta(respuesta);
			final List<BeanGrupo> grupo = respuesta.getGrupos();
			establecerPantallas(grupo,sessionBean);
			beanUsuario.setGrupos(grupo);
		}
		this.info("Finaliza el establecimiento de grupos");
	}

	/**
	 * Metodo que establece las pantallas en relacion a un listado de gruopos
	 * @param grupo La lista de grupos a establecer las pantallas
	 * @param sessionBean Un objeto de tipo ArchitechSessionBean
	 * @throws BusinessException En caso de presentarse un error al consultar la base de datos
	 */
	private void establecerPantallas(List<BeanGrupo> grupo,
			ArchitechSessionBean sessionBean)throws BusinessException {
		this.info("iniciando la busqueda de todas las pantallas relacionadas a un grupo");
		for (BeanGrupo beanGrupo : grupo) {
			final BeanPantallaRespuesta respuesta = daoPantalla.obtenerPantallasPorGrupo(sessionBean, beanGrupo.getIdGrupo());
			verificarRespuesta(respuesta);
			final List<BeanPantalla> pantallas = respuesta.getPantallas();
			beanGrupo.setPantallas(pantallas);
		}
		this.info("Finaliza el establecimiento de las pantallas");
	}
	
	/**
	 * Valida si un usuario pertenece a un grupo operativo que esta asociado con un perfil administrador en especifico.
	 * @param usuario Usuario a validar.
	 * @param grupoAdmn Perfil contra el cual se validara al usuario.
	 * @return boolean
	 */
	private boolean validaPertenenciaUsuarioGrupo(BeanUsuario usuario, BeanGrupo grupoAdmn){
		if(usuario.getGrupos() == null){
			return false;
		}
		for(BeanGrupo grupoUsuario : usuario.getGrupos()){
			if(grupoAdmn.getIdGrupo().equals(grupoUsuario.getGrupoAdministrador())){
				return true;
			}
		}
		return false;
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
