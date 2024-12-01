package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Esta va a ser la base de datos, para guardar los datos de los clientes
//al realizar sus compras al PAGAR
public class GestorBDPagar {
	public void guardarPago(int idCliente, double importe, String nombre, String numeroTarjeta, String fechaCaducidad, String cvv) {
        String sql = "INSERT INTO pagos (id_cliente, importe, nombre, numero_tarjeta, fecha_caducidad, cvv, fecha_pago) VALUES (?, ?, ?, ?, ?, ?, NOW())";
        
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ps.setDouble(2, importe);
            ps.setString(3, nombre);  // Se guarda el nombre ingresado en el formulario
            ps.setString(4, numeroTarjeta);
            ps.setString(5, fechaCaducidad);
            ps.setString(6, cvv);

            ps.executeUpdate();
            System.out.println("Pago guardado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar el pago", e);
        }
    }
}
