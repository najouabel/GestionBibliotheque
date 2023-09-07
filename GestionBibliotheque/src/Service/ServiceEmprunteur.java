package Service;

import Interface.InterfaceEmprunteur;
import Model.Emprunteur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceEmprunteur implements InterfaceEmprunteur {
    @Override
    public boolean emprunteurExiste(int numeroMembre) {
        Connection con = Database.DBconnection.getConnection();
        if (con == null) {
            return false;
        } else {
            try {
                String query = "SELECT COUNT(*) FROM emprunteur WHERE Numero_membre = ?";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, numeroMembre);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }

                return false;
            } catch (SQLException se) {
                se.printStackTrace();
                return false;
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
    public Emprunteur ajouterEmprunteur(Emprunteur emprunteur) {
        Connection con = Database.DBconnection.getConnection();
        if (con == null) {
            return null;
        } else {
            try {
                if (emprunteurExiste(emprunteur.getNumero_membre())) {
                    return null;
                }

                String query = "INSERT INTO emprunteur (Numero_membre, nom, prenom, tele) VALUES (?, ?, ?, ?);";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setInt(1, emprunteur.getNumero_membre());
                preparedStatement.setString(2, emprunteur.getNom());
                preparedStatement.setString(3, emprunteur.getPrenom());
                preparedStatement.setString(4, emprunteur.getTele());

                int rowsInserted = preparedStatement.executeUpdate();

                if (rowsInserted > 0) {
                    return emprunteur;
                } else {
                    return null;
                }
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

}
