package mx.isban.cifrascontrol.servicio.reprocesos;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.commons.beanutils.BeanUtils;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.agave.logging.Level;
import mx.isban.cifrascontrol.bean.reprocesos.BeanDatosClienteDAO;
import mx.isban.cifrascontrol.bean.reprocesos.BeanDatosSolicitudReprocesos;
import mx.isban.cifrascontrol.bean.reprocesos.BeanParamsConsultaReproceso;
import mx.isban.cifrascontrol.bean.reprocesos.BeanRegistroReproceso;
import mx.isban.cifrascontrol.dao.reprocesos.DAOReprocesos;
import mx.isban.cifrascontrol.util.reproceso.ConstantesReprocesos;
import mx.isban.cifrascontrol.webservice.reproceso.Reproceso;
import mx.isban.cifrascontrol.webservice.reproceso.ReprocesoDTO;
import mx.isban.cifrascontrol.webservice.reproceso.ReprocesoException_Exception;
import mx.isban.cifrascontrol.webservice.reproceso.ReprocesoService;
import mx.isban.cifrascontrol.webservice.reproceso.SolicitudReprocesoDTO;

/**
 * Session Bean implementation class BOReprocesosImpl
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BOReprocesosImpl extends Architech implements BOReprocesos {
       
    /**
	 * Serial.
	 */
	private static final long serialVersionUID = -481786980887361770L;
	/**
	 * Objeto de la capa de acceso a datos.
	 */
	@EJB
	private transient DAOReprocesos reprocesos;

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.reprocesos.BOReprocesos#realizarConsultaPersonas(java.lang.String, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public BeanDatosSolicitudReprocesos realizarConsultaPersonas(String numeroCuenta, ArchitechSessionBean sessionBean) 
			throws BusinessException {
		this.info("Se ejecutara la consulta de personas con el numero de cuenta: " + numeroCuenta);
		final BeanDatosClienteDAO respuestaConsulta = reprocesos.ejecutaConsultaPersonas(numeroCuenta, sessionBean);
		if(DAOReprocesos.CODIGO_OPERACION_OK.equals(respuestaConsulta.getCodError())){
			this.info("Se retorna la respuesta encontrada hacia el front");
			final BeanDatosSolicitudReprocesos datosFiscales = new BeanDatosSolicitudReprocesos();
			try{
				BeanUtils.copyProperties(datosFiscales, respuestaConsulta);
				this.info("Los datos recuperados de la consulta de personas son: " + datosFiscales.toString());
			}catch(IllegalAccessException e){
				showException(e, Level.ERROR);
				throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_PROCESA_RESULTADO_CONSULTA);
			}catch(InvocationTargetException e){
				showException(e, Level.ERROR);
				throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_PROCESA_RESULTADO_CONSULTA);
			}
			return datosFiscales;
		}else if(DAOReprocesos.CODIGO_NO_RESULTADOS.equals(respuestaConsulta.getCodError())){
			this.info("La consulta de personas no arrojo resultados para el numero de cuenta: " + numeroCuenta);
			return null;
		}else{
			this.info("Se presento un error al ejecutar la consulta de personas: " + respuestaConsulta.getCodError());
			throw new BusinessException(respuestaConsulta.getCodError());
		}
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.reprocesos.BOReprocesos#registrarSolicitudReproceso(mx.isban.cifrascontrol.bean.reprocesos.BeanDatosSolicitudReprocesos, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public void registrarSolicitudReproceso(BeanDatosSolicitudReprocesos datosSolicitud, ArchitechSessionBean sessionBean) 
			throws BusinessException {
		this.info("Los datos para solicitud de reprocesos se muestran a continuacion: " + datosSolicitud.toString());
		ReprocesoService rs = new ReprocesoService();
		Reproceso reprocesoProxy = rs.getReprocesoImplPort();
		final SolicitudReprocesoDTO solicitudDTO = new SolicitudReprocesoDTO();
		
		try{
			BeanUtils.copyProperties(solicitudDTO, datosSolicitud);
			final StringBuilder periodo = new StringBuilder();
			periodo.append(datosSolicitud.getAnio()).append("-").append(datosSolicitud.getMes());
			solicitudDTO.setPeriodo(periodo.toString());
			final SimpleDateFormat sdf = new SimpleDateFormat(ConstantesReprocesos.FORMATO_FECHA_SOLICITUD_REPROCESO);
			solicitudDTO.setFechaSolicitud(sdf.format(new Date()));
			solicitudDTO.setUsuarioOperativo(sessionBean.getUsuario());
			reprocesoProxy.solicitarReproceso(solicitudDTO);
		}catch(ReprocesoException_Exception e){
			showException(e, Level.ERROR);
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_SOLICITUD_REPROCESO);
		}catch(IllegalAccessException e){
			showException(e, Level.ERROR);
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_GENERA_SOLICITUD_REPROCESO);
		}catch(InvocationTargetException e){
			showException(e, Level.ERROR);
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_GENERA_SOLICITUD_REPROCESO);
		}
		this.info("Se llevo a cabo la solicitud de reproceso.");
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.reprocesos.BOReprocesos#ejecutaConsultaReprocesos(mx.isban.cifrascontrol.bean.reprocesos.BeanParamsConsultaReproceso, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanRegistroReproceso> ejecutaConsultaReprocesos(BeanParamsConsultaReproceso parametros,
			ArchitechSessionBean sessionBean) throws BusinessException {
		this.info("Se ejecutara la consulta de reprocesos con los siguientes parametros: " + parametros.toString());
		final ReprocesoService rs = new ReprocesoService();
		final Reproceso reprocesoProxy = rs.getReprocesoImplPort();
		final StringBuilder periodo = new StringBuilder();
		periodo.append(parametros.getAnio()).append("-").append(parametros.getMes());
		List<BeanRegistroReproceso> listaReprocesos = null;
		try{
			final List<ReprocesoDTO> resultadoConsulta = reprocesoProxy.consultarReprocesos(periodo.toString());
			if(resultadoConsulta != null && resultadoConsulta.size() > 0){
				this.info("Cantidad de reprocesos encontrada: " + resultadoConsulta.size());
				listaReprocesos = new ArrayList<BeanRegistroReproceso>();
				for(ReprocesoDTO reproceso : resultadoConsulta){
					BeanRegistroReproceso reprocesoGenerado = new BeanRegistroReproceso();
					BeanUtils.copyProperties(reprocesoGenerado, reproceso);
					listaReprocesos.add(reprocesoGenerado);
				}
			}else{
				this.info("No se encontraron reprocesos para el periodo: " + periodo.toString());
			}
		}catch(ReprocesoException_Exception e){
			showException(e, Level.ERROR);
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_CONSULTA_REPROCESOS);
		}catch(IllegalAccessException e){
			showException(e, Level.ERROR);
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_GENERA_SOLICITUD_REPROCESO);
		}catch(InvocationTargetException e){
			showException(e, Level.ERROR);
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_GENERA_SOLICITUD_REPROCESO);
		}
		return listaReprocesos;
	}
	
}
