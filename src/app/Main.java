package app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import domain.enums.Priority;
import domain.model.Mempool;
import domain.model.Transaction;
import domain.model.Wallet;
import domain.service.MempoolService;
import domain.service.TransactionService;
import domain.service.WalletService;
import util.CSVExporter;
import util.ValidateAdresse;
import util.validation;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;

        WalletService walletService = new WalletService();
        TransactionService transactionService = new TransactionService();
        MempoolService mempoolService = new MempoolService();

        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String BLUE = "\u001B[34m";

        while (isRunning) {
        	
            System.out.println("====================================");
            System.out.println(RED + "         Crypto Wallet Simulator            " + RESET);
            System.out.println(" ");
            System.out.println(YELLOW + " [1]  Créer un wallet crypto");
            System.out.println(" [2]  Créer une nouvelle transaction");
            System.out.println(" [3]  Calculer la position dans le mempool et temps d'attente estimé");
            System.out.println(" [4]  Comparer les 3 fee levels avec position réelle dans la file");
            System.out.println(" [5]  Consulter le solde");
            System.out.println(" [6]  Export Csv");
            System.out.println(" [7]  Exit" + RESET);
            System.out.println("====================================\n");

            int choix = validation.readInt(BLUE + "Enter your choice: " + RESET, 1, 7);

            switch (choix) {

                case 1:
                    createWallet(walletService, RED, GREEN, BLUE, RESET);

                    break;
                case 2:
                	   createTransaction(transactionService,mempoolService ,walletService, RED, GREEN, BLUE, RESET);
                    break;

                case 3:
                    calculerPosition(transactionService, RED, GREEN, BLUE, RESET);                  
                    break;
                case 4:                 
                	    comparerLesFee( mempoolService );
                    break;
                case 5:                  
                	    consolteSolde(walletService, RED, GREEN, BLUE, RESET);
                    break;

                case 6:
                	exportCsvTransaction(transactionService);
                    break;
                case 7 :
                	 sc.close();
                    isRunning = false;
                    break ;
                default:
                    System.out.println(RED + " Invalid choice, try again!" + RESET);
            }
            
        }
        
        
      
    }
    
    public static void createWallet(WalletService walletService, String RED, String GREEN, String BLUE, String RESET) {
    	
    	  System.out.println(RED + "        Choisir le type            \n" + RESET);
          System.out.println(" [0]  Retour Au Menu");
          System.out.println(" [1]  BITCOIN ");
          System.out.println(" [2]  ETHEREUM");

          int id_type = validation.readInt(BLUE + " Choisir le type: " + RESET, 0, 3);

          if (id_type == 0) {
              return; 
          }
          
          if (walletService.CreateWallet(id_type)) {
              System.out.println(GREEN + " Inscription réussie !"  + RESET);
          } else {
              System.out.println(RED + " Erreur inscription. Vérifiez vos données ou compte déjà utilisé." + RESET);
          }
          
    }
    
    public static void createTransaction(TransactionService transactionService,MempoolService  mempoolService ,WalletService walletService, String RED, String GREEN, String BLUE, String RESET) {
      	System.out.println(" [0]  Retour Au Menu");

        String adresseSource = ValidateAdresse.validatAdresse(BLUE + " Enter adresse source " + RESET);
        String adresseDestination = ValidateAdresse.validatAdresse(BLUE + " Enter adresse destination " + RESET);

        double montant = validation.readDouble(BLUE + " Enter montant " + RESET);
        if (montant == 0 || adresseSource == null ) return;

        System.out.println(RED + "        Choisir fee level           \n" + RESET);
        System.out.println(" [0]  Retour Au Menu");
        System.out.println(" [1]  ÉCONOMIQUE ");
        System.out.println(" [2]  STANDARD");
        System.out.println(" [3]  RAPIDE");

        int idFeelevel = validation.readInt(BLUE + " Choisir fee level : " + RESET, 0, 4);
        if (idFeelevel == 0) return;

        double fees = transactionService.calculerFee(idFeelevel, adresseSource);
        Priority priority = transactionService.getPriority(idFeelevel);

        try {
            Transaction transaction = transactionService.CreateTransaction(adresseSource, adresseDestination, montant, fees, String.valueOf(priority));
            String message = mempoolService.CreateMemPool(transaction, fees);
            walletService.UbdateBalance(adresseSource, adresseDestination, montant, fees);

            System.out.println(GREEN + message + RESET);

        } catch (Exception e) {
            System.out.println(RED + "Erreur: " + e.getMessage() + RESET);
        }
    }
    
	public static void calculerPosition(TransactionService transactionService ,String RED, String GREEN, String BLUE, String RESET) {
	     	
	    	String adresse = ValidateAdresse.validatAdresse(BLUE + "Enter your address: " + RESET);
	    	
	    	System.out.println(RED + "Your Transactions (your address in blue):" + RESET);
	        System.out.println("+----+----------------+-------+----------+--------+");
	        System.out.printf("| %-2s | %-14s | %-5s | %-8s | %-6s |\n",
	                "Position", "Wallet ID", "Fees", "Priority", "Status");
	        System.out.println("+----+----------------+-------+----------+--------+");                 
	        List<Map<String, Object>> transactions = transactionService.getAllTransaction(adresse);
	                      

	        System.out.println("+----+----------------+-------+----------+--------+");
	
	}
	
	public static void comparerLesFee(MempoolService mempoolService ) {
     	
	    System.out.println("+-------------+-----------+--------------+------------------+");
        System.out.println("| Priority    | Position  | Total Fees   | Est. Time (min)  |");
        System.out.println("+-------------+-----------+--------------+------------------+");
        List<Map<String, Object>> feeLevels = mempoolService.ComparerLesFees();
        System.out.println("+-------------+-----------+--------------+------------------+");
        
    }  
		
	public static void consolteSolde(WalletService walletService, String RED, String GREEN, String BLUE, String RESET) {
		 String address = ValidateAdresse.validatAdresse(BLUE + " Enter your address  " + RESET);
         Wallet wallet = walletService.findByAddress(address);
         if (wallet != null) {
             System.out.println(GREEN + " your solde is " + wallet.getBalance() + RESET);
         } else {
             System.out.println(RED + " Erreur Mise à jour. Vérifiez vos données ou compte déjà utilisé." + RESET);
         }
	}
    
	public static void exportCsvTransaction(TransactionService transactionService) {
		 List<Map<String, Object>> transaction = transactionService.getAllTransaction("");                    
         CSVExporter.exportTransactionsToCSV(transaction, "exports/transaction.csv");

        
	}
}
