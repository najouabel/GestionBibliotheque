package Model;

import java.sql.Date;

public class Emprunt {
    private Date date_emprunt ;
    private Date date_retour ;
    private Livre livre;
    private Emprunteur emprunteur;

    public Emprunt() {
    }

    public Emprunt(Date date_emprunt, Date date_retour, Livre livre, Emprunteur emprunteur) {
        this.date_emprunt = date_emprunt;
        this.date_retour = date_retour;
        this.livre = livre;
        this.emprunteur = emprunteur;
    }

    public Date getDate_emprunt() {
        return date_emprunt;
    }

    public void setDate_emprunt(Date date_emprunt) {
        this.date_emprunt = date_emprunt;
    }

    public Date getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(Date date_retour) {
        this.date_retour = date_retour;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Emprunteur getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(Emprunteur emprunteur) {
        this.emprunteur = emprunteur;
    }


}