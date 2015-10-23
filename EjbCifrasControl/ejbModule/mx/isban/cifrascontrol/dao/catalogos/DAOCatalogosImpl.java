package mx.isban.cifrascontrol.dao.catalogos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import mx.isban.agave.commons.architech.Architech;
import mx.isban.agave.commons.exception.BusinessException;
import mx.isban.agave.commons.exception.ExceptionDataAccess;
import mx.isban.agave.dataaccess.DataAccess;
import mx.isban.agave.dataaccess.channels.database.dto.RequestMessageDataBaseDTO;
import mx.isban.agave.dataaccess.channels.database.dto.ResponseMessageDataBaseDTO;
import mx.isban.agave.dataaccess.factories.jdbc.ConfigFactoryJDBC;
import mx.isban.agave.logging.Level;
import mx.isban.cifrascontrol.beans.producto.BeanIDProductoRespuesta;
import mx.isban.cifrascontrol.beans.producto.BeanProducto;
import mx.isban.cifrascontrol.beans.producto.BeanProductoRespuesta;
import mx.isban.cifrascontrol.util.general.ConexionUtil;
import mx.isban.cifrascontrol.util.general.ConstantesModuloIntegrador;

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
	 * Constante con la consulta para los productos EDC.
	 */
	private static final String CONSULTA_PRODUCTOS_EDC = "SELECT ID_CATAL, DSC_PROD FROM MOI_MX_MAE_ADMIN_CATA_PROD";
	/**
	 * Constante con la consulta de producto EDC por ID.
	 */
	private static final String CONSULTA_PRODUCTO_EDC_POR_ID = 
			"SELECT ID_CATAL, DSC_PROD FROM MOI_MX_MAE_ADMIN_CATA_PROD WHERE ID_CATAL = ?";
	/**
	 * Consulta de productos por usuario.
	 */
	private static final String CONSULTA_PRODUCTOS_USUARIO = "SELECT ID_PROD,FLG_PER_REPRO FROM MOI_MX_REL_USR_PROD "
			+ " WHERE ID_USER_FK = ? AND COD_TIPO_PROD = ?";
	
	/**
     * @see Architech#Architech()
     */
    public DAOCatalogosImpl() {
        super();
    }

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.catalogos.DAOCatalogos#obtenerTodosProductos(java.lang.String)
	 */
