package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class VentanaInicial extends JFrame {

    private List<String> rutasImagenes = List.of(
        "/resources/img/portadas/12 Años de Esclavitud.jpg",
        "/resources/img/portadas/Alien El Octavo Pasajero.jpg",
        "/resources/img/portadas/Barbie.jpg",
        "/resources/img/portadas/Blade Runner.jpg",
        "/resources/img/portadas/Casablanca.jpg",
        "/resources/img/portadas/Dune Parte uno.jpg",
        "/resources/img/portadas/El Caballero Oscuro.jpg",
        "/resources/img/portadas/El Club de la Pelea.jpg",
        "/resources/img/portadas/El Gran Escape.jpg",
        "/resources/img/portadas/El Padrino.jpg",
        "/resources/img/portadas/El renacido.jpg",
        "/resources/img/portadas/El Rey Leon.jpg",
        "/resources/img/portadas/El Señor de los Anillos La Comunidad del Anillo.jpg",
        "/resources/img/portadas/Encanto.jpeg",
        "/resources/img/portadas/Forrest Gump.jpg",
        "/resources/img/portadas/Frozen II.jpeg",
        "/resources/img/portadas/Gladiador.jpg",
        "/resources/img/portadas/Gran Torino.jpg",
        "/resources/img/portadas/Indiana Jones En busca del arca perdida.png",
        "/resources/img/portadas/Jurassic Park.jpg",
        "/resources/img/portadas/La Ballena.jpeg",
        "/resources/img/portadas/La Guerra de las Galaxias.jpg",
        "/resources/img/portadas/Los Infiltrados.jpg",
        "/resources/img/portadas/Luca.jpeg",
        "/resources/img/portadas/Matrix.jpg",
        "/resources/img/portadas/Minions El Origen de Gru.jpg",
        "/resources/img/portadas/MisionImposible Sentencia Mortal - Parte uno.jpg",
        "/resources/img/portadas/Oppenheimer.jpg",
        "/resources/img/portadas/Slumdog Millionaire.jpg",
        "/resources/img/portadas/Soul.jpg",
        "/resources/img/portadas/Spider Man Cruzando el multiverso.jpg",
        "/resources/img/portadas/Tiempos Modernos.jpg",
        "/resources/img/portadas/Titanic.jpg",
        "/resources/img/portadas/Toy Story 4.jpg",
        "/resources/img/portadas/Volver al Futuro.jpg"
    );

    private List<String> titulosPeliculas = List.of(
        "12 Años de Esclavitud", "Alien El Octavo Pasajero", "Barbie", 
        "Blade Runner", "Casablanca", "Dune Parte uno", "El Caballero Oscuro",
        "El Club de la Pelea", "El Gran Escape", "El Padrino", "El renacido", "El Rey Leon",
        "El Señor de los Anillos La Comunidad del Anillo", "Encanto", "Forrest Gump", 
        "Frozen II", "Gladiador", "Gran Torino", "Indiana Jones En busca del arca perdida", 
        "Jurassic Park", "La Ballena", "La Guerra de las Galaxias", "Los Infiltrados", 
        "Luca", "Matrix", "Minions El Origen de Gru", "MisionImposible Sentencia Mortal - Parte uno",
        "Oppenheimer", "Slumdog Millionaire", "Soul", "Spider Man Cruzando el multiverso", "Tiempos Modernos",
        "Titanic", "Toy Story 4", "Volver al Futuro"
    );

    public VentanaInicial() {
    	
        setTitle("SkyMovie");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        panelSuperior.setPreferredSize(new Dimension(screenSize.width, 150)); 
        JLabel imagenSuperior = new JLabel("resources/img/iconoSkyMovie.png"); 
        imagenSuperior.setHorizontalAlignment(JLabel.CENTER);
        panelSuperior.add(imagenSuperior);

        // JTabbedPane para las películas
        JTabbedPane tabbedPane = new JTabbedPane();
        for (int i = 1; i <= 7; i++) { // 7 pestañas
            tabbedPane.addTab("Día " + i, crearPanelPeliculas()); 
        }

        // Añadir los paneles a la ventana principal
        add(panelSuperior, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

 // Método para crear un panel con todas las películas en una cuadrícula 3x3
    private JScrollPane crearPanelPeliculas() {
        // Panel que contendrá todas las películas en una cuadrícula
        JPanel panelGrid = new JPanel();
        panelGrid.setLayout(new GridLayout(0, 3, 10, 10)); // 3 columnas y filas dinámicas

        // Añadir cada película al grid
        for (int i = 0; i < rutasImagenes.size(); i++) {
            JPanel panelPelicula = new JPanel();
            panelPelicula.setLayout(new BorderLayout());

            // Cargar la imagen desde la ruta
            String rutaImagen = rutasImagenes.get(i);
            java.net.URL imgURL = getClass().getResource(rutaImagen);
            
            // Verificar si la imagen existe antes de crear el ImageIcon
            ImageIcon imagenIcono;
            if (imgURL != null) {
                imagenIcono = new ImageIcon(imgURL);
            } else {
                System.out.println("Imagen no encontrada en la ruta: " + rutaImagen);
                // Usar una imagen de reemplazo o continuar sin imagen
                imagenIcono = new ImageIcon(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB));
            }

            // Escalar la imagen
            ImageIcon imagenEscalada = new ImageIcon(
                imagenIcono.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
            JLabel imagenPelicula = new JLabel(imagenEscalada, JLabel.CENTER);
            imagenPelicula.setPreferredSize(new Dimension(100, 100));

            // Título de la película
            String titulo = titulosPeliculas.get(i);
            JLabel tituloPelicula = new JLabel(titulo, JLabel.CENTER);

            // Añadir la imagen y el título al panel
            panelPelicula.add(imagenPelicula, BorderLayout.CENTER);
            panelPelicula.add(tituloPelicula, BorderLayout.SOUTH);

            // Añadir el panel de la película al grid
            panelGrid.add(panelPelicula);
        }

        JScrollPane scrollPane = new JScrollPane(panelGrid);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        return scrollPane;
    }

    public static void main(String[] args) {
        new VentanaInicial();
    }
}

