package naufragos;

public class Contador extends Thread {
	private Playa playa;
	private int n;

	public Contador(Playa playa) {
		this.playa = playa;
		n = 1;
	}

	@Override
	public void run() {
		while (playa.quedanNaufragos()) {
			try {
				for (int i = 0; i < 2; i++) {
					Thread.sleep(1000);
					System.out.println(n++);
				}
				Thread.sleep(1000);

				System.out.println("\n" + (n++) + " Quedan " + playa.getNaufragos() + " naufragos\n");

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
