import java.util.ArrayList;
import java.util.Scanner;

public class Bushi {

      protected int abs;
      protected int ord;
      protected int etat;    // 0=vide, -1= case bloquée, -2=case portail,
                             //
      protected int jouable; // o=jouable 1=sauté un allié -1=sauté un ennemi
                             // -2=non jouable

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

      /*
       * public int getTaille() { return taille; }
       */

      /*
       * public void setTaille(int taille) { this.taille = taille; }
       */

      public int getAbs() {

            return abs;
      }

      public void setAbs(int abs) {

            this.abs = abs;
      }

      public int getOrd() {

            return ord;
      }

      public void setOrd(int ord) {

            this.ord = ord;
      }

      public int getEtat() {

            return etat;
      }

      public boolean isJouable() {

            if (this.etat == 0) {
                  return true;
            }
            else {
                  return false;
            }
      }

      public boolean isOccupee() {

            if (this.etat == -1) {
                  return true;
            }
            else {
                  return false;
            }
      }

      public boolean isBloque() {

            if (this.etat == -2) {
                  return true;
            }
            else {
                  return false;
            }
      }

      public boolean isPortail() {

            if (this instanceof Portail) {
                  return true;
            }
            else {
                  return false;
            }
      }

      /**
       * Détermine si le bushi a sauté
       * 
       * @param destination
       * @param p
       * @return le type de saut (allié,ennemi,rien)(0,1,2)
       */
      public int aSaute(Bushi destination, Plateau p) {

            if ((destination.abs - this.abs <= 1 && destination.abs + this.abs >= -1)
                        && (destination.ord - this.ord <= 1 && destination.ord + this.ord >= -1)) {
                  return 0; // verifier si la case est contigue ==> rien sauté
            }

            else {
                  int absInter = (this.abs + destination.abs) / 2;
                  int ordInter = (this.ord + destination.ord) / 2;

                  // Bushi aCherche = new Bushi(absInter, ordInter, 0,
                  // 0);renvoie toujours False car pas de comparaison par
                  // référence

                  if (p.joueurs[p.joueurCourant].bushiJoueur.contains(p.plateau[absInter][ordInter])) {
                        return 1; // a saute un allié
                  }
                  else {
                        return 2; // a saute un enemi
                  }
            }

      }

      // public ArrayList

      /**
       * Regarde si la destination est accessible
       * 
       * @param destination
       *              case d'arrivée
       * @param p
       *              le plateau de jeu
       * @return boolean true si la case est atteignable
       */
      public boolean reachable(Bushi destination, Plateau p) {

            boolean rep = false;
            // On teste si la case de destination est contigue à la case de
            // depart
            if ((destination.abs - this.abs <= 1 && destination.abs + this.abs >= -1)
                        && (destination.ord - this.ord <= 1 && destination.ord + this.ord >= -1)) {
                  // Dans ce cas il suffit de regardée si la case est jouable
                  rep = destination.etat == 0;
            }
            else {
                  /*
                   * Sinon il faut vérifier que la case intermediaire est vide
                   * ou bien contient un bushi que l'on peut sauter
                   */

                  // Calcul des coordonnees de la case intermediaire
                  int absInter = (this.abs + destination.abs) / 2;
                  int ordInter = (this.ord + destination.ord) / 2;

                  rep = (p.plateau[absInter][ordInter].etat >= 0 && p.plateau[absInter][ordInter].etat <= this.etat);
                  /*
                   * etat==0 ::::> la case est vide etat<=1 ::::> la case est
                   * occupee par un bushi plus petit ou de taille équivalente
                   */
            }

            return rep;

      }

      /**
       * 
       * @param destination
       *              case d'arrivee
       * @param p
       *              plateau de jeu Effectue le déplacement
       */

      public void effectuerDeplacement(Bushi destination, Plateau p) {

            int prevAbs = this.abs;
            int prevOrd = this.ord;

            // I) on met à jour les coord du Bushi deplace et la case d'arrivee
            this.abs = destination.abs;
            this.ord = destination.ord;
            p.plateau[this.abs][this.ord] = this;

            // II)On vide la case de depart
            p.plateau[prevAbs][prevOrd] = new Bushi(prevAbs, prevOrd, 0, -2);

            // III)On vérifie si on a saute
            switch (this.jouable) {
                  case 0:
                        break;
                  case 1:
            }

      }

      /**
       * Demande au joueur quel case il souhaite atteindre
       * 
       * @see Deplacable#choisirDeplacement(Plateau)
       * @param p
       *              le plateau de jeu
       * @return Bushi la case de destination choisie par le joueur
       */

      public Bushi choisirDeplacement(Plateau p) {

            Scanner sc = new Scanner(System.in);
            int i;
            int choix = 0;
            ArrayList<Bushi> deplacementsPossibles = this.listerDeplacement(p);

            for (i = 0; i < deplacementsPossibles.size(); i++) {
                  System.out.println((i + 1) + ": [" + deplacementsPossibles.get(i).getAbs() + ","
                              + deplacementsPossibles.get(i).getOrd() + "]\n");
            }
            try {
                  choix = sc.nextInt();
                  sc.close();

            }
            catch (Exception e) {
                  System.out.println(e);
                  // !!!!!!!!!!!EXPERIMENTAL!!!!!!!!!!!!!!!!
                  this.choisirDeplacement(p);
            }

            return this.listerDeplacement(p).get(choix - 1);

      }

      // CRADE
      public ArrayList<Bushi> listerDeplacement(Plateau p) {

            return new ArrayList<Bushi>();
      }

      @Override
      public String toString() {

            String s = "";
            switch (this.etat) {
                  case 0:
                        s = " ";
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

}
