public class Dragon extends Bushi implements Deplacable {

      public Dragon(int abs, int ord) {
            super(abs, ord, 3, 0);
      }

      @Override
      public String toString() {

            return "d";

      }
}

/*
 * public Dragon(int abs, int ord, int etat) { super(abs, ord, etat);
 * this.taille = 3; }
 * 
 * /* public int reachable(Bushi b,Plateau p){ int temp_abs=b.getAbs(); int
 * temp_ord=b.getOrd();
 * 
 * if(p.plateau[temp_abs][temp_ord].isJouable()){ return 0; }
 * if(p.plateau[temp_abs][temp_ord].isOccupee()){ return -1; }
 * if(p.plateau[temp_abs][temp_ord].isPortail()){ return -3; } else{ return -2;
 * }
 * 
 * }
 * 
 * public ArrayList<Bushi> Deplacement(Plateau p){ int i; int j;
 * ArrayList<Bushi> possible = new ArrayList<Bushi>(); for (i=this.getAbs()-2;
 * i<=this.getAbs()+2; i++){
 * 
 * for(j=this.getOrd()-2; j<=this.getOrd()+2; j++){
 * 
 * if(this.reachable(p.plateau[i][j], p) ==0){ possible.add(p.plateau[i][j]); }
 * } } return possible; }
 * 
 * public ArrayList<Bushi> CaseIntermediaireCheck(Plateau p){ int i; int j;
 * 
 * ArrayList<Bushi> inter = new ArrayList<Bushi>();
 * 
 * for (i=this.getAbs()-1; i<=this.getAbs()+1; i++){
 * 
 * for(j=this.getOrd()-1; j<=this.getOrd()+1; j++){
 * if(p.plateau[i][j].isOccupee()){ inter.add(p.plateau[i][j]); } } } return
 * inter; } //public Bushi demanderDéplacement(Plateau p);
 * 
 * /*public Bushi verifieObstacle(Plateau p){ this.demanderDéplacement(p); int
 * abs_deplacement =this.demanderDéplacement(p).getAbs(); int
 * ord_deplacement=this.demanderDéplacement(p).getOrd();
 * 
 * int abs_inter = (this.abs + abs_deplacement)/2; int ord_inter = (this.ord +
 * ord_deplacement)/2;
 * 
 * if (p.plateau[abs_inter][ord_inter].isOccupee()){ return
 * p.plateau[abs_inter][ord_inter]; } else { return new Dragon(-1,-1,50); }
 * 
 * }
 * 
 * }
 */
