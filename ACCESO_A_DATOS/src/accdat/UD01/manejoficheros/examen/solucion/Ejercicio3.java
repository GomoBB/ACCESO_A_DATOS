/**
 *
 */
package accdat.UD01.manejoficheros.examen.solucion;

import java.io.IOException;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author JESUS
 *
 */
public class Ejercicio3 {

	private static final String FIC_SALIDA = "ficheros/Ejercicio3.xml";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SuperMercado.cargaInformacion();

		// 252, 250
		try {
			generaXML("251");
		} catch (ClassNotFoundException | ParserConfigurationException | TransformerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***
	 * Se prepara la infraestructura necesaria para la generación del XML y se llama al método annadirElementosAlArbol que se encargará
	 * de encapsular toda la lógica de generación de los nodos hijo.
	 *
	 * @param idCliente Cliente para el que generar el XML.
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void generaXML(String idCliente) throws ParserConfigurationException, TransformerException, ClassNotFoundException, IOException {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
		Document document = documentBuilder.getDOMImplementation().createDocument(null, "info_cliente", null);
		document.setXmlVersion("1.0");

		annadirElementosAlArbol(document, idCliente);

		// Vuelca el XML a un fichero XML:
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult(FIC_SALIDA));
	}

	/***
	 * Método para generar el árbol XML recorriendo la información cargada.
	 * @param document raíz del elemento xml previamente creada (info_cliente)
	 * @param idCliente Identificador del cliente a partir del cual obtener la información
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static void annadirElementosAlArbol(Document document, String idCliente) throws IOException, ClassNotFoundException {
		// Obtengo el objeto cliente de los clientes cargados en el objeto SuperMercado a partir del idCliente.
		Cliente c = SuperMercado.clientes.get(idCliente);

		// Referencia raiz a partir del cual crear hijos.
		Element raiz = document.getDocumentElement();

		// Se obtiene la información del cliente y se añade.
		Element cliente = document.createElement("cliente");

		Element nombreCliente = document.createElement("nombre");
		nombreCliente.setTextContent(c.nombre);
		Element apellido1 = document.createElement("apellido1");
		apellido1.setTextContent(c.apellido1);
		Element apellido2 = document.createElement("apellido2");
		apellido2.setTextContent(c.apellido2);

		cliente.appendChild(nombreCliente);
		cliente.appendChild(apellido1);
		cliente.appendChild(apellido2);
		// Fin información del cliente.

		// Se recorren los pedidos almacenados en el objeto de tipo Cliente.
		Element pedidos = document.createElement("pedidos");
		Collection<Pedido> lPedidos = c.pedidos.values();

		for (Pedido ped : lPedidos) {
			// Por cada pedido se crea un nodo "pedido" del que colgarán los productos.
			Element pedido = document.createElement("pedido");

			// Se añade la información con el idPedido
			Element idPedido = document.createElement("idPedido");
			idPedido.setTextContent(ped.idPedido);
			pedido.appendChild(idPedido);

			Element productos = document.createElement("productos");

			for (Producto prod : ped.productos) {
				// Por cada producto se añade la información.
				Element producto = document.createElement("producto");

				Element nombreProducto = document.createElement("nombre");
				nombreProducto.setTextContent(prod.nombreProducto);

				Element precio = document.createElement("precio");
				precio.setTextContent(prod.precioProducto);

				producto.appendChild(nombreProducto);
				producto.appendChild(precio);

				// Se añade el nodo "producto" como hijo i-ésimo del nodo "productos"
				productos.appendChild(producto);
			}
			// Se añade el nodo productos al nodo pedido.
			pedido.appendChild(productos);
			// Se añade el nodo pedido al nodo pedidos.
			pedidos.appendChild(pedido);
		}
		// Se añaden los hijos del nodo raíz.
		raiz.appendChild(cliente);
		raiz.appendChild(pedidos);
	}
}
