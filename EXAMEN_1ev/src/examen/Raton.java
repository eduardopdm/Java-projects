package examen;

public class Raton implements Runnable {
	Comedero comedero;

	private String nombre;

	private int peso;

	int contGruyer;
	int contadorFresco;

	public Raton(Comedero comedero, String nombre) {
		this.comedero = comedero;
		this.nombre = nombre;
		peso = 100;

		contGruyer = 0;
		contadorFresco = 0;
	}

	@Override
	public void run() {
		while (peso < 110) {
			String queso = comedero.comerQueso(nombre);

			if (queso.equals("Gruyer")) {
				peso += 1;
				contGruyer++;
			} else if (queso.equals("Queso fresco")) {
				peso += 2;
				contadorFresco++;
			}

			try {
				Thread.sleep((int) (Math.random() * 500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		comedero.parar();
		System.out.println(nombre.toUpperCase() + " HA TERMINADO DE COMER");
		System.out.println("TOTAL: " + nombre + " ha comido " + contGruyer + " bolas de Gruyer");
		System.out.println("TOTAL: " + nombre + " ha comido " + contadorFresco + " bolas de Queso Fresco");
		System.out.println();
	}

}
