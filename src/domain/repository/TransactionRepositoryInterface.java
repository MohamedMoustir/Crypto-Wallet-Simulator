package domain.repository;

import domain.model.Mempool;
import domain.model.Transaction;
import domain.model.Wallet;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;

public interface TransactionRepositoryInterface {

    public abstract Transaction CreateTransaction(Wallet wallet , Transaction transaction);
    List<Map<String, Object>> getAllTransaction();


}
