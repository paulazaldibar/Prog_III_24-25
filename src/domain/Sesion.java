package domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Sesion {
	private int idSesion;
	private LocalDate dia;
    private LocalTime hora;
	private Pelicula idPelicula;
	private List<Reserva> listaReservas;
	
	public Sesion() {
		super();
	}

	public Sesion(int idSesion, LocalDate dia, LocalTime hora, Pelicula idPelicula) {
		super();
		this.idSesion = idSesion;
		this.dia = dia;
		this.hora = hora;
		this.idPelicula = idPelicula;
	}

	public Sesion(int idSesion, LocalDate dia, LocalTime hora, Pelicula idPelicula, List<Reserva> listaReservas) {
		super();
		this.idSesion = idSesion;
		this.dia = dia;
		this.hora = hora;
		this.idPelicula = idPelicula;
		this.listaReservas = listaReservas;
	}

	public int getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(int idSesion) {
		this.idSesion = idSesion;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Pelicula getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(Pelicula idPelicula) {
		this.idPelicula = idPelicula;
	}

	public List<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(List<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	@Override
	public String toString() {
		return "Sesion [idSesion=" + idSesion + ", dia=" + dia + ", hora=" + hora + ", idPelicula=" + idPelicula
				+ ", listaReservas=" + listaReservas + "]";
	}

	
}

