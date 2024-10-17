package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaInicial extends JFrame{

	// Lista de películas de ejemplo
    private List<String> peliculas = List.of(
            "Película 1", "Película 2", "Película 3", 
            "Película 4", "Película 5", "Película 6",
            "Película 7", "Película 8", "Película 9"
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
        for (int i = 1; i <= 5; i++) { // 5 pestañas como ejemplo
            tabbedPane.addTab("Día " + i, crearPanelPeliculas());
        }

        // Añadir los paneles a la ventana principal
        add(panelSuperior, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // Método para crear un panel con GridLayout que muestre las películas
    private JScrollPane crearPanelPeliculas() {
        // Crear un panel con GridLayout (3 columnas, número de filas dinámico)
        JPanel panelGrid = new JPanel();
        int numPeliculas = peliculas.size();
        panelGrid.setLayout(new GridLayout(0, 3, 10, 10)); // 0 filas significa que se ajusta automáticamente

        // Añadir las películas al grid
        for (String pelicula : peliculas) {
            JPanel panelPelicula = new JPanel();
            panelPelicula.setLayout(new BorderLayout());

            // Simulando la imagen de la película
            JLabel imagenPelicula = new JLabel("Imagen", JLabel.CENTER); 
            imagenPelicula.setPreferredSize(new Dimension(100, 100));

            // Título de la película
            JLabel tituloPelicula = new JLabel(pelicula, JLabel.CENTER);

            // Añadir la imagen y el título al panel
            panelPelicula.add(imagenPelicula, BorderLayout.CENTER);
            panelPelicula.add(tituloPelicula, BorderLayout.SOUTH);

            // Añadir el panel de la película al grid
            panelGrid.add(panelPelicula);
        }

        // Devolver el panel dentro de un JScrollPane
        JScrollPane scrollPane = new JScrollPane(panelGrid);
        return scrollPane;
    }

    public static void main(String[] args) {
        new VentanaInicial();
    }
}
