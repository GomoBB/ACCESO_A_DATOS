/**
 *
 */
package accdat.UD01.manejoficheros;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import accdat.UD01.manejoficheros.utilidades.Alumno;

/**
 * @author JESUS
 *
 */
public class Ejercicio9_B {

	public static final String FICHERO_ALUMNOS = "ficheros/alumnos.csv";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Alumno aluNuevo = new Alumno("Jes�s", "R�denas", "Jaque", 38);
		Alumno aluRepe =  new Alumno("Luis", "Ant�nez", "P�rez", 20);

		try {
			insertaAlumno(aluNuevo);
			insertaAlumno(aluRepe);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este m�todo recorrer� todo el fichero hasta finalizar O encontrar un alumno
	 * ID�NTICO al pasado como par�metro.
	 * Si encuentra alumno, informa de la imposibilidad de inserci�n.
	 * Si no encuentra alumno, lo inserta e informa de la inserci�n.
	 * @param alumno
	 * @throws IOException
	 */
	public static void insertaAlumno(Alumno alumno) throws IOException {
		try (FileReader flujoLectura = new FileReader(FICHERO_ALUMNOS);
		BufferedReader filtroLectura = new BufferedReader(flujoLectura)){

			boolean encontrado = false;

			String linea = filtroLectura.readLine();

			while ((linea != null) && !encontrado ) {
				Alumno aux = Alumno.procesaLinea(linea);
				encontrado = aux.equals(alumno);
				linea = filtroLectura.readLine();
			}

			if (!encontrado) {
				// Apertura del fichero en modo a�adir.
				// FileWriter(String fileName, boolean append)
				PrintWriter pw = new PrintWriter(new FileWriter(FICHERO_ALUMNOS, true));

				// Inserci�n de salto de l�nea.
				pw.write("\n");

				// Escritura de la l�nea correspondiente al alumno en formato csv.
				pw.println(alumno.toString_csv());

				// cierre del flujo de salida.
				pw.close();
				System.out.println("Inserci�n realizada. Fichero actualizado.");
			}else{
				System.out.println("No es posible insertar: El alumno ya existe en el fichero.");
			}
		}
	}

}
