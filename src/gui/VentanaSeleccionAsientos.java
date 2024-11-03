package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaSeleccionAsientos extends JFrame {
    private static int numFila = 6; 
    private static int asientosFila = 18;
    private static double precioEntrada = 10.0; 
    private static int totalAsientosSeleccionados = 0; 
    private ArrayList<JButton> asientos; 
    private JButton btnAsiento, btnContinuar, btnVolver;
    private JLabel lblTotal, lblTitulo, lblSubtitulo; 
    private JPanel pTitulo, pAsientos, pSur; 
    
    
    public VentanaSeleccionAsientos() {
        setTitle("Seleccione sus asientos"); 
        setBounds(300, 200, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // posición de la ventana en el centro de la pantalla
        setLayout(new BorderLayout());
        
        pTitulo = new JPanel();
        pTitulo.setLayout(new BorderLayout());
        lblTitulo = new JLabel("Seleccione sus asientos", JLabel.CENTER);
        lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16)); // Fuente específica para label con tamaño ajustado
        lblSubtitulo = new JLabel("PANTALLA", JLabel.CENTER);
        lblSubtitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18)); // Fuente específica para label con tamaño ajustado

        pTitulo.add(lblTitulo, BorderLayout.NORTH);
        pTitulo.add(lblSubtitulo, BorderLayout.SOUTH);
        this.add(pTitulo, BorderLayout.NORTH);
        
        // Panel de asientos seleccionables
        pAsientos = new JPanel();
        pAsientos.setLayout(new GridLayout(numFila, asientosFila + 2, 10, 10)); // Le sumamos 2 que seran los pasillos
        pAsientos.setBorder(BorderFactory.createEmptyBorder(30,40,30,40)); // Dejamos un margen alrededor del panel
        
        asientos = new ArrayList<>(); // Inicializamos el ArrayList
        for(int i=0; i<numFila; i++) {
        	for(int j=0; j<asientosFila +2; j++) {
        		if(j==4 || j==15) { // Las columnas 4 y 15 son los pasillos
        			pAsientos.add(new JLabel("")); // Añadimos un espacio en blanco para simular el pasillo
        		}else {
        			btnAsiento = new JButton();
        			btnAsiento.setBackground(Color.GREEN);
        			btnAsiento.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(btnAsiento.getBackground() == Color.GREEN) {
								btnAsiento.setBackground(Color.RED);
								totalAsientosSeleccionados++;
								// si al principio el asiento esta verde, al seleccionarlo cambiará a rojo e implica que tenemos que sumarlo al número total de asiento seleccionados
							}else {
								btnAsiento.setBackground(Color.GREEN);
								totalAsientosSeleccionados--; 
								// si al principio el asiento esta rojo, al seleccionarlo cambiará a verde e implica que tenemos que restarlo del número total de asientos seleccionados
							}
							actualizarTotal();
						}
					});
        			asientos.add(btnAsiento); // Añadimos el boton al ArrayList
        			pAsientos.add(btnAsiento);
        		}
        	}
        }
        
        
        // Panel al sur de la ventana para mostrar el total a pagar por las entradas
        
        
        
        
        setVisible(true);
    }
    
    private void actualizarTotal() {
		// TODO Auto-generated method stub
		
	}
    
    public static void main(String[] args) {
        VentanaSeleccionAsientos v1 = new VentanaSeleccionAsientos();
    }
}
