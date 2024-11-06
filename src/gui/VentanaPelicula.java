package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VentanaPelicula extends JFrame {

	private JLabel portadaLabel;
	private JLabel tituloLabel;
	private JButton btnDia; 
	private LocalDate fechaActual = LocalDate.now(); // Fecha actual del sistema
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM"); // Formato de fecha (6 Oct, 7 Oct...)

	
    public VentanaPelicula(String titulo, String rutaPortada) {
    	
    	setTitle("SkyMovie");
        ImageIcon imagen = new ImageIcon("resources/img/iconoSkyMovie.png");
        setIconImage(imagen.getImage());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Panel superior para el trailer/fondo
        JPanel trailerPanel = new JPanel();
        trailerPanel.setPreferredSize(new Dimension(400, 100));
        trailerPanel.setBorder(BorderFactory.createTitledBorder("Trailer / Fondo"));
        mainPanel.add(trailerPanel, BorderLayout.NORTH);
        
        // Panel central para la información de la película
        JPanel infoPanel = new JPanel(new BorderLayout());

        //Imagen de portada
        portadaLabel = new JLabel();
        portadaLabel.setHorizontalAlignment(JLabel.CENTER);
        portadaLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        portadaLabel.setPreferredSize(new Dimension(100, 150));
        infoPanel.add(portadaLabel, BorderLayout.WEST);
        
        actualizarPortada(rutaPortada);
        
        // Panel de texto con la información de la película
        JPanel textPanel = new JPanel(new GridLayout(6, 1));
        tituloLabel = new JLabel("Título: " + titulo, JLabel.LEFT);
        textPanel.add(tituloLabel);
        textPanel.add(new JLabel("Director: ")); 
        textPanel.add(new JLabel("Actores: "));
        textPanel.add(new JLabel("Sinopsis: "));
        textPanel.add(new JLabel("Duración: "));
        textPanel.add(new JLabel("Estreno: "));
        infoPanel.add(textPanel, BorderLayout.CENTER);

        mainPanel.add(infoPanel, BorderLayout.CENTER);
        
        /*
        // Panel para la portada MIRAR
        JLabel portada = new JLabel("Portada");
        portada.setHorizontalAlignment(JLabel.CENTER);
        portada.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        portada.setPreferredSize(new Dimension(100, 150));
        infoPanel.add(portada, BorderLayout.WEST);
        */
        
        // Panel para los días y horarios
        JPanel schedulePanel = new JPanel(new GridLayout(2, 1));
        
        // Panel de días
        JPanel daysPanel = new JPanel(new GridLayout(1, 5));
        
        for(int i=0; i<5; i++) {
        	btnDia = new JButton(fechaActual.plusDays(i).format(formatter));
        	daysPanel.add(btnDia);
        	
        };
        
        schedulePanel.add(daysPanel);
        
        // Panel de horarios
        JPanel timePanel = new JPanel(new GridLayout(2, 2));
        for (int i = 0; i < 4; i++) {
            JButton timeButton = new JButton("15:30");
            timePanel.add(timeButton);
        }
        schedulePanel.add(timePanel);
        
        mainPanel.add(schedulePanel, BorderLayout.SOUTH);
        add(mainPanel);
        
        
        btnDia.addActionListener((e)->{
        	new VentanaSeleccionAsientos();
        	this.dispose();
        });
      
    }

    private void actualizarPortada(String rutaPortada) {
        // Cargar la imagen desde la ruta proporcionada
        ImageIcon portadaIcono = new ImageIcon(rutaPortada);

        // Verificar si la imagen existe o es válida
        if (portadaIcono.getIconWidth() == -1) {
            portadaLabel.setText("Imagen no disponible");
            portadaLabel.setIcon(null); // No mostrar icono si la imagen no se encuentra
        } else {
            // Escalar la imagen si es válida
            Image portadaEscalada = portadaIcono.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            portadaLabel.setIcon(new ImageIcon(portadaEscalada));
            portadaLabel.setText(""); // Limpiar el texto en caso de imagen válida
        }
    }
    
    public static void main(String[] args) {
        
    }
}
