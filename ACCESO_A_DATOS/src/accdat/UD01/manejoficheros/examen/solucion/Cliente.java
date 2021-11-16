package accdat.UD01.manejoficheros.examen.solucion;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase para almacenar la información del cliente del supermercado.
 * Contendrá un Map de pedidos referenciados por su id.
 * @author JESUS
 *
 */
public class Cliente {
	public String idCliente, nombre, apellido1, apellido2;	
	public Map<String, Pedido> pedidos;

	public Cliente(String idCliente, String nombre, String apellido1, String apellido2) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.pedidos = new HashMap<String, Pedido>();
	}
}