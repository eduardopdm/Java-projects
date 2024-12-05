package procesador;

import java.util.Locale;

public class Procesador extends Thread {
	private static int n = 1;

	private CuentaEmpresa empresa;
	private int id;
	private double total;

	public Procesador(CuentaEmpresa empresa) {
		this.empresa = empresa;
		id = n;
		total = 0;

		n++;
	}

	@Override
	public void run() {
		while (!empresa.isFinalizado()) {

			total += empresa.hacerTransaccion(this);
		}

		total = Double.parseDouble(String.format(Locale.ENGLISH, "%.2f", total));

		/*
		 * Hago el Sleep fuera del while solo para que Procesador X ha terminado se
		 * escriba despues de que todas las escrituras en fichero hayan terminado. Es
		 * puramente estetico, se puede quitar y sigue funcionando.
		 */

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(("PROCESADOR " + id + " HA TERMINADO. TOTAL: " + total + "\u20AC\n"));
	}

	public int getID() {
		return id;
	}
}
