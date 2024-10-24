package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class VentanaInicial extends JFrame {

    // Lista de rutas de las imágenes de las películas
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

    // Lista de títulos de las películas
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
        // Configuración de la ventana principal
        setTitle("Ventana Principal");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior con una imagen
        JPanel panelSuperior = new JPanel();
        panelSuperior.setPreferredSize(new Dimension(400, 150)); // Altura para el panel
        JLabel imagenSuperior = new JLabel("Imagen aquí"); // Puedes reemplazar esto con una imagen real
        imagenSuperior.setHorizontalAlignment(JLabel.CENTER);
        panelSuperior.add(imagenSuperior);

        // JTabbedPane para las películas
        JTabbedPane tabbedPane = new JTabbedPane();
        for (int i = 1; i <= 7; i++) { // 7 pestañas (una por película)
            tabbedPane.addTab("Día " + i, crearPanelPeliculas(i-1)); // Se pasa el índice a crearPanelPeliculas
        }

        // Añadir los paneles a la ventana principal
        add(panelSuperior, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // Método para crear un panel con una película según el índice
    private JScrollPane crearPanelPeliculas(int index) {
        // Crear un panel con GridLayout (1 película por panel)
        JPanel panelGrid = new JPanel();
        panelGrid.setLayout(new GridLayout(1, 1, 10, 10)); // Solo una fila

        // Añadir la película al grid
        JPanel panelPelicula = new JPanel();
        panelPelicula.setLayout(new BorderLayout());

        // Cargar la imagen desde la ruta
        String rutaImagen = rutasImagenes.get(index);
        ImageIcon imagenIcono = new ImageIcon(rutaImagen);

        // Escalar la imagen si es necesario
        ImageIcon imagenEscalada = new ImageIcon(
            imagenIcono.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));

        JLabel imagenPelicula = new JLabel(imagenEscalada, JLabel.CENTER);
        imagenPelicula.setPreferredSize(new Dimension(100, 100));

        // Título de la película
        String titulo = titulosPeliculas.get(index);
        JLabel tituloPelicula = new JLabel(titulo, JLabel.CENTER);

        // Añadir la imagen y el título al panel
        panelPelicula.add(imagenPelicula, BorderLayout.CENTER);
        panelPelicula.add(tituloPelicula, BorderLayout.SOUTH);

        // Añadir el panel de la película al grid
        panelGrid.add(panelPelicula);

        // Devolver el panel dentro de un JScrollPane
        JScrollPane scrollPane = new JScrollPane(panelGrid);
        return scrollPane;
    }

    public static void main(String[] args) {
        new VentanaInicial();
    }
}
