package mx.isban.cifrascontrol.dao.administracion.modulo;

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
import mx.isban.cifrascontrol.beans.administracion.modulo.BeanModulo;
import mx.isban.cifrascontrol.beans.administracion.modulo.BeanModuloRespuesta;

/**
 * Session Bean implementation class DAOModulo
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class DAOModuloImpl extends Architech implements DAOModulo {

	private static final long serialVersionUID = 1L;
	
	private static final String MENSAJE_ERROR = 
			"Se obtuvo un codigo de error al ejecutar una consulta :";
	private static final String ID_CANAL = "ID_CANAL_DATABASE_JDBC";
	private static final String QUERY_MODULOS_POR_USUARIO = 
			"SELECT DISTINCT M.ID_MODULO,M.NOMBRE"
			+ " FROM MODULO M"
			+ " JOIN PANTALLA P"
			+ " ON M.ID_MODULO = P.FK_MODULO"
			+ " JOIN REL_GRUPO_PANTALLA REL_PAN"
			+ " ON P.ID_PANTALLA = REL_PAN.FK_ID_PANTALLA"
			+ " JOIN GRUPO G"
			+ " ON G.ID_GRUPO = REL_PAN.FK_ID_GRUPO"
			+ " JOIN REL_USUARIO_GRUPO REL_USU"
			+ " ON REL_USU.FK_ID_GRUPO = G.ID_GRUPO"
			+ " JOIN USUARIO U"
			+ " ON U.ID_USUARIO = REL_USU.FK_ID_USUARIO"
			+ " WHERE U.ID_USUARIO = ?";

	private static final String QUERY_OBTENER_MODULO_POR_PANTALLA = 
			"SELECT M.ID_MODULO,M.NOMBRE,M.DESCRIPCION"
			+ " FROM MODULO M"
			+ " JOIN PANTALLA P"
			+ " ON M.ID_MODULO = P.FK_MODULO"
			+ " WHERE P.ID_PANTALLA = ?";
	
	private static final String QUERY_CONSULTA_MODULO_POR_ID = 
			"SELECT ID_MODULO,NOMBRE,DESCRIPCION FROM MODULO WHERE ID_MODULO = ?";
	
	private static final String QUERY_UPDATE_MODULO = 
			"UPDATE MODULO SET NOMBRE = ?, DESCRIPCION = ? WHERE ID_MODULO = ?";
	
	private static final String QUERY_ELIMINA_MODULO =
			"DELETE FROM MODULO WHERE ID_MODULO = ?";
	
	private static final String QUERY_ELIMINA_RELACIONES_PANTALLA_MODULO =
			"DELETE FROM PANTALLA WHERE FK_MODULO = ?";
	
	private static final String QUERY_ALTA_MODULO = 
			"INSERT INTO MODULO (ID_MODULO,NOMBRE,DESCRIPCION) VALUES (SQ_MODULO.NEXTVAL,?,?)";
	
	/**
     * Default constructor. 
     */
    public DAOModuloImpl() {
       super();
    }

	@Override
	public BeanModuloRespuesta obtenerModulosPorUsuario(
			ArchitechSessionBean sessionBean, String idUsuario) {
		final BeanModuloRespuesta modulos = new BeanModuloRespuesta();
		this.info("Se inicia la consulta del perfil con el id de usaurio:"+idUsuario);
		this.info(idUsuario);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY_PARAMS);
		requestDTO.setQuery(QUERY_MODULOS_POR_USUARIO);
		requestDTO.setCodeOperation("COD22 Consulta- Modulos por usuario");
		requestDTO.addParamToSql(idUsuario);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				modulos.setCodError("2001");
				modulos.setMsgError(responseDTO.getMessageError());
			}else{
				List<BeanModulo> listaModulos = new ArrayList<BeanModulo>();
				for(Map<String, Object> registro : responseDTO.getResultQuery()){
					BeanModulo modulo = new BeanModulo();
					modulo.setIdModulo(String.valueOf(registro.get("ID_MODULO")));
					modulo.setNombreModulo(String.valueOf(registro.get("NOMBRE")));
					listaModulos.add(modulo);
				}
				modulos.setModulos(listaModulos);
				modulos.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			modulos.setCodError("2001");
		}
		return modulos;
	}

	@Override
	public BeanModuloRespuesta obtenerTodosModulos(
			ArchitechSessionBean sessionBean) {
		this.info("Se inicia la consulta de todos los modulos...");
		final String consulta = "SELECT ID_MODULO,NOMBRE,DESCRIPCION FROM MODULO";
		final BeanModuloRespuesta modulos = new BeanModuloRespuesta();
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY);
		requestDTO.setQuery(consulta);
		requestDTO.setCodeOperation("COD01 Consultar-Todos-Modulos");
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError());
				modulos.setCodError(responseDTO.getCodeError());
				modulos.setMsgError(responseDTO.getMessageError());
			}else{
				final List<BeanModulo> listaModulos = new ArrayList<BeanModulo>();
				for(Map<String, Object> registro : responseDTO.getResultQuery()){
					final BeanModulo modulo = new BeanModulo();
					modulo.setIdModulo(String.valueOf(registro.get("ID_MODULO")));
					modulo.setNombreModulo(String.valueOf(registro.get("NOMBRE")));
					modulo.setDescripcionModulo(String.valueOf(registro.get("DESCRIPCION")));
					listaModulos.add(modulo);
				}
				modulos.setModulos(listaModulos);
				modulos.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			this.error(ERROR_DATAACCESS+e);
			modulos.setCodError(CODIGO_ERROR_GENERAL);
			modulos.setMsgError(e.getMessage());
		}
		return modulos;
	}

	@Override
	public BeanModuloRespuesta obtenerModuloPorPantalla(
			ArchitechSessionBean sessionBean, String idPantalla) {
		final BeanModuloRespuesta modulos = new BeanModuloRespuesta();
		this.info("Se inicia la consulta del modulo con el id de pantalla:"+idPantalla);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY_PARAMS);
		requestDTO.setQuery(QUERY_OBTENER_MODULO_POR_PANTALLA);
		requestDTO.setCodeOperation("COD06 Consulta-Grupo por id usuario");
		requestDTO.addParamToSql(idPantalla);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError());
				modulos.setCodError(responseDTO.getCodeError());
				modulos.setMsgError(responseDTO.getMessageError());
			}else{
				final List<BeanModulo> listaModulos = new ArrayList<BeanModulo>();
				for(Map<String, Object> registro : responseDTO.getResultQuery()){
					final BeanModulo modulo = new BeanModulo();
					modulo.setIdModulo(String.valueOf(registro.get("ID_MODULO")));
					modulo.setNombreModulo(String.valueOf(registro.get("NOMBRE")));
					modulo.setDescripcionModulo(String.valueOf(registro.get("DESCRIPCION")));
					listaModulos.add(modulo);
				}
				modulos.setModulos(listaModulos);
				modulos.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			this.error(ERROR_DATAACCESS+e);
			modulos.setCodError(CODIGO_ERROR_GENERAL);
			modulos.setMsgError(e.getMessage());
		}
		return modulos;
	}

	@Override
	public BeanModuloRespuesta obtenerModuloPorId(
			ArchitechSessionBean sessionBean, String idModulo) {
		this.info("Se inicia la consulta del modulo con id:"+idModulo);
		final BeanModuloRespuesta modulos = new BeanModuloRespuesta();
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY_PARAMS);
		requestDTO.setQuery(QUERY_CONSULTA_MODULO_POR_ID);
		requestDTO.setCodeOperation("COD010 Consulta-Pantallas por ID");
		requestDTO.addParamToSql(idModulo);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				modulos.setCodError("2001");
				modulos.setMsgError(responseDTO.getMessageError());
			}else{
				List<BeanModulo> listaModulos = new ArrayList<BeanModulo>();
				for(Map<String, Object> registro : responseDTO.getResultQuery()){
					BeanModulo modulo = new BeanModulo();
					modulo.setIdModulo(String.valueOf(registro.get("ID_MODULO")));
					modulo.setNombreModulo(String.valueOf(registro.get("NOMBRE")));
					modulo.setDescripcionModulo(String.valueOf(registro.get("DESCRIPCION")));
					listaModulos.add(modulo);
				}
				modulos.setModulos(listaModulos);
				modulos.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			modulos.setCodError("2001");
			modulos.setMsgError(e.getMessage());
		}
		return modulos;
	}

	@Override
	public BeanModuloRespuesta modificarModulo(
			ArchitechSessionBean sessionBean, BeanModulo modulo) {
		final BeanModuloRespuesta respuesta = new BeanModuloRespuesta();
		this.info("Se inicia la actualizacion del modulo");
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_UPDATE_PARAMS);
		requestDTO.setQuery(QUERY_UPDATE_MODULO);
		requestDTO.setCodeOperation("COD10 Actualiza-Modulo");
		requestDTO.addParamToSql(modulo.getNombreModulo());
		requestDTO.addParamToSql(modulo.getDescripcionModulo());
		requestDTO.addParamToSql(modulo.getIdModulo());
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			//El componente IsbanDataAccess no contiene el metodo beginTransaction
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				respuesta.setCodError("2001");
				respuesta.setMsgError(responseDTO.getMessageError());
			}else{
				respuesta.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			respuesta.setCodError("2001");
			respuesta.setMsgError(e.getMessage());
		}
		return respuesta;
	}

	@Override
	public BeanModuloRespuesta guardarModulo(ArchitechSessionBean sessionBean,
			BeanModulo modulo) {
		final BeanModuloRespuesta respuesta = new BeanModuloRespuesta();
		this.info("Se inicia el alta de un modulo");
		this.info(QUERY_ALTA_MODULO);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_INSERT_PARAMS);
		requestDTO.setQuery(QUERY_ALTA_MODULO);
		requestDTO.setCodeOperation("COD11 Alta-Pantalla");
		requestDTO.addParamToSql(modulo.getNombreModulo());
		requestDTO.addParamToSql(modulo.getDescripcionModulo());
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			//El componente IsbanDataAccess no contiene el metodo beginTransaction
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				respuesta.setCodError("2001");
				respuesta.setMsgError(responseDTO.getMessageError());
			}else{
				respuesta.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			respuesta.setCodError("2001");
			respuesta.setMsgError(e.getMessage());
		}
		return respuesta;
	}

	@Override
	public BeanModuloRespuesta borrarModulo(ArchitechSessionBean sessionBean,
			String idModulo) {
		this.info("Se inicia la eliminacion del modulo");
		final BeanModuloRespuesta eliminacionRelaciones = eliminarRelacionesPantallaModulo(sessionBean, idModulo);
		if(!CODIGO_SIN_ERRORES.equals(eliminacionRelaciones.getCodError())){
			this.error(MENSAJE_ERROR+eliminacionRelaciones.getCodError());
		}else{
			final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
			requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_DELETE_PARAMS);
			requestDTO.setQuery(QUERY_ELIMINA_MODULO);
			requestDTO.setCodeOperation("COD05 Eliminar-Modulo");
			requestDTO.addParamToSql(idModulo);
			try{
				final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
				final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
				if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
					this.error(MENSAJE_ERROR+responseDTO.getCodeError());
					eliminacionRelaciones.setCodError(responseDTO.getCodeError());
					eliminacionRelaciones.setMsgError(responseDTO.getMessageError());
				}else{
					eliminacionRelaciones.setCodError(CODIGO_SIN_ERRORES);
				}
			}catch(ExceptionDataAccess e){
				this.error(ERROR_DATAACCESS+e);
				eliminacionRelaciones.setCodError(CODIGO_ERROR_GENERAL);
				eliminacionRelaciones.setMsgError(e.getMessage());
			}
		}
	return eliminacionRelaciones;
	}

	private BeanModuloRespuesta eliminarRelacionesPantallaModulo(
			ArchitechSessionBean sessionBean, String idModulo){
		final BeanModuloRespuesta modulos = new BeanModuloRespuesta();
		this.info("Se inicia la eliminacion de las relaciones Pantalla - Grupo");
		this.info(QUERY_ELIMINA_RELACIONES_PANTALLA_MODULO);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_DELETE_PARAMS);
		requestDTO.setQuery(QUERY_ELIMINA_RELACIONES_PANTALLA_MODULO);
		requestDTO.setCodeOperation("COD04 Elimina Relaciones Modulo - Pantalla");
		requestDTO.addParamToSql(idModulo);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError());
				modulos.setCodError(responseDTO.getCodeError());
				modulos.setMsgError(responseDTO.getMessageError());
			}else{
				modulos.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			this.error(ERROR_DATAACCESS+e);
			modulos.setCodError(CODIGO_ERROR_GENERAL);
			modulos.setMsgError(e.getMessage());
		}
		return modulos;
	}

}
