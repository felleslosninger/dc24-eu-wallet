package digdir.dc24_eu_wallet.dto;

import com.google.gson.annotations.Expose;

public class PresentationResponseDTO {

  @Expose
  private String id;
  @Expose
  private String callbackUrl;
  @Expose
  private String expiresTime;
  @Expose
  private String didcommUri;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  public String getExpiresTime() {
    return expiresTime;
  }

  public void setExpiresTime(String expiresTime) {
    this.expiresTime = expiresTime;
  }

  public String getDidcommUri() {
    return didcommUri;
  }

  public void setDidcommUri(String didcommUri) {
    this.didcommUri = didcommUri;
  }
}
