package mx.isban.cifrascontrol.servicio.reprocesos;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.commons.beanutils.BeanUtils;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.agave.logging.Level;
import mx.isban.cifrascontrol.bean.auditoria.BeanPistaAuditoria;
import mx.isban.cifrascontrol.bean.reprocesos.BeanCancelacion;
import mx.isban.cifrascontrol.bean.reprocesos.BeanDatosClienteDAO;
import mx.isban.cifrascontrol.bean.reprocesos.BeanDatosSolicitudReprocesos;
import mx.isban.cifrascontrol.bean.reprocesos.BeanParamsConsultaPrevios;
import mx.isban.cifrascontrol.bean.reprocesos.BeanParamsConsultaReproceso;
import mx.isban.cifrascontrol.bean.reprocesos.BeanPrevioEdc;
import mx.isban.cifrascontrol.bean.reprocesos.BeanRegistroReproceso;
import mx.isban.cifrascontrol.dao.reprocesos.DAOReprocesos;
import mx.isban.cifrascontrol.servicio.auditoria.BOPistasAuditoria;
import mx.isban.cifrascontrol.util.general.ConstantesModuloIntegrador;
import mx.isban.cifrascontrol.util.general.UtilGeneralCifras;
import mx.isban.cifrascontrol.util.reproceso.ConstantesReprocesos;
import mx.isban.cifrascontrol.webservice.reproceso.CancelacionDTO;
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
	/**
	 * Referencia al servicio BOPistasAuditoria.
	 */
	@EJB
	private transient BOPistasAuditoria boPistas;

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.reprocesos.BOReprocesos#realizarConsultaPersonas(java.lang.String, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public BeanDatosSolicitudReprocesos realizarConsultaPersonas(String filtroConsulta, ArchitechSessionBean sessionBean) 
			throws BusinessException {
		this.info("Se ejecutara la consulta de personas con el siguiente parametro: " + filtroConsulta);
		String numCuenta = null;
		String numTarjeta = null;
		if(Pattern.matches("^(\\d{11})|(\\d{20})$", filtroConsulta)){
			numCuenta = filtroConsulta;
		}else if(Pattern.matches("^(\\d{15})|(\\d{16})|(\\d{22})$", filtroConsulta)){
			numTarjeta = filtroConsulta;
		}else{
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_FILTRO_CONSULTA_PERSONAS);
		}
		
		final BeanDatosClienteDAO respuestaConsulta = reprocesos.ejecutaConsultaPersonas(numCuenta, numTarjeta, sessionBean);
		
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
			this.info("La consulta de personas no arrojo resultados para el numero de cuenta: " + filtroConsulta);
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
		final StringBuilder clienteAfectado = new StringBuilder();
		clienteAfectado.append(datosSolicitud.getNombre()).append(" ").append(datosSolicitud.getPaterno()).append(" ")
			.append(datosSolicitud.getMaterno());
		boolean ocurrioError = false;
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
			ocurrioError = true;
			showException(e, Level.ERROR);
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_SOLICITUD_REPROCESO);
		}catch(IllegalAccessException e){
			ocurrioError = true;
			showException(e, Level.ERROR);
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_GENERA_SOLICITUD_REPROCESO);
		}catch(InvocationTargetException e){
			ocurrioError = true;
			showException(e, Level.ERROR);
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_GENERA_SOLICITUD_REPROCESO);
		}finally{
			if(ocurrioError){
				registrarPistaAuditoria(ConstantesModuloIntegrador.COD_PA_SOLICITUD_REPROCESOS, 
						ConstantesModuloIntegrador.COD_PA_OPERACION_NO_OK, clienteAfectado.toString(), sessionBean);
			}
		}
		registrarPistaAuditoria(ConstantesModuloIntegrador.COD_PA_SOLICITUD_REPROCESOS, 
				ConstantesModuloIntegrador.COD_PA_OPERACION_OK, clienteAfectado.toString(), sessionBean);
		
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
		final StringBuilder noCuenta = new StringBuilder();
		noCuenta.append(parametros.getNumeroCuenta());
		periodo.append(parametros.getAnio()).append("-").append(parametros.getMes());
		List<BeanRegistroReproceso> listaReprocesos = null;
		boolean ocurrioError = false;
		try{
			final List<String> productos = UtilGeneralCifras.obtenerNombresProductos(parametros.getProductos());
			List<ReprocesoDTO> resultadoConsulta = null;
			if(!productos.isEmpty()){
				resultadoConsulta = reprocesoProxy.consultarReprocesos(noCuenta.toString(),periodo.toString(),parametros.getProductoSeleccionado());
			}
			if(resultadoConsulta != null && resultadoConsulta.size() > 0){
				this.info("Cantidad de reprocesos encontrada: " + resultadoConsulta.size());
				listaReprocesos = new ArrayList<BeanRegistroReproceso>();
				for(ReprocesoDTO reproceso : resultadoConsulta){
					BeanRegistroReproceso reprocesoGenerado = new BeanRegistroReproceso();
					BeanUtils.copyProperties(reprocesoGenerado, reproceso);
					listaReprocesos.add(reprocesoGenerado);
				}
			}else{
				this.info("No se encontraron reprocesos para el periodo: " + periodo.toString() + noCuenta.toString());
			}
		}catch(ReprocesoException_Exception e){
			ocurrioError = true;
			showException(e, Level.ERROR);
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_CONSULTA_REPROCESOS);
		}catch(IllegalAccessException e){
			ocurrioError = true;
			showException(e, Level.ERROR);
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_GENERA_SOLICITUD_REPROCESO);
		}catch(InvocationTargetException e){
			ocurrioError = true;
			showException(e, Level.ERROR);
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_GENERA_SOLICITUD_REPROCESO);
		}finally{
			if(ocurrioError){
				registrarPistaAuditoria(ConstantesModuloIntegrador.COD_PA_CONSULTA_REPROCESOS, 
						ConstantesModuloIntegrador.COD_PA_OPERACION_NO_OK, ConstantesModuloIntegrador.COD_PA_NO_APLICA, sessionBean);
			}
		}
		registrarPistaAuditoria(ConstantesModuloIntegrador.COD_PA_CONSULTA_REPROCESOS, 
				ConstantesModuloIntegrador.COD_PA_OPERACION_OK, ConstantesModuloIntegrador.COD_PA_NO_APLICA, sessionBean);
		
		return listaReprocesos;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.reprocesos.BOReprocesos#ejecutaConsultaPrevios(mx.isban.cifrascontrol.bean.reprocesos.BeanParamsConsultaPrevios, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanPrevioEdc> ejecutaConsultaPrevios(BeanParamsConsultaPrevios parametros,
			ArchitechSessionBean sessionBean) throws BusinessException {
		this.info("Se ejecuta la consulta de Previos con los siguientes parametros: " + parametros.toString());
		final String mascaraPrevios = this.getConfigDeCmpAplicacion("MASCARA_PREVIOS");
		final String rutaPrevios = this.getConfigDeCmpAplicacion("RUTA_PREVIOS");
		if(mascaraPrevios == null || rutaPrevios == null){
			this.warn("Error al cargar la configuracion para consulta de Previos");
			registrarPistaAuditoria(ConstantesModuloIntegrador.COD_PA_CONSULTA_PREVIOS, 
					ConstantesModuloIntegrador.COD_PA_OPERACION_NO_OK, ConstantesModuloIntegrador.COD_PA_NO_APLICA, sessionBean);
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_CONFIG_PREVIOS);
		}
		final String periodo = parametros.getMes().concat(parametros.getAnio());
		final String mascaraPreviosEditada = mascaraPrevios.replace("PRODUCTO", parametros.getProducto())
				.replace("NUMEROCUENTA", parametros.getNumeroCuenta().trim()).replace("PERIODO", periodo);
		this.info("La mascara utilizada para realizar la consulta es " + mascaraPreviosEditada);
		final List<File> listaCoincidencias = UtilGeneralCifras.filtrarListaArchivos(rutaPrevios, mascaraPreviosEditada);
		this.info("La consulta de previos arrojo el siguiente numero de coincidencias: " + listaCoincidencias.size());
		
		final List<BeanPrevioEdc> listaPrevios = new ArrayList<BeanPrevioEdc>();
		for(File coincidencia : listaCoincidencias){
			try{
				final BeanPrevioEdc previo = new BeanPrevioEdc();
				previo.setRutaPrevio(coincidencia.toString());
				previo.setNumeroCuenta(parametros.getNumeroCuenta());
				previo.setProducto(parametros.getProducto());
				previo.setFecha(UtilGeneralCifras.obtenerFecha(parametros.getAnio() + parametros.getMes(), "yyyyMM"));
				listaPrevios.add(previo);
			}catch(ParseException e){
				registrarPistaAuditoria(ConstantesModuloIntegrador.COD_PA_CONSULTA_PREVIOS, 
						ConstantesModuloIntegrador.COD_PA_OPERACION_NO_OK, ConstantesModuloIntegrador.COD_PA_NO_APLICA, sessionBean);
				showException(e, Level.ERROR);
				throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_PROCESO_PREVIOS);
			}
		}
		
		registrarPistaAuditoria(ConstantesModuloIntegrador.COD_PA_CONSULTA_PREVIOS, 
				ConstantesModuloIntegrador.COD_PA_OPERACION_OK, ConstantesModuloIntegrador.COD_PA_NO_APLICA, sessionBean);
		
		return listaPrevios;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.reprocesos.BOReprocesos#ejecutaConsultaCancelaciones(java.lang.String, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanCancelacion> ejecutaConsultaCancelaciones(String periodo, ArchitechSessionBean sessionBean) 
			throws BusinessException {
		this.info("Se ejecutara la consulta de Cancelacion con el siguiente parametro AQUI: " + periodo);
		final ReprocesoService rps = new ReprocesoService();
		final Reproceso reprocesoProxy = rps.getReprocesoImplPort();
		
		List<BeanCancelacion> listaCancelaciones = new ArrayList<BeanCancelacion>();
		boolean ocurrioError = false;
		try{
			final String fechasCancel = UtilGeneralCifras.obtenerFechas(periodo);
			List<CancelacionDTO> resultConsulta = null;
			if(!fechasCancel.isEmpty()){
				resultConsulta = reprocesoProxy.consultaCancelacion(fechasCancel);
			}
			
			if(resultConsulta != null && resultConsulta.size()>0){
				this.info("Cantidad de cancelaciones encontradas: " + resultConsulta.size());
				for(CancelacionDTO repro : resultConsulta){
					BeanCancelacion reproGenerado = new BeanCancelacion();
					BeanUtils.copyProperties(reproGenerado, repro);
					listaCancelaciones.add(reproGenerado);
				}
			}else {
				this.info("No se encontro cancelacion para el periodo seleccionado :" + periodo);
			}
		}catch(ReprocesoException_Exception e){
			ocurrioError = true;
			showException(e, Level.ERROR);
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_CONSULTA_REPROCESOS);
		}catch(IllegalAccessException e){
			ocurrioError = true;
			showException(e, Level.ERROR);
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_GENERA_SOLICITUD_REPROCESO);
		}catch(InvocationTargetException e){
			ocurrioError = true;
			showException(e, Level.ERROR);
			throw new BusinessException(ConstantesReprocesos.CODIGO_ERROR_GENERA_SOLICITUD_REPROCESO);
		}finally{
			if(ocurrioError){
				registrarPistaAuditoria(ConstantesModuloIntegrador.COD_PA_CONSULTA_CANCELACION, 
						ConstantesModuloIntegrador.COD_PA_OPERACION_NO_OK, ConstantesModuloIntegrador.COD_PA_NO_APLICA, sessionBean);
			}
		}

		registrarPistaAuditoria(ConstantesModuloIntegrador.COD_PA_CONSULTA_CANCELACION, 
				ConstantesModuloIntegrador.COD_PA_OPERACION_OK, ConstantesModuloIntegrador.COD_PA_NO_APLICA, sessionBean);
		
		return listaCancelaciones;
	}
	
	/**
	 * Metodo utilitario para regstrar pistas de auditoria.
	 * @param operacion La operacion que fue realizada.
	 * @param estatus El estatus resultado de la operacion.
	 * @param clienteAfectado El cliente que fue afectado con la operacion, en caso de que aplique.
	 * @param sessionBean Objeto de la arquitectura agave.
	 */
	private void registrarPistaAuditoria(String operacion, String estatus, String clienteAfectado, 
			ArchitechSessionBean sessionBean){
		final BeanPistaAuditoria pistaAuditoria = new BeanPistaAuditoria();
		pistaAuditoria.setCodigoOperacion(operacion);
		pistaAuditoria.setEstatusOperacion(estatus);
		pistaAuditoria.setClienteAfectado(clienteAfectado);
		boPistas.generaPistaAuditoria(pistaAuditoria, sessionBean);
	}
}