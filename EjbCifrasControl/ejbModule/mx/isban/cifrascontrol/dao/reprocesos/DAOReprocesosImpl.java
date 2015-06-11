package mx.isban.cifrascontrol.dao.reprocesos;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.ExceptionDataAccess;
import mx.isban.agave.dataaccess.ConfigFactory;
import mx.isban.agave.dataaccess.DataAccess;
import mx.isban.agave.dataaccess.channels.cics.dto.RequestMessageCicsDTO;
import mx.isban.agave.dataaccess.channels.cics.dto.ResponseMessageCicsDTO;
import mx.isban.agave.dataaccess.factories.jms.ConfigFactoryJMS;
import mx.isban.agave.logging.Level;
import mx.isban.cifrascontrol.bean.reprocesos.BeanDatosClienteDAO;
import mx.isban.cifrascontrol.util.reproceso.ConstantesReprocesos;

/**
 * Session Bean implementation class DAOReprocesosImpl
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class DAOReprocesosImpl extends Architech implements DAOReprocesos {
       
    /**
	 * Serial.
	 */
	private static final long serialVersionUID = -8320566037431736019L;

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.reprocesos.DAOReprocesos#ejecutaConsultaPersonas(java.lang.String, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public BeanDatosClienteDAO ejecutaConsultaPersonas(String numeroCuenta,	ArchitechSessionBean sessionBean) {
		this.info("Se realiza la consulta por medio del numero de cuenta: " + numeroCuenta);
		final BeanDatosClienteDAO respuesta = new BeanDatosClienteDAO();
		final StringBuilder mensajeTrama = new StringBuilder();
		
		final RequestMessageCicsDTO requestDTO = new RequestMessageCicsDTO();
		requestDTO.setCodeOperation("COD_OPER_CONSULTA_PERSONAS_01_CICS");
		requestDTO.setTypeOperation(ConfigFactoryJMS.OPERATION_TYPE_SEND_AND_RECEIVE_MESSAGE);
		requestDTO.setTransaction("DEIF");
		requestDTO.setMessage(mensajeTrama.toString());
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageCicsDTO responseDTO = (ResponseMessageCicsDTO)ida.execute("ID_CANAL_CICS");
			if(!ConfigFactory.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se genero un errro al realizar la consulta de personas: " + responseDTO.getCodeError());
				respuesta.setCodError(ConstantesReprocesos.CODIGO_ERROR_EJECUCION_CONSULTA_PERSONAS);
				respuesta.setMsgError(responseDTO.getMessageError());
				return respuesta;
			}
			this.info("El resultado retornado es: " + responseDTO.getResponseMessage());
			this.info("El resultado en formato JSON es: " + responseDTO.getResultToJSONString());
		}catch(ExceptionDataAccess e){
			showException(e, Level.ERROR);
			respuesta.setCodError(ConstantesReprocesos.CODIGO_ERROR_EJECUCION_CONSULTA_PERSONAS);
			respuesta.setMsgError(e.getMessage());
		}
		// TODO Auto-generated method stub
		
		if(numeroCuenta.equals("00000000000")){
			this.info("Se encontraron datos para el numero de cuenta: " + numeroCuenta);
			respuesta.setCodError(CODIGO_OPERACION_OK);
			respuesta.setNumeroCuenta(numeroCuenta);
			respuesta.setCalle("Ezequiel Montes");
			respuesta.setColonia("Centro");
			respuesta.setCodigoPostal("76000");
			respuesta.setEstado("Guerrero");
			respuesta.setMunicipio("Acapulco");
			respuesta.setNumeroExterior("56");
			respuesta.setNombre("Rigoberto");
			respuesta.setMaterno("Aguilera");
			respuesta.setPaterno("Pantoja");
			respuesta.setRfc("PAAR900124M87");
			respuesta.setNumeroExterior("60");
		}else if(numeroCuenta.equals("11111111111")){
			respuesta.setCodError(CODIGO_NO_RESULTADOS);
		}else if(numeroCuenta.equals("22222222222")){
			respuesta.setCodError(CODIGO_ERROR_EJECUCION_IDA);
		}
		return respuesta;
	}


}
