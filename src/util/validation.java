
package util;
import java.util.Scanner;

public class validation {
   private static final Scanner sc = new Scanner(System.in);

    final static String RESET = "\u001B[0m";
    final static String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";


    public static int readInt(String message, int min, int max) {
        int value;
        while (true) {
            System.out.print(message);

            String input = sc.nextLine();
            try {
                value = Integer.parseInt(input.trim());

                if (value == 0) {
                    return 0;
                }

                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println(RED + "Choix invalide ! Veuillez entrer un nombre entre "
                            + min + " et " + max + RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Veuillez entrer un nombre valide !" + RESET);
                sc.nextLine();

            }
        }
    }


    public static double readDouble(String message) {
        double value;
        while (true) {
            System.out.print(message);

            if (sc.hasNextDouble()) {
                value = sc.nextDouble();
                if(value == 0) {
                    return 0;
                }
                return value;

            } else {
                System.out.println(RED+"Veuillez entrer un Solde valide !"+ RESET);
                sc.nextLine();
            }
        }
    }
}
