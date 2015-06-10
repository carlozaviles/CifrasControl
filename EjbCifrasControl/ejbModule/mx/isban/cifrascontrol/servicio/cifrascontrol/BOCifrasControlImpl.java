package mx.isban.cifrascontrol.servicio.cifrascontrol;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.beans.cifrascontrol.BeanCifrasControl;
import mx.isban.cifrascontrol.beans.cifrascontrol.BeanDetalleCifrasControl;
import mx.isban.cifrascontrol.beans.general.BeanProducto;
import mx.isban.cifrascontrol.webservice.cifrascontrol.CifrasControl;
import mx.isban.cifrascontrol.webservice.cifrascontrol.CifrasControlService;

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

	private CifrasControl cifrasControl;
	
	/**
     * @see Architech#Architech()
     */
    public BOCifrasControlImpl() {
        super();
        CifrasControlService service = new CifrasControlService();
        setCifrasControl(service.getCifrasControlImplPort());
    }

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.cifrascontrol.BOCifrasControl#obtenerProductos(mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanProducto> obtenerProductos(ArchitechSessionBean sessionBean)
			throws BusinessException {
		List<BeanProducto> listaProductos = new ArrayList<BeanProducto>();

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
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.cifrascontrol.BOCifrasControl#obtenerCifrasAplicativoOrigen(mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanCifrasControl> obtenerCifrasAplicativoOrigen(
			ArchitechSessionBean sessionBean) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.cifrascontrol.BOCifrasControl#obtenerCifrasInterfase(mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanCifrasControl> obtenerCifrasInterfase(
			ArchitechSessionBean sessionBean) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.cifrascontrol.BOCifrasControl#obtenerCifrasSat(mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanCifrasControl> obtenerCifrasSat(
			ArchitechSessionBean sessionBean) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.cifrascontrol.BOCifrasControl#obtenerCifrasCuentasEDC(mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanCifrasControl> obtenerCifrasCuentasEDC(
			ArchitechSessionBean sessionBean) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.cifrascontrol.BOCifrasControl#obtenerDetalleCifrasControl(java.lang.String, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanDetalleCifrasControl> obtenerDetalleCifrasControl(
			String periodo, ArchitechSessionBean sessionBean)
			throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Metodo que obtiene un objeto de tipo {@link CifrasControl}
	 * @return Un objeto de tipo cifras de control 
	 */
	public CifrasControl getCifrasControl() {
		return cifrasControl;
	}

	/**
	 * @param cifrasControl the cifrasControl to set
	 */
	public void setCifrasControl(CifrasControl cifrasControl) {
		this.cifrasControl = cifrasControl;
	}

}
