/**
 *
 */
package accdat.UD01.manejoficheros;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import accdat.UD01.manejoficheros.utilidades.Cadenas;

/**
 *
 * @author JESUS
 *
 */
public class Ejercicio5 {

	private static final String FICHERO_ORIGEN = "original.png";
	private static final String FICHERO_COPIA = "copia.png";

	// Vamos a definir bloques de 16KB
	private static final int TAM = 1024 * 16;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		copiarImagen(FICHERO_ORIGEN);
	}

	public static void copiarImagen (String rutaImagenOrigen) {
		try (BufferedInputStream bin =
				new BufferedInputStream(
						new FileInputStream(Cadenas.getUbicacion(rutaImagenOrigen)))){
			BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(Cadenas.getUbicacion(FICHERO_COPIA)));
			int cantidadBytesleidos = 0;
			byte[] buffer = new byte[TAM];

			while ((cantidadBytesleidos = bin.read(buffer, 0, TAM)) != -1) {
				bout.write(buffer, 0, cantidadBytesleidos);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("Operaci�n finalizada. Imagen copiada en: " + Cadenas.getUbicacion(FICHERO_COPIA));
		}
	}
}