//	@Override
//	public List<BeanProducto> obtenerTodosProductos(String tipoProducto) throws BusinessException {
//		String consultaSQL = "SELECT ID, FISCALNAME FROM Fiscalentity ";
//		Connection conexion = null;
//		PreparedStatement sentencia = null;
//		ResultSet filas = null;
//		List<BeanProducto> productosList = new ArrayList<BeanProducto>();
//		try {
//			conexion = ConexionUtil.getInstance().getConexion();
//			sentencia = conexion.prepareStatement(consultaSQL);
//			this.info("Ejecutando la consulta:"+consultaSQL);
//			filas = sentencia.executeQuery();
//			while (filas.next()) {
//				final BeanProducto producto = new BeanProducto();
//				producto.setIdProducto(filas.getString("ID"));
//				producto.setDescripcion(filas.getString("FISCALNAME"));
//				producto.setTipoProducto(tipoProducto);
//				productosList.add(producto);
//			}
//			this.info("Tamanio de lista:"+productosList.size());
//		} catch (SQLException e) {
//			showException(e,Level.ERROR);
//			throw new BusinessException(CODIGO_ERROR_CONSULTA_PRODUCTOS);
//		}
//		finally{
//			cerrarRecursos(filas,sentencia,conexion);
//		}
//		return productosList;
//	}
	

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.catalogos.DAOCatalogos#obtenerProductosEDC()
	 */
	@Override
	public BeanProductoRespuesta obtenerProductosEDC() {
		this.info("Se ejecuta la consulta para obtener todos los productos EDC");
		final BeanProductoRespuesta respuesta = new BeanProductoRespuesta();
		List<BeanProducto> listaProductos = new ArrayList<BeanProducto>();
		final RequestMessageDataBaseDTO requestDto = new RequestMessageDataBaseDTO();
		requestDto.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY);
		requestDto.setQuery(CONSULTA_PRODUCTOS_EDC);
		try{
			DataAccess ida = DataAccess.getInstance(requestDto, this.getLoggingBean());
			ResponseMessageDataBaseDTO response = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(response.getCodeError())){
				for(Map<String, Object> fila : response.getResultQuery()){
					BeanProducto producto = new BeanProducto();
					producto.setIdProducto(String.valueOf(fila.get("ID_CATAL")));
					producto.setDescripcion(String.valueOf(fila.get("DSC_PROD")));
					producto.setTipoProducto(ConstantesModuloIntegrador.CLAVE_PRODUCTOS_EDC);
					listaProductos.add(producto);
				}
				respuesta.setProductos(listaProductos);
				respuesta.setCodError(CODIGO_SIN_ERRORES);
			}else{
				respuesta.setCodError(CODIGO_ERROR_CONSULTA_PRODUCTOS);
			}
		}catch(ExceptionDataAccess e){
			showException(e);
			respuesta.setCodError(CODIGO_ERROR_CONSULTA_PRODUCTOS);
		}
		return respuesta;
	}

	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.catalogos.DAOCatalogos#obtenerProductosFacturas()
	 */
	@Override
	public BeanProductoRespuesta obtenerProductosFacturas() {
		String consultaSQL = "SELECT ID, FISCALNAME FROM Fiscalentity ";
		Connection conexion = null;
		PreparedStatement sentencia = null;
		ResultSet filas = null;
		List<BeanProducto> productosList = new ArrayList<BeanProducto>();
		BeanProductoRespuesta respuesta = new BeanProductoRespuesta();
		try {
			conexion = ConexionUtil.getInstance().getConexion();
			sentencia = conexion.prepareStatement(consultaSQL);
			this.info("Ejecutando la consulta:"+consultaSQL);
			filas = sentencia.executeQuery();
			while (filas.next()) {
				final BeanProducto producto = new BeanProducto();
				producto.setIdProducto(filas.getString("ID"));
				producto.setDescripcion(filas.getString("FISCALNAME"));
				producto.setTipoProducto(ConstantesModuloIntegrador.CLAVE_PRODUCTOS_FACTURAS);
				productosList.add(producto);
			}
			this.info("Tamanio de lista: "+productosList.size());
			respuesta.setCodError(CODIGO_SIN_ERRORES);
			respuesta.setProductos(productosList);
		} catch (SQLException e) {
			showException(e,Level.ERROR);
			respuesta.setCodError(String.valueOf(e.getErrorCode()));
			respuesta.setMsgError(e.getMessage());
		} catch(BusinessException e){
			showException(e,Level.ERROR);
			respuesta.setCodError(String.valueOf(e.getCode()));
			respuesta.setMsgError(e.getMessage());
		}
		finally{
			cerrarRecursos(filas,sentencia,conexion);
		}
		return respuesta;
	}
	
	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.catalogos.DAOCatalogos#obtenerProductoPorId(java.lang.String)
	 */
	@Override
	public BeanProducto obtenerProductoFacturaPorId(String idProducto)throws BusinessException {
		String consultaSQL = "SELECT ID, FISCALNAME FROM Fiscalentity WHERE ID = ?";
		Connection conexion = null;
		PreparedStatement sentencia = null;
		ResultSet filas = null;
		final BeanProducto producto = new BeanProducto();
		try {
			conexion = ConexionUtil.getInstance().getConexion();
			sentencia = conexion.prepareStatement(consultaSQL);
			sentencia.setInt(1, Integer.parseInt(idProducto));
			filas = sentencia.executeQuery();
			while (filas.next()) {
				producto.setIdProducto(filas.getString("ID"));
				producto.setDescripcion(filas.getString("FISCALNAME"));
				producto.setTipoProducto(FACT);
			}
			this.info("Ejecucion del metodo de consulta por id correcto");
		} catch (SQLException e) {
			showException(e,Level.ERROR);
			throw new BusinessException(CODIGO_ERROR_CONSULTA_PRODUCTOS_POR_ID);
		}finally{
			cerrarRecursos(filas,sentencia,conexion);
		}
		return producto;
	}
	
	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.catalogos.DAOCatalogos#obtenerProductoEDCPorId(java.lang.String)
	 */
	@Override
	public BeanProductoRespuesta obtenerProductoEDCPorId(String idProducto) {
		this.info("Consulta de producto EDC con ID producto: " + idProducto);
		BeanProductoRespuesta respuesta = new BeanProductoRespuesta();
		List<BeanProducto> coincidencia = new ArrayList<BeanProducto>();
		final RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY_PARAMS);
		requestDTO.setQuery(CONSULTA_PRODUCTO_EDC_POR_ID);
		requestDTO.addParamToSql(idProducto);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			final ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				if(responseDTO.getResultQuery().size() >= 1){
					BeanProducto producto = new BeanProducto();
					producto.setIdProducto(idProducto);
					producto.setDescripcion(String.valueOf(responseDTO.getResultQuery().get(0).get("DSC_PROD")));
					producto.setTipoProducto(ConstantesModuloIntegrador.CLAVE_PRODUCTOS_EDC);
					coincidencia.add(producto);
				}
				respuesta.setProductos(coincidencia);
				respuesta.setCodError(CODIGO_SIN_ERRORES);
			}else{
				this.info("La consulta por ID de EDC retorno un codigo de error: " + responseDTO.getCodeError());
				respuesta.setCodError(CODIGO_ERROR_CONSULTA_PRODUCTOS_POR_ID);
			}
		}catch(ExceptionDataAccess e){
			showException(e, Level.ERROR);
			respuesta.setCodError(CODIGO_ERROR_CONSULTA_PRODUCTOS_POR_ID);
		}
		return respuesta;
	}
		
	/* (non-Javadoc)
	 * @see mx.isban.cifrascontrol.dao.catalogos.DAOCatalogos#obtenerIdentificadoresProductos(java.lang.String, java.lang.String)
	 */
	@Override
	public BeanIDProductoRespuesta obtenerIdentificadoresProductosPorUsuario(String idUsuario, String tipo) {
		this.info("Se consultaran los productos de tipo: " + tipo);
		this.info("Para el usuario: " + idUsuario);
		BeanIDProductoRespuesta respuesta = new BeanIDProductoRespuesta();
		Map<String, String> relacionID = new HashMap<String, String>();
		RequestMessageDataBaseDTO requestDTO = new RequestMessageDataBaseDTO();
		requestDTO.setTypeOperation(ConfigFactoryJDBC.OPERATION_TYPE_QUERY_PARAMS);
		requestDTO.setQuery(CONSULTA_PRODUCTOS_USUARIO);
		requestDTO.addParamToSql(idUsuario);
		requestDTO.addParamToSql(tipo);
		try{
			final DataAccess ida = DataAccess.getInstance(requestDTO, this.getLoggingBean());
			ResponseMessageDataBaseDTO responseDTO = (ResponseMessageDataBaseDTO)ida.execute(ID_CANAL);
			if(ConfigFactoryJDBC.CODE_SUCCESFULLY.equals(responseDTO.getCodeError())){
				for(Map<String, Object> registro : responseDTO.getResultQuery()){
					String idProducto = String.valueOf(registro.get("ID_PROD"));
					String permisoReproceso = String.valueOf(registro.get("FLG_PER_REPRO"));
					relacionID.put(idProducto, permisoReproceso);
				}
				respuesta.setCodError(CODIGO_SIN_ERRORES);
				respuesta.setListaIdentificadoresProductos(relacionID);
			}else{
				respuesta.setCodError(CODIGO_ERROR_CONSULTA_PRODUCTOS);
			}
		}catch(ExceptionDataAccess e){
			showException(e, Level.ERROR);
			respuesta.setCodError(CODIGO_ERROR_CONSULTA_PRODUCTOS);
		}
		return respuesta;
	}
	
	/**
	 * Cierra recursos a BD.
	 * @param filas ResultSet
	 * @param sentencia Objeto de tipo PreparedStatement
	 * @param conexion Objeto de tipo Connection.
	 */
	private void cerrarRecursos(ResultSet filas, PreparedStatement sentencia, Connection conexion){
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

	}

}
