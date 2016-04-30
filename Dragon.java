import java.util.ArrayList;

public class Dragon extends Bushi {

	private static final long serialVersionUID = -6792626011613747810L;

	/**
	 * 
	 */
	public Dragon(int abs, int ord) {
		super(abs, ord, 3, 0);
	}

	/**
	 * listerDeplacement permet de d'ajouter les deplacements possibles d'un
	 * bushi a une arraylist
	 * 
	 * @param p
	 *            le plateau de jeu
	 * @return possible Renvoie une ArrayList contenant les deplacements
	 *         possibles de l'instance courante de Dragon
	 */

	public ArrayList<Bushi> listerDeplacement(Plateau p) {

		int i;
		int j;
		ArrayList<Bushi> possible = new ArrayList<Bushi>();

		// i & j repr�sente la valeur du d�calage par rapport aux coordonn�es
		// courantes
		for (i = -1; i <= 1; i++) {

			for (j = 1; j >= -1; j--) {
				// System.out.println("x= " + (this.abs + i) + " y =" /**

				// (this.ord + j));
				if (this.reachable(this.abs + 2 * i, this.ord + 2 * j, p)) {
					possible.add(p.plateau[this.ord + 2 * j][this.abs + 2 * i]);

				}
			}

		}
		return possible;

	}

	@Override
	public boolean reachable(int abs, int ord, Plateau p) {
		boolean rep = super.reachable(abs, ord, p);
		if (rep) {// Si la case est dans le plateau
			// System.out.println("La case n'est pas contigue");
			Bushi destination = p.plateau[ord][abs];
			if ((abs - this.abs <= 1 && abs - this.abs >= -1) && (ord - this.ord <= 1 && ord - this.ord >= -1)) { // Si
																													// la
																													// case
																													// est
																													// contigue
				return false;
			} else {
				int absInter = (this.abs + abs) / 2;
				int ordInter = (this.ord + ord) / 2;

				rep = (((p.plateau[ordInter][absInter].getEtat() > 0)
						|| (p.plateau[ordInter][absInter].getEtat() == -2))
						&& ((destination.getEtat() == 0) || (destination.getEtat() == -2)));

			}

		}
		// System.out.println(rep);
		return rep;
	}

	@Override
	public String toString() {

		return "Dragon [" + (char) (this.abs + 'a') + "," + this.ord + "]";

	}
}
