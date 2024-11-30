package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
	
	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "resources/db/database.db";
	protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;
	 
	public static Connection getConexion() throws SQLException {
		Connection connection = DriverManager.getConnection(CONNECTION_STRING);
		crearTablas(connection); // Asegura la creación de las tablas
        return connection;
	}
	
	 private static void crearTablas(Connection conn) {
	        String tablaUsuarios = """
	                CREATE TABLE IF NOT EXISTS usuarios (
	                    id INTEGER PRIMARY KEY AUTOINCREMENT,
	                    nombre TEXT NOT NULL,
	                    email TEXT UNIQUE NOT NULL,
	                    contrasenia TEXT NOT NULL
	                );
	                """;

	        String tablaPagos = """
	                CREATE TABLE IF NOT EXISTS pagos (
	                    id INTEGER PRIMARY KEY AUTOINCREMENT,
	                    id_cliente INTEGER NOT NULL,
	                    importe REAL NOT NULL,
	                    numero_tarjeta TEXT NOT NULL,
	                    fecha_caducidad TEXT NOT NULL,
	                    cvv TEXT NOT NULL,
	                    fecha_pago TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	                    FOREIGN KEY (id_cliente) REFERENCES usuarios (id)
	                );
	                """;

	        try (Statement stmt = conn.createStatement()) {
	            stmt.execute(tablaUsuarios);
	            stmt.execute(tablaPagos);
	            System.out.println("Tablas verificadas/creadas exitosamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Error al crear/verificar tablas", e);
	        }
	    }
}
