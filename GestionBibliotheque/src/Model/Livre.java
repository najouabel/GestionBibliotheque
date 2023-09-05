package Model;



public class Livre {
    private String isbn;
    private String titre;
    private String auteur;

    private Statut statut = Statut.valueOf("Disponible");
    public enum Statut { Disponible, Emprunte, Perdu }

    public Livre() {
    }

    public Livre(String isbn, String titre, String auteur, Statut statut) {
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.statut = statut;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }


}
