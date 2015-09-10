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

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.agave.logging.Level;
import mx.isban.cifrascontrol.util.cifrascontrol.ConstantesCifrasControl;

/**
 * Clase ConexionUtil
 * 
 * Singleton encargado de obtener la conexion a la base de datos de Catalogos.
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
	 * Origen de datos.
	 */
	private DataSource origen;
	
	/**
	 * Metodo encargado de obtener una instancia de tipo {@link ConexionUtil}
	 * @return Una instancia de tipo {@link ConexionUtil}
	 * @throws BusinessException En caso de presentarse un error al momento de realizar la consulta del jndi
	 */
	public static ConexionUtil getInstance()throws BusinessException{
		if(conexionUtil == null){
			conexionUtil = new ConexionUtil();
		}
		return conexionUtil;
	}
	
	/**
	 * Constructor vacio que inicializa la variable jndi
	 * @throws BusinessException En caso de presentarse un error 
	 * al momento de leer el archivo de propiedades
	 */
	private ConexionUtil()throws BusinessException{
		final String nombreOrigenDatos = this.getConfigDeCmpAplicacion("ORIGEN_DATOS");
		if(nombreOrigenDatos == null){
			final String mensaje = "No se encontro el nombre del Datasource en el archivo de configuracion"; 
			this.warn(mensaje);
			throw new BusinessException(ConstantesCifrasControl.ERROR_CARGA_CONFIG_DS_CATALOGOS, mensaje);
		}
		InitialContext ctx = null;
		try{
			ctx = new InitialContext();
			origen = (DataSource)ctx.lookup(nombreOrigenDatos);
			ctx.close();
		}catch(NamingException e){
			showException(e, Level.ERROR);
			throw new BusinessException(ConstantesCifrasControl.ERROR_DS_LOOKUP, e.getMessage());
		}
	}
	
	/**
	 * Metodo encargado de obtener la conexion en relacion a un jndi
	 * @return Un objeto de tipo {@link ConnectException}
	 * @throws BusinessException En caso de presentarse un error
	 * al momento de crear la conexion
	 */
	public Connection getConexion()throws BusinessException{
		Connection conn = null;
		try {
			conn = origen.getConnection();
		} catch (SQLException e) {
			showException(e, Level.ERROR);
			throw new BusinessException("Se ha presentado un error de tipo SQLException",e.getMessage());
		} 
		
		return conn;
	}
	
	/**
	 * Metodo encargado de cerrar las conexiones del objeto 
	 * de tipo {@link Connection}
	 * @param conn Conexion a base de datos que sera cerrada.
	 */
	public static void cerrarConexion(Connection conn){
		try {
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			showException(e, Level.ERROR);
		}
	}
}