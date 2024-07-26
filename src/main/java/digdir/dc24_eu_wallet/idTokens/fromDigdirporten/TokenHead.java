package digdir.dc24_eu_wallet.idTokens.fromDigdirporten;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the information from the entire payload of the oidc id token.
 *
 * @Author Langbakk & Bråtveit
 * @Version 15.07.2024
 */
public record TokenHead(
    String sub,
    ArrayList<String> amr,
    String iss,
    String pid,
    String locale,
    String nonce,
    String aud,
    String acr,
    List<AutorizationDetails> authorization_details,
    long authTime,
    String name,
    long exp,
    long iat,
    String jti)
{

    /**
     * Constructs the token payload object. Initializes lists for parts that
     * contain several elements.
     */
    public TokenHead {
        if (amr == null) {
            amr = new ArrayList<>();
        }
        if (authorization_details == null) {
            authorization_details = new ArrayList<>();
        }
    }

    /**
     * Gets the list of Authentication Methods References.
     *
     * @return A list of AMR values
     */
    public List<String> getAmr() {
        return amr;
    }

    /**
     * Gets the list of authorization details.
     *
     * @return A list of AuthorizationDetails objects
     */
    public List<AutorizationDetails> getAuthorizationDetails() {
        return authorization_details;
    }
}