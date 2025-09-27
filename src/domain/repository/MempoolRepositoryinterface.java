package domain.repository;

import domain.model.Mempool;
import domain.model.Transaction;

import java.time.LocalDateTime;
import java.util.PriorityQueue;

public interface MempoolRepositoryinterface {

	boolean CreateMemPool(Transaction transaction, int position_in_queue, double fee, double estimatedTime);

	PriorityQueue<Mempool> GetAllMemepool();
}
