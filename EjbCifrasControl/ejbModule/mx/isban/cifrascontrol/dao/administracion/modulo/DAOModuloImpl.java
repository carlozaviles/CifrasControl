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
* Clase DAOModuloImpl
* 
* Clase encargada de implementar la interface DAOModulo.
* Esta clase se encarga de todas las operaciones relacionadas a la tabla Modulo
* 
* @author Everis
* @version 1.0
* @see www.everis.com/mexico
* 
*/
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class DAOModuloImpl extends Architech implements DAOModulo {

	/**
	 * Numero de version de la clase serializada
	 */
	private static final long serialVersionUID = -8765543418865372499L;
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
	 * Constante con la consulta SQL que indica los modulos encontrados por usuario
	 */
	private static final String QUERY_MODULOS_POR_USUARIO = 
			"SELECT DISTINCT M.ID_MODU ,M.TXT_NOM,M.DSC_DESC"
			+ " FROM MOI_MX_MAE_ADMIN_MODU M"
			+ " JOIN MOI_MX_MAE_ADMIN_PANT P"
			+ " ON M.ID_MODU = P.NUM_MOD_FK"
			+ " JOIN MOI_MX_REL_GPO_PANT REL_PAN"
			+ " ON P.ID_PANT = REL_PAN.ID_PANT_FK"
			+ " JOIN MOI_MX_MAE_ADMIN_GPO G"
			+ " ON G.ID_GPO = REL_PAN.ID_GPO_FK"
			+ " JOIN MOI_MX_REL_USR_GPO REL_USU"
			+ " ON REL_USU.ID_GPO_FK = G.ID_GPO"
			+ " JOIN MOI_MX_MAE_ADMIN_USER U"
			+ " ON U.ID_USER = REL_USU.ID_USER_FK"
			+ " WHERE U.FLG_STAT = 1"
			+ " AND U.ID_USER = ?";

	/**
	 * Constante que contiene la consulta SQL para obtener un modulo en relacion a un
	 * id de pantalla
	 */
	private static final String QUERY_OBTENER_MODULO_POR_PANTALLA = 
			"SELECT M.ID_MODU,M.TXT_NOM,M.DSC_DESC"
			+ " FROM MOI_MX_MAE_ADMIN_MODU M"
			+ " JOIN MOI_MX_MAE_ADMIN_PANT P"
			+ " ON M.ID_MODU = P.NUM_MOD_FK"
			+ " WHERE P.ID_PANT = ?";
	
	/**
	 * Constante que contiene una consulta SQL para obtener un modulo por id
	 */
	private static final String QUERY_CONSULTA_MODULO_POR_ID = 
			"SELECT ID_MODU,TXT_NOM,DSC_DESC FROM MOI_MX_MAE_ADMIN_MODU WHERE ID_MODU= ?";
	
	/**
	 * Constante que contiene una consulta SQL para actualizar un modulo
	 */
	private static final String QUERY_UPDATE_MODULO = 
			"UPDATE MOI_MX_MAE_ADMIN_MODU SET TXT_NOM = ?, DSC_DESC = ? WHERE ID_MODU = ?";
	
	/**
	 * Constante que contiene una consulta SQL para eliminar un modulo en relacion a su id
	 */
	private static final String QUERY_ELIMINA_MODULO =
			"DELETE FROM MOI_MX_MAE_ADMIN_MODU WHERE ID_MODU = ?";
	
	/**
	 * Constante que contiene una consulta SQL para eliminar las relaciones Pantalla - Modulo
	 */
	private static final String QUERY_ELIMINA_RELACIONES_PANTALLA_MODULO =
			"UPDATE MOI_MX_MAE_ADMIN_PANT SET NUM_MOD_FK = ? WHERE NUM_MOD_FK = ?";
	
	
	/**
	 * Constante que contiene una consulta para dar de alta un modulo 
	 */
	private static final String QUERY_ALTA_MODULO = 
			"INSERT INTO MOI_MX_MAE_ADMIN_MODU (ID_MODU,TXT_NOM,DSC_DESC) VALUES (MOI_MX_SEQ_MOD.NEXTVAL,?,?)";
	
	/**
     * Default constructor. 
     */
    public DAOModuloImpl() {
       super();
    }

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.modulo.DAOModulo#obtenerModulosPorUsuario(mx.isban.agave.commons.beans.ArchitechSessionBean, java.lang.String)
	 */
	@Override
	public BeanModuloRespuesta obtenerModulosPorUsuario(
			ArchitechSessionBean sessionBean, String idUsuario) {
		this.info("Se inicia la consulta del perfil con el id de usaurio:"+idUsuario);
		final BeanModuloRespuesta modulos = new BeanModuloRespuesta();
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY_PARAMS);
		requestDTO.setQuery(QUERY_MODULOS_POR_USUARIO);
		requestDTO.setCodeOperation("COD22 Consulta- Modulos por usuario");
		requestDTO.addParamToSql(idUsuario);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError());
				modulos.setCodError(responseDTO.getCodeError());
				modulos.setMsgError(responseDTO.getMessageError());
			}else{
				final List<BeanModulo> listaModulos = obtenerListadoModulos(responseDTO);
				modulos.setModulos(listaModulos);
				modulos.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			this.error(ERROR_IDA+e);
			modulos.setCodError(CODIGO_ERROR_GENERAL);
			modulos.setMsgError(ERROR_IDA+e.getMessage());
		}
		return modulos;
	}

	/**
	 * Metodo que itera en el resultado de la consulta y obtiene un listado 
	 * de objetos tipo BeanModulo
	 * @param responseDTO La respuesta de la consulta en la base de datos
	 * @return Listado de objetos de tipo BeanModulo
	 */
	private List<BeanModulo> obtenerListadoModulos(
			ResponseMessageDataBaseDTO responseDTO) {
		final List<BeanModulo> listaModulos = new ArrayList<BeanModulo>();
		for(Map<String, Object> registro : responseDTO.getResultQuery()){
			final BeanModulo modulo = new BeanModulo();
			modulo.setIdModulo(String.valueOf(registro.get("ID_MODU")));
			modulo.setNombreModulo(String.valueOf(registro.get("TXT_NOM")));
			modulo.setDescripcionModulo(String.valueOf(registro.get("DSC_DESC")));
			listaModulos.add(modulo);
		}
		return listaModulos;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.modulo.DAOModulo#obtenerTodosModulos(mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public BeanModuloRespuesta obtenerTodosModulos(
			ArchitechSessionBean sessionBean) {
		this.info("Se inicia la consulta de todos los modulos...");
		final String consulta = "SELECT ID_MODU,TXT_NOM,DSC_DESC FROM MOI_MX_MAE_ADMIN_MODU";
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
				final List<BeanModulo> listaModulos = obtenerListadoModulos(responseDTO);
				modulos.setModulos(listaModulos);
				modulos.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			this.error(ERROR_IDA+e);
			modulos.setCodError(CODIGO_ERROR_GENERAL);
			modulos.setMsgError(ERROR_IDA+e.getMessage());
		}
		return modulos;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.modulo.DAOModulo#obtenerModuloPorPantalla(mx.isban.agave.commons.beans.ArchitechSessionBean, java.lang.String)
	 */
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
				final List<BeanModulo> listaModulos = obtenerListadoModulos(responseDTO);
				modulos.setModulos(listaModulos);
				modulos.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			this.error(ERROR_IDA+e);
			modulos.setCodError(CODIGO_ERROR_GENERAL);
			modulos.setMsgError(e.getMessage());
		}
		return modulos;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.modulo.DAOModulo#obtenerModuloPorId(mx.isban.agave.commons.beans.ArchitechSessionBean, java.lang.String)
	 */
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
				this.error(MENSAJE_ERROR+responseDTO.getCodeError());
				modulos.setCodError(responseDTO.getCodeError());
				modulos.setMsgError(responseDTO.getMessageError());
			}else{
				final List<BeanModulo> listaModulos = obtenerListadoModulos(responseDTO);
				modulos.setModulos(listaModulos);
				modulos.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			this.error(ERROR_IDA+e);
			modulos.setCodError(CODIGO_ERROR_GENERAL);
			modulos.setMsgError(ERROR_IDA+e.getMessage());
		}
		return modulos;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.modulo.DAOModulo#modificarModulo(mx.isban.agave.commons.beans.ArchitechSessionBean, mx.isban.cifrascontrol.beans.administracion.modulo.BeanModulo)
	 */
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
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError());
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
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.modulo.DAOModulo#guardarModulo(mx.isban.agave.commons.beans.ArchitechSessionBean, mx.isban.cifrascontrol.beans.administracion.modulo.BeanModulo)
	 */
	@Override
	public BeanModuloRespuesta guardarModulo(ArchitechSessionBean sessionBean,
			BeanModulo modulo) {
		final BeanModuloRespuesta respuesta = new BeanModuloRespuesta();
		this.info("Se inicia el alta de un modulo");
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_INSERT_PARAMS);
		requestDTO.setQuery(QUERY_ALTA_MODULO);
		requestDTO.setCodeOperation("COD11 Alta-Pantalla");
		requestDTO.addParamToSql(modulo.getNombreModulo());
		requestDTO.addParamToSql(modulo.getDescripcionModulo());
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError());
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
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.modulo.DAOModulo#borrarModulo(mx.isban.agave.commons.beans.ArchitechSessionBean, java.lang.String)
	 */
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
				this.error(ERROR_IDA+e);
				eliminacionRelaciones.setCodError(CODIGO_ERROR_GENERAL);
				eliminacionRelaciones.setMsgError(e.getMessage());
			}
		}
	return eliminacionRelaciones;
	}

	/**
	 * Metodo que elimina las relaciones Pantalla - Modulo
	 * @param sessionBean Un objeto de tipo ArchitechSessionBean
	 * @param idModulo El id del modulo para eliminar las relaciones con la pantalla
	 * @return Un objeto de tipo BeanModuloRespuesta, con el resultado de la consulta en base de datos
	 */
	private BeanModuloRespuesta eliminarRelacionesPantallaModulo(
			ArchitechSessionBean sessionBean, String idModulo){
		final BeanModuloRespuesta modulos = new BeanModuloRespuesta();
		this.info("Se inicia la eliminacion de las relaciones Pantalla - Grupo");
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_UPDATE_PARAMS);
		requestDTO.setQuery(QUERY_ELIMINA_RELACIONES_PANTALLA_MODULO);
		requestDTO.setCodeOperation("COD04 Elimina Relaciones Modulo - Pantalla");
		requestDTO.addParamToSql("0");
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
			this.error(ERROR_IDA+e);
			modulos.setCodError(CODIGO_ERROR_GENERAL);
			modulos.setMsgError(e.getMessage());
		}
		return modulos;
	}

}
