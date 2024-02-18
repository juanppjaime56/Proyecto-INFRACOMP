package Proyecto1;

import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Buffer extends Thread{
	
	private ArrayList buff ;
	private int n ;
	private Cell celula;
	private  int countMax;
	private int countActual=0;
	CyclicBarrier barrera;
	CyclicBarrier barrera2;
	
		public Buffer (int n, Cell celula, int countMax, CyclicBarrier barrera,CyclicBarrier barrera2) {
			this.n = n ;
			this.buff = new ArrayList() ;
			this.celula = celula;
			this.countMax=countMax;
			this.barrera = barrera;
			this.barrera2 = barrera2;
	}
		
		public synchronized void almacenar (boolean i) throws InterruptedException 
		{
		   while (buff.size() == n)
			 wait() ; //Conceptual
			 buff.add (i) ;
			 //System.out.print(i);
			 countActual++;
			}
		
		public synchronized void vaciarBuffer()throws InterruptedException
		{
			while(buff.size()==0) {
				wait();
			}
			while(buff.size()!=0)
			{
				if (this.buff.get(0).equals(true))
				{
					//System.out.println("entro");
					int cantidad = celula.getInfoVecinas().get(0);
					int cantidad2 = celula.getInfoVecinas().get(1);
					ArrayList cantidad3 = new ArrayList();
					cantidad3.add(cantidad);
					cantidad3.add(cantidad2+1);
					celula.setInfoVecinas(cantidad3);
					buff.remove(0);
					
				}else {
					//System.out.println("noe");
					int cantidad = celula.getInfoVecinas().get(0);
					int cantidad2 = celula.getInfoVecinas().get(1);
					ArrayList cantidad3 = new ArrayList();
					cantidad3.add(cantidad+1);
					cantidad3.add(cantidad2);
					celula.setInfoVecinas(cantidad3);
					buff.remove(0);
				}
			}
			
			notifyAll();
			
		}
		
		
		public void run()
		{
		
			countActual=0;
			while(countActual<countMax)
			{
				try {
					sleep(1000);
					this.vaciarBuffer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("AAAA" + celula.getFila() +"," + celula.getColumna());
			
			try {
				
				barrera.await();
				System.out.println("BBB");
				celula.cambioEstado();
				
				barrera2.await();
				System.out.println("CCC");
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
			
		}

		public ArrayList getBuff() {
			return buff;
		}

		public void setBuff(ArrayList buff) {
			this.buff = buff;
		}

		public int getN() {
			return n;
		}

		public void setN(int n) {
			this.n = n;
		}

		public Cell getCelula() {
			return celula;
		}

		public void setCelula(Cell celula) {
			this.celula = celula;
		}

		public int getCountMax() {
			return countMax;
		}

		public void setCountMax(int countMax) {
			this.countMax = countMax;
		}

		public int getCountActual() {
			return countActual;
		}

		public void setCountActual(int countActual) {
			this.countActual = countActual;
		}
		
		
}