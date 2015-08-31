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
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.cifrascontrol.bean.reprocesos.BeanPrevioEdc;
import mx.isban.cifrascontrol.beans.cifrascontrol.BeanInsidenciaCifras;
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
	 * Constante que representa una cadena vacia.
	 */
	private static final String CADENA_VACIA = "";
	/**
	 * Cadena que representa la fase del SAT.
	 */
	private static final String SAT = "SAT";
	/**
	 * Cadena que representa la fase ORIGEN
	 */
	private static final String ORIGEN = "ORIGEN";

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
	 * Dado el nombre de un archivo, crea una instancia de BeanIncidenciaCifras 
	 * @param archivoInsidencia Nombre del archivo a partir del cual se crea una instancia.
	 * @return BeanIncidenciaCifras
	 * @throws ParseException Exception
	 */
	public static BeanInsidenciaCifras fabricaBeanInsidencia(File archivoInsidencia) throws ParseException{
		String[] tokens = archivoInsidencia.getName().split("_");
		final String producto = tokens[0];
		String fase = null;
		if("ORI".equals(tokens[1])){
			fase = ORIGEN;
		}else {
			fase = tokens[1];
		}
		final String textoFecha = tokens[3].substring(0, 8);
		final Date fecha = obtenerFecha(textoFecha);
		final BeanInsidenciaCifras insidencia = new BeanInsidenciaCifras();
		insidencia.setProducto(producto);
		insidencia.setFase(fase);
		insidencia.setFechaInsidencia(fecha);
		insidencia.setRutaIncidencia(archivoInsidencia.toString());
		
		return insidencia;
	}
	
	/**
	 * Construye una instancia de BeanIncidenciaCifras a partir del nombre de un archivo de incidencias SAT.
	 * @param archivoInsidencia Nombre del archivo que se utiliza para generar el BeanIncidenciaCifras.
	 * @return BeanInsidenciaCifras
	 * @throws ParseException Exception
	 */
	public static BeanInsidenciaCifras fabricaBeanIncidenciaSat(File archivoInsidencia) throws ParseException{
		final String nombreArchivo = archivoInsidencia.getName();
		Pattern patronFecha = Pattern.compile("[0-9]{8}\\.TXT");
		Matcher buscador = patronFecha.matcher(nombreArchivo);
		String fechaIncidencias = null;
		if(buscador.find()){
			fechaIncidencias = buscador.group().replace(".TXT", CADENA_VACIA);
		}else {
			return null;
		}
		final Date fecha = obtenerFecha(fechaIncidencias);
		final String producto = nombreArchivo.replace("ERR", CADENA_VACIA).replaceFirst("[0-9]{8}\\.TXT", CADENA_VACIA);
		final BeanInsidenciaCifras incidenciaSat = new BeanInsidenciaCifras();
		incidenciaSat.setFase(SAT);
		incidenciaSat.setFechaInsidencia(fecha);
		incidenciaSat.setProducto(producto);
		incidenciaSat.setRutaIncidencia(archivoInsidencia.toString());
		return incidenciaSat;
	}
	
	/**
	 * Genera una instancia de tipo BeanPrevioEdc a partir de el nombre de un archivo.
	 * @param previo Objeto de tipo archivo a partir del cual se extrae la informacion del previo.
	 * @return BeanPrevioEdc
	 * @throws ParseException Exception
	 */
	public static BeanPrevioEdc fabricaBeanPrevioEdc(File previo) throws ParseException {
		return null;
	}
	
	/**
	 * Crea un objeto de tipo fecha a partir de una cadena.
	 * @param cadenaFecha Cadena en formato de cadena.
	 * @return Date
	 * @throws ParseException Exception
	 */
	private static Date obtenerFecha(String cadenaFecha) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.parse(cadenaFecha);
	}
}
