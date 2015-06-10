package mx.isban.cifrascontrol.dao.reprocesos;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.cifrascontrol.bean.reprocesos.BeanDatosClienteDAO;

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
		// TODO Auto-generated method stub
		final BeanDatosClienteDAO respuesta = new BeanDatosClienteDAO();
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
