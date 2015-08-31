package mx.isban.cifrascontrol.servicio.cifrascontrol;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.xml.ws.BindingProvider;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.beans.cifrascontrol.BeanCifrasControl;
import mx.isban.cifrascontrol.util.cifrascontrol.ConstantesCifrasControl;
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
		this.info("Creando la solicitud para consultar las cifras de control");
		final SolicitudCifrasControlDTO solicitud = new SolicitudCifrasControlDTO();
		solicitud.setAplicativo(aplicativo);
		solicitud.setPeriodo(periodo);
		try {
			this.info("Consultando las cifras de control con el aplicativo:"
					+ aplicativo + " y el periodo:" + periodo);
			final List<CifrasControlDTO> cifrasControlDTO = cifrasControl
					.consultarCifrasControl(solicitud);
			final List<BeanCifrasControl> cifrasControlList = UtilGeneralCifras
					.establecerRegistros(cifrasControlDTO,
							CifrasControlDTO.class, BeanCifrasControl.class);
			this.cifrasControlList = cifrasControlList;
			this.info("Consulta de cifras de control realizada con exito");
		} catch (CifrasControlException_Exception e) {
			this.error("Ocurrio un error al momento de consultar el web service"
					+ e.getFaultInfo());
			throw new BusinessException(
					ConstantesCifrasControl.ERROR_CONSULTAR_CIFRAS_CONTROL);
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

}
