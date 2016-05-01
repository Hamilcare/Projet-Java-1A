import java.util.ArrayList;

/**
 * 
 * Gere l'affichage du plateau et des ASCII arts
 *
 */

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

	/**
	 * Permet de mettre une chaine de caracteres en rouge
	 * 
	 * @return "\u001B[31m" permet de prendre "l'identifiant de la couleur"
	 *         rouge
	 */
	public static String getRouge() {
		return "\u001B[31m";
	}

	/**
	 * Permet de mettre une chaine de caracteres en bleu
	 * 
	 * @return "\u001B[34m" permet de prendre "l'identifiant de la couleur" bleu
	 */
	public static String getBleu() {
		return "\u001B[34m";
	}

	/**
	 * Permet de mettre une chaine de caracteres en vert
	 * 
	 * @return "\u001B[32m" permet de prendre "l'identifiant de la couleur" vert
	 */
	public static String getVert() {
		return "\u001B[32m";
	}

	/**
	 * Permet de mettre une chaine de caracteres en blanc
	 * 
	 * @return "\u001B[37m" permet de prendre "l'identifiant de la couleur"
	 *         balnc
	 */
	public static String getBlanc() {
		return "\u001B[37m";

	}

	/**
	 * Permet de mettre une chaine de caracteres en jaune
	 * 
	 * @return "\u001B[31m" permet de prendre "l'identifiant de la couleur"
	 *         jaune
	 */
	public static String getJaune() {
		return "\u001B[33m";
	}

	/**
	 * Affiche le plateau en couleur
	 * 
	 * @param p
	 *            le plateau de jeu
	 */
	public static void affichePlateau(Plateau p) {
		Affichage.clearConsole();
		Affichage.afficheJouer(p);
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
	 * Affiche le plateau en couleur dans la situation d'un bushi selectionne
	 * avec ses deplacements possibles en couleur
	 * 
	 * @param p
	 *            le plateau de jeu
	 * @param bushiBouge
	 *            le Bushi que l'on souhaite déplacer
	 * @param deplacement
	 *            la liste des déplacement possibles de bushiBouge
	 */

	public static void affichePlateau(Plateau p, Bushi bushiBouge, ArrayList<Bushi> deplacements) {

		Affichage.clearConsole();
		Affichage.afficheJouer(p);

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

	public static void nomJeu() {

		System.out.println(" __ _     _             __ _                         _____  ___   ___   ___  ");
		System.out.println("/ _\\ |__ (_)_ __   __ _/ _\\ |__   __ _ _ __   __ _  |___ / / _ \\ / _ \\ / _ \\ ");
		System.out.println("\\ \\| '_ \\| | '_ \\ / _` \\ \\| '_ \\ / _` | '_ \\ / _` |   |_ \\| | | | | | | | | |");
		System.out.println("_\\ \\ | | | | | | | (_| |\\ \\ | | | (_| | | | | (_| |  ___) | |_| | |_| | |_| |");
		System.out.println("\\__/_| |_|_|_| |_|\\__, \\__/_| |_|\\__,_|_| |_|\\__, | |____/ \\___/ \\___/ \\___/ ");
		System.out.println("                  |___/                      |___/                           ");
	}

	public static void afficheJouer(Plateau p) {
		String color = "";
		if (p.joueurCourant == 0) {
			color = getRouge();

			System.out.println(color + "	       _                               __ ");
			System.out.println(color + "	      | |                             /_ |");
			System.out.println(color + "	      | | ___  _   _  ___ _   _ _ __   | |");
			System.out.println(color + "	  _   | |/ _ \\| | | |/ _ \\ | | | '__|  | |");
			System.out.println(color + "	 | |__| | (_) | |_| |  __/ |_| | |     | |");
			System.out.println(color + "	  \\____/ \\___/ \\__,_|\\___|\\__,_|_|     |_|");
		}

		else {
			color = getBleu();
			System.out.println(color + "	       _                               ___  ");
			System.out.println(color + "	      | |                             |__ \\ ");
			System.out.println(color + "	      | | ___  _   _  ___ _   _ _ __     ) |");
			System.out.println(color + "	  _   | |/ _ \\| | | |/ _ \\ | | | '__|   / / ");
			System.out.println(color + "	 | |__| | (_) | |_| |  __/ |_| | |     / /_ ");
			System.out.println(color + "	  \\____/ \\___/ \\__,_|\\___|\\__,_|_|    |____|  ");
		}
		color = getBlanc();
		System.out.println(color + "\n");
	}

	/**
	 * Simule un clear system
	 */
	public static void clearConsole() {
		final String ANSI_CLS = "\u001b[2J";
		final String ANSI_HOME = "\u001b[H";
		System.out.print(ANSI_CLS + ANSI_HOME);
		System.out.flush();
	}

	public static void afficheVictoire(int i) {

		String color = "";

		if (i == 0) {
			color = getRouge();
			System.out.println(color + " _   _ _      _        _                 ");
			System.out.println(color + "| | | (_)    | |      (_)                ");
			System.out.println(color + "| | | |_  ___| |_ ___  _ _ __ ___        ");
			System.out.println(color + "| | | | |/ __| __/ _ \\| | '__/ _ \\       ");
			System.out.println(color + "\\ \\_/ / | (__| || (_) | | | |  __/       ");
			System.out.println(color + "\\___/|_|\\___|\\__\\___/|_|_|  \\___|       ");
			System.out.println(color + "                                         ");
			System.out.println(color + "                                         ");
			System.out.println(color + "   ______                                ");
			System.out.println(color + "   |  _  \\                               ");
			System.out.println(color + "   | | | |_   _                          ");
			System.out.println(color + "   | | | | | | |                         ");
			System.out.println(color + "   | |/ /| |_| |                         ");
			System.out.println(color + "   |___/  \\__,_|                         ");
			System.out.println(color + "                                         ");
			System.out.println(color + "                                         ");
			System.out.println(color + "   ___                               __  ");
			System.out.println(color + "  |_  |                             /  | ");
			System.out.println(color + "    | | ___  _   _  ___ _   _ _ __  `| | ");
			System.out.println(color + "    | |/ _ \\| | | |/ _ \\ | | | '__|  | | ");
			System.out.println(color + "/\\__/ / (_) | |_| |  __/ |_| | |    _| |_");
			System.out.println(color + "\\____/ \\___/ \\__,_|\\___|\\__,_|_|    \\___/");
			System.out.println(color + "                                         ");
			System.out.println(color + "                                         ");
			System.out.println(color + "   _____ _____   _    _______            ");
			System.out.println(color + "  |  __ \\  __ \\ | |  | | ___ \\           ");
			System.out.println(color + "  | |  \\/ |  \\/ | |  | | |_/ /           ");
			System.out.println(color + "  | | __| | __  | |/\\| |  __/            ");
			System.out.println(color + "  | |_\\ \\ |_\\ \\ \\  /\\  / |               ");
			System.out.println(color + "   \\____/\\____/  \\/  \\/\\_|               ");

		} else {
			color = getBleu();

			System.out.println(color + " _   _ _      _        _                   ");
			System.out.println(color + "| | | (_)    | |      (_)                  ");
			System.out.println(color + "| | | |_  ___| |_ ___  _ _ __ ___          ");
			System.out.println(color + "| | | | |/ __| __/ _ \\| | '__/ _ \\         ");
			System.out.println(color + "\\ \\_/ / | (__| || (_) | | | |  __/         ");
			System.out.println(color + " \\___/|_|\\___|\\__\\___/|_|_|  \\___|         ");
			System.out.println(color + "                                           ");
			System.out.println(color + "                                           ");
			System.out.println(color + "   ______                                  ");
			System.out.println(color + "   |  _  \\                                 ");
			System.out.println(color + "   | | | |_   _                            ");
			System.out.println(color + "   | | | | | | |                           ");
			System.out.println(color + "   | |/ /| |_| |                           ");
			System.out.println(color + "   |___/  \\__,_|                           ");
			System.out.println(color + "                                           ");
			System.out.println(color + "                                           ");
			System.out.println(color + "   ___                               _____ ");
			System.out.println(color + "  |_  |                             / __  \\");
			System.out.println(color + "    | | ___  _   _  ___ _   _ _ __  `' / /'");
			System.out.println(color + "    | |/ _ \\| | | |/ _ \\ | | | '__|   / /  ");
			System.out.println(color + "/\\__/ / (_) | |_| |  __/ |_| | |    ./ /___");
			System.out.println(color + "\\____/ \\___/ \\__,_|\\___|\\__,_|_|    \\_____/");
			System.out.println(color + "                                           ");
			System.out.println(color + "                                           ");
			System.out.println(color + "   _____ _____   _    _______              ");
			System.out.println(color + "  |  __ \\  __ \\ | |  | | ___ \\             ");
			System.out.println(color + "  | |  \\/ |  \\/ | |  | | |_/ /             ");
			System.out.println(color + "  | | __| | __  | |/\\| |  __/              ");
			System.out.println(color + "  | |_\\ \\ |_\\ \\ \\  /\\  / |                 ");
			System.out.println(color + "   \\____/\\____/  \\/  \\/\\_|        		 ");

		}

		color = getBlanc();
		System.out.println(color + "\n");

	}

}
