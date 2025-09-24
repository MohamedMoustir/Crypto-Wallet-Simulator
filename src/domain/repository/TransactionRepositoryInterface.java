package domain.repository;

import domain.model.Wallet;

import java.util.UUID;

public interface TransactionRepositoryInterface {

    public abstract boolean CreateTransaction(Wallet wallet ,  String address , String source , String destination, double amount , double fees, String priority);

}
