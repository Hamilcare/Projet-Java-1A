import java.util.ArrayList;
import java.util.Scanner;

public class Bushi {

	protected int abs;
	protected int ord;
	protected int etat; // 0=vide, -1= case bloqu�e, -2=case portail,
						//
	protected int jouable; // o=jouable 1=saut� un alli� -1=saut� un ennemi
							// -2=non jouable

	public void setEtat(int etat) {

		this.etat = etat;
	}

	public Bushi(int abs, int ord, int etat, int jouable) {
		super();
		this.abs = abs;
		this.ord = ord;
		this.etat = etat;
		this.jouable = jouable;
	}

	/*
	 * public int getTaille() { return taille; }
	 */

	/*
	 * public void setTaille(int taille) { this.taille = taille; }
	 */

	public int getAbs() {

		return abs;
	}

	public void setAbs(int abs) {

		this.abs = abs;
	}

	public int getOrd() {

		return ord;
	}

	public void setOrd(int ord) {

		this.ord = ord;
	}

	public int getEtat() {

		return etat;
	}

	public boolean isJouable() {

		if (this.etat == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isOccupee() {

		if (this.etat == -1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isBloque() {

		if (this.etat == -2) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isPortail() {

		if (this instanceof Portail) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * D�termine si le bushi a saut�
	 * 
	 * @param destination
	 * @param p
	 * @return le type de saut (alli�,ennemi,rien)(0,1,2)
	 */
	public boolean aSaute(Bushi destination, Plateau p) {

		if ((destination.abs - this.abs <= 1 && destination.abs + this.abs >= -1)
				&& (destination.ord - this.ord <= 1 && destination.ord + this.ord >= -1)) {
			return false; // verifier si la case est contigue ==> rien saut�
		}

		else {
			int absInter = (this.abs + destination.abs) / 2;
			int ordInter = (this.ord + destination.ord) / 2;

			if (p.joueurs[p.joueurCourant].bushiJoueur.contains(p.plateau[absInter][ordInter])) {
				return true; // a saute un allie
			} else {
				return true; // a saute un enemi
			}
		}

	}

	// public ArrayList

	/**
	 * Regarde si la destination est accessible
	 * 
	 * @param destination
	 *            case d'arriv�e
	 * @param p
	 *            le plateau de jeu
	 * @return boolean true si la case est atteignable
	 */
	public boolean reachable(int abs, int ord, Plateau p) {

		boolean rep = false;
		// On teste si la case de destination est contigue à la case de
		// depart
		if ((abs >= 0 && abs < 10) && (ord >= 0 && ord < 10)) {
			Bushi destination = p.plateau[ord][abs];
			// System.out.println(destination);
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

				rep = (p.plateau[absInter][ordInter].etat >= 0 && p.plateau[absInter][ordInter].etat <= this.etat
						&& destination.etat == 0);
				/*
				 * etat==0 ::::> la case est vide etat<=1 ::::> la case est
				 * occupee par un bushi plus petit ou de taille �quivalente
				 */
			}
		}
		// System.out.println("rep=" + rep);
		return rep;

	}

	/**
	 * 
	 * @param destination
	 *            case d'arrivee
	 * @param p
	 *            plateau de jeu Effectue le d�placement
	 */

	public void effectuerDeplacement(Bushi destination, Plateau p) {

		int prevAbs = this.abs;
		int prevOrd = this.ord;

		// I) on met � jour les coord du Bushi deplace et la case d'arrivee
		this.abs = destination.abs;
		this.ord = destination.ord;
		p.plateau[this.ord][this.abs] = this;

		// II)On vide la case de depart
		p.plateau[prevOrd][prevAbs] = new Bushi(prevAbs, prevOrd, 0, -2);

	}

	/**
	 * Demande au joueur quel case il souhaite atteindre
	 * 
	 * @see Deplacable#choisirDeplacement(Plateau)
	 * @param p
	 *            le plateau de jeu
	 * @return Bushi la case de destination choisie par le joueur
	 */

	public Bushi choisirDeplacement(Plateau p) {

		Scanner sc = new Scanner(System.in);
		int i;
		int choix = 0;
		ArrayList<Bushi> deplacementsPossibles = this.listerDeplacement(p);

		Affichage.affichePlateau(p, this, deplacementsPossibles);

		for (i = 0; i < deplacementsPossibles.size(); i++) {
			/*
			 * System.out.println((i + 1) + ": [" +
			 * deplacementsPossibles.get(i).getAbs() + "," +
			 * deplacementsPossibles.get(i).getOrd() + "]\n");
			 */
			System.out.println((i + 1) + " " + deplacementsPossibles.get(i));
		}
		try {
			choix = sc.nextInt();
			sc.close();

		} catch (Exception e) {
			System.out.println(e);
			// !!!!!!!!!!!EXPERIMENTAL!!!!!!!!!!!!!!!!
			// return this.choisirDeplacement(p);
		}
		System.out.println("Choix : " + choix);

		return deplacementsPossibles.get((choix - 1));

	}

	// CRADE
	public ArrayList<Bushi> listerDeplacement(Plateau p) {

		return new ArrayList<Bushi>();
	}

	@Override
	public String toString() {

		String s = "";
		switch (this.etat) {
		case 0:
			s = "[" + (char) (this.abs + 'a') + "," + this.ord + "]";
			break;
		case -1:
			s = "X";
			break;
		default:
			s = "8";
			break;
		}
		return s;

	}

	public boolean equals(Bushi b) {

		return this.abs == b.abs && this.ord == b.ord && this.etat == b.etat && this.jouable == b.jouable;

	}

	/**
	 * 
	 * @param j
	 * @return true si le bushi appartient au joueur passer en paramètre
	 */
	public boolean Appartient(Joueur j) {
		return j.bushiJoueur.contains(this);

	}

}
