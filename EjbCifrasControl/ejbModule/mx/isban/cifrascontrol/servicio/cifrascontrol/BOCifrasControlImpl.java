package mx.isban.cifrascontrol.servicio.cifrascontrol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.xml.ws.BindingProvider;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.agave.logging.Level;
import mx.isban.cifrascontrol.bean.auditoria.BeanPistaAuditoria;
import mx.isban.cifrascontrol.beans.cifrascontrol.BeanCifrasControl;
import mx.isban.cifrascontrol.beans.cifrascontrol.BeanInsidenciaCifras;
import mx.isban.cifrascontrol.servicio.auditoria.BOPistasAuditoria;
import mx.isban.cifrascontrol.util.cifrascontrol.ConstantesCifrasControl;
import mx.isban.cifrascontrol.util.general.ConstantesModuloIntegrador;
import mx.isban.cifrascontrol.util.general.OrdenadorInsidenciaCifras;
import mx.isban.cifrascontrol.util.general.UtilGeneralCifras;
import mx.isban.cifrascontrol.webservice.cifrascontrol.CifrasControl;
import mx.isban.cifrascontrol.webservice.cifrascontrol.CifrasControlDTO;
import mx.isban.cifrascontrol.webservice.cifrascontrol.CifrasControlException_Exception;
import mx.isban.cifrascontrol.webservice.cifrascontrol.CifrasControlService;
import mx.isban.cifrascontrol.webservice.cifrascontrol.DetalleCifrasControlDTO;
import mx.isban.cifrascontrol.webservice.cifrascontrol.SolicitudCifrasControlDTO;

