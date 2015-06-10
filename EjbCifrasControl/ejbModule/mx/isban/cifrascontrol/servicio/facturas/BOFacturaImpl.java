/**************************************************************
* Queretaro, Qro Mayo 2015
*
* La redistribucion y el uso en formas fuente y binario, 
* son responsabilidad del propietario.
* 
* Este software fue elaborado en @Everis
* 
* Para mas informacion, consulte <www.everis.com/mexico>
***************************************************************/
package mx.isban.cifrascontrol.servicio.facturas;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.beans.facturas.BeanFactura;
import mx.isban.cifrascontrol.beans.general.BeanProducto;
import mx.isban.cifrascontrol.webservice.cifrascontrol.CifrasControl;
import mx.isban.cifrascontrol.webservice.cifrascontrol.CifrasControlException_Exception;
import mx.isban.cifrascontrol.webservice.cifrascontrol.CifrasControlService;
import mx.isban.cifrascontrol.webservice.cifrascontrol.FacturaDTO;
import mx.isban.cifrascontrol.webservice.cifrascontrol.SolicitudFacturaDTO;

/**
* Clase BOfacturaImpl
* 
* <P>Clase de tipo SessionBean, la cual implementa la interfaz BOFactura, 
* la cual define los metodos necesarios para realizar la logica de negocio
* relacionada a la consulta de:
* <ul>
* <li>Productos</li>
* <li>Facturas</li>
* <li>Notas de credito </li>
* <li>Divisas</li>
* <li>Recibos deducibles</li>
* </ul>
* @author Everis
* @version 1.0
* @see www.everis.com/mexico
* 
*/
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BOFacturaImpl extends Architech implements BOFactura {
       
    /**
	 *Numero de la clase serializada 
	 */
	private static final long serialVersionUID = 4332825216520593352L;

	/**
     * @see Architech#Architech()
     */
    public BOFacturaImpl() {
        super();
    }

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.facturas.BOFactura#obtenerProductos(mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanProducto> obtenerProductos(ArchitechSessionBean sessionBean)
			throws BusinessException {
		//Se realiza simulacion de productos, para llenar listas de productos
		List<BeanProducto> productosList = new ArrayList<BeanProducto>();
		BeanProducto universia = new BeanProducto();
		universia.setIdProducto("universia");
		universia.setDescripcion("Universia México, S.A. de C.V.");
		productosList.add(universia);
		BeanProducto santanderGlobal = new BeanProducto();
		santanderGlobal.setIdProducto("global");
		santanderGlobal.setDescripcion("Santander Global Facilities, S.A. de C.V.");
		productosList.add(santanderGlobal);
		BeanProducto santaserf = new BeanProducto();
		santaserf.setIdProducto("santaserf");
		santaserf.setDescripcion("Instituto Santander Serfin, A.C.");
		productosList.add(santaserf);
		BeanProducto sercorpo = new BeanProducto();
		sercorpo.setIdProducto("serCorpo");
		sercorpo.setDescripcion("WIM Servicios Corporativos, S.A. de C.V.");
		productosList.add(sercorpo);
		BeanProducto financiera = new BeanProducto();
		financiera.setIdProducto("financiera");
		financiera.setDescripcion("FINANCIERA ALCANZA, S.A. DE C.V., SOFOM ER");
		productosList.add(financiera);
		BeanProducto gesmex = new BeanProducto();
		gesmex.setIdProducto("gesmex");
		gesmex.setDescripcion("GESBAN México Servicios Administrativos Globales, S.A. de C.V.");
		productosList.add(gesmex);
		BeanProducto direccion = new BeanProducto();
		direccion.setIdProducto("direccion");
		direccion.setDescripcion("Dirección Estratega, S.C.");
		productosList.add(direccion);
		BeanProducto alfin = new BeanProducto();
		alfin.setIdProducto("alfin");
		alfin.setDescripcion("Comercializadora Al-fin, S.A. de C.V.");
		productosList.add(alfin);
		BeanProducto estratega = new BeanProducto();
		estratega.setIdProducto("estratega");
		estratega.setDescripcion("Asesoria Estratega, S.C.");
		productosList.add(estratega);
		BeanProducto global = new BeanProducto();
		global.setIdProducto("global");
		global.setDescripcion("Santander Global Property M&eacute;xico, S.A. de C.V.");
		productosList.add(global);
		BeanProducto seguros = new BeanProducto();
		seguros.setIdProducto("seguros");
		seguros.setDescripcion("Seguros Santander, SA , Grupo Financiero Santander");
		productosList.add(seguros);
		BeanProducto gestion = new BeanProducto();
		gestion.setIdProducto("gestion");
		gestion.setDescripcion("Gesti&oacute;n Santander, S.A. de C.V");
		productosList.add(gestion);
		BeanProducto isban = new BeanProducto();
		isban.setIdProducto("isban");
		isban.setDescripcion("ISBAN México, S.A. de C.V.");
		productosList.add(isban);
		BeanProducto produban = new BeanProducto();
		produban.setIdProducto("produban");
		produban.setDescripcion("Produban Servicios Informáticos Generales, S.L.");
		productosList.add(produban);
		BeanProducto geoban = new BeanProducto();
		geoban.setIdProducto("geoban");
		geoban.setDescripcion("Geoban, S.A.");
		productosList.add(geoban);
		BeanProducto alcaza = new BeanProducto();
		alcaza.setIdProducto("alcaza");
		alcaza.setDescripcion("Grupo Alcanza, S.A. de C.V.");
		productosList.add(alcaza);
		BeanProducto promosan = new BeanProducto();
		promosan.setIdProducto("promosan");
		promosan.setDescripcion("Promociones y Servicios Santiago, S.A. de C.V.");
		productosList.add(promosan);
		BeanProducto promoMonte = new BeanProducto();
		promoMonte.setIdProducto("promoMonte");
		promoMonte.setDescripcion("Promociones y Servicios Monterrey, S.A. de C.V.");
		productosList.add(promoMonte);
		BeanProducto promoPola = new BeanProducto();
		promoPola.setIdProducto("promoPola");
		promoPola.setDescripcion("Promociones y Servicios Polanco, S.A. de C.V.");
		productosList.add(promoPola);
		BeanProducto santaBanca = new BeanProducto();
		santaBanca.setIdProducto("santaBanca");
		santaBanca.setDescripcion("Banco Santander (M&eacute;xico), S.A. Instituci&oacute;n de Banca Multiple Grupo Financiero Santander");
		productosList.add(santaBanca);
		BeanProducto serfide = new BeanProducto();
		serfide.setIdProducto("serfide");
		serfide.setDescripcion("Banca Serfin, S.A. Fideicomiso 100740 ");
		productosList.add(serfide);
		BeanProducto consumo = new BeanProducto();
		consumo.setIdProducto("consumo");
		consumo.setDescripcion("Santander Consumo, S.A. DE C.V. SOFOM ER");
		productosList.add(consumo);
		BeanProducto santaMexico = new BeanProducto();
		santaMexico.setIdProducto("santaMexico");
		santaMexico.setDescripcion("Banco Santander (México), S.A. No. 1 ");
		productosList.add(santaMexico);
		BeanProducto casaBolsa = new BeanProducto();
		casaBolsa.setIdProducto("casabolsa");
		casaBolsa.setDescripcion("Casa de Bolsa Santander, S.A DE C.V");
		productosList.add(casaBolsa);
		BeanProducto santander = new BeanProducto();
		santander.setIdProducto("SANTANDER");
		santander.setDescripcion("Grupo Financiero Santander, S.A.B. de C.V.");
		productosList.add(santander);
		BeanProducto fideicomiso = new BeanProducto();
		fideicomiso.setIdProducto("fideicomiso");
		fideicomiso.setDescripcion("Fideicomiso GFSSLPT, Banca Serfin, S.A");
		productosList.add(fideicomiso);
		BeanProducto confiFact = new BeanProducto();
		confiFact.setIdProducto("ConfiFact");
		confiFact.setDescripcion("Confirming y Factoraje");
		productosList.add(confiFact);
		return productosList;
	}

	
	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.facturas.BOFactura#consultarFacturas(java.lang.String, java.lang.String, java.lang.String, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanFactura> consultarFacturas(String aplicativo,
			String periodo, String tipoFactura, ArchitechSessionBean sessionBean)throws BusinessException {
		this.info("Iniciando la consulta de las facturas para el tipo:"+tipoFactura);
		this.info("Los parametros para realizar la busqueda son:"+aplicativo+","+periodo);
		this.info("Creando los objetos que consumiran el web service...");
		CifrasControlService service = new CifrasControlService();
		CifrasControl cifrasControl = service.getCifrasControlImplPort();
		SolicitudFacturaDTO solicitudFactura = new SolicitudFacturaDTO();
		solicitudFactura.setAplicativo(aplicativo);
		solicitudFactura.setPeriodo(periodo);
		solicitudFactura.setTipoFactura(tipoFactura);
		List<FacturaDTO> facturaList = new ArrayList<FacturaDTO>();
		List<BeanFactura> beanFacturaList = new ArrayList<BeanFactura>();
		try {
			facturaList = cifrasControl.consultarFacturasCifrasControl(solicitudFactura);
			if(!facturaList.isEmpty()){
				beanFacturaList = establecerRegistros(facturaList, FacturaDTO.class);
			}
			this.info("Consulta del servicio web, realizada correctamente");
		} catch (CifrasControlException_Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
		return beanFacturaList;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.facturas.BOFactura#obtenerFacturasCorrectas(java.util.List, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanFactura> obtenerFacturasCorrectas(List<BeanFactura> totalFacturas,ArchitechSessionBean sessionBean){
		List<BeanFactura> facturasCorrectas = new ArrayList<BeanFactura>();
		for (BeanFactura beanFactura : totalFacturas) {
			String indicadorIncidente = beanFactura.getFacturaIncidente();
			if(null!=indicadorIncidente && "0".equals(indicadorIncidente)){
				facturasCorrectas.add(beanFactura);
			}
		}
		
		return obtenerSumatoriaTotales(facturasCorrectas);
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.facturas.BOFactura#obtenerFacturasIncorrectas(java.util.List, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanFactura> obtenerFacturasIncorrectas(List<BeanFactura> totalFacturas,ArchitechSessionBean sessionBean){
		List<BeanFactura> facturasIncorrectas = new ArrayList<BeanFactura>();
		for (BeanFactura beanFactura : totalFacturas) {
			String indicadorIncidente = beanFactura.getFacturaIncidente();
			if(null!=indicadorIncidente && "1".equals(indicadorIncidente)){
				facturasIncorrectas.add(beanFactura);
			}
		}
		
		return obtenerSumatoriaTotales(facturasIncorrectas);
	}
	
	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.facturas.BOFactura#obtenerFacturasRecibosGenerados(java.util.List, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanFactura> obtenerFacturasRecibosGenerados(
			List<BeanFactura> totalRecibos, ArchitechSessionBean sessionBean) {
		List<BeanFactura> recibosGenerados = new ArrayList<BeanFactura>();
		for (BeanFactura beanFactura : totalRecibos) {
			if("0".equals(beanFactura.getFacturaIncidente().trim())){
				recibosGenerados.add(beanFactura);
			}
		}
		if(recibosGenerados.isEmpty()){
			BeanFactura factura = new BeanFactura();
			factura.setCantidadRecibos("0");
			factura.setImporte("0.00");
			recibosGenerados.add(factura);
		}
		return recibosGenerados;
	}
	
	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.facturas.BOFactura#obtenerFacturasRecibosCancelados(java.util.List, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanFactura> obtenerFacturasRecibosCancelados(
			List<BeanFactura> totalRecibos, ArchitechSessionBean sessionBean) {
		List<BeanFactura> recibosCancelados = new ArrayList<BeanFactura>();
		for (BeanFactura beanFactura : totalRecibos) {
			if("1".equals(beanFactura.getFacturaIncidente().trim())){
				recibosCancelados.add(beanFactura);
			}
		}
		if(recibosCancelados.isEmpty()){
			BeanFactura factura = new BeanFactura();
			factura.setCantidadRecibos("0");
			factura.setImporte("0.00");
			recibosCancelados.add(factura);
		}
		return recibosCancelados;
	}
	
	/**
	 * Metodo encargado de establecer el resultado de la consulta del web service 
	 * cifras de control.- Consulta de facturas,  en las propiedades definidas
	 * para el Bean {@link BeanFactura}
	 * 
	 * @param listaResultado El listado de la consulta al web service
	 * @param clase El tipo de clase obtenido como resultado de la consulta al web service
	 * @return Un listado de objetos de tipo {@link BeanFactura}
	 */
	private <T> List<BeanFactura> establecerRegistros(List<T> listaResultado, Class<T> clase){
		List<BeanFactura> lista = new ArrayList<BeanFactura>();
		try {
			
			for (T objeto : listaResultado) {
				final BeanFactura factura = new BeanFactura();
				final Method[] metodosBean = factura.getClass().getDeclaredMethods();
				final Method[] metodosDTO = clase.getDeclaredMethods();
				for (int i = 0; i < metodosBean.length; i++) {
					if(metodosBean[i].getName().startsWith("set")){
						String nombreMetodoBean = metodosBean[i].getName().substring(3);
						for (int j = 0; j < metodosDTO.length; j++) {
							if(metodosDTO[j].getName().startsWith("get")){
								String nombreMetodoDTO = metodosDTO[j].getName().substring(3);
								if(nombreMetodoBean.equalsIgnoreCase(nombreMetodoDTO)){
									metodosBean[i].invoke(factura, metodosDTO[j].invoke(objeto));
								}
							}
						}
					}
				}
				lista.add(factura);
			}
		} catch (IllegalAccessException e) {
			this.error("Error de tipo IllegalAccessException"+e.getMessage());
		} catch (IllegalArgumentException e) {
			this.error("Error de tipo IllegalArgumentException"+e.getMessage());
		} catch (InvocationTargetException e) {
			this.error("Error de tipo InvocationTargetException"+e.getMessage());
		}
		return lista;
	}

	private List<BeanFactura> obtenerSumatoriaTotales(List<BeanFactura> facturasCorrectas){
		List<BeanFactura> facturasVigentesTasa = new ArrayList<BeanFactura>();
		List<BeanFactura> facturasVigentesSinTasa = new ArrayList<BeanFactura>();
		List<BeanFactura> facturasVigentesExcentas = new ArrayList<BeanFactura>();
		String producto = "";
		for (BeanFactura beanFactura : facturasCorrectas) {
			producto = beanFactura.getAplicativo();
			if(null == beanFactura.getIva() || "".equals(beanFactura.getIva().trim())){
				facturasVigentesExcentas.add(beanFactura);
			}else{
				if("0.00".equals(beanFactura.getIva().trim())){
					facturasVigentesSinTasa.add(beanFactura);
				}else{
					facturasVigentesTasa.add(beanFactura);
				}
			}
		}
		
		List<BeanFactura> facturasList = new ArrayList<BeanFactura>();
			facturasList.add(obtenerFacturaTotales(facturasVigentesTasa,"16 %",producto));
			facturasList.add(obtenerFacturaTotales(facturasVigentesSinTasa,"0 %",producto));
			facturasList.add(obtenerFacturaTotales(facturasVigentesExcentas,"Exentas",producto));
		return facturasList;
	}
	
	private BeanFactura obtenerFacturaTotales(List<BeanFactura> listaFacturas,String valorIva, String producto){
		int cantidadTotalFacturas = 0;
		BigDecimal sumatoriaSubTotales = new BigDecimal(0);
		BigDecimal sumatoriaTotales = new BigDecimal(0);
		String productoFinal = producto;
		for (BeanFactura beanFactura : listaFacturas) {
			String cantidadFacturas = beanFactura.getCantidadFacturas();
			if(null == cantidadFacturas || "".equals(cantidadFacturas)){
				cantidadFacturas = "0";
			}
			cantidadTotalFacturas = cantidadTotalFacturas + Integer.parseInt(cantidadFacturas);
			String subTotal = beanFactura.getSubTotal();
			if(null == subTotal || "".equals(subTotal)){
				subTotal = "0";
			}
			sumatoriaSubTotales = sumatoriaSubTotales.add(new BigDecimal(subTotal));
			String total = beanFactura.getTotalImpuestos();
			if(null == total || "".equals(total)){
				total = "0";
			}
			sumatoriaTotales = sumatoriaTotales.add(new BigDecimal(total));
		}
		this.info("Total de facturas:"+cantidadTotalFacturas);
		DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
		decimalFormat.applyPattern("#,##0.00");
		String resultSubTotales = decimalFormat.format(sumatoriaSubTotales.setScale(2,RoundingMode.CEILING));
		this.info("Sumatoria subtotal:"+resultSubTotales);
		String resultTotal = decimalFormat.format(sumatoriaTotales.setScale(2,RoundingMode.CEILING));
		this.info("Sumatoria de totales:"+resultTotal);
		BeanFactura factura = new BeanFactura();
		factura.setAplicativo(productoFinal);
		factura.setTotalImpuestos(resultTotal);
		factura.setSubTotal(resultSubTotales);
		factura.setIva(valorIva);
		factura.setCantidadFacturas(Integer.toString(cantidadTotalFacturas));
		return factura;
	}

	

	
	
}
