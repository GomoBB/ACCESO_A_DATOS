package accdat.UD01.manejoficheros.examen.solucion;

/**
 * Clase para almacenar la información de un producto: id, nombre y precio.
 * @author JESUS
 *
 */
public class Producto {
	public String idProducto, nombreProducto, precioProducto;

	public Producto(String idProducto, String nombreProducto, String precioProducto) {
		super();
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.precioProducto = precioProducto;
	}
}