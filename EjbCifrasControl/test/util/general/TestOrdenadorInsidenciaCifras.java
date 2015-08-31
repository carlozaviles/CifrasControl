/**
 * 
 */
package util.general;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import mx.isban.cifrascontrol.beans.cifrascontrol.BeanInsidenciaCifras;
import mx.isban.cifrascontrol.util.general.OrdenadorInsidenciaCifras;

import org.junit.Test;

/**
 * @author everis
 *
 */
public class TestOrdenadorInsidenciaCifras {

	/**
	 * Test method for {@link mx.isban.cifrascontrol.util.general.OrdenadorInsidenciaCifras#compare(mx.isban.cifrascontrol.beans.cifrascontrol.BeanInsidenciaCifras, mx.isban.cifrascontrol.beans.cifrascontrol.BeanInsidenciaCifras)}.
	 */
	@Test
	public void testCompare() {
		BeanInsidenciaCifras origen = new BeanInsidenciaCifras();
		origen.setFase("ORIGEN");
		origen.setProducto("A");
		origen.setFechaInsidencia(new Date());
		BeanInsidenciaCifras cfd = new BeanInsidenciaCifras();
		cfd.setFase("CFD");
		cfd.setProducto("B");
		cfd.setFechaInsidencia(new Date());
		BeanInsidenciaCifras sat = new BeanInsidenciaCifras();
		sat.setFase("SAT");
		sat.setProducto("C");
		sat.setFechaInsidencia(new Date());
		BeanInsidenciaCifras edc = new BeanInsidenciaCifras();
		edc.setFase("EDC");
		edc.setProducto("D");
		edc.setFechaInsidencia(new Date());
		List<BeanInsidenciaCifras> lista = new ArrayList<BeanInsidenciaCifras>();
		lista.add(edc);
		lista.add(sat);
		lista.add(cfd);
		lista.add(origen);
		String [] fases = {"ORIGEN", "CFD", "SAT", "EDC"};
		OrdenadorInsidenciaCifras ordenador = new OrdenadorInsidenciaCifras(fases);
		Collections.sort(lista, ordenador);
		assertEquals("El origen debe de ser el primer objeto", "ORIGEN", lista.get(0).getFase());
		assertEquals("El CFD debe de ser el segundo objeto", "CFD", lista.get(1).getFase());
		assertEquals("El SAT debe ser el tercer objeto", "SAT", lista.get(2).getFase());
		assertEquals("El EDC debe de ser el cuarto objeto", "EDC", lista.get(3).getFase());
	}

}
