/**
 *
 */
package accdat.UD01.manejoficheros;

import java.io.File;
import java.util.Scanner;

import accdat.UD01.manejoficheros.utilidades.Cadenas;

/**
 * @author JESUS
 *
 */
public class Ejercicio7 {

	/**
	 * Ejercicio 7:
	 * Modificaci�n del ejercicio 6 para que los ficheros sean creados unos dentro de otros en forma de directorios.
	 * Es decir, crear� una jerarqu�a de carpetas.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String mensaje = "";

		System.out.println("�Cu�ntos directorios quiere crear?");

		// Variable para almacenar el n�mero de ficheros.
		int numFile = sc.nextInt();

		// Variable para almacenar el nombre del fichero.
		String nomFile = "";

		for (int i=1; i<=numFile; i++) {

			// Se crea el objeto de tipo File, abstracci�n que Java hace de un archivo/directorio.
			System.out.println("Introduzca el nombre del " + i + "� directorio:");

			// Para conseguir que se creen de manera recursiva (una carpeta dentro de otra)
			// en lugar de asignar cadena vac�a al nombre de fichero a cada iteraci�n como se hace en el
			// Ejercicio6, lo que se har� ser� a�adir al nombre del fichero anterior una barra "/" y el nuevo nombre
			// introducido por el usuario, consiguiendo as� el efecto deseado:
			// - directorio1
			// - directorio1/directorio2
			// - directorio1/directorio2/directorio3
			// ...
			nomFile += "/" + sc.next();

			File f = new File(Cadenas.getUbicacion(nomFile));

			try {
				// Si el fichero es creado con �xito, el mensaje ser� satisfactorio.
				// En caso contrario el mensaje informar� acerca del error.
				mensaje = f.mkdir()?"El directorio ha sido creado con �xito.":"El directorio no ha podido ser creado.";

			}finally {
				System.out.println(mensaje);
			}

		}

		sc.close();
	}
}
