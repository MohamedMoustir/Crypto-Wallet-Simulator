package infrastructure.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/crypto_wallet?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";



    public static Connection getConnection() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                return DriverManager.getConnection(URL ,USER ,PASSWORD);
            }catch(Exception e){
                throw new RuntimeException("Erreur connexion DB", e);
            }

        }

}
