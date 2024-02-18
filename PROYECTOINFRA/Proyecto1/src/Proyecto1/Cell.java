package Proyecto1;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Cell {
	
	private Boolean estado;
	private int fila;
	private int columna;
	private ArrayList<Integer> infoVecinas= new ArrayList<>();
	
	public Cell(Boolean estado, int fila, int columna) {
		
		this.estado = estado;
		this.fila = fila;
		this.columna = columna;
		this.infoVecinas.add(0);
		this.infoVecinas.add(0);
		
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public ArrayList<Integer> getInfoVecinas() {
		return infoVecinas;
	}

	public void setInfoVecinas(ArrayList<Integer> infoVecinas) {
		this.infoVecinas = infoVecinas;
	}
	
	 public void cambioEstado() {
	        if (infoVecinas.get(1)== 3 && estado.equals(false)) {
	            estado = true;
	        } else if (estado.equals(true) && infoVecinas.get(1) > 3) {
	            estado = false;
	        } else if (estado.equals(true) && infoVecinas.get(1) == 0) {
	            estado = false;
	        } else if (estado.equals(true) && infoVecinas.get(1) >= 1 && infoVecinas.get(1) <= 3) {
	            estado = true;
	        }
	 }

}

