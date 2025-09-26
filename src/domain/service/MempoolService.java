package domain.service;

import java.sql.Timestamp;

import domain.model.Transaction;
import infrastructure.repository.MempoolRepository;
import domain.enums.Priority;
import domain.model.Mempool ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import java.nio.charset.StandardCharsets;
public class MempoolService {
	
	
	final String RESET = "\u001B[0m";
    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    final String YELLOW = "\u001B[33m";
    final String BLUE = "\u001B[34m";
    
    private static final int BLOCK_SIZE_BYTES = 1_000_000;
    private static final int  BLOCK_TIME = 600;
    
    
	 final Logger logger = LoggerFactory.getLogger(MempoolRepository.class);
	MempoolRepository Mempool = MempoolRepository.getMempoolInstance();
	
	
     
	public String CreateMemPool(Transaction transaction ,double fees) {
		
		
		PriorityQueue<Mempool> queue = Mempool.GetAllMemepool(); 
		
		int txSize = calculateTxSize(transaction);
		double transactionsBlock =(double) BLOCK_SIZE_BYTES / txSize ;

		int position = 1;
		
		//String newTxId = "TX_NEW"; 
		
		
		for (Mempool tx : queue) {
			if(fees <= tx.getFee()) {
				position++;
			}
		  
		}
		
		double estimatedTime = (Math.ceil((double) position * BLOCK_TIME));
		
		Mempool.CreateMemPool(transaction, position , fees,estimatedTime);
		
		String message = GREEN + "Votre transaction est en position " + position + " sur " + Mempool.GetAllMemepool().size()  + RESET ;
		return  message;
		
		
	}

	
	public int calculateTxSize(Transaction transaction) {
	    int size = 0;

	    size += 16;
	    size += transaction.getAddress().getBytes(StandardCharsets.UTF_8).length;
	    size += transaction.getDestination().getBytes(StandardCharsets.UTF_8).length;
	    size += transaction.getPriority().getBytes(StandardCharsets.UTF_8).length;
	    size += transaction.getStatus().getBytes(StandardCharsets.UTF_8).length;
	    size += Double.BYTES; 
	    size += Double.BYTES; 
	    return size;
	}
	
	
	public List<Map<String, Object>> ComparerLesFees() {
		
	    return Mempool.getMemPool();
	}
	
	
	

}
