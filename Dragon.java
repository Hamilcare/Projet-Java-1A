import java.util.ArrayList;

public class Dragon extends Bushi implements Deplacable {

	public Dragon(int abs, int ord) {
		super(abs, ord, 3, 0);
	}

/**
	 * listerDeplacement permet de d'ajouter les deplacements possibles d'un bushi a une arraylist
	 * @param p le plateau de jeu 
	 * @return possible Renvoie une ArrayList contenant les deplacements possibles de l'instance courante de Dragon
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
				if(this.reachable(this.abs + i, this.ord +j, p) && p.plateau[this.ord +j][this.abs + i].etat != 0 
						&& p.plateau[this.ord +j][this.abs + i].etat > 0 
						&& this.reachable(this.abs + 2 * i, this.ord + 2 * j, p)){
						possible.add(p.plateau[this.ord + 2 * j][this.abs + 2 * i]);
					
				}
			}

		}
		return possible;

	}
	
	@Override
	public String toString() {

		return "Dragon [" + (char) (this.abs + 'a') + "," + this.ord + "]";

	}
}
