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
     *            le plateau de jeu
     * @return ArrayList<Bushi> liste des déplacement possible du bushi passé.
     * 
     * 
     */

    @Override
    public ArrayList<Bushi> listerDeplacement(Plateau p) {

        int i;
        int j;
        ArrayList<Bushi> possible = new ArrayList<Bushi>();

        // i & j représente la valeur du décalage par rapport aux coordonnées
        // courantes
        for (i = 1; i >= -1; i--) {

            for (j = 1; j >= -1; j--) {
                // System.out.println("x= " + (this.abs + i) + " y =" +
                // (this.ord + j));
                if (this.reachable(this.abs + i, this.ord + j, p)) {
                    possible.add(p.plateau[this.ord + j][this.abs + i]);
                    // On regarde un coup plus loin
                    if (this.reachable(this.abs + 2 * i, this.ord + 2 * j, p)) {
                        possible.add(p.plateau[this.ord + 2 * j][this.abs + 2 * i]);
                    }
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
