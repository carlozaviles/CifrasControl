/**
 * 
 */
package mx.isban.cifrascontrol.bean.reprocesos;

import java.io.Serializable;

/**
 * @author everis
 *
 */
public class BeanParamsConsultaPrevios implements Serializable {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = -7517914661629359832L;
	/**
	 * Filtro para Consulta Numero de Cuenta.
	 */
	private String numeroCuenta;
	/**
	 * Filtro para Consulta Producto.
	 */
	private String producto;
	/**
	 * Filtro para Consulta Mes.
	 */
	private String mes;
	/**
	 * Filtro para Consulta Anio.
	 */
	private String anio;
	
	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	
	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	/**
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}
	
	/**
	 * @param producto the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}
	
	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}
	
	/**
	 * @param mes the mes to set
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	/**
	 * @return the anio
	 */
	public String getAnio() {
		return anio;
	}
	
	/**
	 * @param anio the anio to set
	 */
	public void setAnio(String anio) {
		this.anio = anio;
	}
	
	@Override
	public String toString(){
		final StringBuilder salida = new StringBuilder();
		salida.append("Numero de Cuenta: ").append(numeroCuenta).append(", Producto: ")
			.append(producto).append(", Mes: ").append(mes).append(", Anio: ").append(anio);
		return salida.toString();
	}
}
