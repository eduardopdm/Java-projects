package examen;

public class Examen_Main {

	public static void main(String[] args) {
		Comedero c = new Comedero();

		Dispensador d = new Dispensador(c);
		new Thread(d).start();

		Raton r1 = new Raton(c, "Dixie");
		Raton r2 = new Raton(c, "Pixie");
		c.anyadirRaton(r1);
		c.anyadirRaton(r2);
	}

}
