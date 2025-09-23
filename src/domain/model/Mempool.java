package domain.model;

import java.util.List;
import java.util.ArrayList;

public class Mempool {
    private int  position ;
    List<String> txs = new ArrayList<>() ;

    public Mempool(int position){
            this.position = position ;
    }

    public int getPosition() {return position;}
    public void setPosition(int position) {this.position = position;}

}
