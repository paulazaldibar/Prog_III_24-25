package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaSeleccionAsientos extends JFrame{
	private static int numFila = 6; 
	private static int asientosFila = 18;
	private static double precioEntrada = 10.0; 
	private static int totalAsientosSeleccionados = 0; 
	private JButton asientos, continuar, volver;
	private JLabel lblTotal; 
	private JPanel pTitulo, pAsientos, pSur; 
	
	
	public VentanaSeleccionAsientos(){
		setTitle("Seleccione sus asientos"); 
		setBounds(300,200,600,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		//pTitulo = new JPanel();
		
	}
	
	
}
