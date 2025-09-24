package util;

import java.util.Scanner;

import static util.validation.RED;
import static util.validation.RESET;

public class ValidateAdresse {
    private static final Scanner sc = new Scanner(System.in);

    public static String validatAdresse( String message) {
        String adresse;

        while (true) {
            System.out.print(message);
            adresse = sc.nextLine();

            if (adresse.startsWith("BTC-")|| adresse.startsWith("ETH-")) {
            return adresse;

            } else {
                System.out.println(RED + "Veuillez entrer un Adrsse valide !" + RESET);

            }
        }
    }
}
