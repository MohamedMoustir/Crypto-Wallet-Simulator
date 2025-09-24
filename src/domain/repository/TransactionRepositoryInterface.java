package domain.repository;

import domain.model.Transaction;
import domain.model.Wallet;

import java.util.UUID;

public interface TransactionRepositoryInterface {

    public abstract boolean CreateTransaction(Wallet wallet , Transaction transaction);

}
