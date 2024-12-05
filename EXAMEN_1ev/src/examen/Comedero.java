package examen;

public class Comedero {
	private String[] huecos;

	private boolean comiendo;

	int ratones;
	int parar;

	int contBolas;

	int recGruyer;
	int recFresco;

	public Comedero() {
		huecos = new String[3];

		comiendo = true;

		parar = 0;

		contBolas = 0;

		recGruyer = 0;
		recFresco = 0;

//		for (String h : huecos) {
//			System.out.println(h);
//		}
	}

	public synchronized void ponerQueso(String queso) {
		// HAY SITIO
		boolean puesto = false;
		int pos = -1;
		while (!puesto) {

			if (comiendo) {

				for (int i = 0; i < huecos.length && !puesto; i++) {

					if (huecos[i] == null) {
						huecos[i] = queso;
						puesto = true;
						pos = i;
						contBolas++;
					}
				}

				if (!puesto) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("El dispensador ha puesto una bola de " + queso + " en la posición " + pos);
					System.out.println(mostrarComedero());
					notifyAll();
					return;
				}

			} else {
				return;
			}
		}
	}

	public synchronized String comerQueso(String nombre) {
		boolean comido = false;
		String queso = "";
		int pos = -1;

		while (!comido) {
			// "Gruyer"
			for (int i = 0; i < huecos.length && !comido; i++) {
				if (huecos[i] != null && huecos[i].equals("Gruyer")) {
					queso = "Gruyer";
					comido = true;
					pos = i;
					recGruyer++;
				}
			}
			// "Queso fresco"
			for (int i = 0; i < huecos.length && !comido; i++) {
				if (huecos[i] != null && huecos[i].equals("Queso fresco")) {
					queso = "Queso fresco";
					comido = true;
					pos = i;
					recFresco++;
				}
			}

			if (!comido) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Thread.sleep((int) (Math.random() * 100));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println(nombre + " ha cogido una bola de " + queso);
				huecos[pos] = null;
				notifyAll();
				return queso;
			}
		}
		return null;
	}

	private String mostrarComedero() {
		String s = "";

		s += "[";
		for (int i = 0; i < huecos.length; i++) {
			if (huecos[i] != null) {
				s += huecos[i];
			} else {
				s += "-";
			}

			if (i != huecos.length - 1) {
				s += ", ";
			}
		}
		s += "]\n";

		return s;
	}

	public void anyadirRaton(Raton r) {
		ratones++;
		new Thread(r).start();
	}

	public synchronized void parar() {
		parar++;

		if (parar == ratones) {
			comiendo = false;
			notifyAll();

			System.out.println("Quedan en los huecos: " + mostrarComedero() + "\n");
			System.out.println("Bolas de queso caídas en total: " + contBolas + "\n");
			System.out.println("Recogidas por tipo: [" + recGruyer + ", " + recFresco + "]\n");
		}
	}

	public synchronized boolean isComiendo() {
		return comiendo;
	}
}
