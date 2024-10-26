 package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaRegistro extends JFrame{

	private JButton btnInicioSesion, btnCierreSesion;
	private JPanel pCentro, pNorte, pEste, pOeste, pSur;
	private JLabel lblTitulo, lblNombreUsuario, lblContraseniaUsuario;
	private JTextField txtNombreUsuario;
	private JPasswordField txtContraseniaUsuario;
	
	public VentanaRegistro() {
		super();
		setBounds(300,200,600,400);
		
		setTitle("SkyMovie");
		ImageIcon imagen = new ImageIcon("resources/img/iconoSkyMovie.png");
		setIconImage(imagen.getImage());
		
		pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(2, 2));
		pNorte = new JPanel();
		pSur = new JPanel();
		pEste = new JPanel();
		pOeste = new JPanel();
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);

		
		btnInicioSesion = new JButton("Iniciar Sesion");
		btnCierreSesion = new JButton("Cerrar Sesion");
		
		btnInicioSesion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		btnCierreSesion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
	
		
		lblTitulo = new JLabel("ONGI ETORRI!!");
		lblNombreUsuario = new JLabel("Introduce tu nombre de usuario: ");
		lblContraseniaUsuario = new JLabel("Introduce tu contraseña: ");
		
		txtNombreUsuario = new JTextField(20);
		txtContraseniaUsuario = new JPasswordField(20);
		
		pSur.add(btnInicioSesion);
		pSur.add(btnCierreSesion);
		
		pNorte.add(lblTitulo);
		pCentro.add(lblNombreUsuario);
		pCentro.add(txtNombreUsuario);
		pCentro.add(lblContraseniaUsuario);
		pCentro.add(txtContraseniaUsuario);
		
		setVisible(true);
	}
	public static void main(String[] args) {
		VentanaRegistro v = new VentanaRegistro();
	}
}
