/**
 *
 */
package accdat.UD01.manejoficheros;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import accdat.UD01.manejoficheros.utilidades.Autor;
import accdat.UD01.manejoficheros.utilidades.Cadenas;
import accdat.UD01.manejoficheros.utilidades.Libro;

/**
 * En este ejercicio hay que tener en cuenta que tanto Libro como Autor han de ser clases serializables.
 * @author JESUS
 *
 */
public class Ejercicio11 {

	private static final String FIC_LIBROS = "libros.csv";
	private static final String FIC_LIBROS_SER = "libros.obj";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		escribirFicheroSerializado();
		leerFicheroSerializado();
	}

	/**
	 * Este m�todo leer� l�nea a l�ne el fichero CSV con los libros siguiendo la misma estructura usada hasta ahora
	 * para la lectura de ficheros de caracteres.
	 *
	 * Una vez tenga la instancia de objeto Libro, proceder� a su serializaci�n usando el flujoEscritura previamente preparado.
	 *
	 *
	 */
	public static void escribirFicheroSerializado() {
		try (BufferedReader br = new BufferedReader(new FileReader(Cadenas.getUbicacion(FIC_LIBROS)))) {

			FileOutputStream flujoEscritura = new FileOutputStream(Cadenas.getUbicacion(FIC_LIBROS_SER));
			ObjectOutputStream filtroEscritura = new ObjectOutputStream(flujoEscritura);

			String linea = br.readLine();
			while (linea != null) {
				// La l�nea tendr� esta estructura
				// 111-2-2222;Un mundo sin fin;Ken Follet;5
				String[] datos = linea.split(";");

				Libro libro = new Libro(datos[0], datos[1], new Autor(datos[2]), Integer.parseInt((datos[3])));

				filtroEscritura.writeObject(libro);

				linea = br.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Este m�todo leer� el ficero .obj con las instancias serializadas, generar� una instancia y llamar� al m�todo toString del objeto.
	 */
	public static void leerFicheroSerializado() {
		Libro libro = null;
		try (FileInputStream flujoEntrada = new FileInputStream(Cadenas.getUbicacion(FIC_LIBROS_SER));
				ObjectInputStream filtroEntrada = new ObjectInputStream(flujoEntrada);) {
			while (flujoEntrada.available() != 0)
			// available Devuelve 0 si no ha podido leer nada
			{
				libro = (Libro) filtroEntrada.readObject();
				System.out.println(libro);
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se ha podido abrir el fichero");
		} catch (IOException e) {
			System.out.println("Error al leer del fichero");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al leer del fichero");
		}
	}
}
