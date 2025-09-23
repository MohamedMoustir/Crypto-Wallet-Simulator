package domain.model;

import java.util.UUID;

public abstract class Wallet {

    private UUID id;
    private String type;
    private String address;
    private double balance;

    public Wallet(UUID id, String type, String address, double balance) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.address = address;
        this.balance = balance;
    }

    public Wallet(String type, String address, double balance) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.address = address;
        this.balance = balance;
    }

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
