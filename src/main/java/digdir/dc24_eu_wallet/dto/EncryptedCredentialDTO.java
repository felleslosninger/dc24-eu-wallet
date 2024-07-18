package digdir.dc24_eu_wallet.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Hold data for credential witch has been encrypted.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
public class EncryptedCredentialDTO {
  private Jwe jwe;

  public Jwe getJwe() {
    return jwe;
  }

  public static class Jwe {
    @SerializedName("protected")
    private String protectedInfo;
    private List<Recipient> recipients;
    private String ciphertext;
    private String iv;
    private String tag;

    // Getters
    public String getProtectedInfo() {
      return protectedInfo;
    }

    public List<Recipient> getRecipients() {
      return recipients;
    }

    public String getCiphertext() {
      return ciphertext;
    }

    public String getIv() {
      return iv;
    }

    public String getTag() {
      return tag;
    }
  }

  public static class Recipient {
    private Header header;
    private String encrypted_key;

    // Getters
    public Header getHeader() {
      return header;
    }

    public String getEncryptedKey() {
      return encrypted_key;
    }

    public void setHeader(Header header) {
      this.header = header;
    }

    public void setEncryptedKey(String encryptedKey) {
      this.encrypted_key = encryptedKey;
    }
  }

  public static class Header {
    private String alg;
    private String kid;
    private String skid;
    private Epk epk;

    // Getters
    public String getAlg() {
      return alg;
    }

    public String getKid() {
      return kid;
    }

    public String getSkid() {
      return skid;
    }

    public Epk getEpk() {
      return epk;
    }
  }

  public static class Epk {
    private String kty;
    private String crv;
    private String x;

    // Getters
    public String getKty() {
      return kty;
    }

    public String getCrv() {
      return crv;
    }

    public String getX() {
      return x;
    }
  }
}
