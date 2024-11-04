package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

	        btnRegistro.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					registrarUsuario();					
				}
			});
	        
	        btnInicioSesion.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					iniciarSesion();
					
				}
			});
	        
	        setVisible(true);
	}
	    
	private void registrarUsuario() {
		String usuario = txtNombreUsuario.getText();
	    String contrasenia = new String(txtContraseniaUsuario.getPassword());

	    if (usuario.isEmpty() || contrasenia.isEmpty()) {
	    	JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("ficheros/usuarios.txt", true))) {
	    	writer.write(usuario + ":" + contrasenia);
	        writer.newLine();
	        JOptionPane.showMessageDialog(this, "Usuario registrado con éxito");
	        redirigirVentanaInicial();
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    	JOptionPane.showMessageDialog(this, "Error al registrar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
	    }
     }
	
	private void iniciarSesion() {
        String usuario = txtNombreUsuario.getText();
        String contrasenia = new String(txtContraseniaUsuario.getPassword());

        if (usuario.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("ficheros/usuarios.txt"))) {
            String linea;
            boolean encontrado = false;

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(":");
                if (partes[0].equals(usuario) && partes[1].equals(contrasenia)) {
                    encontrado = true;
                    JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso");
                    redirigirVentanaInicial();
                    break;
                }
            }

            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "Usuario no registrado o contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al iniciar sesión", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	private void redirigirVentanaInicial() {
        this.dispose(); 
        new VentanaInicial();
    }
	    
	public static void main(String[] args) {
		VentanaRegistro v = new VentanaRegistro();
	}
}
