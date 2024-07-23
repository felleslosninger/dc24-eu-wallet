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
public class TokenHead {

    @Setter
    @Getter
    private String sub;
    private ArrayList<String> amr;
    @Setter
    @Getter
    private String iss;
    @Setter
    @Getter
    private String pid;
    @Setter
    @Getter
    private String locale;
    @Setter
    @Getter
    private String nonce;
    @Setter
    @Getter
    private String aud;
    @Setter
    @Getter
    private String acr;
    private List<AutorizationDetails> authorization_details;
    @Setter
    @Getter
    private long authTime;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private long exp;
    @Setter
    @Getter
    private long iat;
    @Setter
    @Getter
    private String jti;

  /**
   * Constructs the token payload object. Initializes lists for parts that
   * contain several elements.
   *
   */
  public TokenHead(){
        amr = new ArrayList<String>();
        authorization_details = new ArrayList<>();
    }

    public List<String> getAmr() {
    return amr;
  }

  public void setAmr(ArrayList<String> amr) {
    this.amr = amr;
  }

    public List<AutorizationDetails> getAuthorizationDetails() {
    return authorization_details;
  }

  public void setAuthorizationDetails(List<AutorizationDetails> authorization_details) {
    this.authorization_details = authorization_details;
  }
}
