package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import domain.Pelicula;
import domain.PeliculasData;

public class VentanaInicial extends JFrame {
	private LocalDate fechaActual = LocalDate.now(); // Fecha actual del sistema
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM"); // Formato de fecha (6 Oct, 7 Oct...)

    private List<String> rutasImagenes = List.of(
        "resources/img/portadas/12 Años de Esclavitud.jpg",
        "resources/img/portadas/Alien El Octavo Pasajero.jpg",
        "resources/img/portadas/Barbie.jpg",
        "resources/img/portadas/Blade Runner.jpg",
        "resources/img/portadas/Casablanca.jpg",
        "resources/img/portadas/Dune Parte uno.jpg",
        "resources/img/portadas/El Caballero Oscuro.jpg",
        "resources/img/portadas/El Club de la Pelea.jpg",
        "resources/img/portadas/El Gran Escape.jpg",
        "resources/img/portadas/El Padrino.jpg",
        "resources/img/portadas/El renacido.jpg",
        "resources/img/portadas/El Rey Leon.jpg",
        "resources/img/portadas/El Señor de los Anillos La Comunidad del Anillo.jpg",
        "resources/img/portadas/Encanto.jpeg",
        "resources/img/portadas/Forrest Gump.jpg",
        "resources/img/portadas/Frozen II.jpeg",
        "resources/img/portadas/Gladiador.jpg",
        "resources/img/portadas/Gran Torino.jpg",
        "resources/img/portadas/Indiana Jones En busca del arca perdida.png",
        "resources/img/portadas/Jurassic Park.jpg",
        "resources/img/portadas/La Guerra de las Galaxias.jpg",
        "resources/img/portadas/Los Infiltrados.jpg",
        "resources/img/portadas/Luca.jpeg",
        "resources/img/portadas/Matrix.jpg",
        "resources/img/portadas/Minions El Origen de Gru.jpg",
        "resources/img/portadas/MisionImposible Sentencia Mortal - Parte uno.jpg",
        "resources/img/portadas/Oppenheimer.jpg",
        "resources/img/portadas/Slumdog Millionaire.jpg",
        "resources/img/portadas/Soul.jpg",
        "resources/img/portadas/Spider Man Cruzando el multiverso.jpg",
        "resources/img/portadas/Tiempos Modernos.jpg",
        "resources/img/portadas/Titanic.jpg",
        "resources/img/portadas/Toy Story 4.jpg"
    );

    private List<String> titulosPeliculas = List.of(
        "12 Años de Esclavitud", "Alien El Octavo Pasajero", "Barbie", 
        "Blade Runner", "Casablanca", "Dune Parte uno", "El Caballero Oscuro",
        "El Club de la Pelea", "El Gran Escape", "El Padrino", "El renacido", "El Rey Leon",
        "El Señor de los Anillos La Comunidad del Anillo", "Encanto", "Forrest Gump", 
        "Frozen II", "Gladiador", "Gran Torino", "Indiana Jones En busca del arca perdida", 
        "Jurassic Park", "La Guerra de las Galaxias", "Los Infiltrados", 
        "Luca", "Matrix", "Minions El Origen de Gru", "MisionImposible Sentencia Mortal - Parte uno",
        "Oppenheimer", "Slumdog Millionaire", "Soul", "Spider Man Cruzando el multiverso", "Tiempos Modernos",
        "Titanic", "Toy Story 4"
    );
    

    private List<Pelicula> peliculas;
    
    public VentanaInicial() {
        this(PeliculasData.cargarPeliculas("ficheros/Peliculas.csv"));
    }

