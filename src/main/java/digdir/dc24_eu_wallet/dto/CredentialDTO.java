package digdir.dc24_eu_wallet.dto;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Information parsed from ansattporten
 * used to create a subject used in CredentialCardDTO.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
@Setter
@Getter
public class CredentialDTO {
  @Expose
  private List<Cred> cred = new ArrayList<>();

  /**
   * Adds a credential to the list.
   *
   * @param cred The credential to be added
   */
  public void addCred(Cred cred) {
    this.cred.add(cred);
  }

  /**
   * Checks if the CredentialDTO is valid.
   *
   * @return true if the credential list is not empty and all credentials are valid, otherwise false.
   */
  public boolean isValid() {
    return cred != null && !cred.isEmpty() && cred.stream().allMatch(Cred::isValid);
  }

  /**
   * Represents a credential with subject, personal identifier, and authorization details.
   */
  @Setter
  @Getter
  public static class Cred {
    @Expose
    private String sub;
    @Expose
    private String pid;
    @Expose
    private List<AuthorizationDetails> authorizationDetails = new ArrayList<>();

    /**
     * Adds authorization details to the credential.
     *
     * @param authorizationDetail The authorization detail to be added
     */
      public void addAuthorizationDetails(AuthorizationDetails authorizationDetail) {
        this.authorizationDetails.add(authorizationDetail);
    }

    /**
     * Checks if the credential is valid.
     *
     * @return true if subject and pid are not empty and all authorization details are valid, otherwise false
     */
    public boolean isValid() {
        return sub != null && !sub.isEmpty() && pid != null && !pid.isEmpty() &&
              authorizationDetails != null && !authorizationDetails.isEmpty() &&
              authorizationDetails.stream().allMatch(AuthorizationDetails::isValid);
    }
  }

  /**
   * Represents the authorization details of a credential.
   */
  @Setter
  @Getter
  public static class AuthorizationDetails {
    @Expose
    private String resource;
    @Expose
    private String type;
    @Expose
    private String resourceName;
    @Expose
    private List<Reportees> reportees = new ArrayList<>();

    /**
     * Adds a reportee to the authorization details.
     *
     * @param reportees The reportee to be added
     */
    public void addReportees(Reportees reportees) {
      this.reportees.add(reportees);
    }

    /**
     * Checks if the authorization details are valid.
     *
     * @return true if resource, type, and resourceName are not empty and all reportees are valid, otherwise false
     */
    public boolean isValid() {
      return resource != null && !resource.isEmpty() &&
              type != null && !type.isEmpty() &&
              resourceName != null && !resourceName.isEmpty() &&
              reportees != null && !reportees.isEmpty() &&
              reportees.stream().allMatch(Reportees::isValid);
    }
  }

  /**
   * Represents the reportees associated with the authorization.
   */
  public static class Reportees {
    @Setter
    @Getter
    @Expose
    private List<String> rights;
    @Setter
    @Getter
    @Expose
    private String authority;
    @Expose
    private String id;
    @Setter
    @Getter
    @Expose
    private String name;

    /**
     * Gets the ID of the reportee.
     *
     * @return The ID of the reportee
     */
      public String getID() {
      return id;
    }

    /**
     * Sets the ID of the reportee.
     *
     * @param id The ID to be set
     */
    public void setID(String id) {
      this.id = id;
    }

    /**
     * Checks if the reportee is valid.
     *
     * @return true if rights, authority, id, and name are not empty, otherwise false
     */
      public boolean isValid() {
      return rights != null && !rights.isEmpty() &&
              authority != null && !authority.isEmpty() &&
              id != null && !id.isEmpty() &&
              name != null && !name.isEmpty();
    }
  }
}