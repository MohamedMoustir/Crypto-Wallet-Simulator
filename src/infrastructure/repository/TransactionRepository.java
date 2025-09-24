package infrastructure.repository;

import domain.model.Wallet;
import domain.repository.TransactionRepositoryInterface;

import java.util.UUID;

public class TransactionRepository implements TransactionRepositoryInterface {

    public boolean CreateTransaction(Wallet wallet ,  String address , String source , String destination, double amount , double fees, String priority){
   return true;//DB

    }




    // logger
    //lamde exprition
    //stream Api
    // Date Time
    //Optional

}
