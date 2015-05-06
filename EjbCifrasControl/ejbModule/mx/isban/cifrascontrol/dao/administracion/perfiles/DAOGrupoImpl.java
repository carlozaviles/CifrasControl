package mx.isban.cifrascontrol.dao.administracion.perfiles;

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
import mx.isban.cifrascontrol.beans.administracion.grupo.BeanGrupoRespuesta;
import mx.isban.cifrascontrol.beans.administracion.grupo.BeanGrupo;
import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantalla;

/**
 * Session Bean implementation class DAOPerfilesImpl
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class DAOGrupoImpl extends Architech implements DAOGrupo {
       
	private static final long serialVersionUID = 1L;
	private static final String ID_CANAL = "ID_CANAL_DATABASE_JDBC";
	private static final String QUERY_ACTUALIZA_GRUPO = 
			"UPDATE GRUPO"
			+ " SET NOMBRE = ?,DESCRIPCION = ?"
			+ " WHERE ID_GRUPO = ?";
	private static final String QUERY_ELIMINA_RELACIONES_GRUPO_PANTALLA = 
			"DELETE FROM REL_GRUPO_PANTALLA WHERE FK_ID_GRUPO = ?";
	
	private static final String QUERY_CREA_RELACION_GRUPO_PANTALLA =
			"INSERT INTO REL_GRUPO_PANTALLA(ID_RELACION,FK_ID_GRUPO,FK_ID_PANTALLA) VALUES (SQ_REL_GRUPO_PANTALLA.NEXTVAL,?,?)";
	private static final String QUERY_ALTA_GRUPO = 
			"INSERT INTO GRUPO(ID_GRUPO,NOMBRE,DESCRIPCION) VALUES (SQ_GRUPO.NEXTVAL,?,?)";
	private static final String QUERY_ELIMINA_GRUPO = 
			"DELETE FROM GRUPO WHERE ID_GRUPO = ?";
	
	/**
     * @see Architech#Arhitech()
     */
    public DAOGrupoImpl() {
        super();
    }

	@Override
	public BeanGrupoRespuesta buscarTodosGrupos(
			ArchitechSessionBean sessionBean) {
		final String consulta = "SELECT ID_GRUPO,NOMBRE,DESCRIPCION FROM GRUPO";
		final BeanGrupoRespuesta perfiles = new BeanGrupoRespuesta();
		this.info("Se inicia la consulta de todos los perfiles...");
		this.info(consulta);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY);
		requestDTO.setQuery(consulta);
		requestDTO.setCodeOperation("COD01 Consulta-Grupos");
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				perfiles.setCodError("2001");
			}else{
				List<BeanGrupo> listaPerfiles = new ArrayList<BeanGrupo>();
				for(Map<String, Object> registro : responseDTO.getResultQuery()){
					BeanGrupo perfil = new BeanGrupo();
					perfil.setIdPerfil(String.valueOf(registro.get("ID_GRUPO")));
					perfil.setNombrePerfil(String.valueOf(registro.get("NOMBRE")));
					perfil.setDescripcionPerfil(String.valueOf(registro.get("DESCRIPCION")));
					listaPerfiles.add(perfil);
				}
				perfiles.setGrupos(listaPerfiles);
				perfiles.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			perfiles.setCodError("2001");
		}
		return perfiles;
	}

	@Override
	public BeanGrupoRespuesta consultarGrupoPorId(ArchitechSessionBean sessionBean,
			String idGrupo) {
		final String consulta = "SELECT ID_GRUPO,NOMBRE,DESCRIPCION FROM GRUPO WHERE ID_GRUPO = ?";
		final BeanGrupoRespuesta perfiles = new BeanGrupoRespuesta();
		this.info("Se inicia la consulta del perfil con id:"+idGrupo);
		this.info(consulta);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY_PARAMS);
		requestDTO.setQuery(consulta);
		requestDTO.setCodeOperation("COD02 Consulta-Grupo");
		requestDTO.addParamToSql(idGrupo);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				perfiles.setCodError("2001");
			}else{
				List<BeanGrupo> listaPerfiles = new ArrayList<BeanGrupo>();
				for(Map<String, Object> registro : responseDTO.getResultQuery()){
					BeanGrupo perfil = new BeanGrupo();
					perfil.setIdPerfil(String.valueOf(registro.get("ID_GRUPO")));
					perfil.setNombrePerfil(String.valueOf(registro.get("NOMBRE")));
					perfil.setDescripcionPerfil(String.valueOf(registro.get("DESCRIPCION")));
					listaPerfiles.add(perfil);
				}
				perfiles.setGrupos(listaPerfiles);
				perfiles.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			perfiles.setCodError("2001");
		}
		return perfiles;
	}

	@Override
	public BeanGrupoRespuesta consultarGrupoPorNombre(
			ArchitechSessionBean sessionBean, String nombreGrupo) {
		final String consulta = "SELECT ID_GRUPO,NOMBRE,DESCRIPCION FROM GRUPO WHERE NOMBRE = ?";
		final BeanGrupoRespuesta perfiles = new BeanGrupoRespuesta();
		this.info("Se inicia la consulta del perfil con el nombre:"+nombreGrupo);
		this.info(consulta);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY_PARAMS);
		requestDTO.setQuery(consulta);
		requestDTO.setCodeOperation("COD09 Consulta-Grupo por nombre");
		requestDTO.addParamToSql(nombreGrupo);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				perfiles.setCodError("2001");
			}else{
				List<BeanGrupo> listaPerfiles = new ArrayList<BeanGrupo>();
				for(Map<String, Object> registro : responseDTO.getResultQuery()){
					BeanGrupo perfil = new BeanGrupo();
					perfil.setIdPerfil(String.valueOf(registro.get("ID_GRUPO")));
					perfil.setNombrePerfil(String.valueOf(registro.get("NOMBRE")));
					perfil.setDescripcionPerfil(String.valueOf(registro.get("DESCRIPCION")));
					listaPerfiles.add(perfil);
				}
				perfiles.setGrupos(listaPerfiles);
				perfiles.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			perfiles.setCodError("2001");
		}
		return perfiles;
	}

	@Override
	public BeanGrupoRespuesta modificarGrupo(ArchitechSessionBean sessionBean,
			BeanGrupo grupo) {
		
		final BeanGrupoRespuesta perfiles = new BeanGrupoRespuesta();
		this.info("Se inicia la actualizacion del grupo");
		this.info(QUERY_ACTUALIZA_GRUPO);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_UPDATE_PARAMS);
		requestDTO.setQuery(QUERY_ACTUALIZA_GRUPO);
		requestDTO.setCodeOperation("COD03 Actualiza-Grupo");
		requestDTO.addParamToSql(grupo.getNombrePerfil());
		requestDTO.addParamToSql(grupo.getDescripcionPerfil());
		requestDTO.addParamToSql(grupo.getIdPerfil());
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			//El componente IsbanDataAccess no contiene el metodo beginTransaction
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				perfiles.setCodError("2001");
			}else{
				BeanGrupoRespuesta eliminacionRelaciones = eliminarRelacionesGrupoPantalla(sessionBean, grupo.getIdPerfil());
				if(!"0".equals(eliminacionRelaciones.getCodError())){
					perfiles.setCodError(eliminacionRelaciones.getCodError());
				}else{
					List<BeanPantalla> pantallas = grupo.getPantallas();
					this.info("Tamaño de lista seleccionada:"+pantallas.size());
					for (int i = 0;i < pantallas.size();i++) {	
						BeanGrupoRespuesta actualizaRelaciones = actualizaRelacionesGrupoPantalla(sessionBean, grupo.getIdPerfil(),pantallas.get(i).getIdPantalla());
						if(!"0".equals(actualizaRelaciones.getCodError())){
							perfiles.setCodError(actualizaRelaciones.getCodError());
							i = pantallas.size()+1;
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
	public BeanGrupoRespuesta altaGrupo(ArchitechSessionBean sessionBean,
			BeanGrupo grupo) {
		final BeanGrupoRespuesta perfiles = new BeanGrupoRespuesta();
		this.info("Se inicia el alta de un nuevo grupo");
		this.info(QUERY_ALTA_GRUPO);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_INSERT_PARAMS);
		requestDTO.setQuery(QUERY_ALTA_GRUPO);
		requestDTO.setCodeOperation("COD08 Alta-Grupo");
		requestDTO.addParamToSql(grupo.getNombrePerfil());
		requestDTO.addParamToSql(grupo.getDescripcionPerfil());
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			//El componente IsbanDataAccess no contiene el metodo beginTransaction
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				perfiles.setCodError("2001");
			}else{
				BeanGrupoRespuesta consultaGrupo = consultarGrupoPorNombre(sessionBean, grupo.getNombrePerfil());
				if(!"0".equals(consultaGrupo.getCodError())){
					perfiles.setCodError(consultaGrupo.getCodError());
				}else{
					if(consultaGrupo.getGrupos().size()>=1){
						String idPerfilNuevo =consultaGrupo.getGrupos().get(0).getIdPerfil(); 
						grupo.setIdPerfil(idPerfilNuevo);
						List<BeanPantalla> pantallas = grupo.getPantallas();
						this.info("Tamaño de lista seleccionada:"+pantallas.size());
						for (int i = 0;i < pantallas.size();i++) {	
							BeanGrupoRespuesta actualizaRelaciones = actualizaRelacionesGrupoPantalla(sessionBean, grupo.getIdPerfil(),pantallas.get(i).getIdPantalla());
							if(!"0".equals(actualizaRelaciones.getCodError())){
								perfiles.setCodError(actualizaRelaciones.getCodError());
								i = pantallas.size()+1;
							}else{
								perfiles.setCodError("0");
							}
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
	public BeanGrupoRespuesta borrarGrupo(ArchitechSessionBean sessionBean,
			String idGrupo) {
		
			BeanGrupoRespuesta eliminacionRelaciones = eliminarRelacionesGrupoPantalla(sessionBean, idGrupo);
			if(!"0".equals(eliminacionRelaciones.getCodError())){
				eliminacionRelaciones.setCodError("2001");
			}else{
				this.info("Se inicia la eliminacion del grupo");
				this.info(QUERY_ELIMINA_GRUPO);
				final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
				requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_DELETE_PARAMS);
				requestDTO.setQuery(QUERY_ELIMINA_GRUPO);
				requestDTO.setCodeOperation("COD09 Elimina-Grupo");
				requestDTO.addParamToSql(idGrupo);
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

	private BeanGrupoRespuesta actualizaRelacionesGrupoPantalla(
			ArchitechSessionBean sessionBean, String idGrupo, String idPantalla) {
		final BeanGrupoRespuesta perfiles = new BeanGrupoRespuesta();
		this.info("Se inicia la creacion de las relaciones Pantalla - Grupo");
		this.info(QUERY_CREA_RELACION_GRUPO_PANTALLA);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_INSERT_PARAMS);
		requestDTO.setQuery(QUERY_CREA_RELACION_GRUPO_PANTALLA);
		requestDTO.setCodeOperation("COD05 Crea Relaciones Grupo- Pantalla");
		requestDTO.addParamToSql(idGrupo);
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

	private BeanGrupoRespuesta eliminarRelacionesGrupoPantalla(ArchitechSessionBean sessionBean,
			String idGrupo){
		final BeanGrupoRespuesta perfiles = new BeanGrupoRespuesta();
		this.info("Se inicia la eliminacion de las relaciones Pantalla - Grupo");
		this.info(QUERY_ELIMINA_RELACIONES_GRUPO_PANTALLA);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_DELETE_PARAMS);
		requestDTO.setQuery(QUERY_ELIMINA_RELACIONES_GRUPO_PANTALLA);
		requestDTO.setCodeOperation("COD04 Elimina Relaciones Grupo- Pantalla");
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

}
