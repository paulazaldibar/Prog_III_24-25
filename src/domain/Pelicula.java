package domain;

public class Pelicula {
	private String titulo;
	private String director;
	private String sinopsis;
	private String duracion;
	private String fechaEstreno;
	private String actores;
	private String imagen;
	
	public Pelicula(String titulo, String director, String sinopsis, String duracion, String fechaEstreno,
			String actores, String imagen) {
		super();
		this.titulo = titulo;
		this.director = director;
		this.sinopsis = sinopsis;
		this.duracion = duracion;
		this.fechaEstreno = fechaEstreno;
		this.actores = actores;
		this.imagen = imagen;
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
	
	public String getActores() {
		return actores;
	}
	
	public void setActores(String actores) {
		this.actores = actores;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", director=" + director + ", actores=" + actores + ", sinopsis="
				+ sinopsis + ", duracion=" + duracion + ", fechaEstreno=" + fechaEstreno + "]";
	}
}