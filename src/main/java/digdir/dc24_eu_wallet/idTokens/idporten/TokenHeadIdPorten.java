package digdir.dc24_eu_wallet.idTokens.idporten;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/* @Author Elise Strand Bråtveit
* @Version 23.07.2024
*/

public class TokenHeadIdPorten {
    
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
    private String guardian_pid;
    @Setter
    @Getter
    private String nonce;
    @Setter
    @Getter
    private String aud;
    @Setter
    @Getter
    private String acr;
    @Setter
    @Getter
    private String ward_pid;
    @Setter
    @Getter
    private long auth_time;
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
  public TokenHeadIdPorten(){
        amr = new ArrayList<String>();
    }

    public List<String> getAmr() {
    return amr;
  }

  public void setAmr(ArrayList<String> amr) {
    this.amr = amr;
  }

   
}
