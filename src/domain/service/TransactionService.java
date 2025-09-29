package domain.service;

import domain.enums.Priority;
import domain.model.Mempool;
import domain.model.Transaction;
import domain.model.Wallet;
import infrastructure.repository.TransactionRepository;
import infrastructure.repository.WalletRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class TransactionService {

     TransactionRepository repo = new  TransactionRepository();
    private WalletRepository walle = WalletRepository.getInstanceWallet();

    private static final int BTC_TX_SIZE = 300 ;
    private static final int ETH_GAS_LIMIT  = 21000 ;

    public Transaction CreateTransaction(  String source , String destination, double amount , double fees, String priority){
        Wallet wallet = walle.findByAddress(source);

         if (wallet == null) {
            return null;
         }else if(wallet.getBalance() < amount + fees || Objects.equals(destination, source) || priority == null){
        return null;
    }

        Transaction transaction = new Transaction(wallet.getId() ,wallet.getType() ,wallet.getAddress(),wallet.getBalance(), source ,  destination,  amount ,  fees,  priority);
        return repo.CreateTransaction(wallet ,transaction);
     }


    public double calculerFee (int idFeelevel ,String adresseSource){
    	
        Priority priority = null ;
        Priority Prio ;
        String adresse ;

        double fees = 0;

        priority  = getPriority(idFeelevel);

        if(adresseSource.startsWith("BTC-")){
            return calculerFeeBitcoin(priority);

        }else if (adresseSource.startsWith("ETH-")){
            return calculerFeeEthereum(priority);
        }else{
            return 0;
        }

    }

    private double calculerFeeEthereum(Priority priority) {
        long gasPrice = 0;

        switch (priority) {
            case ECONOMIQUE:
                gasPrice = 20L * 1_000_000_000L ;
                break;
            case STANDARD:
                gasPrice = 50L * 1_000_000_000L ;
                break;
            case RAPIDE:
                gasPrice = 1000L * 1_000_000_000L ;
                break;
        }

        // calcule fee b wei
       java.math.BigDecimal feewi =  java.math.BigDecimal.valueOf(ETH_GAS_LIMIT).multiply(java.math.BigDecimal.valueOf(gasPrice));
        java.math.BigDecimal weiEth = java.math.BigDecimal.TEN.pow(18);
        double feeEth =  feewi.divide(weiEth ,8 ,java.math.RoundingMode.HALF_UP).doubleValue();
        System.out.printf("Fee choisi: " + priority +"\n" + "Fee en satoshis: " + weiEth + "\n" + "Fee en ETH: %.8f\n", feeEth);
        return feeEth ;
    }

    private double calculerFeeBitcoin(Priority priority) {
        int satePerByte = 0;

        switch (priority) {
            case ECONOMIQUE:
                satePerByte = 10 ;
                break;
            case STANDARD:
                satePerByte = 30 ;
                break;
            case RAPIDE:
                satePerByte = 60 ;
                break;
        }


        long feeBitc = BTC_TX_SIZE * satePerByte ;
        //Satoshi
        double BTC = feeBitc / 100000000.0 ;
        System.out.println("Fee choisi: " + priority +"\n" + "Fee en satoshis: " +feeBitc + "\n" + "Fee en BTC: " + BTC +"\n");

        return BTC ;
    }

    public Priority getPriority(int idFeelevel){
        switch (idFeelevel){
            case 1: return Priority.ECONOMIQUE;
            case 2: return Priority.STANDARD;
            case 3: return Priority.RAPIDE;
            default: return Priority.STANDARD;
        }
    }
    
    public List<Map<String, Object>> getAllTransaction(String adresse){
		
   	 List<Map<String, Object>> transactions = repo.getAllTransaction();
   	 
   	 transactions.sort((t1, t2) -> {
            String p1 = (String) t1.get("priority");
            String p2 = (String) t2.get("priority");

            List<String> priorityOrder = Arrays.asList("RAPIDE", "STANDARD", "ECONOMIQUE");
            int cmpPriority = Integer.compare(priorityOrder.indexOf(p1), priorityOrder.indexOf(p2));
            if (cmpPriority != 0) return cmpPriority;

            double fee1 = (Double) t1.get("fees");
            double fee2 = (Double) t2.get("fees");
            return Double.compare(fee2, fee1);
        });

   	 

        int position = 0;
        for (Map<String, Object> tx : transactions) {
            position++;

            String walletId = (String) tx.get("walletId");
            double fee = (Double) tx.get("fees");
            String priority1 = (String) tx.get("priority");
            String status = (String) tx.get("status");
            String source = (String) tx.get("source");

            String output = String.format("| %-2d | %-14s | %-5.4f | %-8s | %-6s |",
                    position, walletId, fee, priority1, status);
            System.out.println(output);   
          
        }

   	return transactions ;
        

	}


}
