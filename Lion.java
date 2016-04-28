public class Lion extends Bushi implements Deplacable {

    public Lion(int abs, int ord, int etat, int jouable) {
        super(abs, ord, etat, jouable);

    }

    public Lion(int abs, int ord) {
        super(abs, ord, 2, 0);
    }
    
    public ArrayList<Bushi> listerDeplacement(Plateau p) {

		int i;
		int j;
		ArrayList<Bushi> possible = new ArrayList<Bushi>();

		// i & j repr�sente la valeur du d�calage par rapport aux coordonn�es
		// courantes
		for (i = -1; i <= 1; i++) {

			for (j = 1; j >= -1; j--) {
				// System.out.println("x= " + (this.abs + i) + " y =" +
				// (this.ord + j));
				if (this.reachable(this.abs + i, this.ord + j, p)) {
					possible.add(p.plateau[this.ord + j][this.abs + i]);
				}

			}

		}
		return possible;

	}
	
	@Override
    public String toString() {

    	return "Lion [" + (char) (this.abs + 'a') + "," + this.ord + "]";

    }

    /*
     * public ArrayList<Bushi> Deplacement(Plateau p) {
     * 
     * int i; int j; ArrayList<Bushi> possible = new ArrayList<Bushi>(); for (i
     * = this.getAbs() - 1; i <= this.getAbs() + 1; i++) {
     * 
     * for (j = this.getOrd() - 1; j <= this.getOrd() + 1; j++) {
     * 
     * if((i >= 0 && i < 10) && (j>=0 && j<10)){ if
     * (this.reachable(p.plateau[i][j], p)) { possible.add(p.plateau[i][j]); } }
     * } } return possible; }
     */

    @Override
    public String toString() {

        return "L";

    }

}
