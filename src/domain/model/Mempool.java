package domain.model;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

public class Mempool  {
	
    private int  positioninqueue ;
    private int estimatedTime;  
    private String transactionId ;
    private double fee ;
    
    List<String> txs = new ArrayList<>() ;

    public Mempool(String transactionid2  ,double fee ) {
    	
	 this.fee = fee;
	 this.transactionId = transactionid2;

	}

    public double getFee() {return fee;}
    public void setFee(int fee) {this.fee = fee;}
    public String getTransactionId() {return transactionId;}
    public void setTransactionId(String transactionId) {this.transactionId = transactionId;}

}
