package util;

import java.util.Scanner;

import static util.validation.RED;
import static util.validation.RESET;

public class ValidateAdresse {
    private static final Scanner sc = new Scanner(System.in);

    public static String validatAdresse(String message) {
        Scanner sc = new Scanner(System.in);
        String adresse;

        
            System.out.print(message);
            adresse = sc.nextLine().trim();

            if (adresse.equals("0")) {
                return null; 
            }

            if (adresse.startsWith("BTC-") || adresse.startsWith("ETH-")) {
                return adresse;  
            } else {
            	
                System.out.println("\u001B[31mVeuillez entrer une adresse valide !\u001B[0m");
                
            }
			return null;
        
    }

    }
