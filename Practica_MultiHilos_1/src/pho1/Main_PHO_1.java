package pho1;

import java.util.*;
import java.io.*;

public class Main_PHO_1 {

	public static void main(String[] args) {
		double azar = Math.random() * 100;

		if (azar < 30) {
			System.out.println("Error");
			System.exit(-2);
		} else {
			try (Scanner sc = new Scanner(System.in)) {
				String ruta = sc.nextLine();
				String nombre = sc.nextLine();
				int nTransferencias = Integer.parseInt(sc.nextLine());

				File fichero = new File(ruta + "\\" + nombre);

				try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichero));) {
					double suma = 0;

					for (int i = 0; i < nTransferencias; i++) {
						int cuenta = (int) (Math.random() * 2 + 1);

						for (int j = 0; j < 8; j++) {
							cuenta = cuenta * 10 + (int) (Math.random() * 10);
						}
						// System.out.print(cuenta);

						double nomina = Double
								.parseDouble(String.format(Locale.ENGLISH, "%.2f", Math.random() * 1500 + 1500));

						// System.out.println(": " + nomina);

						suma = Double.parseDouble(String.format(Locale.ENGLISH, "%.2f", suma + nomina));

						writer.write(cuenta + ";" + nomina + "\n");
					}

					System.out.println(suma);
				}
			} catch (NumberFormatException e) {
				// System.out.println(e.getMessage());
				System.exit(-1);

			} catch (IOException e) {
				// System.out.println(e.getMessage());
				System.exit(-1);

			} catch (Exception e) {
				// System.out.println(e.getMessage());
				System.exit(-1);

			}
		}
	}

}
