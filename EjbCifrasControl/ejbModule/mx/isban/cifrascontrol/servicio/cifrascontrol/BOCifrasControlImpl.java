package mx.isban.cifrascontrol.servicio.cifrascontrol;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.xml.ws.BindingProvider;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.agave.logging.Level;
import mx.isban.cifrascontrol.beans.cifrascontrol.BeanCifrasControl;
import mx.isban.cifrascontrol.beans.cifrascontrol.BeanInsidenciaCifras;
import mx.isban.cifrascontrol.util.cifrascontrol.ConstantesCifrasControl;
import mx.isban.cifrascontrol.util.general.OrdenadorInsidenciaCifras;
import mx.isban.cifrascontrol.util.general.UtilGeneralCifras;
import mx.isban.cifrascontrol.webservice.cifrascontrol.CifrasControl;
import mx.isban.cifrascontrol.webservice.cifrascontrol.CifrasControlDTO;
import mx.isban.cifrascontrol.webservice.cifrascontrol.CifrasControlException_Exception;
import mx.isban.cifrascontrol.webservice.cifrascontrol.CifrasControlService;
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
				throw new BusinessException(
						ConstantesCifrasControl.ERROR_CONSULTAR_CIFRAS_CONTROL);
			}
		}
		
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
		//Este mapa contiene la relacion entre los nombres de los aplicativos, y los codigos manejados en la parte batch.
		final Map<String, String> relacionProductoCodigo = new HashMap<String, String>();
		final String cadenaCantidadProductos = this.getConfigDeCmpAplicacion("NUMERO_PARES_PRODUCTO_CODIGO");
		final String mascaraOrigenYCfd = this.getConfigDeCmpAplicacion("MASCARA_ORIGEN_Y_CFD");
		final String mascaraSat = this.getConfigDeCmpAplicacion("MASCARA_SAT");
		final String rutaIncidencias = this.getConfigDeCmpAplicacion("RUTA_INCIDENCIAS");
		int cantidadProductos = 0;
		//Se valida que todas las configuraciones necesarias para esta funcionalidad hayan sido cargadas de manera exitosa.
		if(cadenaCantidadProductos == null || mascaraOrigenYCfd == null || mascaraSat == null || 
				rutaIncidencias == null){
			this.warn("Error al cargar la configuracion para la consulta de incidencias de Cifras de Control");
			throw new BusinessException(ConstantesCifrasControl.ERROR_CONFIGURACION_CONSULTA_INCIDENCIAS);
		}else{
			cantidadProductos = Integer.parseInt(cadenaCantidadProductos);
		}
		//Carga las propiedades que contiene la relacion entre el nombre de los pruductos y su codigo.
		for(int i = 1; i <= cantidadProductos; i++) {
			final String producto = this.getConfigDeCmpAplicacion("NOMBRE_PRODUCTO" + i);
			final String codigo = this.getConfigDeCmpAplicacion("CODIGO_PRODUCTO" + i);
			if(producto != null && codigo != null){
				relacionProductoCodigo.put(producto, codigo);
			}else{
				this.debug("Numero de Producto con error: " + i);
				this.warn("La configuracion para la consulta de incidencias de cifras de control no es correcta.");
				throw new BusinessException(ConstantesCifrasControl.ERROR_CONFIGURACION_CONSULTA_INCIDENCIAS);
			}
		}
		
		String codigoProducto = relacionProductoCodigo.get(aplicativo.toUpperCase());
		
		final String []tokens = codigoProducto.split(",");
		//Si el codigo de producto contiene valores separados por comas, estos se pasaran en la exprecion regular separados por pipes. 
		if(tokens.length > 1){
			final StringBuilder exprecion = new StringBuilder("(");
			for(int i = 0; i < tokens.length; i++){
				exprecion.append(tokens[i]);
				if(i < (tokens.length -1)){
					exprecion.append("|");
				}
			}
			exprecion.append(")");
			codigoProducto = exprecion.toString();
		}
		
		final String fecha = anio + mes;
		
		this.info("El codigo de producto medianta el cual se realizara la consulta es: " + codigoProducto);
		this.info("La fecha que se utilizara en la consulta se muestra a continuacion: " + fecha);
		final String mascaraOrigenYCfdEdit = mascaraOrigenYCfd.replace("CODIGOPRODUCTO", codigoProducto).replace("FECHA", fecha);
		this.info("La mascara para buscar incidencias de Origen y EDC es: " + mascaraOrigenYCfd);
		final List<File> listaOrigenYCfd = UtilGeneralCifras.filtrarListaArchivos(rutaIncidencias, mascaraOrigenYCfdEdit);
		final List<BeanInsidenciaCifras> insidenciasOrigenYCfd = new ArrayList<BeanInsidenciaCifras>();
		
		final String mascaraSatEdit = mascaraSat.replace("CODIGOPRODUCTO", codigoProducto).replace("FECHA", fecha);
		this.info("La mascara para buscar incidencias de SAT es: " + mascaraSatEdit);
		final List<File> listaSat = UtilGeneralCifras.filtrarListaArchivos(rutaIncidencias, mascaraSatEdit);
		final List<BeanInsidenciaCifras> incidenciasSat = new ArrayList<BeanInsidenciaCifras>();
		
		try{
			for(File coincidencia : listaOrigenYCfd){
				final BeanInsidenciaCifras incidencia = UtilGeneralCifras.fabricaBeanInsidencia(coincidencia);
				incidencia.setProducto(aplicativo);
				insidenciasOrigenYCfd.add(incidencia);
			}
			
			for(File coincidencia : listaSat){
				final BeanInsidenciaCifras incidencia = UtilGeneralCifras.fabricaBeanIncidenciaSat(coincidencia);
				incidencia.setProducto(aplicativo);
				incidenciasSat.add(incidencia);
			}
		}catch(ParseException e){
			showException(e, Level.WARN);
			throw new BusinessException(ConstantesCifrasControl.ERROR_PROCESA_ARCHIVOS_CONSULTA_INCIDENCIAS);
		}
		
		//Concatena las dos listas de incidencias SAT y Origen-EDC.
		final List<BeanInsidenciaCifras> listaFinalIncidencias = new ArrayList<BeanInsidenciaCifras>();
		for(BeanInsidenciaCifras incidencia : insidenciasOrigenYCfd){
			listaFinalIncidencias.add(incidencia);
		}
		
		for(BeanInsidenciaCifras incidencia : incidenciasSat){
			listaFinalIncidencias.add(incidencia);
		}
		
		String []ordenParametros = {"ORIGEN", "SAT", "EDC"};
		
		Collections.sort(listaFinalIncidencias, new OrdenadorInsidenciaCifras(ordenParametros));
		
		return listaFinalIncidencias;
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
	 * @param cifrasControlList
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
