package domain;

public class Pelicula {
	private String titulo;
	private String directores;
	private String sinopsis;
	private String durante;
	private String fechaEstreno;
	private String actores;
	private String imagen;
	
	public Pelicula(String titulo, String directores, String sinopsis, String durante, String fechaEstreno,
			String actores, String imagen) {
		super();
		this.titulo = titulo;
		this.directores = directores;
		this.sinopsis = sinopsis;
		this.durante = durante;
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
	
	public String getDirectores() {
		return directores;
	}
	
	public void setDirectores(String directores) {
		this.directores = directores;
	}
	
	public String getSinopsis() {
		return sinopsis;
	}
	
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	
	public String getDurante() {
		return durante;
	}
	
	public void setDurante(String durante) {
		this.durante = durante;
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
		return "Pelicula [titulo=" + titulo + ", directores=" + directores + ", sinopsis=" + sinopsis + ", durante="
				+ durante + ", fechaEstreno=" + fechaEstreno + ", actores=" + actores + ", imagen=" + imagen + "]";
	}
	
}
