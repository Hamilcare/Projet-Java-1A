import java.util.ArrayList;

public class Singe extends Bushi implements Deplacable {
	
	
	/**
	 * Ce constructeur permet de creer un singe en précisant ses coordonnees, son etat (sa taille) 
	 * et jouable (pour savoir si le lion est jouable ou non)
	 * @param abs abscisse que l'on souhaite attribuer au singe
	 * @param ord ordonnee que l'on souhaite attribuer au singe
	 * @param etat etat (taille) que l'on souhaite attribuer au singe 
	 * @param jouable precise si le singe est jouable ou non (0=jouable, 1=a saute un allie -1=a saute un ennemi
		-2=non jouable)
	 */
	public Singe(int abs, int ord, int etat, int jouable) {
		super(abs, ord, etat, jouable);

	}
	
	/**
     * Ce constructeur permet de creer un singe en précisant ses coordonnees, les autres champs sont fixes par defaut
     * @param abs abscisse que l'on souhaite attribuer au singe
     * @param ord ordonnee que l'on souhaite attribuer au singe
     */
	public Singe(int abs, int ord) {
		super(abs, ord, 1, 0);
	}

	/**
	 * listerDeplacement permet de d'ajouter les deplacements possibles d'un bushi a une arraylist
	 * @param p le plateau de jeu
	 * @return ArrayList<Bushi> liste des deplacement possibles de l'instance courante de singe
	 */

	@Override
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
				if (this.reachable(this.abs + 2 * i, this.ord + 2 * j, p)) {
					possible.add(p.plateau[this.ord + 2 * j][this.abs + 2 * i]);
				}
			}

		}
		return possible;

	}
	

	/**
	  * toString() renvoie une chaine de caracteres précisant la classe (Singe) et ses coordonnees
	  * @return "Singe [" + (char) (this.abs + 'a') + "," + this.ord + "]"; précise la classe et l'abcisse et l'ordonnee entre crochets
	  */
	@Override
	public String toString() {

		return "Singe [" + (char) (this.abs + 'a') + "," + this.ord + "]";

	}

}
