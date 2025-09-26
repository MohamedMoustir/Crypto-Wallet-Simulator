package domain.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.PriorityQueue;

import domain.model.Mempool;
import domain.model.Transaction;

public interface MempoolRepositoryInteface {
	
    PriorityQueue<Mempool> GetAllMemepool();
	boolean CreateMemPool(Transaction transaction, int position_in_queue ,double fee,double estimatedTime);
    
}
