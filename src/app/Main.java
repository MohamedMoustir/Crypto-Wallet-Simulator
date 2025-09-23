package app;

import java.util.Scanner;

import domain.service.WalletService;
import util.validation;
public class Main {
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
          boolean isRunning = true;
        WalletService walletService = new WalletService();
        final String RESET = "\u001B[0m";
        final String RED = "\u001B[31m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String BLUE = "\u001B[34m";




       while(isRunning){
           System.out.println("====================================");

           System.out.println(RED + "                   Crypto Wallet Simulator            "+RESET);
           System.out.println(" ");

           System.out.println(YELLOW +" [1]  Créer un wallet crypto");
           System.out.println(" [2]  Créer une nouvelle transaction");
           System.out.println(" [3]  Calculer la position dans le mempool et temps d'attente estimé");
           System.out.println(" [4]  Comparer les 3 fee levels avec position réelle dans la file");
           System.out.println(" [5]  Exit" + RESET);

           System.out.println("====================================\n");
           int Choix = validation.readInt(BLUE + "Enter your choice: "+ RESET, 1, 5);

           switch(Choix){
               case 1 :
                   System.out.println(RED + "                   Choisir le type            \n"+RESET);
                   System.out.println(" [0]  Retour Au Menu");
                   System.out.println(" [1]  BITCOIN ");
                   System.out.println(" [2]  ETHEREUM");
                   int type = validation.readInt(BLUE + " Choisir le type: "+ RESET, 0, 3);

                   walletService.CreateWallet("BITCOIN",  "adDdress" , 100);

                   break ;

               case 2 :

                   break;

               case 3 :

                   break;

               case 4 :
                   break;

               case 5 :

                   break ;

               default :
                   System.out.println(RED+" Invalid choice, try again!"+ RESET);
           }
       }

    }
}
