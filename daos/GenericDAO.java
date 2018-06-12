package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class GenericDAO {
	
	// tanto desde la propia clase como de las que hereden(protected)
	protected Connection con = null;

	// carga del driver de conexion con jdbc:
	static {

		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			System.out.println("No encuentro el Driver");
		}

	}// end Static

	protected void conectar() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/endometriosis", "root", "12345");
		} catch (SQLException e) {
			System.out.println("Error al conectar con el servidor");
			e.printStackTrace();
		}

	}// end conectar

	protected void desconectar() {

		try {
			con.close();
		} catch (SQLException e) {
			System.out.println("Error al desconectar");
			e.printStackTrace();
		}
	}// end desconectar
}
