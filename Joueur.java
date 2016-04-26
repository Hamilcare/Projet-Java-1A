import java.util.ArrayList;

public class Joueur {

	String nom;
	// Bushi[] tab = new Bushi[12];
	ArrayList<Bushi> bushiJoueur = new ArrayList<Bushi>();

	public Joueur() {
		nom = "";
		bushiJoueur = new ArrayList<Bushi>();

	}

	public Joueur(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {

		return nom;
	}

	public void setNom(String nom) {

		this.nom = nom;
	}

	// Ici m�thode pour load un fichier texte avec tous les bushis du joueur
	// afin de les placer dans le tableau
	public void ajouterBushi(Bushi b) {

		bushiJoueur.add(b);
	}

	public void parcourirBushi() {

		Bushi[] tab = new Bushi[12];
		bushiJoueur.toArray(tab);

		for (Bushi b : bushiJoueur) {
			System.out.println(b);
		}
	}

	@Override
	public String toString() {

		return "Joueur [nom=" + nom + "]";
	}

	/**
	 * Le joueur a perdu si il n'a plus de Dragon ou si un de ses portails est
	 * pris
	 * 
	 * @return true si le joueur à perdu
	 */
	public boolean aPerdu() {
		int nbDragon = 0;
		int nbPortail = 0;

		for (Bushi b : bushiJoueur) {
			if (b instanceof Dragon)
				nbDragon++;
			if (b instanceof Portail) {
				nbPortail++;
			}
		}
		return nbDragon >= 1 && nbPortail == 2;

	}

}
