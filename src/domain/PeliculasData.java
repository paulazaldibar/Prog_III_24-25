package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PeliculasData {
	
	 public static List<Pelicula> cargarPeliculas(String rutaArchivo) {
	        List<Pelicula> peliculas = new ArrayList<>();

	        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
	            String linea;
	            while ((linea = br.readLine()) != null) {
	                String[] datos = linea.split(","); // Asume que los campos están separados por comas
	                if (datos.length >= 7) {
	                    List<String> actores = Arrays.stream(datos[5].split(";"))
	                                                 .map(String::trim)
	                                                 .collect(Collectors.toList());

	                    Pelicula pelicula = new Pelicula(
	                        datos[0], // título
	                        datos[1], // director
	                        datos[2], // sinopsis
	                        datos[3], // duracion
	                        datos[4], // fecha de estreno
	                        actores,   // lista de actores
	                        datos[6] //ruta de portada
	                    );
	                    peliculas.add(pelicula);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return peliculas;
	    }
}
