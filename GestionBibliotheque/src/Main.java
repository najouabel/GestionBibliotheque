import java.sql.Connection;
import Service.Menu;
import Service.ServiceEmprunt;
import Service.ServiceLivre;

public class Main {
    public static void main(String[] args) {
        Connection con = Database.DBconnection.getConnection();
        if (con == null) {
            System.out.println("Connection failed");
        } else {
            System.out.println("Connection success");
            ServiceLivre serviceLivre = new ServiceLivre();
            ServiceEmprunt serviceEmprunt = new ServiceEmprunt();
            serviceEmprunt.updatestatus();
            Menu menu = new Menu(serviceLivre, serviceEmprunt);

            menu.displayMenu();
        }
    }
}
