package digdir.dc24_eu_wallet.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to hold data about
 * web credential before it will be signed.
 *
 * @author Daniel Neset
 * @version 19.07.2024
 */
@Setter
@Getter
public class CredentialCardDTO {
  private Payload payload;
  private String proofType;
  private String tag;
  private boolean persist;
  private boolean revocable;
  private boolean includeId;

  /**
   * Represents the payload containing credential information.
   */
  @Setter
  @Getter
  public static class Payload {
    private String name;
    private String description;
    private List<String> type;
    private CredentialSubject credentialSubject;
    private CredentialBranding credentialBranding;
    private Issuer issuer;
    private String expirationDate;
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
    private List<AuthorizationDetails> authorizationDetails = new ArrayList<>();

    /**
     * Adds authorization details to the credential.
     *
     * @param authorizationDetails The authorization details to be added
     */
    public void addAuthorizationDetails(AuthorizationDetails authorizationDetails) {
      this.authorizationDetails.add(authorizationDetails);
    }
  }

  /**
   * Represents the authorization details of a credential subject.
   */
  @Setter
  @Getter
  public static class AuthorizationDetails {
    private String resource;
    private String type;
    private String resourceName;
    private List<Reportees> reportees = new ArrayList<>();

    /**
     * Adds a reportee to the list of reportees.
     *
     * @param reportees The reportee to be added
     */
    public void addReportees(Reportees reportees) {
      this.reportees.add(reportees);
    }
  }

  /**
   * Represents the reportees associated with the authorization.
   */
  @Setter
  @Getter
  public static class Reportees {
    private List<String> rights;
    private String authority;
    private String id;
    private String name;
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
    private String iconUrl;
    private String logoUrl;
  }
}