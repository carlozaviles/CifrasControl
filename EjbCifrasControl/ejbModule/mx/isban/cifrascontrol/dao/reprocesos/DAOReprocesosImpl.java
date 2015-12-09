package mx.isban.cifrascontrol.dao.reprocesos;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.commons.lang.StringUtils;

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
import mx.isban.cifrascontrol.util.general.ConstantesModuloIntegrador;
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
	 * @see mx.isban.cifrascontrol.dao.reprocesos.DAOReprocesos#ejecutaConsultaPersonas(java.lang.String, java.lang.String, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public BeanDatosClienteDAO ejecutaConsultaPersonas(String numeroCuenta,	String numeroTarjeta, ArchitechSessionBean sessionBean) {
		BeanDatosClienteDAO respuesta = new BeanDatosClienteDAO();
		final String mensajeTrama = construirTramaEntrada("0014", numeroCuenta, numeroTarjeta);
		this.info("La trama de entrada es la siguiente: " + mensajeTrama);		
		final RequestMessageCicsDTO requestDTO = new RequestMessageCicsDTO();
		requestDTO.setCodeOperation("COD_OPER_CONSULTA_PERSONAS_01_CICS");
		requestDTO.setTypeOperation(ConfigFactoryJMS.OPERATION_TYPE_SEND_AND_RECEIVE_MESSAGE);
		requestDTO.setTransaction("ODCE");
		requestDTO.setMessage(mensajeTrama);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageCicsDTO responseDTO = (ResponseMessageCicsDTO)ida.execute("ID_CANAL_CICS");
			if(!ConfigFactory.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se genero un errro al realizar la consulta de personas: " + responseDTO.getCodeError());
				respuesta.setCodError(ConstantesReprocesos.CODIGO_ERROR_EJECUCION_CONSULTA_PERSONAS);
				respuesta.setMsgError(responseDTO.getMessageError());
				return respuesta;
			}else if(StringUtils.isBlank(responseDTO.getResponseMessage())){
				respuesta.setCodError(CODIGO_NO_RESULTADOS);
			}
			respuesta = mapearRespuestaConsultaPersonas(responseDTO.getResponseMessage());
			respuesta.setCodError(CODIGO_OPERACION_OK);
			this.info("El resultado retornado es: " + responseDTO.getResponseMessage());
			this.info("El resultado en formato JSON es: " + responseDTO.getResultToJSONString());
		}catch(ExceptionDataAccess e){
			showException(e, Level.ERROR);
			respuesta.setCodError(ConstantesReprocesos.CODIGO_ERROR_EJECUCION_CONSULTA_PERSONAS);
			respuesta.setMsgError(e.getMessage());
		}
//		String tramaEjemplo = "12311111111SANTOYO             BAEZA               URIEL                                   SABU860825          FRAY ALIPIO RANGEL                                26                            CENTRO                        YURIRIA                       GTGUANAJUATO                    25865111";
//		respuesta = mapearRespuestaConsultaPersonas(tramaEjemplo);
//		respuesta.setCodError(CODIGO_OPERACION_OK);
		return respuesta;
	}
	
	/**
	 * Arma la trama de entrada para la consulta de personas.
	 * @param entidad Entidad del banco
	 * @param numeroCuenta Numero de cuenta.
	 * @param numeroTarjeta Numero de tarjeta
	 * @return String
	 */
	private String construirTramaEntrada(String entidad, String numeroCuenta, String numeroTarjeta){
		final StringBuilder tramaEntrada = new StringBuilder();
		final String canalOperacion = ConstantesModuloIntegrador.CARACTER_ESPACIO;
		final String canalComercializacion = ConstantesModuloIntegrador.CARACTER_ESPACIO;
		tramaEntrada.append(StringUtils.leftPad(entidad, 4, ConstantesModuloIntegrador.CARACTER_ESPACIO))
		.append(StringUtils.rightPad(canalOperacion, 2, ConstantesModuloIntegrador.CARACTER_ESPACIO))
		.append(StringUtils.rightPad(canalComercializacion, 2, ConstantesModuloIntegrador.CARACTER_ESPACIO))
		.append(StringUtils.rightPad(StringUtils.defaultString(numeroCuenta), 20, 
				ConstantesModuloIntegrador.CARACTER_ESPACIO))
		.append(StringUtils.rightPad(StringUtils.defaultString(numeroTarjeta), 22, 
				ConstantesModuloIntegrador.CARACTER_ESPACIO));
		return tramaEntrada.toString();
	}
	
	/**
	 * Procesa la trama de respuesta de la consulta de personas y forma un objeto de tipo BeanDatosCliente.
	 * @param tramaSalida Contiene la trama de salida de la consulta de personas. 
	 * @return BeanDatosClienteDAO
	 */
	private BeanDatosClienteDAO mapearRespuestaConsultaPersonas(String tramaSalida){
		final BeanDatosClienteDAO respuesta = new BeanDatosClienteDAO();
		respuesta.setPaterno(StringUtils.substring(tramaSalida, 11, 31).trim());
		respuesta.setMaterno(StringUtils.substring(tramaSalida, 31, 51).trim());
		respuesta.setNombre(StringUtils.substring(tramaSalida, 51, 91).trim());
		respuesta.setRfc(StringUtils.substring(tramaSalida, 91, 111).trim());
		respuesta.setCalle(StringUtils.substring(tramaSalida, 111, 161).trim());
		respuesta.setNumeroExterior(StringUtils.substring(tramaSalida, 161, 176).trim());
		respuesta.setNumeroInterior(StringUtils.substring(tramaSalida, 176, 191).trim());
		respuesta.setColonia(StringUtils.substring(tramaSalida, 191, 221).trim());
		respuesta.setMunicipio(StringUtils.substring(tramaSalida, 221, 251).trim());
		respuesta.setEstado(StringUtils.substring(tramaSalida, 253, 283).trim());
		respuesta.setCodigoPostal(StringUtils.substring(tramaSalida, 283).trim());
		
		return respuesta;
	}
}
