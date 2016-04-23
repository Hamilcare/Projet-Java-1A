import java.util.ArrayList;

public class Joueur {

      String           nom;
      // Bushi[] tab = new Bushi[12];
      ArrayList<Bushi> bushiJoueur = new ArrayList<Bushi>();

      public Joueur() {
            nom = "";
            bushiJoueur = new ArrayList<Bushi>();

      }

      public Joueur(String nom) {
            super();
            this.nom = nom;
      }

      public String getNom() {

            return nom;
      }

      public void setNom(String nom) {

            this.nom = nom;
      }

      // Ici méthode pour load un fichier texte avec tous les bushis du joueur
      // afin de les placer dans le tableau
      public void ajouterBushi(Bushi b) {

            bushiJoueur.add(b);
      }

      public void parcourirBushi() {

            Bushi[] tab = new Bushi[12];
            bushiJoueur.toArray(tab);

            for (Bushi b : bushiJoueur) {
                  System.out.println(b);
            }
      }

      @Override
      public String toString() {

            return "Joueur [nom=" + nom + "]";
      }

}
