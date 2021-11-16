/**
 * 
 */
package accdat.UD01.manejoficheros.utilidades;

/**
 * @author JESUS
 *
 */
public class Cadenas {
	public static final String FICHEROS = "ficheros/";
	
	public static String getUbicacion(String nombreFichero) {
		return FICHEROS + nombreFichero;
	}
}
