
public class Portail extends Bushi {

	private static final long serialVersionUID = -5918860274769389792L;

	/**
	 * Le constructeur du portail permet d'instancier un portail en précisant
	 * abscisse et ordonnee, son etat (sa taille) et jouable (pour savoir si le
	 * lion est jouable ou non)
	 * 
	 * @param abs
	 *            l'abscisse que l'on souhaite attribue au portail
	 * @param ord
	 *            l'ordonnee que l'on souhaite attribue au portail
	 * @param etat
	 *            etat (taille) que l'on souhaite attribuer au portail
	 * @param jouable
	 *            precise si le portail est jouable ou non (0=jouable, 1=a saute
	 *            un allie -1=a saute un ennemi -2=non jouable)
	 */

	public Portail(int abs, int ord, int etat, int jouable) {
		super(abs, ord, etat, jouable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Le constructeur du portail permet d'instancier un portail en précisant
	 * abscisse et ordonnee
	 * 
	 * @param abs
	 *            l'abscisse que l'on souhaite attribue au portail
	 * @param ord
	 *            l'ordonnee que l'on souhaite attribue au portail
	 */
	public Portail(int abs, int ord) {
		super(abs, ord, -2, -2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * toString() renvoie une chaine de caracteres précisant la classe (Portail)
	 * et ses coordonnees
	 * 
	 * @return "P" renvoie l'initial du portail
	 */
	@Override
	public String toString() {

		return "P";

	}

}
