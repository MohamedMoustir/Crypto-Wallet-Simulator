package infrastructure.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import domain.model.Transaction;
import domain.model.Wallet;
import domain.repository.WalletRepositoryInterface;
import domain.service.WalletService;
import infrastructure.persistence.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;

public class WalletRepository implements WalletRepositoryInterface{
    private static final Logger logger = LoggerFactory.getLogger(WalletRepository.class);

         private static WalletRepository instanceWallet;

         public static WalletRepository getInstanceWallet(){
             if (instanceWallet == null) {
                 instanceWallet = new WalletRepository();
             }
             return instanceWallet;
         }
         
    public boolean CreateWallet(Wallet wallet){

       String sql = "INSERT INTO wallet (wallet_id ,type , address , balance) VALUES (?,?,?,?)";

        try(Connection conn =  DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); ){
           stmt.setString(1,wallet.getId().toString());
           stmt.setString(2,wallet.getType());
           stmt.setString(3,wallet.getAddress());
           stmt.setDouble(4,wallet.getBalance());
           int rows = stmt.executeUpdate();
           if(rows > 0){
               try(ResultSet res = stmt.getGeneratedKeys()){
                 if(res.next()){
                     String id = String.valueOf(res.getInt(1));
                     wallet.setId(UUID.fromString(id));
                     logger.error(" Wallet {} créée avec succès." ,wallet.getId());

                     return true;
                 }

               }catch(Exception e) {
                   logger.error(" Erreur (DB) lors de la création de la Wallet: {}", e.getMessage(), e);
                   return false;
               }
           }

       }catch(Exception e){
            logger.error(" Erreur (DB) lors de la création de la Wallite: {}", e.getMessage(), e);

        }
        return true;

    }


    public Wallet findByAddress(String address) {
        String sql = "SELECT * FROM wallet WHERE address = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,address.toString() );
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                String type = rs.getString("type");
                double balance = rs.getDouble("balance");
                String Aaddress = rs.getString("address");
                String wallet_id = rs.getString("wallet_id");

                Wallet wallet = new Transaction(type, Aaddress, balance);
                wallet.setId(UUID.fromString(wallet_id));
                
                return wallet;
            }

        } catch (Exception e) {
            logger.error("Erreur lors de la recherche du wallet pour la transaction: {}", e.getMessage(), e);

        }
        return null;
    }

    public boolean UbdateBalance( String address ,double balance  ){

        String sql = "UPDATE  wallet SET  balance = ?  WHERE address = ?";

        try(Connection conn =  DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ){

            stmt.setDouble(1,balance);
            stmt.setString(2,address.toString());

            int rows = stmt.executeUpdate();
            if(rows > 0){
                try(ResultSet res = stmt.getGeneratedKeys()){

                        return true;
                }catch(Exception e) {
                    return false;
                }
            }

        }catch(Exception e){
            logger.error("Erreur lors de mes ajouts : {}", e.getMessage(), e);
        }
        return false ;
    }
}
