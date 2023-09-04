CREATE DATABASE bibliotheque;

USE bibliotheque;

CREATE TABLE livre (
                       isbn VARCHAR(20) PRIMARY KEY,
                       titre VARCHAR(255) NOT NULL,
                       auteur VARCHAR(255),
                       statut ENUM('disponible', 'emprunté', 'perdu')
);

CREATE TABLE bibliothecaire (
                                id_bibliothecaire INT AUTO_INCREMENT PRIMARY KEY,
                                nom_bibliothecaire VARCHAR(255),
                                Pass_bibliothecaire VARCHAR(255)
);

CREATE TABLE emprunteur (
                            Numero_membre INT AUTO_INCREMENT PRIMARY KEY,
                            Nom VARCHAR(255),
                            Prenom VARCHAR(255),
                            Tele VARCHAR(20)
);

CREATE TABLE emprunt (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         emprunteur_id INT,
                         livre_isbn VARCHAR(20),
                         date_emprunt DATE,
                         date_retour DATE,
                         date_retour_prevu DATE,
                         FOREIGN KEY (emprunteur_id) REFERENCES emprunteur(Numero_membre),
                         FOREIGN KEY (livre_isbn) REFERENCES livre(isbn)
);