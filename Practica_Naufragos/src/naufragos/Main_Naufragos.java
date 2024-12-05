package naufragos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main_Naufragos {

	public static void main(String[] args) {
		ExecutorService ex = Executors.newFixedThreadPool(4);

		Playa p = new Playa(2);

		ex.submit(new Barco(p, 20, 40));
		ex.submit(new Barco(p, 30, 50));
		ex.submit(new Barco(p, 40, 60));
		ex.submit(new Contador(p));

		ex.shutdown();
	}

}
