package mx.isban.cifrascontrol.servicio.reprocesos;

import java.util.List;

import javax.ejb.Remote;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.bean.reprocesos.BeanDatosSolicitudReprocesos;
import mx.isban.cifrascontrol.bean.reprocesos.BeanParamsConsultaReproceso;
import mx.isban.cifrascontrol.bean.reprocesos.BeanRegistroReproceso;

@Remote
public interface BOReprocesos {

	/**
	 * Realiza el llamado de la consulta de personas.
	 * @param numeroCuenta Parametro para la consulta de personas.
	 * @param sessionBean Objeto de arquitectura Agave.
	 * @return BeanDatosSolicitudReprocesos
	 * @throws BusinessException Exception
	 */
	public BeanDatosSolicitudReprocesos realizarConsultaPersonas(String numeroCuenta, 
			ArchitechSessionBean sessionBean) throws BusinessException;
	
	/**
	 * Lleva a cabo el registro de una solicitud de Reproceso.
	 * @param datosSolicitud Datos necesarios para realizar el registro del reproceso.
	 * @param sessionBean Objetod de la arquitectura Agave.
	 * @throws BusinessException Exception
	 */
	public void registrarSolicitudReproceso(BeanDatosSolicitudReprocesos datosSolicitud, 
			ArchitechSessionBean sessionBean) throws BusinessException;
	
	/**
	 * Realiza la ejecucion del reprocesos de acuerdo a los parametros elegidos por el usuario.
	 * @param parametros Contiene los parametros para realizar la consulta.
	 * @param sessionBean Objteto de la arquitectura agave.
	 * @return List<BeanRegistroReproceso>
	 * @throws BusinessException Exception
	 */
	public List<BeanRegistroReproceso> ejecutaConsultaReprocesos(BeanParamsConsultaReproceso parametros, 
			ArchitechSessionBean sessionBean) throws BusinessException;
}
