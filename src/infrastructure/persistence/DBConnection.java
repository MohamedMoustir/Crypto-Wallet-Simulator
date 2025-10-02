package infrastructure.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	
	    private static final String URL = System.getenv("DB_URL");
	    private static final String USER = System.getenv("DB_USER");
	    private static final String PASSWORD = System.getenv("DB_PASSWORD");

	    static {
	        if (URL == null || USER == null || PASSWORD == null) {
	            throw new RuntimeException("DB environment variables not set!");
	        }
	    }
    public static Connection getConnection() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                return DriverManager.getConnection(URL ,USER ,PASSWORD);
            }catch(Exception e){
                throw new RuntimeException("Erreur connexion DB", e);
            }

        }

}
