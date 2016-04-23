import java.util.ArrayList;

public class Singe extends Bushi implements Deplacable {

      public Singe(int abs, int ord, int etat, int jouable) {
            super(abs, ord, etat, jouable);

      }

      public Singe(int abs, int ord) {
            super(abs, ord, 1, 0);
      }

      /**
       * @see Deplacable#listerDeplacement(Plateau)
       * @param Plateau
       *              le plateau de jeu
       * @return ArrayList<Bushi> liste des déplacement possible du bushi passé.
       * 
       * 
       */

      @Override
      public ArrayList<Bushi> listerDeplacement(Plateau p) {

            int i;
            int j;
            ArrayList<Bushi> possible = new ArrayList<Bushi>();
            for (i = this.getAbs() - 2; i <= this.getAbs() + 2; i++) {

                  for (j = this.getOrd() - 2; j <= this.getOrd() + 2; j++) {

                        if (this.reachable(p.plateau[i][j], p)) {
                              possible.add(p.plateau[i][j]);
                        }
                  }
            }
            return possible;
      }

      @Override
      public String toString() {

            return "s";

      }

}
