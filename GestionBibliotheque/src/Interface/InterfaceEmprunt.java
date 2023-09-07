package Interface;

import Model.Emprunt;
import Model.Emprunteur;
import Model.Livre;

public interface InterfaceEmprunt {
    Emprunt enregistrerEmprunt(Livre livre, Emprunteur emprunteur);
    Emprunt retournerEmprunt(Livre livre, Emprunteur emprunteur);
}
