package app;

import domain.service.*;
import util.*;

import java.util.Scanner;

public class Menu {

    public static void start() {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        //ScheduledExecutorService.startScheduler();
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
            System.out.println(" [3]  Calculer la position dans le mempool");
            System.out.println(" [4]  Comparer les 3 fee levels");
            System.out.println(" [5]  Consulter le solde");
            System.out.println(" [6]  Export Csv");
            System.out.println(" [7]  Exit" + RESET);
            System.out.println("====================================\n");

            int choix = validation.readInt(BLUE + "Enter your choice: " + RESET, 1, 7);

            switch (choix) {
                case 1:
                    MenuActions.createWallet(walletService, RED, GREEN, BLUE, RESET);
                    break;
                case 2:
                    MenuActions.createTransaction(transactionService, mempoolService, walletService, RED, GREEN, BLUE, RESET);
                    break;
                case 3:
                    MenuActions.calculerPosition(transactionService, RED, GREEN, BLUE, RESET);
                    break;
                case 4:
                    MenuActions.comparerLesFee(mempoolService);
                    break;
                case 5:
                    MenuActions.consulteSolde(walletService, RED, GREEN, BLUE, RESET);
                    break;
                case 6:
                    MenuActions.exportCsvTransaction(transactionService);
                    break;
                case 7:
                    sc.close();
                    isRunning = false;
                    break;
                default:
                    System.out.println(RED + " Invalid choice, try again" + RESET);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        start();
    }
}
