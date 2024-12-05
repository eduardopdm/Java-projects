package main;

import java.io.*;
import java.util.*;

public class Main_Padre {

	public static void main(String[] args) {

		File fImpares = new File("..\\PMPO_Impares\\bin");
		ProcessBuilder pbImpares = new ProcessBuilder("java", "impares.Impares");
		pbImpares.directory(fImpares);

		File fOrdenar = new File("..\\PMPO_Ordenar\\bin");
		ProcessBuilder pbOrdenar = new ProcessBuilder("java", "ordenar.Ordenar");
		pbOrdenar.directory(fOrdenar);

		try {
			Process pImpares = pbImpares.start();

			int exitI = pImpares.waitFor();

			System.out.println("Valor de salida de proceso Impares: " + exitI);
			System.out.println("Salida de proceso Impares:");

			ArrayList<Integer> numeros = new ArrayList<Integer>();

			try (Scanner reader = new Scanner(pImpares.getInputStream())) {
				while (reader.hasNext()) {
					int n = Integer.parseInt(reader.nextLine());
					numeros.add(n);
					System.out.println(n);
				}
			}
			System.out.println("Fin de salida de proceso Impares\n");

			Process pOrdenar = pbOrdenar.start();

			try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(pOrdenar.getOutputStream()))) {
				for (int i = 0; i < numeros.size(); i++) {
					int n = numeros.get(i);
					writer.write(n + "\n");
				}
			}

			int exitO = pOrdenar.waitFor();

			System.out.println("Valor de salida de proceso Ordenar: " + exitO);
			System.out.println("Salida de proceso Ordenar:");

			try (Scanner reader = new Scanner(pOrdenar.getInputStream())) {
				while (reader.hasNext()) {
					System.out.println(reader.nextLine());
				}
			}
			System.out.println("Fin de salida de proceso Ordenar");

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}

	}

}
