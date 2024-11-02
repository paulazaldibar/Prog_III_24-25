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

public class VentanaPagar extends JFrame {
    private JButton btnPagar, btnVolver;
    private JPanel pCentro, pNorte, pSur, pCentroDatos;
    private JLabel lblTitulo, lblNombre, lblTarjeta, lblFecha, lblCVV;
    private JTextField txtNombre, txtTarjeta, txtFecha, txtCVV;
    private JCheckBox checkTerminos;
    private JTextArea txtCondiciones;

    public VentanaPagar() {
        super();
        setBounds(300, 200, 600, 400);
        
        setTitle("SkyMovie");

        ImageIcon imagen = new ImageIcon("resources/img/iconoSkyMovie.png");
        setIconImage(imagen.getImage());

        pCentro = new JPanel(new BorderLayout(10, 10));
        pNorte = new JPanel();
        pSur = new JPanel();
        pCentroDatos = new JPanel(new GridLayout(5, 2, 10, 10));

        getContentPane().add(pCentro, BorderLayout.CENTER);
        getContentPane().add(pNorte, BorderLayout.NORTH);
        getContentPane().add(pSur, BorderLayout.SOUTH);

        pCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btnPagar = new JButton("PAGAR");
        btnPagar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        
        btnVolver = new JButton("VOLVER"); // Este botón cerrará la ventanaPagar y nos devolverá a la ventanaSeleccionAsientos
        btnVolver.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));

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
        pSur.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        setVisible(true);
        
        btnVolver.addActionListener((e) ->{
        	new VentanaSeleccionAsientos();
        	this.dispose();
        });
    }

}
