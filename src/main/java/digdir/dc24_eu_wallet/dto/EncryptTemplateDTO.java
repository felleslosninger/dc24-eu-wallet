package digdir.dc24_eu_wallet.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Template before the web credential is encrypted.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
@Setter
@Getter
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
  @Setter
  @Getter
  public static class Payload {
    private String id;
    private String type;
    private List<String> to;
    private String from;
    private long createdTime;
    private Body body;
  }

  /**
   * Represents the body of the payload, containing credentials and domain information.
   */
  @Setter
  @Getter
  public static class Body {
    private List<CredentialSignDTO.Credential> credentials = new ArrayList<>();
    private String domain;

      public void addCredentials(CredentialSignDTO.Credential credential) {
      this.credentials.add(credential);
    }
  }
}