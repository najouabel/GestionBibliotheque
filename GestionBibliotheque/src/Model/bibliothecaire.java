package Model;

public class bibliothecaire {
 private String nom_bibliothecaire;
 private String pass_bibliothecaire;

    public bibliothecaire() {
    }

    public bibliothecaire(String nom_bibliothecaire, String pass_bibliothecaire) {
        this.nom_bibliothecaire = nom_bibliothecaire;
        this.pass_bibliothecaire = pass_bibliothecaire;
    }

    public String getNom_bibliothecaire() {
        return nom_bibliothecaire;
    }

    public void setNom_bibliothecaire(String nom_bibliothecaire) {
        this.nom_bibliothecaire = nom_bibliothecaire;
    }

    public String getPass_bibliothecaire() {
        return pass_bibliothecaire;
    }

    public void setPass_bibliothecaire(String pass_bibliothecaire) {
        this.pass_bibliothecaire = pass_bibliothecaire;
    }
}
