package Service;

import Interface.InterfaceLivre;
import Model.Livre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServiceLivre implements InterfaceLivre {


    @Override
    public Livre ajouterlivre(Livre livre) {
        Connection con = Database.DBconnection.getConnection();
        if (con == null) {
            return null;
        } else {
            String query = "INSERT INTO livre (isbn, titre, auteur, statut) VALUES (?, ?, ?, ?);";

            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, livre.getIsbn());
                preparedStatement.setString(2, livre.getTitre());
                preparedStatement.setString(3, livre.getAuteur());
                preparedStatement.setString(4, livre.getStatut() != null ? livre.getStatut().name() : "DefaultStatut");

                int insertedRows = preparedStatement.executeUpdate();
                if (insertedRows > 0) {
                    return livre;
                } else {
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

