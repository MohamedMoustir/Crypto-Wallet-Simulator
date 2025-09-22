package app;

import infrastructure.persistence.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from wallet");

            while (rs.next()) {
                System.out.println("Wallet: " + rs.getString("address") + " - Balance: " + rs.getDouble("balance"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
