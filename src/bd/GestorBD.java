package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import domain.Asientos;
import domain.Pelicula;
import domain.Sesion;
import domain.Usuario;

public class GestorBD {
	public static Connection con;
	
	public static void initBD(String nombreBD)  {
		con = null;

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
			if (con != null) {
			    System.out.println("Conexión establecida correctamente.");
			} else {
			    System.err.println("Error al conectar con la base de datos.");
			}

		} catch (ClassNotFoundException e) {
	        System.err.println("Controlador JDBC no encontrado.");
			e.printStackTrace();
		} catch (SQLException e) {
	        System.err.println("Error al conectar a la base de datos.");
			e.printStackTrace();
		}
	}

	public static void closeBD() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void crearTablas() {
		//String sql = "CREATE TABLE IF NOT EXISTS Usuario (" +"id INTEGER PRIMARY KEY AUTOINCREMENT, " +"nombre TEXT NOT NULL, " +"contraseña TEXT NOT NULL)";
		String sql1 = "CREATE TABLE IF NOT EXISTS Usuario (" +"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
	              "nombre TEXT NOT NULL, " +"contraseña TEXT NOT NULL)";

		String sql2 = "CREATE TABLE IF NOT EXISTS Pelicula (" +"idPelicula INTEGER PRIMARY KEY AUTOINCREMENT, " +
	              "titulo TEXT NOT NULL, " +"director TEXT, " +"sinopsis TEXT, " +
	              "duracion TEXT, " +"fechaEstreno DATE, " +"actores TEXT, " +"rutaPortada TEXT)";

		String sql3 = "CREATE TABLE IF NOT EXISTS Sesion (" +"idSesion INTEGER PRIMARY KEY AUTOINCREMENT, " +
	              "dia DATE NOT NULL, " +"hora TIME NOT NULL, " +"idPelicula INTEGER NOT NULL, " +
	              "FOREIGN KEY (idPelicula) REFERENCES Pelicula(idPelicula) ON DELETE CASCADE)";

		String sql4 = "CREATE TABLE IF NOT EXISTS Reserva (" +"idReserva INTEGER PRIMARY KEY AUTOINCREMENT, " +
	              "idUsuario INTEGER NOT NULL, " +"idSesion INTEGER NOT NULL, " +
	              "FOREIGN KEY (idUsuario) REFERENCES Usuario(id) ON DELETE CASCADE, " +
	              "FOREIGN KEY (idSesion) REFERENCES Sesion(idSesion) ON DELETE CASCADE)";

		String sql5 = "CREATE TABLE IF NOT EXISTS asientos (" +"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
	              "fila INTEGER NOT NULL, " +"columna INTEGER NOT NULL, " +"ocupado BOOLEAN NOT NULL)";

		/*
		String sql2 = "CREATE TABLE IF NOT EXISTS Sesion (" +
                  "idSesion INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  "dia DATE NOT NULL, " +
                  "hora TIME NOT NULL, " +
                  "idPelicula INTEGER NOT NULL, " +
                  "FOREIGN KEY (idPelicula) REFERENCES Pelicula(idPelicula) ON DELETE CASCADE)";
		 */
		
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql1);
		    stmt.executeUpdate(sql2);
		    stmt.executeUpdate(sql3);
		    stmt.executeUpdate(sql4);
		    stmt.executeUpdate(sql5);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void borrarTablas() {
		String sql = "DROP TABLE IF EXISTS Usuario";
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//IAG (Chat GPT)
	public static void guardarAsiento(int fila, int columna, boolean ocupado) {
	    // Código para insertar o actualizar el estado del asiento en la base de datos
		String sql = "UPDATE asientos SET ocupado = ? WHERE fila = ? AND columna = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
		    stmt.setBoolean(1, ocupado);
		    stmt.setInt(2, fila);
		    stmt.setInt(3, columna);
		    stmt.executeUpdate();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}
	
	//IAG (Chat GPT)
	public static boolean consultarAsiento(int fila, int columna) {
		 String sql = "SELECT ocupado FROM asientos WHERE fila = ? AND columna = ?";
		    try (PreparedStatement stmt = con.prepareStatement(sql)) {
		        stmt.setInt(1, fila);
		        stmt.setInt(2, columna);
		        ResultSet rs = stmt.executeQuery();
		        if (rs.next()) {
		            return rs.getBoolean("ocupado");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return false;	
	}

	//IAG (Chat GPT)
	public static void marcarAsientoComoOcupado(Asientos asiento) {
	    String sql = "UPDATE asientos SET ocupado = TRUE WHERE fila = ? AND columna = ?";
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setInt(1, asiento.getFila());
	        stmt.setInt(2, asiento.getColumna());
	        stmt.executeUpdate(); // Ejecuta la actualización
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	//IAG (Chat GPT)
	public static void insertarSesion(Sesion s) {
	    String sql = "INSERT INTO Sesion (dia, hora, idPelicula) VALUES (?, ?, ?)";
	    try {
	    	PreparedStatement ps = con.prepareStatement(sql);
	        ps.setDate(1, java.sql.Date.valueOf(s.getDia())); // LocalDate to SQL Date
	        ps.setTime(2, java.sql.Time.valueOf(s.getHora())); // LocalTime to SQL Time
	        ps.setInt(3, s.getIdPelicula().getIdPelicula()); // Relación con Pelicula
	        ps.executeUpdate();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void insertarUsuario(Usuario u) {
	    String sql = "INSERT INTO Usuario (nombre, contraseña) VALUES (?, ?)";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, u.getNombre());
	        ps.setString(2, u.getContrasenia());
	        ps.execute();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	//IAG (Chat GPT)
	public static void insertarAsientoReservado(int idReserva, Asientos asiento) {
	    String sql = "INSERT INTO AsientoReservado (idReserva, fila, columna, ocupado) VALUES (?, ?, ?, ?)";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, idReserva);
	        ps.setInt(2, asiento.getFila());
	        ps.setInt(3, asiento.getColumna());
	        ps.setBoolean(4, asiento.isOcupado());
	        ps.executeUpdate();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public static void insertarPelicula(Pelicula pelicula) {
        String sql = "INSERT INTO Pelicula (titulo, director, sinopsis, duracion, fechaEstreno, actores, rutaPortada) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, pelicula.getTitulo());
            ps.setString(2, pelicula.getDirector());
            ps.setString(3, pelicula.getSinopsis());
            ps.setString(4, pelicula.getDuracion());
            ps.setString(5, pelicula.getFechaEstreno()); // Usa formato "YYYY-MM-DD"
            ps.setString(6, String.join(",", pelicula.getActores())); // Lista separada por comas
            ps.setString(7, pelicula.getRutaPortada());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
	public static void borrarUsuario(Usuario u) {
		String sql = "DELETE FROM Usuario VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, u.getId());
			ps.setString(2, u.getNombre());
			ps.setString(3, u.getContrasenia());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//IAG (Chat GPT)
	public static void borrarSesion(int idSesion) {
	    String sql = "DELETE FROM Sesion WHERE idSesion = ?";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, idSesion);
	        ps.executeUpdate();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	//IAG (Chat GPT)
	public static void borrarReserva(int idReserva) {
	    String sql = "DELETE FROM Reserva WHERE idReserva = ?";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, idReserva);
	        ps.executeUpdate();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	//IAG (Chat GPT)
	public static void borrarAsientosDeReserva(int idReserva) {
	    String sql = "DELETE FROM AsientoReservado WHERE idReserva = ?";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, idReserva);
	        ps.executeUpdate();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	//IAG (Chat GPT)
	public static void borrarPelicula(int idPelicula) {
	    String sql = "DELETE FROM Pelicula WHERE idPelicula = ?";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, idPelicula);
	        ps.executeUpdate();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	public static Usuario obtenerUsuarioPorId(int id) {
	    Usuario usuario = null;
	    String sql = "SELECT * FROM Usuario WHERE id = ?";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) { // Si se encuentra un usuario con el ID dado
	            String nombre = rs.getString("nombre");
	            String contrasenia = rs.getString("contraseña");
	            usuario = new Usuario(id, nombre, contrasenia);
	        }
	        rs.close();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return usuario;
	}
	
	// Método para obtener un usuario por nombre y contraseña
    public static Usuario obtenerUsuarioPorNombreYContrasenia(String nombre, String contrasenia) {
        Usuario usuario = null;
        String sql = "SELECT * FROM Usuario WHERE nombre = ? AND contraseña = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, contrasenia);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                usuario = new Usuario(id, nombre, contrasenia);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

	public static boolean existeUsuario(int idU) {
		boolean existe = false;
		String sql = "SELECT * FROM Usuario WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idU);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) { //Si la SELECT SÍ ha devuelto información
				existe = true;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}
	
	public static List<Usuario> obtenerTodosLosUsuarios(){
		List<Usuario> listaUsuarios = new ArrayList<>();
		String sql = "SELECT * FROM Usuario";
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) { //Recorremos todas las tuplas/filas del ResultSet
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String contrasenia = rs.getString("contraseña");
				Usuario u = new Usuario(id, nombre, contrasenia);
				listaUsuarios.add(u);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaUsuarios;
	}
	
	//IAG (Chat GPT)
	public static List<Asientos> obtenerAsientosPorReserva(int idReserva) {
	    List<Asientos> asientos = new ArrayList<>();
	    String sql = "SELECT fila, columna FROM AsientoReservado WHERE idReserva = ?";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, idReserva);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            int fila = rs.getInt("fila");
	            int columna = rs.getInt("columna");
	            asientos.add(new Asientos(fila, columna)); // Constructor para Asientos
	        }
	        rs.close();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return asientos;
	}

	//IAG (Chat GPT)
	public static void actualizarEstadoAsiento(int idReserva, int fila, int columna, boolean ocupado) {
	    String sql = "UPDATE AsientoReservado SET ocupado = ? WHERE idReserva = ? AND fila = ? AND columna = ?";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setBoolean(1, ocupado);
	        ps.setInt(2, idReserva);
	        ps.setInt(3, fila);
	        ps.setInt(4, columna);
	        ps.executeUpdate();
	        ps.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}
