package domain;

import java.awt.Color;

import javax.swing.JPanel;

public class Asientos extends JPanel{
	
    private static final long serialVersionUID = 1L;
    private int posicionFila;
    private int posicionColumna;
    private boolean ocupado;  // Estado del asiento: true = ocupado, false = disponible
	
    public Asientos(int posicionFila, int posicionColumna) {
		super();
		this.posicionFila = posicionFila;
		this.posicionColumna = posicionColumna;
		this.ocupado = false;  //Por defecto un asiento esta libre/disponible
		this.setBackground(new Color(89, 169, 106));  // Verde para disponible
	}
    
    public int getFila() {
        return posicionFila;
    }

    public void setFila(int fila) {
        this.posicionFila = fila;
    }

    public int getColumna() {
        return posicionColumna;
    }

    public void setColumna(int columna) {
        this.posicionColumna = columna;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
        actualizarColor(); // Cambiar color según estado
    }

    // Cambia el color según el estado del asiento
    private void actualizarColor() {
        if (ocupado) {
            this.setBackground(new Color(164, 3, 31)); // Rojo para ocupado
        } else {
            this.setBackground(new Color(89, 169, 106)); // Verde para disponible
        }
        if (!isEnabled()) {
            this.setBackground(Color.BLACK); // Negro para pagado
        }
    }

    // Alternar el estado del asiento
    public void alternarEstado() {
        this.ocupado = !this.ocupado;
        actualizarColor();
    }
    
    public void reiniciarEstado() {
        this.ocupado = false; // Si tienes un atributo que indica si el asiento está ocupado
        this.setBackground(new Color(89, 169, 106)); // Verde personalizado
    }

	@Override
	public String toString() {
		return "Asientos [posicionFila=" + posicionFila + ", posicionColumna=" + posicionColumna + ", ocupado="
				+ ocupado + "]";
	}
}
