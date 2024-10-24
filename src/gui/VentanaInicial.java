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
        "/resources/img/portadas/imagen1.jpg",
        "/resources/img/portadas/imagen2.jpg",
        "/resources/img/portadas/imagen3.jpg",
        "/resources/img/portadas/imagen4.jpg",
        "/resources/img/portadas/imagen5.jpg",
        "/resources/img/portadas/imagen6.jpg",
        "/resources/img/portadas/imagen7.jpg"
    );

    // Lista de títulos de las películas
    private List<String> titulosPeliculas = List.of(
        "Película 1", "Película 2", "Película 3", 
        "Película 4", "Película 5", "Película 6", 
        "Película 7"
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
