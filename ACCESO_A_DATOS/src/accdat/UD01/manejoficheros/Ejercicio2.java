/**
 *
 */
package accdat.UD01.manejoficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import accdat.UD01.manejoficheros.utilidades.Cadenas;

/**
 * Ejercicio 2.
 *
 * Implementar una clase Java cuyo m�todo main copie el contenido de un
 * fichero �origen.txt� en un fichero �destino.txt�.
 *
 * @author JESUS
 *
 */
public class Ejercicio2 {

	private static final String FIC_ORIGEN = "origen.txt";
	private static final String FIC_DESTINO = "destino.txt";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintWriter pw = null;
		String leido = null;

		// Apertura del flujo desde el fichero de origen
		try (BufferedReader br = new BufferedReader(
				new FileReader(Cadenas.getUbicacion(FIC_ORIGEN)))){
			pw = new PrintWriter (new FileWriter(Cadenas.getUbicacion(FIC_DESTINO)));

			// En una �nica l�nea.
			// - Actualizo el valor de la variable le�do con una nueva l�nea.
			// - Controlo que ese contenido no sea nulo, ya que esa es la condici�n para salir del bucle.
			while((leido=br.readLine())!=null) {
				pw.println(leido);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pw!=null) {
				pw.close();
			}
			System.out.println("Fichero copiado en: " + Cadenas.getUbicacion(FIC_DESTINO));
		}
	}

}
