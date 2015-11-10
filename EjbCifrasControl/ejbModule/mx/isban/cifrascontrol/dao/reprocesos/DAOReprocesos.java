package mx.isban.cifrascontrol.dao.reprocesos;

import javax.ejb.Local;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.cifrascontrol.bean.reprocesos.BeanDatosClienteDAO;

@Local
public interface DAOReprocesos {

	/**
	 * Codigo que indica que la operacion se relizo de manera correcta.
	 */
	public static final String CODIGO_OPERACION_OK = "0";
	/**
	 * Codigo de error que sera retornado por el componente cuando suceda algun error
	 * al ejecutar el IDA.
	 */
	public static final String CODIGO_ERROR_EJECUCION_IDA = "2001";
	/**
	 * Codigo que indica que la operacion no arrojo resultados.
	 */
	public static final String CODIGO_NO_RESULTADOS = "2000";
	
	/**
	 * Se ejecuta la consulta para econtrar los datos fiscales dado un numero de cuenta.
	 * @param numeroCuenta Parametro para realizar la consulta.
	 * @param sessionBean Objeto de arquitectura Agave.
	 * @return BeanDatosClienteDAO
	 */
	public BeanDatosClienteDAO ejecutaConsultaPersonas(String numeroCuenta, String numeroTarjeta, ArchitechSessionBean sessionBean);
}
