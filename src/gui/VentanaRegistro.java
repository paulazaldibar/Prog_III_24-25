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
	//Declaramos los componentes boton
	private JButton btnInicioSesion, btnCierreSesion;
	//Declaramos los paneles
	private JPanel pCentro, pNorte, pEste, pOeste, pSur;
	//Declaramos los componentes etiqueta
	private JLabel lblTitulo, lblNombreUsuario, lblContraseniaUsuario;
	//Declaramos los componente cuadro de texto
	private JTextField txtNombreUsuario;
	private JPasswordField txtContraseniaUsuario;
	
	public VentanaRegistro() {
		super();
		setBounds(300,200,600,400);
		
		//Editar el nombre de la ventana
		setTitle("SkyMovie");
		//Ponerle un icono personalizado a la ventana (esquina superior izquierda)
		ImageIcon imagen = new ImageIcon("img/iconoSkyMovie.png");
		setIconImage(imagen.getImage());
		
		//Instanciamos los paneles
		pCentro = new JPanel();
		//Modificamos el Layout del panel centro
		pCentro.setLayout(new GridLayout(2, 2));
		pNorte = new JPanel();
		pSur = new JPanel();
		pEste = new JPanel();
		pOeste = new JPanel();
		
		//Añadimos los paneles al panel principal de la ventana
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pEste, BorderLayout.EAST);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);

		
		//Instanciamos los componentes botón
		btnInicioSesion = new JButton("Iniciar Sesion");
		btnCierreSesion = new JButton("Cerrar Sesion");
		
		//Cambiamos el tipo de letra en los botones
		btnInicioSesion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		btnCierreSesion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
	
		
		lblTitulo = new JLabel("ONGI ETORRI!!");
		lblNombreUsuario = new JLabel("Introduce tu nombre de usuario: ");
		lblContraseniaUsuario = new JLabel("Introduce tu contraseña: ");
		
		txtNombreUsuario = new JTextField(20);
		txtContraseniaUsuario = new JPasswordField(20);
		
		//Añadimos el boton al panel sur de la ventana
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
