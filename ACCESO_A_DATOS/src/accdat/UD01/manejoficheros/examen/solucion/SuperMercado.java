package accdat.UD01.manejoficheros.examen.solucion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase envoltorio que contendrá la información para resolver el sistema.
 *
 * @author JESUS
 *
 */
public class SuperMercado {
	private static String FIC_CLIENTES = "ficheros/clientes.csv";
	private static String FIC_PRODUCTOS = "ficheros/productos.csv";
	private static String FIC_PEDIDOS = "ficheros/pedidos.csv";

	// Catálogo de cliente: Map de clientes para obtener un objeto de tipo cliente a partir de su id.
	public static Map<String, Cliente> clientes;

	// Catálogo de productos: Map de clientes para obtener un objeto de tipo cliente a partir de su id.
	public static Map<String, Producto> productos;

	/***
	 * Método que carga la información necesaria.
	 */
	public static void cargaInformacion() {
		cargaClientes();
		cargaProductos();
		cargaPedidosClientes();
	}

	/**
	 * Recorrerá el archivo de clientes creando objetos de tipo Cliente y añadiéndolos a la lista de clientes.
	 */
	public static void cargaClientes() {
		if (clientes==null) {
			clientes = new HashMap<>();
		}

		try(FileReader fr = new FileReader(FIC_CLIENTES);
				BufferedReader br = new BufferedReader(fr)){
			String linea = br.readLine();

			while(linea!=null) {
				String[] datos = linea.split(";");

				Cliente c = new Cliente(datos[0], datos[1], datos[2], datos[3]);

				// Añado el cliente referenciado por su id.
				clientes.put(datos[0], c);

				linea = br.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Num clientes: " + clientes.size());
	}

	/**
	 * Recorrerá el archivo de productos creando objetos de tipo Producto y añadiéndolos a la lista de productos.
	 */
	public static void cargaProductos() {
		if (productos==null) {
			productos = new HashMap<>();
		}

		try(FileReader fr = new FileReader(FIC_PRODUCTOS);
				BufferedReader br = new BufferedReader(fr)){
			String linea = br.readLine();

			while(linea!=null) {
				String[] datos = linea.split(";");

				Producto p = new Producto(datos[0], datos[1], datos[2]);

				// Añado el Producto referenciado por su id.
				productos.put(datos[0], p);

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
	 * Recorrerá el archivo de pedidos añadiendo a cada uno de los pedidos de un cliente los productos que contiene.
	 */
	public static void cargaPedidosClientes() {
		try(FileReader fr = new FileReader(FIC_PEDIDOS);
				BufferedReader br = new BufferedReader(fr)){
			String linea = br.readLine();

			while(linea!=null) {
				String[] datos = linea.split(";");

				String idPedido  = datos[0];
				String idCliente = datos[1];
				String idProducto = datos[2];

				// Obtener el cliente del catálogo.
				Cliente cli = clientes.get(idCliente);

				// Obtener el pedido de su lista de pedidos.
				Pedido ped = cli.pedidos.get(idPedido);

				// Si el pedido es nulo es porque es el primer producto que se está leyendo de ese pedido.
				if (ped==null) {
					// Se crea y se añade.
					ped = new Pedido(idPedido);
					cli.pedidos.put(idPedido, ped);
				}

				// Añado a los productos del pedido, el producto obtenido a partir del catálogo.
				Producto prod = SuperMercado.productos.get(idProducto);
				ped.productos.add(prod);

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
}
