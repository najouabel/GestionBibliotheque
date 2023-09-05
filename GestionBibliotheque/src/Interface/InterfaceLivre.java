package Interface;
import Model.Livre;

import java.util.List;


public interface InterfaceLivre {
    Livre ajouterlivre(Livre livre);
    Livre modifierlivre(Livre livre);
    Livre supprimerlivre(Livre livre);
    Livre rechercherlivre(String isbn);
    List<Livre> afficher();
    List<Livre> livreempruntes();
    List<Livre> livreperdus();
    List<Livre> livredisponible();


}
