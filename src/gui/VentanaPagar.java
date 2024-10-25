package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaPagar extends JFrame{
	private JButton btnPagar; 
	private JPanel pCentro, pNorte, pSur; 
	private JLabel lblTitulo, lblNombre, lblTarjeta, lblFecha, lblCVV;
	private JTextField txtNombre, txtTarjeta, txtFecha, txtCVV; 
	private JCheckBox checkTerminos;
	private JTextArea txtCondiciones; 
	
	public VentanaPagar() {
		super();
		setBounds(300,200,600,400);
		
		setTitle("SkyMovie");
		
		ImageIcon imagen = new ImageIcon("resources/img/iconoSkyMovie.png");
		setIconImage(imagen.getImage());
		
		pCentro = new JPanel();
		pNorte = new JPanel(); 
		pSur = new JPanel(); 
		
		
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		pCentro.setLayout(new GridLayout(6, 2, 10, 10)); // 6 filas, 2 columnas
		pCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // para dejar espacio en los bordes

		btnPagar = new JButton("PAGAR");
		
		btnPagar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		
		lblTitulo = new JLabel("Introduzca sus datos para realizar el pago:");
		lblNombre = new JLabel("Nombre: ");
		lblTarjeta = new JLabel("Número de tarjeta: ");
		lblFecha = new JLabel("Fecha de caducidad: ");
		lblCVV = new JLabel("CVV: "); 
		
		txtNombre = new JTextField(50);
		txtTarjeta = new JTextField(20);
		txtFecha = new JTextField("MM/AA", 5);
		txtCVV = new JTextField(3);
		
		checkTerminos = new JCheckBox("Aceptar términos y condiciones");
		
		txtCondiciones = new JTextArea("Al proceder con la compra, usted acepta los términos y condiciones," 
									+ "así como el uso de sus datos personales para fines de gestión de compra "
									+ "y entrega de servicios. La información será tratada de acuerdo con la "
									+ "legislación vigente en materia de protección de datos y nuestra política"
									+ " de privacidad, la cual puede consultar en nuestra página web.");
		txtCondiciones.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10)); 
		
		//txtCondiciones.setLineWrap(true);
       // txtCondiciones.setWrapStyleWord(true);
        txtCondiciones.setEditable(false); // Solo lectura
        txtCondiciones.setBackground(getBackground()); // Sin fondo visible
		 
		
		
		pSur.add(btnPagar);
		pNorte.add(lblTitulo);
		pCentro.add(lblNombre);
		pCentro.add(txtNombre);
		pCentro.add(lblTarjeta);
		pCentro.add(txtTarjeta);
		pCentro.add(lblFecha);
		pCentro.add(txtFecha);
		pCentro.add(lblCVV); 
		pCentro.add(txtCVV);
		pCentro.add(checkTerminos);
		pCentro.add(txtCondiciones);
		
		setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		VentanaPagar vPagar = new VentanaPagar();
	}
	
}
