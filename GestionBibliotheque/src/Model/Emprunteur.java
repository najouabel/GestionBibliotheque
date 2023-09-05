package Model;

public class Emprunteur {
    private int Numero_membre ;
    private String nom;
    private String prenom;
    private String tele;

    public Emprunteur() {
    }

    public Emprunteur(int numero_membre, String nom, String prenom, String tele) {
        Numero_membre = numero_membre;
        this.nom = nom;
        this.prenom = prenom;
        this.tele = tele;
    }

    public int getNumero_membre() {
        return Numero_membre;
    }

    public void setNumero_membre(int numero_membre) {
        Numero_membre = numero_membre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTele() {
        return tele;
    }

    public void setTele(String tele) {
        this.tele = tele;
    }

}
