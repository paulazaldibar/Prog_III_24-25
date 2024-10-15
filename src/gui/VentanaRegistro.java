package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaRegistro extends JFrame{
	//Declaramos los componentes boton
	private JButton btnInicioSesion, btnCierreSesion;
	//Declaramos los paneles
	private JPanel pCentro, pNorte, PEste, POeste, PSur;
	
	public VentanaRegistro() {
		super();
		setBounds(300,200,600,400);
		
		//Instanciamos los paneles
		pCentro = new JPanel();
		//Modificamos el Layout del panel centro
		pCentro.setLayout(new GridLayout(2, 2));
		pNorte = new JPanel();
		PSur = new JPanel();
		PEste = new JPanel();
		POeste = new JPanel();
		
		//Añadimos los paneles al panel principal de la ventana
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(PEste, BorderLayout.EAST);
		getContentPane().add(POeste, BorderLayout.WEST);
		getContentPane().add(PSur, BorderLayout.SOUTH);
		//getContentPane().add(PCentro, BorderLayout.CENTER);

		
		//Instanciamos los componentes botón
		btnInicioSesion = new JButton("Iniciar Sesion");
		btnCierreSesion = new JButton("Cerrar Sesion");
		//Cambiamos el tipo de letra en los botones
		btnInicioSesion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		btnCierreSesion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		//Añadimos el boton al panel sur de la ventana
		PSur.add(btnInicioSesion);
		PSur.add(btnCierreSesion);
	
		setVisible(true);
	}
}
