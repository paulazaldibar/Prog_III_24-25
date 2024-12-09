package domain;

import java.util.List;

public class Pelicula {
	private String idPelicula;
	private String titulo;
	private String director;
	private String sinopsis;
	private String duracion;
	private String fechaEstreno;
	private List<String> actores;
	private String rutaPortada;
	
	
	public Pelicula(String idPelicula, String titulo, String director, String sinopsis, String duracion,
			String fechaEstreno, List<String> actores, String rutaPortada) {
		super();
		this.idPelicula = idPelicula;
		this.titulo = titulo;
		this.director = director;
		this.sinopsis = sinopsis;
		this.duracion = duracion;
		this.fechaEstreno = fechaEstreno;
		this.actores = actores;
		this.rutaPortada = rutaPortada;
	}

	public Pelicula(String titulo, String director, String sinopsis, String duracion, String fechaEstreno,
			List<String> actores, String rutaPortada) {
		super();
		this.titulo = titulo;
		this.director = director;
		this.sinopsis = sinopsis;
		this.duracion = duracion;
		this.fechaEstreno = fechaEstreno;
		this.actores = actores;
		this.rutaPortada = rutaPortada;
	}

	public String getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(String idPelicula) {
		this.idPelicula = idPelicula;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(String fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public List<String> getActores() {
		return actores;
	}

	public void setActores(List<String> actores) {
		this.actores = actores;
	}
	
	public String getRutaPortada() { 
		return rutaPortada; 
	}

	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", director=" + director + ", sinopsis=" + sinopsis + ", duracion="
				+ duracion + ", fechaEstreno=" + fechaEstreno + ", actores=" + actores + "]";
	}
	
}