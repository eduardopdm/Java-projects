package main;

import java.io.*;
import java.util.*;

public class Main_Padre_Ficheros {

	public static void main(String[] args) {

		File fImparesOut = new File("imparess.txt");
		File fOrdenarOut = new File("orden.txt");

		File fImpares = new File("..\\PMPO_Impares\\bin");
		ProcessBuilder pbImpares = new ProcessBuilder("java", "impares.Impares");
		pbImpares.directory(fImpares);

		pbImpares.redirectOutput(fImparesOut);

		File fOrdenar = new File("..\\PMPO_Ordenar\\bin");
		ProcessBuilder pbOrdenar = new ProcessBuilder("java", "ordenar.Ordenar");
		pbOrdenar.directory(fOrdenar);

		pbOrdenar.redirectInput(fImparesOut);
		pbOrdenar.redirectOutput(fOrdenarOut);

		try {
			Process pImpares = pbImpares.start();

			int exitI = pImpares.waitFor();

			System.out.println("Valor de salida de proceso Impares: " + exitI);

			Process pOrdenar = pbOrdenar.start();

			int exitO = pOrdenar.waitFor();

			System.out.println("Valor de salida de proceso Ordenar: " + exitO);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}

	}

}
