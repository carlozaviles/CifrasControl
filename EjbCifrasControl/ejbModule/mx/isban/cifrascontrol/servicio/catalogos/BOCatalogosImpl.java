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
package mx.isban.cifrascontrol.servicio.catalogos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.agave.commons.interfaces.BeanResultBO;
import mx.isban.cifrascontrol.beans.producto.BeanProducto;
import mx.isban.cifrascontrol.beans.producto.BeanProductoRespuesta;
import mx.isban.cifrascontrol.dao.catalogos.DAOCatalogos;
import mx.isban.cifrascontrol.util.reproceso.CatalogoProductosReproceso;

/**
 * Session Bean implementation class BOCatalogosImpl
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class BOCatalogosImpl extends Architech implements BOCatalogos {
       
	/**
	 * Propiedad de tipo {@link DAOCatalogos}
	 */
	@EJB
	private DAOCatalogos daoCatalogos;
	
    /**
	 * Numero de la clase serializada
	 */
	private static final long serialVersionUID = -1980194162404005418L;

	/**
	 * Constante que contiene el valor para el codigo de un resultado nulo, el cual es 4002
	 */
	private static final String CODIGO_RESULTADO_NULO = "4002";
	/**
	 * Constante para el valor del codigo sin errores
	 */
	private static final String CODIGO_SIN_ERRORES = "0";
	
	
	/**
     * @see Architech#Architech()
     */
    public BOCatalogosImpl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.catalogos.BOCatalogos#obtenerTodosProductos()
	 */
	@Override
	public List<BeanProducto> obtenerTodosProductos(String tipoProducto) throws BusinessException {
		this.info("Iniciando la busqueda de todos los productos");
		List<BeanProducto> listaProductos = new ArrayList<BeanProducto>();
		if("EDC".equalsIgnoreCase(tipoProducto)){
			listaProductos = CatalogoProductosReproceso.obtenerCatalogoProductosReprocesos();
		}else{
			listaProductos = daoCatalogos.obtenerTodosProductos(tipoProducto);
		}
		this.info("El numero de productos encontrados es:"+listaProductos.size());
		this.info("Metodo de consulta de todos los productos, realizado con exito");
		return listaProductos;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.servicio.catalogos.BOCatalogos#obtenerProductosUsuario(mx.isban.agave.commons.beans.ArchitechSessionBean, java.lang.String, java.lang.String)
	 */
	@Override
	public List<BeanProducto> obtenerProductosUsuario(
			ArchitechSessionBean sessionBean, String idUsuario, String tipo)
			throws BusinessException {
		this.info("Iniciando la busqueda de productos de tipo:"+tipo+" para el usuario:"+idUsuario);
		final BeanProductoRespuesta respuesta = daoCatalogos.obtenerProductoPorUsuario(idUsuario, tipo, sessionBean);
		verificarRespuesta(respuesta);
		final List<BeanProducto> productos = respuesta.getProductos();
		this.info("El numero de productos encontrados es:"+productos.size());
		this.info("Metodo de consulta de productos por usuario, realizado con exito");
		return productos;
	}
	
	/**
	 * Metodo encargado de verificar las respuestas obtenidas del DAO
	 * @param resultadoConsulta Un objeto con los resultados de la consulta
	 * @throws BusinessException En caso de presentarse un error
	 */
	private void verificarRespuesta(BeanResultBO resultadoConsulta)throws BusinessException{
		if(null == resultadoConsulta){
			this.error("Respuesta nula al consultar la base de datos");
			throw new BusinessException(CODIGO_RESULTADO_NULO,"Respuesta nula al consultar la base de datos");
		}
		if(!CODIGO_SIN_ERRORES.equals(resultadoConsulta.getCodError())){
			this.error("Se ha presentado un error al momento de realizar la consulta en la base de datos :"+resultadoConsulta.getCodError());
			throw new BusinessException(resultadoConsulta.getCodError());
		}
	}

}
