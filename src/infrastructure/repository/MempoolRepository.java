package infrastructure.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.model.Mempool;
import domain.model.Transaction;
import domain.repository.MempoolRepositoryinterface;
import infrastructure.persistence.DBConnection;

public class MempoolRepository implements MempoolRepositoryinterface{
	 final Logger logger = LoggerFactory.getLogger(MempoolRepository.class);

	private static MempoolRepository MempoolInstance ;
	
    private Map<String , Double> pool = new HashMap<>();
    
	public static MempoolRepository getMempoolInstance() {
		if (MempoolInstance == null) {
			MempoolInstance = new MempoolRepository();
        }	
		
		return MempoolInstance ;
	}
	 

	@Override
	public boolean CreateMemPool(Transaction transaction ,int position_in_queue ,double fee ,double estimatedTime) {
		

		  String sql = "INSERT INTO mempool (transaction_id  , position_in_queue , estimated_time ,fee ) VALUES (?,?,?,?)";

	       
			try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
				
				if(transaction == null){
				    throw new IllegalArgumentException("Transaction cannot be null");
				}
				

	            stmt.setString(1, transaction.getId().toString());
	            stmt.setInt(2, position_in_queue);
	            stmt.setDouble(3, estimatedTime);
	            stmt.setDouble(4, fee);

	            int rows = stmt.executeUpdate();

	            if (rows > 0){
	                logger.error("MemPool {} créée avec succès.", transaction.getId());
	                return true;
	            }

	        } catch (Exception e) {
	            logger.error(" Erreur lors de la création de la MemPool: {}", e.getMessage(), e);
	        }
	          return true;
	
	}

	@Override

	 public  PriorityQueue<Mempool> GetAllMemepool() {
		
	    String sql = "SELECT * FROM mempool ";
	    
	    
	    PriorityQueue<Mempool>  mempoolQueue = new PriorityQueue<>(
	    		Comparator.comparingDouble((Mempool t)->t.getFee()).reversed()
	    		);
	    
	    		
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	
	        while (rs.next()) { 
	        	
	        	String transactionid = rs.getString("transaction_id");
	            double fee = rs.getDouble("fee");
	            Mempool tx = new Mempool(transactionid, fee);
	            
	            mempoolQueue.add(tx);          
	            
	        }
	
	        logger.info("MemPool retrieved successfully.");
	        return mempoolQueue;
	
	    } catch (Exception e) {
	    	System.out.println("Error retrieving MemPool: {}");
	        return mempoolQueue;
	    }
	}
	
	public List<Map<String, Object>> getMemPool() {
    String sql = 
        "SELECT " +
        "    priority, " +
        "    COUNT(*) AS position, " +
        "    CAST(SUM(fee) AS DECIMAL(20,8)) AS total_fees, " +
        "    CEIL(COUNT(*) / 3) * 10 / 60 AS est_time_minutes " +
        "FROM `transaction` " +
        "WHERE status = 'PENDING' " +
        "GROUP BY priority " +
        "ORDER BY " +
        "    CASE priority " +
        "        WHEN 'RAPIDE' THEN 1 " +
        "        WHEN 'STANDARD' THEN 2 " +
        "        WHEN 'ECONOMIQUE' THEN 3 " +
        "        ELSE 4 " +
        "    END;";

    List<Map<String, Object>> mempool = new ArrayList<>();

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
        	
            Map<String, Object> tx = new HashMap<>();
            
            tx.put("priority", rs.getString("priority"));
            tx.put("position", rs.getInt("position"));
            tx.put("total_fees", rs.getBigDecimal("total_fees"));
            tx.put("est_time_minutes", rs.getDouble("est_time_minutes"));

            mempool.add(tx);
        }

        logger.info("mempool aggregated successfully.");
        return mempool;

    } catch (Exception e) {
        System.out.println("Error retrieving transactions: " + e.getMessage());
        return mempool; 
    }
}

	
	
	
}
