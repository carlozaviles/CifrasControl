/**
 * 
 */
package mx.isban.cifrascontrol.utileria.general;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

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
		final String[] tiposDeMovimientos = {"INGRESO", "EGRESO", "AMBOS"};
		for(String movimiento : tiposDeMovimientos){
			catalogoTipoMovimiento.put(movimiento, movimiento);
		}
		return catalogoTipoMovimiento;
	}
	
	/**
	 * Obtiene el catalogo de productos para la solicitud de reprocesos.
	 * Se retorna un objeto de tipo Map que se utilizara en la capa cliente para llenar un bojeto de tipo select.
	 * @return Map<String, String>
	 */
	public static Map<String, String> obtenerCatalogoProductosReprocesos(){
		final Map<String, String> catalogoProductos = new LinkedHashMap<String, String>();
		final String[] arregloProductos = {"CAPTACION VISTA Y CHEQUES", "CAPTACION PLAZO", "CAPTACION NOMINA",
				"CAPTACION UDIS", "FONDOS", "TARJETAS PAMPA BANCO", "TARJETAS PAMPA SOFOM", "CARTERA",
				"FIDUCIARIO", "MEXDER 21", "MEXDER 22", "MEXDER CHICAGO", "OPICS BANCO", "OPICS CASA DE BOLSA",
				"OPICS GESTORA", "OPICS FACTORAJE", "SAF PPR", "SOFIA SANTANDER", "SOFIA SERFIN", "ACTIVO ALTAIR"};
		for(String producto : arregloProductos){
			catalogoProductos.put(producto, producto);
		}
		return catalogoProductos;
	}
}
