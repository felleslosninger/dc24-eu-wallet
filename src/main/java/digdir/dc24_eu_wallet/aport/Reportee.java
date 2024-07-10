package digdir.dc24_eu_wallet.aport;


import java.util.ArrayList;
import java.util.List;

public class Reportee {
    //private ArrayList<String> Rights;
    private ArrayList<String> Rights;
    private String Authority;
    private String ID;
    private String Name;
   // private ArrayList<String>reportees;

    public Reportee(){

    }

    public void setRights(ArrayList<String>Rights){
        this.Rights = Rights;
    }
    public ArrayList<String> getRights() {
        return Rights;
    }

    public String getAuthority() {
        return Authority;
    }

    public void setAuthority(String authority) {
        this.Authority = authority;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
