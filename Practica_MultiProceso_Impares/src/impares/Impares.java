package impares;

public class Impares {

	public static void main(String[] args) {

		int i = 10;

		while (i > 0) {
			int n = (int) (Math.random() * 100);

			if (n % 2 != 0) {
				System.out.println(n);
				i--;
			}
		}
	}

}
