/**
 *
 */
package accdat.UD02.accesobdrelacionales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author JESUS
 *
 */
public class Ejercicio2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection con = null;

		try {
			// Si trabajaramos con JDBC < 4.0 tendríamos que indicar esta línea
			// para indicar el tipo de driver que tiene que cargar DriverManager.
			// Class.forName("com.mysql.jdbc.Driver");

			// Obtenemos la conexión a partir de la URL jdbc correspondiente
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/PruebaConexionBD", "root", "");

			// Obtenermos una instancia de un objeto que implementa la interface statement.
			Statement st = con.createStatement();

			String sql = "SELECT * FROM ALUMNOS";

			ResultSet rs = st.executeQuery(sql);

			// De esta forma vamos a saber si hay tablas o no
			boolean hayFilas = false;
			while(rs.next()) {
				hayFilas = true;
				System.out.println(rs.getString("NOMBRE") + " " + rs.getString("APELLIDO1") + " " + rs.getString("APELLIDO2"));
			}
			if (!hayFilas) {
				System.out.println("No hay resultados que mostrar");
			}

			//Cerramos ResultSet y Statement
			rs.close();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}

}
