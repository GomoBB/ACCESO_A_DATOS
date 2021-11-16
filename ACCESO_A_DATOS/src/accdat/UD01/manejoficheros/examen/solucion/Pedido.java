package accdat.UD01.manejoficheros.examen.solucion;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para almacenar la información de un pedido: Su id y los productos que lo componen.
 * @author JESUS
 *
 */
public class Pedido {
	public String idPedido;
	public List<Producto> productos;
	
	public Pedido(String idPedido) {
		this.idPedido = idPedido;
		this.productos = new ArrayList<Producto>();
	}
}