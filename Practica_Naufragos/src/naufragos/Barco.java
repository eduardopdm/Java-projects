package naufragos;

public class Barco implements Runnable {
	private static int n = 1;

	private int id;
	private Playa playa;
	private int capacidadBarco;

	private int rescatados;

	public Barco(Playa playa, int min, int max) {
		id = n;
		n++;

		this.playa = playa;
		capacidadBarco = (int) (Math.random() * (max - min + 1) + min);
		rescatados = 0;
	}

	@Override
	public void run() {
		System.out.println("Barco " + id + " es hilo " + Thread.currentThread().getName() + " y tiene capacidad "
				+ capacidadBarco);

		playa.sumarBarco();

		while (playa.quedanNaufragos()) {
			rescatados += playa.recogerNaufragos(this);
//			Thread.yield();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("\nBARCO " + id + " HA TERMINADO. Ha recogido en total " + rescatados + " naufragos\n");
		playa.terminarRescate();
	}

	public int getId() {
		return id;
	}

	public int getCapacidadBarco() {
		return capacidadBarco;
	}

}