    public VentanaInicial(List<Pelicula> peliculas) {
    	
    	this.peliculas = peliculas;
    	for (Pelicula p : peliculas) {
            System.out.println(p); // Confirmar que cada película tiene los datos esperados
        }
    	
        setTitle("SkyMovie");
        ImageIcon imagen = new ImageIcon("resources/img/iconoSkyMovie.png");
        setIconImage(imagen.getImage());

        // Conseguir que la ventana ocupe toda la pantalla, teniendo en cuenta el espacio de la barra de tareas para evitarlo. (Chat GPT)
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza la ventana automáticamente
        setUndecorated(false); // Asegura que se vea la barra de título y controles de la ventana
  
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        panelSuperior.setPreferredSize(new Dimension(screenSize.width, 150)); 
        
        //Añadir imagen al panel superior
        ImageIcon imagenSuperiorIcono = new ImageIcon("resources/img/foto cabecera.jpg");
        Image imagenEscalada = imagenSuperiorIcono.getImage().getScaledInstance(screenSize.width, 150, Image.SCALE_SMOOTH);
        JLabel imagenSuperior = new JLabel(new ImageIcon(imagenEscalada));
        panelSuperior.add(imagenSuperior);

        // JTabbedPane para las películas
        JTabbedPane tabbedPane = new JTabbedPane();
        for (int i = 0; i < 7; i++) {
            String fechaPestana = fechaActual.plusDays(i).format(formatter); 
            tabbedPane.addTab(fechaPestana, crearPanelPeliculas());
        }

        // Añadir los paneles a la ventana principal
        add(panelSuperior, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

    //Metodo con base nuestra pero resuelto por chat al final
	// Método para crear un panel con todas las películas en una cuadrícula 3x3
    private JScrollPane crearPanelPeliculas() {
        JPanel panelGrid = new JPanel(new GridLayout(0,  3, 10, 10));
        panelGrid.setBackground(Color.WHITE);
             
        for (int i = 0; i < peliculas.size(); i++) {
        	final int indice = i;
        	
            JPanel panelPelicula = new JPanel();
            panelPelicula.setLayout(new BorderLayout());
            panelGrid.setBackground(Color.WHITE);
            panelPelicula.setBackground(Color.WHITE);
            
            // Arreglo para visualizar las fotos:
            String rutaImagen = rutasImagenes.get(i);
            ImageIcon imagenIcono = new ImageIcon(rutaImagen);
            Image imagenEscalada = imagenIcono.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
            JLabel imagenPelicula = new JLabel(new ImageIcon(imagenEscalada));
            imagenPelicula.setHorizontalAlignment(JLabel.CENTER);
            
            // Título de la película
            String titulo = titulosPeliculas.get(i);
            JLabel tituloPelicula = new JLabel(titulo, JLabel.CENTER);
            
            //Guardar la ventana inicial como una variable
            JFrame ventanaIni = this;

            imagenPelicula.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                	Pelicula peliculaSeleccionada = peliculas.get(indice); 
                    VentanaPelicula ventanaPelicula = new VentanaPelicula(peliculaSeleccionada);
                    //ventanaPelicula.setPelicula(peliculaSeleccionada); 
                    ventanaPelicula.setVisible(true);

                    ventanaIni.dispose();
                	
                }
            });
            
            // Añadir la imagen y el título al panel
            panelPelicula.add(imagenPelicula, BorderLayout.CENTER);
            panelPelicula.add(tituloPelicula, BorderLayout.SOUTH);
            panelGrid.add(panelPelicula);
        }

        JScrollPane scrollPane = new JScrollPane(panelGrid);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        return scrollPane;
    }
    
    private void abrirVentanaPelicula(int indice) {
        String titulo = titulosPeliculas.get(indice);
        String rutaImagen = rutasImagenes.get(indice);
        VentanaPelicula ventanaPelicula = new VentanaPelicula();
        ventanaPelicula.setVisible(true);
        
    }

    public static void main(String[] args) {
    	List<Pelicula> peliculas = PeliculasData.cargarPeliculas("ficheros/Peliculas.csv");

    	if (peliculas != null && !peliculas.isEmpty()) {
            VentanaInicial ventanaInicial = new VentanaInicial(peliculas);
            ventanaInicial.setVisible(true);
        } else {
            System.out.println("No se pudo cargar ninguna película.");
        }
    }
}

