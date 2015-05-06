package mx.isban.cifrascontrol.dao.administracion.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.ExceptionDataAccess;
import mx.isban.agave.dataaccess.DataAccess;
import mx.isban.agave.dataaccess.channels.database.dto.RequestMessageDataBaseDTO;
import mx.isban.agave.dataaccess.channels.database.dto.ResponseMessageDataBaseDTO;
import mx.isban.agave.dataaccess.factories.jdbc.ConfigFactoryJDBC;
import mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuario;
import mx.isban.cifrascontrol.beans.administracion.usuario.BeanUsuarioRespuesta;

/**
 * Session Bean implementation class DAOUsuarioImpl
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class DAOUsuarioImpl extends Architech implements DAOUsuario {
       
	private static final long serialVersionUID = 1L;
	private static final String ID_CANAL = "ID_CANAL_DATABASE_JDBC";
	
	/**
     * @see Architech#Architech()
     */
    public DAOUsuarioImpl() {
        super();
    }

	@Override
	public BeanUsuarioRespuesta obtenerTodosUsuarios(
			ArchitechSessionBean sessionBean) {
		final String consulta = "SELECT ID_USUARIO,ESTATUS FROM USUARIO";
		final BeanUsuarioRespuesta usuariosRespuesta = new BeanUsuarioRespuesta();
		this.info("Se inicia la consulta de todos los usuarios...");
		this.info(consulta);
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY);
		requestDTO.setQuery(consulta);
		requestDTO.setCodeOperation("COD15 Consulta-Usuarios");
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error("Se obtuvo un codigo de error al ejecutar la consulta :"+responseDTO.getCodeError());
				usuariosRespuesta.setCodError("2001");
			}else{
				List<BeanUsuario> listaUsuarios = new ArrayList<BeanUsuario>();
				for(Map<String, Object> registro : responseDTO.getResultQuery()){
					BeanUsuario usuario = new BeanUsuario();
					usuario.setIdUsuario(String.valueOf(registro.get("ID_USUARIO")));
					String status = String.valueOf(registro.get("ESTATUS"));
					if("1".equals(status)){
						usuario.setEstatus(true);
					}
					listaUsuarios.add(usuario);
				}
				usuariosRespuesta.setUsuarios(listaUsuarios);
				usuariosRespuesta.setCodError("0");
			}
		}catch(ExceptionDataAccess e){
			this.error("Error al realizar una consulta en el componente DataAcces"+e);
			usuariosRespuesta.setCodError("2001");
		}
		return usuariosRespuesta;
	}

	@Override
	public BeanUsuarioRespuesta obtenerUsuarioPorID(
			ArchitechSessionBean sessionBean, String idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeanUsuarioRespuesta modificarUsuario(
			ArchitechSessionBean sessionBean, BeanUsuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeanUsuarioRespuesta altaUsuario(ArchitechSessionBean sessionBean,
			BeanUsuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeanUsuarioRespuesta bajaUsuario(ArchitechSessionBean sessionBean,
			BeanUsuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
