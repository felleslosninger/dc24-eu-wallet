package digdir.dc24_eu_wallet.aport;

import java.util.ArrayList;
import java.util.List;


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

    public TokenHead(){
        amr = new ArrayList<String>();
        authorization_details = new ArrayList<>();
    }

  public String getSub() {
    return sub;
  }

  public void setSub(String sub) {
    this.sub = sub;
  }

  public List<String> getAmr() {
    return amr;
  }

  public void setAmr(ArrayList<String> amr) {
    this.amr = amr;
  }
  public String getIss() {
    return iss;
  }

  public void setIss(String iss) {
    this.iss = iss;
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }

  public String getNonce() {
    return nonce;
  }

  public void setNonce(String nonce) {
    this.nonce = nonce;
  }

  public String getAud() {
    return aud;
  }

  public void setAud(String aud) {
    this.aud = aud;
  }

  public String getAcr() {
    return acr;
  }

  public void setAcr(String acr) {
    this.acr = acr;
  }

  public List<AutorizationDetails> getAuthorizationDetails() {
    return authorization_details;
  }

  public void setAuthorizationDetails(List<AutorizationDetails> authorization_details) {
    this.authorization_details = authorization_details;
  }

  public long getAuthTime() {
    return authTime;
  }

  public void setAuthTime(long authTime) {
    this.authTime = authTime;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getExp() {
    return exp;
  }

  public void setExp(long exp) {
    this.exp = exp;
  }

  public long getIat() {
    return iat;
  }

  public void setIat(long iat) {
    this.iat = iat;
  }

  public String getJti() {
    return jti;
  }

  public void setJti(String jti) {
    this.jti = jti;
  }
}
