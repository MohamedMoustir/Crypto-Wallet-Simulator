package domain.repository;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import domain.model.Mempool;
import domain.model.Transaction;

public interface MempoolRepositoryinterface {

	boolean CreateMemPool(Transaction transaction, int position_in_queue, double fee, double estimatedTime);

	PriorityQueue<Mempool> GetAllMemepool();
	List<Map<String, Object>> getMemPool();
}
