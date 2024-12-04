package domain;


public class Usuario {
	private int id;
	private String nombre;
	private String contrasenia;
	
	public Usuario() {
		super();
	}
	public Usuario(String nombre, String contrasenia) {
		super();
		this.nombre = nombre;
		this.contrasenia = contrasenia;
	}
	
	public Usuario(int id, String nombre, String contrasenia) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.contrasenia = contrasenia;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", contrasenia=" + contrasenia + "]";
	}
	
	
	
	

}
