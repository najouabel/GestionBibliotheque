package Service;

import java.util.List;
import java.util.Scanner;

import Model.Emprunt;
import Model.Emprunteur;
import Model.Livre;
import Model.bibliothecaire;

public class Menu {
    private ServiceLivre serviceLivre;
    private ServiceEmprunt serviceEmprunt;

    private ServiceEmprunteur ServiceEmprunteur;

    private bibliothecaire authenticatedUser;

    public Menu(ServiceLivre serviceLivre, ServiceEmprunt serviceEmprunt) {
        this.serviceLivre = serviceLivre;
        this.serviceEmprunt = serviceEmprunt;
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
            System.out.println("*************GESTION LIVRE (bibliothecaire)***************");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. modifier un livre");
            System.out.println("3. supprimer un livre");
            System.out.println("************* AFFICHAGE LIVRE***************");
            System.out.println("4. afficher les livres disponible");
            System.out.println("5. afficher les livres emprunter ");
            System.out.println("*************GESTION LIVRE (emprunteur)***************");
            System.out.println("6. recherche un livre");
            System.out.println("7. emprunter un livre");
            System.out.println("8. returner un livre");
            System.out.println("*************GESTION LIVRE (emprunteur)***************");
            System.out.println("9. les statistique des livre");

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
                    Livre livrerecherche=serviceLivre.rechercherLivreParIsbn(isbn);

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
                    List<Livre> livrelistdispoinible= serviceLivre.livredisponible();
                    for(Livre i:livrelistdispoinible){
                        System.out.println(" ISBN: "+i.getIsbn() + "   titre: "+i.getTitre()+"   auteur: "+i.getAuteur()+"   statut: "+i.getStatut());
                    }
                    break;
                case 5:
                    System.out.println("list des livres emprunter: ");
                    List<Livre> livrelistemprunter= serviceLivre.livreempruntes();
                    for(Livre j:livrelistemprunter){
                        System.out.println(" ISBN: "+j.getIsbn() + "   titre: "+j.getTitre()+"   auteur: "+j.getAuteur()+"   statut: "+j.getStatut());
                    }
                    break;
                case 6:
                    System.out.println("1. recherche par ISBN");
                    System.out.println("2. recherche par title");
                    System.out.print("Enter votre choix: ");
                    choix = scanner.nextInt();
                    Livre livrerechercher = new Livre();
                    switch (choix) {
                        case 1:
                            System.out.print("Entrer ISBN rechercher: ");
                            String isbnrechercher=scanner.next();
                            livrerechercher=serviceLivre.rechercherLivreParIsbn(isbnrechercher);
                            break;
                        case 2:
                            System.out.print("Entrer titre recherhcer : ");
                            String titrerechercher=scanner.next();
                            livrerechercher=serviceLivre.rechercherLivreParTitre(titrerechercher);
                            break;

                        default:
                            System.out.println("Invalid choix");
                            break;
                    }
                    if (livrerechercher != null) {
                        System.out.println("ISBN: " + livrerechercher.getIsbn());
                        System.out.println("Titre: " + livrerechercher.getTitre());
                        System.out.println("Auteur: " + livrerechercher.getAuteur());
                        System.out.println("Statut: " + livrerechercher.getStatut());
                    } else {
                        System.out.println("Livre non trouvé.");
                    }
                    break;
                case 7:
                    System.out.print("Entrer ISBN du livre à emprunter : ");
                    String isbnToBorrow = scanner.next();
                    System.out.print("Entrer votre numéro de membre : ");
                    int emprunteurNumeroMembre;

                    emprunteurNumeroMembre = scanner.nextInt();

                    Livre bookToBorrow = serviceLivre.rechercherLivreParIsbn(isbnToBorrow);
                    Emprunteur emprunteur = new Emprunteur();
                    emprunteur.setNumero_membre(emprunteurNumeroMembre);

                    if (bookToBorrow != null) {
                        Emprunt emprunt = serviceEmprunt.enregistrerEmprunt(bookToBorrow, emprunteur);
                        if (emprunt != null) {
                            System.out.println("Livre emprunté avec succès.");
                        } else {
                            System.out.println("Erreur lors de l'emprunt du livre.");
                        }
                    } else {
                        System.out.println("Livre non trouvé.");
                    }
                    break;

                case 8:
                    System.out.print("Entrer ISBN du livre à retourner : ");
                    String isbnToReturn = scanner.next();
                    System.out.print("Entrer votre numéro de membre : ");
                    int emprunteurNumeroMembreToReturn = scanner.nextInt();

                    Livre bookToReturn = serviceLivre.rechercherLivreParIsbn(isbnToReturn);
                    Emprunteur emprunteurToReturn = new Emprunteur();
                    emprunteurToReturn.setNumero_membre(emprunteurNumeroMembreToReturn);

                    if (bookToReturn != null) {
                        Emprunt emprunt = serviceEmprunt.retournerEmprunt(bookToReturn, emprunteurToReturn);
                        if (emprunt != null) {
                            System.out.println("Livre retourné avec succès.");
                        } else {
                            System.out.println("Erreur lors du retour du livre.");
                        }
                    } else {
                        System.out.println("Livre non trouvé.");
                    }
                    break;
                case 9:
                    List<Livre> toutleslivre = serviceLivre.afficher();
                    List<Livre> livreempruntes = serviceLivre.livreempruntes();
                    List<Livre> livredisponible = serviceLivre.livredisponible();
                    List<Livre> livreperdus = serviceLivre.livreperdus();

                    int totallivres = toutleslivre.size();
                    int totallivreempruntes = livreempruntes.size();
                    int totallivredisponible = livredisponible.size();
                    int totallivreperdus = livreperdus.size();

                    System.out.println("Total de livres : " + totallivres);
                    System.out.println("Total de livres empruntés : " + totallivreempruntes);
                    System.out.println("Total de livres disponibles : " + totallivredisponible);
                    System.out.println("Total de livres perdus : " + totallivreperdus);

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
