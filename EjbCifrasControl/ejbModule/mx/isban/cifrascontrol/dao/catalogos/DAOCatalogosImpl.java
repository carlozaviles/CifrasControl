package mx.isban.cifrascontrol.dao.catalogos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.beans.ArchitechSessionBean;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.agave.commons.exception.ExceptionDataAccess;
import mx.isban.agave.dataaccess.DataAccess;
import mx.isban.agave.dataaccess.channels.database.dto.RequestMessageDataBaseDTO;
import mx.isban.agave.dataaccess.channels.database.dto.ResponseMessageDataBaseDTO;
import mx.isban.agave.dataaccess.factories.jdbc.ConfigFactoryJDBC;
import mx.isban.agave.logging.Level;
import mx.isban.cifrascontrol.beans.producto.BeanProducto;
import mx.isban.cifrascontrol.beans.producto.BeanProductoRespuesta;
import mx.isban.cifrascontrol.util.general.ConexionUtil;

/**
 * Session Bean implementation class DAOCatalogosImpl
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class DAOCatalogosImpl extends Architech implements DAOCatalogos {
       
    /**
	 * Numero de la clase serializada
	 */
	private static final long serialVersionUID = -2263939006206421131L;
	
	/**
	 * Constante con el valor ID_CANAL_DATABASE_JDBC
	 */
	private static final String ID_CANAL = "ID_CANAL_DATABASE_JDBC";
	
	/**
     * @see Architech#Architech()
     */
    public DAOCatalogosImpl() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<BeanProducto> obtenerTodosProductos() throws BusinessException {
		String consultaSQL = "SELECT ID_CATAL, DSC_PROD, FLG_TIPO_PROD FROM MOI_MX_MAE_ADMIN_CATA_PROD";
		Connection conexion = null;
		Statement sentencia = null;
		ResultSet filas = null;
		List<BeanProducto> productosList = new ArrayList<BeanProducto>();
		try {
			conexion = ConexionUtil.getInstance().getConexion();
			sentencia = conexion.createStatement();
			filas = sentencia.executeQuery(consultaSQL);
			while (filas.next()) {
				final BeanProducto producto = new BeanProducto();
				producto.setIdProducto(filas.getString("ID_CATAL"));
				producto.setDescripcion(filas.getString("DSC_PROD"));
				producto.setTipoProducto(filas.getString("FLG_TIPO_PROD"));
				productosList.add(producto);
			}
			this.info("Tamanio de lista:"+productosList.size());
		} catch (SQLException e) {
			showException(e,Level.ERROR);
			throw new BusinessException(CODIGO_ERROR_CONSULTA_PRODUCTOS);
		}
		finally{
			if(null != filas){
				try {
					filas.close();
				} catch (SQLException e) {
					showException(e,Level.WARN);
				}
			}
			if(null != sentencia){
				try {
					sentencia.close();
				} catch (SQLException e) {
					showException(e,Level.WARN);
				}

			}
			if(null != conexion){
				try {
					conexion.close();
				} catch (SQLException e) {
					showException(e,Level.WARN);
				}

			}
			ConexionUtil.borrarInstancia();
		}
		return productosList;
	}

	@Override
	public BeanProductoRespuesta obtenerProductoPorUsuario(String idUsuario,
			String tipo, ArchitechSessionBean sessionBean){
		this.info("Se inicia la consulta de productos para el usuario:"+idUsuario+" y el tipo:"+tipo);
		final String consulta = "SELECT ID_PROD, DSC_DESC,COD_TIPO_PROD "
				+ "FROM MOI_MX_REL_USR_PROD WHERE ID_USER_FK = ? AND COD_TIPO_PROD = ?";
		final BeanProductoRespuesta productoRespuesta = new BeanProductoRespuesta();
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY_PARAMS);
		requestDTO.setQuery(consulta);
		requestDTO.setCodeOperation("COD01 Consulta productos por usuario");
		requestDTO.addParamToSql(idUsuario);
		requestDTO.addParamToSql(tipo);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(!ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				this.error(responseDTO.getCodeError());
				productoRespuesta.setCodError(responseDTO.getCodeError());
				productoRespuesta.setMsgError(responseDTO.getMessageError());
			}else{
				List<BeanProducto> listaProductos = obtenerListadoProductos(responseDTO);
				productoRespuesta.setProductos(listaProductos);
				productoRespuesta.setCodError(CODIGO_SIN_ERRORES);
			}
		}catch(ExceptionDataAccess e){
			showException(e,Level.ERROR);
			productoRespuesta.setCodError(CODIGO_ERROR_CONSULTA_PRODUCTOS_USUARIO);
			productoRespuesta.setMsgError(e.getMessage());
		}
		return productoRespuesta;
	}
	
	/**
	 * Metodo que itera en el resultado de la consulta y obtiene un listado 
	 * de objetos tipo {@link BeanProducto}
	 * @param responseDTO La respuesta de la consulta en la base de datos
	 * @return Listado de objetos de tipo {@link BeanProducto}
	 */
	private List<BeanProducto> obtenerListadoProductos(
			ResponseMessageDataBaseDTO responseDTO) {
		final List<BeanProducto> listaProductos = new ArrayList<BeanProducto>();
		for(Map<String, Object> registro : responseDTO.getResultQuery()){
			final BeanProducto producto = new BeanProducto();
			producto.setIdProducto(String.valueOf(registro.get("ID_PROD")));
			producto.setDescripcion(String.valueOf(registro.get("DSC_DESC")));
			producto.setTipoProducto(String.valueOf(registro.get("COD_TIPO_PROD")));
			listaProductos.add(producto);
		}
		return listaProductos;
	}


}
