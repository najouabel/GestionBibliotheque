package Service;

import java.util.List;
import java.util.Scanner;
import Model.Livre;
import Model.bibliothecaire;

public class Menu {
    private ServiceLivre serviceLivre;
    private bibliothecaire authenticatedUser;

    public Menu(ServiceLivre serviceLivre) {
        this.serviceLivre = serviceLivre;
    }

    public Menu() {
    }

    public void displayMenu() {
        authentification authenticator = new authentification();
        if (!authenticator.authenticate()) {
            System.out.println("Authentication failed. Access denied.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. modifier un livre");
            System.out.println("3. supprimer un livre");
            System.out.println("4. afficher un livre");
            System.out.println("0. Exit");
            System.out.print("Entrez votre choix: ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    Livre livre = new Livre();
                    System.out.print("Entrer ISBN: ");
                    livre.setIsbn(scanner.next());
                    scanner.nextLine();
                    System.out.print("Entrer le titre: ");
                    livre.setTitre(scanner.nextLine());
                    System.out.print("Entrer Auteur: ");
                    livre.setAuteur(scanner.nextLine());

                    Livre addedLivre = serviceLivre.ajouterlivre(livre);
                    if (addedLivre != null) {
                        System.out.println("Livre ajouté avec succès.");
                    } else {
                        System.out.println("Erreur lors de l'ajout du livre.");
                    }
                    break;

                case 2:
                    System.out.print("Entrer ISBN: ");
                    String isbn=scanner.next();
                    scanner.nextLine();
                    Livre livrerecherche=serviceLivre.rechercherlivre(isbn);

                    System.out.println("entrer nouveau titre");
                    String modifiertitre= scanner.nextLine();
                    if(!modifiertitre.isEmpty()){
                        livrerecherche.setTitre(modifiertitre);
                    }

                    System.out.println("entrer nouveau auteur");
                    String modifierauteur= scanner.nextLine();
                    if(!modifierauteur.isEmpty()){
                        livrerecherche.setAuteur(modifierauteur);
                    }

                    /*System.out.println("entrer nouveau statut");
                    statut nouveaustatut= scanner.nextLine();
                    if(nouveaustatut.isEmpty()){
                        livrerecherche.setStatut(nouveaustatut);
                    }
                    */

                    livrerecherche=serviceLivre.modifierlivre(livrerecherche);
                    System.out.println("Livre modifier avec succès.");
                    break;
                case 3:
                    System.out.print("Entrer ISBN: ");
                    Livre livresupprimer = new Livre();
                    livresupprimer.setIsbn(scanner.next());
                    livresupprimer=serviceLivre.supprimerlivre(livresupprimer);
                    System.out.println("Livre supprimer avec succès.");
                    break;
                case 4:
                    System.out.println("list des livres disponible: ");
                    List<Livre> livrelist= serviceLivre.livredisponible();
                    for(Livre i:livrelist){
                        System.out.println(" ISBN: "+i.getIsbn() + "   titre: "+i.getTitre()+"   auteur: "+i.getAuteur()+"   statut: "+i.getStatut());
                    }
                    break;
                case 0:
                    System.out.println("Au revoir!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }
}
