package Service;

import Interface.InterfaceEmprunt;
import Model.Emprunt;
import Model.Livre;
import Model.Emprunteur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ServiceEmprunt implements InterfaceEmprunt {


@Override
        public Emprunt enregistrerEmprunt(Livre livre, Emprunteur emprunteur) {
            Connection con = Database.DBconnection.getConnection();
            if (con == null) {
                return null;
            } else {
                try {
                    String query = "SELECT statut FROM livre WHERE isbn = ?";
                    PreparedStatement preparedStatement = con.prepareStatement(query);
                    preparedStatement.setString(1, livre.getIsbn());
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (!resultSet.next() || !resultSet.getString("statut").equals("disponible")) {
                        System.out.println("Le livre n'est pas disponible.");
                        return null;
                    }

                    ServiceEmprunteur serviceEmprunteur = new ServiceEmprunteur();
                    if (!serviceEmprunteur.emprunteurExiste(emprunteur.getNumero_membre())) {
                        System.out.print("Entrer le nom de l'emprunteur : ");
                        Scanner scanner=new Scanner(System.in);
                        String nomEmprunteur = scanner.nextLine();
                        System.out.print("Entrer le prénom de l'emprunteur : ");
                        String prenomEmprunteur = scanner.nextLine();
                        System.out.print("Entrer le numéro de téléphone de l'emprunteur : ");
                        String telephoneemprunteur = scanner.nextLine();
                        
                        emprunteur.setNom(nomEmprunteur);
                        emprunteur.setPrenom(prenomEmprunteur);
                        emprunteur.setTele(telephoneemprunteur);

                        serviceEmprunteur.ajouterEmprunteur(emprunteur);
                    }

                    String queryselect = "SELECT COUNT(*) as count FROM emprunt WHERE numero_membre = ? AND livre_isbn = ?";
                    PreparedStatement verification = con.prepareStatement(queryselect);
                    verification.setInt(1, emprunteur.getNumero_membre());
                    verification.setString(2, livre.getIsbn());
                    ResultSet ResultSet = verification.executeQuery();

                    if (ResultSet.next() && ResultSet.getInt("count") > 0) {
                        System.out.println("Vous avez déjà emprunté ce livre.");
                        return null;
                    }

                    Emprunt emprunt = new Emprunt();
                    emprunt.setLivre(livre);
                    emprunt.setEmprunteur(emprunteur);

                    java.util.Date currentDate = new java.util.Date();
                    java.sql.Date sqlCurrentDate = new java.sql.Date(currentDate.getTime());

                    java.util.Calendar calendar = java.util.Calendar.getInstance();
                    calendar.setTime(currentDate);
                    calendar.add(java.util.Calendar.DAY_OF_MONTH, 15);
                    java.util.Date returnDate = calendar.getTime();

                    java.sql.Date returndate = new java.sql.Date(returnDate.getTime());

                    emprunt.setDate_emprunt(sqlCurrentDate);
                    emprunt.setDate_retour(returndate);

                    String insertQuery = "INSERT INTO emprunt (Numero_membre, livre_isbn, date_emprunt, date_retour) VALUES (?, ?, ?, ?);";
                    PreparedStatement insertStatement = con.prepareStatement(insertQuery);
                    insertStatement.setInt(1, emprunteur.getNumero_membre());
                    insertStatement.setString(2, livre.getIsbn());
                    insertStatement.setDate(3, sqlCurrentDate);
                    insertStatement.setDate(4, returndate);
                    insertStatement.executeUpdate();

                   /* String updateQuery = "UPDATE livre SET statut = ? WHERE isbn = ?";
                    PreparedStatement updateStatement = con.prepareStatement(updateQuery);
                    updateStatement.setString(1, "emprunte");
                    updateStatement.setString(2, livre.getIsbn());
                    updateStatement.executeUpdate();*/

                    return emprunt;
                } catch (SQLException se) {
                    se.printStackTrace();
                    return null;
                } finally {
                    try {
                        con.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        }

    @Override
    public Emprunt retournerEmprunt(Livre livre, Emprunteur emprunteur) {
        Connection con = Database.DBconnection.getConnection();
        if (con == null) {
            return null;
        } else {
            try {
                String query = "SELECT * FROM emprunt WHERE Numero_membre = ? AND livre_isbn = ?";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, emprunteur.getNumero_membre());
                preparedStatement.setString(2, livre.getIsbn());

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    Emprunt emprunt = new Emprunt();
                    emprunt.setLivre(livre);
                    emprunt.setEmprunteur(emprunteur);
                    emprunt.setDate_emprunt(resultSet.getDate("date_emprunt"));
                    emprunt.setDate_retour(resultSet.getDate("date_retour"));

                    String deleteQuery = "DELETE FROM emprunt WHERE Numero_membre = ? AND livre_isbn = ?";
                    PreparedStatement deleteStatement = con.prepareStatement(deleteQuery);
                    deleteStatement.setInt(1, emprunteur.getNumero_membre());
                    deleteStatement.setString(2, livre.getIsbn());
                    deleteStatement.executeUpdate();


                    /* String update = "UPDATE livre SET statut = ? WHERE isbn = ?";
                    PreparedStatement updateStatement = con.prepareStatement(update);
                    updateStatement.setString(1, "disponible");
                    updateStatement.setString(2, livre.getIsbn());
                    updateStatement.executeUpdate();*/

                    return emprunt;
                }

                return null;
            } catch (SQLException se) {
                se.printStackTrace();
                return null;
            } finally {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
@Override
    public void updatestatus() {
        Connection con = Database.DBconnection.getConnection();
        if (con == null) {
            return;
        } else {
            try {
                java.util.Date currentDate = new java.util.Date();
                java.sql.Date sqlCurrentDate = new java.sql.Date(currentDate.getTime());

                String updateQuery = "UPDATE livre l " +
                                     "INNER JOIN emprunt e ON l.isbn = e.livre_isbn " +
                                     "SET l.statut = 'perdu' " +
                                     "WHERE e.date_retour < ?";
                PreparedStatement updateStatement = con.prepareStatement(updateQuery);
                updateStatement.setDate(1, sqlCurrentDate);
                updateStatement.executeUpdate();
            } catch (SQLException se) {
                se.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

}
