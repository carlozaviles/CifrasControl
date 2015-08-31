/**
 * 
 */
package mx.isban.cifrascontrol.utileria.general;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author everis
 *
 */
public class CifrasControlUtil {

	/**
	 * Tamanio del buffer utilizado para escribir el archivo de descarga.
	 */
	private static final int TAMANIO_BUFFER = 500;
	
	/**
	 * Lee un archivo y lo escribe en el OutputStream que se recibido como parametro.
	 * @param rutaArchivo Archivo que sera escrito en el OutputStream
	 * @param os OutputStream por medio del cual se escribira el archivo.
	 * @throws IOException Exception
	 */
	public static void escribeArchivo(String rutaArchivo, OutputStream os) throws IOException{
		File archivoDescarga = new File(rutaArchivo);
		FileInputStream fis = new FileInputStream(archivoDescarga);
		byte []buffer = new byte[TAMANIO_BUFFER];
		int bytesLeidos = -1;
		while((bytesLeidos = fis.read(buffer)) != -1){
			os.write(buffer, 0, bytesLeidos);
		}
		fis.close();
		os.flush();
	}
	
	/**
	 * Genera el formato de fecha dd MES yyyy. Ejemplo 27 Agosto 2015.
	 * @param fecha Fecha con la que se genera la salida.
	 * @param local La localizacion para generar la fecha.
	 * @return String
	 */
	public static String generaFormatoFechaTipoUno(Date fecha, Locale local){
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
		return sdf.format(fecha);
	}
}
