import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represente un bushi avec son abscisse, son ordonne, sa taille et sa
 * jouabilité
 * 
 * 
 * Implemente l'interface Serializable afin d'etre serialiser lors de la
 * sauvegarde de la partie
 */
public class Bushi implements Serializable {

	private static final long serialVersionUID = 3656608221346934953L;
	protected int abs;
	protected int ord;
	protected int etat; /* 0=vide, -1= case bloquée, -2=case portail*/

	protected int jouable; /*o=jouable -2 non jouable*/

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

	/**
	 * 
	 * @return l'abscisse
	 */
	public int getAbs() {

		return abs;
	}

	/**
	 * 
	 * @param abs
	 *            l'abscisse que l'on souhaite donner
	 */
	public void setAbs(int abs) {

		this.abs = abs;
	}

	/**
	 * 
	 * @return l'ordonnée
	 */
	public int getOrd() {

		return ord;
	}

	/**
	 * 
	 * @param ord
	 *            l'ord que l'on souhaite donner
	 */
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

	/**
	 * Détermine si le bushi a sauté
	 * 
	 * @param destination
	 * @param p
	 * @return true si le bushi a sauté
	 */
	public boolean aSaute(Bushi destination, Plateau p) {

		// System.out.println(this instanceof Singe);

		if (((destination.abs - this.abs <= 1) && (destination.abs - this.abs >= -1))
				&& ((destination.ord - this.ord <= 1) && (destination.ord - this.ord >= -1))) {

			return false; // verifier si la case est contigue ==> rien

		}

		else {
			int absInter = (this.abs + destination.abs) / 2;
			int ordInter = (this.ord + destination.ord) / 2;
			Bushi inter = p.plateau[ordInter][absInter];
			System.out.println(inter);

			if (inter.etat == 0) {
				return false;
			} else { // Arrive sur une case non contigue

				if (this instanceof Singe && this.reachable(absInter, ordInter, p)) {
					System.out.println("COUCOU");
					/*
					 * case intermédiaire est vide ==> le singe s'est déplacé de
					 * deux cases sans sauter un autre BUshi
					 */
					return false;

				}
				/* A saute un allie */
				else if (p.joueurs[p.joueurCourant].getBushiJoueur().contains(inter)) {

					for (Bushi b : p.joueurs[p.joueurCourant].getBushiJoueur()) {
						b.jouable = -2;
					}
					this.jouable = 0;

					return true;
				} else {

					/* A saute un ennemi */
					if (!p.joueurs[p.joueurCourant].getBushiJoueur().contains(inter)) {

						if ((p.plateau[ordInter][absInter] instanceof Portail) == false) {
							p.joueurs[p.autreJoueur()].retirerBushi(p.plateau[ordInter][absInter]);
							p.plateau[ordInter][absInter] = new Bushi(absInter, ordInter, 0, 0);
						}

						for (Bushi b : p.joueurs[p.joueurCourant].getBushiJoueur()) {
							if (!(b instanceof Portail))
								b.jouable = 0;
						}
						this.jouable = -2;
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Regarde si la destination est accessible
	 * 
	 * @param destination
	 *            case d'arrivée
	 * @param p
	 *            le plateau de jeu
	 * @return boolean true si la case est atteignable
	 */
	public boolean reachable(int abs, int ord, Plateau p) {

		return (abs >= 0 && abs < 10) && (ord >= 0 && ord < 10);

	}

	/**
	 * 
	 * @param destination
	 *            case d'arrivee
	 * @param p
	 *            plateau de jeu Effectue le déplacement
	 */

	public void effectuerDeplacement(Bushi destination, Plateau p) {

		int prevAbs = this.abs;
		int prevOrd = this.ord;

		// I) on met à jour les coord du Bushi deplace et la case d'arrivee
		p.joueurs[p.autreJoueur()].retirerBushi(p.plateau[destination.ord][destination.abs]);
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

	public Bushi choisirDeplacement(Plateau p, Scanner sc) {

		int i;
		int choix = 0;
		ArrayList<Bushi> deplacementsPossibles = this.listerDeplacement(p);

		Affichage.affichePlateau(p, this, deplacementsPossibles);

		int j = 1;// Utilisé pour l'affichage

		for (i = 0; i < deplacementsPossibles.size(); i++) {

			System.out.print((i + 1) + " " + deplacementsPossibles.get(i) + "		");
			if (j % 6 == 0)
				System.out.print("\n");
			j++;
		}

		System.out.println("Si vous souhaitez changer de Bushi, entrez un nombre qui n'est pas dans la liste");

		try {
			choix = sc.nextInt();

		} catch (InputMismatchException e) {
			System.out.println("Mauvaise saisie, vous devez rentrer un nombre figurant dans la liste");
			sc.next();
			return this.choisirDeplacement(p, sc);
		}

		catch (IndexOutOfBoundsException e) {
			throw e;
		}
		System.out.println("Choix : " + choix);

		return deplacementsPossibles.get((choix - 1));

	}

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
		return j.getBushiJoueur().contains(this);

	}

}
