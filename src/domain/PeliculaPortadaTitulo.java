package domain;

public class PeliculaPortadaTitulo {
	 private String titulo;
	 private String rutaImagen;

	 public PeliculaPortadaTitulo(String titulo, String rutaImagen) {
		 this.titulo = titulo;
		 this.rutaImagen = rutaImagen;
	 }

	 public String getTitulo() {
		 return titulo;
	 }

	 public String getRutaImagen() {
		 return rutaImagen;
	 }
	 
}
