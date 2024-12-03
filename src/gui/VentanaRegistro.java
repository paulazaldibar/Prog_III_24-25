package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class VentanaRegistro extends JDialog {
	private static final long serialVersionUID = 1L;
	private JButton btnInicioSesion, btnRegistro, btnMostrarContrasenia;
   private JPanel pCentro, pNorte, pSur;
   private JLabel lblTitulo, lblNombreUsuario, lblContraseniaUsuario;
   private JTextField txtNombreUsuario;
   private JPasswordField txtContraseniaUsuario;
   private boolean contraseniaVisible = false;
  
   //Para que la imagen del ojo funcione bien introduzco nuevos parametro
   private ImageIcon iconoOjoAbierto;
   private ImageIcon iconoOjoCerrado;
  
   
   private void iniciarCarga() { // IAG, hilo hecho con ayuda de Chat GPT 
	    // Cerramos la ventana actual
	    this.dispose();

	    JDialog ventanaCargando = new JDialog(this, "Cargando...", true);
	    ventanaCargando.setSize(300, 150);
	    ventanaCargando.setLocationRelativeTo(null);
	    ventanaCargando.setLayout(new BorderLayout());
	    //ventanaCargando.setTitle("SkyMovie");
	    //ImageIcon imagen = new ImageIcon("resources/img/iconoSkyMovie.png");
	    //ventanaCargando.setIconImage(imagen.getImage());
	    ventanaCargando.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); //No se permite cerrar la ventana del JDialog

	    JLabel lblCargando = new JLabel("Cargando", SwingConstants.CENTER);
	    lblCargando.setFont(new Font("SansSerif", Font.BOLD, 16));
	    ventanaCargando.add(lblCargando, BorderLayout.CENTER);

	    // Hilo para ejecutar en orden los estados y simular el tiempo de carga   
	    Thread hiloCargando = new Thread(() -> {
	        String[] estados = {"Cargando", "Cargando.", "Cargando..", "Cargando..."}; // Cadena de strings que se ejecutarán en orden
	        int contador = 0;

	        long tiempoInicio = System.currentTimeMillis();
	        long tiempoCarga = 4500; // 4,5 segundos de carga simulada

	        while (System.currentTimeMillis() - tiempoInicio < tiempoCarga) {
	            lblCargando.setText(estados[contador % estados.length]);
	            contador++;
	            try {
	                Thread.sleep(500); // Cambia cada 500ms
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
	        }
	        // Cerramos la ventana de carga y abrimos la ventanaInicial
	        ventanaCargando.dispose();
	        SwingUtilities.invokeLater(() -> {
	            VentanaInicial ventanaInicial = new VentanaInicial();
	            ventanaInicial.setVisible(true);
	        });
	    });
	    hiloCargando.start();
	    ventanaCargando.setVisible(true);
	}
   
  
  
   public VentanaRegistro(JFrame parent) {
       super(parent, "SkyMovie", true);
       setBounds(300, 200, 600, 400);
       ImageIcon imagen = new ImageIcon("resources/img/iconoSkyMovie.png");
       setIconImage(imagen.getImage());
      
       iconoOjoAbierto = new ImageIcon("resources/img/ojo abierto.jpg");
       iconoOjoCerrado = new ImageIcon("resources/img/ojo cerrado.jpg");
      
       Image imgAbierto = iconoOjoAbierto.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
       Image imgCerrado = iconoOjoCerrado.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
      
       iconoOjoAbierto = new ImageIcon(imgAbierto);
       iconoOjoCerrado = new ImageIcon(imgCerrado);
      
       // Configuración de paneles
       pCentro = new JPanel();
       pCentro.setLayout(new GridBagLayout()); // Usamos GridBagLayout para que quede alineado
       pNorte = new JPanel();
       pSur = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
       getContentPane().add(pNorte, BorderLayout.NORTH);
       getContentPane().add(pCentro, BorderLayout.CENTER);
       getContentPane().add(pSur, BorderLayout.SOUTH);
       // Personalización de los botones
       btnInicioSesion = new JButton("Iniciar Sesión");
       btnRegistro = new JButton("Registrarse");
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
       pNorte.add(lblTitulo);
       // Etiquetas y campos de texto
       lblNombreUsuario = new JLabel("Introduce tu nombre de usuario:");
       lblNombreUsuario.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
       txtNombreUsuario = new JTextField(12); // Tamaño del campo de texto reducido
       txtNombreUsuario.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
       lblContraseniaUsuario = new JLabel("Introduce tu contraseña:");
       lblContraseniaUsuario.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
       txtContraseniaUsuario = new JPasswordField(12); // Tamaño del campo de texto reducido
       txtContraseniaUsuario.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
      
       btnMostrarContrasenia = new JButton(iconoOjoCerrado);
       btnMostrarContrasenia.setPreferredSize(new Dimension(20, 20));
       btnMostrarContrasenia.setFocusPainted(false);
       btnMostrarContrasenia.setContentAreaFilled(false);
       btnMostrarContrasenia.setBorderPainted(false);
      
       // Con esto conseguimos que los componentes queden centrados y en columnas alineadas (Chat GPT)
       GridBagConstraints gbc = new GridBagConstraints();
       gbc.fill = GridBagConstraints.HORIZONTAL;
       gbc.insets = new Insets(10, 10, 10, 10);
       // Etiqueta Nombre de Usuario
       gbc.gridx = 0;
       gbc.gridy = 0;
       gbc.anchor = GridBagConstraints.LINE_END;
       pCentro.add(lblNombreUsuario, gbc);
       // Campo de texto Nombre de Usuario
       gbc.gridx = 1;
       gbc.anchor = GridBagConstraints.LINE_START;
       pCentro.add(txtNombreUsuario, gbc);
       // Etiqueta Contraseña
       gbc.gridx = 0;
       gbc.gridy = 1;
       gbc.anchor = GridBagConstraints.LINE_END;
       pCentro.add(lblContraseniaUsuario, gbc);
       // Campo de texto Contraseña con el botón de mostrar/ocultar
       JPanel contraseniaPanel = new JPanel(new BorderLayout());
       contraseniaPanel.add(txtContraseniaUsuario, BorderLayout.CENTER);
       contraseniaPanel.add(btnMostrarContrasenia, BorderLayout.EAST);
       gbc.gridx = 1;
       gbc.anchor = GridBagConstraints.LINE_START;
       pCentro.add(contraseniaPanel, gbc);
       pSur.add(btnInicioSesion);
       pSur.add(btnRegistro);
       // ActionListener para mostrar/ocultar contraseña
       btnMostrarContrasenia.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               contraseniaVisible = !contraseniaVisible;
               if (contraseniaVisible) {
                   txtContraseniaUsuario.setEchoChar((char) 0);
                   btnMostrarContrasenia.setIcon(iconoOjoAbierto);
               } else {
                   txtContraseniaUsuario.setEchoChar('•');
                   btnMostrarContrasenia.setIcon(iconoOjoCerrado);
               }
           }
       });
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
       setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
           iniciarCarga();
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
                   iniciarCarga();
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
  
   public static void main(String[] args) {
       JFrame parent = new JFrame();
       new VentanaRegistro(parent);
   }
}


