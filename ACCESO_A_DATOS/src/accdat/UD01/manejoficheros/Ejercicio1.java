/**
 *
 */
package accdat.UD01.manejoficheros;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import accdat.UD01.manejoficheros.utilidades.Cadenas;

/**
 * Ejercicio 1
 *
 * Preparar un fichero de texto plano e implementar una clase Java cuyo m�todo main lea su contenido y lo escriba por pantalla.
 * El fichero est� en ficheros/fichero_txt_plano.txt
 * @author JESUS
 *
 */
public class Ejercicio1 {

	/**
	 * @param args
	 */
	private static final String FICHERO = "fichero_txt_plano.txt";

	public static void main	(String[] args) {
		String leido = null;

		// try con recursos que abre un flujo de lectura desde un fichero y
		// que puede ser leido l�nea a l�nea gracias al filtro BufferedReader.
		/*
		 * LA UTILIDAD CADENAS.GETUBICACION �NICAMENTE DEVUELVE EL NOMBRE DE LA CARPETA DE FICHEROS M�S EL DEL FICHERO EN CUESTI�N.
		 */
		try (BufferedReader br = new BufferedReader(
				new FileReader(Cadenas.getUbicacion(FICHERO)))){

			// lectura l�nea a l�nea y su posterior escritura mientras
			// el flujo posea l�neas.
			leido = br.readLine();
			while (leido!=null) {
				System.out.println(leido);
				leido = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
