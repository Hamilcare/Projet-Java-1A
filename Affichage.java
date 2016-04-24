import java.util.ArrayList;

public class Affichage {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static String getRouge() {
		return "\u001B[31m";
	}

	public static String getBleu() {
		return "\u001B[34m";
	}

	public static String getVert() {
		return "\u001B[32m";
	}

	public static String getBlanc() {
		return "\u001B[37m";

	}

	public static String getJaune() {
		return "\u001B[33m";
	}

	/**
	 * Affiche le plateau en couleur
	 * 
	 * @param p
	 */
	public static void affichePlateau(Plateau p) {

		int i, j, k;
		String color = "";
		int n = p.plateau.length;

		System.out.println("        ---------------------------------------------------      ");
		System.out.println("        | AA | BB | CC | DD | EE | FF | GG | HH | II | JJ |      ");
		System.out.println("        | AA | BB | CC | DD | EE | FF | GG | HH | II | JJ |      ");
		System.out.println("        ---------------------------------------------------      ");
		System.out.println("------       -----------------------------------------           ");
		for (i = 0; i < n; i++) {
			for (k = 0; k < 2; k++) {
				// On formate l'affichage afin de gérer l'alignement
				System.out.printf("| %02d |  ", i);
				for (j = 0; j < n; j++) {

					if (j == 0 && p.plateau[i][j].etat != -1)
						System.out.print("|");

					if (p.plateau[i][j].Appartient(p.joueurs[0])) {
						// System.out.println("LELELELLE");
						color = getRouge();

					}

					else if (p.plateau[i][j].Appartient(p.joueurs[1])) {
						color = getBleu();

					}

					else {
						color = getBlanc();
					}

					switch (p.plateau[i][j].etat) {
					case -2:
						System.out.print(color + " PP ");
						break;
					case -1:
						System.out.print(color + "     ");
						break;
					case 0:
						System.out.print(color + "    ");
						break;
					case 1:
						System.out.print(color + " SS ");
						break;
					case 2:
						System.out.print(color + " LL ");
						break;
					case 3:
						System.out.print(color + " DD ");
						break;
					default:
						break;
					}
					color = getBlanc();
					if (!(j == n - 1 && p.plateau[i][j].etat == -1))
						System.out.print(color + "|");
				}
				System.out.print("\n");
			}
			if (i >= 3 && i <= 5)
				System.out.println("------  ---------------------------------------------------");
			else
				System.out.println("------       -----------------------------------------      ");
		}

	}

	/**
	 * 
	 * @param p
	 * @param bushiBouge
	 *            le Bushi que l'on souhaite déplacer
	 * @param deplacement
	 *            la liste des déplacement possibles de bushiBouge
	 */

	public static void affichePlateau(Plateau p, Bushi bushiBouge, ArrayList<Bushi> deplacements) {

		System.out.println(bushiBouge);

		int i, j, k;
		String color = "";
		int n = p.plateau.length;

		boolean estDep = false;

		System.out.println("        ---------------------------------------------------      ");
		System.out.println("        | AA | BB | CC | DD | EE | FF | GG | HH | II | JJ |      ");
		System.out.println("        | AA | BB | CC | DD | EE | FF | GG | HH | II | JJ |      ");
		System.out.println("        ---------------------------------------------------      ");
		System.out.println("------       -----------------------------------------           ");
		for (i = 0; i < n; i++) {
			for (k = 0; k < 2; k++) {
				// On formate l'affichage afin de gérer l'alignement
				System.out.printf("| %02d |  ", i);
				for (j = 0; j < n; j++) {

					estDep = false;

					if (j == 0 && p.plateau[i][j].etat != -1)
						System.out.print("|");

					if (p.plateau[i][j].equals(bushiBouge)) {
						color = getVert();

					}

					else if (p.plateau[i][j].Appartient(p.joueurs[0])) {
						// System.out.println("LELELELLE");
						color = getRouge();

					}

					else if (p.plateau[i][j].Appartient(p.joueurs[1])) {
						color = getBleu();

					}

					else if (deplacements.contains(p.plateau[i][j])) {
						color = getJaune();
						estDep = true;
					}

					else {
						color = getBlanc();
					}

					switch (p.plateau[i][j].etat) {
					case -2:
						System.out.print(color + " PP ");
						break;
					case -1:
						System.out.print(color + "     ");
						break;
					case 0:
						if (estDep) {
							System.out.printf(color + " %02d ", deplacements.indexOf(p.plateau[i][j]) + 1);
						} else {
							System.out.print(color + "    ");
						}
						break;
					case 1:
						System.out.print(color + " SS ");
						break;
					case 2:
						System.out.print(color + " LL ");
						break;
					case 3:
						System.out.print(color + " DD ");
						break;
					default:
						break;
					}
					color = getBlanc();
					if (!(j == n - 1 && p.plateau[i][j].etat == -1))
						System.out.print(color + "|");
				}
				System.out.print("\n");
			}
			if (i >= 3 && i <= 5)
				System.out.println("------  ---------------------------------------------------");
			else
				System.out.println("------       -----------------------------------------      ");
		}

	}

}
