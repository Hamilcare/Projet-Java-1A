import java.io.Serializable;
import java.util.ArrayList;

public class Lion extends Bushi implements Serializable {

	private static final long serialVersionUID = 8168766674021870305L;

	/**
	 * Ce constructeur permet de creer un lion en précisant ses coordonnees, son
	 * etat (sa taille) et jouable (pour savoir si le lion est jouable ou non)
	 * 
	 * @param abs
	 *            abscisse que l'on souhaite attribuer au Lion
	 * @param ord
	 *            ordonnee que l'on souhaite attribuer au Lion
	 * @param etat
	 *            etat (taille) que l'on souhaite attribuer au Lion
	 * @param jouable
	 *            precise si le Lion est jouable ou non (0=jouable, 1=a saute un
	 *            allie -1=a saute un ennemi -2=non jouable)
	 */
	public Lion(int abs, int ord, int etat, int jouable) {
		super(abs, ord, etat, jouable);

	}

	/**
	 * Ce constructeur permet de creer un lion en précisant ses coordonnees, les
	 * autres champs sont fixes par defaut
	 * 
	 * @param abs
	 *            abscisse que l'on souhaite attribuer au Lion
	 * @param ord
	 *            ordonnee que l'on souhaite attribuer au Lion
	 */
	public Lion(int abs, int ord) {
		super(abs, ord, 2, 0);
	}

	/**
	 * listerDeplacement permet de d'ajouter les deplacements possibles d'un
	 * bushi a une arraylist
	 * 
	 * @param p
	 *            le plateau de jeu
	 * @return possible Renvoie une ArrayList contenant les deplacements
	 *         possibles de l'instance courante de Lion
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

				// On regarde un coup plus loin si la case intermédiare est
				// occupée
				if (this.reachable(this.abs + 2 * i, this.ord + 2 * j, p)
						&& p.plateau[this.ord + j][this.abs + i].etat <= this.etat) {
					possible.add(p.plateau[this.ord + 2 * j][this.abs + 2 * i]);
				}
			}

		}
		return possible;

	}

	/**
	 * toString() renvoie une chaine de caracteres précisant la classe (Lion) et
	 * ses coordonnees
	 * 
	 * @return "Lion [" + (char) (this.abs + 'a') + "," + this.ord + "]";
	 *         précise la classe et l'abcisse et l'ordonnee entre crochets
	 */
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

	/**
	 * Regarde si la destination est accessible
	 * 
	 * @param destination
	 *            case d'arriv�e
	 * @param p
	 *            le plateau de jeu
	 * @return boolean true si la case est atteignable
	 */
	@Override
	public boolean reachable(int abs, int ord, Plateau p) {

		boolean rep = super.reachable(abs, ord, p);

		if (rep) {// Regarde si les coordonnées sont
					// valides ie dans le plateau

			Bushi destination = p.plateau[ord][abs];
			if ((abs - this.abs <= 1 && abs + this.abs >= -1) && (ord - this.ord <= 1 && ord + this.ord >= -1)) {
				// Dans ce cas il suffit de regarder si la case est vide

				rep = destination.etat == 0;
			} else {
				/*
				 * Sinon il faut vérifier que la case intermediaire est vide ou
				 * bien contient un bushi que l'on peut sauter
				 */

				// Calcul des coordonnees de la case intermediaire
				int absInter = (this.abs + abs) / 2;
				int ordInter = (this.ord + ord) / 2;

				rep = (p.plateau[ordInter][absInter].getEtat() > 0
						&& p.plateau[ordInter][absInter].getEtat() <= this.etat && destination.etat == 0);

				/*
				 * etat==0 ::::> la case est vide etat<=1 ::::> la case est
				 * occupee par un bushi plus petit ou de taille �quivalente
				 */
			}
		}
		// System.out.println("rep=" + rep);
		return rep;

	}

}
/*
 * boolean rep = false; // On teste si la case de destination est contigue à la
 * case de // depart if ((abs >= 0 && abs < 10) && (ord >= 0 && ord < 10)) {
 * Bushi destination = p.plateau[ord][abs]; // System.out.println(destination);
 * if ((abs - this.abs <= 1 && abs + this.abs >= -1) && (ord - this.ord <= 1 &&
 * ord + this.ord >= -1)) { // Dans ce cas il suffit de regarder si la case est
 * vide
 * 
 * rep = destination.etat == 0; } else { /* Sinon il faut vérifier que la case
 * intermediaire est vide ou bien contient un bushi que l'on peut sauter
 */
/*
 * // Calcul des coordonnees de la case intermediaire int absInter = (this.abs +
 * abs) / 2; int ordInter = (this.ord + ord) / 2;
 * 
 * rep = (p.plateau[ordInter][absInter].etat >= 0 &&
 * p.plateau[ordInter][absInter].etat <= this.etat && destination.etat == 0);
 * 
 * if (this instanceof Dragon && p.plateau[ord][abs] instanceof Portail) {
 * return true; }
 * 
 * /* etat==0 ::::> la case est vide etat<=1 ::::> la case est occupee par un
 * bushi plus petit ou de taille �quivalente
 * 
 * } }
 */
