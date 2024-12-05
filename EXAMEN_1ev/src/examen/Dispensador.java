package examen;

public class Dispensador implements Runnable {
	Comedero comedero;

	public Dispensador(Comedero comedero) {
		this.comedero = comedero;
	}

	@Override
	public void run() {
		while (comedero.isComiendo()) {
			int azar = (int) (Math.random() * 2);
			String queso = azar == 0 ? "Gruyer" : "Queso fresco";

			comedero.ponerQueso(queso);

			try {
				Thread.sleep((int) (Math.random() * 200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("SE PARA EL DISPENSADOR");
	}

}
