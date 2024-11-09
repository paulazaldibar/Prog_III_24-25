package gui;

import javax.swing.*;

import domain.Pelicula;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VentanaPelicula extends JDialog {

	private JLabel portadaLabel;
    private JLabel tituloLabel;
    private JLabel directorLabel;
    private JLabel actoresLabel;
    private JLabel sinopsisLabel;
    private JLabel duracionLabel;
    private JLabel estrenoLabel;
    
	private JButton btnDia; 
	private JButton btnVolver;
	private LocalDate fechaActual = LocalDate.now(); // Fecha actual del sistema
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM"); // Formato de fecha (6 Oct, 7 Oct...)

    // Para mantener el botón seleccionado
    private JButton selectedDayButton = null;
	
    
    public VentanaPelicula(Pelicula pelicula) {
        this(); // Llama al constructor sin parámetros para inicializar la interfaz
        setPelicula(pelicula); // Establece la película en la ventana
    }
    public VentanaPelicula() {
    	
    	setTitle("SkyMovie");
        ImageIcon imagen = new ImageIcon("resources/img/iconoSkyMovie.png");
        setIconImage(imagen.getImage());

        // Conseguir que la ventana ocupe toda la pantalla, teniendo en cuenta el espacio de la barra de tareas para evitarlo. (Chat GPT)
       
  //      setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza la ventana automáticamente
        setUndecorated(false); // Asegura que se vea la barra de título y controles de la ventana
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Panel superior para el trailer/fondo
        JPanel trailerPanel = new JPanel(new BorderLayout());
        trailerPanel.setPreferredSize(new Dimension(400, 100));
        trailerPanel.setBorder(BorderFactory.createTitledBorder("Trailer / Fondo"));
                
        btnVolver = new JButton("←");
        btnVolver.setPreferredSize(new Dimension(50, 50));
        btnVolver.addActionListener(e -> {
            new VentanaInicial();
            this.dispose();
        });
        trailerPanel.add(btnVolver, BorderLayout.WEST);
        mainPanel.add(trailerPanel, BorderLayout.NORTH);
        
        JPanel infoPanel = new JPanel(new BorderLayout());

        //Imagen de portada
        portadaLabel = new JLabel();
        portadaLabel.setHorizontalAlignment(JLabel.CENTER);
        portadaLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        portadaLabel.setPreferredSize(new Dimension(100, 150));
        infoPanel.add(portadaLabel, BorderLayout.WEST);
        
        JPanel textPanel = new JPanel(new GridLayout(6, 1));
        tituloLabel = new JLabel();
        directorLabel = new JLabel();
        actoresLabel = new JLabel();
        sinopsisLabel = new JLabel();
        duracionLabel = new JLabel();
        estrenoLabel = new JLabel();
        
        textPanel.add(tituloLabel);
        textPanel.add(directorLabel);
        textPanel.add(sinopsisLabel);
        textPanel.add(duracionLabel);
        textPanel.add(estrenoLabel);
        textPanel.add(actoresLabel);

        
        infoPanel.add(textPanel, BorderLayout.CENTER);
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        
        JPanel schedulePanel = new JPanel(new GridLayout(2, 1));
        
        // Para restaurar el color original del boton al cambiar la seleccion
        Color originalColor = new JButton().getBackground();
        
        // Panel de días
        //EL PUNTERO AL SELECCIONAR UN DIA NO FUNCIONA
        JPanel daysPanel = new JPanel(new GridLayout(1, 5));
        for(int i=0; i<5; i++) {
        	JButton btnDia = new JButton(fechaActual.plusDays(i).format(formatter));
        	btnDia.setFocusable(false);
        	
        	btnDia.addActionListener(e -> {
                if (selectedDayButton != null) {
                    selectedDayButton.setBackground(originalColor); // Restaurar color original
                }
                btnDia.setBackground(Color.WHITE); 
                selectedDayButton = btnDia; 
            });
        	daysPanel.add(btnDia);
        	
        };
        schedulePanel.add(daysPanel);
        
        
        // Panel de horarios
        JPanel timePanel = new JPanel(new GridLayout(2, 2));
        String[] horarios = {"15:00", "18:00", "21:00", "23:00"};
        for (String horario : horarios) {
            JButton timeButton = new JButton(horario);
            timeButton.addActionListener(e -> {
                new VentanaSeleccionAsientos();
                this.dispose();
            });
            timePanel.add(timeButton);
        }
        schedulePanel.add(timePanel);
        
        mainPanel.add(schedulePanel, BorderLayout.SOUTH);
        add(mainPanel);
        
       
        setVisible(true);
    }
    

    
	public void setPelicula(Pelicula pelicula) {
    	 tituloLabel.setText("Título: " + pelicula.getTitulo());
         directorLabel.setText("Director: " + pelicula.getDirector());
         sinopsisLabel.setText("Sinopsis: " + pelicula.getSinopsis());
         duracionLabel.setText("Duración: " + pelicula.getDuracion());
         estrenoLabel.setText("Estreno: " + pelicula.getFechaEstreno());
         actoresLabel.setText("Actores: " + String.join(", ", pelicula.getActores()));

         actualizarPortada(pelicula.getRutaPortada());
    }

    private void actualizarPortada(String rutaPortada) {
        ImageIcon portadaIcono = new ImageIcon(rutaPortada);

        if (portadaIcono.getIconWidth() == -1) {
            portadaLabel.setText("Imagen no disponible");
            portadaLabel.setIcon(null); // No mostrar icono si la imagen no se encuentra
        } else {
            Image portadaEscalada = portadaIcono.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            portadaLabel.setIcon(new ImageIcon(portadaEscalada));
            portadaLabel.setText("");
        }
    }
    
    public static void main(String[] args) {
    	VentanaPelicula dialog = new VentanaPelicula();
    }
}
