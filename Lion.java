import java.util.ArrayList;

public class Lion extends Bushi implements Deplacable {

	public Lion(int abs, int ord, int etat, int jouable) {
		super(abs, ord, etat, jouable);

	}

	public Lion(int abs, int ord) {
		super(abs, ord, 2, 0);
	}

	/**
	 * listerDeplacement permet de d'ajouter les deplacements possibles d'un bushi a une arraylist
	 * @param p le plateau de jeu 
	 * @return possible Renvoie une ArrayList contenant les deplacements possibles de l'instance courante de Lion
	 */
    public ArrayList<Bushi> listerDeplacement(Plateau p) {

    	int i;
		int j;
		ArrayList<Bushi> possible = new ArrayList<Bushi>();

		// i & j repr�sente la valeur du d�calage par rapport aux coordonn�es
		// courantes
		for (i = -1; i <= 1; i++) {

			for (j = 1; j >= -1; j--) {
				// System.out.println("x= " + (this.abs + i) + " y =" +
				// (this.ord + j));
				if (this.reachable(this.abs + i, this.ord + j, p)) {
					possible.add(p.plateau[this.ord + j][this.abs + i]);
				}

				// On regarde un coup plus loin
				if (this.reachable(this.abs + 2 * i, this.ord + 2 * j, p) && p.plateau[this.ord + j][this.abs + i].etat <= this.etat) {
					possible.add(p.plateau[this.ord + 2 * j][this.abs + 2 * i]);
				}
			}

		}
		return possible;


	}

	@Override
	public String toString() {

		return "Lion [" + (char) (this.abs + 'a') + "," + this.ord + "]";

	}

	/*
	 * public ArrayList<Bushi> Deplacement(Plateau p) {
	 * 
	 * int i; int j; ArrayList<Bushi> possible = new ArrayList<Bushi>(); for (i
	 * = this.getAbs() - 1; i <= this.getAbs() + 1; i++) {
	 * 
	 * for (j = this.getOrd() - 1; j <= this.getOrd() + 1; j++) {
	 * 
	 * if((i >= 0 && i < 10) && (j>=0 && j<10)){ if
	 * (this.reachable(p.plateau[i][j], p)) { possible.add(p.plateau[i][j]); } }
	 * } } return possible; }
	 */

}
