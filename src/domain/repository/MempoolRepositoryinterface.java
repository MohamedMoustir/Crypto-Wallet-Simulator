package domain.repository;

public interface MempoolRepository {
    boolean CreateMemPool(Transaction transaction ,int position_in_queue ,LocalDateTime estimated_time);
}
