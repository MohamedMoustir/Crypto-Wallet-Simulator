package domain.service;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.sql.Timestamp;
import java.time.Duration;
import domain.model.Transaction;
import infrastructure.repository.TransactionRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

	public class ScheduledExecutorService {
		
	    static TransactionRepository repo = new  TransactionRepository();

		
		public static void startScheduler() {
			java.util.concurrent.ScheduledExecutorService scheduler =
			        Executors.newScheduledThreadPool(1);
			
			scheduler.scheduleAtFixedRate(()->{
				
				checkPendingTransactions();
			},1000,10,TimeUnit.MINUTES);
				
			
		}
		
		
		public static void checkPendingTransactions() {
		    List<Map<String, Object>> pendingTxs = repo.getAllTransaction();
		    
		      
		    pendingTxs.sort((t1, t2) ->{
     		    String p1 = (String) t1.get("priority");
     		    String p2 = (String) t2.get("priority");

     		    List<String> priorityOrder = Arrays.asList("RAPIDE", "STANDARD", "ECONOMIQUE");

     		    int cmpPriority = Integer.compare(priorityOrder.indexOf(p1), priorityOrder.indexOf(p2)); 

     		    if (cmpPriority != 0) {
     		        return cmpPriority; 
     		    }

     		    double fee1 = (Double) t1.get("fees");
     		    double fee2 = (Double) t2.get("fees");
     		    return Double.compare(fee2, fee1); 
     		    
     		});
     		   
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		    int limit = Math.min(4, pendingTxs.size());
		    
		    for (int i = 0; i < limit; i++) {
		    	
		        Map<String, Object> tx = pendingTxs.get(i);

		        String createdAtStr = (String) tx.get("created_at");
		        LocalDateTime createdAt = LocalDateTime.parse(createdAtStr, formatter);

		        System.out.println("✅ Transaction confirmed!");
		        System.out.println("   • Wallet: " + tx.get("walletId"));
		        System.out.println("   • source_address: " + tx.get("source"));
		        System.out.println("   • Created at: " + createdAt);
		        System.out.println("   • Priority: " + tx.get("priority"));
		        System.out.println("-----------------------------");
        
		        repo.updateTransactionStatus((String) tx.get("transaction_id"), "CONFIRMED");
		    }
		}


	
}
