package domain;

import java.util.List;

public class Reserva {
	private Usuario id;
	private Sesion idSesion;
	private List<Asientos> listaAsientos;
	
	public Reserva() {
		super();
	}

	public Reserva(Usuario id, Sesion idSesion, List<Asientos> listaAsientos) {
		super();
		this.id = id;
		this.idSesion = idSesion;
		this.listaAsientos = listaAsientos;
	}

	public Usuario getId() {
		return id;
	}

	public void setId(Usuario id) {
		this.id = id;
	}

	public Sesion getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(Sesion idSesion) {
		this.idSesion = idSesion;
	}

	public List<Asientos> getListaAsientos() {
		return listaAsientos;
	}

	public void setListaAsientos(List<Asientos> listaAsientos) {
		this.listaAsientos = listaAsientos;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", idSesion=" + idSesion + ", listaAsientos=" + listaAsientos + "]";
	}
}
