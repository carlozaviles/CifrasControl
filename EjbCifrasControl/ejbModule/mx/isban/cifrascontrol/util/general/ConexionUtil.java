/**************************************************************
* Queretaro, Qro 2015 Marzo
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.agave.logging.Level;

/**
 * Clase ConexionUtil
 * 
 * Singleton encargado de obtener la conexion a la base de datos por medio
 * de la lectura de un archivo properties con el valor del jndi
 * 
 * @author Everis
 * @version 1.0
 * @see www.everis.com/mexico
 * 
 */
public final class ConexionUtil extends Architech{
	
	/**
	 * Numero de la clase serializada
	 */
	private static final long serialVersionUID = -1977151055593846687L;

	/**
	 * Propiedad de tipo {@link ConexionUtil}
	 */
	private static ConexionUtil conexionUtil;
	
	/**
	 * Propiedad de tipo {@link Connection}
	 */
	private static Connection conexion;
	
	/**
	 * Constante que contiene la ruta de la ubicacion del archivo properties, del cual se realiza
	 * la lectura de la propiedad JNDI
	 */
	private static final String PATH_PROPERTIES = "/arquitecturaAgave/DistV1/Configuracion/CatalogoProductos.properties";
	
	/**
	 * Propiedad que contiene el jndi asociado a la conexion datasource
	 */
	private String jndi;
	
	/**
	 * Metodo encargado de obtener una instancia de tipo {@link ConexionUtil}
	 * @return Una instancia de tipo {@link ConexionUtil}
	 * @throws BusinessException En caso de presentarse un error al momento de realizar la consulta del jndi
	 */
	public static ConexionUtil getInstance()throws BusinessException{
		conexionUtil = new ConexionUtil();
		return conexionUtil;
	}
	
	/**
	 * Constructor vacio que inicializa la variable jndi
	 * @throws BusinessException En caso de presentarse un error 
	 * al momento de leer el archivo de propiedades
	 */
	private ConexionUtil()throws BusinessException{
		establecerJndi();
	}
	
	/**
	 * Metodo encargado de leer un archivo de tipo properties con
	 * el valor del JNDI
	 * @throws ReprocesoException En caso de presentarse un error al momento
	 * de leer el archivo properties
	 */
	private void establecerJndi()throws BusinessException{
		try {
			final Properties propiedades = new Properties();
			final File archivoPropiedades = new File(PATH_PROPERTIES);
			if(archivoPropiedades.isFile()){
				final InputStream imputStreamPropiedades = new FileInputStream(archivoPropiedades);
				propiedades.load(imputStreamPropiedades);
				jndi = propiedades.getProperty("reproceso.db.jndi");
				this.info("Se obtiene la propiedad "+jndi+" de la ruta "+PATH_PROPERTIES);
			}else{
				this.error("No se encuentra el archivo de propiedades para la ruta:"+PATH_PROPERTIES);
				throw new BusinessException("No se encontro el archivo properties", "No se ha encontrado el archivo properties relacionado al path"+PATH_PROPERTIES);
			}	
		} catch (IOException e) {
			showException(e, Level.ERROR);
			throw new BusinessException(e.getMessage(), "Error de tipo IOException al "
					+ "momento de leer el archivo de propiedades");
		}
		
	}
	
	/**
	 * Metodo encargado de obtener la conexion en relacion a un jndi
	 * @return Un objeto de tipo {@link ConnectException}
	 * @throws BusinessException En caso de presentarse un error
	 * al momento de crear la conexion
	 */
	public Connection getConexion()throws BusinessException{
		final InitialContext context;
		try {
			context = new InitialContext();
			final DataSource dataSource = (DataSource) context.lookup(jndi);
			conexion = dataSource.getConnection();
		} catch (NamingException e) {
			showException(e, Level.ERROR);
			throw new BusinessException("Se ha presentado un error de tipo NamingException",e.getMessage());
		} catch (SQLException e) {
			showException(e, Level.ERROR);
			throw new BusinessException("Se ha presentado un error de tipo SQLException",e.getMessage());
		}
		
		return conexion;
	
	}
	
	/**
	 * Metodo encargado de cerrar la conexion y establecer el valor null
	 * para la instancia conexionUtil 
	 */
	public static void borrarInstancia(){
		cerrarConexion();
		conexionUtil = null;
	}
	
	/**
	 * Metodo encargado de cerrar las conexiones del objeto 
	 * de tipo {@link Connection}
	 */
	private static void cerrarConexion(){
		try {
			if(conexion != null){
				conexion.close();
			}
		} catch (SQLException e) {
			showException(e, Level.ERROR);
		}
	}

}