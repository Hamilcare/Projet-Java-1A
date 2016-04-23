import java.util.ArrayList;

public class Lion extends Bushi implements Deplacable {

      public Lion(int abs, int ord, int etat, int jouable) {
            super(abs, ord, etat, jouable);

      }

      public Lion(int abs, int ord) {
            super(abs, ord, 2, 0);
      }

      public ArrayList<Bushi> Deplacement(Plateau p) {

            int i;
            int j;
            ArrayList<Bushi> possible = new ArrayList<Bushi>();
            for (i = this.getAbs() - 1; i <= this.getAbs() + 1; i++) {

                  for (j = this.getOrd() - 1; j <= this.getOrd() + 1; j++) {

                        if (this.reachable(p.plateau[i][j], p)) {
                              possible.add(p.plateau[i][j]);
                        }
                  }
            }
            return possible;
      }

      @Override
      public String toString() {

            return "l";

      }

}
