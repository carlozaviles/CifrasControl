package mx.isban.cifrascontrol.dao.auditoria;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.ExceptionDataAccess;
import mx.isban.agave.commons.interfaces.BeanResultBO;
import mx.isban.agave.dataaccess.DataAccess;
import mx.isban.agave.dataaccess.channels.database.dto.RequestMessageDataBaseDTO;
import mx.isban.agave.dataaccess.channels.database.dto.ResponseMessageDataBaseDTO;
import mx.isban.agave.dataaccess.factories.jdbc.ConfigFactoryJDBC;
import mx.isban.agave.logging.Level;
import mx.isban.cifrascontrol.bean.auditoria.BeanPistaAuditoria;
import mx.isban.cifrascontrol.bean.reprocesos.BeanDatosClienteDAO;

/**
 * Session Bean implementation class DAOPistasAuditoriaImpl
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class DAOPistasAuditoriaImpl extends Architech implements DAOPistasAuditoria {
       
    /**
	 * Serial.
	 */
	private static final long serialVersionUID = -9038197627841853386L;
	/**
	 * Instruccion SQL para insertar pistas de auditoria.
	 */
	private static final String INSERT_PISTA_AUDITORIA = "INSERT INTO DEHTMOIN.MOI_MX_AUX_PISTA_AUDIT"
			+ " (ID_PISTA_AUDIT_PK, FCH_OPER,HOR_OPER,VAL_IP_CLIEN,USR_APP,VAL_NOM_APP,DSC_OPER,VAL_ESTAT_OPER,"
			+ "  USR_AFECT_OPER,ID_SESIO,VAL_NOMB_SERVI_WEB,VAL_IP_SERVI_WEB)"
			+ " VALUES (DEHTMOIN.MOI_MX_SEQ_PISTA_AUDIT.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.auditoria.DAOPistasAuditoria#guardaPistaAuditoria(mx.isban.cifrascontrol.bean.auditoria.BeanPistaAuditoria, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public BeanResultBO guardaPistaAuditoria(BeanPistaAuditoria pistaAuditoria, ArchitechSessionBean sessionBean) {
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_INSERT_PARAMS);
		requestDTO.setQuery(INSERT_PISTA_AUDITORIA);
		requestDTO.setCodeOperation("COD30 Inserta-Pista");
		requestDTO.addParamToSql(pistaAuditoria.getFecha() != null? pistaAuditoria.getFecha() : "");
		requestDTO.addParamToSql(pistaAuditoria.getHora() != null? pistaAuditoria.getHora() : "");
		requestDTO.addParamToSql(pistaAuditoria.getIpTerminalCliente() != null? pistaAuditoria.getIpTerminalCliente() : "");
		requestDTO.addParamToSql(pistaAuditoria.getUsuarioApp() != null? pistaAuditoria.getUsuarioApp() : "");
		requestDTO.addParamToSql(pistaAuditoria.getAplicacion() != null? pistaAuditoria.getAplicacion() : "");
		requestDTO.addParamToSql(pistaAuditoria.getCodigoOperacion() != null? pistaAuditoria.getCodigoOperacion() : "");
		requestDTO.addParamToSql(pistaAuditoria.getEstatusOperacion() != null? pistaAuditoria.getEstatusOperacion() : "");
		requestDTO.addParamToSql(pistaAuditoria.getClienteAfectado() != null? pistaAuditoria.getClienteAfectado() : "");
		requestDTO.addParamToSql(pistaAuditoria.getIdSesion() != null? pistaAuditoria.getIdSesion() : "");
		requestDTO.addParamToSql(pistaAuditoria.getNombreServidorWeb() != null? pistaAuditoria.getNombreServidorWeb() : "");
		requestDTO.addParamToSql(pistaAuditoria.getIpServidorWeb() != null? pistaAuditoria.getIpServidorWeb() : "");
		
		final BeanResultBO estatusOperacion = new BeanDatosClienteDAO();
		
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute("ID_CANAL_DATABASE_JDBC");
			if(ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.info("El insert de la pista de auditoria se realizo de manera exitosa.");
				estatusOperacion.setCodError(CODIGO_OPERACION_OK);
				estatusOperacion.setMsgError(responseDTO.getMessageError());
			}else{
				this.warn("Se obtuvo el siguiente codigo de error al intentar realizar la insercion de la pista de auditoria: "
						+ responseDTO.getCodeError());
				estatusOperacion.setCodError(responseDTO.getCodeError());
			}
		}catch(ExceptionDataAccess ex){
			estatusOperacion.setCodError(ex.getCode());
			showException(ex, Level.ERROR);
		}
		return estatusOperacion;
	}

}
