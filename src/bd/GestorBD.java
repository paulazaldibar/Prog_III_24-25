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
import domain.Sesion;
import domain.Usuario;

public class GestorBD {
	private static Connection con;
	
	public static void initBD(String nombreBD)  {
		con = null;

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
		String sql1 = "CREATE TABLE IF NOT EXISTS Usuario (" +"id INTEGER PRIMARY KEY AUTOINCREMENT, " +"nombre TEXT NOT NULL, " +"contraseña TEXT NOT NULL)";
		String sql2 = "CREATE TABLE IF NOT EXISTS Sesion (" +"idSesion INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "dia DATE NOT NULL, " +"hora TIME NOT NULL, " +"idPelicula INTEGER, " +
                "FOREIGN KEY (idPelicula) REFERENCES Pelicula(id) ON DELETE CASCADE)";
		/*
		String sql2 = "CREATE TABLE IF NOT EXISTS Sesion (" +
                  "idSesion INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  "dia DATE NOT NULL, " +
                  "hora TIME NOT NULL, " +
                  "idPelicula INTEGER NOT NULL, " +
                  "FOREIGN KEY (idPelicula) REFERENCES Pelicula(idPelicula) ON DELETE CASCADE)";
		 */
		String sql3 = "CREATE TABLE IF NOT EXISTS Reserva (" +
                "idReserva INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idUsuario INTEGER NOT NULL, " +
                "idSesion INTEGER NOT NULL, " +
                "FOREIGN KEY (idUsuario) REFERENCES Usuario(id) ON DELETE CASCADE, " +
                "FOREIGN KEY (idSesion) REFERENCES Sesion(idSesion) ON DELETE CASCADE)";

		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql2);
			stmt.executeUpdate(sql3);
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
	
	public static void insertarSesion(Sesion s) {
	    String sql = "INSERT INTO Sesion (dia, hora, idPelicula) VALUES (?, ?, ?)";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setDate(1, java.sql.Date.valueOf(s.getDia())); // Convertir LocalDate a Date
	        ps.setTime(2, java.sql.Time.valueOf(s.getHora())); // Convertir LocalTime a Time
	        ps.setInt(3, s.getIdPelicula().getIdPelicula()); // Suponiendo que tienes un método getId() en Pelicula
	        ps.execute();
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
