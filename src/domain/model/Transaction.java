package domain.model;
import java.util.UUID;

import static java.util.UUID.randomUUID;

public class Transaction extends Wallet {

    private UUID id ;
    private String source;
    private String destination;
    private double amount ;
    private double fees ;
    private String priority;
    private String status = "PENDING";


    public Transaction (UUID id_Wallet ,String type, String address , double balance   ,String source ,String destination,double amount ,double fees,String priority ){
        super( id_Wallet,type,  address , balance );
        this.id = UUID.randomUUID();
        this.source = source ;
        this.destination = destination ;
        this.amount = amount;
        this.fees = fees ;
        this.priority = priority ;
    }


    public Transaction (String type, String address , double balance   ){
        super( type,  address , balance );
    }




    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public double getFees() { return fees; }
    public void setFees(double fees) { this.fees = fees; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    

}
