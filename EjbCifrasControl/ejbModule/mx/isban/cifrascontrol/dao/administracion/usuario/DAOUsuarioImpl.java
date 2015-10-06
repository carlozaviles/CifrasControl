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
package mx.isban.cifrascontrol.dao.administracion.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.ExceptionDataAccess;
import mx.isban.agave.dataaccess.DataAccess;
import mx.isban.agave.dataaccess.channels.database.dto.RequestMessageDataBaseDTO;
import mx.isban.agave.dataaccess.channels.database.dto.ResponseMessageDataBaseDTO;
import mx.isban.agave.dataaccess.factories.jdbc.ConfigFactoryJDBC;
import mx.isban.agave.logging.Level;
import mx.isban.cifrascontrol.beans.administracion.grupo.BeanGrupo;
import mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuario;
import mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuarioRespuesta;
import mx.isban.cifrascontrol.beans.producto.BeanProducto;

/**
* Clase DAOUsuarioImpl
* 
* Clase encargada de implementar la interface DAOUsuario.
* Esta clase se encarga de todas las operaciones relacionadas a la tabla de usuarios
* 
* @author Everis
* @version 1.0
* @see www.everis.com/mexico
* 
*/
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class DAOUsuarioImpl extends Architech implements DAOUsuario {
    
	/**
	 * Numero de version de la clase serializada
	 */
	private static final long serialVersionUID = -6678344268852753892L;
	/**
     *Constante con un mensaje indicando que se obtuvo un codigo de error al ejecutar
     *una consulta 
     */
	private static final String MENSAJE_ERROR = 
			"Se obtuvo un codigo de error al ejecutar una consulta :";
	/**
	 * Constante con el valor: ID_CANAL_DATABASE_JDBC
	 */
	private static final String ID_CANAL = "ID_CANAL_DATABASE_JDBC";
	/**
	 * Constante que contiene una consulta SQL que permite la actualizacion del usuario
	 */
	private static final String QUERY_ACTUALIZA_USUARIO = 
			"UPDATE MOI_MX_MAE_ADMIN_USER"
		+ " SET FLG_STAT = ?"
		+ " WHERE ID_USER = ?";
	
	/**
	 * Constante que contiene una consulta SQL que permite la eliminacion de las relaciones 
	 * Usuario - Grupo
	 */
	private static final String QUERY_ELIMINA_RELACIONES_USUARIO_GRUPO = 
			"DELETE FROM MOI_MX_REL_USR_GPO WHERE ID_USER_FK = ?";
	
	private static final String QUERY_ELIMINA_RELACIONES_USUARIO_PRODUCTO =
			"DELETE FROM MOI_MX_REL_USR_PROD WHERE ID_USER_FK = ?";
	
	/**
	 * Constante que contiene una consulta SQL que permite la creacion de las relaciones Usuario - Grupo
	 */
	private static final String QUERY_CREA_RELACIONES_USUARIO_GRUPO =
			"INSERT INTO MOI_MX_REL_USR_GPO(ID_REL,ID_USER_FK,ID_GPO_FK) VALUES (MOI_MX_SEQ_USR_GPO.NEXTVAL,?,?)";
	
	/**
	 * Constante que contiene una consulta SQL que permite la creacion de relaciones Usuario - Producto
	 */
	private static final String QUERY_CREA_RELACIONES_USUARIO_PRODUCTO =
			"INSERT INTO MOI_MX_REL_USR_PROD(ID_REL,ID_PROD,ID_USER_FK,DSC_DESC,COD_TIPO_PROD) VALUES (MOI_MX_SEQ_USR_PROD.NEXTVAL,?,?,?,?)";
	
	/**
	 * Constante que contiene una consulta SQL que permite realizar el alta de un usuario
	 */
	private static final String QUERY_ALTA_USUARIO = 
			"INSERT INTO MOI_MX_MAE_ADMIN_USER(ID_USER,FLG_STAT) VALUES (?,?)";
	
	/**
     * @see Architech#Architech()
     */
    public DAOUsuarioImpl() {
        super();
    }

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.usuario.DAOUsuario#altaUsuario(mx.isban.agave.commons.beans.ArchitechSessionBean, mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuario)
	 */
	@Override
	public BeanUsuarioRespuesta altaUsuario(ArchitechSessionBean sessionBean,
			BeanUsuario usuario) {
		this.info("Se inicia el alta de un nuevo usuario");
		final BeanUsuarioRespuesta respuesta = new BeanUsuarioRespuesta();
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_INSERT_PARAMS);
		requestDTO.setQuery(QUERY_ALTA_USUARIO);
		requestDTO.setCodeOperation("COD21 Alta-Usuario");
		requestDTO.addParamToSql(usuario.getIdUsuario());
		Integer estatus = 0;
		if(usuario.isEstatus()){
			estatus = 1;
		}
		requestDTO.addParamToSql(estatus);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError()+responseDTO.getMessageError());
				respuesta.setCodError(responseDTO.getCodeError());
				respuesta.setMsgError(responseDTO.getMessageError());
			}else{
				final List<BeanGrupo> grupos = usuario.getGrupos();
				if(grupos != null && !grupos.isEmpty()){
					for (int i = 0;i < grupos.size();i++) {	
						final BeanUsuarioRespuesta actualizaRelaciones = actualizaRelacionesUsuarioGrupo(sessionBean, usuario.getIdUsuario(), grupos.get(i).getIdGrupo());
						if(!CODIGO_SIN_ERRORES.equals(actualizaRelaciones.getCodError())){
							respuesta.setCodError(actualizaRelaciones.getCodError());
							respuesta.setMsgError(actualizaRelaciones.getMsgError());
							i = grupos.size()+1;
						}else{
							respuesta.setCodError(CODIGO_SIN_ERRORES);
						}
					}
				}
				final List<BeanProducto> productos = usuario.getProductos();
				if(productos != null && !productos.isEmpty()){
					for (int i = 0;i < productos.size();i++) {
						final BeanUsuarioRespuesta actualizaRelacionesProducto = actualizaRelacionesUsuarioProducto(sessionBean, usuario.getIdUsuario(), productos.get(i));
						if(!CODIGO_SIN_ERRORES.equals(actualizaRelacionesProducto.getCodError())){
							respuesta.setCodError(actualizaRelacionesProducto.getCodError());
							respuesta.setMsgError(actualizaRelacionesProducto.getMsgError());
							i = productos.size()+1;
						}else{
							respuesta.setCodError(CODIGO_SIN_ERRORES);
						}
					}
				}
				
			}
		}catch(ExceptionDataAccess e){
			this.error(ERROR_IDA+e);
			respuesta.setCodError(CODIGO_ERROR_GENERAL);
			respuesta.setMsgError(ERROR_IDA+e.getMessage());
		}
		this.info("Finaliza la ejecucion del metodo de alta de usuarios");
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.usuario.DAOUsuario#obtenerTodosUsuarios(mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public BeanUsuarioRespuesta obtenerTodosUsuarios(
			ArchitechSessionBean sessionBean) {
		final String consulta = "SELECT ID_USER,FLG_STAT FROM MOI_MX_MAE_ADMIN_USER";
		final BeanUsuarioRespuesta usuariosRespuesta = new BeanUsuarioRespuesta();
		this.info("Se inicia la consulta de todos los usuarios...");
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY);
		requestDTO.setQuery(consulta);
		requestDTO.setCodeOperation("COD15 Consulta-Usuarios");
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError()+responseDTO.getMessageError());
				usuariosRespuesta.setCodError(responseDTO.getCodeError());
				usuariosRespuesta.setMsgError(responseDTO.getMessageError());
			}else{
				final List<BeanUsuario> listaUsuarios = new ArrayList<BeanUsuario>();
				for(Map<String, Object> registro : responseDTO.getResultQuery()){
					final BeanUsuario usuario = new BeanUsuario();
					usuario.setIdUsuario(String.valueOf(registro.get("ID_USER")));
					final String status = String.valueOf(registro.get("FLG_STAT"));
					if("1".equals(status)){
						usuario.setEstatus(true);
					}
					listaUsuarios.add(usuario);
				}
				usuariosRespuesta.setUsuarios(listaUsuarios);
				usuariosRespuesta.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			this.error(ERROR_IDA+e);
			usuariosRespuesta.setCodError(CODIGO_ERROR_GENERAL);
			usuariosRespuesta.setMsgError(ERROR_IDA+e.getMessage());
		}
		this.info("Finaliza la ejecucion del metodo de obtencion de todos los usuarios");
		return usuariosRespuesta;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.usuario.DAOUsuario#obtenerUsuarioPorID(mx.isban.agave.commons.beans.ArchitechSessionBean, java.lang.String)
	 */
	@Override
	public BeanUsuarioRespuesta obtenerUsuarioPorID(
			ArchitechSessionBean sessionBean, String idUsuario) {
		final String consulta = "SELECT ID_USER,FLG_STAT FROM MOI_MX_MAE_ADMIN_USER WHERE ID_USER = ?";
		final BeanUsuarioRespuesta usuarios = new BeanUsuarioRespuesta();
		this.info("Se inicia la consulta del usuario con id:"+idUsuario);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY_PARAMS);
		requestDTO.setQuery(consulta);
		requestDTO.setCodeOperation("COD17 Consulta-Usuario por id");
		requestDTO.addParamToSql(idUsuario);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError()+responseDTO.getMessageError());
				usuarios.setCodError(responseDTO.getCodeError());
				usuarios.setMsgError(responseDTO.getMessageError());
			}else{
				final List<BeanUsuario> listaUsuarios = new ArrayList<BeanUsuario>();
				for(Map<String, Object> registro : responseDTO.getResultQuery()){
					final BeanUsuario usuario = new BeanUsuario();
					usuario.setIdUsuario(String.valueOf(registro.get("ID_USER")));
					final String status = String.valueOf(registro.get("FLG_STAT"));
					if("1".equals(status)){
						usuario.setEstatus(true);
					}
					listaUsuarios.add(usuario);
				}
				usuarios.setUsuarios(listaUsuarios);
				usuarios.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			this.error(ERROR_IDA+e);
			usuarios.setCodError(CODIGO_ERROR_GENERAL);
			usuarios.setMsgError(ERROR_IDA+e.getMessage());
		}
		return usuarios;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.usuario.DAOUsuario#modificarUsuario(mx.isban.agave.commons.beans.ArchitechSessionBean, mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuario)
	 */
	@Override
	public BeanUsuarioRespuesta modificarUsuario(
			ArchitechSessionBean sessionBean, BeanUsuario usuario) {
		final BeanUsuarioRespuesta respuesta = new BeanUsuarioRespuesta();
		this.info("Se inicia la actualizacion del usuario");
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_UPDATE_PARAMS);
		requestDTO.setQuery(QUERY_ACTUALIZA_USUARIO);
		requestDTO.setCodeOperation("COD19 Actualiza-Usuario");
		Integer estatus = 0;
		if(usuario.isEstatus()){
			estatus = 1;
		}
		requestDTO.addParamToSql(estatus);
		requestDTO.addParamToSql(usuario.getIdUsuario());
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError()+responseDTO.getMessageError());
				respuesta.setCodError(responseDTO.getCodeError());
				respuesta.setMsgError(responseDTO.getCodeError());
			}else{
				BeanUsuarioRespuesta eliminacionRelaciones = eliminarRelacionesUsuarioGrupo(sessionBean, usuario.getIdUsuario());
				if(!CODIGO_SIN_ERRORES.equals(eliminacionRelaciones.getCodError())){
					this.error(MENSAJE_ERROR+eliminacionRelaciones.getCodError()+eliminacionRelaciones.getMsgError());
					respuesta.setCodError(eliminacionRelaciones.getCodError());
					respuesta.setMsgError(eliminacionRelaciones.getMsgError());
				}else{
					List<BeanGrupo> grupos = usuario.getGrupos();
					this.info("Tamaño de lista seleccionada:"+grupos.size());
					for (int i = 0;i < grupos.size();i++) {	
						BeanUsuarioRespuesta actualizaRelaciones = actualizaRelacionesUsuarioGrupo(sessionBean, usuario.getIdUsuario(),grupos.get(i).getIdGrupo());
						if(!CODIGO_SIN_ERRORES.equals(actualizaRelaciones.getCodError())){
							this.error(MENSAJE_ERROR+actualizaRelaciones.getCodError()+actualizaRelaciones.getMsgError());
							respuesta.setCodError(actualizaRelaciones.getCodError());
							respuesta.setMsgError(actualizaRelaciones.getMsgError());
							i = grupos.size()+1;
						}else{
							respuesta.setCodError(CODIGO_SIN_ERRORES);
						}
					}
					BeanUsuarioRespuesta eliminacionRelacionesProducto = eliminarRelacionesUsuarioProducto(sessionBean, usuario.getIdUsuario());
					if(!CODIGO_SIN_ERRORES.equals(eliminacionRelacionesProducto.getCodError())){
						this.error(MENSAJE_ERROR+eliminacionRelacionesProducto.getCodError()+eliminacionRelaciones.getMsgError());
						respuesta.setCodError(eliminacionRelacionesProducto.getCodError());
						respuesta.setMsgError(eliminacionRelacionesProducto.getMsgError());
					}else{
						List<BeanProducto> productos = usuario.getProductos();
						this.info("Tamaño de lista seleccionada:"+productos.size());
						for (int i = 0;i < productos.size();i++) {	
							BeanUsuarioRespuesta actualizaRelacionesProducto = actualizaRelacionesUsuarioProducto(sessionBean, usuario.getIdUsuario(),productos.get(i));
							if(!CODIGO_SIN_ERRORES.equals(actualizaRelacionesProducto.getCodError())){
								this.error(MENSAJE_ERROR+actualizaRelacionesProducto.getCodError()+actualizaRelacionesProducto.getMsgError());
								respuesta.setCodError(actualizaRelacionesProducto.getCodError());
								respuesta.setMsgError(actualizaRelacionesProducto.getMsgError());
								i = productos.size()+1;
							}else{
								respuesta.setCodError(CODIGO_SIN_ERRORES);
							}
						}
					}
				}
				
			}
		}catch(ExceptionDataAccess e){
			this.error(ERROR_IDA+e);
			respuesta.setCodError(CODIGO_ERROR_GENERAL);
			respuesta.setMsgError(ERROR_IDA+e.getMessage());
		}
		return respuesta;
	}

	/**
	 * Metodo encargado de actualizar las relaciones usuario - grupo
	 * @param sessionBean Un objeto de tipo ArchitechSessionBean
	 * @param idUsuario El id del usuario a crear las relaciones
	 * @param idGrupo El id del grupo a crear las relaciones
	 * @return Un objeto de tipo BeanUsuarioRespuesta
	 */
	private BeanUsuarioRespuesta actualizaRelacionesUsuarioGrupo(
			ArchitechSessionBean sessionBean, String idUsuario, String idGrupo) {
		final BeanUsuarioRespuesta respuesta = new BeanUsuarioRespuesta();
		this.info("Se inicia la creacion de las relaciones Usuario - Grupo");
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_INSERT_PARAMS);
		requestDTO.setQuery(QUERY_CREA_RELACIONES_USUARIO_GRUPO);
		requestDTO.setCodeOperation("COD21 Crea Relaciones Usuario - Grupo");
		requestDTO.addParamToSql(idUsuario);
		requestDTO.addParamToSql(idGrupo);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError()+responseDTO.getMessageError());
				respuesta.setCodError(responseDTO.getCodeError());
				respuesta.setMsgError(responseDTO.getMessageError());
			}else{
				respuesta.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			this.error(ERROR_IDA+e);
			respuesta.setCodError(CODIGO_ERROR_GENERAL);
			respuesta.setMsgError(ERROR_IDA+e.getMessage());
		}
		this.info("Finaliza el metodo de actualizacion de relaciones");
		return respuesta;
	}

	/**
	 * Metodo encargado de actualizar las relaciones usuario - producto
	 * @param sessionBean Un objeto de tipo ArchitechSessionBean
	 * @param idUsuario El id del usuario a crear las relaciones
	 * @param producto El producto a relacionar con el usuario
	 * @return Un objeto de tipo BeanUsuarioRespuesta
	 */
	private BeanUsuarioRespuesta actualizaRelacionesUsuarioProducto(
			ArchitechSessionBean sessionBean, String idUsuario, BeanProducto producto) {
		final BeanUsuarioRespuesta respuesta = new BeanUsuarioRespuesta();
		this.info("Se inicia la creacion de las relaciones Usuario - Producto");
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_INSERT_PARAMS);
		requestDTO.setQuery(QUERY_CREA_RELACIONES_USUARIO_PRODUCTO);
		requestDTO.setCodeOperation("COD25 Crea Relaciones Usuario - Producto");
		requestDTO.addParamToSql(producto.getIdProducto());
		requestDTO.addParamToSql(idUsuario);
		requestDTO.addParamToSql(producto.getDescripcion());
		requestDTO.addParamToSql(producto.getTipoProducto().trim());
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError()+responseDTO.getMessageError());
				respuesta.setCodError(responseDTO.getCodeError());
				respuesta.setMsgError(responseDTO.getMessageError());
			}else{
				respuesta.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			showException(e,Level.ERROR);
			respuesta.setCodError(CODIGO_ERROR_GENERAL);
			respuesta.setMsgError(ERROR_IDA+e.getMessage());
		}
		this.info("Finaliza el metodo de actualizacion de relaciones Usuario - Producto");
		return respuesta;
	}

	
	/**
	 * Metodo encargado de eliminar las relaciones UsuarioGrupo
	 * @param sessionBean Un objeto de tipo ArchitechSessionBean
	 * @param idUsuario El id del usuario a eliminar las relaciones Usuario - Grupo
	 * @return Un objeto de tipo BeanUsuarioRespuesta con el resultado de la consulta en la bd
	 */
	private BeanUsuarioRespuesta eliminarRelacionesUsuarioGrupo(
			ArchitechSessionBean sessionBean, String idUsuario) {
		final BeanUsuarioRespuesta usuarios = new BeanUsuarioRespuesta();
		this.info("Se inicia la eliminacion de las relaciones Usuario - Grupo");
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_DELETE_PARAMS);
		requestDTO.setQuery(QUERY_ELIMINA_RELACIONES_USUARIO_GRUPO);
		requestDTO.setCodeOperation("COD20 Elimina Relaciones Usuario -Grupo");
		requestDTO.addParamToSql(idUsuario);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError()+responseDTO.getMessageError());
				usuarios.setCodError(responseDTO.getCodeError());
				usuarios.setMsgError(responseDTO.getMessageError());
			}else{
				usuarios.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			this.error(ERROR_IDA+e);
			usuarios.setCodError(CODIGO_ERROR_GENERAL);
			usuarios.setMsgError(ERROR_IDA+e.getMessage());
		}
		return usuarios;
	}
	
	/**
	 * Metodo encargado de eliminar las relaciones Usuario - Producto
	 * @param sessionBean Un objeto de tipo ArchitechSessionBean
	 * @param idUsuario El id del usuario a eliminar las relaciones Usuario - Producto
	 * @return Un objeto de tipo BeanUsuarioRespuesta con el resultado de la consulta en la bd
	 */
	private BeanUsuarioRespuesta eliminarRelacionesUsuarioProducto(
			ArchitechSessionBean sessionBean, String idUsuario) {
		final BeanUsuarioRespuesta usuarios = new BeanUsuarioRespuesta();
		this.info("Se inicia la eliminacion de las relaciones Usuario - Grupo");
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_DELETE_PARAMS);
		requestDTO.setQuery(QUERY_ELIMINA_RELACIONES_USUARIO_PRODUCTO);
		requestDTO.setCodeOperation("COD27 Elimina Relaciones Usuario - Producto");
		requestDTO.addParamToSql(idUsuario);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError()+responseDTO.getMessageError());
				usuarios.setCodError(responseDTO.getCodeError());
				usuarios.setMsgError(responseDTO.getMessageError());
			}else{
				usuarios.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			this.error(ERROR_IDA+e);
			usuarios.setCodError(CODIGO_ERROR_GENERAL);
			usuarios.setMsgError(ERROR_IDA+e.getMessage());
		}
		return usuarios;
	}


}
