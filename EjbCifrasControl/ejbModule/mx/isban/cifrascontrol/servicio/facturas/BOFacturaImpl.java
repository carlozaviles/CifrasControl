package mx.isban.cifrascontrol.servicio.facturas;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;


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
import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.beans.facturas.BeanFactura;
import mx.isban.cifrascontrol.beans.general.BeanProducto;

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
        // TODO Auto-generated constructor stub
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
		santander.setIdProducto("santander");
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
			String periodo, String tipoFactura, ArchitechSessionBean sessionBean) {
		// TODO Auto-generated method stub
		return null;
	}

}
