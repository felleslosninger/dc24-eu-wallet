package digdir.dc24_eu_wallet.idTokens.ansattporten.toMattr;

import digdir.dc24_eu_wallet.idTokens.ansattporten.fromAnsattporten.AutorizationDetails;
import digdir.dc24_eu_wallet.idTokens.ansattporten.fromAnsattporten.Reportee;
import digdir.dc24_eu_wallet.idTokens.ansattporten.fromAnsattporten.TokenHead;
import digdir.dc24_eu_wallet.dto.CredentialDTO;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

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

    @Getter
    private CredentialDTO credential;

    /**
     * Constructs MattrObjectHead.
     *
     * @param token token is the decoded payload part of the OIDC id token from the logged in user.
     */
    public MattrObjectHead(TokenHead token) {
        this.token = token;
        this.credential = new CredentialDTO();
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
                CredentialDTO.Cred credentials = new CredentialDTO.Cred();
                credentials.setPid(token.getPid());
                credentials.setSub(token.getSub());

                CredentialDTO.AuthorizationDetails authorizationDetails = new CredentialDTO.AuthorizationDetails();
                authorizationDetails.setResource(ignored.getResource());
                authorizationDetails.setType(ignored.getType());
                authorizationDetails.setResourceName(ignored.getResource_name());

                CredentialDTO.Reportees reportees = new CredentialDTO.Reportees();
                reportees.setID(rep.getID());
                reportees.setAuthority(rep.getAuthority());
                reportees.setRights(rep.getRights());
                reportees.setName(rep.getName());

                authorizationDetails.addReportees(reportees);
                credentials.addAuthorizationDetails(authorizationDetails);
                credential.addCred(credentials);
            }
        }
        return gson.toJson(credential);
    }
}