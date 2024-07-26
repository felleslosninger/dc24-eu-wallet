package digdir.dc24_eu_wallet.idTokens.ansattporten.fromAnsattporten;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * Holds information from the Reportees field in the payload
 * of oidc id token.
 */
@Setter
@Getter
public class Reportee {
    private ArrayList<String> Rights;
    private String Authority;
    private String ID;
    private String Name;

    /**
     * Initializes a new instance of Reportee.
     */
    public Reportee() {
        // Left empty for flexible instantiation
    }
}
