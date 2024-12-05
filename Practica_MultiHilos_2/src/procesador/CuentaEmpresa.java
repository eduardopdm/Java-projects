package procesador;

import java.io.*;
import java.util.*;

public class CuentaEmpresa {

	private double saldo;

	private File fTransacciones;
	private File fInternas;
	private File fExternas;
	private File fSinSaldo;

	private ArrayList<String> transascciones;

	private int i;

	private boolean finalizado;

	Procesador p1;
	Procesador p2;
	Procesador p3;

	public CuentaEmpresa(double saldo, String ruta, String fTransacciones) {
		this.saldo = saldo;
		
		this.fTransacciones = new File(ruta + "\\" + fTransacciones);
		fInternas = new File(ruta + "\\transferenciasInternas.txt");
		fExternas = new File(ruta + "\\transferenciasExternas.txt");
		fSinSaldo = new File(ruta + "\\transferenciasSinSaldo.txt");

		if (fInternas.exists())
			fInternas.delete();

		if (fExternas.exists())
			fExternas.delete();

		if (fSinSaldo.exists())
			fSinSaldo.delete();

		this.transascciones = new ArrayList<String>();

		i = 0;

		finalizado = false;

		try (Scanner reader = new Scanner(new FileReader(this.fTransacciones))) {
			while (reader.hasNext()) {
				transascciones.add(reader.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		p1 = new Procesador(this);
		p2 = new Procesador(this);
		p3 = new Procesador(this);

		p1.start();
		p2.start();
		p3.start();
	}

	public double hacerTransaccion(Procesador p) {

		if (i >= transascciones.size()) {
			finalizado = true;
			return 0;
		}

		String transaccion = elegirTransaccion();

		String[] array = transaccion.split(";");

		String cuenta = array[0];
		double nomina = Double.parseDouble(array[1]);

		if (actualizarSaldo(nomina)) {
			if (((Character) (cuenta.charAt(0))).equals('1')) {
				// INTERNA()

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				escribirFichero(p, fInternas, cuenta, nomina);

			} else if (((Character) (cuenta.charAt(0))).equals('2')) {
				// EXTERNA()

				escribirFichero(p, fExternas, cuenta, nomina);

			} else {
				System.out.println("ERROR. Cuenta incorrecta\n");
			}

		} else {

			escribirFichero(p, fSinSaldo, cuenta, nomina);
		}

		return nomina;
	}

	private synchronized String elegirTransaccion() {
		String s = transascciones.get(i);
		i++;
		return s;
	}

	private synchronized boolean actualizarSaldo(double nomina) {
		if (getSaldo() >= nomina) {

			saldo = Double.parseDouble(String.format(Locale.ENGLISH, "%.2f", saldo - nomina));

			return true;

		} else {
			return false;
		}
	}

	private synchronized boolean escribirFichero(Procesador p, File f, String cuenta, double nomina) {
		System.out.println("Cuenta: " + cuenta + " - Nómina: " + nomina + "\u20AC (Procesador " + p.getID() + ") ");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(f, true))) {
			writer.write(cuenta + ";" + nomina + "\n");

			if (f.compareTo(fInternas) == 0) {
				System.out.println("Transferencia INTERNA grabada. Cuenta: " + cuenta + ".\n");
			} else if (f.compareTo(fExternas) == 0) {
				System.out.println("Transferencia EXTERNA grabada. Cuenta: " + cuenta + ".\n");
			} else if (f.compareTo(fSinSaldo) == 0) {
				System.out.println("No hay saldo para la siguiente transferencia. Cuenta: " + cuenta + ".\n");
			}

			return true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public synchronized boolean isFinalizado() {
		return finalizado;
	}

	public synchronized double getSaldo() {
		return saldo;
	}

}
