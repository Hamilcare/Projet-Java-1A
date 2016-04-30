import java.io.Serializable;
import java.util.ArrayList;

public class Joueur implements Serializable {

	private static final long serialVersionUID = 3493603960468269309L;
	String nom;
	// Bushi[] tab = new Bushi[12];
	ArrayList<Bushi> bushiJoueur = new ArrayList<Bushi>();

	/**
	 * Constructeur qui instancie un joueur sans nom
	 */
	public Joueur() {
		nom = "";
		bushiJoueur = new ArrayList<Bushi>();

	}

	/**
	 * Constructeur qui instancie un joueur en precisant un nom
	 * 
	 * @param nom
	 *            Nom du joueur
	 */
	public Joueur(String nom) {
		super();
		this.nom = nom;
	}

	/**
	 * Renvoie le nom d'un joueur
	 * 
	 * @return nom nom du joueur
	 */
	public String getNom() {

		return nom;
	}

	/**
	 * Permet de modifier le nom d'un joueur
	 * 
	 * @param nom
	 *            Nom que l'on souhaite donner au joueur
	 */
	public void setNom(String nom) {

		this.nom = nom;
	}

	/**
	 * Permet d'ajouter un bushi a la liste des bushis d'un joueur
	 * 
	 * @param b
	 *            Bushi que l'on souhaite attribuer au joueur
	 */
	public void ajouterBushi(Bushi b) {

		bushiJoueur.add(b);
	}

	/**
	 * Permet de parcourir la liste des bushis de l'instance courante de Joueur
	 * 
	 */
	public void parcourirBushi() {

		Bushi[] tab = new Bushi[12];
		bushiJoueur.toArray(tab);

		for (Bushi b : bushiJoueur) {
			System.out.println(b);
		}
	}

	/**
	 * Renvoie une chaine de caracteres precisant la classe (Joueur) et son nom
	 * 
	 * @return "Joueur [nom=" + nom + "]"; Indique la classe et son nom entre
	 *         crochets
	 */
	@Override
	public String toString() {

		return "Joueur [nom=" + nom + "]";
	}

	/**
	 * Renvoie si le joueur a perdu(n'a plus de dragons ou a perdu un portail)
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
		return !(nbDragon >= 1 && nbPortail == 2);

	}

	public void resetJouable() {
		for (Bushi b : this.bushiJoueur) {
			if (!(b instanceof Portail))
				b.jouable = 0;
		}
	}

}
