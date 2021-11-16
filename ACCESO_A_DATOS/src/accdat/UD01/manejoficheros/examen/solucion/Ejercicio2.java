package accdat.UD01.manejoficheros.examen.solucion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Ejercicio2 {

	private static String FIC_CL = "ficheros/clientes.csv";
	private static String FIC_CARTA = "ficheros/carta_a_incluir.txt";
	
	/**
	 * Se generará una carta por cada uno de los clientes almacenados en clientes.csv.
	 * @param args
	 */
	public static void main(String[] args) {
		try(FileReader fr = new FileReader(FIC_CL);
				BufferedReader br = new BufferedReader(fr)){
			String lineaCliente = br.readLine();
			while(lineaCliente!=null) {				
				generaCarta(lineaCliente, FIC_CARTA);
				lineaCliente = br.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void generaCarta(String lineaDatos, String nombreCarta) {
		String[] datos = lineaDatos.split(";");
		
		// En datos[0] está el id, comprobamos que la carpeta no esté creada, en cuyo caso la crearemos.
		File fDir = new File("ficheros/" + datos[0]);		
		if (!fDir.exists()) {
			fDir.mkdir();
		}
		
		// Se obtiene el nombre en formato "natural" del cliente. 
		String nomCarCliente = obtenerNomCar(datos);
		
		// Obtenemos el nombre del fichero con su respectivo sufijo (índice) en caso de ser necesario.
		String nomFicCliente = obtenerNomFic(datos, 0);
		
		// En primer lugar lo obtengo sin índice y como hijo de la carpeta creada con el id.
		File fCarta = new File(fDir, nomFicCliente);
		int i = 1;
		
		// Mientras el fichero exista, obtengo un nuevo nombre añadiéndole un índice, hasta que consiga un archivo nuevo
		// sin machacar ninguna carta del histórico.
		while(fCarta.exists()) {
			nomFicCliente = obtenerNomFic(datos, i);
			fCarta = new File(fDir, nomFicCliente);
			i++;
		}
		
		//El archivo y la carpeta contenedora han sido creados en este punto, por tanto, sólo falta crear la carta incluyendo
		// la plantilla contenida en el archivo cuyo nombre ha sido pasado como parámetro.
		PrintWriter pw = null;
		// 
		try(FileReader fr = new FileReader(FIC_CARTA);
				BufferedReader br = new BufferedReader(fr);){
			
			pw = new PrintWriter(fCarta);
			
			// Cabecera de la carta.
			pw.println("Estimado " + nomCarCliente + ":");
			pw.println("");
			
			// Incluyo línea a línea la plantilla.
			String lineaPlantilla = br.readLine();
			while (lineaPlantilla!=null) {
				pw.println(lineaPlantilla);
				lineaPlantilla = br.readLine();
			}
			
			// Finalización de la carta.
			pw.println("");
			pw.println("Reciba un cordial saludo.");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pw.close();
		}
	}
	
	/**
	 * Genera el nomnbre natural del cliente.
	 * @param datos Array con los datos almacenados en una línea del archivo clientes.csv.
	 * @return
	 */
	public static String obtenerNomCar(String[] datos) {
		String nombre = datos[1] + " " + datos[2] + " " + datos[3];		
		return nombre;
	}
	
	/**
	 * Devuelve una cadena con el nombre del fichero formado por las posiciones 1, 2 y 3 del vector, añadiéndole un 
	 * índice en caso de que éste sea distinto de 0.
	 * @param datos
	 * @param indice
	 * @return
	 */
	public static String obtenerNomFic(String[] datos, int indice) {
		String nombre = datos[3] + datos[2] + datos[1];		
		
		if (indice>0){
			nombre += "_" + indice + ".txt";
		}else {
			nombre += ".txt";
		}
		
		return nombre;
	}

}

