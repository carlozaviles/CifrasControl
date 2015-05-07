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
import mx.isban.cifrascontrol.beans.administracion.grupo.BeanGrupo;
import mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuario;
import mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuarioRespuesta;

/**
 * Session Bean implementation class DAOUsuarioImpl
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class DAOUsuarioImpl extends Architech implements DAOUsuario {
       
	private static final long serialVersionUID = 1L;
	private static final String ID_CANAL = "ID_CANAL_DATABASE_JDBC";
	private static final String QUERY_ACTUALIZA_USUARIO = 
			"UPDATE USUARIO"
		+ " SET ESTATUS = ?"
		+ " WHERE ID_USUARIO = ?";
	
	private static final String QUERY_ELIMINA_RELACIONES_USUARIO_GRUPO = 
			"DELETE FROM REL_USUARIO_GRUPO WHERE FK_ID_USUARIO = ?";
	private static final String QUERY_CREA_RELACIONES_USUARIO_GRUPO =
			"INSERT INTO REL_USUARIO_GRUPO(ID_RELACION,FK_ID_USUARIO,FK_ID_GRUPO) VALUES (SQ_REL_USUARIO_GRUPO.NEXTVAL,?,?)";
	private static final String QUERY_ALTA_USUARIO = 
			"INSERT INTO USUARIO(ID_USUARIO,ESTATUS) VALUES (?,?)";
	
	/**
     * @see Architech#Architech()
     */
    public DAOUsuarioImpl() {
        super();
    }

	@Override
	public BeanUsuarioRespuesta altaUsuario(ArchitechSessionBean sessionBean,
			BeanUsuario usuario) {
		final BeanUsuarioRespuesta perfiles = new BeanUsuarioRespuesta();
		this.info("Se inicia el alta de un nuevo usuario");
		this.info(QUERY_ALTA_USUARIO);
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
			//El componente IsbanDataAccess no contiene el metodo beginTransaction
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				perfiles.setCodError("2001");
			}else{
				List<BeanGrupo> grupos = usuario.getGrupos();
				if(grupos.size()>=1){
					for (int i = 0;i < grupos.size();i++) {	
						BeanUsuarioRespuesta actualizaRelaciones = actualizaRelacionesUsuarioGrupo(sessionBean, usuario.getIdUsuario(), grupos.get(i).getIdGrupo());
						if(!"0".equals(actualizaRelaciones.getCodError())){
							perfiles.setCodError(actualizaRelaciones.getCodError());
							i = grupos.size()+1;
						}else{
							perfiles.setCodError("0");
						}
					}
				}
			
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			perfiles.setCodError("2001");
		}
		return perfiles;
	}

	@Override
	public BeanUsuarioRespuesta obtenerTodosUsuarios(
			ArchitechSessionBean sessionBean) {
		final String consulta = "SELECT ID_USUARIO,ESTATUS FROM USUARIO";
		final BeanUsuarioRespuesta usuariosRespuesta = new BeanUsuarioRespuesta();
		this.info("Se inicia la consulta de todos los usuarios...");
		this.info(consulta);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY);
		requestDTO.setQuery(consulta);
		requestDTO.setCodeOperation("COD15 Consulta-Usuarios");
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				usuariosRespuesta.setCodError("2001");
			}else{
				List<BeanUsuario> listaUsuarios = new ArrayList<BeanUsuario>();
				for(Map<String, Object> registro : responseDTO.getResultQuery()){
					BeanUsuario usuario = new BeanUsuario();
					usuario.setIdUsuario(String.valueOf(registro.get("ID_USUARIO")));
					String status = String.valueOf(registro.get("ESTATUS"));
					if("1".equals(status)){
						usuario.setEstatus(true);
					}
					listaUsuarios.add(usuario);
				}
				usuariosRespuesta.setUsuarios(listaUsuarios);
				usuariosRespuesta.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			usuariosRespuesta.setCodError("2001");
		}
		return usuariosRespuesta;
	}

	@Override
	public BeanUsuarioRespuesta obtenerUsuarioPorID(
			ArchitechSessionBean sessionBean, String idUsuario) {
		final String consulta = "SELECT ID_USUARIO,ESTATUS FROM USUARIO WHERE ID_USUARIO = ?";
		final BeanUsuarioRespuesta usuarios = new BeanUsuarioRespuesta();
		this.info("Se inicia la consulta del usuario con id:"+idUsuario);
		this.info(consulta);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY_PARAMS);
		requestDTO.setQuery(consulta);
		requestDTO.setCodeOperation("COD17 Consulta-Usuario por id");
		requestDTO.addParamToSql(idUsuario);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				usuarios.setCodError("2001");
			}else{
				List<BeanUsuario> listaUsuarios = new ArrayList<BeanUsuario>();
				for(Map<String, Object> registro : responseDTO.getResultQuery()){
					BeanUsuario usuario = new BeanUsuario();
					usuario.setIdUsuario(String.valueOf(registro.get("ID_USUARIO")));
					String status = String.valueOf(registro.get("ESTATUS"));
					if("1".equals(status)){
						usuario.setEstatus(true);
					}
					listaUsuarios.add(usuario);
				}
				usuarios.setUsuarios(listaUsuarios);
				usuarios.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			usuarios.setCodError("2001");
		}
		return usuarios;
	}

	@Override
	public BeanUsuarioRespuesta modificarUsuario(
			ArchitechSessionBean sessionBean, BeanUsuario usuario) {
		final BeanUsuarioRespuesta perfiles = new BeanUsuarioRespuesta();
		this.info("Se inicia la actualizacion del usuario");
		this.info(QUERY_ACTUALIZA_USUARIO);
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
			//El componente IsbanDataAccess no contiene el metodo beginTransaction
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				perfiles.setCodError("2001");
			}else{
				BeanUsuarioRespuesta eliminacionRelaciones = eliminarRelacionesUsuarioGrupo(sessionBean, usuario.getIdUsuario());
				if(!"0".equals(eliminacionRelaciones.getCodError())){
					perfiles.setCodError(eliminacionRelaciones.getCodError());
				}else{
					List<BeanGrupo> grupos = usuario.getGrupos();
					this.info("Tamaño de lista seleccionada:"+grupos.size());
					for (int i = 0;i < grupos.size();i++) {	
						BeanUsuarioRespuesta actualizaRelaciones = actualizaRelacionesUsuarioGrupo(sessionBean, usuario.getIdUsuario(),grupos.get(i).getIdGrupo());
						if(!"0".equals(actualizaRelaciones.getCodError())){
							perfiles.setCodError(actualizaRelaciones.getCodError());
							i = grupos.size()+1;
						}else{
							perfiles.setCodError("0");
						}
					}
				}
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			perfiles.setCodError("2001");
		}
		return perfiles;
	}

	private BeanUsuarioRespuesta actualizaRelacionesUsuarioGrupo(
			ArchitechSessionBean sessionBean, String idUsuario, String idGrupo) {
		final BeanUsuarioRespuesta perfiles = new BeanUsuarioRespuesta();
		this.info("Se inicia la creacion de las relaciones Usuario - Grupo");
		this.info(QUERY_CREA_RELACIONES_USUARIO_GRUPO);
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
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				perfiles.setCodError("2001");
			}else{
				perfiles.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			perfiles.setCodError("2001");
		}
		return perfiles;
	}

	private BeanUsuarioRespuesta eliminarRelacionesUsuarioGrupo(
			ArchitechSessionBean sessionBean, String idUsuario) {
		final BeanUsuarioRespuesta usuarios = new BeanUsuarioRespuesta();
		this.info("Se inicia la eliminacion de las relaciones Usuario - Grupo");
		this.info(QUERY_ELIMINA_RELACIONES_USUARIO_GRUPO);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_DELETE_PARAMS);
		requestDTO.setQuery(QUERY_ELIMINA_RELACIONES_USUARIO_GRUPO);
		requestDTO.setCodeOperation("COD20 Elimina Relaciones Usuario -Grupo");
		requestDTO.addParamToSql(idUsuario);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				usuarios.setCodError("2001");
			}else{
				usuarios.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			usuarios.setCodError("2001");
		}
		return usuarios;
	}


}
