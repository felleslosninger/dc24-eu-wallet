package digdir.dc24_eu_wallet.idTokens.idporten;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import digdir.dc24_eu_wallet.dto.CredentialDTO;
import lombok.Getter;

public class MattrObjectHeadIdPort {

    Gson gson = new Gson();

    @Getter
    @Expose
    private final TokenHeadIdPorten tokenIdPorten;

    @Getter
    private CredentialDTO credential;


    /**
     * Constructs MattrObjectHead.
     *
     * @param tokenIdPorten token is the decoded payload part of the OIDC id token from the logged in user.
     */
    public MattrObjectHeadIdPort(TokenHeadIdPorten tokenIdPorten) {
        this.tokenIdPorten = tokenIdPorten;
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
    public String getFormattedJsonDataIdPorten() {
        CredentialDTO.Cred credentials = new CredentialDTO.Cred();
        credentials.setPid("234982849920");
        credentials.setSub("enfefijifjeo");


        CredentialDTO.AuthorizationDetails authorizationDetails = new CredentialDTO.AuthorizationDetails();
                ArrayList <String> listOfRights = new ArrayList<>();
                listOfRights.add("Read");
                listOfRights.add("Delete");

                authorizationDetails.setResource("urn:idporten");
                authorizationDetails.setType("idporten");
                authorizationDetails.setResource_name("Vergemaal");

                CredentialDTO.Reportees reportees = new CredentialDTO.Reportees();
                reportees.setID("8398598395");
                reportees.setAuthority("Statsforvalteren");
                reportees.setRights(listOfRights);
                reportees.setName("Vergemaal for verge");

                authorizationDetails.addReportees(reportees);
                credentials.addAuthorization_details(authorizationDetails);
                credential.addCred(credentials);

      
        return gson.toJson(credential);
    }

    

    
}
