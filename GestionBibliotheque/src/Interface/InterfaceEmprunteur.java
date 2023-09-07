package Interface;

import Model.Emprunteur;

public interface InterfaceEmprunteur {
    Emprunteur ajouterEmprunteur(Emprunteur emprunteur);
    boolean emprunteurExiste(int numeroMembre);
}
