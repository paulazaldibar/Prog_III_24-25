package domain;

import java.time.LocalDate;
import java.util.List;

public class Pelicula {
	private String titulo;
	private String director;
	private List<Actores> actores; 
	private String sinopsis;
	private int duracion; // duracion en minutos
	private LocalDate estreno;
	
	
	public Pelicula() {
		super();
	}
	
	public Pelicula(String titulo, String director, List<Actores> actores, String sinopsis, int duracion,
			LocalDate estreno) {
		super();
		this.titulo = titulo;
		this.director = director;
		this.actores = actores;
		this.sinopsis = sinopsis;
		this.duracion = duracion;
		this.estreno = estreno;
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
	public List<Actores> getActores() {
		return actores;
	}
	public void setActores(List<Actores> actores) {
		this.actores = actores;
	}
	public String getSinopsis() {
		return sinopsis;
	}
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public LocalDate getEstreno() {
		return estreno;
	}
	public void setEstreno(LocalDate estreno) {
		this.estreno = estreno;
	}
	
	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", director=" + director + ", actores=" + actores + ", sinopsis="
				+ sinopsis + ", duracion=" + duracion + ", estreno=" + estreno + "]";
	}
	
	
	

}
// tabbed pane