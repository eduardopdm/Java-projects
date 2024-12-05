package pho2;

import java.io.*;
import java.util.*;
import procesador.*;

public class Main_PHO_2 {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {

			System.out.println("Ruta del fichero:");
			String rutaF = sc.nextLine();
			// C:\Users\Vespertino\Documents\WS_Procesos_Servicios\PHO_2
			// C:\Users\gladi\Documents\DAMDAW_2\WS_Procesos_Servicios\PHO_2

			System.out.println("Nombre del fichero:");
			String nombreF = sc.nextLine();
			// transferencias.txt

			int numeroT = -1;

			boolean bien = false;
			System.out.println("Número de transferencias:");
			while (!bien) {
				try {
					numeroT = Integer.parseInt(sc.nextLine());
					bien = true;
				} catch (NumberFormatException e) {
					System.out.println("Introduzca un número entero");
				}
			}

			File fPB = new File("C:\\Users\\Vespertino\\Documents\\WS_Procesos_Servicios\\PHO_1\\bin");
//			File fPB = new File("C:\\Users\\gladi\\Documents\\DAMDAW_2\\WS_Procesos_Servicios\\PHO_1\\bin");
			ProcessBuilder pb = new ProcessBuilder("java", "pho1.Main_PHO_1");
			pb.directory(fPB);

			Process p;
			int exit = -1;
			try {
				p = pb.start();

				try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()))) {
					writer.write(rutaF + "\n");
					writer.write(nombreF + "\n");
					writer.write(numeroT + "\n");
				}

				exit = p.waitFor();

				System.out.println("Valor de salida: " + exit + "\n");

				if (exit == -1) {
					System.out.println("HA HABIDO ALGÚN ERROR");
				} else if (exit == -2) {
					System.out.println("NO HA LLEGADO EL FICHERO DE TRANSFERENCIAS");
				} else if (exit == 0) {
					double saldo = Double.parseDouble(
							String.format(Locale.ENGLISH, "%.2f", (Math.random() * 1000 + 2000) * numeroT));

					double suma = 0;
					try (Scanner reader = new Scanner(p.getInputStream())) {
						if (reader.hasNext()) {
							try {
								suma = Double.parseDouble(reader.nextLine());
							} catch (NumberFormatException e) {
								System.out.println(e.getMessage());
							}
						}
					}
					System.out.println("El importe total de las nóminas es: " + suma + "\u20AC.\n");

					System.out.println("El saldo de la cuenta de la empresa es: " + saldo + "\u20AC.\n");

					CuentaEmpresa empresa = new CuentaEmpresa(saldo, rutaF, nombreF);

					while (!empresa.isFinalizado())
						;

					saldo = empresa.getSaldo();

					Thread.sleep(2000);

					System.out.println("Saldo final: " + saldo + "\u20AC");
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
