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
package mx.isban.cifrascontrol.util.general;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.beans.producto.BeanProducto;
import static mx.isban.cifrascontrol.util.cifrascontrol.ConstantesCifrasControl.*;

/**
* Clase UtilGeneralCifras
* 
* <P>Clase de utileria, que tiene los metodos generales a utilizarse por
* todo el aplicativo de cifras de control. 
* 
* @author Everis
* @version 1.0
* @see www.everis.com/mexico
* 
*/
public final class UtilGeneralCifras {

	/**
	 * Constructor privado
	 */
	private UtilGeneralCifras() {
		super();
	}
	
	
	
	
	/**
	 * Metodo encargado de establecer el resultado de la consulta del web service 
	 * cifras de control.- Consulta de cifras control,  en las propiedades definidas
	 * para cualquier clase con la cual concuerden los nombres de las propiedades
	 * con sus respectivos metodos get y set
	 * 
	 * @param listaResultado El listado de la consulta al web service
	 * @param clase El tipo de clase obtenido como resultado de la consulta al web service
	 * @param claseContenedora El tipo de retorno 
	 * @return Un listado de objetos de tipo de la clase de retorno
	 */
	public static <T,E> List<E> establecerRegistros(List<T> listaResultado, Class<T> clase,Class<E> claseContenedora)throws BusinessException{
		List<E> lista = new ArrayList<E>();
		try {
			
			for (T objeto : listaResultado) {
				final E factura = claseContenedora.cast(Class.forName(claseContenedora.getName()).newInstance());
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
			throw new BusinessException(ILLEGAL_ACCESS_EXCEPTION_CODE);
		} catch (IllegalArgumentException e) {
			throw new BusinessException(ILLEGAL_ARGUMENT_EXCEPTION);
		} catch (InvocationTargetException e) {
			throw new BusinessException(INVOCATION_TARGET_EXCEPTION);
		} catch (InstantiationException e) {
			throw new BusinessException(INSTANTIATION_EXCEPTION);
		} catch (ClassNotFoundException e) {
			throw new BusinessException(CLASS_NOT_FOUND_EXCEPTION);
		}
		return lista;
	}
	
	/**
	 * Metodo encargado de obtener los nombres de los productos, con el fin de ejecutar
	 * la consulta de reprocesos
	 * @param productos Los productos a obtener el nombre
	 * @return Listado de productos (nombre)
	 */
	public static List<String> obtenerNombresProductos(List<BeanProducto>productosList){
		final List<String> productos = new ArrayList<String>();
		if(null != productosList && !productosList.isEmpty()){
			for (BeanProducto producto : productosList) {
				final String nombre = producto.getDescripcion();
				productos.add(nombre);
			}
		}
		return productos;
	}
	
}
