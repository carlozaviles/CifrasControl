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

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;

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
	 * @param <T> Representa el tipo de objeto que contiene los datos a procesar.
	 * @param <E> Representa el tipo de objeto en el que se copiaran las propiedades.
	 * @param listaResultado El listado de la consulta al web service
	 * @param clase El tipo de clase obtenido como resultado de la consulta al web service
	 * @param claseContenedora El tipo de retorno 
	 * @return Un listado de objetos de tipo de la clase de retorno
	 * @throws BusinessException Exception
	 */
	public static <T,E> List<E> establecerRegistros(List<T> listaResultado, Class<T> clase,Class<E> claseContenedora)
			throws BusinessException{
		List<E> lista = new ArrayList<E>();
		try {
			
			for (T objeto : listaResultado) {
				final E factura = claseContenedora.cast(Class.forName(claseContenedora.getName()).newInstance());
				BeanUtils.copyProperties(factura, objeto);
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
	 * @param productosList Los productos a obtener el nombre
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
	
	/**
	 * Busca en un directorio los archivos que coincidan con un filtro dado.
	 * @param pathDirectorio Directorio en el que se realiza la busqueda de archivos.
	 * @param filtro Filtro que se utiliza para realizar la busqueda de archivos.
	 * @return List<File>
	 */
	public static List<File> filtrarListaArchivos(String pathDirectorio, String filtro){
		List<File> archivosCoincidentes = new ArrayList<File>();
		final File directorio = new File(pathDirectorio);
		File []totalArchivos = directorio.listFiles();
		if(totalArchivos == null){
			return archivosCoincidentes;
		}
		for(File archivo : totalArchivos){
			if(archivo.isFile() && Pattern.matches(filtro, archivo.getName())){
				archivosCoincidentes.add(archivo);
			}
		}
		
		return archivosCoincidentes;
	}
	
	/**
	 * Crea un objeto de tipo fecha a partir de una cadena.
	 * @param cadenaFecha Cadena en formato de cadena.
	 * @param formato Formato de la cadena de fecha.
	 * @return Date
	 * @throws ParseException Exception
	 */
	public static Date obtenerFecha(String cadenaFecha, String formato) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		return sdf.parse(cadenaFecha);
	}

	/**
	 * Obtiene el formato de fecha esperado para realizar la consulta de cancelaciones.
	 * @param periodo Fecha que sera tratada.
	 * @return String
	 */
	public static String obtenerFechas(String periodo) {
		final StringBuilder periodoConcatenado = new StringBuilder(periodo);
		periodoConcatenado.insert(4, "-");
		return periodoConcatenado.toString();
	}
//	
//	public static List<String> obtenerCodigosAplicacion(String nombreAplicacion, Architech objArchitech){
//		String numeroAplic
//	}
}
