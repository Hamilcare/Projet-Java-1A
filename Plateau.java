import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Plateau {

      Bushi[][] plateau = new Bushi[10][10];
      Joueur[]  joueurs = new Joueur[2];
      int       joueurCourant;              // permet de connaitre le joueur qui
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

      public void bloquerCases() { // bloque les cases sur les côtés du plateau

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
       * Initialise toutes les cases du plateau à un Bushi(0,0,0,0)
       */
      public void initPlateau() {

            for (int i = 0; i < 10; i++) {
                  for (int j = 0; j < 10; j++) {
                        this.plateau[i][j] = new Bushi(0, 0, 0, 0);
                  }
            }

      }

      /**
       * Initialise le plateau et place les bushis en suivant les instruction du
       * fichier fileName
       * 
       * @param fileName
       *              le nom du fichier à charger
       */

      public void nouvellePartie(String fileName) {

            // On bloque les cases non accessibles
            this.initPlateau();
            this.bloquerCases();

            try {

                  Scanner fichier = new Scanner(new File(fileName)).useDelimiter("[\n]");

                  while (fichier.hasNext()) {// tant que le fichier n'est pas
                                             // termine

                        String ligne = fichier.next();// contient
                                                      // la
                                                      // ligne
                        // courante de
                        // fichier fileName
                        /*
                         * Chaque ligne du fichier décrit un bushi comme suit :
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

                        // On génère la liste des Bushis de chaque joueur
                        switch (intArray[0]) {
                              case 1:
                                    joueurs[intArray[3] - 1].bushiJoueur.add(new Singe(intArray[2], intArray[1]));
                                    break;
                              case 2:
                                    joueurs[intArray[3] - 1].bushiJoueur.add(new Lion(intArray[2], intArray[1]));
                                    break;
                              case 3:
                                    joueurs[intArray[3] - 1].bushiJoueur.add(new Dragon(intArray[2], intArray[1]));
                                    break;
                              case -2:
                                    joueurs[intArray[3] - 1].bushiJoueur.add(new Portail(intArray[2], intArray[1]));

                              default:
                                    System.out.println("merde");
                                    break;
                        }

                  }

                  fichier.close();

            }

            catch (FileNotFoundException e) {
                  System.out.println(e);
            }

            /* On place les bushis de chaque joueur sur le plateau */
            for (Bushi b : joueurs[0].bushiJoueur) {
                  plateau[b.abs][b.ord] = b;
                  // System.out.println("coucou");
            }
            for (Bushi b : joueurs[1].bushiJoueur) {
                  plateau[b.abs][b.ord] = b;
            }

      }

      public void YOLOLOLDeplacement() {

            ArrayList<Bushi> jouable = new ArrayList<Bushi>();
            Scanner sc = new Scanner(System.in);
            int choix = 0;
            // Présente la liste des bushis jouable du joueur

            for (Bushi b : joueurs[joueurCourant].bushiJoueur) {
                  if (b.jouable == 0) {
                        jouable.add(b);
                  }
            }
            for (Bushi b : jouable) {
                  choix = jouable.indexOf(b) + 1;
                  System.out.println(choix + ":" + b);
            }
            // Le joueur choisi le bushi qu'il souhaite déplacé
            choix = sc.nextInt() - 1;

            // Le joueur choisi la destination du Bushi qu'il souhaite déplacé
            jouable.get(choix).choisirDeplacement(this);

            // Vérifié si le bushi déplacé saute
            jouable.get(choix).aSaute(jouable.get(choix).choisirDeplacement(this), this);

            // Effectuer le déplacement
            jouable.get(choix).effectuerDeplacement(jouable.get(choix).choisirDeplacement(this), this);
            sc.close();
      }

      public static void main(String args[]) {

            Plateau p1 = new Plateau();
            p1.nouvellePartie("standard.txt");
            System.out.println(p1);
            p1.YOLOLOLDeplacement();
      }

}
