// El for para recorrer las lista de los botones de los asientos y el método para actualizar el precio total
// esta hecho con ayuda de chat GPT
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bd.GestorBD;
import domain.Asientos;

public class VentanaSeleccionAsientos extends JFrame {
    
	private static final long serialVersionUID = 1L;
	private static int numFila = 6; 
    private static int asientosFila = 18;
    private static double precioEntrada = 10.0; 
    private static int totalAsientosSeleccionados = 0; 
    private ArrayList<Asientos> asientos; 
    private JButton btnContinuar, btnVolver;
    private JLabel lblTotal, lblTitulo, lblSubtitulo; 
    private JPanel pTitulo, pAsientos, pTotal, pBotones, pSur; 
    
    // Lista para almacenar asientos seleccionados
    private List<Asientos> asientosSeleccionados = new ArrayList<>();
    
    private static Color verdePersonalizado = new Color(89, 169, 106);
    private static Color rojoPersonalizado = new Color(164, 3, 31);
    
    // Método para agregar asientos seleccionados
    public void agregarAsientoSeleccionado(Asientos asiento) {
        asientosSeleccionados.add(asiento);
    }
    
    public void reiniciarSeleccion() {
        totalAsientosSeleccionados = 0; 
        for (Asientos asiento : asientos) {
        	asiento.reiniciarEstado();
        }
        actualizarTotal(); 
    }

    
    public VentanaSeleccionAsientos() {
        setTitle("SkyMovie"); 
        ImageIcon imagen = new ImageIcon("resources/img/iconoSkyMovie.png");
        setIconImage(imagen.getImage());
        
        // Conseguir que la ventana ocupe toda la pantalla, teniendo en cuenta el espacio de la barra de tareas para evitarlo. (Chat GPT)
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza la ventana automáticamente
        setUndecorated(false); // Asegura que se vea la barra de título y controles de la ventana
  
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        pTitulo = new JPanel();
        pTitulo.setLayout(new BorderLayout());
        lblTitulo = new JLabel("Seleccione sus asientos", JLabel.CENTER);
        lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 16)); // Fuente específica para label con tamaño ajustado
        lblSubtitulo = new JLabel("PANTALLA", JLabel.CENTER);
        lblSubtitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18)); // Fuente específica para label con tamaño ajustado

        pTitulo.add(lblTitulo, BorderLayout.NORTH);
        pTitulo.add(lblSubtitulo, BorderLayout.SOUTH);
        this.add(pTitulo, BorderLayout.NORTH);
        
        // Panel de asientos seleccionables
        pAsientos = new JPanel();
        pAsientos.setLayout(new GridLayout(numFila, asientosFila + 2, 10, 10)); // Le sumamos 2 que seran los pasillos
        pAsientos.setBorder(BorderFactory.createEmptyBorder(30,40,30,40)); // Dejamos un margen alrededor del panel
        
        asientos = new ArrayList<>(); // Inicializamos el ArrayList
        //Color verdePersonalizado = new Color(89, 169, 106);
        //Color rojoPersonalizado = new Color(164, 3, 31);
        
        /*for(int i=0; i<numFila; i++) {
        	for(int j=0; j<asientosFila +2; j++) {
        		if(j==4 || j==15) { // Las columnas 4 y 15 son los pasillos
        			pAsientos.add(new JLabel("")); // Añadimos un espacio en blanco para simular el pasillo
        		}else {
        			JButton btnAsiento = new JButton();
        			btnAsiento.setBackground(verdePersonalizado);
        			btnAsiento.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(btnAsiento.getBackground().equals(verdePersonalizado)) {
								btnAsiento.setBackground(rojoPersonalizado);
								totalAsientosSeleccionados++;
								// si al principio el asiento esta verde, al seleccionarlo cambiará a rojo e implica que tenemos que sumarlo al número total de asiento seleccionados
							}else {
								btnAsiento.setBackground(verdePersonalizado);
								totalAsientosSeleccionados--; 
								// si al principio el asiento esta rojo, al seleccionarlo cambiará a verde e implica que tenemos que restarlo del número total de asientos seleccionados
							}
							actualizarTotal();
						}
					});
        			asientos.add(btnAsiento); // Añadimos el boton al ArrayList
        			pAsientos.add(btnAsiento);
        		}
        	}
        }*/
        
        for(int i=0; i<numFila; i++) {
        	for(int j=0; j<asientosFila +2; j++) {
        		if(j==4 || j==15) { // Las columnas 4 y 15 son los pasillos
        			pAsientos.add(new JLabel("")); // Añadimos un espacio en blanco para simular el pasillo
        		}else {
        			Asientos asiento = new Asientos(i, j);
        			boolean ocupado = GestorBD.consultarAsiento(i, j);
        			if (ocupado) {
                        asiento.setOcupado(true);
                        asiento.setEnabled(false); // Deshabilitar interacción
                        asiento.setBackground(Color.BLACK); // Asiento pagado
                    }
                    asiento.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                        	if (asiento.isEnabled()) {
                                asiento.alternarEstado();
                                if (asiento.isOcupado()) {
                                    totalAsientosSeleccionados++;
                                } else {
                                    totalAsientosSeleccionados--;
                                }
                                actualizarTotal();
                        	}
                        	/*asiento.alternarEstado();
                            if (asiento.isOcupado()) {
                                totalAsientosSeleccionados++;
                            } else {
                                totalAsientosSeleccionados--;
                            }
                            actualizarTotal();*/
                        }
					});
                    asientos.add(asiento); // Añadimos el asiento a la lista
                    pAsientos.add(asiento);
        		}
        	}
        }
        
        
        // Panel al sur de la ventana para mostrar el total a pagar por las entradas
        pSur = new JPanel(); 
        pSur.setLayout(new BorderLayout());
        
        pTotal = new JPanel();
        lblTotal = new JLabel("Total: €0.00"); 
        lblTotal.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        pTotal.add(lblTotal);
        pSur.add(pTotal, BorderLayout.NORTH);
        
        pBotones = new JPanel();
        pBotones.setLayout(new GridLayout(1, 2, 10, 0));  // Al panel botones podemos un gridLayout para que los separe en 1 fila y 2 columnas   
        btnContinuar = new JButton("CONTINUAR");
        btnContinuar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        btnVolver = new JButton("VOLVER");
        btnVolver.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
        pBotones.add(btnVolver);
        pBotones.add(btnContinuar);
        pSur.add(pBotones, BorderLayout.SOUTH);
        
        pBotones.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        
        //Añadimos los paneles a la ventana
        this.add(pAsientos, BorderLayout.CENTER);
        this.add(pSur, BorderLayout.SOUTH);
        
        btnVolver.addActionListener((e) ->{
        	new VentanaPelicula();
        	this.dispose();
        });
        
        btnContinuar.addActionListener((e)->{
        	double total = totalAsientosSeleccionados * precioEntrada;
        	
        	// Guardar asientos ocupados en la base de datos
            for (Asientos asiento : asientos) {
                if (asiento.isOcupado()) {
                    GestorBD.guardarAsiento(asiento.getFila(), asiento.getColumna(), true); // true para ocupado
                    asiento.setEnabled(false); // Deshabilitar interacción
                    asiento.setBackground(Color.BLACK); // Cambiar a negro
                }
            }
            
            // Crear lista de asientos ocupados
            List<Asientos> asientosOcupados = new ArrayList<>();
            for (Asientos asiento : asientos) {
                if (asiento.isOcupado()) {
                    asientosOcupados.add(asiento);
                }
            }
        	new VentanaPagar(total, asientosOcupados);
        	this.dispose();
        });
        
        setVisible(true);
    }
    
    /*private void actualizarTotal() {
    	double total = totalAsientosSeleccionados * precioEntrada;
    	lblTotal.setText("Total: €" + String.format("%.2f", total));
	}*/
    
    private void actualizarTotal() {
        double total = totalAsientosSeleccionados * precioEntrada;
        lblTotal.setText("Total: €" + String.format("%.2f", total));
    }

    
    @Override
    public void dispose() {
        GestorBD.closeBD(); 
        super.dispose();
    }

    public static void main(String[] args) {
        VentanaSeleccionAsientos v1 = new VentanaSeleccionAsientos();
    }
}