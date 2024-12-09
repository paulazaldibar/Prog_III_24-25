package domain;

public class Pago {
	private Reserva id;
	private String importe;
	private String Nombre;
	private String numeroTarjeta;
	private String fechaCaducidad;
	private String CVV;
	
	public Pago() {
		super();
	}

	public Pago(Reserva id, String importe, String nombre, String numeroTarjeta, String fechaCaducidad, String cVV) {
		super();
		this.id = id;
		this.importe = importe;
		Nombre = nombre;
		this.numeroTarjeta = numeroTarjeta;
		this.fechaCaducidad = fechaCaducidad;
		CVV = cVV;
	}

	public Reserva getId() {
		return id;
	}

	public void setId(Reserva id) {
		this.id = id;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getCVV() {
		return CVV;
	}

	public void setCVV(String cVV) {
		CVV = cVV;
	}

	@Override
	public String toString() {
		return "Pago [id=" + id + ", importe=" + importe + ", Nombre=" + Nombre + ", numeroTarjeta=" + numeroTarjeta
				+ ", fechaCaducidad=" + fechaCaducidad + ", CVV=" + CVV + "]";
	}
}
