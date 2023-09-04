package Service;
import Model.bibliothecaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class authentification {

    boolean authenticate() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter librarian username: ");
        String username = scanner.nextLine();
        System.out.print("Enter librarian password: ");
        String password = scanner.nextLine();


        Connection con = Database.DBconnection.getConnection();
        if (con != null) {
            try {
                String query = "SELECT * FROM bibliothecaire WHERE nom_bibliothecaire = ? AND pass_bibliothecaire = ?";
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    bibliothecaire authenticatedUser = new bibliothecaire(username, password);
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

}
