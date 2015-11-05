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

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.bean.auditoria.BeanPistaAuditoria;
import mx.isban.cifrascontrol.beans.facturas.BeanFactura;
import mx.isban.cifrascontrol.beans.facturas.BeanReporteFacturas;
import mx.isban.cifrascontrol.servicio.auditoria.BOPistasAuditoria;
import mx.isban.cifrascontrol.util.general.ConstantesModuloIntegrador;
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
	 * Referencia al objeto de la capa de negocio BOPistasAuditoria.
	 */
	@EJB
	private BOPistasAuditoria boPistas;

	/**
     * @see Architech#Architech()
     */
    public BOFacturaImpl() {
        super();
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
			ejecutaEnvioPistaAuditoria(tipoFactura, ConstantesModuloIntegrador.COD_PA_OPERACION_NO_OK, sessionBean);
			throw new BusinessException(e.getMessage());
		}
		ejecutaEnvioPistaAuditoria(tipoFactura, ConstantesModuloIntegrador.COD_PA_OPERACION_OK, sessionBean);
		
		return beanFacturaList;
	}
	
	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.facturas.BOFactura#obtenerReporteFacturas(java.lang.String, java.lang.String, java.lang.String, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanReporteFacturas> obtenerReporteFacturas(String aplicativo, String periodo, 
			String tipoFactura, ArchitechSessionBean sessionBean) throws BusinessException {
		final List<BeanFactura> totalFacturas = this.consultarFacturas(aplicativo, periodo, tipoFactura, sessionBean);
		final List<BeanReporteFacturas> infoReporteFacturas = new ArrayList<BeanReporteFacturas>();
		if(totalFacturas.size() > 0){
			final List<BeanFactura> facturasCorrectas = this.obtenerFacturasCorrectas(totalFacturas, sessionBean);
			final List<BeanFactura> facturasIncorrectas = this.obtenerFacturasIncorrectas(totalFacturas, sessionBean);
			
			for(int i = 0; i < 3; i++){
				BeanReporteFacturas beanReporte = new BeanReporteFacturas();
				beanReporte.setFacturasCorrectas(facturasCorrectas.get(i).getCantidadFacturas());
				beanReporte.setSubtotalFactCorrectas(facturasCorrectas.get(i).getSubTotal());
				beanReporte.setIvaFactCorrectas(facturasCorrectas.get(i).getIva());
				beanReporte.setTotalFactCorrectas(facturasCorrectas.get(i).getTotalImpuestos());
				
				beanReporte.setFacturasCanceladas(facturasIncorrectas.get(i).getCantidadFacturas());
				beanReporte.setSubtotalFactCanceladas(facturasIncorrectas.get(i).getSubTotal());
				beanReporte.setIvaFactCanceladas(facturasIncorrectas.get(i).getIva());
				beanReporte.setTotalFactCanceladas(facturasIncorrectas.get(i).getTotalImpuestos());
				infoReporteFacturas.add(beanReporte);
			}
		}
		return infoReporteFacturas;
	}
	
	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.facturas.BOFactura#obtenerReporteRecibos(java.lang.String, java.lang.String, java.lang.String, mx.isban.agave.commons.beans.ArchitechSessionBean)
	 */
	@Override
	public List<BeanReporteFacturas> obtenerReporteRecibos(String aplicativo, String periodo, String tipoRecibo, 
			ArchitechSessionBean sessionBean) throws BusinessException {
		final List<BeanFactura> totalRecibos = this.consultarFacturas(aplicativo, periodo, tipoRecibo, sessionBean);
		final List<BeanReporteFacturas> reporteRecibos = new ArrayList<BeanReporteFacturas>();
		if(!totalRecibos.isEmpty()){
			final List<BeanFactura> recibosGenerados = this.obtenerFacturasRecibosGenerados(totalRecibos, sessionBean);
			final List<BeanFactura> recibosCancelados = this.obtenerFacturasRecibosCancelados(totalRecibos, sessionBean);
			int recibosExpedidos = 0;
			BigDecimal totalRecibosExpedidos = new BigDecimal(0).setScale(2);
			for(BeanFactura generado : recibosGenerados){
				recibosExpedidos += Integer.parseInt(generado.getCantidadRecibos());
				totalRecibosExpedidos = totalRecibosExpedidos.add(new BigDecimal(generado.getImporte()));
			}
			
			int numRecibosCancelados = 0;
			BigDecimal totalRecibosCancelados = new BigDecimal(0).setScale(2);
			for(BeanFactura cancelado : recibosCancelados){
				numRecibosCancelados += Integer.parseInt(cancelado.getCantidadRecibos());
				totalRecibosCancelados = totalRecibosCancelados.add(new BigDecimal(cancelado.getImporte()));
			}
			
			DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
			decimalFormat.applyPattern("#,##0.00");
			
			final BeanReporteFacturas reporte = new BeanReporteFacturas();
			reporte.setFacturasCorrectas(String.valueOf(recibosExpedidos));
			reporte.setTotalFactCorrectas(decimalFormat.format(totalRecibosExpedidos));
			reporte.setFacturasCanceladas(String.valueOf(numRecibosCancelados));
			reporte.setTotalFactCanceladas(decimalFormat.format(totalRecibosCancelados));
			
			reporteRecibos.add(reporte);
		}
		
		return reporteRecibos;
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
			if(null == beanFactura.getIva() || "e".equals(beanFactura.getIva().trim())){
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

	/**
	 * Genera una pista de auditoria, en la que el valor Cliente Afectado es enviado con un valor de no aplica.
	 * @param tipoFactura Codigo de operacion que se coloca en la pista de auditoria.
	 * @param estatus Estatus que se envia en la pista de auditoria.
	 * @param sessionBean Objeto de la arquitectura agave.
	 */
	private void ejecutaEnvioPistaAuditoria(String tipoFactura, String estatus, ArchitechSessionBean sessionBean){
		final BeanPistaAuditoria pistaAuditoria = new BeanPistaAuditoria();
		if("FACT".equals(tipoFactura)){
			pistaAuditoria.setCodigoOperacion(ConstantesModuloIntegrador.COD_PA_CONSULTA_FACTURAS);
		}else if("DIVI".equals(tipoFactura)){
			pistaAuditoria.setCodigoOperacion(ConstantesModuloIntegrador.COD_PA_CONSULTA_DIVISAS);
		}else if("NOTA".equals(tipoFactura)){
			pistaAuditoria.setCodigoOperacion(ConstantesModuloIntegrador.COD_PA_CONSULTA_NOTAS_CREDITO);
		}else if("RECI".equals(tipoFactura)){
			pistaAuditoria.setCodigoOperacion(ConstantesModuloIntegrador.COD_PA_CONSULTA_RECIBOS);
		}
		pistaAuditoria.setEstatusOperacion(estatus);
		pistaAuditoria.setClienteAfectado(ConstantesModuloIntegrador.COD_PA_NO_APLICA);
		boPistas.generaPistaAuditoria(pistaAuditoria, sessionBean);
	}

}
