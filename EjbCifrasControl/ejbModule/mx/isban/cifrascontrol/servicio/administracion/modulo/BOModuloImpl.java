package mx.isban.cifrascontrol.servicio.administracion.modulo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.agave.commons.interfaces.BeanResultBO;
import mx.isban.cifrascontrol.beans.administracion.modulo.BeanModulo;
import mx.isban.cifrascontrol.beans.administracion.modulo.BeanModuloRespuesta;
import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantalla;
import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantallaRespuesta;
import mx.isban.cifrascontrol.dao.administracion.modulo.DAOModulo;
import mx.isban.cifrascontrol.dao.administracion.pantalla.DAOPantalla;

/**
 * Session Bean implementation class BOModuloImpl
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Remote(BOModulo.class)
public class BOModuloImpl extends Architech implements BOModulo {
       
	private static final long serialVersionUID = 1L;
	
	private static final String CODIGO_RESULTADO_NULO = "4002";
	private static final String CODIGO_SIN_ERRORES = "0";
	
	@EJB
	private DAOModulo daoModulo;
	
	@EJB
	private DAOPantalla daoPantalla;

	/**
     * @see Architech#Architech()
     */
    public BOModuloImpl() {
        super();
    }

	@Override
	public List<BeanModulo> obtenerModulosPorUsuarioLogueado(
			ArchitechSessionBean sessionBean, String idUsuario)
			throws BusinessException {
		this.info("Iniciando la busqueda de modulos para mostrar las pantallas en relacion a un grupo");
		BeanModuloRespuesta resultadoConsulta = daoModulo.obtenerModulosPorUsuario(sessionBean, idUsuario);
		verificarRespuesta(resultadoConsulta);
		List<BeanModulo> modulos = resultadoConsulta.getModulos();
		establecerPantallas(sessionBean,modulos,idUsuario);
		this.info("El numero de grupos administrativos encontrados:"+modulos.size());
		this.info("Finaliza la ejecucion del metodo de busqueda de todos los grupos administrativos");
		return modulos;
	}
	
	private void establecerPantallas(ArchitechSessionBean sessionBean,
			List<BeanModulo> modulos,String idUsuario)throws BusinessException {
		this.info("Iniciando la busqueda de todas las pantallas relacionadas a un usuario....");
		for (BeanModulo beanModulo : modulos) {
			BeanPantallaRespuesta respuesta = daoPantalla.obtenerPantallasPorUsuarioModulo(sessionBean, beanModulo.getIdModulo(), idUsuario);
			verificarRespuesta(respuesta);
			List<BeanPantalla> pantallas = respuesta.getPantallas();
			beanModulo.setPantallas(pantallas);
		}
	}

	
	
	private void verificarRespuesta(BeanResultBO resultadoConsulta)throws BusinessException{
		if(null == resultadoConsulta){
			this.error("Respuesta nula al consultar la base de datos");
			throw new BusinessException(CODIGO_RESULTADO_NULO);
		}
		if(!CODIGO_SIN_ERRORES.equals(resultadoConsulta.getCodError())){
			this.error("Se ha presentado un error al momento de realizar la consulta en la base de datos :"+resultadoConsulta.getCodError());
			throw new BusinessException(resultadoConsulta.getCodError());
		}
	}

	@Override
	public List<BeanModulo> obtenerTodosModulos(ArchitechSessionBean sessionBean)
			throws BusinessException {
		this.info("Iniciando la busqueda de todos los modulos....");
		final BeanModuloRespuesta resultadoConsulta = daoModulo.obtenerTodosModulos(sessionBean);
		verificarRespuesta(resultadoConsulta);
		final List<BeanModulo> modulos = resultadoConsulta.getModulos();
		this.info("El numero de modulos administrativos encontrados:"+modulos.size());
		this.info("Finaliza la ejecucion del metodo de busqueda de todos los modulos");
		return modulos;
	}

	@Override
	public BeanModulo obtenerModuloPorId(ArchitechSessionBean sessionBean,
			String idModulo) throws BusinessException {
		this.info("Iniciando la busqueda del modulo por id....");
		BeanModuloRespuesta resultadoConsulta = daoModulo.obtenerModuloPorId(sessionBean, idModulo);
		verificarRespuesta(resultadoConsulta);
		List<BeanModulo> modulos = resultadoConsulta.getModulos();
		BeanModulo modulo = new BeanModulo();
		if(null !=  modulos && modulos.size()>0){
			modulo = modulos.get(0);
		}
		this.info("Finaliza la ejecucion del metodo de busqueda de la pantalla por Id");
		return modulo;
	}

	@Override
	public void modificarModulo(ArchitechSessionBean sessionBean,
			BeanModulo modulo) throws BusinessException{
		this.info("Iniciando la modificacion del modulo....");
		BeanModuloRespuesta resultadoConsulta = daoModulo.modificarModulo(sessionBean, modulo);
		verificarRespuesta(resultadoConsulta);
		this.info("Finaliza la ejecucion del metodo actualizacion de modulo");
		
	}

	@Override
	public void altaModulo(ArchitechSessionBean sessionBean, BeanModulo modulo)
			throws BusinessException {
		this.info("Iniciando el alta de modulo....");
		BeanModuloRespuesta resultadoConsulta = daoModulo.guardarModulo(sessionBean, modulo);
		verificarRespuesta(resultadoConsulta);
		this.info("Finaliza la ejecucion del metodo de alta de modulo");
		
	}

	@Override
	public void borrarModulo(ArchitechSessionBean sessionBean, String idModulo)
			throws BusinessException {
		this.info("Iniciando el alta de modulo....");
		BeanModuloRespuesta resultadoConsulta = daoModulo.borrarModulo(sessionBean, idModulo);
		verificarRespuesta(resultadoConsulta);
		this.info("Finaliza la ejecucion del metodo de alta de modulo");
	}

	public DAOModulo getDaoModulo() {
		return daoModulo;
	}

	public void setDaoModulo(DAOModulo daoModulo) {
		this.daoModulo = daoModulo;
	}

	public DAOPantalla getDaoPantalla() {
		return daoPantalla;
	}

	public void setDaoPantalla(DAOPantalla daoPantalla) {
		this.daoPantalla = daoPantalla;
	}
	
	
	
}
