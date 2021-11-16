/**
 *
 */
package accdat.UD01.manejoficheros;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import accdat.UD01.manejoficheros.utilidades.Alumno;

/**
 * @author JESUS
 *
 */
public class Ejercicio9_A {

	public static final String FICHERO_ALUMNOS = "ficheros/alumnos.csv";
	public static final String FICHERO_ALUMNOS_ORD= "ficheros/alumnos_ordenados.csv";
	public static List<Alumno> conjuntoAlumnos;

	public static void main(String[] args) {
		procesaFichero();
		System.out.println(conjuntoAlumnos);
		ordernaAlumnos();
		System.out.println(conjuntoAlumnos);
		generaFicheroOrdenado();
	}

	/**
	 * Establece un flujo de lectura entre el fichero y nuestro programa y genera una colecci�n de objetos tipo Alumno.
	 * @return Devolver� un List<Alumno> con los alumnos registrados en el fichero.
	 */
	public static void procesaFichero() {
		try (FileReader flujoLectura = new FileReader(FICHERO_ALUMNOS);
				BufferedReader filtroLectura = new BufferedReader(flujoLectura)){
			String linea = filtroLectura.readLine();
			while (linea != null) {
				procesaLinea(linea);
				linea = filtroLectura.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Procesar� una l�nea del fichero de alumnos y devolver� un objeto de tipo Alumno con la informaci�n.
	 * @param linea Ser� una l�nea del fichero con informaci�n del tipo "apellido1;apellido2;nombre;edad"
	 */
	public static void procesaLinea(String linea){
		Alumno alu = Alumno.procesaLinea(linea);
		if (conjuntoAlumnos==null) {
			conjuntoAlumnos = new ArrayList<>();
		}
		conjuntoAlumnos.add(alu);
	}

	/**
	 * Se valdr� del m�todo compareTo del comparable Alumno.
	 */
	public static void ordernaAlumnos() {
		Collections.sort(conjuntoAlumnos);
	}

	/**
	 * Ser� llamado tras el m�todo ordenar alumnos, por lo que la colecci�n conjuntoAlumnos estar� ordenada.
	 * Abrimos flujo de escritura sobre el fichero ordenado y pintamos l�nea a l�nea.
	 */
	public static void generaFicheroOrdenado() {
		try {
			FileWriter fw = new FileWriter(FICHERO_ALUMNOS_ORD);
			PrintWriter pw = new PrintWriter(fw);

			for (Alumno alu : conjuntoAlumnos) {
				pw.println(alu.toString_csv());
			}

			fw.close();
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

