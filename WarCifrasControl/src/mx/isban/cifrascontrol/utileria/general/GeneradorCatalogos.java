/**
 * 
 */
package mx.isban.cifrascontrol.utileria.general;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mx.isban.cifrascontrol.beans.producto.BeanProducto;

/**
 * @author everis
 *
 */
public final class GeneradorCatalogos {
	
	/**
	 * Construye un catalogo que retorna los meses del anio. Este se retorna dentro de un objeto
	 * de tipo Map, para que sea utilizado en la capa cliente para llenar un input de tipo select.
	 * @return Map<String, String>
	 */
	public static Map<String, String> obtenerListaMeses(){
		final Map<String, String> catalogoMeses = new LinkedHashMap<String, String>();
		final String[] meses = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", 
				"AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
		int codigoMes = 1;
		for(String mes : meses){
			String cadenaCodigoMes = null;
			if(codigoMes <= 9){
				cadenaCodigoMes = "0" + codigoMes;
			}else{
				cadenaCodigoMes = String.valueOf(codigoMes);
			}
			catalogoMeses.put(cadenaCodigoMes, mes);
			codigoMes++;
		}
		return catalogoMeses;
	}
	
	/**
	 * Retorna los ultimos n meses de acuerdo al parametro rango negativo. La llave del HashMap tiene un formato yyyyMM.
	 * @param rangoNegativo Determina la cantidad de meses a regresar.
	 * @return Map<String,String>
	 */
	public static Map<String, String> obtenerListaMeses(int rangoNegativo){
		if(rangoNegativo < 1 || rangoNegativo > 12){
			throw new IllegalArgumentException("El parametra de entrada debe de ser entre 1 y 12");
		}
		final Map<String, String> mesesAnteriores = new LinkedHashMap<String, String>();
		final Map<String, String> totalMeses = obtenerListaMeses();
		final Date fechaHoy = new Date();
		final Calendar calendario = Calendar.getInstance();
		calendario.setTime(fechaHoy);
		for(int i = 1; i <= rangoNegativo; i++){
			int anio = calendario.get(Calendar.YEAR);
			int mes = calendario.get(Calendar.MONTH) + 1;
			String cadenaMes = null;
			if(mes <= 9){
				cadenaMes = "0" + String.valueOf(mes);
			}else{
				cadenaMes = String.valueOf(mes);
			}
			String key = String.valueOf(anio) + cadenaMes;
			mesesAnteriores.put(key, totalMeses.get(cadenaMes));
			calendario.add(Calendar.MONTH, -1);
		}
		return mesesAnteriores;
	}
	
	/**
	 * Construye un catalogo de anios de acuerdo a los parametros recibidos. Se construye un objeto de tipo
	 * Map ya que sera utilizado para llenar un input de tipo select en la capa cliente.
	 * @param rangoNegativo El catalogo sera construido a partir de anioActual - rangoNegativo.
	 * @param rangoPositivo El catalogo sera construido hasta anioActual + rangoPositivo.
	 * @return Map<String, String>
	 */
	public static Map<String, String> obtenerListaAnios(int rangoNegativo, int rangoPositivo){
		if((rangoNegativo == 0 && rangoPositivo == 0) || (rangoNegativo < 0 || rangoPositivo < 0)){
			throw new IllegalArgumentException("Almenos alguno de los dos argumentos debe de ser diferente de 0, y los dos"
					+ " argumentos deben de ser positivos.");
		}
		final Calendar calendario = Calendar.getInstance();
		calendario.setTime(new Date());
		final int anioActual = calendario.get(Calendar.YEAR);
		final Map<String, String> catalogoAnios = new LinkedHashMap<String, String>();
		for(int i = anioActual - rangoNegativo; i <= anioActual + rangoPositivo; i++){
			final String anio = String.valueOf(i);
			catalogoAnios.put(anio, anio);
		}
		return catalogoAnios;
	}
	
	/**
	 * Obtiene el catalogo de tipos de Movimiento para el formulario de solicitud de reprocesos.
	 * Se retorna un objeto de tipo Map que se utlizara en la capa cliente para llenar un objeto de tipo select. 
	 * @return Map<String, String>
	 */
	public static Map<String, String> obtenerCatalogoTipoMov(){
		final Map<String, String> catalogoTipoMovimiento = new LinkedHashMap<String, String>();
		final String[] tiposDeMovimientos = {"INGRESO", "EGRESO", "AMBOS","NINGUNO"};
		for(String movimiento : tiposDeMovimientos){
			catalogoTipoMovimiento.put(movimiento, movimiento);
		}
		return catalogoTipoMovimiento;
	}
	
	/**
	 * Obtiene el catalogo de productos para la solicitud de reprocesos.
	 * Se retorna un objeto de tipo Map que se utilizara en la capa cliente para llenar un bojeto de tipo select.
	 * @param productoList Lista de productos utilizada para formar el catalogo.
	 * @return Map<String, String>
	 */
	public static Map<String, String> obtenerCatalogoProductosReprocesos(List<BeanProducto> productoList){
		final Map<String, String> catalogoProductos = new LinkedHashMap<String, String>();
		for (BeanProducto beanProducto : productoList) {
			catalogoProductos.put(beanProducto.getDescripcion(), beanProducto.getDescripcion());
		}
		return catalogoProductos;
	}

	/**
	 * Obtiene el catalogo con los tipos de perfiles existentes en la aplicacion.
	 * @return Map<String, String>
	 */
	public static Map<String, String> obtenerCatalogoTipoPerfil(){
		final Map<String, String> tipoPerfil = new LinkedHashMap<String, String>();
		tipoPerfil.put("S", "Seguridad Inform\u00e1tica");
		tipoPerfil.put("A", "Administrador");
		tipoPerfil.put("O", "Operativo");
		return tipoPerfil;
	}
}
