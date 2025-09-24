package domain.service;

import domain.enums.CryptoType;
import domain.model.Transaction;
import domain.model.Wallet;
import infrastructure.repository.WalletRepository;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public class WalletService {

    WalletRepository walletRepository = new WalletRepository();

    public boolean CreateWallet (int id_type){

        CryptoType type = null ;
        String bitAddrese   = "";
        double balance = 0;

        if(id_type == 1){
               type = CryptoType.BITCOIN;
            bitAddrese  = "BTC-" + String.valueOf(UUID.randomUUID());
          }else if(id_type == 2){
              type = CryptoType.ETHEREUM;
            bitAddrese  = "ETH-" + String.valueOf(UUID.randomUUID());
          }

        Transaction transaction = new Transaction(type.toString(), bitAddrese , balance);

        return walletRepository.CreateWallet(transaction);

    }

    public Wallet findByAddress(String address){
       return  walletRepository.findByAddress(address);
    }

}
