package Interface;
import Model.Livre;

import java.util.List;


public interface InterfaceLivre {
    Livre ajouterlivre(Livre livre);
    Livre modifierlivre(Livre livre);
    Livre supprimerlivre(Livre livre);
    List<Livre> afficher();
    List<Livre> livreempruntes();
    List<Livre> livreperdus();
    List<Livre> livredisponible();

    Livre rechercherLivreParIsbn(String isbn);

    Livre rechercherLivreParTitre(String titre);
}
