package infrastructure.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import domain.model.Mempool;

import domain.model.Transaction;
import domain.model.Wallet;
import domain.repository.TransactionRepositoryInterface;
import domain.service.WalletService;
import infrastructure.persistence.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TransactionRepository implements TransactionRepositoryInterface {
    private static final Logger logger = LoggerFactory.getLogger(TransactionRepository.class);

    public Transaction CreateTransaction(Wallet wallet , Transaction transaction) {

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
                logger.info("Transaction {} créée avec succès.", transaction.getId());
                return transaction;

            }

        } catch (Exception e) {
            logger.error(" Erreur lors de la création de la transaction: {}", e.getMessage(), e);
        }
          return transaction;
    }
    
   public List<Map<String, Object>> getAllTransaction() {
    String sql = "SELECT * FROM transaction \r\n"
    		+ "LEFT JOIN mempool AS m on m.transaction_id = transaction.transaction_id WHERE transaction.status = \"Pending\";";
  
	List<Map<String, Object>> transactions = new ArrayList<>();

    
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {


    	while (rs.next()) {
    	    Map<String, Object> tx = new HashMap<>();
    	    tx.put("transaction_id", rs.getString("transaction_id"));
    	    tx.put("walletId", rs.getString("wallet_id"));
    	    tx.put("source", rs.getString("source_address"));
    	    tx.put("destination", rs.getString("destination_address"));
    	    tx.put("amount", rs.getDouble("amount"));
    	    tx.put("fees", rs.getDouble("fee"));
    	    tx.put("priority", rs.getString("priority"));
    	    tx.put("status", rs.getString("status"));
    	    tx.put("created_at", rs.getString("created_at"));
    	    tx.put("estimated_time", rs.getInt("estimated_time"));

    	    transactions.add(tx);
    	}


        logger.info("Transactions retrieved successfully.");
        return transactions;

    } catch (Exception e) {
        logger.error("Error retrieving transactions: " + e.getMessage());
        return transactions; 
    }
}

   public void updateTransactionStatus(String transaction_id, String status) {
	    String sql = "UPDATE transaction SET status = ? WHERE transaction_id = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, status);         
	        stmt.setString(2, transaction_id);  

	        int rows = stmt.executeUpdate();

	    } catch (Exception e) {
	        logger.error("Erreur lors de la mise à jour : {}", e.getMessage(), e);
	    }
	}

   
 


}
