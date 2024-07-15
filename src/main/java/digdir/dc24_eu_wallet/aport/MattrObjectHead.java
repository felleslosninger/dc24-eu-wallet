package digdir.dc24_eu_wallet.aport;

import lombok.Getter;
import lombok.Setter;

public class MattrObjectHead {

    @Getter
    private String challenger;
    @Setter
    @Getter
    private Cred cred;

    private TokenHead token;


    public MattrObjectHead(TokenHead token) {
        this.token = token;
        setCred(new Cred(token));
        setChallenger();
    }

    public void setChallenger() {
        this.challenger = String.valueOf(Math.random());
    }

}
