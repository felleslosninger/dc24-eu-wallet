package digdir.dc24_eu_wallet.aport;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MattrObjectHead {

    @Getter
    private String challenger;
    @Getter
    private List<Cred> cred;

    private TokenHead token;


    public MattrObjectHead(TokenHead token) {

        this.cred = new ArrayList<>();
        this.token = token;
        setChallenger();
        setCred();

    }

    public void setChallenger() {
        this.challenger = String.valueOf(Math.random());
    }

    public void setCred(){
        this.cred.add(new Cred(token));
    }

    public List<Cred> getCred(){
        return cred;
    }
}
