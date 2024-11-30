package domain;

import java.util.Objects;

public class Usuario {
	private int id = -1; //clave primaria
	private String nombre;
	private String email;
	private String contrasenia;
	
	public Usuario() {
		
	}
	
	public Usuario(int id, String nombre, String email, String contrasenia) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasenia = contrasenia;
    }
	
	public Usuario(String nombre, String email, String contrasenia) {
		this.nombre = nombre;
		this.email = email;
		this.contrasenia = contrasenia;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return nombre;
	}

	public void setName(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return contrasenia;
	}

	public void setPassword(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", email=" + email + ", contraseña=" + contrasenia + "]";
	}

	//Redefinirlos para trabajar con Collecciones
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return id == other.id;
	}

}
