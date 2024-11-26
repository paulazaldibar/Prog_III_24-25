package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VentanaPagar extends JDialog {
    
	private static final long serialVersionUID = 1L;
	private JButton btnPagar, btnVolver, btnCancelar;
    private JPanel pCentro, pNorte, pSur, pCentroDatos;
    private JLabel lblTitulo, lblNombre, lblTarjeta, lblFecha, lblCVV, lblTotal;
    private JTextField txtNombre, txtTarjeta, txtFecha, txtCVV, txtTotal;
    private JCheckBox checkTerminos;
    private JTextArea txtCondiciones;

    //Requisitos a cumplir a la hora de validar los campos
    private boolean validarNombre(String nombre) {
        return nombre.matches("[a-zA-Z]+");  // Validación para que solo se introduzcan letras
    }
    
    private boolean validarTarjeta(String numeroTarjeta) {
        numeroTarjeta = numeroTarjeta.replaceAll("\\s", ""); 
        return numeroTarjeta.matches("\\d{16}"); // Verificar que tiene exactamente 16 dígitos
    }
    
    private boolean validarFecha(String fecha) {
        return fecha.matches("^(0[1-9]|1[0-2])/(0[1-9]|1[0-9]|2[0-9]|3[0-1])$"); // Validación para fecha de caducidad en formato MM/AA
    }
    
    private boolean validarCVV(String cvv) {
        return cvv.matches("\\d{3}"); // Validación para el CVV: debe ser solo números y exactamente 3 dígitos
    }
    
    
    //Hilo que genera al pulsar el boton pagar un hilo con una progress bar, cargando 
    //la aceptacion de la compra y abriendo al finalizar la ventana inicial
    public void hiloBarraProgreso(){
    	
    	JDialog ventanaProgreso = new JDialog(this, true);
    	ventanaProgreso.setSize(300,120);
    	ventanaProgreso.setLayout(new BorderLayout());
    	ventanaProgreso.setLocationRelativeTo(null); //Para que este centrada en la ventana
    	ventanaProgreso.setTitle("SkyMovie");
    	
        ImageIcon imagen = new ImageIcon("resources/img/iconoSkyMovie.png");
        ventanaProgreso.setIconImage(imagen.getImage());
    	
    	JLabel etiquetaCargando = new JLabel("Procesando la compra...", JLabel.CENTER);
    	JProgressBar progressBar = new JProgressBar(0, 100);
    	progressBar.setStringPainted(false); //No aparezca el porcentaje unicamente la barra en crecimiento
    	
    	ventanaProgreso.add(etiquetaCargando, BorderLayout.NORTH);
    	ventanaProgreso.add(progressBar, BorderLayout.CENTER);
    	
    	Thread t = new Thread(() -> {
    		
                try {
                	for(int i = 0; i<=100; i++) {
            			progressBar.setValue(i); //Va actualizando los valores de la progress bar para que vaya progresando
            			Thread.sleep(30); //Cada cuanto tiene que cargarse de nuevo
                	}
                	ventanaProgreso.dispose(); //cerrar el JDialog de ventana progreso
                	
                	SwingUtilities.invokeLater(() -> {
                		VentanaInicial ventanaInicial = new VentanaInicial();
                		ventanaInicial.setVisible(true);
                	});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    	});
    	t.start();
    	ventanaProgreso.setVisible(true);
    }
    
    public VentanaPagar(double total) {
        super();
        setBounds(300, 200, 600, 430);
        setLocationRelativeTo(null); // Con esta línea la ventana se centrará en la pantalla

        setTitle("SkyMovie");
        ImageIcon imagen = new ImageIcon("resources/img/iconoSkyMovie.png");
        setIconImage(imagen.getImage());

        pCentro = new JPanel(new BorderLayout(10, 10));
        pNorte = new JPanel();
        pSur = new JPanel();
        pCentroDatos = new JPanel(new GridLayout(6, 2, 10, 10));

        getContentPane().add(pCentro, BorderLayout.CENTER);
        getContentPane().add(pNorte, BorderLayout.NORTH);
        getContentPane().add(pSur, BorderLayout.SOUTH);

        pCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btnPagar = new JButton("PAGAR");
        btnPagar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        
        btnVolver = new JButton("VOLVER"); // Este botón cerrará la ventanaPagar y nos devolverá a la ventanaSeleccionAsientos
        btnVolver.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        
        btnCancelar = new JButton("CANCELAR");
        btnCancelar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

        lblTitulo = new JLabel("Introduzca sus datos para realizar el pago:");
        lblNombre = new JLabel("Nombre: ");
        lblTarjeta = new JLabel("Número de tarjeta: ");
        lblFecha = new JLabel("Fecha de caducidad: ");
        lblCVV = new JLabel("CVV: ");
        lblTotal = new JLabel("Total a pagar: ");
        lblTotal.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        txtNombre = new JTextField(50);
        //IA GENERATIVA 
        // Hacer que la primera letra del nombre sea mayúscula
        txtNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String texto = txtNombre.getText();
                
                // Si el texto no está vacío y la primera letra es minúscula
                if (!texto.isEmpty() && Character.isLowerCase(texto.charAt(0))) {
                    // Convertimos la primera letra a mayúscula
                    texto = Character.toUpperCase(texto.charAt(0)) + texto.substring(1);
                    txtNombre.setText(texto); // Actualizamos el texto del JTextField
                }
            }
        });

        txtTarjeta = new JTextField(20);
        //IA GENERATIVA 
        //PARA HACER UNA PRUEBA CON EL TEXTFIELD DE LOS NUMEROS DE LA TARJETA
        txtTarjeta.addKeyListener(new KeyAdapter() {
        	
        	@Override
            public void keyReleased(KeyEvent e) {
        		String tarjeta = txtTarjeta.getText().replaceAll("\\s", ""); // Eliminar espacios
                if (tarjeta.length() > 16) { 
                    tarjeta = tarjeta.substring(0, 16); // Limitar a 16 dígitos sin formatear
                }
                StringBuilder formateado = new StringBuilder();

                // Añadir espacio cada 4 caracteres para el número de tarjeta
                for (int i = 0; i < tarjeta.length(); i++) {
                    if (i > 0 && i % 4 == 0) {
                        formateado.append(" "); // Añadir un espacio después de cada 4 dígitos
                    }
                    formateado.append(tarjeta.charAt(i));
                }

                // Limitar el tamaño máximo de caracteres (16 dígitos y 1 letra)
                /*
                if (formateado.length() > 19) {
                    formateado.setLength(19); // 16 dígitos + 3 espacios
                }
				*/
                txtTarjeta.setText(formateado.toString()); // Actualizar el texto en el JTextField
            }
		});
        
        txtFecha = new JTextField("MM/AA", 5);
       
        
        txtCVV = new JTextField(3);
        //IA GENERATIVA 
        // Limitar el campo CVV a 3 dígitos numéricos
        txtCVV.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                // Verifica si el carácter ingresado es un número
                if (!Character.isDigit(c)) {
                    e.consume(); // Evita que se ingrese un carácter no numérico
                }
                
                // Limita la longitud del campo a 3 caracteres
                if (txtCVV.getText().length() >= 3) {
                    e.consume(); // Evita que se ingrese más de 3 dígitos
                }
            }
        });

        
        txtTotal = new JTextField(String.format("€%.2f", total), 10);
        txtTotal.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        txtTotal.setEditable(false); // No se podrá editar este campo

        checkTerminos = new JCheckBox("Aceptar términos y condiciones");

        txtCondiciones = new JTextArea("Al proceder con la compra, usted acepta los términos y condiciones,"
                + " así como el uso de sus datos personales para fines de gestión de compra "
                + "y entrega de servicios. La información será tratada de acuerdo con la "
                + "legislación vigente en materia de protección de datos y nuestra política"
                + " de privacidad, la cual puede consultar en nuestra página web.");
        txtCondiciones.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
        txtCondiciones.setLineWrap(true); // Permite hacer saltos de línea
        txtCondiciones.setWrapStyleWord(true); // En los saltos de línea separa las palabras completas, no las separa a la mitad
        txtCondiciones.setEditable(false); // No permite modificar el texto
        txtCondiciones.setBackground(getBackground()); // Mantiene el color de fondo puesto por defecto
 
        // Añadimos el título en el panel norte
        pNorte.add(lblTitulo);
        
        // Añadimos los componentes al panel de datos
        pCentroDatos.add(lblTotal);
        pCentroDatos.add(txtTotal);
        pCentroDatos.add(lblNombre);
        pCentroDatos.add(txtNombre);
        pCentroDatos.add(lblTarjeta);
        pCentroDatos.add(txtTarjeta);
        pCentroDatos.add(lblFecha);
        pCentroDatos.add(txtFecha);
        pCentroDatos.add(lblCVV);
        pCentroDatos.add(txtCVV);
        pCentroDatos.add(checkTerminos);

        // Añadimos los datos y el área de texto al panel central
        pCentro.add(pCentroDatos, BorderLayout.NORTH);
        pCentro.add(txtCondiciones, BorderLayout.CENTER);

        // Añadimos el botón al panel sur dejando un pequeño borde
        pSur.add(btnVolver); 
        pSur.add(btnPagar);
        pSur.add(btnCancelar);
        pSur.setLayout(new GridLayout(1, 2, 5, 20)); // Distribuye los botones horizontalmente con un espacio de 10px entre ellos
        pSur.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        setVisible(true);
        
        btnVolver.addActionListener((e) ->{
        	VentanaSeleccionAsientos ventanaSeleccionAsientos = new VentanaSeleccionAsientos();
            ventanaSeleccionAsientos.reiniciarSeleccion();
            this.dispose();
        });
        
        btnCancelar.addActionListener((e) -> {
        	VentanaInicial ventanaInicial = new VentanaInicial();
        	ventanaInicial.setVisible(true); 
        	
            this.dispose(); 
        });
        
        
        
     // Añadimos un action listener que compruebe que todos los datos se han rellenado para poder realizar la compra
        btnPagar.addActionListener(e ->{
        	String nombre = txtNombre.getText();
        	String numeroTarjeta = txtTarjeta.getText();
        	String fecha = txtFecha.getText();
        	String cvv = txtCVV.getText();
        	
        	if(txtNombre.getText().isEmpty() || txtTarjeta.getText().isEmpty() || txtFecha.getText().isEmpty() || txtCVV.getText().isEmpty() || !checkTerminos.isSelected()) {
        		JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos y acepte los términos y condiciones.", "No se ha podido realizar el pago", JOptionPane.WARNING_MESSAGE);
        	}else if (!validarNombre(nombre)){
        		JOptionPane.showMessageDialog(this, "El nombre solo contiene letras", "Error en el nombre", JOptionPane.WARNING_MESSAGE);
        	}else if (!validarTarjeta(numeroTarjeta)){
        		JOptionPane.showMessageDialog(this, "El número de tarjeta debe contener 16 números", "Error en la tarjeta", JOptionPane.WARNING_MESSAGE);
        	}else if (!validarFecha(fecha)){
        		JOptionPane.showMessageDialog(this, "La fecha de caducidad debe tener el formato correcto", "Error en la fecha", JOptionPane.WARNING_MESSAGE);
        	}else if (!validarCVV(cvv)){
        		JOptionPane.showMessageDialog(this, "El CVV debe ser un número de 3 dígitos.", "Error en el CVV", JOptionPane.WARNING_MESSAGE);
        	}
        	else {
        		JOptionPane.showMessageDialog(this, "Su compra se ha realizado con éxito", "Pago completado", JOptionPane.INFORMATION_MESSAGE);
        		this.dispose();
        		hiloBarraProgreso();
        		/*
        		JOptionPane.showMessageDialog(this, "Su compra se ha realizado con éxito", "Pago completado", JOptionPane.INFORMATION_MESSAGE);
        		VentanaInicial ventanaInicial = new VentanaInicial();
        	    ventanaInicial.setVisible(true);
        		this.dispose();
        		*/
        	}
        });   
    }
}