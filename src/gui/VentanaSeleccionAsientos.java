package gui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaSeleccionAsientos extends JFrame {
    private static int numFila = 6; 
    private static int asientosFila = 18;
    private static double precioEntrada = 10.0; 
    private static int totalAsientosSeleccionados = 0; 
    private JButton asientos, continuar, volver;
    private JLabel lblTotal, lblTitulo; 
    private JPanel pTitulo, pAsientos, pSur; 
    
    
    public VentanaSeleccionAsientos() {
        setTitle("Seleccione sus asientos"); 
        setBounds(300, 200, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // posición de la ventana en el centro de la pantalla
        setLayout(new BorderLayout());
        
        pTitulo = new JPanel();
        lblTitulo = new JLabel("Seleccione sus asientos");
        
        // Fuente específica para label con tamaño ajustado
        Font fuente = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        lblTitulo.setFont(fuente);
        pTitulo.add(lblTitulo);
        this.add(pTitulo, BorderLayout.NORTH);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        VentanaSeleccionAsientos v1 = new VentanaSeleccionAsientos();
    }
}
