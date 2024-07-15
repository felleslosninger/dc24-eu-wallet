package digdir.dc24_eu_wallet.aport;

import lombok.Getter;

import java.util.List;

public class Cred {
    @Getter
    private String sub;
    @Getter
    private String pid;
    @Getter
    private List<AutorizationDetails> authorization_details;
    private TokenHead token;

    public Cred(TokenHead token) {
        this.token = token;
        setAuthorization_details();
        setPid();
        setSub();
    }

    public void setAuthorization_details() {
        authorization_details = token.getAuthorizationDetails();
    }

    public void setPid() {
        pid = token.getPid();
    }

    public void setSub(){
        sub = token.getSub();
    }
}
