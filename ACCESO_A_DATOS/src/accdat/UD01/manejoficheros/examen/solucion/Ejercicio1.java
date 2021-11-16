package accdat.UD01.manejoficheros.examen.solucion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

public class Ejercicio1 {
	
	private static String FIC_SUPER = "ficheros/supermercado.csv";
	
	private static String FIC_CL = "ficheros/clientes.csv";
	private static String FIC_PR = "ficheros/productos.csv";
	private static String FIC_PE = "ficheros/pedidos.csv";

	/**
	 * Se abrirán 3 PrintWriters, uno por cada fichero a escribir. Según el primer elemento de la línea csv (datos[0]) se volcará la 
	 * información en un fichero u otro.
	 * @param args
	 */
	public static void main(String[] args) {
		boolean primeraLineaClientes = true;
		boolean primeraLineaProductos = true;
		boolean primeraLineaPedidos = true;
		
		PrintWriter pwClientes = null;
		PrintWriter pwProductos = null;
		PrintWriter pwPedidos = null;
		
		try (FileReader fr = new FileReader(FIC_SUPER);
				BufferedReader br = new BufferedReader(fr)){
			
			pwClientes = new PrintWriter(new FileWriter(FIC_CL));
			pwProductos = new PrintWriter(new FileWriter(FIC_PR));
			pwPedidos = new PrintWriter(new FileWriter(FIC_PE));
			
			String linea = br.readLine();
			while (linea!=null){
				String[] datos = linea.split(";");
				// En función del primer elemento, se vuelca la info en su respectivo fichero.
				switch (datos[0]) {
				case "CL":
					if(!primeraLineaClientes) {
						pwClientes.print("\n");						
					}
					pwClientes.print(obtenerLinea(datos));
					primeraLineaClientes = false;
					break;
				case "PR":
					if(!primeraLineaProductos) {
						pwProductos.print("\n");						
					}
					pwProductos.print(obtenerLinea(datos));
					primeraLineaProductos = false;
					break;
				case "PE":
					if(!primeraLineaPedidos) {
						pwPedidos.print("\n");						
					}
					pwPedidos.print(obtenerLinea(datos));
					primeraLineaPedidos = false;
					break;							
				}
				linea = br.readLine();
			}			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			pwClientes.close();
			pwProductos.close();
			pwPedidos.close();
		}
	}
	
	// Se obtiene la línea en formato csv de la primera posición en adelante.
	public static String obtenerLinea(String[] datos) {
		String mensaje = "";
		
		for (int i=1; i<datos.length;i++) {
			mensaje += datos[i];
			
			if (i!=(datos.length-1)) {
				mensaje += ";";
			}
		}
		
		return mensaje;
	}

}

