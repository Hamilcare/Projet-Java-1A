import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Jeu {

	/**
	 * Permet au joueur de choisir la configuration des Bushis dans laquelle il
	 * souhaite jouer
	 * 
	 * @param sc
	 * @return Le nom du fichier à charger
	 */
	public static String choisirPartie(Scanner sc) {

		int choix = 0;
		String tab[] = new String[] { "standard", "demoSaut", "demoShingshang" };

		System.out.println("Avec quelle configuration souhaitez vous débuter ?\n");
		System.out.println("1 : Standard ");
		System.out.println("2 : Demonstration saut");
		System.out.println("3 : Demonstration ShingShang");

		try {
			choix = sc.nextInt();

			return tab[choix - 1];

		}

		catch (InputMismatchException e) {
			System.out.println("Mauvaise saisie, vous devez rentrer un nombre figurant dans la liste\n");
			sc.next();
			return choisirPartie(sc);
		}

		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Mauvaise saisie, vous devez rentrer un nombre figurant dans la liste\n");
			return choisirPartie(sc);
		}

		catch (Exception e) {
			System.out.println("Erreur survenue lors du choix de la configuration de la partie\n");
			System.out.println(e);
			return choisirPartie(sc);
		}

	}

	/**
	 * Detecte si une partie précédente a été sauvegardée
	 * 
	 * @param sc
	 * @return true si le joueur souahite charger la sauvegarde
	 */
	public static boolean continuerPartie(Scanner sc) {
		int choix = 0;
		File f = new File("partie.save");
		if (f.exists()) {// Si la sauvegarde existe
			System.out.println("Voulez vous continuer la partie précédente ?\n 1.oui\n 2.non\n");
			try {
				choix = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Vous devez entrer 1 ou 2\n");
				sc.next();
				return continuerPartie(sc);
			}

			catch (Exception e) {
				System.out.println("Erreur inconue survenue lors du chargement de la partie précédente\n");
				sc.next();
				return continuerPartie(sc);
			}

			if (choix == 1) {
				return true;
			} else if (choix == 2) {
				return false;
			}

			else {
				System.out.println("Vous devez entrer 1 ou 2\n");
				return continuerPartie(sc);

			}

		}

		return false;

	}

	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		String fileName = "";
		Plateau p1 = new Plateau();

		if (continuerPartie(sc)) {
			try {
				p1 = Plateau.charge();
			} catch (IOException e) {
				System.out.println("Impossible de charger la partie");
			}

		} else {
			Affichage.clearConsole();
			fileName = choisirPartie(sc);

			p1.nouvellePartie(fileName);
		}

		p1.YOLOLOLDeplacement(sc);

		try {
			p1.sauvegarde();
		} catch (IOException e) {
			System.out.println(e);
		}

		sc.close();

	}

}
