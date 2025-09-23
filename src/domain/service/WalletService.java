package domain.service;

import domain.model.Transaction;
import domain.model.Wallet;
import infrastructure.repository.WalletRepository;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public class WalletService {

    WalletRepository walletRepository = new WalletRepository();

    public boolean CreateWallet (String type, String address , double balance ){

        Transaction transaction = new Transaction(type,  address , balance);
        System.out.println("" + transaction.getId());

        return walletRepository.CreateWallet(transaction);

    }
}
