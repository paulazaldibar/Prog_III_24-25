package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaRegistro extends JFrame{

	 private JButton btnInicioSesion, btnRegistro;
	    private JPanel pCentro, pNorte, pEste, pOeste, pSur;
	    private JLabel lblTitulo, lblNombreUsuario, lblContraseniaUsuario;
	    private JTextField txtNombreUsuario;
	    private JPasswordField txtContraseniaUsuario;

	    public VentanaRegistro() {
	        super();
	        setBounds(300, 200, 600, 400);

	        setTitle("SkyMovie");
	        ImageIcon imagen = new ImageIcon("resources/img/iconoSkyMovie.png");
	        setIconImage(imagen.getImage());

	        pCentro = new JPanel(new GridLayout(2, 2, 5, 5));
	        pNorte = new JPanel();
	        pSur = new JPanel();
	        pEste = new JPanel();
	        pOeste = new JPanel();

	        // Colores de fondo para hacerlo más moderno
	        pCentro.setBackground(Color.decode("#F2F2F2"));
	        pNorte.setBackground(Color.decode("#F2F2F2"));
	        pSur.setBackground(Color.decode("#F2F2F2"));
	        
	        getContentPane().add(pNorte, BorderLayout.NORTH);
	        getContentPane().add(pEste, BorderLayout.EAST);
	        getContentPane().add(pOeste, BorderLayout.WEST);
	        getContentPane().add(pSur, BorderLayout.SOUTH);
	        getContentPane().add(pCentro, BorderLayout.CENTER);

	        // Personalización de los botones
	        btnInicioSesion = new JButton("Iniciar Sesión");
	        btnRegistro = new JButton("Registrarse");

	        // Cambiar estilo de los botones
	        btnInicioSesion.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
	        btnInicioSesion.setBackground(new Color(70, 130, 180));
	        btnInicioSesion.setForeground(Color.WHITE);
	        btnInicioSesion.setFocusPainted(false);
	        btnInicioSesion.setPreferredSize(new Dimension(150, 40));
	        
	        btnRegistro.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
	        btnRegistro.setBackground(new Color(100, 70, 1));
	        btnRegistro.setForeground(Color.WHITE);
	        btnRegistro.setFocusPainted(false);
	        btnRegistro.setPreferredSize(new Dimension(150, 40));

	        // Título
	        lblTitulo = new JLabel("ONGI ETORRI!!");
	        lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
	        
	        // Etiquetas
	        lblNombreUsuario = new JLabel("Introduce tu nombre de usuario:");
	        lblNombreUsuario.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
	        
	        lblContraseniaUsuario = new JLabel("Introduce tu contraseña:");
	        lblContraseniaUsuario.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

	        // Campos de texto, más finos y con borde
	        txtNombreUsuario = new JTextField(15);
	        txtNombreUsuario.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
	        txtNombreUsuario.setBorder(BorderFactory.createCompoundBorder(
	                txtNombreUsuario.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

	        txtContraseniaUsuario = new JPasswordField(15);
	        txtContraseniaUsuario.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
	        txtContraseniaUsuario.setBorder(BorderFactory.createCompoundBorder(
	                txtContraseniaUsuario.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

	        // Añadir componentes a los paneles
	        pSur.add(btnInicioSesion);
	        pSur.add(btnRegistro);

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
