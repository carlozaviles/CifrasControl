/**
 * 
 */
package com.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mx.isban.cifrascontrol.beans.cifrascontrol.BeanInsidenciaCifras;
import mx.isban.cifrascontrol.util.general.OrdenadorInsidenciaCifras;

import org.junit.Before;
import org.junit.Test;

/**
 * @author everis
 *
 */
public class TestOrdenadorIncidenciasCifras {
	
	String []reglasOrdenamiento = {"origen", "cfd", "sat", "edc"};
	List<BeanInsidenciaCifras> listaIncidencias;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		listaIncidencias = new ArrayList<BeanInsidenciaCifras>();
		BeanInsidenciaCifras b1 = new BeanInsidenciaCifras();
		b1.setFase("edc");
		b1.setNumeroCuenta("1");
		listaIncidencias.add(b1);
		BeanInsidenciaCifras b2 = new BeanInsidenciaCifras();
		b2.setFase("edc");
		b2.setNumeroCuenta("2");
		listaIncidencias.add(b2);
		BeanInsidenciaCifras b3 = new BeanInsidenciaCifras();
		b3.setFase("cfd");
		b3.setNumeroCuenta("3");
		listaIncidencias.add(b3);
		BeanInsidenciaCifras b4 = new BeanInsidenciaCifras();
		b4.setFase("cfd");
		b4.setNumeroCuenta("4");
		listaIncidencias.add(b4);
		BeanInsidenciaCifras b5 = new BeanInsidenciaCifras();
		b5.setFase("origen");
		b5.setNumeroCuenta("6");
		listaIncidencias.add(b5);
		BeanInsidenciaCifras b6 =  new BeanInsidenciaCifras();
		b6.setFase("origen");
		b6.setNumeroCuenta("5");
		listaIncidencias.add(b6);
	}

	@Test
	public void test() {
		assertEquals("El tamanio de la lista no es correcto", 6, listaIncidencias.size());
		Collections.sort(listaIncidencias, new OrdenadorInsidenciaCifras(reglasOrdenamiento));
		assertEquals("El primer objeto de la lista es incorrecto (Fase)", "origen", 
				listaIncidencias.get(0).getFase());
		assertEquals("El primer objeto de la lista es incorrecto (Numero Cuenta)", 
				"5", listaIncidencias.get(0).getNumeroCuenta());
		assertEquals("El ultimo objeto de la lista es incorrecto (Fase)", 
				"edc", listaIncidencias.get(5).getFase());
		assertEquals("El ultimo objeto de la lista es incorrecto (Numero de cuenta)", 
				"2", listaIncidencias.get(5).getNumeroCuenta());
	}

}
