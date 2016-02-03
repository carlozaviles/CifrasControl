/**
 * 
 */
package util.general;

import static org.junit.Assert.*;

import java.io.File;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mx.isban.cifrascontrol.beans.cifrascontrol.BeanInsidenciaCifras;
import mx.isban.cifrascontrol.util.general.UtilGeneralCifras;

import org.junit.Test;

/**
 * @author everis
 *
 */
public class TestUtilGeneralCifras {

	/**
	 * Test method for {@link mx.isban.cifrascontrol.util.general.UtilGeneralCifras#establecerRegistros(java.util.List, java.lang.Class, java.lang.Class)}.
	 */
	@Test
	public void testEstablecerRegistros() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link mx.isban.cifrascontrol.util.general.UtilGeneralCifras#obtenerNombresProductos(java.util.List)}.
	 */
	@Test
	public void testObtenerNombresProductos() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link mx.isban.cifrascontrol.util.general.UtilGeneralCifras#filtrarListaArchivos(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testFiltrarListaArchivos() {
		List<File> listaVacia = UtilGeneralCifras.filtrarListaArchivos("NO_EXISTE", "");
		assertEquals("La lista debe estar vacia.", 0, listaVacia.size());
		List<File> listaTest2 = UtilGeneralCifras.filtrarListaArchivos("/arquitecturaAgave/DistV1/Detalle_Diferencias", 
				"BRAN7_[A-Z]+_INC_[0-9]{8}\\.TXT");
		assertEquals("La longitud de la lista debe ser Uno", 1, listaTest2.size());
	}

	@Test
	public void testFabricaBeanInsidencia()throws ParseException{
		String ruta = "/arquitecturaAgave/DistV1/Detalle_Diferencias/BRAN7_ORI_INC_20150313.TXT";
		File testFile = new File(ruta);
		BeanInsidenciaCifras resultado = UtilGeneralCifras.fabricaBeanInsidencia(testFile);
		assertEquals("El valor del producto no es correcto", "BRAN7", resultado.getProducto());
		assertEquals("El valor de la fase es incorrecto", "ORI", resultado.getFase());
		assertEquals("El valor de la ruta es incorrecto", ruta, resultado.getRutaIncidencia());
		Date fecha = resultado.getFechaInsidencia();
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);
		assertEquals("El anio de la fecha es incorrecto", 2015, calendario.get(Calendar.YEAR));
		assertEquals("El mes de la fecha es incorrecto", 2, calendario.get(Calendar.MONTH));
		assertEquals("El dia en la fecha es incorrecto", 13, calendario.get(Calendar.DATE));
	}
	
	@Test
	public void testFabricaBeanIncidenciaSat() throws ParseException {
		String ruta = "/arquitecturaAgave/DistV1/Detalle_Diferencias/ERRBGUDISS20150824.TXT";
		File testFile = new File(ruta);
		BeanInsidenciaCifras resultado = UtilGeneralCifras.fabricaBeanIncidenciaSat(testFile);
		assertEquals("El valor del producto no es correcto", "BGUDISS", resultado.getProducto());
		assertEquals("El valor de la fase es incorrecto", "SAT", resultado.getFase());
		assertEquals("El valor de la ruta es incorrecto", ruta, resultado.getRutaIncidencia());
		Date fecha = resultado.getFechaInsidencia();
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);
		assertEquals("El anio de la fecha es incorrecto", 2015, calendario.get(Calendar.YEAR));
		assertEquals("El mes de la fecha es incorrecto", 7, calendario.get(Calendar.MONTH));
		assertEquals("El dia en la fecha es incorrecto", 24, calendario.get(Calendar.DATE));
	}
}
