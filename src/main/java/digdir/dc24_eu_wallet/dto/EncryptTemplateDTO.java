package digdir.dc24_eu_wallet.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Template before the web credential is encrypted.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
public class EncryptTemplateDTO {
  private String senderDidUrl;
  private List<String> recipientDidUrls;
  private Payload payload;

  /**
   * Constructs an EncryptedTemplateDTO with the given parameters.
   *
   * @param senderDidUrl URL of the sender's DID (Decentralized Identifier)
   * @param recipientDidUrls List of URLs of the recipients' DIDs
   * @param payload The payload to be encrypted and sent
   */
  public EncryptTemplateDTO(String senderDidUrl, List<String> recipientDidUrls, Payload payload) {
    this.senderDidUrl = senderDidUrl;
    this.recipientDidUrls = recipientDidUrls;
    this.payload = payload;
  }

  /**
   * Represents the payload of an encrypted message.
   */
  public static class Payload {
    private String id;
    private String type;
    private List<String> to;
    private String from;
    private long created_time;
    private Body body;

    public Body getBody() {
      return body;
    }

    public void setBody(Body body) {
      this.body = body;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public List<String> getTo() {
      return to;
    }

    public void setTo(List<String> to) {
      this.to = to;
    }

    public String getFrom() {
      return from;
    }

    public void setFrom(String from) {
      this.from = from;
    }

    public long getCreated_time() {
      return created_time;
    }

    public void setCreated_time(long created_time) {
      this.created_time = created_time;
    }
  }

  /**
   * Represents the body of the payload, containing credentials and domain information.
   */
  public static class Body{
    private List<CredentialSignDTO.Credential> credentials = new ArrayList<>();
    private String domain;

    public List<CredentialSignDTO.Credential> getCredentials() {
      return credentials;
    }

    public void setCredentials(List<CredentialSignDTO.Credential> credentials) {
      this.credentials = credentials;
    }

    public void addCredentials(CredentialSignDTO.Credential credential) {
      this.credentials.add(credential);
    }

    public String getDomain() {
      return domain;
    }

    public void setDomain(String domain) {
      this.domain = domain;
    }
  }

  public String getSenderDidUrl() {
    return senderDidUrl;
  }

  public void setSenderDidUrl(String senderDidUrl) {
    this.senderDidUrl = senderDidUrl;
  }

  public List<String> getRecipientDidUrls() {
    return recipientDidUrls;
  }

  public void setRecipientDidUrls(List<String> recipientDidUrls) {
    this.recipientDidUrls = recipientDidUrls;
  }

  public Payload getPayload() {
    return payload;
  }

  public void setPayload(Payload payload) {
    this.payload = payload;
  }
}
