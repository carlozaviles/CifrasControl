package mx.isban.cifrascontrol.util.reproceso;

import java.util.ArrayList;
import java.util.List;

import mx.isban.cifrascontrol.beans.producto.BeanProducto;

public final class CatalogoProductosReproceso {
	
	private CatalogoProductosReproceso() {
		super();
	}
	
	public static List<BeanProducto> obtenerCatalogoProductosReprocesos(){

 		final List<BeanProducto> catalogoProductos = new ArrayList<BeanProducto>();

		final String[] arregloProductos = {"CAPTACION VISTA Y CHEQUES", "CAPTACION PLAZO", "CAPTACION NOMINA",
		"CAPTACION UDIS", "FONDOS", "TARJETAS PAMPA BANCO", "TARJETAS PAMPA SOFOM", "CARTERA",
		"FIDUCIARIO", "MEXDER 21", "MEXDER 22", "MEXDER CHICAGO", "OPICS BANCO", "OPICS CASA DE BOLSA",
		"OPICS GESTORA", "OPICS FACTORAJE", "SAF PPR", "SOFIA SANTANDER", "SOFIA SERFIN", "ACTIVO ALTAIR"};
		for (int i = 0; i < arregloProductos.length; i++) {
			BeanProducto producto = new BeanProducto();
			producto.setIdProducto(String.valueOf(i+1));
			producto.setDescripcion(arregloProductos[i]);
			producto.setTipoProducto("EDC");
			catalogoProductos.add(producto);
		}
 		return catalogoProductos;

 	}

}
