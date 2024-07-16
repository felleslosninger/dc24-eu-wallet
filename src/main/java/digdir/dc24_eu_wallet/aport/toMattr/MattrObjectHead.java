package digdir.dc24_eu_wallet.aport.toMattr;


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

    private final TokenHead token;
    private List<Reportee> reportees;

    /**
     * Constructs MattrObjectHead.
     *
     * @param token token is the decoded payload part of the OIDC id token from the logged in user.
     */


    public MattrObjectHead(TokenHead token) {
        this.cred = new ArrayList<>();
        this.token = token;
        reportees = token.getAuthorizationDetails().get(0).getReportees();
        System.out.println("Size of the array with all the reportees : "+reportees.size());
        System.out.println("This is the reportees that are registered ion MattrObjectHead  "+ reportees.get(0).getName());
        System.out.println("This is the reportees that are registered ion MattrObjectHead  "+ reportees.get(1).getName());
        System.out.println("This is the reportees that are registered ion MattrObjectHead  "+ reportees.get(2).getName());
        System.out.println("This is the reportees that are registered ion MattrObjectHead  "+ reportees.get(3).getName());
        System.out.println("This is the reportees that are registered ion MattrObjectHead  "+ reportees.get(4).getName());
        setChallenger();
        setCred(cred);
        System.out.println("Size of the array with all the cred : "+cred.size());
    }

    public void setChallenger() {
        this.challenger = String.valueOf(Math.random());
    }

    public void setCred(List<Cred>cred){
        for (Reportee reportee: reportees) {
            cred.add(new Cred(token, reportee));
        }
    }
}
