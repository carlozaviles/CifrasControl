package mx.isban.cifrascontrol.dao.administracion.modulo;

import javax.ejb.Local;

import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.cifrascontrol.beans.administracion.modulo.BeanModulo;
import mx.isban.cifrascontrol.beans.administracion.modulo.BeanModuloRespuesta;

@Local
public interface DAOModulo {

	/**
	 * Constante con el valor 0, el cual indica que no se presento un error al 
	 * momento de realizar acciones relacionadas al Grupo
	 */
	public static final String CODIGO_SIN_ERRORES = "0";
	
	/**
	 * Constante con el valor 2001, el cual es el codigo de error generico
	 */
	public static final String CODIGO_ERROR_GENERAL = "2001";
	/**
	 * Constante para indicar que existe un error de tipo DATAACCESS
	 */
	public static final String ERROR_DATAACCESS = "Error al realizar una consulta en el componente DataAcces";

	
	public BeanModuloRespuesta obtenerModulosPorUsuario(ArchitechSessionBean sessionBean,String idUsuario);
	public BeanModuloRespuesta obtenerTodosModulos(ArchitechSessionBean sessionBean);
	public BeanModuloRespuesta obtenerModuloPorPantalla(ArchitechSessionBean sessionBean,String idPantalla);
	public BeanModuloRespuesta obtenerModuloPorId(ArchitechSessionBean sessionBean,String idModulo);
	public BeanModuloRespuesta modificarModulo(ArchitechSessionBean sessionBean,BeanModulo modulo);
	public BeanModuloRespuesta guardarModulo(ArchitechSessionBean sessionBean,BeanModulo modulo);
	public BeanModuloRespuesta borrarModulo(ArchitechSessionBean sessionBean,String idModulo);
	
}
