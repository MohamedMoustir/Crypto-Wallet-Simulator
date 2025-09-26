package domain.repository;

import domain.model.Transaction;

import java.time.LocalDateTime;

public interface MempoolRepositoryinterface {
    boolean CreateMemPool(Transaction transaction , int position_in_queue , LocalDateTime estimated_time);
}
