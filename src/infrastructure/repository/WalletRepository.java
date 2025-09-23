package infrastructure.repository;
import domain.model.Wallet;
import domain.repository.WalletRepositoryInterface;
import infrastructure.persistence.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class WalletRepository implements WalletRepositoryInterface{


    public boolean CreateWallet(Wallet wallet){

       String sql = "INSERT INTO wallet (id ,type , address , balance) VALUES (?,?,?,?)";


        try(Connection conn =  DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); ){
           stmt.setString(1,wallet.getId().toString());
           stmt.setString(2,wallet.getType());
           stmt.setString(3,wallet.getAddress());
           stmt.setDouble(4,wallet.getBalance());
           int rows = stmt.executeUpdate();
           if(rows > 0){
               System.out.println("done");
               return true;
           }

       }catch(Exception e){
          e.printStackTrace();
       }
        return true;

    }
}
