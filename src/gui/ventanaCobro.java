package gui;

import java.awt.*;
import javax.swing.*;

public class ventanaCobro extends JFrame {
    private JButton btnPagar; 
    private JPanel pCentro, pNorte, pSur; 
    private JLabel lblTitulo, lblNombre, lblTarjeta, lblFecha, lblCVV;
    private JTextField txtNombre, txtTarjeta, txtFecha, txtCVV; 
    private JCheckBox chkTerminos;
    private JTextArea txtCondiciones;

    public ventanaCobro() {
        super();
        setBounds(300, 200, 600, 400);
        setTitle("SkyMovie");

        ImageIcon imagen = new ImageIcon("resources/img/iconoSkyMovie.png");
        setIconImage(imagen.getImage());

        // Crear los paneles
        pCentro = new JPanel();
        pCentro.setLayout(new BoxLayout(pCentro, BoxLayout.Y_AXIS)); // Disposición vertical
        pNorte = new JPanel(); 
        pSur = new JPanel(); // Solo para el botón de pagar

        getContentPane().add(pCentro, BorderLayout.CENTER);
        getContentPane().add(pNorte, BorderLayout.NORTH);
        getContentPane().add(pSur, BorderLayout.SOUTH);

        // Configuración del botón de pago
        btnPagar = new JButton("PAGAR");
        btnPagar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		pCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // para dejar espacio en los bordes


        // Crear etiquetas y campos de texto
        lblTitulo = new JLabel("Introduzca sus datos para realizar el pago:");
        lblNombre = new JLabel("Nombre: ");
        lblTarjeta = new JLabel("Número de tarjeta: ");
        lblFecha = new JLabel("Fecha de caducidad: ");
        lblCVV = new JLabel("CVV: ");
        txtNombre = new JTextField(20);
        txtTarjeta = new JTextField(20);
        txtFecha = new JTextField("MM/AA", 5);
        txtCVV = new JTextField(3);

        // Checkbox para aceptar términos
        chkTerminos = new JCheckBox("Aceptar términos y condiciones");

        // Texto de condiciones en un JTextArea solo lectura con ajuste de línea
        txtCondiciones = new JTextArea("Al proceder con la compra, usted acepta los términos y condiciones, "
                + "incluido el uso de sus datos personales para gestionar la compra. Los datos serán tratados "
                + "según la normativa vigente y nuestra política de privacidad.");
        txtCondiciones.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
        txtCondiciones.setLineWrap(true);
        txtCondiciones.setWrapStyleWord(true);
        txtCondiciones.setEditable(false);  // Solo lectura
        txtCondiciones.setOpaque(false);    // Fondo transparente para que parezca una etiqueta
        txtCondiciones.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinear a la izquierda

        // Añadir componentes al panel de encabezado
        pNorte.add(lblTitulo);

        // Añadir componentes al panel central con BoxLayout (disposición vertical)
        pCentro.add(lblNombre);
        pCentro.add(txtNombre);
        pCentro.add(lblTarjeta);
        pCentro.add(txtTarjeta);
        pCentro.add(lblFecha);
        pCentro.add(txtFecha);
        pCentro.add(lblCVV);
        pCentro.add(txtCVV);
        pCentro.add(chkTerminos);
        pCentro.add(txtCondiciones); // Añadir el JTextArea justo debajo del checkbox

        // Añadir botón pagar al panel sur
        pSur.add(btnPagar);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ventanaCobro();
    }
}
