package ordenar;

import java.util.*;

public class Ordenar {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int[] numeros = new int[10];

		for (int i = 0; i < 10 && sc.hasNext(); i++) {
			try {
				numeros[i] = Integer.parseInt(sc.nextLine());

			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());

				sc.close();
				System.exit(-1);
			}
		}

//		Arrays.sort(numeros);

		boolean sorted;
		do {
			sorted = false;

			for (int i = 0; i < numeros.length - 1; i++) {
				int n1 = numeros[i];
				int n2 = numeros[i + 1];

				if (n1 > n2) {
					numeros[i] = n2;
					numeros[i + 1] = n1;

					sorted = true;
				}
			}
		} while (sorted);

		for (int n : numeros) {
			System.out.println(n);
		}

		sc.close();
		System.exit(0);

	}

}
