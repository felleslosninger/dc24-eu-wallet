package digdir.dc24_eu_wallet.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the data witch is used to send
 * an encrypted credential to the wallet.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
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
  public static class JweMessage {
    @SerializedName("protected")
    private String protectedInfo;
    private List<Recipient> recipients = new ArrayList<>();
    private String ciphertext;
    private String iv;
    private String tag;

    public String getProtectedInfo() {
      return protectedInfo;
    }

    public void setProtectedInfo(String protectedInfo) {
      this.protectedInfo = protectedInfo;
    }

    public List<Recipient> getRecipients() {
      return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
      this.recipients = recipients;
    }

    public void addRecipients(Recipient recipient) {
      this.recipients.add(recipient);
    }

    public String getCiphertext() {
      return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
      this.ciphertext = ciphertext;
    }

    public String getIv() {
      return iv;
    }

    public void setIv(String iv) {
      this.iv = iv;
    }

    public String getTag() {
      return tag;
    }

    public void setTag(String tag) {
      this.tag = tag;
    }

    public String getProtected() {
      return protectedInfo;
    }

    public void setProtected(String protectedInfo) {
      this.protectedInfo = protectedInfo;
    }
  }

  /**
   * Represents a recipient of the JSON Web Encryption (JWE) message.
   */
  public static class Recipient {
    private Header header;

    private String encrypted_key;

    public Header getHeader() {
      return header;
    }

    public void setHeader(Header header) {
      this.header = header;
    }

    public String getEncryptedKey() {
      return encrypted_key;
    }

    public void setEncryptedKey(String encryptedKey) {
      this.encrypted_key = encryptedKey;
    }
  }

  /**
   * Represents the header of a recipient in the JSON Web Encryption (JWE) message.
   */
  public static class Header {
    private String alg;
    private String kid;
    private String skid;
    private Epk epk;

    public String getAlg() {
      return alg;
    }

    public void setAlg(String alg) {
      this.alg = alg;
    }

    public String getKid() {
      return kid;
    }

    public void setKid(String kid) {
      this.kid = kid;
    }

    public String getSkid() {
      return skid;
    }

    public void setSkid(String skid) {
      this.skid = skid;
    }

    public Epk getEpk() {
      return epk;
    }

    public void setEpk(Epk epk) {
      this.epk = epk;
    }
  }

  /**
   * Represents the Ephemeral Public Key (EPK) used in the encryption process within the JWE header.
   */
  public static class Epk {
    private String kty;
    private String crv;
    private String x;

    public String getKty() {
      return kty;
    }

    public void setKty(String kty) {
      this.kty = kty;
    }

    public String getCrv() {
      return crv;
    }

    public void setCrv(String crv) {
      this.crv = crv;
    }

    public String getX() {
      return x;
    }

    public void setX(String x) {
      this.x = x;
    }
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public JweMessage getMessage() {
    return message;
  }

  public void setMessage(JweMessage message) {
    this.message = message;
  }
// Getters and Setters for NewMessageStructure
}


