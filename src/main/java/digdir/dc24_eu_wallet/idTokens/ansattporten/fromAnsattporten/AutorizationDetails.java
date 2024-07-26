package digdir.dc24_eu_wallet.idTokens.ansattporten.fromAnsattporten;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds information from the authorization_details field in the payload
 * of oidc id token.
 *
 * @author Langbakk
 * @version 16.07.2024
 */
@Setter
@Getter
public class AutorizationDetails {
    private String resource;
    private String type;
    private String resource_name;
    private List<Reportee> reportees;
    private String authority;
    private String id;
    private String name;

    /**
     * Initializes a new instance of AuthorizationDetails.
     */
    public AutorizationDetails() {
        // Left empty for flexible instantiation
    }

    /**
     * Sets the reportee for the authorization details.
     *
     * @param reportee The Reportee object to be added to the list of reportees.
     */
    public void mattrSetReportee(Reportee reportee) {
        this.reportees = new ArrayList<>();
        //ArrayList <Reportee>reportees = new ArrayList<>();
        reportees.add(reportee);
    }
}