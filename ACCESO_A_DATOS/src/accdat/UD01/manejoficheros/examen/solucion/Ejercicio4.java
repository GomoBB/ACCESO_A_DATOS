/**
 * 
 */
package accdat.UD01.manejoficheros.examen.solucion;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author JESUS
 *
 */
public class Ejercicio4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		
		try {
			// Preparación de la infraestructura necesaria para recorrer el documento
			DocumentBuilder builder=factory.newDocumentBuilder();  // crea un objeto builder que permite crear el arbol DOM
			Document arbol=builder.parse(new File("ficheros/Ejercicio3.xml"));
			
			// Se obtiene una referencia al nodo cliente (será un único nodo, por lo que accedemos directamente al 0).
			Element cliente = (Element) arbol.getElementsByTagName("cliente").item(0);
			String nombre   = cliente.getElementsByTagName("nombre").item(0).getTextContent();
			String ap1      = cliente.getElementsByTagName("apellido1").item(0).getTextContent();
			String ap2      = cliente.getElementsByTagName("apellido2").item(0).getTextContent();
			
			System.out.println("Cliente: ");			
			System.out.println("\t" + nombre);
			System.out.println("\t" + ap1);
			System.out.println("\t" + ap2);
			
			// Para los pedidos, se obtendrá un NodeList con todos ellos y se recorrerá
			System.out.println("Pedidos: ");
			
			NodeList pedidos = arbol.getElementsByTagName("pedido");
			
			for (int i=0; i<pedidos.getLength();i++) {
				// Por cada pedido...
				System.out.println("\t**");
				Element pedido = (Element) pedidos.item(i);
				
				// Se obtiene el id
				String idPedido = pedido.getElementsByTagName("idPedido").item(0).getTextContent();
				System.out.println("\tPedido: " + idPedido);
				
				// Se obtienen todos los nodos "nombre" que cuelgan de ese nodo "pedido"
				NodeList nombres = pedido.getElementsByTagName("nombre");
				System.out.print("\tProductos: ");				
				
				for (int j=0; j<nombres.getLength(); j++){
					// Se pintan en la misma línea todos los nombres.
					System.out.print(nombres.item(j).getTextContent());
										
					// Si es el último elemento se pinta un "." y salto de línea. Si no, se escriben los nobmres seguidos de comas.
					if(j!=(nombres.getLength()-1)) {
						System.out.print(", ");	
					}else {
						System.out.println(".");
					}
				}
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // crea el arbol DOM a partir del fichero
	}
}
