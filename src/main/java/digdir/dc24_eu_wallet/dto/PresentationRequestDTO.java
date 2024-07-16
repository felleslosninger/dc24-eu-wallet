package digdir.dc24_eu_wallet.dto;

import com.google.gson.annotations.Expose;

/**
 * This class contains the data witch is sent
 * to Mattr to create the QR code.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
public class PresentationRequestDTO {

  @Expose
  private String challenge;
  @Expose
  private String did;
  @Expose
  private String templateId;
  @Expose
  private String callbackUrl;

  public String getChallenge() {
    return challenge;
  }

  public void setChallenge(String challenge) {
    this.challenge = challenge;
  }

  public String getDid() {
    return did;
  }

  public void setDid(String did) {
    this.did = did;
  }

  public String getTemplateId() {
    return templateId;
  }

  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }
}
