package mx.isban.cifrascontrol.servicio.administracion.usuario;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.agave.commons.interfaces.BeanResultBO;
import mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuario;

/**
 * Session Bean implementation class BOUsuarioImpl
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@Remote(BOUsuario.class)
public class BOUsuarioImpl extends Architech implements BOUsuario {

	private static final long serialVersionUID = 1L;
	private static final String CODIGO_RESULTADO_NULO = "4002";
	private static final String CODIGO_SIN_ERRORES = "0";

	/**
     * Default constructor. 
     */
    public BOUsuarioImpl() {
        super();
    }

	@Override
	public List<BeanUsuario> obtenerTodosUsuarios(
			ArchitechSessionBean sessionBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeanUsuario obtenerUsuarioPorID(ArchitechSessionBean sessionBean,
			String idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarUsuario(ArchitechSessionBean sessionBean,
			BeanUsuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void altaUsuario(ArchitechSessionBean sessionBean,
			BeanUsuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bajaUsuario(ArchitechSessionBean sessionBean,
			BeanUsuario usuario) {
		// TODO Auto-generated method stub
		
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
}
