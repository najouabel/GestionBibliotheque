package Service;

import Interface.InterfaceLivre;
import Model.Livre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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

<<<<<<< HEAD
                preparedStatement.executeUpdate();

            } catch (SQLException se) {
                se.printStackTrace();
=======
                int insertedRows = preparedStatement.executeUpdate();
                if (insertedRows > 0) {
                    return livre;
                } else {
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
>>>>>>> 5da0968fbde307b376ba45850259beb136373f3b
                return null;
            } finally {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return livre;
    }

    public ServiceLivre() {
        super();
    }

    @Override
    public Livre modifierlivre(Livre livre) {
        Connection con = Database.DBconnection.getConnection();
        if (con == null) {
            return null;
        } else {
            String query = "UPDATE  livre SET titre=?, auteur=?, statut=? WHERE isbn=?;";

            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, livre.getTitre());
                preparedStatement.setString(2, livre.getAuteur());
                preparedStatement.setString(3, livre.getStatut() != null ? livre.getStatut().name() : "DefaultStatut");
                preparedStatement.setString(4, livre.getIsbn());
                preparedStatement.executeUpdate();

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
        return livre;

    }

    @Override
    public Livre supprimerlivre(Livre livre) {
        Connection con = Database.DBconnection.getConnection();
        if (con == null) {
            return null;
        } else {
            String query = "DELETE FROM livre WHERE isbn =? ;";

            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, livre.getIsbn());
                preparedStatement.executeUpdate();
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
        return livre;


    }

    @Override
    public List<Livre> afficher() {
            List<Livre> toutlivres = new LinkedList<>();
            Connection con = Database.DBconnection.getConnection();
            if (con == null) {
                return null;
            } else {
                String query = "SELECT * FROM livre ;";

                try {
                    PreparedStatement preparedStatement = con.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        Livre livre = new Livre();
                        livre.setIsbn(resultSet.getString("isbn"));
                        livre.setTitre(resultSet.getString("titre"));
                        livre.setAuteur(resultSet.getString("auteur"));
                        toutlivres.add(livre);
                    }

                    return toutlivres;
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
    public List<Livre> livreempruntes() {
            List<Livre> livreempruntes = new LinkedList<>();
            Connection con = Database.DBconnection.getConnection();
            if (con == null) {
                return null;
            } else {
                String query = "SELECT * FROM livre WHERE statut = 'emprunter';";

                try {
                    PreparedStatement preparedStatement = con.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        Livre livre = new Livre();
                        livre.setIsbn(resultSet.getString("isbn"));
                        livre.setTitre(resultSet.getString("titre"));
                        livre.setAuteur(resultSet.getString("auteur"));
                        livreempruntes.add(livre);
                    }

                    return livreempruntes;
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
    public List<Livre> livreperdus() {
            List<Livre> livreperdus = new LinkedList<>();
            Connection con = Database.DBconnection.getConnection();
            if (con == null) {
                return null;
            } else {
                String query = "SELECT * FROM livre WHERE statut = 'perdu';";

                try {
                    PreparedStatement preparedStatement = con.prepareStatement(query);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        Livre livre = new Livre();
                        livre.setIsbn(resultSet.getString("isbn"));
                        livre.setTitre(resultSet.getString("titre"));
                        livre.setAuteur(resultSet.getString("auteur"));
                        livreperdus.add(livre);
                    }

                    return livreperdus;
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
    public List<Livre> livredisponible() {
        List<Livre> livresDisponibles = new LinkedList<>();
        Connection con = Database.DBconnection.getConnection();
        if (con == null) {
            return null;
        } else {
            String query = "SELECT * FROM livre WHERE statut = 'disponible';";

            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Livre livre = new Livre();
                    livre.setIsbn(resultSet.getString("isbn"));
                    livre.setTitre(resultSet.getString("titre"));
                    livre.setAuteur(resultSet.getString("auteur"));
                    livresDisponibles.add(livre);
                }

                return livresDisponibles;
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
    public Livre rechercherlivre(String isbn) {
        Connection con = Database.DBconnection.getConnection();
        if (con == null) {
            return null;
        } else {
            String query = "SELECT * FROM livre WHERE isbn = ? ;";

            try {
                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, isbn);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    Livre livre = new Livre();
                    livre.setIsbn(resultSet.getString("isbn"));
                    livre.setTitre(resultSet.getString("titre"));
                    livre.setAuteur(resultSet.getString("auteur"));

                    return livre;
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

