package gui;

import javax.swing.*;
import java.awt.*;

public class VentanaPelicula extends JFrame {

    public VentanaPelicula() {
    	
    	setTitle("SkyMovie");
        ImageIcon imagen = new ImageIcon("resources/img/iconoSkyMovie.png");
        setIconImage(imagen.getImage());

        // Configuración de la ventana principal
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        // Panel superior para el trailer/fondo
        JPanel trailerPanel = new JPanel();
        trailerPanel.setPreferredSize(new Dimension(400, 100));
        trailerPanel.setBorder(BorderFactory.createTitledBorder("Trailer / Fondo"));
        mainPanel.add(trailerPanel, BorderLayout.NORTH);
        
        // Panel central para la información de la película
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        
        // Panel para la portada
        JLabel portada = new JLabel("Portada");
        portada.setHorizontalAlignment(JLabel.CENTER);
        portada.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        portada.setPreferredSize(new Dimension(100, 150));
        infoPanel.add(portada, BorderLayout.WEST);
        
        // Panel de texto con la información de la película
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(6, 1));
        textPanel.add(new JLabel("Título: "));
        textPanel.add(new JLabel("Director: "));
        textPanel.add(new JLabel("Actores: "));
        textPanel.add(new JLabel("Sinopsis: "));
        textPanel.add(new JLabel("Duración: "));
        textPanel.add(new JLabel("Estreno: "));
        infoPanel.add(textPanel, BorderLayout.CENTER);
        
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        
        // Panel para los días y horarios
        JPanel schedulePanel = new JPanel();
        schedulePanel.setLayout(new GridLayout(2, 1));
        
        // Panel de días
        JPanel daysPanel = new JPanel();
        daysPanel.setLayout(new GridLayout(1, 5));
        for (int i = 0; i < 5; i++) {
            JButton dayButton = new JButton("Día " + (i + 1));
            daysPanel.add(dayButton);
        }
        schedulePanel.add(daysPanel);
        
        // Panel de horarios
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new GridLayout(2, 2));
        for (int i = 0; i < 4; i++) {
            JButton timeButton = new JButton("15:30");
            timePanel.add(timeButton);
        }
        schedulePanel.add(timePanel);
        
        mainPanel.add(schedulePanel, BorderLayout.SOUTH);
        
        // Añadir el panel principal a la ventana
        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPelicula ventana = new VentanaPelicula();
            ventana.setVisible(true);
        });
    }
}
