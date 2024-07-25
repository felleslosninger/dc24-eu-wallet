package digdir.dc24_eu_wallet.idTokens.ansattporten.fromAnsattporten;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the information from the entire payload of the oidc id token.
 *
 * @Author Langbakk
 * @Version 15.07.2024
 */
@Getter
@Setter
public class TokenHead {
    private String sub;
    private ArrayList<String> amr;
    private String iss;
    private String pid;
    private String locale;
    private String nonce;
    private String aud;
    private String acr;
    private List<AutorizationDetails> authorization_details;
    private long authTime;
    private String name;
    private long exp;
    private long iat;
    private String jti;

  /**
   * Constructs the token payload object. Initializes lists for parts that
   * contain several elements.
   *
   */
  public TokenHead() {
        amr = new ArrayList<>();
        authorization_details = new ArrayList<>();
    }

    public List<String> getAmr() {
    return amr;
  }

  public List<AutorizationDetails> getAuthorizationDetails() {
    return authorization_details;
  }

  public void setAuthorizationDetails(List<AutorizationDetails> authorizationDetails) {
    this.authorization_details = authorizationDetails;
  }
}
