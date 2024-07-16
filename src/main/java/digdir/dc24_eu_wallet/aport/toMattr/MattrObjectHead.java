package digdir.dc24_eu_wallet.aport.toMattr;


import com.google.gson.Gson;
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

    Gson gson =  new Gson();

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



        Credential credential = new Credential();



        for (AutorizationDetails ignored : token.getAuthorizationDetails()) {


            for (Reportee rep: ignored.getReportees()) {
                Credential.Cred credentials = new Credential.Cred();
                credentials.setPid(token.getPid());
                credentials.setSub(token.getSub());

                Credential.Authorization_details authorizationDetails = new Credential.Authorization_details();
                authorizationDetails.setResource(ignored.getResource());
                authorizationDetails.setType(ignored.getType());
                authorizationDetails.setResource_name(ignored.getResource_name());


                Credential.Reportees repo = new Credential.Reportees();
                repo.setId(rep.getID());
                repo.setAuthority(rep.getAuthority());
                repo.setRights(rep.getRights());
                repo.setName(rep.getName());

                authorizationDetails.addReportees(repo);
                credentials.addAuthorization_details(authorizationDetails);
                credential.addCred(credentials);
            }

        }




        System.out.println(gson.toJson(credential));

        //setChallenger();
        //setCred(cred);
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
