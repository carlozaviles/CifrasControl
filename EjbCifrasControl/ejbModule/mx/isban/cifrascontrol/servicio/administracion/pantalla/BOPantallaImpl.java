package mx.isban.cifrascontrol.servicio.administracion.pantalla;

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
import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantalla;
import mx.isban.cifrascontrol.beans.administracion.pantalla.BeanPantallaRespuesta;
import mx.isban.cifrascontrol.dao.administracion.pantalla.DAOPantalla;

/**
 * Session Bean implementation class BOPantallaImpl
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Remote(BOPantalla.class)
public class BOPantallaImpl extends Architech implements BOPantalla {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String CODIGO_RESULTADO_NULO = "4002";
	
	private static final String CODIGO_SIN_ERRORES = "0";
	
	@EJB
	private DAOPantalla daoPantalla;

	/**
     * @see Architech#Architech()
     */
    public BOPantallaImpl() {
        super();
    }

	@Override
	public BeanPantalla obtenerPantallaPorId(ArchitechSessionBean sessionBean,
			String idPantalla) throws BusinessException {
		this.info("Iniciando la busqueda de la pantalla por id....");
		BeanPantallaRespuesta resultadoConsulta = daoPantalla.obtenerPantallaPorId(sessionBean, idPantalla);
		verificarRespuesta(resultadoConsulta);
		List<BeanPantalla> pantallas = resultadoConsulta.getPantallas();
		BeanPantalla pantalla = new BeanPantalla();
		if(null != pantallas && pantallas.size()>0){
			pantalla = pantallas.get(0);
		}
		this.info("Finaliza la ejecucion del metodo de busqueda de la pantalla por Id");
		return pantalla;
	}

	@Override
	public List<BeanPantalla> buscarTodasPantallas(
			ArchitechSessionBean sessionBean)throws BusinessException{
		this.info("Iniciando la busqueda de todas las pantallas....");
		BeanPantallaRespuesta resultadoConsulta = daoPantalla.obtenerTodasPantallas(sessionBean);
		verificarRespuesta(resultadoConsulta);
		List<BeanPantalla> pantallas = resultadoConsulta.getPantallas();
		this.info("El numero de pantallas es:"+pantallas.size());
		this.info("Finaliza la ejecucion del metodo de busqueda de todas las pantallas");
		return pantallas;
	}

	@Override
	public void modificarPantalla(ArchitechSessionBean sessionBean,
			BeanPantalla pantalla) throws BusinessException {
		this.info("Iniciando la modificacion de la pantalla....");
		BeanPantallaRespuesta resultadoConsulta = daoPantalla.modificarPantalla(sessionBean, pantalla);
		verificarRespuesta(resultadoConsulta);
		this.info("Finaliza la ejecucion del metodo actualizacion de pantalla");
	}

	@Override
	public void agregarPantalla(ArchitechSessionBean sessionBean,
			BeanPantalla pantalla) throws BusinessException {
		this.info("Iniciando el alta de la pantalla....");
		BeanPantallaRespuesta resultadoConsulta = daoPantalla.altaPantalla(sessionBean, pantalla);
		verificarRespuesta(resultadoConsulta);
		this.info("Finaliza la ejecucion del metodo de alta de pantalla");
	}

	@Override
	public void borrarPantalla(ArchitechSessionBean sessionBean,
			String idPantalla) throws BusinessException {
		this.info("Iniciando la baja de la pantalla....");
		BeanPantallaRespuesta resultadoConsulta = daoPantalla.borrarPantalla(sessionBean, idPantalla);
		verificarRespuesta(resultadoConsulta);
		this.info("Finaliza la ejecucion del metodo de alta de pantalla");
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

	public DAOPantalla getDaoPantalla() {
		return daoPantalla;
	}

	public void setDaoPantalla(DAOPantalla daoPantalla) {
		this.daoPantalla = daoPantalla;
	}
	
	
}