/**
 * Session Bean implementation class BOCifrasControlImpl
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BOCifrasControlImpl extends Architech implements BOCifrasControl {

	/**
	 * Numero de la clase serializada
	 */
	private static final long serialVersionUID = -3603871439981570935L;

	/**
	 * Objeto que contiene los metodos a ejecutar por el servicio web
	 */
	private CifrasControl cifrasControl;
	/**
	 * Listado de las cifras de control
	 */
	private List<BeanCifrasControl> cifrasControlList;
	/**
	 * Referencia hacia objeto de la capa de negocio BOPistasAuditoria.
	 */
	@EJB
	private BOPistasAuditoria boPistas;

	/**
	 * @see Architech#Architech()
	 */
	public BOCifrasControlImpl() {
		super();
		CifrasControlService service = new CifrasControlService();
		setCifrasControl(service.getCifrasControlImplPort());
		((BindingProvider) cifrasControl).getRequestContext().put(
				"com.sun.xml.internal.ws.connect.timeout", 10000);
		((BindingProvider) cifrasControl).getRequestContext().put(
				"com.sun.xml.internal.ws.request.timeout", 10000);
		this.cifrasControlList = new ArrayList<BeanCifrasControl>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.isban.cifrascontrol.servicio.cifrascontrol.BOCifrasControl#
	 * consultarCifrasControl(java.lang.String, java.lang.String,
	 * mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public int consultarCifrasControl(final String aplicativo,
			final String periodo, final ArchitechSessionBean sessionBean)
			throws BusinessException {
		this.cifrasControlList.clear();
		
		final BeanPistaAuditoria pistaAuditoria = new BeanPistaAuditoria();
		pistaAuditoria.setCodigoOperacion(ConstantesModuloIntegrador.COD_PA_CONSULTA_CIFRAS);
		pistaAuditoria.setClienteAfectado(ConstantesModuloIntegrador.COD_PA_NO_APLICA);
		
		this.info("Creando la solicitud para consultar las cifras de control");
		
		this.info("Consultando las cifras de control con el aplicativo:"
				+ aplicativo + " y el periodo:" + periodo);
		
		List<String> codigos = codigosAplicacion(aplicativo);
		
		for(String codigoAplicativo : codigos){
			final SolicitudCifrasControlDTO solicitud = new SolicitudCifrasControlDTO();
			solicitud.setAplicativo(codigoAplicativo);
			solicitud.setPeriodo(periodo);
			try {
				this.info("Consultando las cifras de control con el codigo de aplicativo:"+ codigoAplicativo);
				final List<CifrasControlDTO> cifrasControlDTO = cifrasControl
						.consultarCifrasControl(solicitud);
				final List<BeanCifrasControl> cifrasControlList = UtilGeneralCifras
						.establecerRegistros(cifrasControlDTO,
								CifrasControlDTO.class, BeanCifrasControl.class);
				this.cifrasControlList.addAll(cifrasControlList);
				this.info("Consulta de cifras de control realizada con exito para codigo: " + codigoAplicativo);
			} catch (CifrasControlException_Exception e) {
				this.error("Ocurrio un error al momento de consultar el web service"
						+ e.getFaultInfo());
				pistaAuditoria.setEstatusOperacion(ConstantesModuloIntegrador.COD_PA_OPERACION_NO_OK);
				boPistas.generaPistaAuditoria(pistaAuditoria, sessionBean);
				throw new BusinessException(
						ConstantesCifrasControl.ERROR_CONSULTAR_CIFRAS_CONTROL);
			}
		}
		
		pistaAuditoria.setEstatusOperacion(ConstantesModuloIntegrador.COD_PA_OPERACION_OK);
		boPistas.generaPistaAuditoria(pistaAuditoria, sessionBean);
		
		return this.cifrasControlList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.isban.cifrascontrol.servicio.cifrascontrol.BOCifrasControl#
	 * obtenerCifrasPorAplicativo(java.lang.String,
	 * mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanCifrasControl> obtenerCifrasPorAplicativo(
			final String aplicativo, final ArchitechSessionBean sessionBean) {
		final List<BeanCifrasControl> cifrasControlAplicativo = new ArrayList<BeanCifrasControl>();
		this.info("Iniciando la obtencion de las cifras para los aplicativos:"
				+ aplicativo);
		for (BeanCifrasControl beanCifrasControl : cifrasControlList) {
			if (aplicativo.equals(beanCifrasControl.getFase().trim())) {
				cifrasControlAplicativo.add(beanCifrasControl);
			}
		}
		this.info("El tamanio de la lista con las cifras de control para el aplicativo:"
				+ aplicativo + " es:" + cifrasControlAplicativo.size());
		return cifrasControlAplicativo;
	}
	
	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.cifrascontrol.BOCifrasControl#ejecutaConsultaInsidencias(java.lang.String, java.lang.String, java.lang.String, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanInsidenciaCifras> ejecutaConsultaInsidencias(String aplicativo, String mes, String anio,
			ArchitechSessionBean sessionBean) throws BusinessException {
		this.info("Se ejecuta la consulta de insidencias.");
		
		final BeanPistaAuditoria pistaAuditoria = new BeanPistaAuditoria();
		pistaAuditoria.setCodigoOperacion(ConstantesModuloIntegrador.COD_PA_CONSULTA_INCIDENCIAS);
		pistaAuditoria.setClienteAfectado(ConstantesModuloIntegrador.COD_PA_NO_APLICA);
		
		final List<String> codigosProductos = codigosAplicacion(aplicativo);
		
		final String fecha = anio + mes;
		this.info("Los codigos de producto medianta los cuales se realizara la consulta son: " + codigosProductos);
		this.info("La fecha que se utilizara en la consulta se muestra a continuacion: " + fecha);
		
		List<BeanInsidenciaCifras> respuestaConsultaIncidencias = new ArrayList<BeanInsidenciaCifras>();
		final String periodo = anio + "-" + mes;
		try{
			final List<DetalleCifrasControlDTO> listaTotalIncidencias = new ArrayList<DetalleCifrasControlDTO>();
			for(String app : codigosProductos){
				final SolicitudCifrasControlDTO solicitud = new SolicitudCifrasControlDTO();
				solicitud.setPeriodo(periodo);
				solicitud.setAplicativo(app);
				final List<DetalleCifrasControlDTO> respuetaConsulta = 
						cifrasControl.consultarIncidenciasCifrasControl(solicitud);
				listaTotalIncidencias.addAll(respuetaConsulta);
			}
			respuestaConsultaIncidencias = UtilGeneralCifras.establecerRegistros(listaTotalIncidencias, 
					DetalleCifrasControlDTO.class, BeanInsidenciaCifras.class);
		}catch(CifrasControlException_Exception e){
			showException(e, Level.ERROR);
			pistaAuditoria.setEstatusOperacion(ConstantesCifrasControl.ERROR_CONSULTAR_CIFRAS_CONTROL_DETALLE);
			boPistas.generaPistaAuditoria(pistaAuditoria, sessionBean);
			throw new BusinessException(ConstantesCifrasControl.ERROR_CONSULTAR_CIFRAS_CONTROL_DETALLE);
		}
		
		String []reglaOrdenamientoCifras = {"Origen", "CFD", "SAT", "EDC"};
		Collections.sort(respuestaConsultaIncidencias, new OrdenadorInsidenciaCifras(reglaOrdenamientoCifras));
		
		pistaAuditoria.setEstatusOperacion(ConstantesModuloIntegrador.COD_PA_OPERACION_OK);
		boPistas.generaPistaAuditoria(pistaAuditoria, sessionBean);
		
		return respuestaConsultaIncidencias;
	}


	/**
	 * Metodo que obtiene un objeto de tipo {@link CifrasControl}
	 *
	 * @return Un objeto de tipo {@link CifrasControl}
	 */
	public CifrasControl getCifrasControl() {
		return cifrasControl;
	}

	/**
	 * Metodo que establece un objeto de tipo {@link CifrasControl}
	 *
	 * @param cifrasControl
	 *            El objeto de tipo {@link CifrasControl} a establecer
	 */
	public void setCifrasControl(final CifrasControl cifrasControl) {
		this.cifrasControl = cifrasControl;
	}

	/**
	 * Metodo que obtiene el listado de las cifras de control
	 *
	 * @return Una lista de objetos de tipo {@link BeanCifrasControl}
	 */
	public List<BeanCifrasControl> getCifrasControlList() {
		return cifrasControlList;
	}

	/**
	 * Metodo para establecer el listado de cifras de control consultadas
	 *
	 * @param cifrasControlList Establecel el valor del campo cifrasControlList
	 */
	public void setCifrasControlList(
			final List<BeanCifrasControl> cifrasControlList) {
		this.cifrasControlList = cifrasControlList;
	}

	/**
	 * Dado el nombre de un aplicativo, recupera su lista de codigos asociados. En caso de no encontrar codigos retorna una lista vacia.
	 * @param nombreAplicacion Nombre de la aplicacion
	 * @return List<String>
	 * @throws BusinessException e
	 */
	private List<String> codigosAplicacion(String nombreAplicacion) throws BusinessException {
		this.info("El nombre del producto que sera consultado es: " + nombreAplicacion);
		String cantidadAplicativos = this.getConfigDeCmpAplicacion("NUMERO_PARES_PRODUCTO_CODIGO");
		int numeroAplicativos = 0;
		if(cantidadAplicativos != null){
			numeroAplicativos = Integer.parseInt(cantidadAplicativos);
		}else{
			throw new BusinessException(ConstantesCifrasControl.ERROR_CONSULTAR_CIFRAS_CONTROL_CONFIG);
		}
		for(int i = 1; i <= numeroAplicativos; i++){
			String aplicativoConfig = this.getConfigDeCmpAplicacion("NOMBRE_PRODUCTO" + i);
			if(nombreAplicacion.equals(aplicativoConfig)){
				String tramaCodigos = this.getConfigDeCmpAplicacion("CODIGO_PRODUCTO" + i);
				String[] tokens = tramaCodigos.split(",");
				return Arrays.asList(tokens);
			}
		}
		return new ArrayList<String>();
	}
	
}
