import java.util.Scanner;

public class Jeu {

	public static String choisirPartie(Scanner sc) {

		int choix = 0;

		System.out.println("Avec quelle configuration souhaitez vous débuter ?\n");
		System.out.println("1 : Standard ");
		System.out.println("2 : Demonstration saut");
		System.out.println("3 : Demonstration ShingShang");

		try {
			choix = sc.nextInt();

		}

		catch (Exception e) {
			System.out.println("Erreur de saisie");
			sc.nextLine();
			return choisirPartie(sc);
		}

		switch (choix) {
		case 1:
			return "standard";

		case 2:
			return "demoSaut";

		case 3:
			return "demoShingShang";

		default:
			break;

		}

		return "";

	}

	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		String fileName = "";
		fileName = choisirPartie(sc);

		Plateau p1 = new Plateau();
		p1.nouvellePartie(fileName);

		/*
		 * p1.YOLOLOLDeplacement(sc);
		 * 
		 * try { p1.sauvegarde(); } catch (IOException e) {
		 * System.out.println(e); }
		 * 
		 * try {
		 * 
		 * p1 = p1.charge();
		 * 
		 * } catch (IOException e) { System.out.println(e); }
		 */
		Affichage.affichePlateau(p1);

		while (p1.joueurs[p1.joueurCourant].aPerdu()) {
			p1.YOLOLOLDeplacement(sc);
			p1.joueurCourant = p1.autreJoueur();
		}
		sc.close();

	}

}
