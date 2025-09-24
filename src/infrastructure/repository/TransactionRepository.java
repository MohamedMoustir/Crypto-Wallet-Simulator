package infrastructure.repository;

import domain.model.Transaction;
import domain.model.Wallet;
import domain.repository.TransactionRepositoryInterface;
import infrastructure.persistence.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class TransactionRepository implements TransactionRepositoryInterface {

    public boolean CreateTransaction(Wallet wallet , Transaction transaction) {

        String sql = "INSERT INTO transaction (transaction_id ,wallet_id , source_address , destination_address ,amount,fee,priority) VALUES (?,?,?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            stmt.setString(1, transaction.getId().toString());
            stmt.setString(2, wallet.getId().toString());
            stmt.setString(3, transaction.getSource());
            stmt.setString(4, transaction.getDestination());
            stmt.setDouble(5, transaction.getAmount());
            stmt.setDouble(6, transaction.getFees());
            stmt.setString(7, transaction.getPriority());
            int rows = stmt.executeUpdate();

            if (rows > 0){

                return true;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
          return true;
    }



    // logger
    //lamde exprition
    //stream Api
    // Date Time
    //Optional

}
