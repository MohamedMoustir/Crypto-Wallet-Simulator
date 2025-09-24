package domain.repository;

import domain.model.Wallet;

public interface WalletRepositoryInterface {
    boolean CreateWallet(Wallet wallet );
    Wallet findByAddress(String address);
    boolean UbdateBalance(String address ,double balance );
}
