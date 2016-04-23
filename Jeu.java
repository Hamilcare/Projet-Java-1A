
public class Jeu {

	
	public boolean existDragon(Joueur j){
		int i;
		
		for (i=0; i<j.bushiJoueur.size();i++){
				if (j.bushiJoueur.get(i) instanceof Dragon){
					return true;
				}	
				else {
					return false;
				}
			}
		return true;
	}

	public boolean portailPris(Joueur j, Plateau p){
		int i;
		int temp_abs;
		int temp_ord;
		for(i=0;i<j.bushiJoueur.size();i++){ //je parcours la liste des bushis du joueur j
			if(j.bushiJoueur.get(i) instanceof Portail){ //si un des bushis est un portail
				temp_abs=j.bushiJoueur.get(i).getAbs();  //je stocke son abscisse dans temp_abs
				temp_ord= j.bushiJoueur.get(i).getOrd(); // je stocke son ordonnée dans temp_ord
				if(p.plateau[temp_abs][temp_ord].etat == -1){// si la case correspondant à ces coordonnées est occupé (portail ==> occupe = false)
					return true;						  // le portail est pris
				}
				else {
					return false;
				}
			}
		
		}
		return false;
	}




}
