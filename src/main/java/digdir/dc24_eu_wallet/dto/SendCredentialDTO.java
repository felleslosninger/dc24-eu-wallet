package digdir.dc24_eu_wallet.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the data witch is used to send
 * an encrypted credential to the wallet.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
@Setter
@Getter
public class SendCredentialDTO {
  private String to;
  private JweMessage message;

  /**
   * Constructs a SendCredentialDTO with the given parameters.
   *
   * @param to The recipient of the credential
   * @param message The JWE (JSON Web Encryption) message to be sent
   */
  public SendCredentialDTO(String to, JweMessage message) {
    this.to = to;
    this.message = message;
  }

  /**
   * Represents a JSON Web Encryption (JWE) message.
   */
  @Setter
  @Getter
  public static class JweMessage {
    @SerializedName("protected")
    private String protectedInfo;
    private List<Recipient> recipients = new ArrayList<>();
    private String ciphertext;
    private String iv;
    private String tag;

    /**
     * Adds a recipient to the list of recipients.
     *
     * @param recipient The Recipient object to be added to the list
     */
      public void addRecipients(Recipient recipient) {
        this.recipients.add(recipient);
    }

    /**
     * Gets the protected information.
     *
     * @return The protected information as a String
     */
      public String getProtected() {
        return protectedInfo;
    }

    /**
     * Sets the protected information.
     *
     * @param protectedInfo The protected information to set
     */
    public void setProtected(String protectedInfo) {
        this.protectedInfo = protectedInfo;
    }
  }

  /**
   * Represents a recipient of the JSON Web Encryption (JWE) message.
   */
  @Setter
  @Getter
  public static class Recipient {
    private Header header;
    private String encrypted_key;
  }

  /**
   * Represents the header of a recipient in the JSON Web Encryption (JWE) message.
   */
  @Setter
  @Getter
  public static class Header {
    private String alg;
    private String kid;
    private String skid;
    private Epk epk;
  }

  /**
   * Represents the Ephemeral Public Key (EPK) used in the encryption process within the JWE header.
   */
  @Setter
  @Getter
  public static class Epk {
    private String kty;
    private String crv;
    private String x;
  }
    // Getters and Setters for NewMessageStructure
}