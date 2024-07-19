package digdir.dc24_eu_wallet.aport.toMattr;


import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import digdir.dc24_eu_wallet.aport.fromAnsattporten.AutorizationDetails;
import digdir.dc24_eu_wallet.aport.fromAnsattporten.Reportee;
import digdir.dc24_eu_wallet.aport.fromAnsattporten.TokenHead;
import lombok.Getter;

/**
 * Topmost class of the payload of the request to MATTR for issuing something. This will hold information which it will
 * get from the TokenHead object, and after that it will be used by GSON to transform into a JSON string based on the
 * objects structure.
 *
 * @author Langbakk & Neset
 * @version 17.07.2024
 */
public class MattrObjectHead {

    Gson gson = new Gson();

    @Getter
    @Expose
    private final TokenHead token;

    private Credential credential;

    /**
     * Constructs MattrObjectHead.
     *
     * @param token token is the decoded payload part of the OIDC id token from the logged in user.
     */
    public MattrObjectHead(TokenHead token) {
        this.token = token;
        this.credential = new Credential();
    }

    /**
     * Getter for the correctly formed string in json format for the data that we send to MATTR to issue rights
     * and/or accesses. It goes through the object of the token payload part, and takes the parts that is supposed
     * to go to MATTR, and puts it into the new object, which represents the structure that MATTR needs.
     * Converts to json in the end.
     *
     * @return json data in a string.
     */
    public String getFormattedJsonData() {

        for (AutorizationDetails ignored : token.getAuthorizationDetails()) {
            for (Reportee rep : ignored.getReportees()) {
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
        return gson.toJson(credential);
    }

    public Credential getCredential(){
        return credential;
    }

}
