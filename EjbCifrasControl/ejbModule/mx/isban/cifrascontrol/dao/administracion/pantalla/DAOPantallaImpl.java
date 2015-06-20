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
package mx.isban.cifrascontrol.dao.administracion.pantalla;

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
import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantalla;
import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantallaRespuesta;

/**
* Clase DAOPantallaImpl
* 
 * Clase encargada de implementar la interface DAOPantalla.
 * Esta clase se encarga de todas las operaciones relacionadas a la tabla Pantalla
* 
* @author Everis
* @version 1.0
* @see www.everis.com/mexico
* 
*/
/**
 * @author everis
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class DAOPantallaImpl extends Architech implements DAOPantalla {
    
	/**
	 * Numero de version de la clase serializada
	 */
	private static final long serialVersionUID = 4644648627781758809L;
	/**
	 * Constante con el valor: ID_CANAL_DATABASE_JDBC
	 */
	private static final String ID_CANAL = "ID_CANAL_DATABASE_JDBC";
    /**
     * Constante con el valore de la consulta SQL que permite obtener las pantallas
     * en relacion a un grupo
     */
    private static final String CONSULTA_PANTALLAS_POR_GRUPO = 
    		"SELECT P.ID_PANT, P.TXT_NOM, P.DSC_DESC "
    		+ " FROM MOI_MX_MAE_ADMIN_PANT P"
    		+ " JOIN MOI_MX_REL_GPO_PANT REL"
    		+ " ON REL.ID_PANT_FK = P.ID_PANT"
    		+ " JOIN MOI_MX_MAE_ADMIN_GPO G"
    		+ " ON G.ID_GPO = REL.ID_GPO_FK"
    		+ " WHERE G.ID_GPO = ?";

    /**
     * Constante con el valor de la consulta SQL que permite obtener la pantalla por id 
     */
    private static final String QUERY_CONSULTA_PANTALLA_POR_ID = 
    		"SELECT ID_PANT,TXT_NOM,DSC_DESC FROM MOI_MX_MAE_ADMIN_PANT WHERE ID_PANT = ?";
    
    /**
     * Constante que contiene el valor de la consulta SQL que permite actualizar la tabla de pantalla
     */
    private static final String QUERY_UPDATE_PANTALLA = 
    		"UPDATE MOI_MX_MAE_ADMIN_PANT"
    	  + " SET TXT_NOM = ?,DSC_DESC = ?,NUM_MOD_FK = ?"
    	  + " WHERE ID_PANT = ?";
    
    /**
     * Constante que contiene el valor de la consulta SQL que permite actualizar la tabla de pantalla
     */
    private static final String QUERY_ALTA_PANTALLA = 
    		"INSERT INTO MOI_MX_MAE_ADMIN_PANT(ID_PANT,TXT_NOM,DSC_DESC,NUM_MOD_FK) VALUES (MOI_MX_SEQ_PANT.NEXTVAL,?,?,?)";
    
    /**
     * Constante que contiene una consulta SQL que permite eliminar un registro de la tabla pantalla
     */
    private static final String QUERY_ELIMINA_PANTALLA = 
			"DELETE FROM MOI_MX_MAE_ADMIN_PANT WHERE ID_PANT = ?";
    
    /**
     * Constante que contiene una consulta SQL que permite eliminar las relaciones presentadas entre las tablas Pantalla - Grupo
     */
    private static final String QUERY_ELIMINA_RELACIONES_PANTALLA_GRUPO = 
			"DELETE FROM MOI_MX_REL_GPO_PANT WHERE ID_PANT_FK = ?";
    
    /**
     * Constante que contiene una consulta SQL que permite obtener las pantallas que puede acceder un usuario
     */
    private static final String QUERY_OBTIENE_PANTALLA_USUARIO_MODULO = 
    		"SELECT P.ID_PANT,P.TXT_NOM "
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
    		+ " WHERE U.ID_USER = ?"
    		+ " AND M.ID_MODU = ?";
    
    
    /**
     *Constante con un mensaje indicando que se obtuvo un codigo de error al ejecutar
     *una consulta 
     */
    private static final String MENSAJE_ERROR = 
			"Se obtuvo un codigo de error al ejecutar una consulta :";
    
	/**
	 * Constructor de la clase DAOPantallaImpl
	 */
	public DAOPantallaImpl() {
        super();
    }

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.pantalla.DAOPantalla#obtenerTodasPantallas(mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public BeanPantallaRespuesta obtenerTodasPantallas(
			ArchitechSessionBean sessionBean) {
		this.info("Iniciando la consulta de todas las pantallas");
		final String consulta = "SELECT ID_PANT,TXT_NOM,DSC_DESC FROM MOI_MX_MAE_ADMIN_PANT";
		final BeanPantallaRespuesta respuesta = new BeanPantallaRespuesta();
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY);
		requestDTO.setQuery(consulta);
		requestDTO.setCodeOperation("COD07 Consulta-Pantallas");
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError());
				respuesta.setCodError(responseDTO.getCodeError());
				respuesta.setMsgError(responseDTO.getMessageError());
			}else{
				final List<BeanPantalla> listaPantallas = obtenerListadoPantallas(responseDTO);
				respuesta.setPantallas(listaPantallas);
				respuesta.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			this.error(ERROR_IDA+e);
			respuesta.setCodError(CODIGO_ERROR_GENERAL);
			respuesta.setMsgError(ERROR_IDA+e.getMessage());
		}
		this.info("Finaliza la ejecucion del metodo de consulta de todas las pantallas");
		return respuesta;
	}

	/**
	 * Metodo que itera en el resultado de la consulta y obtiene un listado 
	 * de objetos tipo BeanPantalla
	 * @param responseDTO La respuesta de la consulta en la base de datos
	 * @return Listado de objetos de tipo BeanPantalla
	 */
	private List<BeanPantalla> obtenerListadoPantallas(
			ResponseMessageDataBaseDTO responseDTO) {
		final List<BeanPantalla> listaPantallas = new ArrayList<BeanPantalla>();
		for(Map<String, Object> registro : responseDTO.getResultQuery()){
			final BeanPantalla pantalla = new BeanPantalla();
			pantalla.setIdPantalla(String.valueOf(registro.get("ID_PANT")));
			pantalla.setNombrePantalla(String.valueOf(registro.get("TXT_NOM")));
			pantalla.setDescripcionPantalla(String.valueOf(registro.get("DSC_DESC")));
			listaPantallas.add(pantalla);
		}
		return listaPantallas;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.pantalla.DAOPantalla#obtenerPantallasPorGrupo(mx.isban.agave.commons.beans.ArchitechSessionBean, java.lang.String)
	 */
	@Override
	public BeanPantallaRespuesta obtenerPantallasPorGrupo(
			ArchitechSessionBean sessionBean, String idGrupo)
			{
		this.info("Se inicia la consulta de las pantallas en relacion a un grupo con id:"+idGrupo);
		final BeanPantallaRespuesta respuesta = new BeanPantallaRespuesta();
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY_PARAMS);
		requestDTO.setQuery(CONSULTA_PANTALLAS_POR_GRUPO);
		requestDTO.setCodeOperation("COD08 Consulta-Pantallas por Grupo");
		requestDTO.addParamToSql(idGrupo);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError());
				respuesta.setCodError(responseDTO.getCodeError());
				respuesta.setMsgError(responseDTO.getMessageError());
			}else{
				final List<BeanPantalla> listaPantallas = obtenerListadoPantallas(responseDTO);
				respuesta.setPantallas(listaPantallas);
				respuesta.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			this.error(ERROR_IDA+e);
			respuesta.setCodError(CODIGO_ERROR_GENERAL);
			respuesta.setMsgError(e.getMessage());
		}
		this.info("Finaliza la ejecucion del metodo de consulta de pantallas por grupo");
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.pantalla.DAOPantalla#obtenerPantallaPorId(mx.isban.agave.commons.beans.ArchitechSessionBean, java.lang.String)
	 */
	@Override
	public BeanPantallaRespuesta obtenerPantallaPorId(
			ArchitechSessionBean sessionBean, String idPantalla)
			{
		this.info("Se inicia la consulta de las pantallas con id:"+idPantalla);
		final BeanPantallaRespuesta respuesta = new BeanPantallaRespuesta();
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY_PARAMS);
		requestDTO.setQuery(QUERY_CONSULTA_PANTALLA_POR_ID);
		requestDTO.setCodeOperation("COD010 Consulta-Pantallas por ID");
		requestDTO.addParamToSql(idPantalla);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError());
				respuesta.setCodError(responseDTO.getCodeError());
				respuesta.setMsgError(responseDTO.getMessageError());
			}else{
				final List<BeanPantalla> listaPantallas = obtenerListadoPantallas(responseDTO);
				respuesta.setPantallas(listaPantallas);
				respuesta.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			this.error(MENSAJE_ERROR+e);
			respuesta.setCodError(CODIGO_ERROR_GENERAL);
			respuesta.setMsgError(MENSAJE_ERROR+e.getMessage());
		}
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.pantalla.DAOPantalla#modificarPantalla(mx.isban.agave.commons.beans.ArchitechSessionBean, mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantalla)
	 */
	@Override
	public BeanPantallaRespuesta modificarPantalla(
			ArchitechSessionBean sessionBean, BeanPantalla pantalla) {
		this.info("Se inicia la actualizacion de la pantalla");
		final BeanPantallaRespuesta respuesta = new BeanPantallaRespuesta();
		final String idModulo = pantalla.getModulos().get(0).getIdModulo();
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_UPDATE_PARAMS);
		requestDTO.setQuery(QUERY_UPDATE_PANTALLA);
		requestDTO.setCodeOperation("COD10 Actualiza-Pantalla");
		requestDTO.addParamToSql(pantalla.getNombrePantalla());
		requestDTO.addParamToSql(pantalla.getDescripcionPantalla());
		requestDTO.addParamToSql(idModulo);
		requestDTO.addParamToSql(pantalla.getIdPantalla());
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
			this.error(MENSAJE_ERROR+e);
			respuesta.setCodError(CODIGO_ERROR_GENERAL);
			respuesta.setMsgError(MENSAJE_ERROR+e.getMessage());
		}
		this.info("Finaliza el metodo de actualizacion de pantalla");
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.pantalla.DAOPantalla#altaPantalla(mx.isban.agave.commons.beans.ArchitechSessionBean, mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantalla)
	 */
	@Override
	public BeanPantallaRespuesta altaPantalla(ArchitechSessionBean sessionBean,
			BeanPantalla pantalla) {
		this.info("Se inicia el alta de una pantalla");
		final BeanPantallaRespuesta respuesta = new BeanPantallaRespuesta();
		final String idModulo = pantalla.getModulos().get(0).getIdModulo();
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_INSERT_PARAMS);
		requestDTO.setQuery(QUERY_ALTA_PANTALLA);
		requestDTO.setCodeOperation("COD11 Alta-Pantalla");
		requestDTO.addParamToSql(pantalla.getNombrePantalla());
		requestDTO.addParamToSql(pantalla.getDescripcionPantalla());
		requestDTO.addParamToSql(idModulo);
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
			this.error(MENSAJE_ERROR+e);
			respuesta.setCodError(CODIGO_ERROR_GENERAL);
			respuesta.setMsgError(MENSAJE_ERROR+e.getMessage());
		}
		this.info("Finaliza la ejecucion del metodo de alta de pantalla");
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.pantalla.DAOPantalla#borrarPantalla(mx.isban.agave.commons.beans.ArchitechSessionBean, java.lang.String)
	 */
	@Override
	public BeanPantallaRespuesta borrarPantalla(
			ArchitechSessionBean sessionBean, String idPantalla) {
		this.info("Se inicia la eliminacion de la pantalla con el id:"+idPantalla);
		final BeanPantallaRespuesta eliminacionRelaciones = eliminarRelacionesPantalla(sessionBean, idPantalla);
		if(!CODIGO_SIN_ERRORES.equals(eliminacionRelaciones.getCodError())){
			this.error(MENSAJE_ERROR+eliminacionRelaciones.getCodError());
		}else{
			final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
			requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_DELETE_PARAMS);
			requestDTO.setQuery(QUERY_ELIMINA_PANTALLA);
			requestDTO.setCodeOperation("COD12 Elimina-Pantalla");
			requestDTO.addParamToSql(idPantalla);
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
				this.error(MENSAJE_ERROR+e);
				eliminacionRelaciones.setCodError(CODIGO_ERROR_GENERAL);
				eliminacionRelaciones.setMsgError(MENSAJE_ERROR+e.getMessage());
			}
		}
	return eliminacionRelaciones;
	}

	/**
	 * Metodo que elimina las relaciones Pantalla - Grupo
	 * @param sessionBean Un objeto de tipo ArchitechSessionBean
	 * @param idPantalla El id de la pantalla a eliminar las relaciones
	 * @return Un objeto de tipo BeanPantallaRespuesta
	 */
	private BeanPantallaRespuesta eliminarRelacionesPantalla(
			ArchitechSessionBean sessionBean, String idPantalla) {
		final BeanPantallaRespuesta respuesta = new BeanPantallaRespuesta();
		this.info("Se inicia la eliminacion de las relaciones Pantalla - Grupo");
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_DELETE_PARAMS);
		requestDTO.setQuery(QUERY_ELIMINA_RELACIONES_PANTALLA_GRUPO);
		requestDTO.setCodeOperation("COD13 Elimina Relaciones Grupo- Pantalla");
		requestDTO.addParamToSql(idPantalla);
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
			this.error(MENSAJE_ERROR+e);
			respuesta.setCodError(CODIGO_ERROR_GENERAL);
			respuesta.setMsgError(MENSAJE_ERROR+e.getMessage());
		}
		this.info("Finaliza la ejecucion del metodo de eliminacion de relaciones Pantalla-Grupo");
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.administracion.pantalla.DAOPantalla#obtenerPantallasPorUsuarioModulo(mx.isban.agave.commons.beans.ArchitechSessionBean, java.lang.String, java.lang.String)
	 */
	@Override
	public BeanPantallaRespuesta obtenerPantallasPorUsuarioModulo(
			ArchitechSessionBean sessionBean, String idModulo, String idUsuario) {
		this.info("Se inicia la consulta de las pantallas para el modulo con id:"+idModulo+" y el usuario:"+idUsuario);
		final BeanPantallaRespuesta respuesta = new BeanPantallaRespuesta();
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY_PARAMS);
		requestDTO.setQuery(QUERY_OBTIENE_PANTALLA_USUARIO_MODULO);
		requestDTO.setCodeOperation("COD010 Consulta-Pantallas por ID");
		requestDTO.addParamToSql(idUsuario);
		requestDTO.addParamToSql(idModulo);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(MENSAJE_ERROR+responseDTO.getCodeError());
				respuesta.setCodError(responseDTO.getCodeError());
				respuesta.setMsgError(responseDTO.getMessageError());
			}else{
				final List<BeanPantalla> listaPantallas = obtenerListadoPantallas(responseDTO);
				respuesta.setPantallas(listaPantallas);
				respuesta.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			this.error(MENSAJE_ERROR+e);
			respuesta.setCodError(CODIGO_ERROR_GENERAL);
			respuesta.setMsgError(MENSAJE_ERROR+e.getMessage());
		}
		this.info("Se termina la ejecuion del metodo de consulta pantallas por modulo y usuario");
		return respuesta;
	}

}
