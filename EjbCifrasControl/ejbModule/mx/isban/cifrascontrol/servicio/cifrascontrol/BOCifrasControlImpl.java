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
import mx.isban.cifrascontrol.beans.cifrascontrol.BeanDetalleCifrasControl;
import mx.isban.cifrascontrol.beans.producto.BeanProducto;
import mx.isban.cifrascontrol.util.cifrascontrol.ConstantesCifrasControl;
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
     * @see Architech#Architech()
     */
    public BOCifrasControlImpl() {
        super();
        CifrasControlService service = new CifrasControlService();
        setCifrasControl(service.getCifrasControlImplPort());
        ((BindingProvider)cifrasControl).getRequestContext().put("com.sun.xml.internal.ws.connect.timeout", 10000);
        ((BindingProvider)cifrasControl).getRequestContext().put("com.sun.xml.internal.ws.request.timeout", 10000);
        this.cifrasControlList = new ArrayList<BeanCifrasControl>();
    }

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.cifrascontrol.BOCifrasControl#obtenerProductos(mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanProducto> obtenerProductos(ArchitechSessionBean sessionBean)
			throws BusinessException {
		final List<BeanProducto> listaProductos = new ArrayList<BeanProducto>();

		BeanProducto captacionVista = new BeanProducto();
		captacionVista.setDescripcion("CAPTACION VISTA Y CHEQUES");
		captacionVista.setIdProducto("captavista");
		listaProductos.add(captacionVista);	
		BeanProducto captacionPlazo = new BeanProducto();
		captacionPlazo.setDescripcion("CAPTACION PLAZO");
		captacionPlazo.setIdProducto("captaplazo");
		listaProductos.add(captacionPlazo);
		BeanProducto captacionNomina = new BeanProducto();
		captacionNomina.setDescripcion("CAPTACION NOMINA");
		captacionNomina.setIdProducto("captaNomi");
		listaProductos.add(captacionNomina);
		BeanProducto captacion = new BeanProducto();
		captacion.setDescripcion("CAPTACION UDIS");
		captacion.setIdProducto("capta");
		listaProductos.add(captacion);
		BeanProducto fondos = new BeanProducto();
		fondos.setDescripcion("FONDOS");
		fondos.setIdProducto("fondos");
		listaProductos.add(fondos);
		BeanProducto pampaban = new BeanProducto();
		pampaban.setDescripcion("TARJETAS PAMPA BANCO");
		pampaban.setIdProducto("TARJETAS PAMPA");
		listaProductos.add(pampaban);
		BeanProducto pampasof = new BeanProducto();
		pampasof.setDescripcion("TARJETAS PAMPA SOFOM");
		pampasof.setIdProducto("pampasof");
		listaProductos.add(pampasof);
		BeanProducto cartera = new BeanProducto();
		cartera.setDescripcion("CARTERA");
		cartera.setIdProducto("CARTERA");
		listaProductos.add(cartera);
		BeanProducto fiduciario = new BeanProducto();
		fiduciario.setDescripcion("FIDUCIARIO");
		fiduciario.setIdProducto("fiduciario");
		listaProductos.add(fiduciario);
		BeanProducto mexder21 = new BeanProducto();
		mexder21.setDescripcion("MEXDER 21");
		mexder21.setIdProducto("mexder21");
		listaProductos.add(mexder21);
		BeanProducto mexder22 = new BeanProducto();
		mexder22.setDescripcion("MEXDER 22");
		mexder22.setIdProducto("captavista");
		listaProductos.add(mexder22);
		BeanProducto chicago = new BeanProducto();
		chicago.setDescripcion("MEXDER CHICAGO");
		chicago.setIdProducto("chicago");
		listaProductos.add(chicago);
		BeanProducto opicsbanco = new BeanProducto();
		opicsbanco.setDescripcion("OPICS BANCO");
		opicsbanco.setIdProducto("opicsbanco");
		listaProductos.add(opicsbanco);
		BeanProducto opicscbs = new BeanProducto();
		opicscbs.setDescripcion("OPICS CASA DE BOLSA ");
		opicscbs.setIdProducto("opicscbs");
		listaProductos.add(opicscbs);
		BeanProducto opicsGes = new BeanProducto();
		opicsGes.setDescripcion("OPICS GESTORA");
		opicsGes.setIdProducto("captavista");
		listaProductos.add(opicsGes);
		BeanProducto opicsfac = new BeanProducto();
		opicsfac.setDescripcion("OPICS FACTORAJE");
		opicsfac.setIdProducto("opicsfac");
		listaProductos.add(opicsfac);
		BeanProducto saf = new BeanProducto();
		saf.setDescripcion("SAF PPR");
		saf.setIdProducto("saf");
		listaProductos.add(saf);
		BeanProducto sofSanta = new BeanProducto();
		sofSanta.setDescripcion("SOFIA SANTANDER");
		sofSanta.setIdProducto("sofsanta");
		listaProductos.add(sofSanta);
		BeanProducto sofSer = new BeanProducto();
		sofSer.setDescripcion("SOFIA SERFIN");
		sofSer.setIdProducto("sofser");
		listaProductos.add(sofSer);
		BeanProducto activoAltair = new BeanProducto();
		activoAltair.setDescripcion("ACTIVO ALTAIR");
		activoAltair.setIdProducto("activoAltair");
		listaProductos.add(activoAltair);
		return listaProductos;																
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.cifrascontrol.BOCifrasControl#consultarCifrasControl(java.lang.String, java.lang.String, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public int consultarCifrasControl(String aplicativo, String periodo,
			ArchitechSessionBean sessionBean) throws BusinessException {
		this.info("Creando la solicitud para consultar las cifras de control");
		final SolicitudCifrasControlDTO solicitud = new SolicitudCifrasControlDTO();
		solicitud.setAplicativo(aplicativo);
		solicitud.setPeriodo(periodo);
		try {
			this.info("Consultando las cifras de control con el aplicativo:"+aplicativo+" y el periodo:"+periodo);
			final List<CifrasControlDTO> cifrasControlDTO = cifrasControl.consultarCifrasControl(solicitud);
			final List<BeanCifrasControl> cifrasControlList = UtilGeneralCifras.establecerRegistros(cifrasControlDTO, CifrasControlDTO.class, BeanCifrasControl.class);
			this.cifrasControlList = cifrasControlList;
			this.info("Consulta de cifras de control realizada con exito");
		} catch (CifrasControlException_Exception e) {
			this.error("Ocurrio un error al momento de consultar el web service"+e.getFaultInfo());
			throw new BusinessException(ConstantesCifrasControl.ERROR_CONSULTAR_CIFRAS_CONTROL);
		}
		return this.cifrasControlList.size();
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.cifrascontrol.BOCifrasControl#obtenerCifrasPorAplicativo(java.lang.String, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanCifrasControl> obtenerCifrasPorAplicativo(
			String aplicativo, ArchitechSessionBean sessionBean) {
		final List<BeanCifrasControl> cifrasControlAplicativo = new ArrayList<BeanCifrasControl>();
		this.info("Iniciando la obtencion de las cifras para los aplicativos:"+aplicativo);
		for (BeanCifrasControl beanCifrasControl : cifrasControlList) {
			if(aplicativo.equals(beanCifrasControl.getFase().trim())){
				cifrasControlAplicativo.add(beanCifrasControl);
			}
		}
		this.info("El tamanio de la lista con las cifras de control para el aplicativo:"+aplicativo+" es:"+cifrasControlAplicativo.size());
		return cifrasControlAplicativo;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.cifrascontrol.BOCifrasControl#obtenerDetalleCifrasControl(java.lang.String, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanDetalleCifrasControl> obtenerDetalleCifrasControl(
			String aplicativo, String periodo, ArchitechSessionBean sessionBean)
			throws BusinessException {
		final SolicitudCifrasControlDTO solicitud = new SolicitudCifrasControlDTO();
		solicitud.setAplicativo(aplicativo);
		solicitud.setPeriodo(periodo);
		final List<DetalleCifrasControlDTO> detalleCifrasControl;
		List<BeanDetalleCifrasControl> beanDetalleCifrasList = new ArrayList<BeanDetalleCifrasControl>();
		try {
			detalleCifrasControl = cifrasControl.consultarDetalleCifrasControl(solicitud);
			beanDetalleCifrasList = UtilGeneralCifras.establecerRegistros(detalleCifrasControl, DetalleCifrasControlDTO.class, BeanDetalleCifrasControl.class);
		} catch (CifrasControlException_Exception e) {
			this.error("Error al consultar el web service"+e.getFaultInfo());
			throw new BusinessException(ConstantesCifrasControl.ERROR_CONSULTAR_CIFRAS_CONTROL_DETALLE);
		}
		return beanDetalleCifrasList;
	}
	

	/**
	 * Metodo que obtiene un objeto de tipo {@link CifrasControl}
	 * @return Un objeto de tipo {@link CifrasControl}
	 */
	public CifrasControl getCifrasControl() {
		return cifrasControl;
	}

	/**
	 * Metodo que establece un objeto de tipo {@link CifrasControl}
	 * @param cifrasControl El objeto de tipo {@link CifrasControl} a establecer
	 */
	public void setCifrasControl(CifrasControl cifrasControl) {
		this.cifrasControl = cifrasControl;
	}


	/**
	 * Metodo que obtiene el listado de las cifras de control
	 * @return Una lista de objetos de tipo {@link BeanCifrasControl}
	 */
	public List<BeanCifrasControl> getCifrasControlList() {
		return cifrasControlList;
	}

	/**
	 * Metodo para establecer el listado de cifras de control consultadas
	 * @param cifrasControlList
	 */
	public void setCifrasControlList(List<BeanCifrasControl> cifrasControlList) {
		this.cifrasControlList = cifrasControlList;
	}


}
