package digdir.dc24_eu_wallet.aport.toMattr;

import digdir.dc24_eu_wallet.aport.fromAnsattporten.AutorizationDetails;
import digdir.dc24_eu_wallet.aport.fromAnsattporten.Reportee;
import digdir.dc24_eu_wallet.aport.fromAnsattporten.TokenHead;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Topmost class of the payload of the request to MATTR for issuing something. This will hold information which it will
 * get from the TokenHead object, and after that it will be used by GSON to transform into a JSON string based on the
 * objects structure.
 *
 */

public class MattrObjectHead {

    @Getter
    private String challenger;
    @Getter
    private List<Cred> cred;

    private TokenHead token;

    /**
     * Constructs MattrObjectHead.
     *
     * @param token token is the decoded payload part of the OIDC id token from the logged in user.
     */


    public MattrObjectHead(TokenHead token) {

        this.cred = new ArrayList<>();
        this.token = token;
        setChallenger();
        setCred(cred);

    }

    public void setChallenger() {
        this.challenger = String.valueOf(Math.random());
    }

    public void setCred(List<Cred>cred){
        for (Reportee reportee: token.getAuthorizationDetails().get(0).getReportees()) {
            Cred oneCred  = new Cred(token);
            cred.add(oneCred);
        }
    }

    public List<Cred> getCred(){
        return cred;
    }
}
