package Proyecto1;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Notificador extends Thread{
	
	private ArrayList<Buffer> listaBuffers;
	private Cell celula; 
	private CyclicBarrier barrera;
	private CyclicBarrier barrera2;
	
	public Notificador(CyclicBarrier barrera2, ArrayList<Buffer> listaBuffers, Cell celula, CyclicBarrier barrera) {
		this.barrera = barrera;
		this.celula = celula;
		this.listaBuffers = listaBuffers;
		this.barrera2 = barrera2;
	}
	
	public void run() {
		
		for(int i = 0; i < listaBuffers.size(); i++) {
			int vecinaFila = listaBuffers.get(i).getCelula().getFila();
			int vecinaColumna = listaBuffers.get(i).getCelula().getColumna();
			int distanciaFila = Math.abs(celula.getFila() - vecinaFila);
			int distanciaColumna = Math.abs(celula.getColumna() - vecinaColumna);
			
			// Verificar si la célula vecina es adyacente
			if ((distanciaFila == 1 && distanciaColumna == 0) || 
				(distanciaFila == 0 && distanciaColumna == 1) || 
				(distanciaFila == 1 && distanciaColumna == 1)) {
				
				// Realizar acciones dependiendo del estado de la célula propia
				try {
					boolean estadoActual = celula.getEstado();
					listaBuffers.get(i).almacenar(estadoActual);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			barrera.await();
			System.out.print("dasdsa");
			barrera2.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
