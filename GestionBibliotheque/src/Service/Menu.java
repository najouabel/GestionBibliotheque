package Service;

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
            System.out.println("2. Exit");
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
                    System.out.println("Au revoir!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }
}
