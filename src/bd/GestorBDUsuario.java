package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Usuario;

public class GestorBDUsuario {
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
		String sql = "CREATE TABLE IF NOT EXISTS Usuario (" +"id INTEGER PRIMARY KEY AUTOINCREMENT, " +"nombre TEXT NOT NULL, " +"contraseña TEXT NOT NULL)";
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
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
	
	
	
	
	
}
