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
 * Session Bean implementation class DAOPantallaImpl
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class DAOPantallaImpl extends Architech implements DAOPantalla {
       
    private static final long serialVersionUID = 1L;
    private static final String ID_CANAL = "ID_CANAL_DATABASE_JDBC";
    private static final String CONSULTA_PANTALLAS_POR_GRUPO = 
    		"SELECT P.ID_PANTALLA, P.NOMBRE, P.DESCRIPCION "
    		+ " FROM PANTALLA P"
    		+ " JOIN REL_GRUPO_PANTALLA REL"
    		+ " ON REL.FK_ID_PANTALLA = P.ID_PANTALLA"
    		+ " JOIN GRUPO G"
    		+ " ON G.ID_GRUPO = REL.FK_ID_GRUPO"
    		+ " WHERE G.ID_GRUPO = ?";

    private static final String QUERY_CONSULTA_PANTALLA_POR_ID = 
    		"SELECT ID_PANTALLA,NOMBRE,DESCRIPCION FROM PANTALLA WHERE ID_PANTALLA = ?";
    
    private static final String QUERY_UPDATE_PANTALLA = 
    		"UPDATE PANTALLA"
    	  + " SET NOMBRE = ?,DESCRIPCION = ?"
    	  + " WHERE ID_PANTALLA = ?";
    
    private static final String QUERY_ALTA_PANTALLA = 
    		"INSERT INTO PANTALLA(ID_PANTALLA,NOMBRE,DESCRIPCION) VALUES (SQ_PANTALLA.NEXTVAL,?,?)";
    
    private static final String QUERY_ELIMINA_PANTALLA = 
			"DELETE FROM PANTALLA WHERE ID_PANTALLA = ?";
    
    private static final String QUERY_ELIMINA_RELACIONES_PANTALLA_GRUPO = 
			"DELETE FROM REL_GRUPO_PANTALLA WHERE FK_ID_PANTALLA = ?";
    
	public DAOPantallaImpl() {
        super();
    }

	@Override
	public BeanPantallaRespuesta obtenerTodasPantallas(
			ArchitechSessionBean sessionBean) {
		final String consulta = "SELECT ID_PANTALLA,NOMBRE,DESCRIPCION FROM PANTALLA";
		final BeanPantallaRespuesta perfiles = new BeanPantallaRespuesta();
		this.info("Se inicia la consulta de todas las pantallas...");
		this.info(consulta);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY);
		requestDTO.setQuery(consulta);
		requestDTO.setCodeOperation("COD07 Consulta-Pantallas");
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				perfiles.setCodError("2001");
			}else{
				List<BeanPantalla> listaPantallas = new ArrayList<BeanPantalla>();
				for(Map<String, Object> registro : responseDTO.getResultQuery()){
					BeanPantalla pantalla = new BeanPantalla();
					pantalla.setIdPantalla(String.valueOf(registro.get("ID_PANTALLA")));
					pantalla.setNombrePantalla(String.valueOf(registro.get("NOMBRE")));
					pantalla.setDescripcionPantalla(String.valueOf(registro.get("DESCRIPCION")));
					listaPantallas.add(pantalla);
				}
				perfiles.setPantallas(listaPantallas);
				perfiles.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			perfiles.setCodError("2001");
		}
		return perfiles;
	}

	@Override
	public BeanPantallaRespuesta obtenerPantallasPorGrupo(
			ArchitechSessionBean sessionBean, String idGrupo)
			{
		final BeanPantallaRespuesta perfiles = new BeanPantallaRespuesta();
		this.info("Se inicia la consulta de las pantallas en relacion a un grupo con id:"+idGrupo);
		this.info(CONSULTA_PANTALLAS_POR_GRUPO);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY_PARAMS);
		requestDTO.setQuery(CONSULTA_PANTALLAS_POR_GRUPO);
		requestDTO.setCodeOperation("COD08 Consulta-Pantallas por Grupo");
		requestDTO.addParamToSql(idGrupo);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				perfiles.setCodError("2001");
			}else{
				List<BeanPantalla> listaPantallas = new ArrayList<BeanPantalla>();
				for(Map<String, Object> registro : responseDTO.getResultQuery()){
					BeanPantalla pantalla = new BeanPantalla();
					pantalla.setIdPantalla(String.valueOf(registro.get("ID_PANTALLA")));
					pantalla.setNombrePantalla(String.valueOf(registro.get("NOMBRE")));
					pantalla.setDescripcionPantalla(String.valueOf(registro.get("DESCRIPCION")));
					listaPantallas.add(pantalla);
				}
				perfiles.setPantallas(listaPantallas);
				perfiles.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			perfiles.setCodError("2001");
		}
		return perfiles;
	}

	@Override
	public BeanPantallaRespuesta obtenerPantallaPorId(
			ArchitechSessionBean sessionBean, String idPantalla)
			{
		final BeanPantallaRespuesta perfiles = new BeanPantallaRespuesta();
		this.info("Se inicia la consulta de las pantallas con id:"+idPantalla);
		this.info(QUERY_CONSULTA_PANTALLA_POR_ID);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY_PARAMS);
		requestDTO.setQuery(QUERY_CONSULTA_PANTALLA_POR_ID);
		requestDTO.setCodeOperation("COD010 Consulta-Pantallas por ID");
		requestDTO.addParamToSql(idPantalla);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				perfiles.setCodError("2001");
			}else{
				List<BeanPantalla> listaPantallas = new ArrayList<BeanPantalla>();
				for(Map<String, Object> registro : responseDTO.getResultQuery()){
					BeanPantalla pantalla = new BeanPantalla();
					pantalla.setIdPantalla(String.valueOf(registro.get("ID_PANTALLA")));
					pantalla.setNombrePantalla(String.valueOf(registro.get("NOMBRE")));
					pantalla.setDescripcionPantalla(String.valueOf(registro.get("DESCRIPCION")));
					listaPantallas.add(pantalla);
				}
				perfiles.setPantallas(listaPantallas);
				perfiles.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			perfiles.setCodError("2001");
		}
		return perfiles;
	}

	@Override
	public BeanPantallaRespuesta modificarPantalla(
			ArchitechSessionBean sessionBean, BeanPantalla pantalla) {
		final BeanPantallaRespuesta respuesta = new BeanPantallaRespuesta();
		this.info("Se inicia la actualizacion de la pantalla");
		this.info(QUERY_UPDATE_PANTALLA);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_UPDATE_PARAMS);
		requestDTO.setQuery(QUERY_UPDATE_PANTALLA);
		requestDTO.setCodeOperation("COD10 Actualiza-Pantalla");
		requestDTO.addParamToSql(pantalla.getNombrePantalla());
		requestDTO.addParamToSql(pantalla.getDescripcionPantalla());
		requestDTO.addParamToSql(pantalla.getIdPantalla());
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			//El componente IsbanDataAccess no contiene el metodo beginTransaction
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				respuesta.setCodError("2001");
			}else{
				respuesta.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			respuesta.setCodError("2001");
		}
		return respuesta;
	}

	@Override
	public BeanPantallaRespuesta altaPantalla(ArchitechSessionBean sessionBean,
			BeanPantalla pantalla) {
		final BeanPantallaRespuesta respuesta = new BeanPantallaRespuesta();
		this.info("Se inicia el alta de una pantalla");
		this.info(QUERY_ALTA_PANTALLA);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_INSERT_PARAMS);
		requestDTO.setQuery(QUERY_ALTA_PANTALLA);
		requestDTO.setCodeOperation("COD11 Alta-Pantalla");
		requestDTO.addParamToSql(pantalla.getNombrePantalla());
		requestDTO.addParamToSql(pantalla.getDescripcionPantalla());
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			//El componente IsbanDataAccess no contiene el metodo beginTransaction
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				respuesta.setCodError("2001");
			}else{
				respuesta.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			respuesta.setCodError("2001");
		}
		return respuesta;
	}

	@Override
	public BeanPantallaRespuesta borrarPantalla(
			ArchitechSessionBean sessionBean, String idPantalla) {
		BeanPantallaRespuesta eliminacionRelaciones = eliminarRelacionesPantalla(sessionBean, idPantalla);
		if(!"0".equals(eliminacionRelaciones.getCodError())){
			eliminacionRelaciones.setCodError("2001");
		}else{
			this.info("Se inicia la eliminacion de la pantalla");
			this.info(QUERY_ELIMINA_PANTALLA);
			final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
			requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_DELETE_PARAMS);
			requestDTO.setQuery(QUERY_ELIMINA_PANTALLA);
			requestDTO.setCodeOperation("COD12 Elimina-Pantalla");
			requestDTO.addParamToSql(idPantalla);
			try{
				final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
				//El componente IsbanDataAccess no contiene el metodo beginTransaction
				final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
				if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
					eliminacionRelaciones.setCodError("2001");
				}else{
					eliminacionRelaciones.setCodError("0");
				}
			}catch(ExceptionDataAccess e){
				this.error("Error al realizar una consulta en el componente DataAcces"+e);
				eliminacionRelaciones.setCodError("2001");
			}
		}
	return eliminacionRelaciones;
	}

	private BeanPantallaRespuesta eliminarRelacionesPantalla(
			ArchitechSessionBean sessionBean, String idPantalla) {
		final BeanPantallaRespuesta perfiles = new BeanPantallaRespuesta();
		this.info("Se inicia la eliminacion de las relaciones Pantalla - Grupo");
		this.info(QUERY_ELIMINA_RELACIONES_PANTALLA_GRUPO);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_DELETE_PARAMS);
		requestDTO.setQuery(QUERY_ELIMINA_RELACIONES_PANTALLA_GRUPO);
		requestDTO.setCodeOperation("COD13 Elimina Relaciones Grupo- Pantalla");
		requestDTO.addParamToSql(idPantalla);
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

}
