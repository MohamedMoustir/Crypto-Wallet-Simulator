package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import domain.model.Transaction;

public class CSVExporter {

	
	public static void exportTransactionsToCSV ( List<Map<String, Object>> transactions ,String fileName) {
		File file = new File(fileName);
		file.getParentFile().mkdir();

		try(FileWriter writer = new FileWriter(fileName)){

			writer.append("Source,Destination,Montant,Fees,Priority,Status\n");
            
			 for (Map<String, Object> tx : transactions) {
	                writer.append((String) tx.get("source")).append(",");
	                writer.append((String) tx.get("destination")).append(",");
	                writer.append(String.valueOf(tx.get("montant"))).append(",");
	                writer.append(String.valueOf(tx.get("fees"))).append(",");
	                writer.append((String) tx.get("priority")).append(",");
	                writer.append((String) tx.get("status")).append("\n");
	            }
			 	
	            System.out.println("Export terminé avec succès !");

		}catch(IOException  e) {
            System.out.println("Erreur lors de l'export CSV : " + e.getMessage());

		}
	};
}
