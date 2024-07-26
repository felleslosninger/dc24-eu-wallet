package digdir.dc24_eu_wallet.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Hold data for credential witch has been encrypted.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
@Getter
public class EncryptedCredentialDTO {
  private Jwe jwe;

  /**
   * Represents th JSON Web Encryption (JWE) structure.
   */
  @Getter
  public static class Jwe {

    // Getters
    @SerializedName("protected")
    private String protectedInfo;
    private List<Recipient> recipients;
    private String ciphertext;
    private String iv;
    private String tag;
  }

  /**
   * Represents a recipient of the JSON Web Encryption (JWE).
   */
  @Setter
  @Getter
  public static class Recipient {

    // Getters
    private Header header;
    private String encryptedKey;
  }

  /**
   * Represents the header of a recipient in the JSON Web Encryption (JWE).
   */
  @Getter
  public static class Header {

    // Getters
    private String alg;
    private String kid;
    private String skid;
    private Epk epk;
  }

  /**
   * Represents the Ephemeral Public Key (EPK) used in the encryption process within the JWE header.
   */
  @Getter
  public static class Epk {

    // Getters
    private String kty;
    private String crv;
    private String x;
  }
}