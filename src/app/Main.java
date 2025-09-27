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
        MempoolService mempoolRepository = new MempoolService();

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
            System.out.println(" [6]  Exit" + RESET);
            System.out.println("====================================\n");

            int Choix = validation.readInt(BLUE + "Enter your choice: " + RESET, 1, 6);

            switch (Choix) {

                case 1:
                    System.out.println(RED + "        Choisir le type            \n" + RESET);
                    System.out.println(" [0]  Retour Au Menu");
                    System.out.println(" [1]  BITCOIN ");
                    System.out.println(" [2]  ETHEREUM");

                    int id_type = validation.readInt(BLUE + " Choisir le type: " + RESET, 0, 3);

                    if (id_type == 0) break;

                    if (walletService.CreateWallet(id_type)) {
                        System.out.println(GREEN + " Inscription réussie !" + RESET);
                    } else {
                        System.out.println(RED + " Erreur inscription. Vérifiez vos données ou compte déjà utilisé." + RESET);
                    }
                    break;


                case 2:
                    System.out.println(" [0]  Retour Au Menu");

                    String adresseSource = ValidateAdresse.validatAdresse(BLUE + " Enter adresse source " + RESET);
                    String adresseDestination = ValidateAdresse.validatAdresse(BLUE + " Enter adresse destination " + RESET);

                    double montant = validation.readDouble(BLUE + " Enter montant " + RESET);
                    if (montant == 0) break;

                    System.out.println(RED + "        Choisir fee level           \n" + RESET);
                    System.out.println(" [0]  Retour Au Menu");
                    System.out.println(" [1]  ÉCONOMIQUE ");
                    System.out.println(" [2]  STANDARD");
                    System.out.println(" [3]  RAPIDE");

                    int idFeelevel = validation.readInt(BLUE + " Choisir fee level : " + RESET, 0, 4);
                    if (idFeelevel == 0) break;

                    double fees = transactionService.calculerFee(idFeelevel, adresseSource);
                    Priority priority = transactionService.getPriority(idFeelevel);

                    try {
                        Transaction transaction = transactionService.CreateTransaction(adresseSource, adresseDestination, montant, fees, String.valueOf(priority));
                        String message = mempoolRepository.CreateMemPool(transaction, fees);
                        walletService.UbdateBalance(adresseSource, adresseDestination, montant, fees);

                        System.out.println(GREEN + message + RESET);

                    } catch (Exception e) {
                        System.out.println(RED + "Erreur: " + e.getMessage() + RESET);
                    }

                    break;


                case 3:
                    String adresse = ValidateAdresse.validatAdresse(BLUE + "Enter your address: " + RESET);
                    List<Map<String, Object>> transactions = transactionService.getAllTransaction();

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

                    System.out.println(RED + "Your Transactions (your address in blue):" + RESET);
                    System.out.println("+----+----------------+-------+----------+--------+");
                    System.out.printf("| %-2s | %-14s | %-5s | %-8s | %-6s |\n",
                            "Position", "Wallet ID", "Fees", "Priority", "Status");
                    System.out.println("+----+----------------+-------+----------+--------+");

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

                        if (adresse.equalsIgnoreCase(source)) {
                            System.out.println(BLUE + output + RESET);
                        } else {
                            System.out.println(GREEN + output + RESET);
                        }
                    }

                    System.out.println("+----+----------------+-------+----------+--------+");
                    break;


                case 4:
                    List<Map<String, Object>> feeLevels = mempoolRepository.ComparerLesFees();

                    System.out.println("+-------------+-----------+--------------+------------------+");
                    System.out.println("| Priority    | Position  | Total Fees   | Est. Time (min)  |");
                    System.out.println("+-------------+-----------+--------------+------------------+");

                    for (Map<String, Object> entry : feeLevels) {
                        String priority1 = (String) entry.get("priority");
                        int position1 = ((Number) entry.get("position")).intValue();
                        double totalFees = ((Number) entry.get("total_fees")).doubleValue();
                        double estTime = ((Number) entry.get("est_time_minutes")).doubleValue();

                        System.out.printf("| %-11s | %-9d | %-12.8f | %-16.2f |\n",
                                priority1, position1, totalFees, estTime);
                    }

                    System.out.println("+-------------+-----------+--------------+------------------+");
                    break;

                case 5:
                    String address = ValidateAdresse.validatAdresse(BLUE + " Enter your address  " + RESET);
                    Wallet wallet = walletService.findByAddress(address);

                    if (wallet != null) {
                        System.out.println(GREEN + " your solde is " + wallet.getBalance() + RESET);
                    } else {
                        System.out.println(RED + " Erreur Mise à jour. Vérifiez vos données ou compte déjà utilisé." + RESET);
                    }
                    break;

                case 6:
                    List<Map<String, Object>> transaction = transactionService.getAllTransaction();
                    
                    CSVExporter.exportTransactionsToCSV(transaction, "exports/transaction.csv");

                    //sc.close();
                    //isRunning = false;
                    break;

                default:
                    System.out.println(RED + " Invalid choice, try again!" + RESET);
            }
        }
    }
}
