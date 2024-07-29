package digdir.dc24_eu_wallet.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Saved data about a signed web credential.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
@Setter
@Getter
public class CredentialSignDTO {
  private String id;
  private String tag;
  private Credential credential;
  private CredentialStatus credentialStatus;
  private String issuanceDate;

  /**
   * Represents a credential containing various information such as the issuer, subject, branding, and status.
   */
  @Setter
  @Getter
  public static class Credential {
    private String id;
    private List<String> type;
    private Issuer issuer;
    private String name;
    private String description;
    private CredentialBranding credentialBranding;
    private String expirationDate;
    private String issuanceDate;
    private CredentialSubject credentialSubject;

    @SerializedName("@context")
    private List<String> context;
    private CredentialStatus credentialStatus;
    private Proof proof;

  }

  /**
   * Represents the branding details of a credential.
   */
  @Setter
  @Getter
  public static class CredentialBranding {
    private String backgroundColor;
    private String watermarkImageUrl;

  }

  /**
   * Represents the issuer of a credential.
   */
  @Setter
  @Getter
  public static class Issuer {
    private String id;
    private String name;
    private String logoUrl;
    private String iconUrl;

  }

  /**
   * Represents the subject of a credential.
   */
  @Setter
  @Getter
  public static class CredentialSubject {
    private String id;
    private String sub;
    private String pid;
    private List<CredentialCardDTO.AuthorizationDetails> authorizationDetails;
  }

  /**
   * Represents the authorization details of a credential.
   */
  @Setter
  @Getter
  public static class AuthorizationDetails {
    private String resource;
    private String type;
    private String resourceName;
    private List<CredentialCardDTO.Reportees> reportees = new ArrayList<>();

    public void addReportees(CredentialCardDTO.Reportees reportees) {
      this.reportees.add(reportees);
    }
  }

  /**
   * Represents the reportees associated with the authorization.
   */
  @Setter
  @Getter
  public static class Reportees {
    private List<String> Rights;
    private String Authority;
    private String ID;
    private String Name;
  }

  /**
   * Represents the status of a credential, including revocation details.
   */
  @Setter
  @Getter
  public static class CredentialStatus {
    private String id;
    private String type;
    private String revocationListIndex;
    private String revocationListCredential;

  }

  /**
   * Represents the proof associated with a credential.
   */
  @Setter
  @Getter
  public static class Proof {
    private String type;
    private String created;
    private String verificationMethod;
    private String proofPurpose;
    private String jws;
  }
}