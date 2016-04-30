import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Plateau implements Serializable {

	private static final long serialVersionUID = 2913362129474053797L;

	Bushi[][] plateau = new Bushi[10][10];
	Joueur[] joueurs = new Joueur[2];
	int joueurCourant; // permet de connaitre le joueur qui
	// est entrain de jouer.(0,1);

	public Plateau() {
		// super();
		this.joueurs = new Joueur[2];
		this.plateau = new Bushi[10][10];
		joueurCourant = 0;
		this.initPlateau();
		joueurs[0] = new Joueur();
		joueurs[1] = new Joueur();
	}

	public void bloquerCases() { // bloque les cases sur les c�t�s du plateau

		int i;

		for (i = 0; i < 4; i++) {
			this.plateau[i][0].etat = -1;
			this.plateau[i][9].etat = -1;
		}

		for (i = 6; i < 10; i++) {
			this.plateau[i][0].etat = -1;
			this.plateau[i][9].etat = -1;
		}

	}

	/**
	 * Affiche en vert les déplacements possibles
	 * 
	 * @param deplacements
	 *
	 *            public void afficheDeplacement(ArrayList<Bushi> deplacements)
	 *            {
	 * 
	 *            for (int i = 0; i < 10; i++) { for (int j = 0; j < 10; j++) {
	 *            if (deplacements.contains(this.plateau[i][j])) {
	 *            System.out.print("1|");
	 * 
	 *            } else { System.out.print(this.plateau[i][j] + "|"); }
	 * 
	 *            } System.out.print("\n"); }
	 * 
	 *            }
	 * 
	 *            /** Affichage utilisé pour le debug, on ne peut stocker des
	 *            informations concernant la couleur dans une String
	 */
	public String toString() {

		String s = "";

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				switch (this.plateau[i][j].etat) {
				case 0:
					s += " ";
					break;
				case 1:
					s += "s";
					break;
				case 2:
					s += "l";
					break;
				case 3:
					s += "d";
					break;
				case -1:
					s += "X";
					break;
				case -2:
					s += "p";
					break;

				}
				s += "|";
				// s += this.plateau[i][j] + "|";

			}
			s += "\n";
		}

		return s;

	}

	/**
	 * Initialise toutes les cases du plateau � un Bushi(0,0,0,0)
	 */
	public void initPlateau() {

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				this.plateau[j][i] = new Bushi(i, j, 0, 0);
			}
		}

	}

	/**
	 * Initialise le plateau et place les bushis en suivant les instruction du
	 * fichier fileName
	 * 
	 * @param fileName
	 *            le nom du fichier � charger
	 */

	public void nouvellePartie(String fileName, Scanner sc) {

		// On bloque les cases non accessibles
		this.initPlateau();
		this.bloquerCases();
		// Scanner fichier = new Scanner("Init Scanner");

		try {

			sc = new Scanner(new File(fileName)).useDelimiter("[\n]");

			while (sc.hasNext()) {// tant que le fichier n'est pas
									// termine

				String ligne = sc.next();// contient
											// la
											// ligne
				// courante de
				// fichier fileName
				/*
				 * Chaque ligne du fichier d�crit un bushi comme suit :
				 * type,abs,ord,joueur,jouable
				 * 
				 */

				String[] strArray = ligne.split(" ");
				// System.out.println("str length : " +
				// strArray.length);
				int[] intArray = new int[strArray.length - 1];

				for (int i = 0; i < strArray.length - 1; i++) {
					intArray[i] = Integer.parseInt(strArray[i]);
					// System.out.println(intArray[i]);
				}

				// On g�n�re la liste des Bushis de chaque joueur
				switch (intArray[0]) {
				case 1:
					joueurs[intArray[3] - 1].ajouterBushi(new Singe(intArray[1], intArray[2]));
					break;
				case 2:
					joueurs[intArray[3] - 1].ajouterBushi(new Lion(intArray[1], intArray[2]));
					break;
				case 3:
					joueurs[intArray[3] - 1].ajouterBushi(new Dragon(intArray[1], intArray[2]));
					break;
				case -2:
					joueurs[intArray[3] - 1].ajouterBushi(new Portail(intArray[1], intArray[2]));

				default:
					break;
				}

			}

		}

		catch (FileNotFoundException e) {
			System.out.println(e);
		}

		/* On place les bushis de chaque joueur sur le plateau */
		for (Bushi b : joueurs[0].getBushiJoueur()) {
			plateau[b.ord][b.abs] = b;
			// System.out.println("coucou");
		}
		for (Bushi b : joueurs[1].getBushiJoueur()) {
			plateau[b.ord][b.abs] = b;
		}

		// fichier.close();

	}

	public ArrayList<Bushi> listerBushiJouable() {
		ArrayList<Bushi> jouable = new ArrayList<Bushi>();
		int choix = 0;

		for (Bushi b : joueurs[joueurCourant].getBushiJoueur()) {
			if (b.jouable == 0) {
				jouable.add(b);
			}
		}
		int i = 0;
		for (Bushi b : jouable) {
			i++;// Utilisé pour l'affichage
			choix = jouable.indexOf(b) + 1;
			System.out.print(choix + ":" + b + "	");
			if (i % 4 == 0) {
				System.out.print("\n");
			}
		}
		System.out.println("\n-1 : Finir votre tour\n");
		System.out.println("\n");
		return jouable;
	}

	public int YOLOLOLDeplacement(Scanner sc) {

		Affichage.clearConsole();
		Affichage.afficheJouer(this);

		boolean saute = false;
		boolean peutJouer;

		do {

			peutJouer = true;
			Affichage.affichePlateau(this);

			/* Contient le bushi que l'on souhaite deplacer */
			Bushi choixBushiDeplace = null;

			/* Contient la destination choisie pour le bushi deplace */
			Bushi choixDestination = null;

			/*
			 * Contient la liste de tous les bushis que le joueur peut déplacer
			 */
			ArrayList<Bushi> listeBushiJouable = this.listerBushiJouable();

			int choix = 0;

			// Presente la liste des bushis jouable du joueur

			if (listeBushiJouable.size() != 0) {
				// Le joueur choisi le bushi qu'il souhaite déplacer
				try {
					choix = sc.nextInt() - 1;

					if (choix == -2) {
						break;
					}

					choixBushiDeplace = listeBushiJouable.get(choix);
				}

				catch (InputMismatchException e) {
					System.out.println("Mauvaise saisie, vous devez rentrer un nombre figurant dans la liste");
					sc.next();
					return this.YOLOLOLDeplacement(sc);
				}

				catch (IndexOutOfBoundsException e) {
					System.out.println("Vous devez rentrer un nombre figurant dans la liste");
					// sc.next();
					return this.YOLOLOLDeplacement(sc);
				}

				catch (Exception e) {
					System.out.println("Erreur inconcue rencontree lors du choix du Bushi a deplacer");
					return this.YOLOLOLDeplacement(sc);

				}

				/*
				 * Le joueur choisi la destination du Bushi qu'il souhaite
				 * déplacer
				 */
				Affichage.clearConsole();
				try {
					choixDestination = choixBushiDeplace.choisirDeplacement(this, sc);
				}

				catch (IndexOutOfBoundsException e) {
					System.out.println("Vous avez decide de changer de Bushi");
					return this.YOLOLOLDeplacement(sc);
				}

				catch (Exception e) {
					System.out.println("Erreur inconcue rencontree lors du choix de la destination");
					return this.YOLOLOLDeplacement(sc);

				}

				// Verifie si le bushi deplace saute
				saute = choixBushiDeplace.aSaute(choixDestination, this);
				System.out.println("asaute= " + saute);

				// Effectuer le deplacement
				choixBushiDeplace.effectuerDeplacement(choixDestination, this);

				Affichage.affichePlateau(this);
			}

			else {

				peutJouer = false;
				System.out.println("Vous ne pouvez plus déplacer de Bushi, c'est la fin de votre tour\n");

			}

		} while (saute && !(joueurs[this.autreJoueur()].aPerdu()) && peutJouer);

		/*
		 * Une fois que le joueur a finis son tour, on reset l'attribut jouable
		 * de tout ses Bushi à 0.
		 */
		this.joueurs[joueurCourant].resetJouable();
		this.joueurCourant = this.autreJoueur();

		return 0;

	}

	public int autreJoueur() {
		return (joueurCourant + 1) % 2;
	}

	public void sauvegarde() throws IOException {
		ObjectOutputStream oos = null;
		try {
			File fichier = new File("partie.save");
			oos = new ObjectOutputStream(new FileOutputStream(fichier));
			oos.writeObject(this);

			/*
			 * this.joueurs = new Joueur[2]; this.plateau = new Bushi[10][10];
			 * joueurCourant = 0; this.initPlateau(); joueurs[0] = new Joueur();
			 * joueurs[1] = new Joueur();
			 */

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		}

		finally {
			oos.close();
		}
	}

	public static Plateau charge() throws IOException {
		Plateau p = new Plateau();
		ObjectInputStream ois = null;

		try {
			ois = new ObjectInputStream(new FileInputStream("partie.save"));
			p = (Plateau) ois.readObject();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			ois.close();
		}
		return p;
	}

}
