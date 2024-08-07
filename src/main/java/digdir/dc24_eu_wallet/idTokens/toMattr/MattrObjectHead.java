package digdir.dc24_eu_wallet.idTokens.toMattr;

import digdir.dc24_eu_wallet.dto.CredentialDTO;
import digdir.dc24_eu_wallet.idTokens.fromDigdirporten.AutorizationDetails;
import digdir.dc24_eu_wallet.idTokens.fromDigdirporten.Reportee;
import digdir.dc24_eu_wallet.idTokens.fromDigdirporten.TokenHead;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import lombok.Getter;

/**
 * Topmost class of the payload of the request to MATTR for issuing something. This will hold information which it will
 * get from the TokenHead object, and after that it will be used by GSON to transform into a JSON string based on the
 * objects structure.
 *
 * @author Langbakk, Neset & Bråtveit
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
        if(token.iss().contains("https://test.ansattporten.no")){
            for (AutorizationDetails ignored : token.getAuthorizationDetails()) {
                for (Reportee rep : ignored.getReportees()) {
                    CredentialDTO.Cred credentials = new CredentialDTO.Cred();
                    credentials.setPid(token.pid());
                    credentials.setSub(token.sub());
    
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
        } else {
            CredentialDTO.Cred credentials = getCred();
            credential.addCred(credentials);
        }
        return gson.toJson(credential);
    }

    /**
     * Creates and returns a CredentialDTO.Cred object populated with token information and predefined authorization details.
     *
     * @return A populated CredentialDTO.Cred object
     */
    private CredentialDTO.Cred getCred() {
        CredentialDTO.Cred credentials = new CredentialDTO.Cred();
        credentials.setPid(token.pid());
        credentials.setSub(token.sub());

        CredentialDTO.AuthorizationDetails authorizationDetails = new CredentialDTO.AuthorizationDetails();
        ArrayList <String> listOfRights = new ArrayList<>();
        listOfRights.add("Read");
        listOfRights.add("Delete");

        authorizationDetails.setResource("urn:idporten");
        authorizationDetails.setType("idporten");
        authorizationDetails.setResourceName("Vergemaal");

        CredentialDTO.Reportees reportees = new CredentialDTO.Reportees();
        reportees.setID("0000000000");
        reportees.setAuthority("Statsforvalteren");
        reportees.setRights(listOfRights);
        reportees.setName("Vergemaal for verge");

        authorizationDetails.addReportees(reportees);
        credentials.addAuthorizationDetails(authorizationDetails);
        return credentials;
    }
}