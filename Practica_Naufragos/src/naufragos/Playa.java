package naufragos;

import java.util.concurrent.Semaphore;

public class Playa {
	private int naufragos;

	private Semaphore capacidadPlaya;

	private int totalBarcos;
	private int contador;
//	private boolean quedanNaufragos;

	public Playa(int capacidad) {
		naufragos = (int) (Math.random() * 201 + 800);
		System.out.println("HAY " + naufragos + " NAUFRAGOS");

		capacidadPlaya = new Semaphore(capacidad);
		totalBarcos = 0;
		contador = 0;
//		quedanNaufragos = naufragos > 0 ? true : false;
	}

	public void sumarBarco() {
		totalBarcos++;
	}

	public int recogerNaufragos(Barco b) {
		try {
			capacidadPlaya.acquire();

			if (quedanNaufragos()) {

				Thread.sleep(3000);
				
				int rescatados = restarNaufragos(b.getCapacidadBarco());

				System.out.println("Barco " + b.getId() + " ha rescatado a " + rescatados + " naufragos");

				capacidadPlaya.release();

				return rescatados;
			}
			return 0;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	private synchronized int restarNaufragos(int n) {
		if (getNaufragos() >= n) {
			naufragos -= n;

			return n;
		} else {
			int quedan = naufragos;
			naufragos = 0;

			return quedan;
		}
	}

	public synchronized int getNaufragos() {
		return naufragos;
	}

	public synchronized boolean quedanNaufragos() {
		return naufragos > 0;
	}

	public synchronized void terminarRescate() {
		contador++;

		if (contador >= 3) {
			System.out.println("SE TERMINÓ EL RESCATE");
		}
	}
}
