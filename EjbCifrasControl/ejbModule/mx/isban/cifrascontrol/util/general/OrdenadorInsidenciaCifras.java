/**
 * 
 */
package mx.isban.cifrascontrol.util.general;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import mx.isban.cifrascontrol.beans.cifrascontrol.BeanInsidenciaCifras;

/**
 * @author everis
 *
 */
public class OrdenadorInsidenciaCifras implements Comparator<BeanInsidenciaCifras> {
	
	/**
	 * Mapa para darle un peso a cada una de las fases y con esta base ordenar los objetos.
	 */
	private Map<String, Integer> relacionFase;
	
	/**
	 * Constructor por defecto.
	 * @param parametrosFaseOrdenados Lista de fases. El orden de esta lista se reflejara en el orden en que el Comparator funcionara.
	 */
	public OrdenadorInsidenciaCifras(String [] parametrosFaseOrdenados){
		relacionFase = new HashMap<String, Integer>();
		for(int i = 0; i < parametrosFaseOrdenados.length; i++){
			relacionFase.put(parametrosFaseOrdenados[i].toUpperCase(), i);
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(BeanInsidenciaCifras beanUno, BeanInsidenciaCifras bean2) {
		final Integer valorBeanUno = relacionFase.get(beanUno.getFase().toUpperCase());
		final Integer valorBeanDos = relacionFase.get(bean2.getFase().toUpperCase());
		if(valorBeanUno != null && valorBeanDos != null && (valorBeanUno - valorBeanDos != 0)){
			return valorBeanUno - valorBeanDos;
		} else if (beanUno.getNumeroCuenta() != null && bean2.getNumeroCuenta() != null){
			return beanUno.getNumeroCuenta().compareTo(bean2.getNumeroCuenta());
		} else {
			return 0;
		}
	}
}
